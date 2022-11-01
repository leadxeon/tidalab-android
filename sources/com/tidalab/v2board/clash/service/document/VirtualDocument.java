package com.tidalab.v2board.clash.service.document;

import java.util.Set;
/* compiled from: VirtualDocument.kt */
/* loaded from: classes.dex */
public final class VirtualDocument implements Document {
    public final Set<Flag> flags;
    public final String id;
    public final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public VirtualDocument(String str, String str2, String str3, long j, long j2, Set<? extends Flag> set) {
        this.id = str;
        this.name = str2;
        this.flags = set;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public Set<Flag> getFlags() {
        return this.flags;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getId() {
        return this.id;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getMimeType() {
        return "vnd.android.document/directory";
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getName() {
        return this.name;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public long getSize() {
        return 0L;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public long getUpdatedAt() {
        return 0L;
    }
}
