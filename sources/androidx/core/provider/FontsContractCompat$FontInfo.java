package androidx.core.provider;

import android.net.Uri;
import java.util.Objects;
/* loaded from: classes.dex */
public class FontsContractCompat$FontInfo {
    public final boolean mItalic;
    public final int mResultCode;
    public final int mTtcIndex;
    public final Uri mUri;
    public final int mWeight;

    @Deprecated
    public FontsContractCompat$FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
        Objects.requireNonNull(uri);
        this.mUri = uri;
        this.mTtcIndex = i;
        this.mWeight = i2;
        this.mItalic = z;
        this.mResultCode = i3;
    }
}
