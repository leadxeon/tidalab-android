package com.tidalab.v2board.clash.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationObserver {
    public static boolean appVisible;
    public static final ApplicationObserver INSTANCE = new ApplicationObserver();
    public static final Set<Activity> activities = new LinkedHashSet();
    public static Function1<? super Boolean, Unit> visibleChanged = ApplicationObserver$visibleChanged$1.INSTANCE;
    public static final ApplicationObserver$activityObserver$1 activityObserver = new Application.ActivityLifecycleCallbacks() { // from class: com.tidalab.v2board.clash.util.ApplicationObserver$activityObserver$1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public synchronized void onActivityCreated(Activity activity, Bundle bundle) {
            ApplicationObserver.activities.add(activity);
            ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
            if (!ApplicationObserver.appVisible) {
                ApplicationObserver.appVisible = true;
                ApplicationObserver.visibleChanged.invoke(Boolean.TRUE);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public synchronized void onActivityDestroyed(Activity activity) {
            Set<Activity> set = ApplicationObserver.activities;
            set.remove(activity);
            ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
            boolean z = !set.isEmpty();
            if (ApplicationObserver.appVisible != z) {
                ApplicationObserver.appVisible = z;
                ApplicationObserver.visibleChanged.invoke(Boolean.valueOf(z));
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    };
}
