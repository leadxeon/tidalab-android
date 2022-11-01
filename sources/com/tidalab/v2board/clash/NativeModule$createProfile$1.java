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
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$createProfile$1", f = "NativeModule.kt", l = {105}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$createProfile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $name;
    public final /* synthetic */ String $source;
    public final /* synthetic */ Callback $successCallback;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$createProfile$1$1", f = "NativeModule.kt", l = {107}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$createProfile$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $name;
        public final /* synthetic */ String $source;
        public final /* synthetic */ Callback $successCallback;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Callback callback, String str, String str2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$successCallback = callback;
            this.$name = str;
            this.$source = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$successCallback, this.$name, this.$source, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$successCallback, this.$name, this.$source, continuation);
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
                Profile.Type type = Profile.Type.Url;
                String str = this.$name;
                String str2 = this.$source;
                this.L$0 = objArr3;
                this.L$1 = callback;
                this.L$2 = objArr3;
                this.label = 1;
                obj = ((IProfileManager) this.L$0).create(type, str, str2, this);
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
            objArr2[0] = ((UUID) obj).toString();
            callback.invoke(objArr);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$createProfile$1(Callback callback, String str, String str2, Continuation<? super NativeModule$createProfile$1> continuation) {
        super(2, continuation);
        this.$successCallback = callback;
        this.$name = str;
        this.$source = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$createProfile$1(this.$successCallback, this.$name, this.$source, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$createProfile$1(this.$successCallback, this.$name, this.$source, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r7 = new AnonymousClass1(this.$successCallback, this.$name, this.$source, null);
            this.label = 1;
            if (InputKt.withProfile$default(null, r7, this, 1) == coroutineSingletons) {
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
