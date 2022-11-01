package androidx.core.content.res;

import androidx.core.provider.FontRequest;
/* loaded from: classes.dex */
public final class FontResourcesParserCompat$ProviderResourceEntry implements FontResourcesParserCompat$FamilyResourceEntry {
    public final FontRequest mRequest;
    public final int mStrategy;
    public final String mSystemFontFamilyName;
    public final int mTimeoutMs;

    public FontResourcesParserCompat$ProviderResourceEntry(FontRequest fontRequest, int i, int i2, String str) {
        this.mRequest = fontRequest;
        this.mStrategy = i;
        this.mTimeoutMs = i2;
        this.mSystemFontFamilyName = str;
    }
}
