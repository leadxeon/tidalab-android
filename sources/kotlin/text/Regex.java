package kotlin.text;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: Regex.kt */
/* loaded from: classes.dex */
public final class Regex implements Serializable {
    public final Pattern nativePattern;

    public Regex(String str) {
        this.nativePattern = Pattern.compile(str);
    }

    public final MatchResult matchEntire(CharSequence charSequence) {
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (!matcher.matches()) {
            return null;
        }
        return new MatcherMatchResult(matcher, charSequence);
    }

    public String toString() {
        return this.nativePattern.toString();
    }
}
