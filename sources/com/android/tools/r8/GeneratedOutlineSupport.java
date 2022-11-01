package com.android.tools.r8;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import org.xmlpull.v1.XmlPullParser;
/* compiled from: outline */
/* loaded from: classes.dex */
public class GeneratedOutlineSupport {
    public static float outline0(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int outline1(String str, int i, int i2) {
        return (str.hashCode() + i) * i2;
    }

    public static String outline10(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static String outline11(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String outline12(XmlPullParser xmlPullParser, StringBuilder sb, String str) {
        sb.append(xmlPullParser.getPositionDescription());
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder outline13(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder outline14(String str, char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(c);
        return sb;
    }

    public static StringBuilder outline15(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder outline16(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static String outline2(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.exceptionLabel());
        return sb.toString();
    }

    public static String outline3(String str, int i) {
        return str + i;
    }

    public static String outline4(String str, int i, String str2) {
        return str + i + str2;
    }

    public static String outline5(String str, int i, String str2, int i2) {
        return str + i + str2 + i2;
    }

    public static String outline6(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String outline7(String str, Object obj) {
        return str + obj;
    }

    public static String outline8(String str, String str2) {
        return str + str2;
    }

    public static String outline9(String str, String str2, String str3) {
        return str + str2 + str3;
    }
}
