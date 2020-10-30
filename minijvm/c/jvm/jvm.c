
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include "jvm.h"
#include "garbage.h"
#include "jvm_util.h"
#include "jdwp.h"


void thread_boundle(Runtime *runtime) {

    JClass *thread_clazz = classes_load_get_c(NULL, STR_CLASS_JAVA_LANG_THREAD, runtime);
    //为主线程创建Thread实例
    Instance *t = instance_create(runtime, thread_clazz);
    instance_hold_to_thread(t, runtime);
    runtime->thrd_info->jthread = t;//Thread.init currentThread() need this
    instance_init(t, runtime);
    jthread_init(t, runtime);
    instance_release_from_thread(t, runtime);

}

void thread_unboundle(Runtime *runtime) {

    runtime->thrd_info->is_suspend = 1;
    Instance *t = runtime->thrd_info->jthread;
    //主线程实例被回收
    jthread_dispose(t, runtime);
}

void print_exception(Runtime *runtime) {
#if _JVM_DEBUG_LOG_LEVEL > 1
    if (runtime) {
        Utf8String *stacktrack = utf8_create();
        getRuntimeStack(runtime, stacktrack);
        jvm_printf("%s\n", utf8_cstr(stacktrack));
        utf8_destory(stacktrack);

        runtime_clear_stacktrack(runtime);
    }
#endif
}

#if _JVM_DEBUG_PROFILE

void profile_init() {
    memset(&profile_instructs, 0, sizeof(ProfileDetail) * INST_COUNT);
}

void profile_put(u8 instruct_code, s64 cost_add, s64 count_add) {
    ProfileDetail *h_s_v = &profile_instructs[instruct_code];

    spin_lock(&pro_lock);
    h_s_v->cost += cost_add;
    h_s_v->count += count_add;
    spin_unlock(&pro_lock);

};

void profile_print() {
    s32 i;
    jvm_printf("id           total    count      avg inst  \n");
    for (i = 0; i < INST_COUNT; i++) {
        ProfileDetail *pd = &profile_instructs[i];
        jvm_printf("%2x %15lld %8d %8lld %s\n",
                   i | 0xffffff00, pd->cost, pd->count, pd->count ? (pd->cost / pd->count) : 0, inst_name[i]);
    }
}

#endif

ClassLoader *classloader_create(c8 *path) {
    ClassLoader *class_loader = jvm_calloc(sizeof(ClassLoader));
    spin_init(&class_loader->lock, 0);

    //split classpath
    class_loader->classpath = arraylist_create(0);
    Utf8String *g_classpath = utf8_create_c(path);
    Utf8String *tmp = NULL;
    s32 i = 0;
    while (i < g_classpath->length) {
        if (tmp == NULL) {
            tmp = utf8_create();
        }
        c8 ch = utf8_char_at(g_classpath, i++);
        if (i == g_classpath->length) {
            if (ch != ';' && ch != ':')utf8_insert(tmp, tmp->length, ch);
            ch = ';';
        }
        if (ch == ';' || ch == ':') {
            if (utf8_last_indexof_c(tmp, "/") == tmp->length - 1)
                utf8_remove(tmp, tmp->length - 1);
            arraylist_push_back(class_loader->classpath, tmp);
            tmp = NULL;
        } else {
            utf8_insert(tmp, tmp->length, ch);
        }
    }
    utf8_destory(g_classpath);
    //创建类容器
    class_loader->classes = hashtable_create(UNICODE_STR_HASH_FUNC, UNICODE_STR_EQUALS_FUNC);

    //创建jstring 相关容器
    class_loader->table_jstring_const = hashtable_create(UNICODE_STR_HASH_FUNC, UNICODE_STR_EQUALS_FUNC);

    return class_loader;
}

void classloader_destory(ClassLoader *class_loader) {
    HashtableIterator hti;
    hashtable_iterate(class_loader->classes, &hti);
    for (; hashtable_iter_has_more(&hti);) {
        HashtableValue v = hashtable_iter_next_value(&hti);
        gc_obj_release(class_loader->jvm->collector, v);
    }

    hashtable_clear(class_loader->classes);
    s32 i;
    for (i = 0; i < class_loader->classpath->length; i++) {
        utf8_destory(arraylist_get_value(class_loader->classpath, i));
    }
    arraylist_destory(class_loader->classpath);
    hashtable_destory(class_loader->classes);

    hashtable_destory(class_loader->table_jstring_const);

    class_loader->classes = NULL;

    spin_destroy(&class_loader->lock);
    jvm_free(class_loader);
}

