package com.facebook.yoga;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class YogaValue {
    public final int unit;
    public final float value;

    public YogaValue(float f, int i) {
        int i2 = 3;
        if (i == 0) {
            i2 = 1;
        } else if (i == 1) {
            i2 = 2;
        } else if (i != 2) {
            if (i == 3) {
                i2 = 4;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown enum value: ", i));
            }
        }
        this.value = f;
        this.unit = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            int i = this.unit;
            if (i == yogaValue.unit && (i == 1 || i == 4 || Float.compare(this.value, yogaValue.value) == 0)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.unit);
    }

    public String toString() {
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.unit);
        if ($enumboxing$ordinal == 0) {
            return "undefined";
        }
        if ($enumboxing$ordinal == 1) {
            return Float.toString(this.value);
        }
        if ($enumboxing$ordinal == 2) {
            return this.value + "%";
        } else if ($enumboxing$ordinal == 3) {
            return "auto";
        } else {
            throw new IllegalStateException();
        }
    }
}
