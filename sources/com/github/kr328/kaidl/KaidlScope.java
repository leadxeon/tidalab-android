package com.github.kr328.kaidl;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public final class KaidlScope implements CoroutineScope {
    public static final KaidlScope INSTANCE = new KaidlScope();
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.IO);

    public KaidlScope() {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }
}
