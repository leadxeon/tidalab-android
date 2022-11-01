package kotlin.collections;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IndexedValue.kt */
/* loaded from: classes.dex */
public final class IndexedValue<T> {
    public final int index;
    public final T value;

    public IndexedValue(int i, T t) {
        this.index = i;
        this.value = t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexedValue)) {
            return false;
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        return this.index == indexedValue.index && Intrinsics.areEqual(this.value, indexedValue.value);
    }

    public int hashCode() {
        int i = this.index * 31;
        T t = this.value;
        return i + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("IndexedValue(index=");
        outline13.append(this.index);
        outline13.append(", value=");
        outline13.append(this.value);
        outline13.append(")");
        return outline13.toString();
    }
}
