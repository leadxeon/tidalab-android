package com.facebook.react.bridge;

import android.os.AsyncTask;
/* loaded from: classes.dex */
public abstract class GuardedAsyncTask<Params, Progress> extends AsyncTask<Params, Progress, Void> {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    @Deprecated
    public GuardedAsyncTask(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    public abstract void doInBackgroundGuarded(Params... paramsArr);

    public GuardedAsyncTask(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }

    @Override // android.os.AsyncTask
    public final Void doInBackground(Params... paramsArr) {
        try {
            doInBackgroundGuarded(paramsArr);
            return null;
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
            return null;
        }
    }
}
