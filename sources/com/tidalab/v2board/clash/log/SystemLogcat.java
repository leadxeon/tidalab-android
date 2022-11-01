package com.tidalab.v2board.clash.log;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__IndentKt;
import okhttp3.HttpUrl;
/* compiled from: SystemLogcat.kt */
/* loaded from: classes.dex */
public final class SystemLogcat {
    public static final String[] command = {"logcat", "-d", "-s", "Go", "DEBUG", "AndroidRuntime", "ClashForAndroid", "LwIP"};

    public static final String dumpCrash() {
        try {
            Process exec = Runtime.getRuntime().exec(command);
            InputStream inputStream = exec.getInputStream();
            List<String> readLines = FilesKt__UtilsKt.readLines(new InputStreamReader(inputStream, Charsets.UTF_8));
            ArrayList arrayList = new ArrayList();
            for (Object obj : readLines) {
                if (!StringsKt__IndentKt.startsWith$default((String) obj, "------", false, 2)) {
                    arrayList.add(obj);
                }
            }
            String joinToString$default = ArraysKt___ArraysKt.joinToString$default(arrayList, "\n", null, null, 0, null, null, 62);
            InputKt.closeFinally(inputStream, null);
            exec.waitFor();
            return StringsKt__IndentKt.trim(joinToString$default).toString();
        } catch (Exception unused) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
    }
}
