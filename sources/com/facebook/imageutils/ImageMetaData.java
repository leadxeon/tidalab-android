package com.facebook.imageutils;

import android.graphics.ColorSpace;
import android.util.Pair;
/* loaded from: classes.dex */
public class ImageMetaData {
    public final ColorSpace mColorSpace;
    public final Pair<Integer, Integer> mDimensions;

    public ImageMetaData(int i, int i2, ColorSpace colorSpace) {
        this.mDimensions = (i == -1 || i2 == -1) ? null : new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
        this.mColorSpace = colorSpace;
    }
}