void classloader_release_classs_static_field(ClassLoader *class_loader) {
    HashtableIterator hti;
    hashtable_iterate(class_loader->classes, &hti);
    for (; hashtable_iter_has_more(&hti);) {
        HashtableValue v = hashtable_iter_next_value(&hti);
        JClass *clazz = (JClass *) (v);
        class_clear_refer(class_loader, clazz);
    }
}

void classloader_add_jar_path(ClassLoader *class_loader, Utf8String *jarPath) {
    s32 i;
    for (i = 0; i < class_loader->classpath->length; i++) {
        if (utf8_equals(arraylist_get_value(class_loader->classpath, i), jarPath)) {
            return;
        }
    }
    Utf8String *jarPath1 = utf8_create_copy(jarPath);
    arraylist_push_back(class_loader->classpath, jarPath1);
}

void set_jvm_state(MiniJVM *jvm, int state) {
    jvm->jvm_state = state;
}

int get_jvm_state(MiniJVM *jvm) {
    return jvm->jvm_state;
}

void _on_jvm_sig_print(int no) {
    jvm_printf("[SIGNAL]jvm sig:%d  errno: %d , %s\n", no, errno, strerror(errno));
}

void _on_jvm_sig(int no) {
    _on_jvm_sig_print(no);
    exit(no);
}

MiniJVM *jvm_create() {
    MiniJVM *jvm = jvm_calloc(sizeof(MiniJVM));
    if (!jvm) {
        jvm_printf("jvm create error.");
        return NULL;
    }
    jvm->env = &jnienv;
    jvm->max_heap_size = MAX_HEAP_SIZE_DEFAULT;
    jvm->heap_overload_percent = GARBAGE_OVERLOAD_DEFAULT;
    jvm->garbage_collect_period_ms = GARBAGE_PERIOD_MS_DEFAULT;
    return jvm;
}

s32 jvm_init(MiniJVM *jvm, c8 *p_bootclasspath, c8 *p_classpath) {
    if (!jvm) {
        jvm_printf("jvm not found.");
        return -1;
    }

    signal(SIGABRT, _on_jvm_sig);
    signal(SIGFPE, _on_jvm_sig);
    signal(SIGSEGV, _on_jvm_sig);
    signal(SIGTERM, _on_jvm_sig);
#ifdef SIGPIPE
    signal(SIGPIPE, _on_jvm_sig_print); //not exit when network sigpipe
#endif

    set_jvm_state(jvm, JVM_STATUS_INITING);

    if (!p_classpath) {
        p_bootclasspath = "./";
    }

    //
    open_log();

#if _JVM_DEBUG_PROFILE
    profile_init();
#endif
    //
    init_jni_func_table(jvm);

    //创建线程容器
    jvm->thread_list = arraylist_create(0);
    //创建垃圾收集器
    gc_create(jvm);

    //本地方法库
    jvm->native_libs = arraylist_create(0);
    reg_std_native_lib(jvm);
    reg_net_native_lib(jvm);
    reg_reflect_native_lib(jvm);

    jvm->boot_classloader = classloader_create(p_bootclasspath);
    jvm->boot_classloader->jvm = jvm;

    //装入系统属性
    sys_properties_load(jvm);
    sys_properties_set_c(jvm, "java.class.path", p_classpath);
    sys_properties_set_c(jvm, "sun.boot.class.path", p_bootclasspath);

    //启动调试器
    jdwp_start_server(jvm);

    set_jvm_state(jvm, JVM_STATUS_RUNNING);

    //init load thread, string etc
    Runtime *runtime = runtime_create(jvm);
    Utf8String *clsName = utf8_create_c(STR_CLASS_JAVA_LANG_INTEGER);
    classes_load_get(NULL, clsName, runtime);
    utf8_clear(clsName);
    utf8_append_c(clsName, STR_CLASS_JAVA_LANG_THREAD);
    classes_load_get(NULL, clsName, runtime);
    utf8_clear(clsName);
    utf8_append_c(clsName, STR_CLASS_ORG_MINI_REFLECT_LAUNCHER);
    classes_load_get(NULL, clsName, runtime);
    //开始装载类
    utf8_destory(clsName);
    runtime_destory(runtime);
    runtime = NULL;
    return 0;
}

void jvm_destroy(MiniJVM *jvm) {
    while (threadlist_count_none_daemon(jvm) > 0 && !jvm->collector->exit_flag) {//wait for other thread over ,
        threadSleep(20);
    }
    set_jvm_state(jvm, JVM_STATUS_STOPED);
    //waiting for daemon thread terminate
    s32 i;
    while (jvm->thread_list->length) {
        thread_stop_all(jvm);
        for (i = 0; i < jvm->thread_list->length; i++) {
            Runtime *r = threadlist_get(jvm, i);
            if (r) {
                if (!r->son) {//未在执行jvm指令
                    thread_unboundle(r);//
                }
            }
        }
        threadSleep(20);
    }

    jdwp_stop_server(jvm);
    //
    gc_destory(jvm);
    //
    thread_lock_dispose(&jvm->threadlock);
    arraylist_destory(jvm->thread_list);
    native_lib_destory(jvm);
    sys_properties_dispose(jvm);
    close_log();
#if _JVM_DEBUG_LOG_LEVEL > 0
    jvm_printf("[INFO]jvm destoried\n");
#endif
    set_jvm_state(jvm, JVM_STATUS_UNKNOW);
    jvm_free(jvm);
}

