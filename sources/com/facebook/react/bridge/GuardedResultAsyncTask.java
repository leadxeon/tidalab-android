package com.facebook.react.bridge;

import android.os.AsyncTask;
/* loaded from: classes.dex */
public abstract class GuardedResultAsyncTask<Result> extends AsyncTask<Void, Void, Result> {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    @Deprecated
    public GuardedResultAsyncTask(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    public abstract Result doInBackgroundGuarded();

    @Override // android.os.AsyncTask
    public final void onPostExecute(Result result) {
        try {
            onPostExecuteGuarded(result);
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
        }
    }

    public abstract void onPostExecuteGuarded(Result result);

    public GuardedResultAsyncTask(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }

    public final Result doInBackground(Void... voidArr) {
        try {
            return doInBackgroundGuarded();
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
            throw e;
        }
    }
}
