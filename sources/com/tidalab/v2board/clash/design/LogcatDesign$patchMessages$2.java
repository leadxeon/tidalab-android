package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.adapter.LogMessageAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogcatDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.LogcatDesign$patchMessages$2", f = "LogcatDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatDesign$patchMessages$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $appended;
    public final /* synthetic */ List<LogMessage> $messages;
    public final /* synthetic */ int $removed;
    public final /* synthetic */ LogcatDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatDesign$patchMessages$2(LogcatDesign logcatDesign, List<LogMessage> list, int i, int i2, Continuation<? super LogcatDesign$patchMessages$2> continuation) {
        super(2, continuation);
        this.this$0 = logcatDesign;
        this.$messages = list;
        this.$appended = i;
        this.$removed = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatDesign$patchMessages$2(this.this$0, this.$messages, this.$appended, this.$removed, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        LogcatDesign logcatDesign = this.this$0;
        List<LogMessage> list = this.$messages;
        int i = this.$appended;
        int i2 = this.$removed;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        LogMessageAdapter logMessageAdapter = logcatDesign.adapter;
        logMessageAdapter.messages = list;
        logMessageAdapter.mObservable.notifyItemRangeInserted(list.size(), i);
        boolean z = false;
        logcatDesign.adapter.mObservable.notifyItemRangeRemoved(0, i2);
        if (logcatDesign.streaming) {
            AppRecyclerView appRecyclerView = logcatDesign.binding.recyclerList;
            if (appRecyclerView.computeHorizontalScrollOffset() == 0 && appRecyclerView.computeVerticalScrollOffset() == 0) {
                z = true;
            }
            if (z) {
                logcatDesign.binding.recyclerList.scrollToPosition(list.size() - 1);
            }
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        LogMessageAdapter logMessageAdapter = this.this$0.adapter;
        List<LogMessage> list = this.$messages;
        logMessageAdapter.messages = list;
        logMessageAdapter.mObservable.notifyItemRangeInserted(list.size(), this.$appended);
        LogMessageAdapter logMessageAdapter2 = this.this$0.adapter;
        boolean z = false;
        logMessageAdapter2.mObservable.notifyItemRangeRemoved(0, this.$removed);
        LogcatDesign logcatDesign = this.this$0;
        if (logcatDesign.streaming) {
            AppRecyclerView appRecyclerView = logcatDesign.binding.recyclerList;
            if (appRecyclerView.computeHorizontalScrollOffset() == 0 && appRecyclerView.computeVerticalScrollOffset() == 0) {
                z = true;
            }
            if (z) {
                this.this$0.binding.recyclerList.scrollToPosition(this.$messages.size() - 1);
            }
        }
        return Unit.INSTANCE;
    }
}
