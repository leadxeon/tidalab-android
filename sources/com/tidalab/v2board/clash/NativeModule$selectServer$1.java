package com.tidalab.v2board.clash;

import com.facebook.react.bridge.Callback;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$selectServer$1", f = "NativeModule.kt", l = {155}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$selectServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $group;
    public final /* synthetic */ String $name;
    public final /* synthetic */ Callback $successCallback;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$selectServer$1$1", f = "NativeModule.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$selectServer$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $group;
        public final /* synthetic */ String $name;
        public final /* synthetic */ Callback $successCallback;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Callback callback, String str, String str2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$successCallback = callback;
            this.$group = str;
            this.$name = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$successCallback, this.$group, this.$name, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            Callback callback = this.$successCallback;
            String str = this.$group;
            String str2 = this.$name;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            callback.invoke(Boolean.valueOf(iClashManager2.patchSelector(str, str2)));
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.$successCallback.invoke(Boolean.valueOf(((IClashManager) this.L$0).patchSelector(this.$group, this.$name)));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$selectServer$1(Callback callback, String str, String str2, Continuation<? super NativeModule$selectServer$1> continuation) {
        super(2, continuation);
        this.$successCallback = callback;
        this.$group = str;
        this.$name = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$selectServer$1(this.$successCallback, this.$group, this.$name, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$selectServer$1(this.$successCallback, this.$group, this.$name, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r7 = new AnonymousClass1(this.$successCallback, this.$group, this.$name, null);
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
