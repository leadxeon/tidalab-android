package defpackage;

import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Intents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI extends Lambda implements Function1<IntentFilter, Unit> {
    public static final $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI INSTANCE$0 = new $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI(0);
    public static final $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI INSTANCE$1 = new $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI(1);
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$j6Mcd8SCB_U0k1dQl5mEbFdQGcI(int i) {
        super(1);
        this.$id$ = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(IntentFilter intentFilter) {
        int i = this.$id$;
        if (i == 0) {
            IntentFilter intentFilter2 = intentFilter;
            intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter2.addAction("android.intent.action.PACKAGE_FULLY_REMOVED");
            intentFilter2.addDataScheme("package");
            return Unit.INSTANCE;
        } else if (i == 1) {
            Intents intents = Intents.INSTANCE;
            intentFilter.addAction(Intents.ACTION_PROFILE_CHANGED);
            return Unit.INSTANCE;
        } else {
            throw null;
        }
    }
}
