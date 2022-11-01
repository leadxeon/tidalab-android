package com.facebook.imagepipeline.producers;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.PrintWriter;
import java.io.StringWriter;
/* loaded from: classes.dex */
public abstract class BaseConsumer<T> implements Consumer<T> {
    public boolean mIsFinished = false;

    public static boolean isLast(int i) {
        return (i & 1) == 1;
    }

    public static boolean isNotLast(int i) {
        return !isLast(i);
    }

    public static boolean statusHasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    @Override // com.facebook.imagepipeline.producers.Consumer
    public synchronized void onCancellation() {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onCancellationImpl();
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public abstract void onCancellationImpl();

    @Override // com.facebook.imagepipeline.producers.Consumer
    public synchronized void onFailure(Throwable th) {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onFailureImpl(th);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public abstract void onFailureImpl(Throwable th);

    @Override // com.facebook.imagepipeline.producers.Consumer
    public synchronized void onNewResult(T t, int i) {
        if (!this.mIsFinished) {
            this.mIsFinished = isLast(i);
            try {
                onNewResultImpl(t, i);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public abstract void onNewResultImpl(T t, int i);

    @Override // com.facebook.imagepipeline.producers.Consumer
    public synchronized void onProgressUpdate(float f) {
        if (!this.mIsFinished) {
            try {
                onProgressUpdateImpl(f);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public abstract void onProgressUpdateImpl(float f);

    public void onUnhandledException(Exception exc) {
        String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", getClass().getSimpleName());
        StringBuilder outline14 = GeneratedOutlineSupport.outline14("unhandled exception", '\n');
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        outline14.append(stringWriter.toString());
        Log.println(6, outline9, outline14.toString());
    }
}
