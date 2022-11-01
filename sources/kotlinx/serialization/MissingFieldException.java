package kotlinx.serialization;
/* compiled from: SerializationException.kt */
/* loaded from: classes.dex */
public final class MissingFieldException extends SerializationException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MissingFieldException(java.util.List<java.lang.String> r3, java.lang.String r4) {
        /*
            r2 = this;
            int r0 = r3.size()
            r1 = 1
            if (r0 != r1) goto L_0x0022
            java.lang.String r0 = "Field '"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r0)
            r1 = 0
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = (java.lang.String) r3
            r0.append(r3)
            java.lang.String r3 = "' is required for type with serial name '"
            r0.append(r3)
            r0.append(r4)
            java.lang.String r3 = "', but it was missing"
            goto L_0x0039
        L_0x0022:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Fields "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = " are required for type with serial name '"
            r0.append(r3)
            r0.append(r4)
            java.lang.String r3 = "', but they were missing"
        L_0x0039:
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4 = 0
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.MissingFieldException.<init>(java.util.List, java.lang.String):void");
    }
}
