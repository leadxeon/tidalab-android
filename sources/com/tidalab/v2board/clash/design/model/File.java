package com.tidalab.v2board.clash.design.model;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.Provider$$ExternalSynthetic0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: File.kt */
/* loaded from: classes.dex */
public final class File {
    public final String id;
    public final boolean isDirectory;
    public final long lastModified;
    public final String name;
    public final long size;

    public File(String str, String str2, long j, long j2, boolean z) {
        this.id = str;
        this.name = str2;
        this.size = j;
        this.lastModified = j2;
        this.isDirectory = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof File)) {
            return false;
        }
        File file = (File) obj;
        return Intrinsics.areEqual(this.id, file.id) && Intrinsics.areEqual(this.name, file.name) && this.size == file.size && this.lastModified == file.lastModified && this.isDirectory == file.isDirectory;
    }

    public int hashCode() {
        int outline1 = GeneratedOutlineSupport.outline1(this.name, this.id.hashCode() * 31, 31);
        int m0 = (Provider$$ExternalSynthetic0.m0(this.lastModified) + ((Provider$$ExternalSynthetic0.m0(this.size) + outline1) * 31)) * 31;
        boolean z = this.isDirectory;
        if (z) {
            z = true;
        }
        int i = z ? 1 : 0;
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        return m0 + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("File(id=");
        outline13.append(this.id);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", size=");
        outline13.append(this.size);
        outline13.append(", lastModified=");
        outline13.append(this.lastModified);
        outline13.append(", isDirectory=");
        outline13.append(this.isDirectory);
        outline13.append(')');
        return outline13.toString();
    }
}
