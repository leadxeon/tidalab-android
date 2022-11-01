package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.LogsDesign;
import com.tidalab.v2board.clash.design.model.LogFile;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: LogsDesign.kt */
/* loaded from: classes.dex */
public final class LogsDesign$adapter$1 extends Lambda implements Function1<LogFile, Unit> {
    public final /* synthetic */ LogsDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsDesign$adapter$1(LogsDesign logsDesign) {
        super(1);
        this.this$0 = logsDesign;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(LogFile logFile) {
        this.this$0.requests.mo14trySendJP2dKIU(new LogsDesign.Request.OpenFile(logFile));
        return Unit.INSTANCE;
    }
}
