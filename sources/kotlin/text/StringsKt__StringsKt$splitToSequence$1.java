package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
/* compiled from: Strings.kt */
/* loaded from: classes.dex */
public final class StringsKt__StringsKt$splitToSequence$1 extends Lambda implements Function1<IntRange, String> {
    public final /* synthetic */ CharSequence $this_splitToSequence;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringsKt__StringsKt$splitToSequence$1(CharSequence charSequence) {
        super(1);
        this.$this_splitToSequence = charSequence;
    }

    @Override // kotlin.jvm.functions.Function1
    public String invoke(IntRange intRange) {
        return StringsKt__IndentKt.substring(this.$this_splitToSequence, intRange);
    }
}
