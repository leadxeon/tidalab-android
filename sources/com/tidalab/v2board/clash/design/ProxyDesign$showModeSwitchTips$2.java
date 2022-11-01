package com.tidalab.v2board.clash.design;

import android.widget.Toast;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProxyDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProxyDesign$showModeSwitchTips$2", f = "ProxyDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyDesign$showModeSwitchTips$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProxyDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyDesign$showModeSwitchTips$2(ProxyDesign proxyDesign, Continuation<? super ProxyDesign$showModeSwitchTips$2> continuation) {
        super(2, continuation);
        this.this$0 = proxyDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProxyDesign$showModeSwitchTips$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        ProxyDesign proxyDesign = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        Toast.makeText(proxyDesign.context, (int) R.string.mode_switch_tips, 1).show();
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Toast.makeText(this.this$0.context, (int) R.string.mode_switch_tips, 1).show();
        return Unit.INSTANCE;
    }
}
