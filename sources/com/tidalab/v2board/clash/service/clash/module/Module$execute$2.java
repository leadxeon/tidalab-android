package com.tidalab.v2board.clash.service.clash.module;

import android.content.BroadcastReceiver;
import android.util.Log;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Module.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.Module$execute$2", f = "Module.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class Module$execute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    public final /* synthetic */ String $moduleName;
    public final /* synthetic */ Module<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Module$execute$2(Module<E> module, String str, Continuation<? super Module$execute$2> continuation) {
        super(2, continuation);
        this.this$0 = module;
        this.$moduleName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Module$execute$2(this.this$0, this.$moduleName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        Continuation<? super Integer> continuation2 = continuation;
        Module<E> module = this.this$0;
        String str = this.$moduleName;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        for (BroadcastReceiver broadcastReceiver : module.receivers) {
            broadcastReceiver.onReceive(null, null);
            module.service.unregisterReceiver(broadcastReceiver);
        }
        return new Integer(Log.d("ClashForAndroid", Intrinsics.stringPlus(str, ": destroyed"), null));
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Module<E> module = this.this$0;
        for (BroadcastReceiver broadcastReceiver : module.receivers) {
            broadcastReceiver.onReceive(null, null);
            module.service.unregisterReceiver(broadcastReceiver);
        }
        boolean z = true & true;
        return new Integer(Log.d("ClashForAndroid", Intrinsics.stringPlus(this.$moduleName, ": destroyed"), null));
    }
}
