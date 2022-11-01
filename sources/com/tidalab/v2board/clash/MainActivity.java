package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: MainActivity.kt */
/* loaded from: classes.dex */
public final class MainActivity extends BaseActivity<MainDesign> {
    @Override // com.facebook.react.ReactActivity
    public String getMainComponentName() {
        return "v2board";
    }

    @Override // com.tidalab.v2board.clash.BaseActivity
    public Object main(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
