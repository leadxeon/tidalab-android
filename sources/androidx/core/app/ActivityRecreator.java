package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class ActivityRecreator {
    public static final Class<?> activityThreadClass;
    public static final Handler mainHandler = new Handler(Looper.getMainLooper());
    public static final Field mainThreadField;
    public static final Method performStopActivity2ParamsMethod;
    public static final Method performStopActivity3ParamsMethod;
    public static final Method requestRelaunchActivityMethod;
    public static final Field tokenField;

    /* loaded from: classes.dex */
    public static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
        public Object currentlyRecreatingToken;
        public Activity mActivity;
        public final int mRecreatingHashCode;
        public boolean mStarted = false;
        public boolean mDestroyed = false;
        public boolean mStopQueued = false;

        public LifecycleCheckCallbacks(Activity activity) {
            this.mActivity = activity;
            this.mRecreatingHashCode = activity.hashCode();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity == activity) {
                this.mActivity = null;
                this.mDestroyed = true;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
            r5.mStopQueued = true;
            r5.currentlyRecreatingToken = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
            return;
         */
        @Override // android.app.Application.ActivityLifecycleCallbacks
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onActivityPaused(android.app.Activity r6) {
            /*
                r5 = this;
                boolean r0 = r5.mDestroyed
                if (r0 == 0) goto L_0x0042
                boolean r0 = r5.mStopQueued
                if (r0 != 0) goto L_0x0042
                boolean r0 = r5.mStarted
                if (r0 != 0) goto L_0x0042
                java.lang.Object r0 = r5.currentlyRecreatingToken
                int r1 = r5.mRecreatingHashCode
                r2 = 0
                r3 = 1
                java.lang.reflect.Field r4 = androidx.core.app.ActivityRecreator.tokenField     // Catch: all -> 0x0033
                java.lang.Object r4 = r4.get(r6)     // Catch: all -> 0x0033
                if (r4 != r0) goto L_0x003b
                int r0 = r6.hashCode()     // Catch: all -> 0x0033
                if (r0 == r1) goto L_0x0021
                goto L_0x003b
            L_0x0021:
                java.lang.reflect.Field r0 = androidx.core.app.ActivityRecreator.mainThreadField     // Catch: all -> 0x0033
                java.lang.Object r6 = r0.get(r6)     // Catch: all -> 0x0033
                android.os.Handler r0 = androidx.core.app.ActivityRecreator.mainHandler     // Catch: all -> 0x0033
                androidx.core.app.ActivityRecreator$3 r1 = new androidx.core.app.ActivityRecreator$3     // Catch: all -> 0x0033
                r1.<init>()     // Catch: all -> 0x0033
                r0.postAtFrontOfQueue(r1)     // Catch: all -> 0x0033
                r2 = 1
                goto L_0x003b
            L_0x0033:
                r6 = move-exception
                java.lang.String r0 = "ActivityRecreator"
                java.lang.String r1 = "Exception while fetching field values"
                android.util.Log.e(r0, r1, r6)
            L_0x003b:
                if (r2 == 0) goto L_0x0042
                r5.mStopQueued = r3
                r6 = 0
                r5.currentlyRecreatingToken = r6
            L_0x0042:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ActivityRecreator.LifecycleCheckCallbacks.onActivityPaused(android.app.Activity):void");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.mActivity == activity) {
                this.mStarted = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0078 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            android.os.Handler r0 = new android.os.Handler
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            r0.<init>(r1)
            androidx.core.app.ActivityRecreator.mainHandler = r0
            r0 = 0
            java.lang.String r1 = "android.app.ActivityThread"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: all -> 0x0013
            goto L_0x0014
        L_0x0013:
            r1 = r0
        L_0x0014:
            androidx.core.app.ActivityRecreator.activityThreadClass = r1
            r1 = 1
            java.lang.Class<android.app.Activity> r2 = android.app.Activity.class
            java.lang.String r3 = "mMainThread"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: all -> 0x0023
            r2.setAccessible(r1)     // Catch: all -> 0x0023
            goto L_0x0024
        L_0x0023:
            r2 = r0
        L_0x0024:
            androidx.core.app.ActivityRecreator.mainThreadField = r2
            java.lang.Class<android.app.Activity> r2 = android.app.Activity.class
            java.lang.String r3 = "mToken"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: all -> 0x0032
            r2.setAccessible(r1)     // Catch: all -> 0x0032
            goto L_0x0033
        L_0x0032:
            r2 = r0
        L_0x0033:
            androidx.core.app.ActivityRecreator.tokenField = r2
            java.lang.Class<?> r2 = androidx.core.app.ActivityRecreator.activityThreadClass
            r3 = 3
            java.lang.String r4 = "performStopActivity"
            r5 = 2
            r6 = 0
            if (r2 != 0) goto L_0x0040
        L_0x003e:
            r2 = r0
            goto L_0x0055
        L_0x0040:
            java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch: all -> 0x003e
            java.lang.Class<android.os.IBinder> r8 = android.os.IBinder.class
            r7[r6] = r8     // Catch: all -> 0x003e
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: all -> 0x003e
            r7[r1] = r8     // Catch: all -> 0x003e
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r5] = r8     // Catch: all -> 0x003e
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r4, r7)     // Catch: all -> 0x003e
            r2.setAccessible(r1)     // Catch: all -> 0x003e
        L_0x0055:
            androidx.core.app.ActivityRecreator.performStopActivity3ParamsMethod = r2
            java.lang.Class<?> r2 = androidx.core.app.ActivityRecreator.activityThreadClass
            if (r2 != 0) goto L_0x005d
        L_0x005b:
            r2 = r0
            goto L_0x006e
        L_0x005d:
            java.lang.Class[] r7 = new java.lang.Class[r5]     // Catch: all -> 0x005b
            java.lang.Class<android.os.IBinder> r8 = android.os.IBinder.class
            r7[r6] = r8     // Catch: all -> 0x005b
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: all -> 0x005b
            r7[r1] = r8     // Catch: all -> 0x005b
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r4, r7)     // Catch: all -> 0x005b
            r2.setAccessible(r1)     // Catch: all -> 0x005b
        L_0x006e:
            androidx.core.app.ActivityRecreator.performStopActivity2ParamsMethod = r2
            java.lang.Class<?> r2 = androidx.core.app.ActivityRecreator.activityThreadClass
            boolean r4 = needsRelaunchCall()
            if (r4 == 0) goto L_0x00af
            if (r2 != 0) goto L_0x007b
            goto L_0x00af
        L_0x007b:
            java.lang.String r4 = "requestRelaunchActivity"
            r7 = 9
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch: all -> 0x00af
            java.lang.Class<android.os.IBinder> r8 = android.os.IBinder.class
            r7[r6] = r8     // Catch: all -> 0x00af
            java.lang.Class<java.util.List> r6 = java.util.List.class
            r7[r1] = r6     // Catch: all -> 0x00af
            java.lang.Class<java.util.List> r6 = java.util.List.class
            r7[r5] = r6     // Catch: all -> 0x00af
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch: all -> 0x00af
            r7[r3] = r5     // Catch: all -> 0x00af
            r3 = 4
            java.lang.Class r5 = java.lang.Boolean.TYPE     // Catch: all -> 0x00af
            r7[r3] = r5     // Catch: all -> 0x00af
            r3 = 5
            java.lang.Class<android.content.res.Configuration> r6 = android.content.res.Configuration.class
            r7[r3] = r6     // Catch: all -> 0x00af
            r3 = 6
            java.lang.Class<android.content.res.Configuration> r6 = android.content.res.Configuration.class
            r7[r3] = r6     // Catch: all -> 0x00af
            r3 = 7
            r7[r3] = r5     // Catch: all -> 0x00af
            r3 = 8
            r7[r3] = r5     // Catch: all -> 0x00af
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r4, r7)     // Catch: all -> 0x00af
            r2.setAccessible(r1)     // Catch: all -> 0x00af
            r0 = r2
        L_0x00af:
            androidx.core.app.ActivityRecreator.requestRelaunchActivityMethod = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ActivityRecreator.<clinit>():void");
    }

    public static boolean needsRelaunchCall() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    public static boolean recreate(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        } else if (needsRelaunchCall() && requestRelaunchActivityMethod == null) {
            return false;
        } else {
            if (performStopActivity2ParamsMethod == null && performStopActivity3ParamsMethod == null) {
                return false;
            }
            try {
                final Object obj2 = tokenField.get(activity);
                if (obj2 == null || (obj = mainThreadField.get(activity)) == null) {
                    return false;
                }
                final Application application = activity.getApplication();
                final LifecycleCheckCallbacks lifecycleCheckCallbacks = new LifecycleCheckCallbacks(activity);
                application.registerActivityLifecycleCallbacks(lifecycleCheckCallbacks);
                Handler handler = mainHandler;
                handler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LifecycleCheckCallbacks.this.currentlyRecreatingToken = obj2;
                    }
                });
                if (needsRelaunchCall()) {
                    Method method = requestRelaunchActivityMethod;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.2
                    @Override // java.lang.Runnable
                    public void run() {
                        application.unregisterActivityLifecycleCallbacks(lifecycleCheckCallbacks);
                    }
                });
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }
}
