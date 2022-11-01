package com.tidalab.v2board.clash;

import com.facebook.react.bridge.Callback;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$selectTunnelStateMode$1", f = "NativeModule.kt", l = {164}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$selectTunnelStateMode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $mode;
    public final /* synthetic */ String $serverName;
    public final /* synthetic */ Callback $successCallback;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$selectTunnelStateMode$1$1", f = "NativeModule.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$selectTunnelStateMode$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $mode;
        public final /* synthetic */ String $serverName;
        public final /* synthetic */ Callback $successCallback;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, String str2, Callback callback, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mode = str;
            this.$serverName = str2;
            this.$successCallback = callback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$mode, this.$serverName, this.$successCallback, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            String str = this.$mode;
            String str2 = this.$serverName;
            Callback callback = this.$successCallback;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            Clash.OverrideSlot overrideSlot = Clash.OverrideSlot.Session;
            ConfigurationOverride queryOverride = iClashManager2.queryOverride(overrideSlot);
            if (Intrinsics.areEqual(str, "rule")) {
                queryOverride.mode = TunnelState.Mode.Rule;
                iClashManager2.patchSelector("SELECT", str2);
            }
            if (Intrinsics.areEqual(str, "global")) {
                queryOverride.mode = TunnelState.Mode.Global;
                iClashManager2.patchSelector("GLOBAL", str2);
            }
            iClashManager2.patchOverride(overrideSlot, queryOverride);
            callback.invoke(String.valueOf(queryOverride.mode));
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            IClashManager iClashManager = (IClashManager) this.L$0;
            Clash.OverrideSlot overrideSlot = Clash.OverrideSlot.Session;
            ConfigurationOverride queryOverride = iClashManager.queryOverride(overrideSlot);
            if (Intrinsics.areEqual(this.$mode, "rule")) {
                queryOverride.mode = TunnelState.Mode.Rule;
                iClashManager.patchSelector("SELECT", this.$serverName);
            }
            if (Intrinsics.areEqual(this.$mode, "global")) {
                queryOverride.mode = TunnelState.Mode.Global;
                iClashManager.patchSelector("GLOBAL", this.$serverName);
            }
            iClashManager.patchOverride(overrideSlot, queryOverride);
            this.$successCallback.invoke(String.valueOf(queryOverride.mode));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$selectTunnelStateMode$1(String str, String str2, Callback callback, Continuation<? super NativeModule$selectTunnelStateMode$1> continuation) {
        super(2, continuation);
        this.$mode = str;
        this.$serverName = str2;
        this.$successCallback = callback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$selectTunnelStateMode$1(this.$mode, this.$serverName, this.$successCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$selectTunnelStateMode$1(this.$mode, this.$serverName, this.$successCallback, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r7 = new AnonymousClass1(this.$mode, this.$serverName, this.$successCallback, null);
            this.label = 1;
            if (InputKt.withClash$default(null, r7, this, 1) == coroutineSingletons) {
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
