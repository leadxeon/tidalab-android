package com.tidalab.v2board.clash.design.util;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt", f = "RecyclerView.kt", l = {53, 57}, m = "patchDataSet")
/* loaded from: classes.dex */
public final class RecyclerViewKt$patchDataSet$1<H extends RecyclerView.ViewHolder, T> extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;

    public RecyclerViewKt$patchDataSet$1(Continuation<? super RecyclerViewKt$patchDataSet$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.patchDataSet(null, null, null, false, null, this);
    }
}
