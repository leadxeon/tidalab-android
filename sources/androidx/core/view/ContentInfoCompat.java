package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Locale;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public final class ContentInfoCompat {
    public final ClipData mClip;
    public final Bundle mExtras;
    public final int mFlags;
    public final Uri mLinkUri;
    public final int mSource;

    /* loaded from: classes.dex */
    public static final class Builder {
        public ClipData mClip;
        public Bundle mExtras;
        public int mFlags;
        public Uri mLinkUri;
        public int mSource;

        public Builder(ClipData clipData, int i) {
            this.mClip = clipData;
            this.mSource = i;
        }
    }

    public ContentInfoCompat(Builder builder) {
        ClipData clipData = builder.mClip;
        Objects.requireNonNull(clipData);
        this.mClip = clipData;
        int i = builder.mSource;
        if (i < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", "source", 0, 3));
        } else if (i <= 3) {
            this.mSource = i;
            int i2 = builder.mFlags;
            if ((i2 & 1) == i2) {
                this.mFlags = i2;
                this.mLinkUri = builder.mLinkUri;
                this.mExtras = builder.mExtras;
                return;
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Requested flags 0x");
            outline13.append(Integer.toHexString(i2));
            outline13.append(", but only 0x");
            outline13.append(Integer.toHexString(1));
            outline13.append(" are allowed");
            throw new IllegalArgumentException(outline13.toString());
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", "source", 0, 3));
        }
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ContentInfoCompat{clip=");
        outline13.append(this.mClip.getDescription());
        outline13.append(", source=");
        int i = this.mSource;
        if (i == 0) {
            str = "SOURCE_APP";
        } else if (i == 1) {
            str = "SOURCE_CLIPBOARD";
        } else if (i != 2) {
            str = i != 3 ? String.valueOf(i) : "SOURCE_DRAG_AND_DROP";
        } else {
            str = "SOURCE_INPUT_METHOD";
        }
        outline13.append(str);
        outline13.append(", flags=");
        int i2 = this.mFlags;
        outline13.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
        Uri uri = this.mLinkUri;
        String str3 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (uri == null) {
            str2 = str3;
        } else {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13(", hasLinkUri(");
            outline132.append(this.mLinkUri.toString().length());
            outline132.append(")");
            str2 = outline132.toString();
        }
        outline13.append(str2);
        if (this.mExtras != null) {
            str3 = ", hasExtras";
        }
        return GeneratedOutlineSupport.outline11(outline13, str3, "}");
    }
}
