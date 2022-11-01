package com.tidalab.v2board.clash.design.util;

import androidx.recyclerview.widget.DiffUtil;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: RecyclerView.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$result$1", f = "RecyclerView.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class RecyclerViewKt$patchDataSet$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DiffUtil.DiffResult>, Object> {
    public final /* synthetic */ boolean $detectMove;
    public final /* synthetic */ Function1<T, Object> $id;
    public final /* synthetic */ List<T> $newDataset;
    public final /* synthetic */ KMutableProperty0<List<T>> $property;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerViewKt$patchDataSet$result$1(KMutableProperty0<List<T>> kMutableProperty0, List<? extends T> list, boolean z, Function1<? super T, ? extends Object> function1, Continuation<? super RecyclerViewKt$patchDataSet$result$1> continuation) {
        super(2, continuation);
        this.$property = kMutableProperty0;
        this.$newDataset = list;
        this.$detectMove = z;
        this.$id = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecyclerViewKt$patchDataSet$result$1(this.$property, this.$newDataset, this.$detectMove, this.$id, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super DiffUtil.DiffResult> continuation) {
        return new RecyclerViewKt$patchDataSet$result$1(this.$property, this.$newDataset, this.$detectMove, this.$id, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        DiffUtil.Snake snake;
        DiffUtil.Range range;
        DiffUtil.Range range2;
        DiffUtil.Diagonal diagonal;
        DiffUtil.Snake snake2;
        DiffUtil.Snake snake3;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        InputKt.throwOnFailure(obj);
        final List list = (List) this.$property.get();
        final List<T> list2 = this.$newDataset;
        boolean z = this.$detectMove;
        final Function1<T, Object> function1 = this.$id;
        DiffUtil.Callback diffKt$diffWith$2 = new DiffUtil.Callback() { // from class: com.tidalab.v2board.clash.design.util.DiffKt$diffWith$2
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i8, int i9) {
                return Intrinsics.areEqual(list.get(i8), list2.get(i9));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i8, int i9) {
                return Intrinsics.areEqual(function1.invoke(list.get(i8)), function1.invoke(list2.get(i9)));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return list2.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return list.size();
            }
        };
        int oldListSize = diffKt$diffWith$2.getOldListSize();
        int newListSize = diffKt$diffWith$2.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new DiffUtil.Range(0, oldListSize, 0, newListSize));
        int i8 = oldListSize + newListSize;
        int i9 = 1;
        int i10 = (((i8 + 1) / 2) * 2) + 1;
        int[] iArr = new int[i10];
        int i11 = i10 / 2;
        int[] iArr2 = new int[i10];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            DiffUtil.Range range3 = (DiffUtil.Range) arrayList2.remove(arrayList2.size() - i9);
            if (range3.oldSize() >= i9 && range3.newSize() >= i9) {
                int newSize = ((range3.newSize() + range3.oldSize()) + i9) / 2;
                int i12 = i9 + i11;
                iArr[i12] = range3.oldListStart;
                iArr2[i12] = range3.oldListEnd;
                int i13 = 0;
                while (i13 < newSize) {
                    boolean z2 = Math.abs(range3.oldSize() - range3.newSize()) % 2 == i9;
                    int oldSize = range3.oldSize() - range3.newSize();
                    int i14 = -i13;
                    int i15 = i14;
                    while (true) {
                        if (i15 > i13) {
                            arrayList2 = arrayList2;
                            z = z;
                            newSize = newSize;
                            snake2 = null;
                            break;
                        }
                        if (i15 == i14 || (i15 != i13 && iArr[i15 + 1 + i11] > iArr[(i15 - 1) + i11])) {
                            i6 = iArr[i15 + 1 + i11];
                            i5 = i6;
                        } else {
                            i6 = iArr[(i15 - 1) + i11];
                            i5 = i6 + 1;
                        }
                        newSize = newSize;
                        z = z;
                        int i16 = ((i5 - range3.oldListStart) + range3.newListStart) - i15;
                        if (i13 == 0 || i5 != i6) {
                            arrayList2 = arrayList2;
                            i7 = i16;
                        } else {
                            i7 = i16 - 1;
                            arrayList2 = arrayList2;
                        }
                        while (i5 < range3.oldListEnd && i16 < range3.newListEnd && diffKt$diffWith$2.areItemsTheSame(i5, i16)) {
                            i5++;
                            i16++;
                        }
                        iArr[i15 + i11] = i5;
                        if (z2) {
                            int i17 = oldSize - i15;
                            z2 = z2;
                            if (i17 >= i14 + 1 && i17 <= i13 - 1 && iArr2[i17 + i11] <= i5) {
                                snake2 = new DiffUtil.Snake();
                                snake2.startX = i6;
                                snake2.startY = i7;
                                snake2.endX = i5;
                                snake2.endY = i16;
                                snake2.reverse = false;
                                break;
                            }
                        } else {
                            z2 = z2;
                        }
                        i15 += 2;
                        newSize = newSize;
                        z = z;
                        arrayList2 = arrayList2;
                    }
                    if (snake2 != null) {
                        snake = snake2;
                        arrayList3 = arrayList3;
                        range3 = range3;
                        break;
                    }
                    boolean z3 = (range3.oldSize() - range3.newSize()) % 2 == 0;
                    int oldSize2 = range3.oldSize() - range3.newSize();
                    int i18 = i14;
                    while (true) {
                        if (i18 > i13) {
                            arrayList3 = arrayList3;
                            range3 = range3;
                            snake3 = null;
                            break;
                        }
                        if (i18 == i14 || (i18 != i13 && iArr2[i18 + 1 + i11] < iArr2[(i18 - 1) + i11])) {
                            i2 = iArr2[i18 + 1 + i11];
                            i = i2;
                        } else {
                            i2 = iArr2[(i18 - 1) + i11];
                            i = i2 - 1;
                        }
                        int i19 = range3.newListEnd - ((range3.oldListEnd - i) - i18);
                        if (i13 == 0 || i != i2) {
                            arrayList3 = arrayList3;
                            i3 = i19;
                        } else {
                            i3 = i19 + 1;
                            arrayList3 = arrayList3;
                        }
                        while (i > range3.oldListStart && i19 > range3.newListStart) {
                            int i20 = i - 1;
                            range3 = range3;
                            int i21 = i19 - 1;
                            if (!diffKt$diffWith$2.areItemsTheSame(i20, i21)) {
                                break;
                            }
                            i = i20;
                            i19 = i21;
                            range3 = range3;
                        }
                        range3 = range3;
                        iArr2[i18 + i11] = i;
                        if (z3 && (i4 = oldSize2 - i18) >= i14 && i4 <= i13 && iArr[i4 + i11] >= i) {
                            snake3 = new DiffUtil.Snake();
                            snake3.startX = i;
                            snake3.startY = i19;
                            snake3.endX = i2;
                            snake3.endY = i3;
                            snake3.reverse = true;
                            break;
                        }
                        i18 += 2;
                        arrayList3 = arrayList3;
                        range3 = range3;
                    }
                    if (snake3 != null) {
                        snake = snake3;
                        break;
                    }
                    i13++;
                    arrayList3 = arrayList3;
                    z = z;
                    arrayList2 = arrayList2;
                    range3 = range3;
                    i9 = 1;
                }
            }
            snake = null;
            if (snake != null) {
                if (snake.diagonalSize() > 0) {
                    int i22 = snake.endY;
                    int i23 = snake.startY;
                    int i24 = i22 - i23;
                    int i25 = snake.endX;
                    int i26 = snake.startX;
                    int i27 = i25 - i26;
                    if (!(i24 != i27)) {
                        diagonal = new DiffUtil.Diagonal(i26, i23, i27);
                    } else if (snake.reverse) {
                        diagonal = new DiffUtil.Diagonal(i26, i23, snake.diagonalSize());
                    } else {
                        if (i24 > i27) {
                            diagonal = new DiffUtil.Diagonal(i26, i23 + 1, snake.diagonalSize());
                        } else {
                            diagonal = new DiffUtil.Diagonal(i26 + 1, i23, snake.diagonalSize());
                        }
                    }
                    arrayList.add(diagonal);
                }
                if (arrayList3.isEmpty()) {
                    range2 = new DiffUtil.Range();
                    arrayList3 = arrayList3;
                    range = range3;
                    i9 = 1;
                } else {
                    i9 = 1;
                    arrayList3 = arrayList3;
                    range2 = (DiffUtil.Range) arrayList3.remove(arrayList3.size() - 1);
                    range = range3;
                }
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                range2.oldListEnd = snake.startX;
                range2.newListEnd = snake.startY;
                arrayList2 = arrayList2;
                arrayList2.add(range2);
                range.oldListEnd = range.oldListEnd;
                range.newListEnd = range.newListEnd;
                range.oldListStart = snake.endX;
                range.newListStart = snake.endY;
                arrayList2.add(range);
            } else {
                arrayList3 = arrayList3;
                arrayList2 = arrayList2;
                i9 = 1;
                arrayList3.add(range3);
            }
        }
        Collections.sort(arrayList, DiffUtil.DIAGONAL_COMPARATOR);
        return new DiffUtil.DiffResult(diffKt$diffWith$2, arrayList, iArr, iArr2, z);
    }
}
