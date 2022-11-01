package com.horcrux.svg;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes.dex */
public class Brush {
    public ReadableArray mColors;
    public Matrix mMatrix;
    public PatternView mPattern;
    public final SVGLength[] mPoints;
    public final int mType;
    public boolean mUseContentObjectBoundingBoxUnits;
    public final boolean mUseObjectBoundingBox;
    public Rect mUserSpaceBoundingBox;

    public Brush(int i, SVGLength[] sVGLengthArr, int i2) {
        this.mType = i;
        this.mPoints = sVGLengthArr;
        this.mUseObjectBoundingBox = i2 != 1 ? false : true;
    }

    public final double getVal(SVGLength sVGLength, double d, float f, float f2) {
        double d2;
        if (this.mUseObjectBoundingBox && sVGLength.unit == 2) {
            d2 = d;
            return PathParser.fromRelative(sVGLength, d, 0.0d, d2, f2);
        }
        d2 = f;
        return PathParser.fromRelative(sVGLength, d, 0.0d, d2, f2);
    }
}
