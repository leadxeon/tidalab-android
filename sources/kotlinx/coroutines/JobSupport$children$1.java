package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
/* compiled from: JobSupport.kt */
@DebugMetadata(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", l = {952, 954}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ChildJob>, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public final /* synthetic */ JobSupport this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobSupport$children$1(JobSupport jobSupport, Continuation<? super JobSupport$children$1> continuation) {
        super(2, continuation);
        this.this$0 = jobSupport;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.L$0 = obj;
        return jobSupport$children$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(SequenceScope<? super ChildJob> sequenceScope, Continuation<? super Unit> continuation) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.L$0 = sequenceScope;
        return jobSupport$children$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0067 -> B:27:0x007d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x007a -> B:27:0x007d). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0029
            if (r1 == r3) goto L_0x0025
            if (r1 != r2) goto L_0x001d
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            java.lang.Object r3 = r7.L$1
            kotlinx.coroutines.internal.LockFreeLinkedListHead r3 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r3
            java.lang.Object r4 = r7.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            r8 = r7
            goto L_0x007d
        L_0x001d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0025:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x0082
        L_0x0029:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlinx.coroutines.JobSupport r1 = r7.this$0
            java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
            boolean r4 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r4 == 0) goto L_0x0047
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            kotlinx.coroutines.ChildJob r1 = r1.childJob
            r7.label = r3
            java.lang.Object r8 = r8.yield(r1, r7)
            if (r8 != r0) goto L_0x0082
            return r0
        L_0x0047:
            boolean r3 = r1 instanceof kotlinx.coroutines.Incomplete
            if (r3 == 0) goto L_0x0082
            kotlinx.coroutines.Incomplete r1 = (kotlinx.coroutines.Incomplete) r1
            kotlinx.coroutines.NodeList r1 = r1.getList()
            if (r1 != 0) goto L_0x0054
            goto L_0x0082
        L_0x0054:
            java.lang.Object r3 = r1.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r4 = r8
            r8 = r7
            r6 = r3
            r3 = r1
            r1 = r6
        L_0x005f:
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r3)
            if (r5 != 0) goto L_0x0082
            boolean r5 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r5 == 0) goto L_0x007d
            r5 = r1
            kotlinx.coroutines.ChildHandleNode r5 = (kotlinx.coroutines.ChildHandleNode) r5
            kotlinx.coroutines.ChildJob r5 = r5.childJob
            r8.L$0 = r4
            r8.L$1 = r3
            r8.L$2 = r1
            r8.label = r2
            java.lang.Object r5 = r4.yield(r5, r8)
            if (r5 != r0) goto L_0x007d
            return r0
        L_0x007d:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r1.getNextNode()
            goto L_0x005f
        L_0x0082:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
