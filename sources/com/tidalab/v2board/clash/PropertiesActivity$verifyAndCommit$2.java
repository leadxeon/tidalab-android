package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IFetchObserver;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2", f = "PropertiesActivity.kt", l = {92}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$verifyAndCommit$2 extends SuspendLambda implements Function2<Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PropertiesDesign $this_verifyAndCommit;
    public /* synthetic */ Object L$0;
    public int label;

    /* compiled from: PropertiesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2$1", f = "PropertiesActivity.kt", l = {93, 95}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ PropertiesDesign $this_verifyAndCommit;
        public final /* synthetic */ Function2<FetchStatus, Continuation<? super Unit>, Object> $updateStatus;
        public /* synthetic */ Object L$0;
        public int label;

        /* compiled from: PropertiesActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2$1$1", f = "PropertiesActivity.kt", l = {96}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ IProfileManager $$this$withProfile;
            public final /* synthetic */ PropertiesDesign $this_verifyAndCommit;
            public final /* synthetic */ Function2<FetchStatus, Continuation<? super Unit>, Object> $updateStatus;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00131(IProfileManager iProfileManager, PropertiesDesign propertiesDesign, Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C00131> continuation) {
                super(2, continuation);
                this.$$this$withProfile = iProfileManager;
                this.$this_verifyAndCommit = propertiesDesign;
                this.$updateStatus = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00131 r0 = new C00131(this.$$this$withProfile, this.$this_verifyAndCommit, this.$updateStatus, continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                C00131 r0 = new C00131(this.$$this$withProfile, this.$this_verifyAndCommit, this.$updateStatus, continuation);
                r0.L$0 = coroutineScope;
                return r0.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    IProfileManager iProfileManager = this.$$this$withProfile;
                    UUID uuid = this.$this_verifyAndCommit.binding.mProfile.uuid;
                    final Function2<FetchStatus, Continuation<? super Unit>, Object> function2 = this.$updateStatus;
                    IFetchObserver iFetchObserver = new IFetchObserver() { // from class: com.tidalab.v2board.clash.-$$Lambda$PropertiesActivity$verifyAndCommit$2$1$1$-WG3m0BihICPlyNJ7optspaJ1pM
                        @Override // com.tidalab.v2board.clash.service.remote.IFetchObserver
                        public final void updateStatus(FetchStatus fetchStatus) {
                            InputKt.launch$default(CoroutineScope.this, null, null, new PropertiesActivity$verifyAndCommit$2$1$1$1$1(function2, fetchStatus, null), 3, null);
                        }
                    };
                    this.label = 1;
                    if (iProfileManager.commit(uuid, iFetchObserver, this) == coroutineSingletons) {
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
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(PropertiesDesign propertiesDesign, Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_verifyAndCommit = propertiesDesign;
            this.$updateStatus = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$this_verifyAndCommit, this.$updateStatus, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$this_verifyAndCommit, this.$updateStatus, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IProfileManager iProfileManager;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                iProfileManager = (IProfileManager) this.L$0;
                Profile profile = this.$this_verifyAndCommit.binding.mProfile;
                UUID uuid = profile.uuid;
                String str = profile.name;
                String str2 = profile.source;
                long j = profile.interval;
                this.L$0 = iProfileManager;
                this.label = 1;
                if (iProfileManager.patch(uuid, str, str2, j, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                iProfileManager = (IProfileManager) this.L$0;
                InputKt.throwOnFailure(obj);
            } else if (i == 2) {
                InputKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            C00131 r12 = new C00131(iProfileManager, this.$this_verifyAndCommit, this.$updateStatus, null);
            this.L$0 = null;
            this.label = 2;
            if (InputKt.coroutineScope(r12, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesActivity$verifyAndCommit$2(PropertiesDesign propertiesDesign, Continuation<? super PropertiesActivity$verifyAndCommit$2> continuation) {
        super(2, continuation);
        this.$this_verifyAndCommit = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PropertiesActivity$verifyAndCommit$2 propertiesActivity$verifyAndCommit$2 = new PropertiesActivity$verifyAndCommit$2(this.$this_verifyAndCommit, continuation);
        propertiesActivity$verifyAndCommit$2.L$0 = obj;
        return propertiesActivity$verifyAndCommit$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        PropertiesActivity$verifyAndCommit$2 propertiesActivity$verifyAndCommit$2 = new PropertiesActivity$verifyAndCommit$2(this.$this_verifyAndCommit, continuation);
        propertiesActivity$verifyAndCommit$2.L$0 = function2;
        return propertiesActivity$verifyAndCommit$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r1 = new AnonymousClass1(this.$this_verifyAndCommit, (Function2) this.L$0, null);
            this.label = 1;
            if (InputKt.withProfile$default(null, r1, this, 1) == coroutineSingletons) {
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
