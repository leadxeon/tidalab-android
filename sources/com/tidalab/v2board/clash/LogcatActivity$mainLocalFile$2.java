package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$mainLocalFile$2", f = "LogcatActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$mainLocalFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ LogFile $file;
    public final /* synthetic */ LogcatActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$mainLocalFile$2(LogcatActivity logcatActivity, LogFile logFile, Continuation<? super LogcatActivity$mainLocalFile$2> continuation) {
        super(2, continuation);
        this.this$0 = logcatActivity;
        this.$file = logFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatActivity$mainLocalFile$2(this.this$0, this.$file, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        LogcatActivity logcatActivity = this.this$0;
        LogFile logFile = this.$file;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return Boolean.valueOf(FilesKt__UtilsKt.resolve(InputKt.getLogsDir(logcatActivity), logFile.fileName).delete());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return Boolean.valueOf(FilesKt__UtilsKt.resolve(InputKt.getLogsDir(this.this$0), this.$file.fileName).delete());
    }
}
