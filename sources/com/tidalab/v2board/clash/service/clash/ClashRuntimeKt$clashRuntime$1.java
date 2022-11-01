package com.tidalab.v2board.clash.service.clash;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: ClashRuntime.kt */
/* loaded from: classes.dex */
public final class ClashRuntimeKt$clashRuntime$1 implements ClashRuntime {
    public final /* synthetic */ Function2<ClashRuntimeScope, Continuation<? super Unit>, Object> $block;
    public final /* synthetic */ CoroutineScope $this_clashRuntime;

    /* JADX WARN: Multi-variable type inference failed */
    public ClashRuntimeKt$clashRuntime$1(CoroutineScope coroutineScope, Function2<? super ClashRuntimeScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.$this_clashRuntime = coroutineScope;
        this.$block = function2;
    }

    @Override // com.tidalab.v2board.clash.service.clash.ClashRuntime
    public void launch() {
        CoroutineScope coroutineScope = this.$this_clashRuntime;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        InputKt.launch$default(coroutineScope, Dispatchers.IO, null, new ClashRuntimeKt$clashRuntime$1$launch$1(this.$block, null), 2, null);
    }

    @Override // com.tidalab.v2board.clash.service.clash.ClashRuntime
    public void requestGc() {
        Clash clash = Clash.INSTANCE;
        Bridge.INSTANCE.nativeForceGc();
    }
}
