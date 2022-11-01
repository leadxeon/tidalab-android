package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
/* compiled from: NetworkObserveModule.kt */
/* loaded from: classes.dex */
public final class NetworkObserveModule extends Module<NetworkChanged> {
    public final NetworkObserveModule$callback$1 callback;
    public final ConnectivityManager connectivity;
    public final Channel<Network> networks = InputKt.Channel$default(-1, null, null, 6);
    public final NetworkRequest request;

    /* compiled from: NetworkObserveModule.kt */
    /* loaded from: classes.dex */
    public static final class NetworkChanged {
        public final Network network;

        public NetworkChanged(Network network) {
            this.network = network;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof NetworkChanged) && Intrinsics.areEqual(this.network, ((NetworkChanged) obj).network);
        }

        public int hashCode() {
            Network network = this.network;
            if (network == null) {
                return 0;
            }
            return network.hashCode();
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("NetworkChanged(network=");
            outline13.append(this.network);
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r3v7, types: [com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule$callback$1] */
    public NetworkObserveModule(Service service) {
        super(service);
        this.connectivity = (ConnectivityManager) ContextCompat.getSystemService(service, ConnectivityManager.class);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addCapability(12);
        builder.addCapability(13);
        if (Build.VERSION.SDK_INT == 23) {
            builder.removeCapability(16);
            builder.removeCapability(17);
        }
        Unit unit = Unit.INSTANCE;
        this.request = builder.build();
        this.callback = new ConnectivityManager.NetworkCallback() { // from class: com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule$callback$1
            public boolean internet;
            public Network network;

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                this.network = network;
                NetworkObserveModule.this.networks.mo14trySendJP2dKIU(network);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                boolean hasCapability = networkCapabilities.hasCapability(12);
                if (Intrinsics.areEqual(this.network, network) && this.internet != hasCapability) {
                    this.internet = hasCapability;
                    NetworkObserveModule.this.networks.mo14trySendJP2dKIU(network);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                if (Intrinsics.areEqual(this.network, network)) {
                    NetworkObserveModule.this.networks.mo14trySendJP2dKIU(network);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                if (Intrinsics.areEqual(this.network, network)) {
                    NetworkObserveModule.this.networks.mo14trySendJP2dKIU(null);
                }
            }
        };
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ba A[RETURN] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00b8 -> B:35:0x0088). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
