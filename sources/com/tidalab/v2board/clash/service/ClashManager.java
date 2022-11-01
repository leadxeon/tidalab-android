package com.tidalab.v2board.clash.service;

import android.content.Context;
import android.content.Intent;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.core.bridge.LogcatInterface;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride$$serializer;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.core.model.LogMessage$$serializer;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.core.model.Provider$$serializer;
import com.tidalab.v2board.clash.core.model.ProviderList;
import com.tidalab.v2board.clash.core.model.Proxy;
import com.tidalab.v2board.clash.core.model.ProxyGroup;
import com.tidalab.v2board.clash.core.model.ProxyGroup$$serializer;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.core.model.TunnelState$$serializer;
import com.tidalab.v2board.clash.core.model.UiConfiguration;
import com.tidalab.v2board.clash.core.model.UiConfiguration$$serializer;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.data.Selection;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import com.tidalab.v2board.clash.service.remote.ILogObserver;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.internal.Composer;
import kotlinx.serialization.json.internal.JsonPrimitiveDecoder;
import kotlinx.serialization.json.internal.JsonStringBuilder;
import kotlinx.serialization.json.internal.JsonTreeDecoder;
import kotlinx.serialization.json.internal.JsonTreeListDecoder;
import kotlinx.serialization.json.internal.PolymorphicKt;
import kotlinx.serialization.json.internal.StreamingJsonEncoder;
import kotlinx.serialization.json.internal.WriteMode;
import okhttp3.HttpUrl;
/* compiled from: ClashManager.kt */
/* loaded from: classes.dex */
public final class ClashManager implements IClashManager, CoroutineScope {
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.IO);
    public final Context context;
    public ReceiveChannel<LogMessage> logReceiver;
    public final ServiceStore store;

    public ClashManager(Context context) {
        this.context = context;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        this.store = new ServiceStore(context);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void clearOverride(Clash.OverrideSlot overrideSlot) {
        Clash.INSTANCE.clearOverride(overrideSlot);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public Object healthCheck(String str, Continuation<? super Unit> continuation) {
        Clash clash = Clash.INSTANCE;
        CompletableDeferred<Unit> CompletableDeferred$default = InputKt.CompletableDeferred$default(null, 1);
        Bridge.INSTANCE.nativeHealthCheck(CompletableDeferred$default, str);
        Object await = ((CompletableDeferredImpl) CompletableDeferred$default).await(continuation);
        return await == CoroutineSingletons.COROUTINE_SUSPENDED ? await : Unit.INSTANCE;
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void patchOverride(Clash.OverrideSlot overrideSlot, ConfigurationOverride configurationOverride) {
        Clash clash = Clash.INSTANCE;
        Bridge bridge = Bridge.INSTANCE;
        int ordinal = overrideSlot.ordinal();
        Json json = Clash.ConfigurationOverrideJson;
        Objects.requireNonNull(ConfigurationOverride.CREATOR);
        ConfigurationOverride$$serializer configurationOverride$$serializer = ConfigurationOverride$$serializer.INSTANCE;
        Objects.requireNonNull(json);
        JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
        try {
            WriteMode writeMode = WriteMode.OBJ;
            WriteMode.values();
            new StreamingJsonEncoder(new Composer(jsonStringBuilder, json), json, writeMode, new JsonEncoder[4]).encodeSerializableValue(configurationOverride$$serializer, configurationOverride);
            String jsonStringBuilder2 = jsonStringBuilder.toString();
            jsonStringBuilder.release();
            bridge.nativeWriteOverride(ordinal, jsonStringBuilder2);
            Context context = this.context;
            Intents intents = Intents.INSTANCE;
            InputKt.sendBroadcastSelf(context, new Intent(Intents.ACTION_OVERRIDE_CHANGED));
        } catch (Throwable th) {
            jsonStringBuilder.release();
            throw th;
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public boolean patchSelector(String str, String str2) {
        Clash clash = Clash.INSTANCE;
        boolean nativePatchSelector = Bridge.INSTANCE.nativePatchSelector(str, str2);
        UUID activeProfile = this.store.getActiveProfile();
        if (activeProfile != null) {
            if (nativePatchSelector) {
                InputKt.SelectionDao().setSelected(new Selection(activeProfile, str, str2));
            } else {
                InputKt.SelectionDao().removeSelected(activeProfile, str);
            }
        }
        return nativePatchSelector;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public UiConfiguration queryConfiguration() {
        Clash clash = Clash.INSTANCE;
        Json.Default r0 = Json.Default;
        Objects.requireNonNull(UiConfiguration.CREATOR);
        return (UiConfiguration) r0.decodeFromString(UiConfiguration$$serializer.INSTANCE, Bridge.INSTANCE.nativeQueryConfiguration());
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ConfigurationOverride queryOverride(Clash.OverrideSlot overrideSlot) {
        Clash clash = Clash.INSTANCE;
        try {
            Json json = Clash.ConfigurationOverrideJson;
            Objects.requireNonNull(ConfigurationOverride.CREATOR);
            return (ConfigurationOverride) json.decodeFromString(ConfigurationOverride$$serializer.INSTANCE, Bridge.INSTANCE.nativeReadOverride(overrideSlot.ordinal()));
        } catch (Exception unused) {
            return new ConfigurationOverride((Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (List) null, (Boolean) null, (String) null, (TunnelState.Mode) null, (LogMessage.Level) null, (Boolean) null, (Map) null, (ConfigurationOverride.Dns) null, (ConfigurationOverride.App) null, 16383);
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProviderList queryProviders() {
        JsonDecoder jsonDecoder;
        Clash clash = Clash.INSTANCE;
        JsonArray jsonArray = (JsonArray) Json.Default.decodeFromString(JsonArray.Companion.serializer(), Bridge.INSTANCE.nativeQueryProviders());
        int size = jsonArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Json.Default r6 = Json.Default;
            Objects.requireNonNull(Provider.CREATOR);
            Provider$$serializer provider$$serializer = Provider$$serializer.INSTANCE;
            JsonElement jsonElement = jsonArray.get2(i);
            Objects.requireNonNull(r6);
            if (jsonElement instanceof JsonObject) {
                jsonDecoder = new JsonTreeDecoder(r6, (JsonObject) jsonElement, null, null, 12);
            } else if (jsonElement instanceof JsonArray) {
                jsonDecoder = new JsonTreeListDecoder(r6, (JsonArray) jsonElement);
            } else if (jsonElement instanceof JsonLiteral ? true : Intrinsics.areEqual(jsonElement, JsonNull.INSTANCE)) {
                jsonDecoder = new JsonPrimitiveDecoder(r6, (JsonPrimitive) jsonElement);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            arrayList.add((Provider) PolymorphicKt.decodeSerializableValuePolymorphic(jsonDecoder, provider$$serializer));
        }
        return new ProviderList(arrayList);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public ProxyGroup queryProxyGroup(String str, ProxySort proxySort) {
        ProxyGroup proxyGroup;
        Clash clash = Clash.INSTANCE;
        String nativeQueryGroup = Bridge.INSTANCE.nativeQueryGroup(str, proxySort.name());
        if (nativeQueryGroup == null) {
            proxyGroup = null;
        } else {
            Json.Default r4 = Json.Default;
            Objects.requireNonNull(ProxyGroup.CREATOR);
            proxyGroup = (ProxyGroup) r4.decodeFromString(ProxyGroup$$serializer.INSTANCE, nativeQueryGroup);
        }
        return proxyGroup == null ? new ProxyGroup(Proxy.Type.Unknown, EmptyList.INSTANCE, HttpUrl.FRAGMENT_ENCODE_SET) : proxyGroup;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public List<String> queryProxyGroupNames(boolean z) {
        Clash clash = Clash.INSTANCE;
        JsonArray jsonArray = (JsonArray) Json.Default.decodeFromString(JsonArray.Companion.serializer(), Bridge.INSTANCE.nativeQueryGroupNames(z));
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(jsonArray, 10));
        Iterator<JsonElement> it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement next = it.next();
            if (InputKt.getJsonPrimitive(next).isString()) {
                arrayList.add(InputKt.getJsonPrimitive(next).getContent());
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        return arrayList;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public long queryTrafficTotal() {
        Clash clash = Clash.INSTANCE;
        return Bridge.INSTANCE.nativeQueryTrafficTotal();
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public TunnelState queryTunnelState() {
        Clash clash = Clash.INSTANCE;
        String nativeQueryTunnelState = Bridge.INSTANCE.nativeQueryTunnelState();
        Json.Default r1 = Json.Default;
        Objects.requireNonNull(TunnelState.CREATOR);
        return (TunnelState) r1.decodeFromString(TunnelState$$serializer.INSTANCE, nativeQueryTunnelState);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public void setLogObserver(ILogObserver iLogObserver) {
        synchronized (this) {
            ReceiveChannel<LogMessage> receiveChannel = this.logReceiver;
            if (receiveChannel != null) {
                receiveChannel.cancel(null);
                Clash clash = Clash.INSTANCE;
                Bridge.INSTANCE.nativeForceGc();
            }
            if (iLogObserver != null) {
                Clash clash2 = Clash.INSTANCE;
                final Channel Channel$default = InputKt.Channel$default(32, null, null, 6);
                Bridge.INSTANCE.nativeSubscribeLogcat(new LogcatInterface() { // from class: com.tidalab.v2board.clash.core.Clash$subscribeLogcat$1$1
                    @Override // com.tidalab.v2board.clash.core.bridge.LogcatInterface
                    public void received(String str) {
                        Channel$default.mo14trySendJP2dKIU(Json.Default.decodeFromString(LogMessage$$serializer.INSTANCE, str));
                    }
                });
                InputKt.launch$default(this, null, null, new ClashManager$setLogObserver$1$2$1(iLogObserver, Channel$default, null), 3, null);
                Unit unit = Unit.INSTANCE;
                this.logReceiver = Channel$default;
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IClashManager
    public Object updateProvider(Provider.Type type, String str, Continuation<? super Unit> continuation) {
        Clash clash = Clash.INSTANCE;
        CompletableDeferred<Unit> CompletableDeferred$default = InputKt.CompletableDeferred$default(null, 1);
        Bridge.INSTANCE.nativeUpdateProvider(CompletableDeferred$default, type.toString(), str);
        Object await = ((CompletableDeferredImpl) CompletableDeferred$default).await(continuation);
        return await == CoroutineSingletons.COROUTINE_SUSPENDED ? await : Unit.INSTANCE;
    }
}
