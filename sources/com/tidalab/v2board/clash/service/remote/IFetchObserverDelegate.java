package com.tidalab.v2board.clash.service.remote;

import android.os.Binder;
import android.os.Parcel;
import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.core.model.FetchStatus$$serializer;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import java.util.Objects;
import kotlin.Unit;
/* compiled from: IFetchObserver.kt */
/* loaded from: classes.dex */
public class IFetchObserverDelegate extends Binder implements IFetchObserver {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ IFetchObserver $$delegate_0;

    public IFetchObserverDelegate(IFetchObserver iFetchObserver) {
        this.$$delegate_0 = iFetchObserver;
    }

    @Override // android.os.Binder, android.os.IBinder
    public String getInterfaceDescriptor() {
        return "com.tidalab.v2board.clash.service.remote.IFetchObserver";
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        if (parcel2 == null) {
            return false;
        }
        parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IFetchObserver");
        Objects.requireNonNull(FetchStatus.CREATOR);
        this.$$delegate_0.updateStatus((FetchStatus) FetchStatus$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel)));
        Unit unit = Unit.INSTANCE;
        parcel2.writeNoException();
        return true;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IFetchObserver
    public void updateStatus(FetchStatus fetchStatus) {
        this.$$delegate_0.updateStatus(fetchStatus);
    }
}
