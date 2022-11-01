package com.tidalab.v2board.clash.service.remote;

import android.os.IBinder;
import android.os.Parcel;
import com.tidalab.v2board.clash.core.model.FetchStatus;
import kotlin.Unit;
/* compiled from: IFetchObserver.kt */
/* loaded from: classes.dex */
public final class IFetchObserverProxy implements IFetchObserver {
    public final IBinder remote;

    public IFetchObserverProxy(IBinder iBinder) {
        this.remote = iBinder;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IFetchObserver
    public void updateStatus(FetchStatus fetchStatus) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IFetchObserverDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IFetchObserver");
            fetchStatus.writeToParcel(obtain, 0);
            this.remote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            Unit unit = Unit.INSTANCE;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
