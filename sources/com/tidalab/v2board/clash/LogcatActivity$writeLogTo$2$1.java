package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.dialog.ModelProgressBarConfigure;
import com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope;
import com.tidalab.v2board.clash.design.model.LogFile;
import com.tidalab.v2board.clash.log.LogcatFilter;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1", f = "LogcatActivity.kt", l = {159}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$writeLogTo$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LogFile $file;
    public final /* synthetic */ LogcatFilter $it;
    public final /* synthetic */ List<LogMessage> $messages;
    public int label;
    public final /* synthetic */ LogcatActivity this$0;

    /* compiled from: LogcatActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1", f = "LogcatActivity.kt", l = {160, 165}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ModelProgressBarScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ LogFile $file;
        public final /* synthetic */ LogcatFilter $it;
        public final /* synthetic */ List<LogMessage> $messages;
        public /* synthetic */ Object L$0;
        public int label;

        /* compiled from: LogcatActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$1", f = "LogcatActivity.kt", l = {}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00111 extends SuspendLambda implements Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<LogMessage> $messages;
            public /* synthetic */ Object L$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00111(List<LogMessage> list, Continuation<? super C00111> continuation) {
                super(2, continuation);
                this.$messages = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00111 r0 = new C00111(this.$messages, continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(ModelProgressBarConfigure modelProgressBarConfigure, Continuation<? super Unit> continuation) {
                ModelProgressBarConfigure modelProgressBarConfigure2 = modelProgressBarConfigure;
                Continuation<? super Unit> continuation2 = continuation;
                List<LogMessage> list = this.$messages;
                if (continuation2 != null) {
                    continuation2.getContext();
                }
                Unit unit = Unit.INSTANCE;
                InputKt.throwOnFailure(unit);
                modelProgressBarConfigure2.setIndeterminate(true);
                modelProgressBarConfigure2.setMax(list.size());
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                InputKt.throwOnFailure(obj);
                ModelProgressBarConfigure modelProgressBarConfigure = (ModelProgressBarConfigure) this.L$0;
                modelProgressBarConfigure.setIndeterminate(true);
                modelProgressBarConfigure.setMax(this.$messages.size());
                return Unit.INSTANCE;
            }
        }

        /* compiled from: LogcatActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$2", f = "LogcatActivity.kt", l = {169}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$2  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ModelProgressBarScope $$this$withModelProgressBar;
            public final /* synthetic */ LogFile $file;
            public final /* synthetic */ LogcatFilter $it;
            public final /* synthetic */ List<LogMessage> $messages;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(LogcatFilter logcatFilter, LogFile logFile, List<LogMessage> list, ModelProgressBarScope modelProgressBarScope, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$it = logcatFilter;
                this.$file = logFile;
                this.$messages = list;
                this.$$this$withModelProgressBar = modelProgressBarScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$it, this.$file, this.$messages, this.$$this$withModelProgressBar, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new AnonymousClass2(this.$it, this.$file, this.$messages, this.$$this$withModelProgressBar, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x005e  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x00bc  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x008a -> B:17:0x008b). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                /*
                    r13 = this;
                    kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r1 = r13.label
                    r2 = 10
                    r3 = 0
                    r4 = 1
                    if (r1 == 0) goto L_0x002c
                    if (r1 != r4) goto L_0x0024
                    int r1 = r13.I$0
                    java.lang.Object r5 = r13.L$3
                    com.tidalab.v2board.clash.core.model.LogMessage r5 = (com.tidalab.v2board.clash.core.model.LogMessage) r5
                    java.lang.Object r6 = r13.L$2
                    java.util.Iterator r6 = (java.util.Iterator) r6
                    java.lang.Object r7 = r13.L$1
                    com.tidalab.v2board.clash.log.LogcatFilter r7 = (com.tidalab.v2board.clash.log.LogcatFilter) r7
                    java.lang.Object r8 = r13.L$0
                    com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope r8 = (com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope) r8
                    com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
                    r14 = r1
                    r1 = r13
                    goto L_0x008b
                L_0x0024:
                    java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r14.<init>(r0)
                    throw r14
                L_0x002c:
                    com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
                    com.tidalab.v2board.clash.log.LogcatFilter r14 = r13.$it
                    com.tidalab.v2board.clash.design.model.LogFile r1 = r13.$file
                    java.util.Date r1 = r1.date
                    android.content.Context r5 = r14.context
                    r6 = 6
                    java.lang.String r1 = com.tidalab.v2board.clash.design.dialog.InputKt.format$default(r1, r5, r3, r3, r6)
                    java.lang.String r5 = "# Capture on "
                    java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r5, r1)
                    java.lang.Appendable r14 = r14.append(r1)
                    r14.append(r2)
                    java.util.List<com.tidalab.v2board.clash.core.model.LogMessage> r14 = r13.$messages
                    com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope r1 = r13.$$this$withModelProgressBar
                    com.tidalab.v2board.clash.log.LogcatFilter r5 = r13.$it
                    java.util.Iterator r14 = r14.iterator()
                    r6 = r14
                    r8 = r1
                    r7 = r5
                    r14 = 0
                    r1 = r13
                L_0x0058:
                    boolean r5 = r6.hasNext()
                    if (r5 == 0) goto L_0x00bc
                    java.lang.Object r5 = r6.next()
                    int r9 = r14 + 1
                    r10 = 0
                    if (r14 < 0) goto L_0x00b8
                    java.lang.Integer r11 = new java.lang.Integer
                    r11.<init>(r14)
                    com.tidalab.v2board.clash.core.model.LogMessage r5 = (com.tidalab.v2board.clash.core.model.LogMessage) r5
                    int r14 = r11.intValue()
                    com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$2$1$1 r11 = new com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$2$1$1
                    r11.<init>(r14, r10)
                    r1.L$0 = r8
                    r1.L$1 = r7
                    r1.L$2 = r6
                    r1.L$3 = r5
                    r1.I$0 = r9
                    r1.label = r4
                    java.lang.Object r14 = r8.configure(r11, r1)
                    if (r14 != r0) goto L_0x008a
                    return r0
                L_0x008a:
                    r14 = r9
                L_0x008b:
                    java.util.Date r9 = r5.time
                    android.content.Context r10 = r7.context
                    r11 = 4
                    java.lang.String r9 = com.tidalab.v2board.clash.design.dialog.InputKt.format$default(r9, r10, r3, r3, r11)
                    com.tidalab.v2board.clash.core.model.LogMessage$Level r10 = r5.level
                    java.lang.String r10 = r10.name()
                    r11 = 3
                    java.lang.Object[] r12 = new java.lang.Object[r11]
                    r12[r3] = r9
                    r12[r4] = r10
                    r9 = 2
                    java.lang.String r5 = r5.message
                    r12[r9] = r5
                    java.lang.Object[] r5 = java.util.Arrays.copyOf(r12, r11)
                    java.lang.String r9 = "%12s %7s: %s"
                    java.lang.String r5 = java.lang.String.format(r9, r5)
                    java.lang.Appendable r5 = r7.append(r5)
                    r5.append(r2)
                    goto L_0x0058
                L_0x00b8:
                    kotlin.collections.ArraysKt___ArraysKt.throwIndexOverflow()
                    throw r10
                L_0x00bc:
                    kotlin.Unit r14 = kotlin.Unit.INSTANCE
                    return r14
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<LogMessage> list, LogcatFilter logcatFilter, LogFile logFile, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$messages = list;
            this.$it = logcatFilter;
            this.$file = logFile;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$messages, this.$it, this.$file, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(ModelProgressBarScope modelProgressBarScope, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$messages, this.$it, this.$file, continuation);
            r0.L$0 = modelProgressBarScope;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ModelProgressBarScope modelProgressBarScope;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                modelProgressBarScope = (ModelProgressBarScope) this.L$0;
                C00111 r11 = new C00111(this.$messages, null);
                this.L$0 = modelProgressBarScope;
                this.label = 1;
                if (modelProgressBarScope.configure(r11, this) == coroutineSingletons) {
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
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass2 r1 = new AnonymousClass2(this.$it, this.$file, this.$messages, modelProgressBarScope, null);
            this.L$0 = null;
            this.label = 2;
            if (InputKt.withContext(coroutineDispatcher, r1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$writeLogTo$2$1(LogcatActivity logcatActivity, List<LogMessage> list, LogcatFilter logcatFilter, LogFile logFile, Continuation<? super LogcatActivity$writeLogTo$2$1> continuation) {
        super(2, continuation);
        this.this$0 = logcatActivity;
        this.$messages = list;
        this.$it = logcatFilter;
        this.$file = logFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatActivity$writeLogTo$2$1(this.this$0, this.$messages, this.$it, this.$file, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new LogcatActivity$writeLogTo$2$1(this.this$0, this.$messages, this.$it, this.$file, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            LogcatActivity logcatActivity = this.this$0;
            AnonymousClass1 r1 = new AnonymousClass1(this.$messages, this.$it, this.$file, null);
            this.label = 1;
            if (InputKt.withModelProgressBar(logcatActivity, r1, this) == coroutineSingletons) {
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
