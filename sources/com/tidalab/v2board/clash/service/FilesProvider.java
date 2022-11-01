package com.tidalab.v2board.clash.service;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import com.facebook.react.modules.dialog.DialogModule;
import com.tidalab.v2board.clash.common.util.PatternsKt;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.document.Document;
import com.tidalab.v2board.clash.service.document.Flag;
import com.tidalab.v2board.clash.service.document.Picker;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import okhttp3.HttpUrl;
/* compiled from: FilesProvider.kt */
/* loaded from: classes.dex */
public final class FilesProvider extends DocumentsProvider {
    public static final String[] DEFAULT_DOCUMENT_COLUMNS = {"document_id", "_display_name", "mime_type", "last_modified", "_size", "flags"};
    public static final String[] DEFAULT_ROOT_COLUMNS = {"root_id", "flags", "icon", DialogModule.KEY_TITLE, "summary", "document_id"};
    public static final int FLAG_VIRTUAL;
    public final Lazy picker$delegate = InputKt.lazy(new FilesProvider$picker$2(this));

    static {
        FLAG_VIRTUAL = Build.VERSION.SDK_INT >= 24 ? 512 : 0;
    }

    public static final MatrixCursor.RowBuilder access$applyDocument(FilesProvider filesProvider, MatrixCursor.RowBuilder rowBuilder, Document document) {
        Objects.requireNonNull(filesProvider);
        int i = 0;
        for (Flag flag : document.getFlags()) {
            int ordinal = flag.ordinal();
            if (ordinal == 0) {
                i |= 2;
            } else if (ordinal == 1) {
                i |= 4;
            } else if (ordinal == 2) {
                i |= FLAG_VIRTUAL;
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        rowBuilder.add("_display_name", document.getName());
        rowBuilder.add("mime_type", document.getMimeType());
        rowBuilder.add("last_modified", Long.valueOf(document.getUpdatedAt()));
        rowBuilder.add("_size", Long.valueOf(document.getSize()));
        rowBuilder.add("flags", Integer.valueOf(i));
        return rowBuilder;
    }

    public static final Picker access$getPicker(FilesProvider filesProvider) {
        return (Picker) filesProvider.picker$delegate.getValue();
    }

    @Override // android.provider.DocumentsProvider
    public void deleteDocument(String str) {
        InputKt.runBlocking$default(null, new FilesProvider$deleteDocument$1(str == null ? "/" : str, str, this, null), 1, null);
    }

    @Override // android.provider.DocumentsProvider
    public boolean isChildDocument(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return StringsKt__IndentKt.startsWith$default(str2, str, false, 2);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.provider.DocumentsProvider
    public ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) {
        return (ParcelFileDescriptor) InputKt.runBlocking$default(null, new FilesProvider$openDocument$1(str, this, str2, ParcelFileDescriptor.parseMode(str2), null), 1, null);
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryChildDocuments(String str, String[] strArr, String str2) {
        return (Cursor) InputKt.runBlocking$default(null, new FilesProvider$queryChildDocuments$1(str, this, strArr, null), 1, null);
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryDocument(String str, String[] strArr) {
        return (Cursor) InputKt.runBlocking$default(null, new FilesProvider$queryDocument$1(str, this, strArr, null), 1, null);
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryRoots(String[] strArr) {
        if (strArr == null) {
            strArr = DEFAULT_ROOT_COLUMNS;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add("root_id", "0");
        newRow.add("flags", 18);
        newRow.add("icon", Integer.valueOf((int) R.drawable.ic_logo_service));
        newRow.add(DialogModule.KEY_TITLE, getContext().getString(R.string.clash_for_android));
        newRow.add("summary", getContext().getString(R.string.profiles_and_providers));
        newRow.add("document_id", "/");
        newRow.add("mime_types", "vnd.android.document/directory");
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public String renameDocument(String str, String str2) {
        String str3 = str2 == null ? HttpUrl.FRAGMENT_ENCODE_SET : str2;
        if (PatternsKt.PatternFileName.nativePattern.matcher(str3).matches()) {
            return (String) InputKt.runBlocking$default(null, new FilesProvider$renameDocument$1(str, this, str3, null), 1, null);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("invalid name ", str2));
    }
}
