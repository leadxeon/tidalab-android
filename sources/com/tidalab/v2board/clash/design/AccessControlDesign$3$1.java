package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.NonCancellable;
/* compiled from: AccessControlDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.AccessControlDesign$3$1", f = "AccessControlDesign.kt", l = {73, 75, 75}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlDesign$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ AccessControlDesign this$0;

    /* compiled from: AccessControlDesign.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.AccessControlDesign$3$1$1", f = "AccessControlDesign.kt", l = {76}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.AccessControlDesign$3$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ AccessControlDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AccessControlDesign accessControlDesign, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = accessControlDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                AccessControlDesign accessControlDesign = this.this$0;
                this.label = 1;
                if (accessControlDesign.rebindAll(this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlDesign$3$1(AccessControlDesign accessControlDesign, Continuation<? super AccessControlDesign$3$1> continuation) {
        super(2, continuation);
        this.this$0 = accessControlDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlDesign$3$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new AccessControlDesign$3$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                AccessControlDesign accessControlDesign = this.this$0;
                this.label = 1;
                Objects.requireNonNull(accessControlDesign);
                Object coroutineScope = InputKt.coroutineScope(new AccessControlDesign$requestSearch$2(accessControlDesign, null), this);
                if (coroutineScope != obj2) {
                    coroutineScope = Unit.INSTANCE;
                }
                if (coroutineScope == obj2) {
                    return obj2;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else if (i == 2) {
                InputKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                Throwable th = (Throwable) this.L$0;
                InputKt.throwOnFailure(obj);
                throw th;
            }
            NonCancellable nonCancellable = NonCancellable.INSTANCE;
            AnonymousClass1 r1 = new AnonymousClass1(this.this$0, null);
            this.label = 2;
            if (InputKt.withContext(nonCancellable, r1, this) == obj2) {
                return obj2;
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            NonCancellable nonCancellable2 = NonCancellable.INSTANCE;
            AnonymousClass1 r3 = new AnonymousClass1(this.this$0, null);
            this.L$0 = th2;
            this.label = 3;
            if (InputKt.withContext(nonCancellable2, r3, this) == obj2) {
                return obj2;
            }
            throw th2;
        }
    }
}
