package com.facebook.imageformat;
/* loaded from: classes.dex */
public final class DefaultImageFormats {
    public static final ImageFormat JPEG = new ImageFormat("JPEG", "jpeg");
    public static final ImageFormat PNG = new ImageFormat("PNG", "png");
    public static final ImageFormat GIF = new ImageFormat("GIF", "gif");
    public static final ImageFormat BMP = new ImageFormat("BMP", "bmp");
    public static final ImageFormat ICO = new ImageFormat("ICO", "ico");
    public static final ImageFormat WEBP_SIMPLE = new ImageFormat("WEBP_SIMPLE", "webp");
    public static final ImageFormat WEBP_LOSSLESS = new ImageFormat("WEBP_LOSSLESS", "webp");
    public static final ImageFormat WEBP_EXTENDED = new ImageFormat("WEBP_EXTENDED", "webp");
    public static final ImageFormat WEBP_EXTENDED_WITH_ALPHA = new ImageFormat("WEBP_EXTENDED_WITH_ALPHA", "webp");
    public static final ImageFormat WEBP_ANIMATED = new ImageFormat("WEBP_ANIMATED", "webp");
    public static final ImageFormat HEIF = new ImageFormat("HEIF", "heif");

    public static boolean isStaticWebpFormat(ImageFormat imageFormat) {
        return imageFormat == WEBP_SIMPLE || imageFormat == WEBP_LOSSLESS || imageFormat == WEBP_EXTENDED || imageFormat == WEBP_EXTENDED_WITH_ALPHA;
    }
}
