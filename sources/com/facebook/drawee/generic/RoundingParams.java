package com.facebook.drawee.generic;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import java.util.Arrays;
/* loaded from: classes.dex */
public class RoundingParams {
    public int mRoundingMethod = 2;
    public float[] mCornersRadii = null;
    public int mOverlayColor = 0;
    public float mBorderWidth = 0.0f;
    public int mBorderColor = 0;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RoundingParams.class != obj.getClass()) {
            return false;
        }
        RoundingParams roundingParams = (RoundingParams) obj;
        if (this.mOverlayColor == roundingParams.mOverlayColor && Float.compare(roundingParams.mBorderWidth, this.mBorderWidth) == 0 && this.mBorderColor == roundingParams.mBorderColor && Float.compare(0.0f, 0.0f) == 0 && this.mRoundingMethod == roundingParams.mRoundingMethod) {
            return Arrays.equals(this.mCornersRadii, roundingParams.mCornersRadii);
        }
        return false;
    }

    public int hashCode() {
        int i = this.mRoundingMethod;
        int $enumboxing$ordinal = (((i != 0 ? SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i) : 0) * 31) + 0) * 31;
        float[] fArr = this.mCornersRadii;
        int hashCode = ((($enumboxing$ordinal + (fArr != null ? Arrays.hashCode(fArr) : 0)) * 31) + this.mOverlayColor) * 31;
        float f = this.mBorderWidth;
        return ((((((((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.mBorderColor) * 31) + 0) * 31) + 0) * 31) + 0;
    }
}
