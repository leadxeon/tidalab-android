package com.tidalab.v2board.clash.design.util;

import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$2", f = "RecyclerView.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class RecyclerViewKt$swapDataSet$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<T> $newDataset;
    public final /* synthetic */ KMutableProperty0<List<T>> $property;
    public final /* synthetic */ RecyclerView.Adapter<H> $this_swapDataSet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerViewKt$swapDataSet$2(KMutableProperty0<List<T>> kMutableProperty0, List<? extends T> list, RecyclerView.Adapter<H> adapter, Continuation<? super RecyclerViewKt$swapDataSet$2> continuation) {
        super(2, continuation);
        this.$property = kMutableProperty0;
        this.$newDataset = list;
        this.$this_swapDataSet = adapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecyclerViewKt$swapDataSet$2(this.$property, this.$newDataset, this.$this_swapDataSet, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        KMutableProperty0<List<T>> kMutableProperty0 = this.$property;
        List<T> list = this.$newDataset;
        RecyclerView.Adapter<H> adapter = this.$this_swapDataSet;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        if (((List) kMutableProperty0.get()).size() == list.size()) {
            kMutableProperty0.set(list);
            adapter.mObservable.notifyItemRangeChanged(0, list.size(), null);
        } else {
            adapter.mObservable.notifyItemRangeRemoved(0, ((List) kMutableProperty0.get()).size());
            kMutableProperty0.set(list);
            adapter.mObservable.notifyItemRangeInserted(0, list.size());
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        if (((List) this.$property.get()).size() == this.$newDataset.size()) {
            this.$property.set(this.$newDataset);
            RecyclerView.Adapter<H> adapter = this.$this_swapDataSet;
            adapter.mObservable.notifyItemRangeChanged(0, this.$newDataset.size(), null);
        } else {
            RecyclerView.Adapter<H> adapter2 = this.$this_swapDataSet;
            adapter2.mObservable.notifyItemRangeRemoved(0, ((List) this.$property.get()).size());
            this.$property.set(this.$newDataset);
            RecyclerView.Adapter<H> adapter3 = this.$this_swapDataSet;
            adapter3.mObservable.notifyItemRangeInserted(0, this.$newDataset.size());
        }
        return Unit.INSTANCE;
    }
}
