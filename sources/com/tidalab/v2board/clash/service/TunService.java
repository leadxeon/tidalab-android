package com.tidalab.v2board.clash.service;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ProxyInfo;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationManagerCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Components;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.common.store.Store;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.core.bridge.TunInterface;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.clash.ClashRuntime;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeKt;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1;
import com.tidalab.v2board.clash.service.clash.module.TunModule;
import com.tidalab.v2board.clash.service.clash.module.TunModule$attach$1;
import com.tidalab.v2board.clash.service.clash.module.TunModule$attach$2;
import com.tidalab.v2board.clash.service.model.AccessControlMode;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import com.tidalab.v2board.clash.service.util.IPNet;
import java.net.InetSocketAddress;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
/* compiled from: TunService.kt */
/* loaded from: classes.dex */
public final class TunService extends VpnService implements CoroutineScope {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.Default);
    public String reason;
    public final ClashRuntime runtime;

    public TunService() {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        TunService$runtime$1 tunService$runtime$1 = new TunService$runtime$1(this, null);
        Mutex mutex = ClashRuntimeKt.globalLock;
        this.runtime = new ClashRuntimeKt$clashRuntime$1(this, tunService$runtime$1);
    }

    public static final void access$open(TunService tunService, TunModule tunModule) {
        List list;
        boolean z;
        Objects.requireNonNull(tunService);
        ServiceStore serviceStore = new ServiceStore(tunService);
        VpnService.Builder builder = new VpnService.Builder(tunService);
        builder.addAddress("172.19.0.1", 30);
        if (serviceStore.getBypassPrivateNetwork()) {
            String[] stringArray = tunService.getResources().getStringArray(R.array.bypass_private_route);
            ArrayList arrayList = new ArrayList(stringArray.length);
            for (String str : stringArray) {
                List split$default = StringsKt__IndentKt.split$default(str, new String[]{"/"}, false, 2, 2);
                if (split$default.size() == 2) {
                    arrayList.add(new IPNet((String) split$default.get(0), Integer.parseInt((String) split$default.get(1))));
                } else {
                    throw new IllegalArgumentException("Invalid address");
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                IPNet iPNet = (IPNet) it.next();
                builder.addRoute(iPNet.ip, iPNet.prefix);
            }
            builder.addRoute("172.19.0.2", 32);
        } else {
            builder.addRoute("0.0.0.0", 0);
        }
        int ordinal = ((AccessControlMode) serviceStore.accessControlMode$delegate.getValue(serviceStore, ServiceStore.$$delegatedProperties[2])).ordinal();
        if (ordinal == 1) {
            Set<String> accessControlPackages = serviceStore.getAccessControlPackages();
            String packageName = tunService.getPackageName();
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet(InputKt.mapCapacity(accessControlPackages.size() + 1));
            linkedHashSet.addAll(accessControlPackages);
            linkedHashSet.add(packageName);
            for (String str2 : linkedHashSet) {
                try {
                    builder.addAllowedApplication(str2);
                } catch (Throwable unused) {
                }
            }
        } else if (ordinal == 2) {
            Set<String> accessControlPackages2 = serviceStore.getAccessControlPackages();
            String packageName2 = tunService.getPackageName();
            LinkedHashSet<String> linkedHashSet2 = new LinkedHashSet(InputKt.mapCapacity(accessControlPackages2.size()));
            boolean z2 = false;
            for (Object obj : accessControlPackages2) {
                if (z2 || !Intrinsics.areEqual(obj, packageName2)) {
                    z = true;
                } else {
                    z2 = true;
                    z = false;
                }
                if (z) {
                    linkedHashSet2.add(obj);
                }
            }
            for (String str3 : linkedHashSet2) {
                try {
                    builder.addDisallowedApplication(str3);
                } catch (Throwable unused2) {
                }
            }
        }
        builder.setBlocking(false);
        builder.setMtu(9000);
        builder.setSession("Clash");
        builder.addDnsServer("172.19.0.2");
        Intent intent = new Intent();
        Components components = Components.INSTANCE;
        builder.setConfigureIntent(PendingIntent.getActivity(tunService, R.id.nf_vpn_status, intent.setComponent(Components.MAIN_ACTIVITY), PathParser.pendingIntentFlags$default(134217728, false, 2)));
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            builder.setMetered(false);
        }
        Integer num = null;
        if (i >= 29 && ((Boolean) serviceStore.systemProxy$delegate.getValue(serviceStore, ServiceStore.$$delegatedProperties[5])).booleanValue()) {
            Objects.requireNonNull(tunModule);
            StringBuilder sb = new StringBuilder();
            sb.append("127.");
            SecureRandom secureRandom = TunModule.random;
            sb.append(Integer.valueOf(secureRandom.nextInt(199) + 1).intValue());
            sb.append('.');
            sb.append(Integer.valueOf(secureRandom.nextInt(199) + 1).intValue());
            sb.append('.');
            sb.append(Integer.valueOf(secureRandom.nextInt(199) + 1).intValue());
            sb.append(":0");
            String sb2 = sb.toString();
            Clash clash = Clash.INSTANCE;
            String nativeStartHttp = Bridge.INSTANCE.nativeStartHttp(sb2);
            InetSocketAddress parseInetSocketAddress = nativeStartHttp == null ? null : PathParser.parseInetSocketAddress(nativeStartHttp);
            if (parseInetSocketAddress != null) {
                String hostAddress = parseInetSocketAddress.getAddress().getHostAddress();
                int port = parseInetSocketAddress.getPort();
                if (serviceStore.getBypassPrivateNetwork()) {
                    list = ArraysKt___ArraysKt.listOf("localhost", "*.local", "127.*", "10.*", "172.16.*", "172.17.*", "172.18.*", "172.19.*", "172.2*", "172.30.*", "172.31.*", "192.168.*");
                } else {
                    list = EmptyList.INSTANCE;
                }
                builder.setHttpProxy(ProxyInfo.buildDirectProxy(hostAddress, port, list));
            }
        }
        ArrayList arrayList2 = new ArrayList(new ArrayAsCollection(new String[]{"172.19.0.1/30"}, true));
        Store.Delegate delegate = serviceStore.blockLoopback$delegate;
        KProperty<?>[] kPropertyArr = ServiceStore.$$delegatedProperties;
        if (((Boolean) delegate.getValue(serviceStore, kPropertyArr[6])).booleanValue()) {
            arrayList2.add("127.0.0.0/8");
        }
        ParcelFileDescriptor establish = builder.establish();
        if (establish != null) {
            num = Integer.valueOf(establish.detachFd());
        }
        Objects.requireNonNull(num, "Establish VPN rejected by system");
        int intValue = num.intValue();
        String joinToString$default = ArraysKt___ArraysKt.joinToString$default(arrayList2, ";", null, null, 0, null, null, 62);
        String str4 = ((Boolean) serviceStore.dnsHijacking$delegate.getValue(serviceStore, kPropertyArr[4])).booleanValue() ? "0.0.0.0" : "172.19.0.2";
        Objects.requireNonNull(tunModule);
        Clash clash2 = Clash.INSTANCE;
        final TunModule$attach$1 tunModule$attach$1 = new TunModule$attach$1(tunModule.vpn);
        final TunModule$attach$2 tunModule$attach$2 = new TunModule$attach$2(tunModule);
        Bridge.INSTANCE.nativeStartTun(intValue, 9000, str4, joinToString$default, new TunInterface() { // from class: com.tidalab.v2board.clash.core.Clash$startTun$1
            @Override // com.tidalab.v2board.clash.core.bridge.TunInterface
            public void markSocket(int i2) {
                tunModule$attach$1.invoke(Integer.valueOf(i2));
            }

            @Override // com.tidalab.v2board.clash.core.bridge.TunInterface
            public int querySocketUid(int i2, String str5, String str6) {
                return tunModule$attach$2.invoke(Integer.valueOf(i2), PathParser.parseInetSocketAddress(str5), PathParser.parseInetSocketAddress(str6)).intValue();
            }
        });
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (StatusProvider.serviceRunning) {
            stopSelf();
            return;
        }
        StatusProvider.setServiceRunning(true);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationManagerCompat notificationManagerCompat = new NotificationManagerCompat(this);
            NotificationChannel notificationChannel = new NotificationChannel("clash_status_channel", getText(R.string.clash_service_status_channel), 2);
            if (i >= 26) {
                notificationManagerCompat.mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this, "clash_status_channel");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.setFlag(2, true);
        notificationCompat$Builder.mColor = PathParser.getColorCompat(this, R.color.color_clash);
        notificationCompat$Builder.setFlag(8, true);
        notificationCompat$Builder.mShowWhen = false;
        notificationCompat$Builder.setContentTitle(getText(R.string.loading));
        startForeground(R.id.nf_clash_status, notificationCompat$Builder.build());
        this.runtime.launch();
    }

    @Override // android.app.Service
    public void onDestroy() {
        TunModule tunModule = TunModule.Companion;
        Clash clash = Clash.INSTANCE;
        Bridge bridge = Bridge.INSTANCE;
        bridge.nativeStopHttp();
        bridge.nativeStopTun();
        StatusProvider.setServiceRunning(false);
        String str = this.reason;
        Intents intents = Intents.INSTANCE;
        InputKt.sendBroadcastSelf(this, new Intent(Intents.ACTION_CLASH_STOPPED).putExtra("stop_reason", str));
        InputKt.cancelAndJoinBlocking(this);
        String str2 = this.reason;
        if (str2 == null) {
            str2 = "successfully";
        }
        Log.i("ClashForAndroid", Intrinsics.stringPlus("TunService destroyed: ", str2), null);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Intents intents = Intents.INSTANCE;
        InputKt.sendBroadcastSelf(this, new Intent(Intents.ACTION_CLASH_STARTED));
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.runtime.requestGc();
    }
}
