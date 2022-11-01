package com.tidalab.v2board.clash.service.clash.module;

import android.content.IntentFilter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: AppListCacheModule.kt */
/* loaded from: classes.dex */
public final class AppListCacheModule$run$packageChanged$1 extends Lambda implements Function1<IntentFilter, Unit> {
    public static final AppListCacheModule$run$packageChanged$1 INSTANCE = new AppListCacheModule$run$packageChanged$1();

    public AppListCacheModule$run$packageChanged$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(IntentFilter intentFilter) {
        IntentFilter intentFilter2 = intentFilter;
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addDataScheme("package");
        return Unit.INSTANCE;
    }
}
