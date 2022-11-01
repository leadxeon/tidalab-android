package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final /* synthetic */ int $r8$clinit = 0;

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.linearProgressIndicatorStyle, 2131886768);
        Context context2 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.spec;
        setIndeterminateDrawable(new IndeterminateDrawable(context2, linearProgressIndicatorSpec, new LinearDrawingDelegate(linearProgressIndicatorSpec), linearProgressIndicatorSpec.indeterminateAnimationType == 0 ? new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec) : new LinearIndeterminateDisjointAnimatorDelegate(context2, linearProgressIndicatorSpec)));
        Context context3 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec2 = (LinearProgressIndicatorSpec) this.spec;
        setProgressDrawable(new DeterminateDrawable(context3, linearProgressIndicatorSpec2, new LinearDrawingDelegate(linearProgressIndicatorSpec2)));
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.spec).indeterminateAnimationType;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.spec).indicatorDirection;
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        S s = this.spec;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) s;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) s).indicatorDirection != 1) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (!((getLayoutDirection() == 1 && ((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 2) || (getLayoutDirection() == 0 && ((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 3))) {
                z2 = false;
            }
        }
        linearProgressIndicatorSpec.drawHorizontallyInverse = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i2 - (getPaddingBottom() + getPaddingTop());
        IndeterminateDrawable<LinearProgressIndicatorSpec> indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        DeterminateDrawable<LinearProgressIndicatorSpec> progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public void setIndeterminateAnimationType(int i) {
        if (((LinearProgressIndicatorSpec) this.spec).indeterminateAnimationType != i) {
            if (!visibleToUser() || !isIndeterminate()) {
                LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.spec;
                linearProgressIndicatorSpec.indeterminateAnimationType = i;
                linearProgressIndicatorSpec.validateSpec();
                if (i == 0) {
                    IndeterminateDrawable<LinearProgressIndicatorSpec> indeterminateDrawable = getIndeterminateDrawable();
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = new LinearIndeterminateContiguousAnimatorDelegate((LinearProgressIndicatorSpec) this.spec);
                    indeterminateDrawable.animatorDelegate = linearIndeterminateContiguousAnimatorDelegate;
                    linearIndeterminateContiguousAnimatorDelegate.drawable = indeterminateDrawable;
                } else {
                    IndeterminateDrawable<LinearProgressIndicatorSpec> indeterminateDrawable2 = getIndeterminateDrawable();
                    LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = new LinearIndeterminateDisjointAnimatorDelegate(getContext(), (LinearProgressIndicatorSpec) this.spec);
                    indeterminateDrawable2.animatorDelegate = linearIndeterminateDisjointAnimatorDelegate;
                    linearIndeterminateDisjointAnimatorDelegate.drawable = indeterminateDrawable2;
                }
                invalidate();
                return;
            }
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((LinearProgressIndicatorSpec) this.spec).validateSpec();
    }

    public void setIndicatorDirection(int i) {
        S s = this.spec;
        ((LinearProgressIndicatorSpec) s).indicatorDirection = i;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) s;
        boolean z = true;
        if (i != 1) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (!((getLayoutDirection() == 1 && ((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 2) || (getLayoutDirection() == 0 && i == 3))) {
                z = false;
            }
        }
        linearProgressIndicatorSpec.drawHorizontallyInverse = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setProgressCompat(int i, boolean z) {
        S s = this.spec;
        if (s == 0 || ((LinearProgressIndicatorSpec) s).indeterminateAnimationType != 0 || !isIndeterminate()) {
            super.setProgressCompat(i, z);
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i) {
        super.setTrackCornerRadius(i);
        ((LinearProgressIndicatorSpec) this.spec).validateSpec();
        invalidate();
    }
}
