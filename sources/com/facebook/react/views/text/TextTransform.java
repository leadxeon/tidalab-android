package com.facebook.react.views.text;

import java.text.BreakIterator;
/* loaded from: classes.dex */
public enum TextTransform {
    NONE,
    UPPERCASE,
    LOWERCASE,
    CAPITALIZE,
    UNSET;

    public static String apply(String str, TextTransform textTransform) {
        if (str == null) {
            return null;
        }
        int ordinal = textTransform.ordinal();
        if (ordinal == 1) {
            return str.toUpperCase();
        }
        if (ordinal == 2) {
            return str.toLowerCase();
        }
        if (ordinal != 3) {
            return str;
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(str);
        StringBuilder sb = new StringBuilder(str.length());
        int first = wordInstance.first();
        while (true) {
            int next = wordInstance.next();
            if (next == -1) {
                return sb.toString();
            }
            String substring = str.substring(first, next);
            if (Character.isLetterOrDigit(substring.charAt(0))) {
                sb.append(Character.toUpperCase(substring.charAt(0)));
                sb.append(substring.substring(1).toLowerCase());
            } else {
                sb.append(substring);
            }
            first = next;
        }
    }
}
