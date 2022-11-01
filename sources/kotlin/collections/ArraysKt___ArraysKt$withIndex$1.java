package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.Lambda;
/* compiled from: _Arrays.kt */
/* loaded from: classes.dex */
public final class ArraysKt___ArraysKt$withIndex$1 extends Lambda implements Function0<Iterator<? extends T>> {
    public final /* synthetic */ Object[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArraysKt___ArraysKt$withIndex$1(Object[] objArr) {
        super(0);
        this.$this_withIndex = objArr;
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return new ArrayIterator(this.$this_withIndex);
    }
}
