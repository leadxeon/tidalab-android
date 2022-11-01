package com.facebook.react.views.progressbar;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
/* loaded from: classes.dex */
public class ProgressBarContainerView extends FrameLayout {
    public Integer mColor;
    public double mProgress;
    public ProgressBar mProgressBar;
    public boolean mIndeterminate = true;
    public boolean mAnimating = true;

    public ProgressBarContainerView(Context context) {
        super(context);
    }
}
