package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.R$dimen;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class DeferredReleaser {
    public static DeferredReleaser sInstance;
    public final Runnable releaseRunnable = new Runnable() { // from class: com.facebook.drawee.components.DeferredReleaser.1
        @Override // java.lang.Runnable
        public void run() {
            DeferredReleaser.ensureOnUiThread();
            for (Releasable releasable : DeferredReleaser.this.mPendingReleasables) {
                releasable.release();
            }
            DeferredReleaser.this.mPendingReleasables.clear();
        }
    };
    public final Set<Releasable> mPendingReleasables = new HashSet();
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    public interface Releasable {
        void release();
    }

    public static void ensureOnUiThread() {
        R$dimen.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
    }

    public void cancelDeferredRelease(Releasable releasable) {
        ensureOnUiThread();
        this.mPendingReleasables.remove(releasable);
    }
}
