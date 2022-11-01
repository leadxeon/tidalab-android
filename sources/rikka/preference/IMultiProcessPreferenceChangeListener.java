package rikka.preference;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import rikka.preference.MultiProcessPreference;
/* loaded from: classes.dex */
public interface IMultiProcessPreferenceChangeListener extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMultiProcessPreferenceChangeListener {
        public static final /* synthetic */ int $r8$clinit = 0;

        /* loaded from: classes.dex */
        public static class Proxy implements IMultiProcessPreferenceChangeListener {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // rikka.preference.IMultiProcessPreferenceChangeListener
            public void onPreferenceChanged(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("rikka.preference.IMultiProcessPreferenceChangeListener");
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0)) {
                        int i = Stub.$r8$clinit;
                    }
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "rikka.preference.IMultiProcessPreferenceChangeListener");
        }

        public static IMultiProcessPreferenceChangeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("rikka.preference.IMultiProcessPreferenceChangeListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiProcessPreferenceChangeListener)) {
                return new Proxy(iBinder);
            }
            return (IMultiProcessPreferenceChangeListener) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("rikka.preference.IMultiProcessPreferenceChangeListener");
                ((MultiProcessPreference.AnonymousClass1) this).onPreferenceChanged(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("rikka.preference.IMultiProcessPreferenceChangeListener");
                return true;
            }
        }
    }

    void onPreferenceChanged(String str) throws RemoteException;
}
