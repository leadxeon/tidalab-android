package com.tidalab.v2board.clash.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tidalab.v2board.clash.common.Global;
import java.io.File;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: StatusProvider.kt */
/* loaded from: classes.dex */
public final class StatusProvider extends ContentProvider {
    public static String currentProfile;
    public static boolean serviceRunning;

    public static final void setServiceRunning(boolean z) {
        serviceRunning = z;
        File resolve = FilesKt__UtilsKt.resolve(Global.INSTANCE.getApplication().getFilesDir(), "service_running.lock");
        if (z) {
            resolve.createNewFile();
        } else {
            resolve.delete();
        }
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (!Intrinsics.areEqual(str, "currentProfile")) {
            return super.call(str, str2, bundle);
        }
        if (!serviceRunning) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", currentProfile);
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalArgumentException("Stub!");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new IllegalArgumentException("Stub!");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalArgumentException("Stub!");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new IllegalArgumentException("Stub!");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalArgumentException("Stub!");
    }
}
