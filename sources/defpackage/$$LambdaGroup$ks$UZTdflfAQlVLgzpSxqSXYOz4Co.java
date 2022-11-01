package defpackage;

import android.content.Context;
import com.tidalab.v2board.clash.design.ApkBrokenDesign;
import com.tidalab.v2board.clash.design.ApkBrokenDesign$screen$1$1$1;
import com.tidalab.v2board.clash.design.ApkBrokenDesign$screen$1$2$1;
import com.tidalab.v2board.clash.design.preference.ClickablePreference;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$UZTdflfAQlVLgzpSxqSXYOz4C-o  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$UZTdflfAQlVLgzpSxqSXYOz4Co extends Lambda implements Function1<ClickablePreference, Unit> {
    public final /* synthetic */ Object $capture$0;
    public final /* synthetic */ Object $capture$1;
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$UZTdflfAQlVLgzpSxqSXYOz4Co(int i, Object obj, Object obj2) {
        super(1);
        this.$id$ = i;
        this.$capture$0 = obj;
        this.$capture$1 = obj2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(ClickablePreference clickablePreference) {
        int i = this.$id$;
        if (i == 0) {
            clickablePreference.clicked(new ApkBrokenDesign$screen$1$1$1((ApkBrokenDesign) this.$capture$0, (Context) this.$capture$1));
            return Unit.INSTANCE;
        } else if (i == 1) {
            clickablePreference.clicked(new ApkBrokenDesign$screen$1$2$1((ApkBrokenDesign) this.$capture$0, (Context) this.$capture$1));
            return Unit.INSTANCE;
        } else {
            throw null;
        }
    }
}
