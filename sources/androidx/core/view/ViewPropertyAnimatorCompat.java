package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.appcompat.app.WindowDecorActionBar;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class ViewPropertyAnimatorCompat {
    public int mOldLayerType = -1;
    public WeakReference<View> mView;

    public ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference<>(view);
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void cancel() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public ViewPropertyAnimatorCompat setDuration(long j) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = this.mView.get();
        if (view != null) {
            setListenerInternal(view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public final void setListenerInternal(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new AnimatorListenerAdapter(this) { // from class: androidx.core.view.ViewPropertyAnimatorCompat.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.mView.get();
        if (view != null) {
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
            if (viewPropertyAnimatorUpdateListener != null) {
                animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(this) { // from class: androidx.core.view.ViewPropertyAnimatorCompat.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ((View) WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
                    }
                };
            }
            view.animate().setUpdateListener(animatorUpdateListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
