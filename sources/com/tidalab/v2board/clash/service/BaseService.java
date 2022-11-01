package com.tidalab.v2board.clash.service;

import android.app.Service;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: BaseService.kt */
/* loaded from: classes.dex */
public abstract class BaseService extends Service implements CoroutineScope {
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.Default);

    public BaseService() {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        InputKt.cancelAndJoinBlocking(this);
    }
}
