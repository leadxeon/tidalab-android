package com.tidalab.v2board.clash.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Remote.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.util.RemoteKt", f = "Remote.kt", l = {35, 39}, m = "withProfile")
/* loaded from: classes.dex */
public final class RemoteKt$withProfile$1<T> extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;

    public RemoteKt$withProfile$1(Continuation<? super RemoteKt$withProfile$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.withProfile(null, null, this);
    }
}
