package kotlin.text;

import java.util.Iterator;
import java.util.regex.Matcher;
import kotlin.collections.AbstractCollection;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.TransformingSequence;
import kotlin.sequences.TransformingSequence$iterator$1;
/* compiled from: Regex.kt */
/* loaded from: classes.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchGroupCollection {
    public final /* synthetic */ MatcherMatchResult this$0;

    public MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj != null ? obj instanceof MatchGroup : true) {
            return super.contains((MatchGroup) obj);
        }
        return false;
    }

    @Override // kotlin.text.MatchGroupCollection
    public MatchGroup get(int i) {
        Matcher matcher = this.this$0.matcher;
        IntRange until = RangesKt___RangesKt.until(matcher.start(i), matcher.end(i));
        if (Integer.valueOf(until.first).intValue() >= 0) {
            return new MatchGroup(this.this$0.matcher.group(i), until);
        }
        return null;
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.this$0.matcher.groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<MatchGroup> iterator() {
        return new TransformingSequence$iterator$1(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(ArraysKt___ArraysKt.getIndices(this)), new MatcherMatchResult$groups$1$iterator$1(this)));
    }
}
