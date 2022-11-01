package com.tidalab.v2board.clash;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$querySelectProxy$1", f = "NativeModule.kt", l = {142, 144}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$querySelectProxy$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Callback $successCallback;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$querySelectProxy$1$1", f = "NativeModule.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$querySelectProxy$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ WritableArray $array;
        public final /* synthetic */ List<String> $names;
        public final /* synthetic */ Callback $successCallback;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<String> list, Callback callback, WritableArray writableArray, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$names = list;
            this.$successCallback = callback;
            this.$array = writableArray;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$names, this.$successCallback, this.$array, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            List<String> list = this.$names;
            Callback callback = this.$successCallback;
            WritableArray writableArray = this.$array;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            if (!list.isEmpty()) {
                callback.invoke(iClashManager2.queryProxyGroup(list.get(0), ProxySort.Default).now, writableArray);
            }
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            IClashManager iClashManager = (IClashManager) this.L$0;
            if (!this.$names.isEmpty()) {
                this.$successCallback.invoke(iClashManager.queryProxyGroup(this.$names.get(0), ProxySort.Default).now, this.$array);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$querySelectProxy$1(Callback callback, Continuation<? super NativeModule$querySelectProxy$1> continuation) {
        super(2, continuation);
        this.$successCallback = callback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$querySelectProxy$1(this.$successCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$querySelectProxy$1(this.$successCallback, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            NativeModule$querySelectProxy$1$names$1 nativeModule$querySelectProxy$1$names$1 = new NativeModule$querySelectProxy$1$names$1(null);
            this.label = 1;
            obj = InputKt.withClash$default(null, nativeModule$querySelectProxy$1$names$1, this, 1);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List list = (List) obj;
        AnonymousClass1 r5 = new AnonymousClass1(list, this.$successCallback, Arguments.fromList(list), null);
        this.label = 2;
        if (InputKt.withClash$default(null, r5, this, 1) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
