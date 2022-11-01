package com.tidalab.v2board.clash.service.remote;

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
/* compiled from: IClashManager.kt */
/* loaded from: classes.dex */
public interface IClashManager {
    void clearOverride(Clash.OverrideSlot overrideSlot);

    Object healthCheck(String str, Continuation<? super Unit> continuation);

    void patchOverride(Clash.OverrideSlot overrideSlot, ConfigurationOverride configurationOverride);

    boolean patchSelector(String str, String str2);

    UiConfiguration queryConfiguration();

    ConfigurationOverride queryOverride(Clash.OverrideSlot overrideSlot);

    ProviderList queryProviders();

    ProxyGroup queryProxyGroup(String str, ProxySort proxySort);

    List<String> queryProxyGroupNames(boolean z);

    long queryTrafficTotal();

    TunnelState queryTunnelState();

    void setLogObserver(ILogObserver iLogObserver);

    Object updateProvider(Provider.Type type, String str, Continuation<? super Unit> continuation);
}
