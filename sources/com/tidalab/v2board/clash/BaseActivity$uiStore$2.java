package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.store.UiStore;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: BaseActivity.kt */
/* loaded from: classes.dex */
public final class BaseActivity$uiStore$2 extends Lambda implements Function0<UiStore> {
    public final /* synthetic */ BaseActivity<D> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseActivity$uiStore$2(BaseActivity<D> baseActivity) {
        super(0);
        this.this$0 = baseActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public UiStore invoke() {
        return new UiStore(this.this$0);
    }
}
