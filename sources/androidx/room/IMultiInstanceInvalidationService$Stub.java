package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.MultiInstanceInvalidationService;
/* loaded from: classes.dex */
public abstract class IMultiInstanceInvalidationService$Stub extends Binder implements IInterface {
    public IMultiInstanceInvalidationService$Stub() {
        attachInterface(this, "androidx.room.IMultiInstanceInvalidationService");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
            int registerCallback = ((MultiInstanceInvalidationService.AnonymousClass2) this).registerCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(registerCallback);
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
            IMultiInstanceInvalidationCallback asInterface = IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder());
            int readInt = parcel.readInt();
            MultiInstanceInvalidationService.AnonymousClass2 r7 = (MultiInstanceInvalidationService.AnonymousClass2) this;
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                MultiInstanceInvalidationService.this.mCallbackList.unregister(asInterface);
                MultiInstanceInvalidationService.this.mClientNames.remove(Integer.valueOf(readInt));
            }
            parcel2.writeNoException();
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
            ((MultiInstanceInvalidationService.AnonymousClass2) this).broadcastInvalidation(parcel.readInt(), parcel.createStringArray());
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("androidx.room.IMultiInstanceInvalidationService");
            return true;
        }
    }
}
