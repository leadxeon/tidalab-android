package com.tidalab.v2board.clash;

import com.facebook.react.bridge.Callback;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$queryProfile$1", f = "NativeModule.kt", l = {71}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$queryProfile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Callback $successCallback;
    public final /* synthetic */ String $uuid;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$queryProfile$1$1", f = "NativeModule.kt", l = {72}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$queryProfile$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Callback $successCallback;
        public final /* synthetic */ String $uuid;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Callback callback, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$successCallback = callback;
            this.$uuid = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$successCallback, this.$uuid, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$successCallback, this.$uuid, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object[] objArr;
            Callback callback;
            Object[] objArr2;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                callback = this.$successCallback;
                Object[] objArr3 = new Object[1];
                UUID fromString = UUID.fromString(this.$uuid);
                this.L$0 = objArr3;
                this.L$1 = callback;
                this.L$2 = objArr3;
                this.label = 1;
                obj = ((IProfileManager) this.L$0).queryByUUID(fromString, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                objArr2 = objArr3;
                objArr = objArr2;
            } else if (i == 1) {
                objArr2 = (Object[]) this.L$2;
                callback = (Callback) this.L$1;
                objArr = (Object[]) this.L$0;
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Profile profile = (Profile) obj;
            objArr2[0] = profile == null ? null : Boolean.valueOf(profile.active);
            callback.invoke(objArr);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$queryProfile$1(Callback callback, String str, Continuation<? super NativeModule$queryProfile$1> continuation) {
        super(2, continuation);
        this.$successCallback = callback;
        this.$uuid = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$queryProfile$1(this.$successCallback, this.$uuid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$queryProfile$1(this.$successCallback, this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r6 = new AnonymousClass1(this.$successCallback, this.$uuid, null);
            this.label = 1;
            if (InputKt.withProfile$default(null, r6, this, 1) == coroutineSingletons) {
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
