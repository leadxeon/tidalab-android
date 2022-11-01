package com.tidalab.v2board.clash.service.util;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Net.kt */
/* loaded from: classes.dex */
public final class IPNet {
    public final String ip;
    public final int prefix;

    public IPNet(String str, int i) {
        this.ip = str;
        this.prefix = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IPNet)) {
            return false;
        }
        IPNet iPNet = (IPNet) obj;
        return Intrinsics.areEqual(this.ip, iPNet.ip) && this.prefix == iPNet.prefix;
    }

    public int hashCode() {
        return (this.ip.hashCode() * 31) + this.prefix;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("IPNet(ip=");
        outline13.append(this.ip);
        outline13.append(", prefix=");
        outline13.append(this.prefix);
        outline13.append(')');
        return outline13.toString();
    }
}
