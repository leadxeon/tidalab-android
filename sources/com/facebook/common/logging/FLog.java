package com.facebook.common.logging;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.PrintWriter;
import java.io.StringWriter;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class FLog {
    public static final /* synthetic */ int $r8$clinit = 0;

    public static void d(String str, String str2) {
    }

    public static void e(String str, String str2) {
        Log.println(6, "unknown:" + str, str2);
    }

    public static String formatString(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    public static boolean isLoggable(int i) {
        return 5 <= i;
    }

    public static void v(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (isLoggable(2)) {
            v(cls, formatString(str, obj, obj2, obj3));
        }
    }

    public static void w(String str, String str2) {
        Log.println(5, "unknown:" + str, str2);
    }

    public static void wtf(String str, String str2, Object... objArr) {
        String formatString = formatString(str2, objArr);
        Log.println(6, "unknown:" + str, formatString);
    }

    public static void e(String str, String str2, Throwable th) {
        String str3;
        if (1 != 0) {
            String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", str);
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, '\n');
            if (th == null) {
                str3 = HttpUrl.FRAGMENT_ENCODE_SET;
            } else {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                str3 = stringWriter.toString();
            }
            outline14.append(str3);
            Log.println(6, outline9, outline14.toString());
        }
    }

    public static void v(Class<?> cls, String str) {
        if (0 != 0) {
            String simpleName = cls.getSimpleName();
            Log.println(2, "unknown:" + simpleName, str);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", str);
        StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, '\n');
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        outline14.append(stringWriter.toString());
        Log.println(5, outline9, outline14.toString());
    }

    public static void e(Class<?> cls, String str) {
        String simpleName = cls.getSimpleName();
        Log.println(6, "unknown:" + simpleName, str);
    }

    public static void w(String str, String str2, Object... objArr) {
        String formatString = formatString(str2, objArr);
        Log.println(5, "unknown:" + str, formatString);
    }

    public static void e(Class<?> cls, String str, Throwable th) {
        String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", cls.getSimpleName());
        StringBuilder outline14 = GeneratedOutlineSupport.outline14(str, '\n');
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        outline14.append(stringWriter.toString());
        Log.println(6, outline9, outline14.toString());
    }

    public static void w(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (isLoggable(5)) {
            String formatString = formatString(str, objArr);
            String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", cls.getSimpleName());
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(formatString, '\n');
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            outline14.append(stringWriter.toString());
            Log.println(5, outline9, outline14.toString());
        }
    }

    public static void e(Class<?> cls, String str, Object... objArr) {
        String simpleName = cls.getSimpleName();
        String formatString = formatString(str, objArr);
        Log.println(6, "unknown:" + simpleName, formatString);
    }

    public static void w(Class<?> cls, String str) {
        String simpleName = cls.getSimpleName();
        Log.println(5, "unknown:" + simpleName, str);
    }

    public static void w(Class<?> cls, String str, Object... objArr) {
        String simpleName = cls.getSimpleName();
        String formatString = formatString(str, objArr);
        Log.println(5, "unknown:" + simpleName, formatString);
    }
}
