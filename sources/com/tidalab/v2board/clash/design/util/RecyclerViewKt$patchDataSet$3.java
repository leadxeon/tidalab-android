package com.tidalab.v2board.clash.design.util;

import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.BatchingListUpdateCallback;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$3", f = "RecyclerView.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class RecyclerViewKt$patchDataSet$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<T> $newDataset;
    public final /* synthetic */ KMutableProperty0<List<T>> $property;
    public final /* synthetic */ DiffUtil.DiffResult $result;
    public final /* synthetic */ RecyclerView.Adapter<H> $this_patchDataSet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerViewKt$patchDataSet$3(KMutableProperty0<List<T>> kMutableProperty0, List<? extends T> list, DiffUtil.DiffResult diffResult, RecyclerView.Adapter<H> adapter, Continuation<? super RecyclerViewKt$patchDataSet$3> continuation) {
        super(2, continuation);
        this.$property = kMutableProperty0;
        this.$newDataset = list;
        this.$result = diffResult;
        this.$this_patchDataSet = adapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecyclerViewKt$patchDataSet$3(this.$property, this.$newDataset, this.$result, this.$this_patchDataSet, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new RecyclerViewKt$patchDataSet$3(this.$property, this.$newDataset, this.$result, this.$this_patchDataSet, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BatchingListUpdateCallback batchingListUpdateCallback;
        int i;
        InputKt.throwOnFailure(obj);
        this.$property.set(this.$newDataset);
        DiffUtil.DiffResult diffResult = this.$result;
        RecyclerView.Adapter<H> adapter = this.$this_patchDataSet;
        Objects.requireNonNull(diffResult);
        AdapterListUpdateCallback adapterListUpdateCallback = new AdapterListUpdateCallback(adapter);
        if (adapterListUpdateCallback instanceof BatchingListUpdateCallback) {
            batchingListUpdateCallback = (BatchingListUpdateCallback) adapterListUpdateCallback;
        } else {
            batchingListUpdateCallback = new BatchingListUpdateCallback(adapterListUpdateCallback);
        }
        int i2 = diffResult.mOldListSize;
        ArrayDeque arrayDeque = new ArrayDeque();
        int i3 = diffResult.mOldListSize;
        int i4 = diffResult.mNewListSize;
        for (int size = diffResult.mDiagonals.size() - 1; size >= 0; size--) {
            DiffUtil.Diagonal diagonal = diffResult.mDiagonals.get(size);
            int i5 = diagonal.x;
            int i6 = diagonal.size;
            int i7 = i5 + i6;
            int i8 = diagonal.y + i6;
            while (true) {
                if (i3 > i7) {
                    i3--;
                    int i9 = diffResult.mOldItemStatuses[i3];
                    if ((i9 & 12) != 0) {
                        DiffUtil.PostponedUpdate postponedUpdate = DiffUtil.DiffResult.getPostponedUpdate(arrayDeque, i9 >> 4, false);
                        if (postponedUpdate != null) {
                            int i10 = (i2 - postponedUpdate.currentPos) - 1;
                            batchingListUpdateCallback.onMoved(i3, i10);
                            if ((i9 & 4) != 0) {
                                Objects.requireNonNull(diffResult.mCallback);
                                batchingListUpdateCallback.onChanged(i10, 1, null);
                            }
                        } else {
                            arrayDeque.add(new DiffUtil.PostponedUpdate(i3, (i2 - i3) - 1, true));
                        }
                    } else {
                        batchingListUpdateCallback.onRemoved(i3, 1);
                        i2--;
                    }
                }
            }
            while (i4 > i8) {
                i4--;
                int i11 = diffResult.mNewItemStatuses[i4];
                if ((i11 & 12) != 0) {
                    DiffUtil.PostponedUpdate postponedUpdate2 = DiffUtil.DiffResult.getPostponedUpdate(arrayDeque, i11 >> 4, true);
                    if (postponedUpdate2 == null) {
                        arrayDeque.add(new DiffUtil.PostponedUpdate(i4, i2 - i3, false));
                    } else {
                        batchingListUpdateCallback.onMoved((i2 - postponedUpdate2.currentPos) - 1, i3);
                        if ((i11 & 4) != 0) {
                            Objects.requireNonNull(diffResult.mCallback);
                            batchingListUpdateCallback.onChanged(i3, 1, null);
                        }
                    }
                } else {
                    batchingListUpdateCallback.onInserted(i3, 1);
                    i2++;
                }
            }
            int i12 = diagonal.x;
            for (i = 0; i < diagonal.size; i++) {
                if ((diffResult.mOldItemStatuses[i12] & 15) == 2) {
                    Objects.requireNonNull(diffResult.mCallback);
                    batchingListUpdateCallback.onChanged(i12, 1, null);
                }
                i12++;
            }
            i3 = diagonal.x;
            i4 = diagonal.y;
        }
        batchingListUpdateCallback.dispatchLastEvent();
        return Unit.INSTANCE;
    }
}
