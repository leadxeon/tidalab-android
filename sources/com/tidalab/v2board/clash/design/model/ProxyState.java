package com.tidalab.v2board.clash.design.model;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProxyState.kt */
/* loaded from: classes.dex */
public final class ProxyState {
    public String now;

    public ProxyState(String str) {
        this.now = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ProxyState) && Intrinsics.areEqual(this.now, ((ProxyState) obj).now);
    }

    public int hashCode() {
        return this.now.hashCode();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ProxyState(now=");
        outline13.append(this.now);
        outline13.append(')');
        return outline13.toString();
    }
}
