package com.tidalab.v2board.clash.service.remote;

import android.os.IBinder;
import android.os.Parcel;
import com.tidalab.v2board.clash.core.model.LogMessage;
import kotlin.Unit;
/* compiled from: ILogObserver.kt */
/* loaded from: classes.dex */
public final class ILogObserverProxy implements ILogObserver {
    public final IBinder remote;

    public ILogObserverProxy(IBinder iBinder) {
        this.remote = iBinder;
    }

    @Override // com.tidalab.v2board.clash.service.remote.ILogObserver
    public void newItem(LogMessage logMessage) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = ILogObserverDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.ILogObserver");
            logMessage.writeToParcel(obtain, 0);
            this.remote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            Unit unit = Unit.INSTANCE;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
