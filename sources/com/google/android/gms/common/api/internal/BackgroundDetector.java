package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public static final BackgroundDetector zzat = new BackgroundDetector();
    public final AtomicBoolean zzau = new AtomicBoolean();
    public final AtomicBoolean zzav = new AtomicBoolean();
    public final ArrayList<BackgroundStateChangeListener> zzaw = new ArrayList<>();
    public boolean zzax = false;

    /* loaded from: classes.dex */
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public final void onBackgroundStateChanged(boolean z) {
        synchronized (zzat) {
            ArrayList<BackgroundStateChangeListener> arrayList = this.zzaw;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                BackgroundStateChangeListener backgroundStateChangeListener = arrayList.get(i);
                i++;
                backgroundStateChangeListener.onBackgroundStateChanged(z);
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzau.compareAndSet(false, true)) {
            this.zzav.set(true);
            onBackgroundStateChanged(true);
        }
    }
}
