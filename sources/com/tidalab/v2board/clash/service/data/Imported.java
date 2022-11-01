package com.tidalab.v2board.clash.service.data;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.Provider$$ExternalSynthetic0;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Imported.kt */
/* loaded from: classes.dex */
public final class Imported {
    public final long createdAt;
    public final long interval;
    public final String name;
    public final String source;
    public final Profile.Type type;
    public final UUID uuid;

    public Imported(UUID uuid, String str, Profile.Type type, String str2, long j, long j2) {
        this.uuid = uuid;
        this.name = str;
        this.type = type;
        this.source = str2;
        this.interval = j;
        this.createdAt = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Imported)) {
            return false;
        }
        Imported imported = (Imported) obj;
        return Intrinsics.areEqual(this.uuid, imported.uuid) && Intrinsics.areEqual(this.name, imported.name) && this.type == imported.type && Intrinsics.areEqual(this.source, imported.source) && this.interval == imported.interval && this.createdAt == imported.createdAt;
    }

    public int hashCode() {
        int outline1 = GeneratedOutlineSupport.outline1(this.name, this.uuid.hashCode() * 31, 31);
        int outline12 = GeneratedOutlineSupport.outline1(this.source, (this.type.hashCode() + outline1) * 31, 31);
        return Provider$$ExternalSynthetic0.m0(this.createdAt) + ((Provider$$ExternalSynthetic0.m0(this.interval) + outline12) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Imported(uuid=");
        outline13.append(this.uuid);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", source=");
        outline13.append(this.source);
        outline13.append(", interval=");
        outline13.append(this.interval);
        outline13.append(", createdAt=");
        outline13.append(this.createdAt);
        outline13.append(')');
        return outline13.toString();
    }
}
