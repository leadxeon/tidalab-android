package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import androidx.fragment.app.BackStackRecord;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentLifecycleCallbacksDispatcher;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.events.StackFinishTransitioningEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
/* compiled from: ScreenStack.kt */
/* loaded from: classes.dex */
public final class ScreenStack extends ScreenContainer<ScreenStackFragment> {
    public static final Companion Companion = new Companion(null);
    public boolean isDetachingCurrentScreen;
    public boolean mRemovalTransitionStarted;
    public ScreenStackFragment mTopScreen;
    public int previousChildrenCount;
    public boolean reverseLastTwoChildren;
    public final ArrayList<ScreenStackFragment> mStack = new ArrayList<>();
    public final Set<ScreenStackFragment> mDismissed = new HashSet();
    public final List<DrawingOp> drawingOpPool = new ArrayList();
    public final List<DrawingOp> drawingOps = new ArrayList();
    public final FragmentManager.OnBackStackChangedListener mBackStackListener = new FragmentManager.OnBackStackChangedListener() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenStack$7w1YUJsLUIjhAOb8TETZ0Ylvaco
        @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
        public final void onBackStackChanged() {
            Integer num;
            ScreenStackFragment screenStackFragment;
            ScreenStack screenStack = ScreenStack.this;
            FragmentManager fragmentManager = screenStack.mFragmentManager;
            if (fragmentManager == null) {
                num = null;
            } else {
                ArrayList<BackStackRecord> arrayList = fragmentManager.mBackStack;
                num = Integer.valueOf(arrayList != null ? arrayList.size() : 0);
            }
            if (num != null && num.intValue() == 0 && (screenStackFragment = screenStack.mTopScreen) != null) {
                screenStack.mDismissed.add(screenStackFragment);
                screenStack.markUpdated();
            }
        }
    };
    public final FragmentManager.FragmentLifecycleCallbacks mLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.swmansion.rnscreens.ScreenStack$mLifecycleCallbacks$1
        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            ScreenStack screenStack = ScreenStack.this;
            if (screenStack.mTopScreen == fragment) {
                screenStack.setupBackHandlerIfNeeded((ScreenStackFragment) fragment);
            }
        }
    };

    /* compiled from: ScreenStack.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* compiled from: ScreenStack.kt */
    /* loaded from: classes.dex */
    public final class DrawingOp {
        public Canvas canvas;
        public View child;
        public long drawingTime;

        public DrawingOp() {
        }
    }

    public ScreenStack(Context context) {
        super(context);
    }

    public static final void access$performDraw(ScreenStack screenStack, DrawingOp drawingOp) {
        Objects.requireNonNull(screenStack);
        super.drawChild(drawingOp.canvas, drawingOp.child, drawingOp.drawingTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupBackHandlerIfNeeded(ScreenStackFragment screenStackFragment) {
        FragmentManager fragmentManager;
        ScreenStackFragment screenStackFragment2 = this.mTopScreen;
        ScreenStackFragment screenStackFragment3 = null;
        if (Intrinsics.areEqual(screenStackFragment2 == null ? null : Boolean.valueOf(screenStackFragment2.isResumed()), Boolean.TRUE) && (fragmentManager = this.mFragmentManager) != null) {
            FragmentManager.OnBackStackChangedListener onBackStackChangedListener = this.mBackStackListener;
            ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = fragmentManager.mBackStackChangeListeners;
            if (arrayList != null) {
                arrayList.remove(onBackStackChangedListener);
            }
            int i = 0;
            fragmentManager.enqueueAction(new FragmentManager.PopBackStackState("RN_SCREEN_LAST", -1, 1), false);
            int size = this.mStack.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                ScreenStackFragment screenStackFragment4 = this.mStack.get(i);
                if (!this.mDismissed.contains(screenStackFragment4)) {
                    screenStackFragment3 = screenStackFragment4;
                    break;
                }
                i++;
            }
            if (screenStackFragment != screenStackFragment3 && screenStackFragment.getScreen().isGestureEnabled) {
                BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
                FragmentManager fragmentManager2 = screenStackFragment.mFragmentManager;
                if (fragmentManager2 == null || fragmentManager2 == backStackRecord.mManager) {
                    backStackRecord.addOp(new FragmentTransaction.Op(5, screenStackFragment));
                    if (backStackRecord.mAllowAddToBackStack) {
                        backStackRecord.mAddToBackStack = true;
                        backStackRecord.mName = "RN_SCREEN_LAST";
                        FragmentManager fragmentManager3 = screenStackFragment.mFragmentManager;
                        if (fragmentManager3 == null || fragmentManager3 == backStackRecord.mManager) {
                            backStackRecord.addOp(new FragmentTransaction.Op(8, screenStackFragment));
                            backStackRecord.commitAllowingStateLoss();
                            FragmentManager.OnBackStackChangedListener onBackStackChangedListener2 = this.mBackStackListener;
                            if (fragmentManager.mBackStackChangeListeners == null) {
                                fragmentManager.mBackStackChangeListeners = new ArrayList<>();
                            }
                            fragmentManager.mBackStackChangeListeners.add(onBackStackChangedListener2);
                            return;
                        }
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment ");
                        outline13.append(screenStackFragment.toString());
                        outline13.append(" is already attached to a FragmentManager.");
                        throw new IllegalStateException(outline13.toString());
                    }
                    throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Cannot show Fragment attached to a different FragmentManager. Fragment ");
                outline132.append(screenStackFragment.toString());
                outline132.append(" is already attached to a FragmentManager.");
                throw new IllegalStateException(outline132.toString());
            }
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public ScreenStackFragment adapt(Screen screen) {
        return new ScreenStackFragment(screen);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        List<DrawingOp> list;
        super.dispatchDraw(canvas);
        int i = 0;
        if (this.drawingOps.size() < this.previousChildrenCount) {
            this.reverseLastTwoChildren = false;
        }
        this.previousChildrenCount = this.drawingOps.size();
        if (this.reverseLastTwoChildren && this.drawingOps.size() >= 2) {
            Collections.swap(this.drawingOps, list.size() - 1, this.drawingOps.size() - 2);
        }
        int size = this.drawingOps.size() - 1;
        if (size >= 0) {
            while (true) {
                int i2 = i + 1;
                DrawingOp drawingOp = this.drawingOps.get(i);
                access$performDraw(ScreenStack.this, drawingOp);
                drawingOp.canvas = null;
                drawingOp.child = null;
                drawingOp.drawingTime = 0L;
                this.drawingOpPool.add(drawingOp);
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        this.drawingOps.clear();
    }

    public final void dispatchOnFinishTransitioning() {
        EventDispatcher eventDispatcher;
        Context context = getContext();
        Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        UIManagerModule uIManagerModule = (UIManagerModule) ((ReactContext) context).getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null && (eventDispatcher = uIManagerModule.getEventDispatcher()) != null) {
            eventDispatcher.dispatchEvent(new StackFinishTransitioningEvent(getId()));
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        DrawingOp drawingOp;
        List<DrawingOp> list = this.drawingOps;
        if (this.drawingOpPool.isEmpty()) {
            drawingOp = new DrawingOp();
        } else {
            List<DrawingOp> list2 = this.drawingOpPool;
            drawingOp = list2.remove(list2.size() - 1);
        }
        drawingOp.canvas = canvas;
        drawingOp.child = view;
        drawingOp.drawingTime = j;
        list.add(drawingOp);
        return true;
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        super.endViewTransition(view);
        if (this.mRemovalTransitionStarted) {
            this.mRemovalTransitionStarted = false;
            dispatchOnFinishTransitioning();
        }
    }

    public final Screen getRootScreen() {
        int screenCount = getScreenCount();
        for (int i = 0; i < screenCount; i++) {
            Screen screenAt = getScreenAt(i);
            if (!ArraysKt___ArraysKt.contains(this.mDismissed, screenAt.getFragment())) {
                return screenAt;
            }
        }
        throw new IllegalStateException("Stack has no root screen set");
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public Screen getTopScreen() {
        ScreenStackFragment screenStackFragment = this.mTopScreen;
        if (screenStackFragment == null) {
            return null;
        }
        return screenStackFragment.getScreen();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public boolean hasScreen(ScreenFragment screenFragment) {
        return ArraysKt___ArraysKt.contains(this.mScreenFragments, screenFragment) && !ArraysKt___ArraysKt.contains(this.mDismissed, screenFragment);
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void notifyContainerUpdate() {
        Iterator<ScreenStackFragment> it = this.mStack.iterator();
        while (it.hasNext()) {
            ScreenStackHeaderConfig headerConfig = it.next().getScreen().getHeaderConfig();
            if (headerConfig != null) {
                headerConfig.onUpdate();
            }
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            fragmentManager.mLifecycleCallbacksDispatcher.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksDispatcher.FragmentLifecycleCallbacksHolder(this.mLifecycleCallbacks, false));
            return;
        }
        throw new IllegalArgumentException("mFragmentManager is null when ScreenStack attached to window".toString());
    }

    @Override // com.swmansion.rnscreens.ScreenContainer, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            FragmentManager.OnBackStackChangedListener onBackStackChangedListener = this.mBackStackListener;
            ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = fragmentManager.mBackStackChangeListeners;
            if (arrayList != null) {
                arrayList.remove(onBackStackChangedListener);
            }
            FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks = this.mLifecycleCallbacks;
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = fragmentManager.mLifecycleCallbacksDispatcher;
            synchronized (fragmentLifecycleCallbacksDispatcher.mLifecycleCallbacks) {
                int size = fragmentLifecycleCallbacksDispatcher.mLifecycleCallbacks.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (fragmentLifecycleCallbacksDispatcher.mLifecycleCallbacks.get(i).mCallback == fragmentLifecycleCallbacks) {
                        fragmentLifecycleCallbacksDispatcher.mLifecycleCallbacks.remove(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!fragmentManager.isStateSaved() && !fragmentManager.mDestroyed) {
                fragmentManager.enqueueAction(new FragmentManager.PopBackStackState("RN_SCREEN_LAST", -1, 1), false);
            }
        }
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0127  */
    @Override // com.swmansion.rnscreens.ScreenContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performUpdate() {
        /*
            Method dump skipped, instructions count: 530
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStack.performUpdate():void");
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeAllScreens() {
        this.mDismissed.clear();
        super.removeAllScreens();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeScreenAt(int i) {
        Screen screen = ((ScreenFragment) this.mScreenFragments.get(i)).getScreen();
        Set<ScreenStackFragment> set = this.mDismissed;
        ScreenFragment fragment = screen.getFragment();
        Objects.requireNonNull(set, "null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        TypeIntrinsics.asMutableCollection(set).remove(fragment);
        super.removeScreenAt(i);
    }

    @Override // com.swmansion.rnscreens.ScreenContainer, android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.isDetachingCurrentScreen) {
            this.isDetachingCurrentScreen = false;
            this.reverseLastTwoChildren = true;
        }
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        super.startViewTransition(view);
        this.mRemovalTransitionStarted = true;
    }
}
