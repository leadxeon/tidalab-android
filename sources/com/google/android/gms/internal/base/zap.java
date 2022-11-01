package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes.dex */
public class zap extends Handler {
    public zap(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }

    public zap(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
