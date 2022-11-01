package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;
import com.android.tools.r8.GeneratedOutlineSupport;
@DoNotOptimize
@TargetApi(18)
/* loaded from: classes.dex */
public class Api18TraceUtils {
    public static void beginTraceSection(String str, String str2, String str3) {
        String outline9 = GeneratedOutlineSupport.outline9(str, str2, str3);
        if (outline9.length() > 127 && str2 != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
            outline13.append(str2.substring(0, (127 - str.length()) - str3.length()));
            outline13.append(str3);
            outline9 = outline13.toString();
        }
        Trace.beginSection(outline9);
    }
}
