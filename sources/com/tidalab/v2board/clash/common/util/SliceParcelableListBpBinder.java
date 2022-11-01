package com.tidalab.v2board.clash.common.util;

import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* compiled from: Parcelable.kt */
/* loaded from: classes.dex */
public final class SliceParcelableListBpBinder extends Binder {
    public final int flags;
    public final List<Parcelable> list;

    /* JADX WARN: Multi-variable type inference failed */
    public SliceParcelableListBpBinder(List<? extends Parcelable> list, int i) {
        this.list = list;
        this.flags = i;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 10) {
            return super.onTransact(i, parcel, parcel2, this.flags);
        }
        if (parcel2 == null) {
            return false;
        }
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt() + readInt;
        int size = this.list.size();
        if (readInt2 > size) {
            readInt2 = size;
        }
        parcel2.writeInt(readInt2 - readInt);
        if (readInt >= readInt2) {
            return true;
        }
        while (true) {
            int i3 = readInt + 1;
            this.list.get(readInt).writeToParcel(parcel2, this.flags);
            if (i3 >= readInt2) {
                return true;
            }
            readInt = i3;
        }
    }
}
