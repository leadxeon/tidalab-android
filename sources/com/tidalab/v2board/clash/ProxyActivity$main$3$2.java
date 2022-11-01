package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.design.ProxyDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProxyState;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.sync.Semaphore;
/* compiled from: ProxyActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2", f = "ProxyActivity.kt", l = {100, 106, 118, 120}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyActivity$main$3$2 extends SuspendLambda implements Function2<ProxyDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProxyDesign $design;
    public final /* synthetic */ List<String> $names;
    public final /* synthetic */ Semaphore $reloadLock;
    public final /* synthetic */ List<ProxyState> $states;
    public final /* synthetic */ Map<String, ProxyState> $unorderedStates;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ProxyActivity this$0;

    /* compiled from: ProxyActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$2", f = "ProxyActivity.kt", l = {138, 82, 90}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProxyActivity$main$3$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProxyDesign $design;
        public final /* synthetic */ ProxyDesign.Request $it;
        public final /* synthetic */ List<String> $names;
        public final /* synthetic */ Semaphore $reloadLock;
        public final /* synthetic */ List<ProxyState> $states;
        public final /* synthetic */ Map<String, ProxyState> $unorderedStates;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public final /* synthetic */ ProxyActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Semaphore semaphore, List<ProxyState> list, ProxyDesign.Request request, ProxyDesign proxyDesign, Map<String, ProxyState> map, List<String> list2, ProxyActivity proxyActivity, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$reloadLock = semaphore;
            this.$states = list;
            this.$it = request;
            this.$design = proxyDesign;
            this.$unorderedStates = map;
            this.$names = list2;
            this.this$0 = proxyActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$reloadLock, this.$states, this.$it, this.$design, this.$unorderedStates, this.$names, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0099  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00a8 A[RETURN] */
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
                r2 = 1
                r3 = 3
                r4 = 2
                r5 = 0
                if (r1 == 0) goto L_0x003c
                if (r1 == r2) goto L_0x0028
                if (r1 == r4) goto L_0x001d
                if (r1 != r3) goto L_0x0015
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
                goto L_0x00a9
            L_0x0015:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L_0x001d:
                java.lang.Object r1 = r13.L$0
                kotlinx.coroutines.sync.Semaphore r1 = (kotlinx.coroutines.sync.Semaphore) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)     // Catch: all -> 0x0025
                goto L_0x006f
            L_0x0025:
                r14 = move-exception
                goto L_0x00ac
            L_0x0028:
                java.lang.Object r1 = r13.L$3
                com.tidalab.v2board.clash.ProxyActivity r1 = (com.tidalab.v2board.clash.ProxyActivity) r1
                java.lang.Object r6 = r13.L$2
                com.tidalab.v2board.clash.design.ProxyDesign$Request r6 = (com.tidalab.v2board.clash.design.ProxyDesign.Request) r6
                java.lang.Object r7 = r13.L$1
                java.util.List r7 = (java.util.List) r7
                java.lang.Object r8 = r13.L$0
                kotlinx.coroutines.sync.Semaphore r8 = (kotlinx.coroutines.sync.Semaphore) r8
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
                goto L_0x0058
            L_0x003c:
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
                kotlinx.coroutines.sync.Semaphore r8 = r13.$reloadLock
                java.util.List<java.lang.String> r7 = r13.$names
                com.tidalab.v2board.clash.design.ProxyDesign$Request r6 = r13.$it
                com.tidalab.v2board.clash.ProxyActivity r1 = r13.this$0
                r13.L$0 = r8
                r13.L$1 = r7
                r13.L$2 = r6
                r13.L$3 = r1
                r13.label = r2
                java.lang.Object r14 = r8.acquire(r13)
                if (r14 != r0) goto L_0x0058
                return r0
            L_0x0058:
                com.tidalab.v2board.clash.ProxyActivity$main$3$2$2$group$1$1 r14 = new com.tidalab.v2board.clash.ProxyActivity$main$3$2$2$group$1$1     // Catch: all -> 0x00ae
                r14.<init>(r7, r6, r1, r5)     // Catch: all -> 0x00ae
                r13.L$0 = r8     // Catch: all -> 0x00ae
                r13.L$1 = r5     // Catch: all -> 0x00ae
                r13.L$2 = r5     // Catch: all -> 0x00ae
                r13.L$3 = r5     // Catch: all -> 0x00ae
                r13.label = r4     // Catch: all -> 0x00ae
                java.lang.Object r14 = com.tidalab.v2board.clash.design.dialog.InputKt.withClash$default(r5, r14, r13, r2)     // Catch: all -> 0x00ae
                if (r14 != r0) goto L_0x006e
                return r0
            L_0x006e:
                r1 = r8
            L_0x006f:
                com.tidalab.v2board.clash.core.model.ProxyGroup r14 = (com.tidalab.v2board.clash.core.model.ProxyGroup) r14     // Catch: all -> 0x0025
                r1.release()
                java.util.List<com.tidalab.v2board.clash.design.model.ProxyState> r1 = r13.$states
                com.tidalab.v2board.clash.design.ProxyDesign$Request r4 = r13.$it
                com.tidalab.v2board.clash.design.ProxyDesign$Request$Reload r4 = (com.tidalab.v2board.clash.design.ProxyDesign.Request.Reload) r4
                int r4 = r4.index
                java.lang.Object r1 = r1.get(r4)
                r10 = r1
                com.tidalab.v2board.clash.design.model.ProxyState r10 = (com.tidalab.v2board.clash.design.model.ProxyState) r10
                java.lang.String r1 = r14.now
                r10.now = r1
                com.tidalab.v2board.clash.design.ProxyDesign r6 = r13.$design
                com.tidalab.v2board.clash.design.ProxyDesign$Request r1 = r13.$it
                com.tidalab.v2board.clash.design.ProxyDesign$Request$Reload r1 = (com.tidalab.v2board.clash.design.ProxyDesign.Request.Reload) r1
                int r7 = r1.index
                java.util.List<com.tidalab.v2board.clash.core.model.Proxy> r8 = r14.proxies
                com.tidalab.v2board.clash.core.model.Proxy$Type r14 = r14.type
                com.tidalab.v2board.clash.core.model.Proxy$Type r1 = com.tidalab.v2board.clash.core.model.Proxy.Type.Selector
                if (r14 != r1) goto L_0x0099
                r9 = 1
                goto L_0x009b
            L_0x0099:
                r2 = 0
                r9 = 0
            L_0x009b:
                java.util.Map<java.lang.String, com.tidalab.v2board.clash.design.model.ProxyState> r11 = r13.$unorderedStates
                r13.L$0 = r5
                r13.label = r3
                r12 = r13
                java.lang.Object r14 = r6.updateGroup(r7, r8, r9, r10, r11, r12)
                if (r14 != r0) goto L_0x00a9
                return r0
            L_0x00a9:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            L_0x00ac:
                r8 = r1
                goto L_0x00af
            L_0x00ae:
                r14 = move-exception
            L_0x00af:
                r8.release()
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.ProxyActivity$main$3$2.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: ProxyActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$3", f = "ProxyActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProxyActivity$main$3$2$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProxyDesign.Request $it;
        public final /* synthetic */ List<String> $names;
        public final /* synthetic */ List<ProxyState> $states;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(List<String> list, ProxyDesign.Request request, List<ProxyState> list2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$names = list;
            this.$it = request;
            this.$states = list2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 r0 = new AnonymousClass3(this.$names, this.$it, this.$states, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            List<String> list = this.$names;
            ProxyDesign.Request request = this.$it;
            List<ProxyState> list2 = this.$states;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            ProxyDesign.Request.Select select = (ProxyDesign.Request.Select) request;
            iClashManager2.patchSelector(list.get(select.index), select.name);
            list2.get(select.index).now = select.name;
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            ((IClashManager) this.L$0).patchSelector(this.$names.get(((ProxyDesign.Request.Select) this.$it).index), ((ProxyDesign.Request.Select) this.$it).name);
            this.$states.get(((ProxyDesign.Request.Select) this.$it).index).now = ((ProxyDesign.Request.Select) this.$it).name;
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ProxyActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$4", f = "ProxyActivity.kt", l = {110, 114}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProxyActivity$main$3$2$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProxyDesign $design;
        public final /* synthetic */ ProxyDesign.Request $it;
        public final /* synthetic */ List<String> $names;
        public int label;

        /* compiled from: ProxyActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$4$1", f = "ProxyActivity.kt", l = {111}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.ProxyActivity$main$3$2$4$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ProxyDesign.Request $it;
            public final /* synthetic */ List<String> $names;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(List<String> list, ProxyDesign.Request request, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$names = list;
                this.$it = request;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 r0 = new AnonymousClass1(this.$names, this.$it, continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
                AnonymousClass1 r0 = new AnonymousClass1(this.$names, this.$it, continuation);
                r0.L$0 = iClashManager;
                return r0.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    this.label = 1;
                    if (((IClashManager) this.L$0).healthCheck(this.$names.get(((ProxyDesign.Request.UrlTest) this.$it).index), this) == coroutineSingletons) {
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
        public AnonymousClass4(ProxyDesign proxyDesign, ProxyDesign.Request request, List<String> list, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$design = proxyDesign;
            this.$it = request;
            this.$names = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.$design, this.$it, this.$names, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass4(this.$design, this.$it, this.$names, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                AnonymousClass1 r7 = new AnonymousClass1(this.$names, this.$it, null);
                this.label = 1;
                if (InputKt.withClash$default(null, r7, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else if (i == 2) {
                InputKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SendChannel sendChannel = this.$design.requests;
            ProxyDesign.Request.Reload reload = new ProxyDesign.Request.Reload(((ProxyDesign.Request.UrlTest) this.$it).index);
            this.label = 2;
            if (sendChannel.send(reload, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ProxyActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$5", f = "ProxyActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProxyActivity$main$3$2$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass5 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProxyDesign.Request $it;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(ProxyDesign.Request request, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$it = request;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass5 r0 = new AnonymousClass5(this.$it, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            ProxyDesign.Request request = this.$it;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            Clash.OverrideSlot overrideSlot = Clash.OverrideSlot.Session;
            ConfigurationOverride queryOverride = iClashManager2.queryOverride(overrideSlot);
            queryOverride.mode = ((ProxyDesign.Request.PatchMode) request).mode;
            iClashManager2.patchOverride(overrideSlot, queryOverride);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            IClashManager iClashManager = (IClashManager) this.L$0;
            Clash.OverrideSlot overrideSlot = Clash.OverrideSlot.Session;
            ConfigurationOverride queryOverride = iClashManager.queryOverride(overrideSlot);
            queryOverride.mode = ((ProxyDesign.Request.PatchMode) this.$it).mode;
            iClashManager.patchOverride(overrideSlot, queryOverride);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyActivity$main$3$2(ProxyActivity proxyActivity, List<String> list, ProxyDesign proxyDesign, Semaphore semaphore, List<ProxyState> list2, Map<String, ProxyState> map, Continuation<? super ProxyActivity$main$3$2> continuation) {
        super(2, continuation);
        this.this$0 = proxyActivity;
        this.$names = list;
        this.$design = proxyDesign;
        this.$reloadLock = semaphore;
        this.$states = list2;
        this.$unorderedStates = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProxyActivity$main$3$2 proxyActivity$main$3$2 = new ProxyActivity$main$3$2(this.this$0, this.$names, this.$design, this.$reloadLock, this.$states, this.$unorderedStates, continuation);
        proxyActivity$main$3$2.L$0 = obj;
        return proxyActivity$main$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ProxyDesign.Request request, Continuation<? super Unit> continuation) {
        return ((ProxyActivity$main$3$2) create(request, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0129 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.ProxyActivity$main$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
