package kotlin.text;

import java.util.List;
import java.util.regex.Matcher;
/* compiled from: Regex.kt */
/* loaded from: classes.dex */
public final class MatcherMatchResult implements MatchResult {
    public List<String> groupValues_;
    public final MatchGroupCollection groups = new MatcherMatchResult$groups$1(this);
    public final Matcher matcher;

    public MatcherMatchResult(Matcher matcher, CharSequence charSequence) {
        this.matcher = matcher;
    }

    @Override // kotlin.text.MatchResult
    public MatchGroupCollection getGroups() {
        return this.groups;
    }
}
