package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: NewProfileActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NewProfileActivity", f = "NewProfileActivity.kt", l = {90}, m = "launchProperties")
/* loaded from: classes.dex */
public final class NewProfileActivity$launchProperties$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ NewProfileActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewProfileActivity$launchProperties$1(NewProfileActivity newProfileActivity, Continuation<? super NewProfileActivity$launchProperties$1> continuation) {
        super(continuation);
        this.this$0 = newProfileActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return NewProfileActivity.access$launchProperties(this.this$0, null, this);
    }
}
