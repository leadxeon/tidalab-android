package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.util.SliceParcelableListBpBinder;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: ProviderList.kt */
/* loaded from: classes.dex */
public final class ProviderList implements List<Provider>, Parcelable, KMappedMarker {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final /* synthetic */ List<Provider> $$delegate_0;

    /* compiled from: ProviderList.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<ProviderList> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public ProviderList createFromParcel(Parcel parcel) {
            return new ProviderList(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProviderList[] newArray(int i) {
            return new ProviderList[i];
        }
    }

    public ProviderList(List<Provider> list) {
        this.$$delegate_0 = list;
    }

    @Override // java.util.List
    public void add(int i, Provider provider) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends Provider> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Provider> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Provider)) {
            return false;
        }
        return this.$$delegate_0.contains((Provider) obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        return this.$$delegate_0.containsAll(collection);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.util.List
    public Provider get(int i) {
        return this.$$delegate_0.get(i);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Provider)) {
            return -1;
        }
        return this.$$delegate_0.indexOf((Provider) obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Provider> iterator() {
        return this.$$delegate_0.iterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Provider)) {
            return -1;
        }
        return this.$$delegate_0.lastIndexOf((Provider) obj);
    }

    @Override // java.util.List
    public ListIterator<Provider> listIterator() {
        return this.$$delegate_0.listIterator();
    }

    @Override // java.util.List
    public ListIterator<Provider> listIterator(int i) {
        return this.$$delegate_0.listIterator(i);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Provider remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<Provider> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public Provider set(int i, Provider provider) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.$$delegate_0.size();
    }

    @Override // java.util.List
    public void sort(Comparator<? super Provider> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<Provider> subList(int i, int i2) {
        return this.$$delegate_0.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SliceParcelableListBpBinder sliceParcelableListBpBinder = new SliceParcelableListBpBinder(this, i);
        parcel.writeInt(size());
        parcel.writeStrongBinder(sliceParcelableListBpBinder);
    }

    public ProviderList(Parcel parcel) {
        this.$$delegate_0 = PathParser.createListFromParcelSlice(Provider.CREATOR, parcel, 0, 20);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        Provider provider = (Provider) obj;
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
