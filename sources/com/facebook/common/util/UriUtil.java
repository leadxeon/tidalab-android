package com.facebook.common.util;

import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
/* loaded from: classes.dex */
public class UriUtil {
    public static final Uri LOCAL_CONTACT_IMAGE_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");

    public static String getSchemeOrNull(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }

    public static boolean isLocalCameraUri(Uri uri) {
        String uri2 = uri.toString();
        return uri2.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || uri2.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean isLocalContentUri(Uri uri) {
        return "content".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalFileUri(Uri uri) {
        return "file".equals(getSchemeOrNull(uri));
    }

    public static boolean isNetworkUri(Uri uri) {
        String schemeOrNull = getSchemeOrNull(uri);
        return "https".equals(schemeOrNull) || "http".equals(schemeOrNull);
    }
}
