package com.facebook.imagepipeline.common;

import androidx.recyclerview.R$dimen;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class BytesRange {
    public static Pattern sHeaderParsingRegEx;
    public final int from;
    public final int to;

    public BytesRange(int i, int i2) {
        this.from = i;
        this.to = i2;
    }

    public static BytesRange fromContentRangeHeader(String str) throws IllegalArgumentException {
        if (str == null) {
            return null;
        }
        if (sHeaderParsingRegEx == null) {
            sHeaderParsingRegEx = Pattern.compile("[-/ ]");
        }
        try {
            String[] split = sHeaderParsingRegEx.split(str);
            R$dimen.checkArgument(split.length == 4);
            R$dimen.checkArgument(split[0].equals("bytes"));
            int parseInt = Integer.parseInt(split[1]);
            int parseInt2 = Integer.parseInt(split[2]);
            int parseInt3 = Integer.parseInt(split[3]);
            R$dimen.checkArgument(parseInt2 > parseInt);
            R$dimen.checkArgument(parseInt3 > parseInt2);
            if (parseInt2 < parseInt3 - 1) {
                return new BytesRange(parseInt, parseInt2);
            }
            return new BytesRange(parseInt, Integer.MAX_VALUE);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", str), e);
        }
    }

    public static String valueOrEmpty(int i) {
        return i == Integer.MAX_VALUE ? HttpUrl.FRAGMENT_ENCODE_SET : Integer.toString(i);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BytesRange)) {
            return false;
        }
        BytesRange bytesRange = (BytesRange) obj;
        return this.from == bytesRange.from && this.to == bytesRange.to;
    }

    public int hashCode() {
        return R$dimen.hashCode(this.from, this.to);
    }

    public String toString() {
        return String.format(null, "%s-%s", valueOrEmpty(this.from), valueOrEmpty(this.to));
    }
}