s32 call_main(MiniJVM *jvm, c8 *p_mainclass, ArrayList *java_para) {
    if (!jvm) {
        jvm_printf("jvm not found .\n");
        return 1;
    }
    Runtime *runtime = runtime_create(jvm);
    thread_boundle(runtime);

    //准备参数
    s32 count = java_para ? java_para->length : 0;
    Utf8String *ustr = utf8_create_c(STR_CLASS_JAVA_LANG_STRING);
    Instance *arr = jarray_create_by_type_name(runtime, count, ustr);
    instance_hold_to_thread(arr, runtime);
    utf8_destory(ustr);
    int i;
    for (i = 0; i < count; i++) {
        Utf8String *utfs = utf8_create_c(arraylist_get_value(java_para, i));
        Instance *jstr = jstring_create(utfs, runtime);
        jarray_set_field(arr, i, (intptr_t) jstr);
        utf8_destory(utfs);
    }
    push_ref(runtime->stack, arr);
    instance_release_from_thread(arr, runtime);

    c8 *p_methodname = "main";
    c8 *p_methodtype = "([Ljava/lang/String;)V";
    s32 ret = call_method(jvm, p_mainclass, p_methodname, p_methodtype, runtime);

    thread_unboundle(runtime);
    runtime_destory(runtime);
    return ret;
}


s32 call_method(MiniJVM *jvm, c8 *p_classname, c8 *p_methodname, c8 *p_methoddesc, Runtime *p_runtime) {
    if (!p_classname) {
        jvm_printf("No main class .\n");
        return RUNTIME_STATUS_ERROR;
    }
    if (p_runtime && p_runtime->jvm != jvm) {
        return RUNTIME_STATUS_ERROR;
    }
    if (!jvm) {
        jvm_printf("jvm not found .\n");
        return RUNTIME_STATUS_ERROR;
    }
    //创建运行时栈
    Runtime *runtime = p_runtime;
    if (!p_runtime) {
        runtime = runtime_create(jvm);
        thread_boundle(runtime);
    }

    //开始装载类

    Utf8String *str_mainClsName = utf8_create_c(p_classname);
    utf8_replace_c(str_mainClsName, ".", "/");

    //装入主类

    JClass *clazz = classes_load_get(NULL, str_mainClsName, runtime);

    s32 ret = 0;
    if (clazz) {
        Utf8String *methodName = utf8_create_c(p_methodname);
        Utf8String *methodType = utf8_create_c(p_methoddesc);

        MethodInfo *m = find_methodInfo_by_name(str_mainClsName, methodName, methodType, runtime);
        if (m) {


            s64 start = currentTimeMillis();
#if _JVM_DEBUG_LOG_LEVEL > 0
            jvm_printf("\n[INFO]main thread start\n");
#endif
            //调用主方法
            if (jvm->jdwp_enable) {
                if (jvm->jdwp_suspend_on_start)jthread_suspend(runtime);
                jvm_printf("[JDWP]waiting for jdwp(port:%s) debug client connected...\n", JDWP_TCP_PORT);
            }//jdwp 会启动调试器
            runtime->method = NULL;
            runtime->clazz = clazz;
            ret = execute_method(m, runtime);
            if (ret == RUNTIME_STATUS_EXCEPTION) {
                print_exception(runtime);
            }
#if _JVM_DEBUG_LOG_LEVEL > 0
            jvm_printf("[INFO]main thread over %llx , return %d , spent : %lld\n", (s64) (intptr_t) runtime->thrd_info->jthread, ret, (currentTimeMillis() - start));
#endif

#if _JVM_DEBUG_PROFILE
            profile_print();
#endif


        }
        utf8_destory(methodName);
        utf8_destory(methodType);
    }
    if (!p_runtime) {
        thread_unboundle(runtime);
        runtime_destory(runtime);
    }


    utf8_destory(str_mainClsName);
    //

    return ret;
}


s32 execute_method(MethodInfo *method, Runtime *runtime) {
    if (!runtime || !method) {
        return RUNTIME_STATUS_ERROR;
    }
    jthread_block_exit(runtime);
    s32 ret = execute_method_impl(method, runtime);
    jthread_block_enter(runtime);
    return ret;
}
