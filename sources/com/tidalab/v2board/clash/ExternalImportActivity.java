package com.tidalab.v2board.clash;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: ExternalImportActivity.kt */
/* loaded from: classes.dex */
public final class ExternalImportActivity extends Activity implements CoroutineScope {
    public final /* synthetic */ CoroutineScope $$delegate_0;

    public ExternalImportActivity() {
        SupervisorJobImpl supervisorJobImpl = new SupervisorJobImpl(null);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        this.$$delegate_0 = new ContextScope(CoroutineContext.Element.DefaultImpls.plus(supervisorJobImpl, MainDispatcherLoader.dispatcher));
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!Intrinsics.areEqual(getIntent().getAction(), "android.intent.action.VIEW")) {
            finish();
            return;
        }
        Uri data = getIntent().getData();
        if (data == null) {
            finish();
            return;
        }
        String queryParameter = data.getQueryParameter("url");
        if (queryParameter == null) {
            finish();
        } else {
            InputKt.launch$default(this, null, null, new ExternalImportActivity$onCreate$1(this, data, queryParameter, null), 3, null);
        }
    }
}
