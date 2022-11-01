package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
/* loaded from: classes.dex */
public class MimeTypeMapWrapper {
    public static final MimeTypeMap sMimeTypeMap = MimeTypeMap.getSingleton();
    public static final Map<String, String> sMimeTypeToExtensionMap = ImmutableMap.of("image/heif", "heif", "image/heic", "heic");
    public static final Map<String, String> sExtensionToMimeTypeMap = ImmutableMap.of("heif", "image/heif", "heic", "image/heic");
}
