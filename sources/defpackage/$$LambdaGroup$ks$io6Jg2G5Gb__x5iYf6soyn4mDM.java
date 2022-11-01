package defpackage;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn-4mDM  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM extends Lambda implements Function1<String, Boolean> {
    public static final $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM INSTANCE$0 = new $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(0);
    public static final $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM INSTANCE$1 = new $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(1);
    public static final $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM INSTANCE$2 = new $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(2);
    public static final $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM INSTANCE$3 = new $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(3);
    public static final $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM INSTANCE$4 = new $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(4);
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM(int i) {
        super(1);
        this.$id$ = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0072, code lost:
        if ((r8 == null ? 0 : r8.longValue()) >= 15) goto L_0x0074;
     */
    @Override // kotlin.jvm.functions.Function1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Boolean invoke(java.lang.String r8) {
        /*
            r7 = this;
            int r0 = r7.$id$
            if (r0 == 0) goto L_0x007a
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x0054
            r3 = 2
            if (r0 == r3) goto L_0x0037
            r3 = 3
            if (r0 == r3) goto L_0x001f
            r1 = 4
            if (r0 != r1) goto L_0x001d
            java.lang.String r8 = (java.lang.String) r8
            boolean r8 = kotlin.text.StringsKt__IndentKt.isBlank(r8)
            r8 = r8 ^ r2
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            return r8
        L_0x001d:
            r8 = 0
            throw r8
        L_0x001f:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r0 = "https://"
            boolean r0 = kotlin.text.StringsKt__IndentKt.startsWith(r8, r0, r2)
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "http://"
            boolean r8 = kotlin.text.StringsKt__IndentKt.startsWith(r8, r0, r2)
            if (r8 == 0) goto L_0x0032
        L_0x0031:
            r1 = 1
        L_0x0032:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r1)
            return r8
        L_0x0037:
            java.lang.String r8 = (java.lang.String) r8
            kotlin.text.Regex r0 = com.tidalab.v2board.clash.common.util.PatternsKt.PatternFileName
            java.util.regex.Pattern r0 = r0.nativePattern
            java.util.regex.Matcher r0 = r0.matcher(r8)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L_0x004f
            boolean r8 = kotlin.text.StringsKt__IndentKt.isBlank(r8)
            r8 = r8 ^ r2
            if (r8 == 0) goto L_0x004f
            r1 = 1
        L_0x004f:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r1)
            return r8
        L_0x0054:
            java.lang.String r8 = (java.lang.String) r8
            int r0 = r8.length()
            if (r0 != 0) goto L_0x005e
            r0 = 1
            goto L_0x005f
        L_0x005e:
            r0 = 0
        L_0x005f:
            if (r0 != 0) goto L_0x0074
            java.lang.Long r8 = kotlin.text.StringsKt__IndentKt.toLongOrNull(r8)
            if (r8 != 0) goto L_0x006a
            r3 = 0
            goto L_0x006e
        L_0x006a:
            long r3 = r8.longValue()
        L_0x006e:
            r5 = 15
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 < 0) goto L_0x0075
        L_0x0074:
            r1 = 1
        L_0x0075:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r1)
            return r8
        L_0x007a:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.$$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.invoke(java.lang.Object):java.lang.Object");
    }
}
