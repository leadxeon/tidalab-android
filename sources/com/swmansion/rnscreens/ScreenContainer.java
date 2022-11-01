package com.swmansion.rnscreens;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.BackStackRecord;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import kotlin.collections.ArraysKt___ArraysKt;
/* compiled from: ScreenContainer.kt */
/* loaded from: classes.dex */
public class ScreenContainer<T extends ScreenFragment> extends ViewGroup {
    public FragmentTransaction mCurrentTransaction;
    public FragmentManager mFragmentManager;
    public boolean mIsAttached;
    public boolean mLayoutEnqueued;
    public boolean mNeedUpdate;
    public ScreenFragment mParentScreenFragment;
    public FragmentTransaction mProcessingTransaction;
    public final ArrayList<T> mScreenFragments = new ArrayList<>();
    public final ChoreographerCompat.FrameCallback mFrameCallback = new ChoreographerCompat.FrameCallback(this) { // from class: com.swmansion.rnscreens.ScreenContainer$mFrameCallback$1
        public final /* synthetic */ ScreenContainer<T> this$0;

        {
            this.this$0 = this;
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            this.this$0.updateIfNeeded();
        }
    };
    public final ChoreographerCompat.FrameCallback mLayoutCallback = new ChoreographerCompat.FrameCallback(this) { // from class: com.swmansion.rnscreens.ScreenContainer$mLayoutCallback$1
        public final /* synthetic */ ScreenContainer<T> this$0;

        {
            this.this$0 = this;
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            ScreenContainer<T> screenContainer = this.this$0;
            screenContainer.mLayoutEnqueued = false;
            screenContainer.measure(View.MeasureSpec.makeMeasureSpec(screenContainer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), 1073741824));
            ViewGroup viewGroup = this.this$0;
            viewGroup.layout(viewGroup.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
        }
    };

    public ScreenContainer(Context context) {
        super(context);
    }

