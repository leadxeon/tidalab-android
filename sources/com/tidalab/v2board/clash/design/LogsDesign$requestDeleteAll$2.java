package com.tidalab.v2board.clash.design;

import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogsDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.LogsDesign$requestDeleteAll$2", f = "LogsDesign.kt", l = {58}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogsDesign$requestDeleteAll$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ LogsDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsDesign$requestDeleteAll$2(LogsDesign logsDesign, Continuation<? super LogsDesign$requestDeleteAll$2> continuation) {
        super(2, continuation);
        this.this$0 = logsDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogsDesign$requestDeleteAll$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return new LogsDesign$requestDeleteAll$2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            LogsDesign logsDesign = this.this$0;
            this.L$0 = logsDesign;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(logsDesign.context);
            materialAlertDialogBuilder.setTitle(R.string.delete_all_logs);
            materialAlertDialogBuilder.setMessage(R.string.delete_all_logs_warn);
            materialAlertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.tidalab.v2board.clash.design.LogsDesign$requestDeleteAll$2$1$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    cancellableContinuationImpl.resumeWith(Boolean.TRUE);
                }
            });
            materialAlertDialogBuilder.setNegativeButton(R.string.cancel, LogsDesign$requestDeleteAll$2$1$2.INSTANCE);
            materialAlertDialogBuilder.show().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tidalab.v2board.clash.design.LogsDesign$requestDeleteAll$2$1$3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    if (!cancellableContinuationImpl.isCompleted()) {
                        cancellableContinuationImpl.resumeWith(Boolean.FALSE);
                    }
                }
            });
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            LogsDesign logsDesign2 = (LogsDesign) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
