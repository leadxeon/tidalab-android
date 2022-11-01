package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.service.ProfileReceiver;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ProfileReceiver.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileReceiver$Companion", f = "ProfileReceiver.kt", l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "reset")
/* loaded from: classes.dex */
public final class ProfileReceiver$Companion$reset$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfileReceiver.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileReceiver$Companion$reset$1(ProfileReceiver.Companion companion, Continuation<? super ProfileReceiver$Companion$reset$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ProfileReceiver.Companion.access$reset(this.this$0, this);
    }
}
