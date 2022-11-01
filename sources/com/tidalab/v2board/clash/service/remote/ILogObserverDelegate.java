package com.tidalab.v2board.clash.service.remote;

import android.os.Binder;
import android.os.Parcel;
import com.tidalab.v2board.clash.core.model.LogMessage;
import kotlin.Unit;
/* compiled from: ILogObserver.kt */
/* loaded from: classes.dex */
public class ILogObserverDelegate extends Binder implements ILogObserver {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ ILogObserver $$delegate_0;

    public ILogObserverDelegate(ILogObserver iLogObserver) {
        this.$$delegate_0 = iLogObserver;
    }

    @Override // android.os.Binder, android.os.IBinder
    public String getInterfaceDescriptor() {
        return "com.tidalab.v2board.clash.service.remote.ILogObserver";
    }

    @Override // com.tidalab.v2board.clash.service.remote.ILogObserver
    public void newItem(LogMessage logMessage) {
        this.$$delegate_0.newItem(logMessage);
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        if (parcel2 == null) {
            return false;
        }
        parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.ILogObserver");
        this.$$delegate_0.newItem(LogMessage.CREATOR.createFromParcel(parcel));
        Unit unit = Unit.INSTANCE;
        parcel2.writeNoException();
        return true;
    }
}
