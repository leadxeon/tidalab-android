package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: FilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.FilesActivity", f = "FilesActivity.kt", l = {150, 155, 158}, m = "fetch")
/* loaded from: classes.dex */
public final class FilesActivity$fetch$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ FilesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesActivity$fetch$1(FilesActivity filesActivity, Continuation<? super FilesActivity$fetch$1> continuation) {
        super(continuation);
        this.this$0 = filesActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        FilesActivity filesActivity = this.this$0;
        int i = FilesActivity.$r8$clinit;
        return filesActivity.fetch(null, null, null, null, this);
    }
}
