package com.tidalab.v2board.clash.remote;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import com.tidalab.v2board.clash.common.constants.Authorities;
/* compiled from: FilesClient.kt */
/* loaded from: classes.dex */
public final class FilesClient {
    public static final String[] FilesProjection = {"document_id", "_display_name", "_size", "last_modified", "mime_type"};
    public final Context context;

    public FilesClient(Context context) {
        this.context = context;
    }

    public final Uri buildDocumentUri(String str) {
        Authorities authorities = Authorities.INSTANCE;
        return DocumentsContract.buildDocumentUri(Authorities.FILES_PROVIDER, str);
    }
}
