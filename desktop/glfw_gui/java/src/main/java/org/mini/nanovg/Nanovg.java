package org.mini.nanovg;
/*this file generated by Nanovg_h_2_java.java ,dont modify it manual.*/
public class Nanovg {
    
    static public int nvglCreateImageFromHandleGLES3(long pctx, int ptextureId, int pw, int ph, int pflags) {
        return Nanovg.nvglCreateImageFromHandleGL3(pctx, ptextureId, pw, ph, pflags);
    }

    public static long nvgCreateGLES3(int pflags) {
        return Nanovg.nvgCreateGL3(pflags);
    }

    public static void nvgDeleteGLES3(long pctx) {
        Nanovg.nvgDeleteGL3(pctx);
    }
    public static final int NVG_CCW = 1;
    public static final int NVG_CW = 2;
    public static final int NVG_SOLID = 1;
    public static final int NVG_HOLE = 2;
    public static final int NVG_BUTT = 0;
    public static final int NVG_ROUND = 1;
    public static final int NVG_SQUARE = 2;
    public static final int NVG_BEVEL = 3;
    public static final int NVG_MITER = 4;
    public static final int NVG_ALIGN_LEFT = 1<<0;
    public static final int NVG_ALIGN_CENTER = 1<<1;
    public static final int NVG_ALIGN_RIGHT = 1<<2;
    public static final int NVG_ALIGN_TOP = 1<<3;
    public static final int NVG_ALIGN_MIDDLE = 1<<4;
    public static final int NVG_ALIGN_BOTTOM = 1<<5;
    public static final int NVG_ALIGN_BASELINE = 1<<6;
    public static final int NVG_ZERO = 1<<0;
    public static final int NVG_ONE = 1<<1;
    public static final int NVG_SRC_COLOR = 1<<2;
    public static final int NVG_ONE_MINUS_SRC_COLOR = 1<<3;
    public static final int NVG_DST_COLOR = 1<<4;
    public static final int NVG_ONE_MINUS_DST_COLOR = 1<<5;
    public static final int NVG_SRC_ALPHA = 1<<6;
    public static final int NVG_ONE_MINUS_SRC_ALPHA = 1<<7;
    public static final int NVG_DST_ALPHA = 1<<8;
    public static final int NVG_ONE_MINUS_DST_ALPHA = 1<<9;
    public static final int NVG_SRC_ALPHA_SATURATE = 1<<10;
    public static final int NVG_SOURCE_OVER = 0;
    public static final int NVG_SOURCE_IN = 1;
    public static final int NVG_SOURCE_OUT = 2;
    public static final int NVG_ATOP = 3;
    public static final int NVG_DESTINATION_OVER = 4;
    public static final int NVG_DESTINATION_IN = 5;
    public static final int NVG_DESTINATION_OUT = 6;
    public static final int NVG_DESTINATION_ATOP = 7;
    public static final int NVG_LIGHTER = 8;
    public static final int NVG_COPY = 9;
    public static final int NVG_XOR = 10;
    public static final int NVG_IMAGE_GENERATE_MIPMAPS = 1<<0;
    public static final int NVG_IMAGE_REPEATX = 1<<1;
    public static final int NVG_IMAGE_REPEATY = 1<<2;
    public static final int NVG_IMAGE_FLIPY = 1<<3;
    public static final int NVG_IMAGE_PREMULTIPLIED = 1<<4;
    public static final int NVG_IMAGE_NEAREST = 1<<5;
    public static final int NVG_ANTIALIAS = 1<<0;
    public static final int NVG_STENCIL_STROKES = 1<<1;
    public static final int NVG_DEBUG = 1<<2;
    public static final int GLNVG_LOC_VIEWSIZE = 0;
    public static final int GLNVG_LOC_SCISSORMAT = 1;
    public static final int GLNVG_LOC_SCISSOREXT = 2;
    public static final int GLNVG_LOC_SCISSORSCALE = 3;
    public static final int GLNVG_LOC_PAINTMAT = 4;
    public static final int GLNVG_LOC_EXTENT = 5;
    public static final int GLNVG_LOC_RADIUS = 6;
    public static final int GLNVG_LOC_FEATHER = 7;
    public static final int GLNVG_LOC_INNERCOL = 8;
    public static final int GLNVG_LOC_OUTERCOL = 9;
    public static final int GLNVG_LOC_STROKEMULT = 10;
    public static final int GLNVG_LOC_TEX = 11;
    public static final int GLNVG_LOC_TEXTYPE = 12;
    public static final int GLNVG_LOC_TYPE = 13;
    public static final int GLNVG_MAX_LOCS = 14;
    public static final int NSVG_SHADER_FILLGRAD = 0;
    public static final int NSVG_SHADER_FILLIMG = 1;
    public static final int NSVG_SHADER_SIMPLE = 2;
    public static final int NSVG_SHADER_IMG = 3;

