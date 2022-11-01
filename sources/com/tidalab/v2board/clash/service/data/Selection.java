package com.tidalab.v2board.clash.service.data;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Selection.kt */
/* loaded from: classes.dex */
public final class Selection {
    public final String proxy;
    public final String selected;
    public final UUID uuid;

    public Selection(UUID uuid, String str, String str2) {
        this.uuid = uuid;
        this.proxy = str;
        this.selected = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Selection)) {
            return false;
        }
        Selection selection = (Selection) obj;
        return Intrinsics.areEqual(this.uuid, selection.uuid) && Intrinsics.areEqual(this.proxy, selection.proxy) && Intrinsics.areEqual(this.selected, selection.selected);
    }

    public int hashCode() {
        return this.selected.hashCode() + GeneratedOutlineSupport.outline1(this.proxy, this.uuid.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Selection(uuid=");
        outline13.append(this.uuid);
        outline13.append(", proxy=");
        outline13.append(this.proxy);
        outline13.append(", selected=");
        outline13.append(this.selected);
        outline13.append(')');
        return outline13.toString();
    }
}