    private final void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        updateIfNeeded();
    }

    public T adapt(Screen screen) {
        return (T) new ScreenFragment(screen);
    }

    public final Screen.ActivityState getActivityState(ScreenFragment screenFragment) {
        return screenFragment.getScreen().getActivityState();
    }

    public final FragmentTransaction getOrCreateTransaction() {
        if (this.mCurrentTransaction == null) {
            FragmentManager fragmentManager = this.mFragmentManager;
            if (fragmentManager != null) {
                BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
                backStackRecord.mReorderingAllowed = true;
                this.mCurrentTransaction = backStackRecord;
            } else {
                throw new IllegalArgumentException("mFragmentManager is null when creating transaction".toString());
            }
        }
        FragmentTransaction fragmentTransaction = this.mCurrentTransaction;
        if (fragmentTransaction != null) {
            return fragmentTransaction;
        }
        throw new IllegalStateException("mCurrentTransaction changed to null during creating transaction");
    }

    public final Screen getScreenAt(int i) {
        return this.mScreenFragments.get(i).getScreen();
    }

    public final int getScreenCount() {
        return this.mScreenFragments.size();
    }

    public Screen getTopScreen() {
        Iterator<T> it = this.mScreenFragments.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (getActivityState(next) == Screen.ActivityState.ON_TOP) {
                return next.getScreen();
            }
        }
        return null;
    }

    public boolean hasScreen(ScreenFragment screenFragment) {
        return ArraysKt___ArraysKt.contains(this.mScreenFragments, screenFragment);
    }

    public final void markUpdated() {
        if (!this.mNeedUpdate) {
            this.mNeedUpdate = true;
            ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(3, this.mFrameCallback);
        }
    }

    public void notifyContainerUpdate() {
        ScreenFragment fragment;
        Screen topScreen = getTopScreen();
        if (topScreen != null && (fragment = topScreen.getFragment()) != null) {
            fragment.onContainerUpdate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        boolean z;
        boolean z2;
        super.onAttachedToWindow();
        boolean z3 = true;
        this.mIsAttached = true;
        this.mNeedUpdate = true;
        ViewParent viewParent = this;
        while (true) {
            z = viewParent instanceof ReactRootView;
            if (z || (viewParent instanceof Screen) || viewParent.getParent() == null) {
                break;
            }
            viewParent = viewParent.getParent();
        }
        if (viewParent instanceof Screen) {
            ScreenFragment fragment = ((Screen) viewParent).getFragment();
            if (fragment == null) {
                z3 = false;
            }
            if (z3) {
                this.mParentScreenFragment = fragment;
                fragment.mChildScreenContainers.add(this);
                setFragmentManager(fragment.getChildFragmentManager());
                return;
            }
            throw new IllegalStateException("Parent Screen does not have its Fragment attached".toString());
        } else if (z) {
            Context context = ((ReactRootView) viewParent).getContext();
            while (true) {
                z2 = context instanceof FragmentActivity;
                if (z2 || !(context instanceof ContextWrapper)) {
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (z2) {
                setFragmentManager(((FragmentActivity) context).getSupportFragmentManager());
                return;
            }
            throw new IllegalStateException("In order to use RNScreens components your app's activity need to extend ReactFragmentActivity or ReactCompatActivity".toString());
        } else {
            throw new IllegalStateException("ScreenContainer is not attached under ReactRootView".toString());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null && !fragmentManager.mDestroyed) {
            BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
            boolean z = false;
            for (Fragment fragment : fragmentManager.getFragments()) {
                if ((fragment instanceof ScreenFragment) && ((ScreenFragment) fragment).getScreen().getContainer() == this) {
                    backStackRecord.remove(fragment);
                    z = true;
                }
            }
            if (z) {
                backStackRecord.disallowAddToBackStack();
                backStackRecord.mManager.execSingleAction(backStackRecord, true);
            }
            fragmentManager.execPendingActions(true);
            fragmentManager.forcePostponedTransactions();
        }
        ScreenFragment screenFragment = this.mParentScreenFragment;
        if (screenFragment != null) {
            screenFragment.mChildScreenContainers.remove(this);
        }
        this.mParentScreenFragment = null;
        super.onDetachedFromWindow();
        this.mIsAttached = false;
        int childCount = getChildCount() - 1;
        if (childCount >= 0) {
            while (true) {
                int i = childCount - 1;
                removeViewAt(childCount);
                if (i >= 0) {
                    childCount = i;
                } else {
                    return;
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, getWidth(), getHeight());
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    public void performUpdate() {
        Screen.ActivityState activityState = Screen.ActivityState.INACTIVE;
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            HashSet hashSet = new HashSet(fragmentManager.getFragments());
            Iterator<T> it = this.mScreenFragments.iterator();
            while (it.hasNext()) {
                T next = it.next();
                if (getActivityState(next) == activityState && next.isAdded()) {
                    getOrCreateTransaction().remove(next);
                }
                hashSet.remove(next);
            }
            boolean z = false;
            if (!hashSet.isEmpty()) {
                Object[] array = hashSet.toArray(new Fragment[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
                Fragment[] fragmentArr = (Fragment[]) array;
                int length = fragmentArr.length;
                int i = 0;
                while (i < length) {
                    Fragment fragment = fragmentArr[i];
                    i++;
                    if (fragment instanceof ScreenFragment) {
                        ScreenFragment screenFragment = (ScreenFragment) fragment;
                        if (screenFragment.getScreen().getContainer() == null) {
                            getOrCreateTransaction().remove(screenFragment);
                        }
                    }
                }
            }
            boolean z2 = getTopScreen() == null;
            Iterator<T> it2 = this.mScreenFragments.iterator();
            while (it2.hasNext()) {
                T next2 = it2.next();
                Screen.ActivityState activityState2 = getActivityState(next2);
                if (activityState2 != activityState && !next2.isAdded()) {
                    getOrCreateTransaction().add(getId(), next2);
                    z = true;
                } else if (activityState2 != activityState && z) {
                    FragmentTransaction orCreateTransaction = getOrCreateTransaction();
                    orCreateTransaction.remove(next2);
                    orCreateTransaction.add(getId(), next2);
                }
                next2.getScreen().setTransitioning(z2);
            }
            tryCommitTransaction();
            return;
        }
        throw new IllegalArgumentException("mFragmentManager is null when performing update in ScreenContainer".toString());
    }

    public void removeAllScreens() {
        Iterator<T> it = this.mScreenFragments.iterator();
        while (it.hasNext()) {
            it.next().getScreen().setContainer(null);
        }
        this.mScreenFragments.clear();
        markUpdated();
    }

    public void removeScreenAt(int i) {
        this.mScreenFragments.get(i).getScreen().setContainer(null);
        this.mScreenFragments.remove(i);
        markUpdated();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == getFocusedChild()) {
            Object systemService = getContext().getSystemService("input_method");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(getWindowToken(), 2);
        }
        super.removeView(view);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (!this.mLayoutEnqueued && this.mLayoutCallback != null) {
            this.mLayoutEnqueued = true;
            ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(3, this.mLayoutCallback);
        }
    }

    public final void tryCommitTransaction() {
        final FragmentTransaction fragmentTransaction = this.mCurrentTransaction;
        if (fragmentTransaction != null) {
            this.mProcessingTransaction = fragmentTransaction;
            Runnable runnable = new Runnable() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenContainer$SH9tDZyQZasu4bp_iXYFubk_3Fg
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenContainer screenContainer = ScreenContainer.this;
                    if (screenContainer.mProcessingTransaction == fragmentTransaction) {
                        screenContainer.mProcessingTransaction = null;
                    }
                }
            };
            fragmentTransaction.disallowAddToBackStack();
            if (fragmentTransaction.mCommitRunnables == null) {
                fragmentTransaction.mCommitRunnables = new ArrayList<>();
            }
            fragmentTransaction.mCommitRunnables.add(runnable);
            fragmentTransaction.commitAllowingStateLoss();
            this.mCurrentTransaction = null;
        }
    }

    public final void updateIfNeeded() {
        FragmentManager fragmentManager;
        if (this.mNeedUpdate && this.mIsAttached && (fragmentManager = this.mFragmentManager) != null) {
            this.mNeedUpdate = false;
            if (fragmentManager != null) {
                fragmentManager.execPendingActions(true);
                fragmentManager.forcePostponedTransactions();
            }
            performUpdate();
            notifyContainerUpdate();
        }
    }
}
