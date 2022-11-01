package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$selected$1", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$selected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Set<String>>, Object> {
    public final /* synthetic */ ServiceStore $service;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$selected$1(ServiceStore serviceStore, Continuation<? super AccessControlActivity$main$selected$1> continuation) {
        super(2, continuation);
        this.$service = serviceStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlActivity$main$selected$1(this.$service, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Set<String>> continuation) {
        Continuation<? super Set<String>> continuation2 = continuation;
        ServiceStore serviceStore = this.$service;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return ArraysKt___ArraysKt.toMutableSet(serviceStore.getAccessControlPackages());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ArraysKt___ArraysKt.toMutableSet(this.$service.getAccessControlPackages());
    }
}
