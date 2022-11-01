package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.ProvidersDesign;
import com.tidalab.v2board.clash.design.ProvidersDesign$notifyChanged$2;
import com.tidalab.v2board.clash.design.ProvidersDesign$notifyUpdated$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.design.util.ToastKt$showExceptionToast$2;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: ProvidersActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$2", f = "ProvidersActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProvidersActivity$main$2$2 extends SuspendLambda implements Function2<ProvidersDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProvidersDesign $design;
    public /* synthetic */ Object L$0;
    public final /* synthetic */ ProvidersActivity this$0;

    /* compiled from: ProvidersActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$2$1", f = "ProvidersActivity.kt", l = {43, 47, 49, 57}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProvidersActivity$main$2$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProvidersDesign $design;
        public final /* synthetic */ ProvidersDesign.Request $it;
        public int label;
        public final /* synthetic */ ProvidersActivity this$0;

        /* compiled from: ProvidersActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$2$1$1", f = "ProvidersActivity.kt", l = {44}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.ProvidersActivity$main$2$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00141 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ProvidersDesign.Request $it;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00141(ProvidersDesign.Request request, Continuation<? super C00141> continuation) {
                super(2, continuation);
                this.$it = request;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00141 r0 = new C00141(this.$it, continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
                C00141 r0 = new C00141(this.$it, continuation);
                r0.L$0 = iClashManager;
                return r0.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    ProvidersDesign.Request request = this.$it;
                    Provider.Type type = ((ProvidersDesign.Request.Update) request).provider.type;
                    String str = ((ProvidersDesign.Request.Update) request).provider.name;
                    this.label = 1;
                    if (((IClashManager) this.L$0).updateProvider(type, str, this) == coroutineSingletons) {
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
        public AnonymousClass1(ProvidersDesign providersDesign, ProvidersDesign.Request request, ProvidersActivity providersActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$design = providersDesign;
            this.$it = request;
            this.this$0 = providersActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$design, this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$design, this.$it, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            try {
            } catch (Exception e) {
                ProvidersDesign providersDesign = this.$design;
                String string = this.this$0.getString(R.string.format_update_provider_failure, new Object[]{((ProvidersDesign.Request.Update) this.$it).provider.name, e.getMessage()});
                this.label = 3;
                Object showToast = providersDesign.showToast(string, ToastDuration.Long, new ToastKt$showExceptionToast$2(string), this);
                if (showToast != obj2) {
                    showToast = Unit.INSTANCE;
                }
                if (showToast == obj2) {
                    return obj2;
                }
            }
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                C00141 r12 = new C00141(this.$it, null);
                this.label = 1;
                if (InputKt.withClash$default(null, r12, this, 1) == obj2) {
                    return obj2;
                }
            } else if (i != 1) {
                if (i == 2) {
                    InputKt.throwOnFailure(obj);
                } else if (i == 3) {
                    InputKt.throwOnFailure(obj);
                    ProvidersDesign providersDesign2 = this.$design;
                    int i2 = ((ProvidersDesign.Request.Update) this.$it).index;
                    this.label = 4;
                    Objects.requireNonNull(providersDesign2);
                    Dispatchers dispatchers = Dispatchers.INSTANCE;
                    Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new ProvidersDesign$notifyUpdated$2(providersDesign2, i2, null), this);
                    if (withContext != obj2) {
                        withContext = Unit.INSTANCE;
                    }
                    if (withContext == obj2) {
                        return obj2;
                    }
                } else if (i == 4) {
                    InputKt.throwOnFailure(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            } else {
                InputKt.throwOnFailure(obj);
            }
            ProvidersDesign providersDesign3 = this.$design;
            int i3 = ((ProvidersDesign.Request.Update) this.$it).index;
            this.label = 2;
            Objects.requireNonNull(providersDesign3);
            Dispatchers dispatchers2 = Dispatchers.INSTANCE;
            Object withContext2 = InputKt.withContext(MainDispatcherLoader.dispatcher, new ProvidersDesign$notifyChanged$2(providersDesign3, i3, null), this);
            if (withContext2 != obj2) {
                withContext2 = Unit.INSTANCE;
            }
            if (withContext2 == obj2) {
                return obj2;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersActivity$main$2$2(ProvidersActivity providersActivity, ProvidersDesign providersDesign, Continuation<? super ProvidersActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = providersActivity;
        this.$design = providersDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProvidersActivity$main$2$2 providersActivity$main$2$2 = new ProvidersActivity$main$2$2(this.this$0, this.$design, continuation);
        providersActivity$main$2$2.L$0 = obj;
        return providersActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ProvidersDesign.Request request, Continuation<? super Unit> continuation) {
        ProvidersDesign.Request request2 = request;
        Continuation<? super Unit> continuation2 = continuation;
        ProvidersActivity providersActivity = this.this$0;
        ProvidersDesign providersDesign = this.$design;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        if (request2 instanceof ProvidersDesign.Request.Update) {
            InputKt.launch$default(providersActivity, null, null, new AnonymousClass1(providersDesign, request2, providersActivity, null), 3, null);
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        ProvidersDesign.Request request = (ProvidersDesign.Request) this.L$0;
        if (request instanceof ProvidersDesign.Request.Update) {
            ProvidersActivity providersActivity = this.this$0;
            InputKt.launch$default(providersActivity, null, null, new AnonymousClass1(this.$design, request, providersActivity, null), 3, null);
        }
        return Unit.INSTANCE;
    }
}
