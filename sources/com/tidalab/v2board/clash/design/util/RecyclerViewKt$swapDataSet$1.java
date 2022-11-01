package com.tidalab.v2board.clash.design.util;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt", f = "RecyclerView.kt", l = {26, WebSocketProtocol.B0_FLAG_RSV2}, m = "swapDataSet")
/* loaded from: classes.dex */
public final class RecyclerViewKt$swapDataSet$1<H extends RecyclerView.ViewHolder, T> extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;

    public RecyclerViewKt$swapDataSet$1(Continuation<? super RecyclerViewKt$swapDataSet$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.swapDataSet(null, null, null, false, this);
    }
}
