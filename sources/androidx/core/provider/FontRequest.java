package androidx.core.provider;

import android.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class FontRequest {
    public final List<List<byte[]>> mCertificates;
    public final String mIdentifier;
    public final String mProviderAuthority;
    public final String mProviderPackage;
    public final String mQuery;

    public FontRequest(String str, String str2, String str3, List<List<byte[]>> list) {
        this.mProviderAuthority = str;
        this.mProviderPackage = str2;
        this.mQuery = str3;
        Objects.requireNonNull(list);
        this.mCertificates = list;
        this.mIdentifier = str + "-" + str2 + "-" + str3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FontRequest {mProviderAuthority: ");
        outline13.append(this.mProviderAuthority);
        outline13.append(", mProviderPackage: ");
        outline13.append(this.mProviderPackage);
        outline13.append(", mQuery: ");
        outline13.append(this.mQuery);
        outline13.append(", mCertificates:");
        sb.append(outline13.toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.mCertificates.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        return GeneratedOutlineSupport.outline11(sb, "}", "mCertificatesArray: 0");
    }
}
