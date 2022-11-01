package com.tidalab.v2board.clash.service.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.facebook.react.R$style;
import com.tidalab.v2board.clash.service.model.Profile;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Reflection;
/* compiled from: IProfileManager.kt */
/* loaded from: classes.dex */
public class IProfileManagerDelegate extends Binder implements IProfileManager {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ IProfileManager $$delegate_0;

    public IProfileManagerDelegate(IProfileManager iProfileManager) {
        this.$$delegate_0 = iProfileManager;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object clone(UUID uuid, Continuation<? super UUID> continuation) {
        return this.$$delegate_0.clone(uuid, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object commit(UUID uuid, IFetchObserver iFetchObserver, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.commit(uuid, iFetchObserver, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object create(Profile.Type type, String str, String str2, Continuation<? super UUID> continuation) {
        return this.$$delegate_0.create(type, str, str2, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object delete(UUID uuid, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.delete(uuid, continuation);
    }

    @Override // android.os.Binder, android.os.IBinder
    public String getInterfaceDescriptor() {
        return "com.tidalab.v2board.clash.service.remote.IProfileManager";
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IFetchObserver iFetchObserver;
        if (i == 1) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$1(this, Profile.Type.values()[parcel.readInt()], parcel.readString(), parcel.readString(), null));
        } else if (i == 2) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable = parcel.readSerializable();
            Objects.requireNonNull(readSerializable, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$2(this, (UUID) readSerializable, null));
        } else if (i == 3) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable2 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable2, "null cannot be cast to non-null type java.util.UUID");
            UUID uuid = (UUID) readSerializable2;
            if (parcel.readInt() != 0) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                Reflection.getOrCreateKotlinClass(IFetchObserver.class);
                if (readStrongBinder instanceof IFetchObserver) {
                    iFetchObserver = (IFetchObserver) readStrongBinder;
                } else {
                    iFetchObserver = new IFetchObserverProxy(readStrongBinder);
                }
            } else {
                iFetchObserver = null;
            }
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$3(this, uuid, iFetchObserver, null));
        } else if (i == 4) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable3 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable3, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$4(this, (UUID) readSerializable3, null));
        } else if (i == 5) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable4 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable4, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$5(this, (UUID) readSerializable4, null));
        } else if (i == 6) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable5 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable5, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$6(this, (UUID) readSerializable5, parcel.readString(), parcel.readString(), parcel.readLong(), null));
        } else if (i == 7) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable6 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable6, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$7(this, (UUID) readSerializable6, null));
        } else if (i == 8) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            Serializable readSerializable7 = parcel.readSerializable();
            Objects.requireNonNull(readSerializable7, "null cannot be cast to non-null type java.util.UUID");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$8(this, (UUID) readSerializable7, null));
        } else if (i == 9) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$9(this, null));
        } else if (i == 10) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$10(this, null));
        } else if (i != 11) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IProfileManager");
            R$style.suspendTransaction(parcel, parcel2, new IProfileManagerDelegate$onTransact$11(this, Profile.CREATOR.createFromParcel(parcel), null));
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object patch(UUID uuid, String str, String str2, long j, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.patch(uuid, str, str2, j, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object queryActive(Continuation<? super Profile> continuation) {
        return this.$$delegate_0.queryActive(continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object queryAll(Continuation<? super List<Profile>> continuation) {
        return this.$$delegate_0.queryAll(continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object queryByUUID(UUID uuid, Continuation<? super Profile> continuation) {
        return this.$$delegate_0.queryByUUID(uuid, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object release(UUID uuid, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.release(uuid, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object setActive(Profile profile, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.setActive(profile, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object update(UUID uuid, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.update(uuid, continuation);
    }
}
