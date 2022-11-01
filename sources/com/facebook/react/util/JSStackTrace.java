package com.facebook.react.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class JSStackTrace {
    public static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");

    public static String format(String str, ReadableArray readableArray) {
        String str2;
        String string;
        Matcher matcher;
        StringBuilder sb = new StringBuilder(str);
        sb.append(", stack:\n");
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            sb.append(map.getString("methodName"));
            sb.append("@");
            if (map.hasKey("file") && !map.isNull("file") && map.getType("file") == ReadableType.String && (string = map.getString("file")) != null) {
                if (FILE_ID_PATTERN.matcher(string).find()) {
                    str2 = matcher.group(1) + ":";
                    sb.append(str2);
                    if (map.hasKey("lineNumber") || map.isNull("lineNumber") || map.getType("lineNumber") != ReadableType.Number) {
                        sb.append(-1);
                    } else {
                        sb.append(map.getInt("lineNumber"));
                    }
                    if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                        sb.append(":");
                        sb.append(map.getInt("column"));
                    }
                    sb.append("\n");
                }
            }
            str2 = HttpUrl.FRAGMENT_ENCODE_SET;
            sb.append(str2);
            if (map.hasKey("lineNumber")) {
            }
            sb.append(-1);
            if (map.hasKey("column")) {
                sb.append(":");
                sb.append(map.getInt("column"));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
