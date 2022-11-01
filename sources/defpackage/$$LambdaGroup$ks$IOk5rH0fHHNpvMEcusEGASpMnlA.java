package defpackage;

import com.tidalab.v2board.clash.design.model.File;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA extends Lambda implements Function1<File, Comparable<?>> {
    public static final $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA INSTANCE$0 = new $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA(0);
    public static final $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA INSTANCE$1 = new $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA(1);
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA(int i) {
        super(1);
        this.$id$ = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Comparable<?> invoke(File file) {
        int i = this.$id$;
        if (i == 0) {
            return Boolean.valueOf(!file.isDirectory);
        }
        if (i == 1) {
            return file.name;
        }
        throw null;
    }
}
