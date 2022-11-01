package com.tidalab.v2board.clash.service;

import android.content.Context;
import com.tidalab.v2board.clash.service.remote.IFetchObserver;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileProcessor.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileProcessor$apply$2", f = "ProfileProcessor.kt", l = {198, 206, 33, 58, 217, 61, 67, 79, 81, 84}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileProcessor$apply$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ IFetchObserver $callback;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ UUID $uuid;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileProcessor$apply$2(IFetchObserver iFetchObserver, Context context, UUID uuid, Continuation<? super ProfileProcessor$apply$2> continuation) {
        super(2, continuation);
        this.$callback = iFetchObserver;
        this.$context = context;
        this.$uuid = uuid;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileProcessor$apply$2(this.$callback, this.$context, this.$uuid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileProcessor$apply$2(this.$callback, this.$context, this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0318: MOVE  (r4 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:114:0x0318 */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02f4 A[Catch: all -> 0x00d2, TRY_ENTER, TryCatch #9 {all -> 0x00ed, blocks: (B:32:0x00e9, B:50:0x018d, B:54:0x0198, B:108:0x02ef, B:29:0x00cb, B:44:0x0148, B:47:0x0160, B:49:0x0164, B:112:0x02f4, B:113:0x030f), top: B:121:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0140 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0164 A[Catch: all -> 0x00d2, TRY_LEAVE, TryCatch #9 {all -> 0x00ed, blocks: (B:32:0x00e9, B:50:0x018d, B:54:0x0198, B:108:0x02ef, B:29:0x00cb, B:44:0x0148, B:47:0x0160, B:49:0x0164, B:112:0x02f4, B:113:0x030f), top: B:121:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01db A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f9 A[Catch: all -> 0x0089, TRY_LEAVE, TryCatch #6 {all -> 0x0089, blocks: (B:19:0x0082, B:62:0x01dc, B:65:0x01f3, B:67:0x01f9), top: B:121:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0257 A[Catch: all -> 0x02d5, TryCatch #5 {all -> 0x02d5, blocks: (B:75:0x024d, B:77:0x0257, B:78:0x025c, B:79:0x0260, B:81:0x026a), top: B:124:0x024d }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x025c A[Catch: all -> 0x02d5, TryCatch #5 {all -> 0x02d5, blocks: (B:75:0x024d, B:77:0x0257, B:78:0x025c, B:79:0x0260, B:81:0x026a), top: B:124:0x024d }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x026a A[Catch: all -> 0x02d5, TRY_LEAVE, TryCatch #5 {all -> 0x02d5, blocks: (B:75:0x024d, B:77:0x0257, B:78:0x025c, B:79:0x0260, B:81:0x026a), top: B:124:0x024d }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0285 A[Catch: all -> 0x006f, TryCatch #10 {all -> 0x006f, blocks: (B:10:0x003d, B:15:0x0067, B:71:0x0238, B:83:0x0270, B:86:0x0285, B:89:0x02a0), top: B:121:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02b8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02d9  */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileProcessor$apply$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
