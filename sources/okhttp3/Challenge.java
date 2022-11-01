package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.Util;
/* loaded from: classes.dex */
public final class Challenge {
    private final Map<String, String> authParams;
    private final String scheme;

    public Challenge(String str, Map<String, String> map) {
        Objects.requireNonNull(str, "scheme == null");
        Objects.requireNonNull(map, "authParams == null");
        this.scheme = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey() == null ? null : entry.getKey().toLowerCase(Locale.US), entry.getValue());
        }
        this.authParams = Collections.unmodifiableMap(linkedHashMap);
    }

    public Map<String, String> authParams() {
        return this.authParams;
    }

    public Charset charset() {
        String str = this.authParams.get("charset");
        if (str != null) {
            try {
                return Charset.forName(str);
            } catch (Exception unused) {
            }
        }
        return Util.ISO_8859_1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            if (challenge.scheme.equals(this.scheme) && challenge.authParams.equals(this.authParams)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.authParams.hashCode() + GeneratedOutlineSupport.outline1(this.scheme, 899, 31);
    }

    public String realm() {
        return this.authParams.get("realm");
    }

    public String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.scheme + " authParams=" + this.authParams;
    }

    public Challenge withCharset(Charset charset) {
        Objects.requireNonNull(charset, "charset == null");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.authParams);
        linkedHashMap.put("charset", charset.name());
        return new Challenge(this.scheme, linkedHashMap);
    }

    public Challenge(String str, String str2) {
        Objects.requireNonNull(str, "scheme == null");
        Objects.requireNonNull(str2, "realm == null");
        this.scheme = str;
        this.authParams = Collections.singletonMap("realm", str2);
    }
}
