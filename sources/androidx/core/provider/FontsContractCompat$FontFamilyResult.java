package androidx.core.provider;
/* loaded from: classes.dex */
public class FontsContractCompat$FontFamilyResult {
    public final FontsContractCompat$FontInfo[] mFonts;
    public final int mStatusCode;

    @Deprecated
    public FontsContractCompat$FontFamilyResult(int i, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr) {
        this.mStatusCode = i;
        this.mFonts = fontsContractCompat$FontInfoArr;
    }
}
