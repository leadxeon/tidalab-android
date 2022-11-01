package com.tidalab.v2board.clash.design;

import android.content.Context;
import com.tidalab.v2board.clash.design.ApkBrokenDesign;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ApkBrokenDesign.kt */
/* loaded from: classes.dex */
public final class ApkBrokenDesign$screen$1$1$1 extends Lambda implements Function0<Unit> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ ApkBrokenDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApkBrokenDesign$screen$1$1$1(ApkBrokenDesign apkBrokenDesign, Context context) {
        super(0);
        this.this$0 = apkBrokenDesign;
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        this.this$0.requests.mo14trySendJP2dKIU(new ApkBrokenDesign.Request(this.$context.getString(R.string.google_play_url)));
        return Unit.INSTANCE;
    }
}
