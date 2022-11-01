package com.tidalab.v2board.clash.common;

import android.app.Application;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: Global.kt */
/* loaded from: classes.dex */
public final class Global implements CoroutineScope {
    public static final Global INSTANCE = new Global();
    public static Application application_;
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.IO);

    public Global() {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
    }

    public final Application getApplication() {
        Application application = application_;
        if (application != null) {
            return application;
        }
        Intrinsics.throwUninitializedPropertyAccessException("application_");
        throw null;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }
}
