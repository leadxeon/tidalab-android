package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Regex.kt */
/* loaded from: classes.dex */
public final class MatcherMatchResult$groups$1$iterator$1 extends Lambda implements Function1<Integer, MatchGroup> {
    public final /* synthetic */ MatcherMatchResult$groups$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatcherMatchResult$groups$1$iterator$1(MatcherMatchResult$groups$1 matcherMatchResult$groups$1) {
        super(1);
        this.this$0 = matcherMatchResult$groups$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public MatchGroup invoke(Integer num) {
        return this.this$0.get(num.intValue());
    }
}
