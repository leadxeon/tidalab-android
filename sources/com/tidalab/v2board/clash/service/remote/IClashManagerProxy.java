package com.tidalab.v2board.clash.service.remote;

import android.os.IBinder;
import android.os.Parcel;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.ProviderList;
import com.tidalab.v2board.clash.core.model.ProxyGroup;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.core.model.TunnelState$$serializer;
import com.tidalab.v2board.clash.core.model.UiConfiguration;
import com.tidalab.v2board.clash.core.model.UiConfiguration$$serializer;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
/* compiled from: IClashManager.kt */
/* loaded from: classes.dex */
public final class IClashManagerProxy implements IClashManager {
    public final IBinder remote;

    public IClashManagerProxy(IBinder iBinder) {
        this.remote = iBinder;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void clearOverride(Clash.OverrideSlot overrideSlot) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeInt(overrideSlot.ordinal());
            this.remote.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            Unit unit = Unit.INSTANCE;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object healthCheck(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IClashManagerProxy$healthCheck$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IClashManagerProxy$healthCheck$1 r0 = (com.tidalab.v2board.clash.service.remote.IClashManagerProxy$healthCheck$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IClashManagerProxy$healthCheck$1 r0 = new com.tidalab.v2board.clash.service.remote.IClashManagerProxy$healthCheck$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0061
        L_0x002f:
            r7 = move-exception
            goto L_0x006d
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IClashManagerDelegate.$r8$clinit     // Catch: all -> 0x0071
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IClashManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x0071
            r7.writeString(r6)     // Catch: all -> 0x0071
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0071
            r4 = 8
            r0.L$0 = r7     // Catch: all -> 0x0071
            r0.L$1 = r2     // Catch: all -> 0x0071
            r0.label = r3     // Catch: all -> 0x0071
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x0071
            if (r6 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r0 = r7
            r6 = r2
        L_0x0061:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x006d:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x0072
        L_0x0071:
            r6 = move-exception
        L_0x0072:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IClashManagerProxy.healthCheck(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void patchOverride(Clash.OverrideSlot overrideSlot, ConfigurationOverride configurationOverride) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeInt(overrideSlot.ordinal());
            configurationOverride.writeToParcel(obtain, 0);
            this.remote.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            Unit unit = Unit.INSTANCE;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public boolean patchSelector(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeString(str);
            obtain.writeString(str2);
            boolean z = false;
            this.remote.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public UiConfiguration queryConfiguration() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            this.remote.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            Objects.requireNonNull(UiConfiguration.CREATOR);
            return (UiConfiguration) UiConfiguration$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(obtain2));
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ConfigurationOverride queryOverride(Clash.OverrideSlot overrideSlot) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeInt(overrideSlot.ordinal());
            this.remote.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            return ConfigurationOverride.CREATOR.createFromParcel(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProviderList queryProviders() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            this.remote.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            Objects.requireNonNull(ProviderList.CREATOR);
            return new ProviderList(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProxyGroup queryProxyGroup(String str, ProxySort proxySort) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeString(str);
            obtain.writeInt(proxySort.ordinal());
            this.remote.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            Objects.requireNonNull(ProxyGroup.CREATOR);
            return new ProxyGroup(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public List<String> queryProxyGroupNames(boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            obtain.writeInt(z ? 1 : 0);
            this.remote.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                arrayList.add(obtain2.readString());
            }
            return arrayList;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public long queryTrafficTotal() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            this.remote.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readLong();
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public TunnelState queryTunnelState() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            this.remote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            Objects.requireNonNull(TunnelState.CREATOR);
            return (TunnelState) TunnelState$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(obtain2));
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void setLogObserver(ILogObserver iLogObserver) {
        IBinder iBinder;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            int i = IClashManagerDelegate.$r8$clinit;
            obtain.writeInterfaceToken("com.tidalab.v2board.clash.service.remote.IClashManager");
            if (iLogObserver != null) {
                obtain.writeInt(1);
                if (iLogObserver instanceof IBinder) {
                    iBinder = (IBinder) iLogObserver;
                } else {
                    iBinder = new ILogObserverDelegate(iLogObserver);
                }
                obtain.writeStrongBinder(iBinder);
            } else {
                obtain.writeInt(0);
            }
            this.remote.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            Unit unit = Unit.INSTANCE;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object updateProvider(com.tidalab.v2board.clash.core.model.Provider.Type r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.remote.IClashManagerProxy$updateProvider$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.remote.IClashManagerProxy$updateProvider$1 r0 = (com.tidalab.v2board.clash.service.remote.IClashManagerProxy$updateProvider$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IClashManagerProxy$updateProvider$1 r0 = new com.tidalab.v2board.clash.service.remote.IClashManagerProxy$updateProvider$1
            r0.<init>(r5, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r7 = r0.L$0
            android.os.Parcel r7 = (android.os.Parcel) r7
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x002f
            goto L_0x0068
        L_0x002f:
            r8 = move-exception
            goto L_0x0074
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            android.os.Parcel r8 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IClashManagerDelegate.$r8$clinit     // Catch: all -> 0x0078
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IClashManager"
            r8.writeInterfaceToken(r4)     // Catch: all -> 0x0078
            int r6 = r6.ordinal()     // Catch: all -> 0x0078
            r8.writeInt(r6)     // Catch: all -> 0x0078
            r8.writeString(r7)     // Catch: all -> 0x0078
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0078
            r7 = 9
            r0.L$0 = r8     // Catch: all -> 0x0078
            r0.L$1 = r2     // Catch: all -> 0x0078
            r0.label = r3     // Catch: all -> 0x0078
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r7, r8, r2, r0)     // Catch: all -> 0x0078
            if (r6 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r7 = r8
            r6 = r2
        L_0x0068:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r7.recycle()
            r6.recycle()
            return r8
        L_0x0074:
            r2 = r6
            r6 = r8
            r8 = r7
            goto L_0x0079
        L_0x0078:
            r6 = move-exception
        L_0x0079:
            r8.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IClashManagerProxy.updateProvider(com.tidalab.v2board.clash.core.model.Provider$Type, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
