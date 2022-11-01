package com.facebook.react.bridge;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public class UiThreadUtil {
    private static Handler sMainHandler;

    public static void assertNotOnUiThread() {
        SoftAssertions.assertCondition(!isOnUiThread(), "Expected not to run on UI thread!");
    }

    public static void assertOnUiThread() {
        SoftAssertions.assertCondition(isOnUiThread(), "Expected to run on UI thread!");
    }

    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0L);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        synchronized (UiThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.postDelayed(runnable, j);
    }
}
