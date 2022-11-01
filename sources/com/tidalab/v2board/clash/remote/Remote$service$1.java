package com.tidalab.v2board.clash.remote;

import android.app.Activity;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.AppCrashedActivity;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.util.ApplicationObserver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
/* compiled from: Remote.kt */
/* loaded from: classes.dex */
public final class Remote$service$1 extends Lambda implements Function0<Unit> {
    public static final Remote$service$1 INSTANCE = new Remote$service$1();

    public Remote$service$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
        for (Activity activity : ApplicationObserver.activities) {
            activity.finish();
        }
        Global.INSTANCE.getApplication().startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AppCrashedActivity.class)).addFlags(268435456));
        return Unit.INSTANCE;
    }
}
