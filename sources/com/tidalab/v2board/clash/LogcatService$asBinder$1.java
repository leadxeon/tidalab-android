package com.tidalab.v2board.clash;

import android.os.Binder;
import android.os.IInterface;
/* compiled from: LogcatService.kt */
/* loaded from: classes.dex */
public final class LogcatService$asBinder$1 extends Binder {
    public final /* synthetic */ LogcatService this$0;

    public LogcatService$asBinder$1(LogcatService logcatService) {
        this.this$0 = logcatService;
    }

    @Override // android.os.Binder, android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        return this.this$0;
    }
}
