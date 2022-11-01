package com.tidalab.v2board.clash.service.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.facebook.react.R$style;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.core.model.ProviderList;
import com.tidalab.v2board.clash.core.model.ProxyGroup;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.core.model.UiConfiguration;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Reflection;
/* compiled from: IClashManager.kt */
/* loaded from: classes.dex */
public class IClashManagerDelegate extends Binder implements IClashManager {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ IClashManager $$delegate_0;

    public IClashManagerDelegate(IClashManager iClashManager) {
        this.$$delegate_0 = iClashManager;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void clearOverride(Clash.OverrideSlot overrideSlot) {
        this.$$delegate_0.clearOverride(overrideSlot);
    }

    @Override // android.os.Binder, android.os.IBinder
    public String getInterfaceDescriptor() {
        return "com.tidalab.v2board.clash.service.remote.IClashManager";
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public Object healthCheck(String str, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.healthCheck(str, continuation);
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        boolean z = false;
        if (i == 1) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            TunnelState queryTunnelState = queryTunnelState();
            parcel2.writeNoException();
            queryTunnelState.writeToParcel(parcel2, 0);
        } else if (i == 2) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            long queryTrafficTotal = queryTrafficTotal();
            parcel2.writeNoException();
            parcel2.writeLong(queryTrafficTotal);
        } else if (i == 3) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            if (parcel.readInt() != 0) {
                z = true;
            }
            List<String> queryProxyGroupNames = queryProxyGroupNames(z);
            parcel2.writeNoException();
            parcel2.writeInt(queryProxyGroupNames.size());
            for (String str : queryProxyGroupNames) {
                parcel2.writeString(str);
            }
        } else if (i == 4) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            ProxyGroup queryProxyGroup = this.$$delegate_0.queryProxyGroup(parcel.readString(), ProxySort.values()[parcel.readInt()]);
            parcel2.writeNoException();
            queryProxyGroup.writeToParcel(parcel2, 0);
        } else if (i == 5) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            UiConfiguration queryConfiguration = this.$$delegate_0.queryConfiguration();
            parcel2.writeNoException();
            queryConfiguration.writeToParcel(parcel2, 0);
        } else if (i == 6) {
            if (parcel2 == null) {
                return false;
            }
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            ProviderList queryProviders = queryProviders();
            parcel2.writeNoException();
            queryProviders.writeToParcel(parcel2, 0);
        } else if (i != 7) {
            ILogObserver iLogObserver = null;
            if (i == 8) {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                R$style.suspendTransaction(parcel, parcel2, new IClashManagerDelegate$onTransact$2(this, parcel.readString(), null));
            } else if (i == 9) {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                R$style.suspendTransaction(parcel, parcel2, new IClashManagerDelegate$onTransact$3(this, Provider.Type.values()[parcel.readInt()], parcel.readString(), null));
            } else if (i == 10) {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                ConfigurationOverride queryOverride = queryOverride(Clash.OverrideSlot.values()[parcel.readInt()]);
                parcel2.writeNoException();
                queryOverride.writeToParcel(parcel2, 0);
            } else if (i == 11) {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                this.$$delegate_0.patchOverride(Clash.OverrideSlot.values()[parcel.readInt()], ConfigurationOverride.CREATOR.createFromParcel(parcel));
                Unit unit = Unit.INSTANCE;
                parcel2.writeNoException();
            } else if (i == 12) {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                this.$$delegate_0.clearOverride(Clash.OverrideSlot.values()[parcel.readInt()]);
                Unit unit2 = Unit.INSTANCE;
                parcel2.writeNoException();
            } else if (i != 13) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                if (parcel2 == null) {
                    return false;
                }
                parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
                if (parcel.readInt() != 0) {
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    Reflection.getOrCreateKotlinClass(ILogObserver.class);
                    if (readStrongBinder instanceof ILogObserver) {
                        iLogObserver = (ILogObserver) readStrongBinder;
                    } else {
                        iLogObserver = new ILogObserverProxy(readStrongBinder);
                    }
                }
                this.$$delegate_0.setLogObserver(iLogObserver);
                Unit unit3 = Unit.INSTANCE;
                parcel2.writeNoException();
            }
        } else if (parcel2 == null) {
            return false;
        } else {
            parcel.enforceInterface("com.tidalab.v2board.clash.service.remote.IClashManager");
            boolean patchSelector = this.$$delegate_0.patchSelector(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(patchSelector ? 1 : 0);
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void patchOverride(Clash.OverrideSlot overrideSlot, ConfigurationOverride configurationOverride) {
        this.$$delegate_0.patchOverride(overrideSlot, configurationOverride);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public boolean patchSelector(String str, String str2) {
        return this.$$delegate_0.patchSelector(str, str2);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public UiConfiguration queryConfiguration() {
        return this.$$delegate_0.queryConfiguration();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ConfigurationOverride queryOverride(Clash.OverrideSlot overrideSlot) {
        return this.$$delegate_0.queryOverride(overrideSlot);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProviderList queryProviders() {
        return this.$$delegate_0.queryProviders();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProxyGroup queryProxyGroup(String str, ProxySort proxySort) {
        return this.$$delegate_0.queryProxyGroup(str, proxySort);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public List<String> queryProxyGroupNames(boolean z) {
        return this.$$delegate_0.queryProxyGroupNames(z);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public long queryTrafficTotal() {
        return this.$$delegate_0.queryTrafficTotal();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public TunnelState queryTunnelState() {
        return this.$$delegate_0.queryTunnelState();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void setLogObserver(ILogObserver iLogObserver) {
        this.$$delegate_0.setLogObserver(iLogObserver);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public Object updateProvider(Provider.Type type, String str, Continuation<? super Unit> continuation) {
        return this.$$delegate_0.updateProvider(type, str, continuation);
    }
}
