package com.tidalab.v2board.clash.service.document;

import java.io.File;
import java.util.Set;
/* compiled from: FileDocument.kt */
/* loaded from: classes.dex */
public final class FileDocument implements Document {
    public final File file;
    public final Set<Flag> flags;
    public final String idOverride;
    public final String nameOverride;

    /* JADX WARN: Multi-variable type inference failed */
    public FileDocument(File file, Set<? extends Flag> set, String str, String str2) {
        this.file = file;
        this.flags = set;
        this.idOverride = str;
        this.nameOverride = str2;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public Set<Flag> getFlags() {
        return this.flags;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getId() {
        String str = this.idOverride;
        return str == null ? this.file.getName() : str;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getMimeType() {
        return this.file.isDirectory() ? "vnd.android.document/directory" : "text/plain";
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public String getName() {
        String str = this.nameOverride;
        return str == null ? this.file.getName() : str;
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public long getSize() {
        return this.file.length();
    }

    @Override // com.tidalab.v2board.clash.service.document.Document
    public long getUpdatedAt() {
        return this.file.lastModified();
    }

    public FileDocument(File file, Set set, String str, String str2, int i) {
        int i2 = i & 4;
        int i3 = i & 8;
        this.file = file;
        this.flags = set;
        this.idOverride = null;
        this.nameOverride = null;
    }
}