    public static native int stbtt_InitFont(long pinfo, byte[] pdata2, int pfontstart); //stbtt_fontinfo*/*ptr*/,const unsigned char*,int, //int
    public static native float stbtt_ScaleForPixelHeight(long pinfo, float ppixels); //const stbtt_fontinfo*/*ptr*/,float, //float
    public static native void stbtt_GetFontVMetrics(long pinfo, int[] pascent, int[] pdescent, int[] plineGap); //const stbtt_fontinfo*/*ptr*/,int*,int*,int*, //void
    public static native void stbtt_GetCodepointBitmapBox(long pfont, int pcodepoint, float pscale_x, float pscale_y, int[] pix0, int[] piy0, int[] pix1, int[] piy1); //const stbtt_fontinfo*/*ptr*/,int,float,float,int*,int*,int*,int*, //void
    public static native void stbtt_MakeCodepointBitmapOffset(long pinfo, byte[] poutput, int poutput_offset, int pout_w, int pout_h, int pout_stride, float pscale_x, float pscale_y, int pcodepoint); //const stbtt_fontinfo*/*ptr*/,unsigned char*,int,int,int,int,float,float,int, //void
    public static native void stbtt_GetCodepointHMetrics(long pinfo, int pcodepoint, int[] padvanceWidth, int[] pleftSideBearing); //const stbtt_fontinfo*/*ptr*/,int,int*,int*, //void
    public static native int stbtt_GetCodepointKernAdvance(long pinfo, int pch1, int pch2); //const stbtt_fontinfo*/*ptr*/,int,int, //int
    public static native byte[]  stbtt_MakeFontInfo(); // //struct stbtt_fontinfo/*none_ptr*/ 
    public static native int stbi_write_png(byte[] pfilename, int pw, int ph, int pcomp, long pdata, int pstride_in_bytes); //char const*,int,int,int,const void*/*ptr*/,int, //int
    public static native int stbi_write_bmp(byte[] pfilename, int pw, int ph, int pcomp, long pdata); //char const*,int,int,int,const void*/*ptr*/, //int
    public static native int stbi_write_tga(byte[] pfilename, int pw, int ph, int pcomp, long pdata); //char const*,int,int,int,const void*/*ptr*/, //int
    public static native long  stbi_load(byte[] pfilename, int[] px, int[] py, int[] pcomp, int preq_comp); //char const*,int*,int*,int*,int, //stbi_uc*/*ptr*/ 
    public static native byte access_mem(long pptr); //stbi_uc*/*ptr*/, //stbi_uc
    public static native long  stbi_load_from_memory(long pbuffer, int plen, int[] px, int[] py, int[] pcomp, int preq_comp); //stbi_uc const*/*ptr*/,int,int*,int*,int*,int, //stbi_uc*/*ptr*/ 
    public static native void stbi_image_free(long pretval_from_stbi_load); //void*/*ptr*/, //void
    public static native void nvgBeginFrame(long pctx, int pwindowWidth, int pwindowHeight, float pdevicePixelRatio); //NVGcontext*/*ptr*/,int,int,float, //void
    public static native void nvgCancelFrame(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgEndFrame(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgGlobalCompositeOperation(long pctx, int pop); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgGlobalCompositeBlendFunc(long pctx, int psfactor, int pdfactor); //NVGcontext*/*ptr*/,int,int, //void
    public static native void nvgGlobalCompositeBlendFuncSeparate(long pctx, int psrcRGB, int pdstRGB, int psrcAlpha, int pdstAlpha); //NVGcontext*/*ptr*/,int,int,int,int, //void
    public static native float[]  nvgRGB(byte pr, byte pg, byte pb); //unsigned char,unsigned char,unsigned char, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgRGBf(float pr, float pg, float pb); //float,float,float, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgRGBA(byte pr, byte pg, byte pb, byte pa); //unsigned char,unsigned char,unsigned char,unsigned char, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgRGBAf(float pr, float pg, float pb, float pa); //float,float,float,float, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgLerpRGBA(float[] pc0, float[] pc1, float pu); //NVGcolor/*none_ptr*/,NVGcolor/*none_ptr*/,float, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgTransRGBA(float[] pc0, byte pa); //NVGcolor/*none_ptr*/,unsigned char, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgTransRGBAf(float[] pc0, float pa); //NVGcolor/*none_ptr*/,float, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgHSL(float ph, float ps, float pl); //float,float,float, //NVGcolor/*none_ptr*/ 
    public static native float[]  nvgHSLA(float ph, float ps, float pl, byte pa); //float,float,float,unsigned char, //NVGcolor/*none_ptr*/ 
    public static native void nvgSave(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgRestore(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgReset(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgShapeAntiAlias(long pctx, int penabled); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgStrokeColor(long pctx, float[] pcolor); //NVGcontext*/*ptr*/,NVGcolor/*none_ptr*/, //void
    public static native void nvgStrokePaint(long pctx, byte[] ppaint); //NVGcontext*/*ptr*/,NVGpaint/*none_ptr*/, //void
    public static native void nvgFillColor(long pctx, float[] pcolor); //NVGcontext*/*ptr*/,NVGcolor/*none_ptr*/, //void
    public static native void nvgFillPaint(long pctx, byte[] ppaint); //NVGcontext*/*ptr*/,NVGpaint/*none_ptr*/, //void
    public static native void nvgMiterLimit(long pctx, float plimit); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgStrokeWidth(long pctx, float psize); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgLineCap(long pctx, int pcap); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgLineJoin(long pctx, int pjoin); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgGlobalAlpha(long pctx, float palpha); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgResetTransform(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgTransform(long pctx, float pa, float pb, float pc, float pd, float pe, float pf); //NVGcontext*/*ptr*/,float,float,float,float,float,float, //void
    public static native void nvgTranslate(long pctx, float px, float py); //NVGcontext*/*ptr*/,float,float, //void
    public static native void nvgRotate(long pctx, float pangle); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgSkewX(long pctx, float pangle); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgSkewY(long pctx, float pangle); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgScale(long pctx, float px, float py); //NVGcontext*/*ptr*/,float,float, //void
    public static native void nvgCurrentTransform(long pctx, float[] pxform); //NVGcontext*/*ptr*/,float*, //void
    public static native void nvgTransformIdentity(float[] pdst); //float*, //void
    public static native void nvgTransformTranslate(float[] pdst, float ptx, float pty); //float*,float,float, //void
    public static native void nvgTransformScale(float[] pdst, float psx, float psy); //float*,float,float, //void
    public static native void nvgTransformRotate(float[] pdst, float pa); //float*,float, //void
    public static native void nvgTransformSkewX(float[] pdst, float pa); //float*,float, //void
    public static native void nvgTransformSkewY(float[] pdst, float pa); //float*,float, //void
    public static native void nvgTransformMultiply(float[] pdst, float[] psrc); //float*,const float*, //void
    public static native void nvgTransformPremultiply(float[] pdst, float[] psrc); //float*,const float*, //void
    public static native int nvgTransformInverse(float[] pdst, float[] psrc); //float*,const float*, //int
    public static native void nvgTransformPoint(float[] pdstx, float[] pdsty, float[] pxform, float psrcx, float psrcy); //float*,float*,const float*,float,float, //void
    public static native float nvgDegToRad(float pdeg); //float, //float
    public static native float nvgRadToDeg(float prad); //float, //float
    public static native int nvgCreateImage(long pctx, byte[] pfilename, int pimageFlags); //NVGcontext*/*ptr*/,const char*,int, //int
    public static native int nvgCreateImageMem(long pctx, int pimageFlags, byte[] pdata, int pndata); //NVGcontext*/*ptr*/,int,unsigned char*,int, //int
    public static native int nvgCreateImageRGBA(long pctx, int pw, int ph, int pimageFlags, byte[] pdata); //NVGcontext*/*ptr*/,int,int,int,const unsigned char*, //int
    public static native void nvgUpdateImage(long pctx, int pimage, byte[] pdata); //NVGcontext*/*ptr*/,int,const unsigned char*, //void
    public static native void nvgImageSize(long pctx, int pimage, int[] pw, int[] ph); //NVGcontext*/*ptr*/,int,int*,int*, //void
    public static native void nvgDeleteImage(long pctx, int pimage); //NVGcontext*/*ptr*/,int, //void
    public static native byte[]  nvgLinearGradient(long pctx, float psx, float psy, float pex, float pey, float[] picol, float[] pocol); //NVGcontext*/*ptr*/,float,float,float,float,NVGcolor/*none_ptr*/,NVGcolor/*none_ptr*/, //NVGpaint/*none_ptr*/ 
    public static native byte[]  nvgBoxGradient(long pctx, float px, float py, float pw, float ph, float pr, float pf, float[] picol, float[] pocol); //NVGcontext*/*ptr*/,float,float,float,float,float,float,NVGcolor/*none_ptr*/,NVGcolor/*none_ptr*/, //NVGpaint/*none_ptr*/ 
    public static native byte[]  nvgRadialGradient(long pctx, float pcx, float pcy, float pinr, float poutr, float[] picol, float[] pocol); //NVGcontext*/*ptr*/,float,float,float,float,NVGcolor/*none_ptr*/,NVGcolor/*none_ptr*/, //NVGpaint/*none_ptr*/ 
    public static native byte[]  nvgImagePattern(long pctx, float pox, float poy, float pex, float pey, float pangle, int pimage, float palpha); //NVGcontext*/*ptr*/,float,float,float,float,float,int,float, //NVGpaint/*none_ptr*/ 
    public static native void nvgScissor(long pctx, float px, float py, float pw, float ph); //NVGcontext*/*ptr*/,float,float,float,float, //void
    public static native void nvgIntersectScissor(long pctx, float px, float py, float pw, float ph); //NVGcontext*/*ptr*/,float,float,float,float, //void
    public static native void nvgResetScissor(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgBeginPath(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgMoveTo(long pctx, float px, float py); //NVGcontext*/*ptr*/,float,float, //void
    public static native void nvgLineTo(long pctx, float px, float py); //NVGcontext*/*ptr*/,float,float, //void
    public static native void nvgBezierTo(long pctx, float pc1x, float pc1y, float pc2x, float pc2y, float px, float py); //NVGcontext*/*ptr*/,float,float,float,float,float,float, //void
    public static native void nvgQuadTo(long pctx, float pcx, float pcy, float px, float py); //NVGcontext*/*ptr*/,float,float,float,float, //void
    public static native void nvgArcTo(long pctx, float px1, float py1, float px2, float py2, float pradius); //NVGcontext*/*ptr*/,float,float,float,float,float, //void
    public static native void nvgClosePath(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgPathWinding(long pctx, int pdir); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgArc(long pctx, float pcx, float pcy, float pr, float pa0, float pa1, int pdir); //NVGcontext*/*ptr*/,float,float,float,float,float,int, //void
    public static native void nvgRect(long pctx, float px, float py, float pw, float ph); //NVGcontext*/*ptr*/,float,float,float,float, //void
    public static native void nvgRoundedRect(long pctx, float px, float py, float pw, float ph, float pr); //NVGcontext*/*ptr*/,float,float,float,float,float, //void
    public static native void nvgRoundedRectVarying(long pctx, float px, float py, float pw, float ph, float pradTopLeft, float pradTopRight, float pradBottomRight, float pradBottomLeft); //NVGcontext*/*ptr*/,float,float,float,float,float,float,float,float, //void
    public static native void nvgEllipse(long pctx, float pcx, float pcy, float prx, float pry); //NVGcontext*/*ptr*/,float,float,float,float, //void
    public static native void nvgCircle(long pctx, float pcx, float pcy, float pr); //NVGcontext*/*ptr*/,float,float,float, //void
    public static native void nvgFill(long pctx); //NVGcontext*/*ptr*/, //void
    public static native void nvgStroke(long pctx); //NVGcontext*/*ptr*/, //void
    public static native int nvgCreateFont(long pctx, byte[] pname, byte[] pfilename); //NVGcontext*/*ptr*/,const char*,const char*, //int
    public static native int nvgCreateFontMem(long pctx, byte[] pname, byte[] pdata, int pndata, int pfreeData); //NVGcontext*/*ptr*/,const char*,unsigned char*,int,int, //int
    public static native int nvgFindFont(long pctx, byte[] pname); //NVGcontext*/*ptr*/,const char*, //int
    public static native int nvgAddFallbackFontId(long pctx, int pbaseFont, int pfallbackFont); //NVGcontext*/*ptr*/,int,int, //int
    public static native int nvgAddFallbackFont(long pctx, byte[] pbaseFont, byte[] pfallbackFont); //NVGcontext*/*ptr*/,const char*,const char*, //int
    public static native void nvgFontSize(long pctx, float psize); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgFontBlur(long pctx, float pblur); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgTextLetterSpacing(long pctx, float pspacing); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgTextLineHeight(long pctx, float plineHeight); //NVGcontext*/*ptr*/,float, //void
    public static native void nvgTextAlign(long pctx, int palign); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgFontFaceId(long pctx, int pfont); //NVGcontext*/*ptr*/,int, //void
    public static native void nvgFontFace(long pctx, byte[] pfont); //NVGcontext*/*ptr*/,const char*, //void
    public static native void nvgTextMetrics(long pctx, float[] pascender, float[] pdescender, float[] plineh); //NVGcontext*/*ptr*/,float*,float*,float*, //void
    public static native long  nvgCreateGL3(int pflags); //int, //NVGcontext*/*ptr*/ 
    public static native void nvgDeleteGL3(long pctx); //NVGcontext*/*ptr*/, //void
    public static native int nvglCreateImageFromHandleGL3(long pctx, int ptextureId, int pw, int ph, int pflags); //NVGcontext*/*ptr*/,GLuint,int,int,int, //int
    public static native int nvglImageHandleGL3(long pctx, int pimage); //NVGcontext*/*ptr*/,int, //GLuint
    public static native long  nvgCreateNVGtextRow(int pcount); //int, //struct NVGtextRow*/*ptr*/ 
    public static native void nvgDeleteNVGtextRow(long pval); //struct NVGtextRow*/*ptr*/, //void
    public static native float nvgNVGtextRow_width(long pptr, int pindex); //struct NVGtextRow*/*ptr*/,int, //float
    public static native long  nvgNVGtextRow_start(long pptr, int pindex); //struct NVGtextRow*/*ptr*/,int, //void*/*ptr*/ 
    public static native long  nvgNVGtextRow_end(long pptr, int pindex); //struct NVGtextRow*/*ptr*/,int, //void*/*ptr*/ 
    public static native long  nvgNVGtextRow_next(long pptr, int pindex); //struct NVGtextRow*/*ptr*/,int, //void*/*ptr*/ 
    public static native long  nvgCreateNVGglyphPosition(int pcount); //int, //struct NVGglyphPosition*/*ptr*/ 
    public static native void nvgDeleteNVGglyphPosition(long pval); //struct NVGglyphPosition*/*ptr*/, //void
    public static native float nvgNVGglyphPosition_x(long pptr, int pcount); //struct NVGglyphPosition*/*ptr*/,int, //float
    public static native float nvgTextJni(long pctx, float px, float py, byte[] pstring, int pstart, int pend); //NVGcontext*/*ptr*/,float,float,const char*,int,int, //float
    public static native void nvgTextBoxJni(long pctx, float px, float py, float pbreakRowWidth, byte[] pstring, int pstart, int pend); //NVGcontext*/*ptr*/,float,float,float,const char*,int,int, //void
    public static native float nvgTextBoundsJni(long pctx, float px, float py, byte[] pstring, int pstart, int pend, float[] pbounds); //NVGcontext*/*ptr*/,float,float,const char*,int,int,float*, //float
    public static native void nvgTextBoxBoundsJni(long pctx, float px, float py, float pbreakRowWidth, byte[] pstring, int pstart, int pend, float[] pbounds); //NVGcontext*/*ptr*/,float,float,float,const char*,int,int,float*, //void
    public static native int nvgTextBreakLinesJni(long pctx, byte[] pstring, int pstart, int pend, float pbreakRowWidth, long prows, int pmaxRows); //NVGcontext*/*ptr*/,const char*,int,int,float,NVGtextRow*/*ptr*/,int, //int
    public static native int nvgTextGlyphPositionsJni(long pctx, float px, float py, byte[] pstring, int pstart, int pend, long ppositions, int pmaxPositions); //NVGcontext*/*ptr*/,float,float,const char*,int,int,NVGglyphPosition*/*ptr*/,int, //int

}

