package com.th3rdwave.safeareacontext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;
import com.horcrux.svg.PathParser;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class SafeAreaView extends ReactViewGroup implements ViewTreeObserver.OnPreDrawListener {
    public EnumSet<SafeAreaViewEdges> mEdges;
    public EdgeInsets mInsets;
    public SafeAreaViewMode mMode = SafeAreaViewMode.PADDING;
    public View mProviderView;

    public SafeAreaView(Context context) {
        super(context);
    }

    public static ReactContext getReactContext(View view) {
        Context context = view.getContext();
        if (!(context instanceof ReactContext) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }

    public final boolean maybeUpdateInsets() {
        EdgeInsets safeAreaInsets;
        EdgeInsets edgeInsets;
        View view = this.mProviderView;
        if (view == null || (safeAreaInsets = PathParser.getSafeAreaInsets(view)) == null || ((edgeInsets = this.mInsets) != null && edgeInsets.equalsToEdgeInsets(safeAreaInsets))) {
            return false;
        }
        this.mInsets = safeAreaInsets;
        updateInsets();
        return true;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        View view;
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        while (true) {
            if (parent == null) {
                view = this;
                break;
            } else if (parent instanceof SafeAreaProvider) {
                view = (View) parent;
                break;
            } else {
                parent = parent.getParent();
            }
        }
        this.mProviderView = view;
        view.getViewTreeObserver().addOnPreDrawListener(this);
        maybeUpdateInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View view = this.mProviderView;
        if (view != null) {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.mProviderView = null;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        return !maybeUpdateInsets();
    }

    public void setEdges(EnumSet<SafeAreaViewEdges> enumSet) {
        this.mEdges = enumSet;
        updateInsets();
    }

    public void setMode(SafeAreaViewMode safeAreaViewMode) {
        this.mMode = safeAreaViewMode;
        updateInsets();
    }

    public final void updateInsets() {
        if (this.mInsets != null) {
            EnumSet<SafeAreaViewEdges> enumSet = this.mEdges;
            if (enumSet == null) {
                enumSet = EnumSet.allOf(SafeAreaViewEdges.class);
            }
            SafeAreaViewLocalData safeAreaViewLocalData = new SafeAreaViewLocalData(this.mInsets, this.mMode, enumSet);
            UIManagerModule uIManagerModule = (UIManagerModule) getReactContext(this).getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), safeAreaViewLocalData);
                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                long nanoTime = System.nanoTime();
                getReactContext(this).runOnNativeModulesQueueThread(new Runnable(this) { // from class: com.th3rdwave.safeareacontext.SafeAreaView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (atomicBoolean) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                atomicBoolean.notify();
                            }
                        }
                    }
                });
                synchronized (atomicBoolean) {
                    long j = 0;
                    while (!atomicBoolean.get() && j < 500000000) {
                        try {
                            atomicBoolean.wait(500L);
                        } catch (InterruptedException unused) {
                            atomicBoolean.set(true);
                        }
                        j += System.nanoTime() - nanoTime;
                    }
                    if (j >= 500000000) {
                        Log.w("SafeAreaView", "Timed out waiting for layout.");
                    }
                }
            }
        }
    }
}
