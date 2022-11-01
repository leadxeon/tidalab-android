package kotlin.collections;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: AbstractCollection.kt */
/* loaded from: classes.dex */
public final class AbstractCollection$toString$1 extends Lambda implements Function1<E, CharSequence> {
    public final /* synthetic */ AbstractCollection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractCollection$toString$1(AbstractCollection abstractCollection) {
        super(1);
        this.this$0 = abstractCollection;
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(Object obj) {
        return obj == this.this$0 ? "(this Collection)" : String.valueOf(obj);
    }
}
