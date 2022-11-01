package com.tidalab.v2board.clash;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
/* compiled from: DashActivity.kt */
/* loaded from: classes.dex */
public final class DashActivity$startClash$2 extends Lambda implements Function1<Snackbar, Unit> {
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$startClash$2(DashActivity dashActivity) {
        super(1);
        this.this$0 = dashActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Snackbar snackbar) {
        final DashActivity dashActivity = this.this$0;
        snackbar.setAction(R.string.profiles, new View.OnClickListener() { // from class: com.tidalab.v2board.clash.-$$Lambda$DashActivity$startClash$2$KOeh09Srdy9ewf0AHoJr5Bw7aZA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashActivity.this.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(ProfilesActivity.class)));
            }
        });
        return Unit.INSTANCE;
    }
}
