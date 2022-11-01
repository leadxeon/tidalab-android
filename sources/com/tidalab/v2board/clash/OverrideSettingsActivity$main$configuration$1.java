package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: OverrideSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$configuration$1", f = "OverrideSettingsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class OverrideSettingsActivity$main$configuration$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super ConfigurationOverride>, Object> {
    public /* synthetic */ Object L$0;

    public OverrideSettingsActivity$main$configuration$1(Continuation<? super OverrideSettingsActivity$main$configuration$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OverrideSettingsActivity$main$configuration$1 overrideSettingsActivity$main$configuration$1 = new OverrideSettingsActivity$main$configuration$1(continuation);
        overrideSettingsActivity$main$configuration$1.L$0 = obj;
        return overrideSettingsActivity$main$configuration$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super ConfigurationOverride> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super ConfigurationOverride> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryOverride(Clash.OverrideSlot.Persist);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryOverride(Clash.OverrideSlot.Persist);
    }
}
