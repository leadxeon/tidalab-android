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
/* compiled from: PropertiesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$requestExitWithoutSaving$2", f = "PropertiesDesign.kt", l = {173}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesDesign$requestExitWithoutSaving$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ PropertiesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesDesign$requestExitWithoutSaving$2(PropertiesDesign propertiesDesign, Continuation<? super PropertiesDesign$requestExitWithoutSaving$2> continuation) {
        super(2, continuation);
        this.this$0 = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PropertiesDesign$requestExitWithoutSaving$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return new PropertiesDesign$requestExitWithoutSaving$2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            PropertiesDesign propertiesDesign = this.this$0;
            this.L$0 = propertiesDesign;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(propertiesDesign.context);
            materialAlertDialogBuilder.setTitle(R.string.exit_without_save);
            materialAlertDialogBuilder.setMessage(R.string.exit_without_save_warning);
            materialAlertDialogBuilder.P.mCancelable = true;
            materialAlertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.tidalab.v2board.clash.design.PropertiesDesign$requestExitWithoutSaving$2$1$dialog$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    cancellableContinuationImpl.resumeWith(Boolean.TRUE);
                }
            });
            materialAlertDialogBuilder.setNegativeButton(R.string.cancel, PropertiesDesign$requestExitWithoutSaving$2$1$dialog$2.INSTANCE);
            materialAlertDialogBuilder.P.mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.tidalab.v2board.clash.design.PropertiesDesign$requestExitWithoutSaving$2$1$dialog$3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    if (!cancellableContinuationImpl.isCompleted()) {
                        cancellableContinuationImpl.resumeWith(Boolean.FALSE);
                    }
                }
            };
            cancellableContinuationImpl.invokeOnCancellation(new PropertiesDesign$requestExitWithoutSaving$2$1$1(materialAlertDialogBuilder.show()));
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            PropertiesDesign propertiesDesign2 = (PropertiesDesign) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
