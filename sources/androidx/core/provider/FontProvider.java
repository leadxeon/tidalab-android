package androidx.core.provider;

import java.util.Comparator;
/* loaded from: classes.dex */
public class FontProvider {
    public static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: androidx.core.provider.FontProvider.1
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int i;
            int i2;
            byte[] bArr3 = bArr;
            byte[] bArr4 = bArr2;
            if (bArr3.length != bArr4.length) {
                i2 = bArr3.length;
                i = bArr4.length;
            } else {
                for (int i3 = 0; i3 < bArr3.length; i3++) {
                    if (bArr3[i3] != bArr4[i3]) {
                        i2 = bArr3[i3];
                        i = bArr4[i3];
                    }
                }
                return 0;
            }
            return (i2 == 1 ? 1 : 0) - (i == 1 ? 1 : 0);
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:27:0x0090 A[LOOP:1: B:14:0x004b->B:27:0x0090, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0094 A[EDGE_INSN: B:75:0x0094->B:29:0x0094 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.provider.FontsContractCompat$FontFamilyResult getFontFamilyResult(android.content.Context r20, androidx.core.provider.FontRequest r21, android.os.CancellationSignal r22) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            Method dump skipped, instructions count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.getFontFamilyResult(android.content.Context, androidx.core.provider.FontRequest, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontFamilyResult");
    }
}
