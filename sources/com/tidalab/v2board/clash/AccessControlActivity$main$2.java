package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$2", f = "AccessControlActivity.kt", l = {28}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ Set<String> $selected;
    public final /* synthetic */ ServiceStore $service;
    public int label;

    /* compiled from: AccessControlActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$2$1", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$main$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Set<String> $selected;
        public final /* synthetic */ ServiceStore $service;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ServiceStore serviceStore, Set<String> set, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$service = serviceStore;
            this.$selected = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$service, this.$selected, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            ServiceStore serviceStore = this.$service;
            Set<String> set = this.$selected;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            serviceStore.accessControlPackages$delegate.setValue(serviceStore, ServiceStore.$$delegatedProperties[3], set);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            ServiceStore serviceStore = this.$service;
            serviceStore.accessControlPackages$delegate.setValue(serviceStore, ServiceStore.$$delegatedProperties[3], this.$selected);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$2(ServiceStore serviceStore, Set<String> set, Continuation<? super AccessControlActivity$main$2> continuation) {
        super(1, continuation);
        this.$service = serviceStore;
        this.$selected = set;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        return new AccessControlActivity$main$2(this.$service, this.$selected, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass1 r1 = new AnonymousClass1(this.$service, this.$selected, null);
            this.label = 1;
            if (InputKt.withContext(coroutineDispatcher, r1, this) == coroutineSingletons) {
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
