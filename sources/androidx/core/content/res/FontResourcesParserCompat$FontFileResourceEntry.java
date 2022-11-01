package androidx.core.content.res;
/* loaded from: classes.dex */
public final class FontResourcesParserCompat$FontFileResourceEntry {
    public final String mFileName;
    public boolean mItalic;
    public int mResourceId;
    public int mTtcIndex;
    public String mVariationSettings;
    public int mWeight;

    public FontResourcesParserCompat$FontFileResourceEntry(String str, int i, boolean z, String str2, int i2, int i3) {
        this.mFileName = str;
        this.mWeight = i;
        this.mItalic = z;
        this.mVariationSettings = str2;
        this.mTtcIndex = i2;
        this.mResourceId = i3;
    }
}
