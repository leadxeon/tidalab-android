package com.facebook.react.bridge;
/* loaded from: classes.dex */
public abstract class GuardedRunnable implements Runnable {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    @Deprecated
    public GuardedRunnable(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            runGuarded();
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
        }
    }

    public abstract void runGuarded();

    public GuardedRunnable(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }
}
