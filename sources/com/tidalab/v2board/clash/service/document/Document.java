package com.tidalab.v2board.clash.service.document;

import java.util.Set;
/* compiled from: Document.kt */
/* loaded from: classes.dex */
public interface Document {
    Set<Flag> getFlags();

    String getId();

    String getMimeType();

    String getName();

    long getSize();

    long getUpdatedAt();
}
