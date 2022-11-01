package com.tidalab.v2board.clash.design.util;

import android.animation.ValueAnimator;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import kotlin.Unit;
/* compiled from: Elevation.kt */
/* loaded from: classes.dex */
public final class AppBarElevationController {
    public final ActivityBarLayout activityBar;
    public ValueAnimator animator;
    public boolean elevated;

    public AppBarElevationController(ActivityBarLayout activityBarLayout) {
        this.activityBar = activityBarLayout;
    }

    public final void setElevated(boolean z) {
        ValueAnimator valueAnimator;
        if (this.elevated != z) {
            this.elevated = z;
            ValueAnimator valueAnimator2 = this.animator;
            if (valueAnimator2 != null) {
                valueAnimator2.end();
            }
            if (z) {
                valueAnimator = ValueAnimator.ofFloat(this.activityBar.getElevation(), InputKt.getPixels(this.activityBar.getContext(), R.dimen.toolbar_elevation));
            } else {
                valueAnimator = ValueAnimator.ofFloat(this.activityBar.getElevation(), 0.0f);
            }
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$AppBarElevationController$7pUwKv3QqP9WPB6FtFjEbSQLpUw
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    ActivityBarLayout activityBarLayout = AppBarElevationController.this.activityBar;
                    Object animatedValue = valueAnimator3.getAnimatedValue();
                    Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
                    activityBarLayout.setElevation(((Float) animatedValue).floatValue());
                }
            });
            valueAnimator.start();
            Unit unit = Unit.INSTANCE;
            this.animator = valueAnimator;
        }
    }
}
