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
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileProcessor$update$2", f = "ProfileProcessor.kt", l = {198, 206, 100, 122, 217, 125}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileProcessor$update$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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
    public ProfileProcessor$update$2(IFetchObserver iFetchObserver, Context context, UUID uuid, Continuation<? super ProfileProcessor$update$2> continuation) {
        super(2, continuation);
        this.$callback = iFetchObserver;
        this.$context = context;
        this.$uuid = uuid;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileProcessor$update$2(this.$callback, this.$context, this.$uuid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileProcessor$update$2(this.$callback, this.$context, this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x01ea: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:62:0x01ea */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fb A[Catch: all -> 0x006d, TRY_LEAVE, TryCatch #4 {all -> 0x01e9, blocks: (B:10:0x003a, B:14:0x004f, B:25:0x00c4, B:39:0x0152, B:16:0x0068, B:29:0x00df, B:32:0x00f7, B:34:0x00fb, B:59:0x01c9, B:60:0x01e4), top: B:65:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0165 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x017c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0188 A[Catch: all -> 0x0027, TryCatch #2 {all -> 0x0027, blocks: (B:7:0x0022, B:46:0x0180, B:48:0x0188, B:49:0x01b4), top: B:66:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01c9 A[Catch: all -> 0x006d, TRY_ENTER, TryCatch #4 {all -> 0x01e9, blocks: (B:10:0x003a, B:14:0x004f, B:25:0x00c4, B:39:0x0152, B:16:0x0068, B:29:0x00df, B:32:0x00f7, B:34:0x00fb, B:59:0x01c9, B:60:0x01e4), top: B:65:0x0007 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileProcessor$update$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
