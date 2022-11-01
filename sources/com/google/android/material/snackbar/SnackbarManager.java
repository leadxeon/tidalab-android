package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class SnackbarManager {
    public static SnackbarManager snackbarManager;
    public SnackbarRecord currentSnackbar;
    public SnackbarRecord nextSnackbar;
    public final Object lock = new Object();
    public final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.SnackbarManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            SnackbarManager snackbarManager2 = SnackbarManager.this;
            SnackbarRecord snackbarRecord = (SnackbarRecord) message.obj;
            synchronized (snackbarManager2.lock) {
                if (snackbarManager2.currentSnackbar == snackbarRecord || snackbarManager2.nextSnackbar == snackbarRecord) {
                    snackbarManager2.cancelSnackbarLocked(snackbarRecord, 2);
                }
            }
            return true;
        }
    });

    /* loaded from: classes.dex */
    public interface Callback {
        void dismiss(int i);

        void show();
    }

    /* loaded from: classes.dex */
    public static class SnackbarRecord {
        public final WeakReference<Callback> callback;
        public int duration;
        public boolean paused;

        public SnackbarRecord(int i, Callback callback) {
            this.callback = new WeakReference<>(callback);
            this.duration = i;
        }
    }

    public static SnackbarManager getInstance() {
        if (snackbarManager == null) {
            snackbarManager = new SnackbarManager();
        }
        return snackbarManager;
    }

    public final boolean cancelSnackbarLocked(SnackbarRecord snackbarRecord, int i) {
        Callback callback = snackbarRecord.callback.get();
        if (callback == null) {
            return false;
        }
        this.handler.removeCallbacksAndMessages(snackbarRecord);
        callback.dismiss(i);
        return true;
    }

    public final boolean isCurrentSnackbarLocked(Callback callback) {
        SnackbarRecord snackbarRecord = this.currentSnackbar;
        if (snackbarRecord == null) {
            return false;
        }
        return callback != null && snackbarRecord.callback.get() == callback;
    }

    public final boolean isNextSnackbarLocked(Callback callback) {
        SnackbarRecord snackbarRecord = this.nextSnackbar;
        if (snackbarRecord == null) {
            return false;
        }
        return callback != null && snackbarRecord.callback.get() == callback;
    }

    public void pauseTimeout(Callback callback) {
        synchronized (this.lock) {
            if (isCurrentSnackbarLocked(callback)) {
                SnackbarRecord snackbarRecord = this.currentSnackbar;
                if (!snackbarRecord.paused) {
                    snackbarRecord.paused = true;
                    this.handler.removeCallbacksAndMessages(snackbarRecord);
                }
            }
        }
    }

    public void restoreTimeoutIfPaused(Callback callback) {
        synchronized (this.lock) {
            if (isCurrentSnackbarLocked(callback)) {
                SnackbarRecord snackbarRecord = this.currentSnackbar;
                if (snackbarRecord.paused) {
                    snackbarRecord.paused = false;
                    scheduleTimeoutLocked(snackbarRecord);
                }
            }
        }
    }

    public final void scheduleTimeoutLocked(SnackbarRecord snackbarRecord) {
        int i = snackbarRecord.duration;
        if (i != -2) {
            if (i <= 0) {
                i = i == -1 ? 1500 : 2750;
            }
            this.handler.removeCallbacksAndMessages(snackbarRecord);
            Handler handler = this.handler;
            handler.sendMessageDelayed(Message.obtain(handler, 0, snackbarRecord), i);
        }
    }

    public final void showNextSnackbarLocked() {
        SnackbarRecord snackbarRecord = this.nextSnackbar;
        if (snackbarRecord != null) {
            this.currentSnackbar = snackbarRecord;
            this.nextSnackbar = null;
            Callback callback = snackbarRecord.callback.get();
            if (callback != null) {
                callback.show();
            } else {
                this.currentSnackbar = null;
            }
        }
    }
}
