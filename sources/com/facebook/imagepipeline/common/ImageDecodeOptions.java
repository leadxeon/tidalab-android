package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
/* loaded from: classes.dex */
public class ImageDecodeOptions {
    public static final ImageDecodeOptions DEFAULTS = new ImageDecodeOptions(new ImageDecodeOptionsBuilder());
    public final Bitmap.Config bitmapConfig;
    public final int minDecodeIntervalMs = 100;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.bitmapConfig = imageDecodeOptionsBuilder.mBitmapConfig;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ImageDecodeOptions.class == obj.getClass() && this.bitmapConfig == ((ImageDecodeOptions) obj).bitmapConfig;
    }

    public int hashCode() {
        return ((((((this.bitmapConfig.ordinal() + (((((((((this.minDecodeIntervalMs * 31) + 0) * 31) + 0) * 31) + 0) * 31) + 0) * 31)) * 31) + 0) * 31) + 0) * 31) + 0;
    }

    public String toString() {
        Boolean bool = Boolean.FALSE;
        return String.format(null, "%d-%b-%b-%b-%b-%b-%s-%s-%s", Integer.valueOf(this.minDecodeIntervalMs), bool, bool, bool, bool, this.bitmapConfig.name(), null, null, null);
    }
}
