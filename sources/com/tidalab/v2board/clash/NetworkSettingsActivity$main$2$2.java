package com.tidalab.v2board.clash;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.NetworkSettingsDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
/* compiled from: NetworkSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NetworkSettingsActivity$main$2$2", f = "NetworkSettingsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NetworkSettingsActivity$main$2$2 extends SuspendLambda implements Function2<NetworkSettingsDesign.Request, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ NetworkSettingsActivity this$0;

    /* compiled from: NetworkSettingsActivity.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = {1};

        static {
            NetworkSettingsDesign.Request.values();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkSettingsActivity$main$2$2(NetworkSettingsActivity networkSettingsActivity, Continuation<? super NetworkSettingsActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = networkSettingsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkSettingsActivity$main$2$2 networkSettingsActivity$main$2$2 = new NetworkSettingsActivity$main$2$2(this.this$0, continuation);
        networkSettingsActivity$main$2$2.L$0 = obj;
        return networkSettingsActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(NetworkSettingsDesign.Request request, Continuation<? super Unit> continuation) {
        NetworkSettingsDesign.Request request2 = request;
        Continuation<? super Unit> continuation2 = continuation;
        NetworkSettingsActivity networkSettingsActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        if (WhenMappings.$EnumSwitchMapping$0[request2.ordinal()] == 1) {
            networkSettingsActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AccessControlActivity.class)));
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        if (WhenMappings.$EnumSwitchMapping$0[((NetworkSettingsDesign.Request) this.L$0).ordinal()] == 1) {
            this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AccessControlActivity.class)));
        }
        return Unit.INSTANCE;
    }
}
