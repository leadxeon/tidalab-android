package com.tidalab.v2board.clash;

import android.content.Intent;
import android.net.Uri;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: HelpActivity.kt */
/* loaded from: classes.dex */
public final class HelpActivity$main$design$1 extends Lambda implements Function1<Uri, Unit> {
    public final /* synthetic */ HelpActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HelpActivity$main$design$1(HelpActivity helpActivity) {
        super(1);
        this.this$0 = helpActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Uri uri) {
        this.this$0.startActivity(new Intent("android.intent.action.VIEW").setData(uri));
        return Unit.INSTANCE;
    }
}
