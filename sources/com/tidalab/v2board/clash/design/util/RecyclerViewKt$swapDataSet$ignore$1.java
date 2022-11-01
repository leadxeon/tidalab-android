package com.tidalab.v2board.clash.design.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$ignore$1", f = "RecyclerView.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class RecyclerViewKt$swapDataSet$ignore$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ boolean $compareEquals;
    public final /* synthetic */ List<T> $newDataset;
    public final /* synthetic */ KMutableProperty0<List<T>> $property;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerViewKt$swapDataSet$ignore$1(boolean z, KMutableProperty0<List<T>> kMutableProperty0, List<? extends T> list, Continuation<? super RecyclerViewKt$swapDataSet$ignore$1> continuation) {
        super(2, continuation);
        this.$compareEquals = z;
        this.$property = kMutableProperty0;
        this.$newDataset = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecyclerViewKt$swapDataSet$ignore$1(this.$compareEquals, this.$property, this.$newDataset, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        boolean z = this.$compareEquals;
        KMutableProperty0<List<T>> kMutableProperty0 = this.$property;
        Collection collection = this.$newDataset;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return Boolean.valueOf(z && Intrinsics.areEqual(kMutableProperty0.get(), collection));
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return Boolean.valueOf(this.$compareEquals && Intrinsics.areEqual(this.$property.get(), this.$newDataset));
    }
}
