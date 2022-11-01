package defpackage;

import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Intents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM extends Lambda implements Function1<IntentFilter, Unit> {
    public static final $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM INSTANCE$0 = new $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM(0);
    public static final $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM INSTANCE$1 = new $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM(1);
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM(int i) {
        super(1);
        this.$id$ = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(IntentFilter intentFilter) {
        int i = this.$id$;
        if (i == 0) {
            Intents intents = Intents.INSTANCE;
            intentFilter.addAction(Intents.ACTION_PROFILE_LOADED);
            return Unit.INSTANCE;
        } else if (i == 1) {
            IntentFilter intentFilter2 = intentFilter;
            intentFilter2.addAction("android.intent.action.SCREEN_ON");
            intentFilter2.addAction("android.intent.action.SCREEN_OFF");
            return Unit.INSTANCE;
        } else {
            throw null;
        }
    }
}
