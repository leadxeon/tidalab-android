package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.dialog.ModelProgressBarConfigure;
import com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: PropertiesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2", f = "PropertiesDesign.kt", l = {46, 51}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesDesign$withProcessing$2 extends SuspendLambda implements Function2<ModelProgressBarScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Function2<Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object>, Continuation<? super Unit>, Object> $executeTask;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ PropertiesDesign this$0;

    /* compiled from: PropertiesDesign.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$1", f = "PropertiesDesign.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object L$0;
        public final /* synthetic */ PropertiesDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PropertiesDesign propertiesDesign, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = propertiesDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(ModelProgressBarConfigure modelProgressBarConfigure, Continuation<? super Unit> continuation) {
            ModelProgressBarConfigure modelProgressBarConfigure2 = modelProgressBarConfigure;
            Continuation<? super Unit> continuation2 = continuation;
            PropertiesDesign propertiesDesign = this.this$0;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            modelProgressBarConfigure2.setIndeterminate(true);
            modelProgressBarConfigure2.setText(propertiesDesign.context.getString(R.string.initializing));
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            ModelProgressBarConfigure modelProgressBarConfigure = (ModelProgressBarConfigure) this.L$0;
            modelProgressBarConfigure.setIndeterminate(true);
            modelProgressBarConfigure.setText(this.this$0.context.getString(R.string.initializing));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: PropertiesDesign.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$2", f = "PropertiesDesign.kt", l = {52}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<FetchStatus, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ModelProgressBarScope $$this$withModelProgressBar;
        public /* synthetic */ Object L$0;
        public int label;
        public final /* synthetic */ PropertiesDesign this$0;

        /* compiled from: PropertiesDesign.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$2$1", f = "PropertiesDesign.kt", l = {}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2$2$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> {
            public final /* synthetic */ FetchStatus $it;
            public /* synthetic */ Object L$0;
            public final /* synthetic */ PropertiesDesign this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PropertiesDesign propertiesDesign, FetchStatus fetchStatus, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = propertiesDesign;
                this.$it = fetchStatus;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 r0 = new AnonymousClass1(this.this$0, this.$it, continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(ModelProgressBarConfigure modelProgressBarConfigure, Continuation<? super Unit> continuation) {
                AnonymousClass1 r0 = new AnonymousClass1(this.this$0, this.$it, continuation);
                r0.L$0 = modelProgressBarConfigure;
                return r0.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                InputKt.throwOnFailure(obj);
                ModelProgressBarConfigure modelProgressBarConfigure = (ModelProgressBarConfigure) this.L$0;
                PropertiesDesign propertiesDesign = this.this$0;
                FetchStatus fetchStatus = this.$it;
                Objects.requireNonNull(propertiesDesign);
                int ordinal = fetchStatus.action.ordinal();
                if (ordinal == 0) {
                    modelProgressBarConfigure.setText(propertiesDesign.context.getString(R.string.format_fetching_configuration, fetchStatus.args.get(0)));
                    modelProgressBarConfigure.setIndeterminate(true);
                } else if (ordinal == 1) {
                    modelProgressBarConfigure.setText(propertiesDesign.context.getString(R.string.format_fetching_provider, fetchStatus.args.get(0)));
                    modelProgressBarConfigure.setIndeterminate(false);
                    modelProgressBarConfigure.setMax(fetchStatus.max);
                    modelProgressBarConfigure.setProgress(fetchStatus.progress);
                } else if (ordinal == 2) {
                    modelProgressBarConfigure.setText(propertiesDesign.context.getString(R.string.verifying));
                    modelProgressBarConfigure.setIndeterminate(false);
                    modelProgressBarConfigure.setMax(fetchStatus.max);
                    modelProgressBarConfigure.setProgress(fetchStatus.progress);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ModelProgressBarScope modelProgressBarScope, PropertiesDesign propertiesDesign, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$$this$withModelProgressBar = modelProgressBarScope;
            this.this$0 = propertiesDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$$this$withModelProgressBar, this.this$0, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(FetchStatus fetchStatus, Continuation<? super Unit> continuation) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$$this$withModelProgressBar, this.this$0, continuation);
            r0.L$0 = fetchStatus;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                ModelProgressBarScope modelProgressBarScope = this.$$this$withModelProgressBar;
                AnonymousClass1 r3 = new AnonymousClass1(this.this$0, (FetchStatus) this.L$0, null);
                this.label = 1;
                if (modelProgressBarScope.configure(r3, this) == coroutineSingletons) {
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
    public PropertiesDesign$withProcessing$2(Function2<? super Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object>, ? super Continuation<? super Unit>, ? extends Object> function2, PropertiesDesign propertiesDesign, Continuation<? super PropertiesDesign$withProcessing$2> continuation) {
        super(2, continuation);
        this.$executeTask = function2;
        this.this$0 = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PropertiesDesign$withProcessing$2 propertiesDesign$withProcessing$2 = new PropertiesDesign$withProcessing$2(this.$executeTask, this.this$0, continuation);
        propertiesDesign$withProcessing$2.L$0 = obj;
        return propertiesDesign$withProcessing$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ModelProgressBarScope modelProgressBarScope, Continuation<? super Unit> continuation) {
        PropertiesDesign$withProcessing$2 propertiesDesign$withProcessing$2 = new PropertiesDesign$withProcessing$2(this.$executeTask, this.this$0, continuation);
        propertiesDesign$withProcessing$2.L$0 = modelProgressBarScope;
        return propertiesDesign$withProcessing$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ModelProgressBarScope modelProgressBarScope;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            modelProgressBarScope = (ModelProgressBarScope) this.L$0;
            AnonymousClass1 r7 = new AnonymousClass1(this.this$0, null);
            this.L$0 = modelProgressBarScope;
            this.label = 1;
            if (modelProgressBarScope.configure(r7, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            modelProgressBarScope = (ModelProgressBarScope) this.L$0;
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Function2<Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object>, Continuation<? super Unit>, Object> function2 = this.$executeTask;
        AnonymousClass2 r3 = new AnonymousClass2(modelProgressBarScope, this.this$0, null);
        this.L$0 = null;
        this.label = 2;
        if (function2.invoke(r3, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
