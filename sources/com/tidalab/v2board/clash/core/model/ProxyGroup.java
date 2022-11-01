package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.util.SliceParcelableListBpBinder;
import com.tidalab.v2board.clash.core.model.Proxy;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.Serializable;
/* compiled from: ProxyGroup.kt */
@Serializable
/* loaded from: classes.dex */
public final class ProxyGroup implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final String now;
    public final List<Proxy> proxies;
    public final Proxy.Type type;

    /* compiled from: ProxyGroup.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<ProxyGroup> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public ProxyGroup createFromParcel(Parcel parcel) {
            return new ProxyGroup(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProxyGroup[] newArray(int i) {
            return new ProxyGroup[i];
        }
    }

    /* compiled from: ProxyGroup.kt */
    /* loaded from: classes.dex */
    public static final class SliceProxyList implements List<Proxy>, Parcelable, KMappedMarker {
        public static final CREATOR CREATOR = new CREATOR(null);
        public final /* synthetic */ List<Proxy> $$delegate_0;

        /* compiled from: ProxyGroup.kt */
        /* loaded from: classes.dex */
        public static final class CREATOR implements Parcelable.Creator<SliceProxyList> {
            public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            }

            @Override // android.os.Parcelable.Creator
            public SliceProxyList createFromParcel(Parcel parcel) {
                return new SliceProxyList(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SliceProxyList[] newArray(int i) {
                return new SliceProxyList[i];
            }
        }

        public SliceProxyList(List<Proxy> list) {
            this.$$delegate_0 = list;
        }

        @Override // java.util.List
        public void add(int i, Proxy proxy) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends Proxy> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends Proxy> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Proxy)) {
                return false;
            }
            return this.$$delegate_0.contains((Proxy) obj);
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
        public Proxy get(int i) {
            return this.$$delegate_0.get(i);
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Proxy)) {
                return -1;
            }
            return this.$$delegate_0.indexOf((Proxy) obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.$$delegate_0.isEmpty();
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<Proxy> iterator() {
            return this.$$delegate_0.iterator();
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Proxy)) {
                return -1;
            }
            return this.$$delegate_0.lastIndexOf((Proxy) obj);
        }

        @Override // java.util.List
        public ListIterator<Proxy> listIterator() {
            return this.$$delegate_0.listIterator();
        }

        @Override // java.util.List
        public ListIterator<Proxy> listIterator(int i) {
            return this.$$delegate_0.listIterator(i);
        }

        @Override // java.util.List
        public /* bridge */ /* synthetic */ Proxy remove(int i) {
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
        public void replaceAll(UnaryOperator<Proxy> unaryOperator) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection<? extends Object> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public Proxy set(int i, Proxy proxy) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public final int size() {
            return this.$$delegate_0.size();
        }

        @Override // java.util.List
        public void sort(Comparator<? super Proxy> comparator) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public List<Proxy> subList(int i, int i2) {
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

        public SliceProxyList(Parcel parcel) {
            this.$$delegate_0 = PathParser.createListFromParcelSlice(Proxy.CREATOR, parcel, 0, 50);
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(Object obj) {
            Proxy proxy = (Proxy) obj;
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public ProxyGroup(int i, Proxy.Type type, List list, String str) {
        if (7 != (i & 7)) {
            ProxyGroup$$serializer proxyGroup$$serializer = ProxyGroup$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 7, ProxyGroup$$serializer.descriptor);
        }
        this.type = type;
        this.proxies = list;
        this.now = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProxyGroup)) {
            return false;
        }
        ProxyGroup proxyGroup = (ProxyGroup) obj;
        return this.type == proxyGroup.type && Intrinsics.areEqual(this.proxies, proxyGroup.proxies) && Intrinsics.areEqual(this.now, proxyGroup.now);
    }

    public int hashCode() {
        int hashCode = this.proxies.hashCode();
        return this.now.hashCode() + ((hashCode + (this.type.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ProxyGroup(type=");
        outline13.append(this.type);
        outline13.append(", proxies=");
        outline13.append(this.proxies);
        outline13.append(", now=");
        outline13.append(this.now);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type.ordinal());
        new SliceProxyList(this.proxies).writeToParcel(parcel, 0);
        parcel.writeString(this.now);
    }

    public ProxyGroup(Proxy.Type type, List<Proxy> list, String str) {
        this.type = type;
        this.proxies = list;
        this.now = str;
    }

    public ProxyGroup(Parcel parcel) {
        Proxy.Type type = Proxy.Type.values()[parcel.readInt()];
        SliceProxyList sliceProxyList = new SliceProxyList(parcel);
        String readString = parcel.readString();
        this.type = type;
        this.proxies = sliceProxyList;
        this.now = readString;
    }
}
