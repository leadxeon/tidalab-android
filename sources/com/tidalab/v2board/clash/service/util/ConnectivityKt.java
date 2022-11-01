package com.tidalab.v2board.clash.service.util;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Connectivity.kt */
/* loaded from: classes.dex */
public final class ConnectivityKt {
    public static final List<String> resolveDns(ConnectivityManager connectivityManager, Network network) {
        List<InetAddress> dnsServers;
        String str;
        ArrayList arrayList = null;
        LinkProperties linkProperties = network == null ? null : connectivityManager.getLinkProperties(network);
        if (!(linkProperties == null || (dnsServers = linkProperties.getDnsServers()) == null)) {
            arrayList = new ArrayList(InputKt.collectionSizeOrDefault(dnsServers, 10));
            for (InetAddress inetAddress : dnsServers) {
                if (inetAddress instanceof Inet6Address) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    byte[] address = ((Inet6Address) inetAddress).getAddress();
                    StringBuilder sb2 = new StringBuilder(39);
                    int i = 0;
                    while (true) {
                        int i2 = i + 1;
                        int i3 = i << 1;
                        sb2.append(Integer.toHexString((address[i3 + 1] & 255) | ((address[i3] << 8) & 65280)));
                        if (i < 7) {
                            sb2.append(":");
                        }
                        if (i2 >= 8) {
                            break;
                        }
                        i = i2;
                    }
                    sb.append(sb2.toString());
                    sb.append("]:");
                    sb.append(53);
                    str = sb.toString();
                } else if (inetAddress instanceof Inet4Address) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append((Object) ((Inet4Address) inetAddress).getHostAddress());
                    sb3.append(':');
                    sb3.append(53);
                    str = sb3.toString();
                } else {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported Inet type ", inetAddress.getClass()));
                }
                arrayList.add(str);
            }
        }
        return arrayList == null ? EmptyList.INSTANCE : arrayList;
    }
}
