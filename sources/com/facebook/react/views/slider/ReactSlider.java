package com.facebook.react.views.slider;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
/* loaded from: classes.dex */
public class ReactSlider extends AppCompatSeekBar {
    public double mMinValue = 0.0d;
    public double mMaxValue = 0.0d;
    public double mValue = 0.0d;
    public double mStep = 0.0d;
    public double mStepCalculated = 0.0d;

    public ReactSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, null, i);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23 && i2 < 26) {
            setStateListAnimator(null);
        }
    }

    private double getStepValue() {
        double d = this.mStep;
        return d > 0.0d ? d : this.mStepCalculated;
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    public double toRealProgress(int i) {
        if (i == getMax()) {
            return this.mMaxValue;
        }
        return (i * getStepValue()) + this.mMinValue;
    }

    public final void updateAll() {
        if (this.mStep == 0.0d) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / 128;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    public final void updateValue() {
        double d = this.mValue;
        double d2 = this.mMinValue;
        setProgress((int) Math.round(((d - d2) / (this.mMaxValue - d2)) * getTotalSteps()));
    }
}
