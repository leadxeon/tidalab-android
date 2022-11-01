package com.tidalab.v2board.clash.service.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
/* compiled from: IRemoteService.kt */
/* loaded from: classes.dex */
public class IRemoteServiceDelegate extends Binder implements IRemoteService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ IRemoteService $$delegate_0;

    public IRemoteServiceDelegate(IRemoteService iRemoteService) {
        this.$$delegate_0 = iRemoteService;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IClashManager clash() {
        return this.$$delegate_0.clash();
    }

    @Override // android.os.Binder, android.os.IBinder
    public String getInterfaceDescriptor() {
        return "com.tidalab.v2board.clash.service.remote.IRemoteService";
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IBinder iBinder;
        IBinder iBinder2;
        if (i == 1) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IRemoteService");
            IClashManager clash = clash();
            parcel2.writeNoException();
            if (clash instanceof IBinder) {
                iBinder2 = (IBinder) clash;
            } else {
                iBinder2 = new IClashManagerDelegate(clash);
            }
            parcel2.writeStrongBinder(iBinder2);
        } else if (i != 2) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IRemoteService");
            IProfileManager profile = profile();
            parcel2.writeNoException();
            if (profile instanceof IBinder) {
                iBinder = (IBinder) profile;
            } else {
                iBinder = new IProfileManagerDelegate(profile);
            }
            parcel2.writeStrongBinder(iBinder);
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IProfileManager profile() {
        return this.$$delegate_0.profile();
    }
}
