package com.tidalab.v2board.clash;

import android.net.Uri;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$mainLocalFile$3", f = "LogcatActivity.kt", l = {77}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$mainLocalFile$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LogFile $file;
    public final /* synthetic */ List<LogMessage> $messages;
    public final /* synthetic */ Uri $output;
    public int label;
    public final /* synthetic */ LogcatActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$mainLocalFile$3(LogcatActivity logcatActivity, List<LogMessage> list, LogFile logFile, Uri uri, Continuation<? super LogcatActivity$mainLocalFile$3> continuation) {
        super(2, continuation);
        this.this$0 = logcatActivity;
        this.$messages = list;
        this.$file = logFile;
        this.$output = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatActivity$mainLocalFile$3(this.this$0, this.$messages, this.$file, this.$output, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new LogcatActivity$mainLocalFile$3(this.this$0, this.$messages, this.$file, this.$output, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            LogcatActivity logcatActivity = this.this$0;
            List<LogMessage> list = this.$messages;
            LogFile logFile = this.$file;
            Uri uri = this.$output;
            this.label = 1;
            if (LogcatActivity.access$writeLogTo(logcatActivity, list, logFile, uri, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
