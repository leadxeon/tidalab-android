package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ViewPropertyAnimatorCompatSet {
    public Interpolator mInterpolator;
    public boolean mIsStarted;
    public ViewPropertyAnimatorListener mListener;
    public long mDuration = -1;
    public final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.view.ViewPropertyAnimatorCompatSet.1
        public boolean mProxyStarted = false;
        public int mProxyEndCount = 0;

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            int i = this.mProxyEndCount + 1;
            this.mProxyEndCount = i;
            if (i == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.mListener;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationEnd(null);
                }
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                ViewPropertyAnimatorCompatSet.this.mIsStarted = false;
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
            if (!this.mProxyStarted) {
                this.mProxyStarted = true;
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.mListener;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationStart(null);
                }
            }
        }
    };
    public final ArrayList<ViewPropertyAnimatorCompat> mAnimators = new ArrayList<>();

    public void cancel() {
        if (this.mIsStarted) {
            Iterator<ViewPropertyAnimatorCompat> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.mIsStarted = false;
        }
    }

    public void start() {
        View view;
        if (!this.mIsStarted) {
            Iterator<ViewPropertyAnimatorCompat> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                ViewPropertyAnimatorCompat next = it.next();
                long j = this.mDuration;
                if (j >= 0) {
                    next.setDuration(j);
                }
                Interpolator interpolator = this.mInterpolator;
                if (!(interpolator == null || (view = next.mView.get()) == null)) {
                    view.animate().setInterpolator(interpolator);
                }
                if (this.mListener != null) {
                    next.setListener(this.mProxyListener);
                }
                View view2 = next.mView.get();
                if (view2 != null) {
                    view2.animate().start();
                }
            }
            this.mIsStarted = true;
        }
    }
}
