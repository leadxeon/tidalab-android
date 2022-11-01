package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.service.ProfileReceiver;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ProfileReceiver.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileReceiver$Companion", f = "ProfileReceiver.kt", l = {WebSocketProtocol.PAYLOAD_SHORT, 59, 60}, m = "rescheduleAll")
/* loaded from: classes.dex */
public final class ProfileReceiver$Companion$rescheduleAll$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfileReceiver.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileReceiver$Companion$rescheduleAll$1(ProfileReceiver.Companion companion, Continuation<? super ProfileReceiver$Companion$rescheduleAll$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.rescheduleAll(null, this);
    }
}
