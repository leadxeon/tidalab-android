package com.tidalab.v2board.clash.service.remote;

import com.reactnativecommunity.webview.RNCWebViewManager;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerProxy", f = "IProfileManager.kt", l = {RNCWebViewManager.SHOULD_OVERRIDE_URL_LOADING_TIMEOUT}, m = "clone")
/* loaded from: classes.dex */
public final class IProfileManagerProxy$clone$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ IProfileManagerProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerProxy$clone$1(IProfileManagerProxy iProfileManagerProxy, Continuation<? super IProfileManagerProxy$clone$1> continuation) {
        super(continuation);
        this.this$0 = iProfileManagerProxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.clone(null, this);
    }
}
