package com.tidalab.v2board.clash.service.remote;

import android.os.IBinder;
import android.os.Parcel;
import kotlin.jvm.internal.Reflection;
/* compiled from: IRemoteService.kt */
/* loaded from: classes.dex */
public final class IRemoteServiceProxy implements IRemoteService {
    public final IBinder remote;

    public IRemoteServiceProxy(IBinder iBinder) {
        this.remote = iBinder;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IClashManager clash() {
        IClashManager iClashManager;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IRemoteServiceDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IRemoteService");
            this.remote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            IBinder readStrongBinder = obtain2.readStrongBinder();
            Reflection.getOrCreateKotlinClass(IClashManager.class);
            if (readStrongBinder instanceof IClashManager) {
                iClashManager = (IClashManager) readStrongBinder;
            } else {
                iClashManager = new IClashManagerProxy(readStrongBinder);
            }
            return iClashManager;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IProfileManager profile() {
        IProfileManager iProfileManager;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IRemoteServiceDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IRemoteService");
            this.remote.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            IBinder readStrongBinder = obtain2.readStrongBinder();
            Reflection.getOrCreateKotlinClass(IProfileManager.class);
            if (readStrongBinder instanceof IProfileManager) {
                iProfileManager = (IProfileManager) readStrongBinder;
            } else {
                iProfileManager = new IProfileManagerProxy(readStrongBinder);
            }
            return iProfileManager;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
