package com.tidalab.v2board.clash.common.store;

import com.tidalab.v2board.clash.common.store.Store;
import kotlin.reflect.KProperty;
/* compiled from: Store.kt */
/* loaded from: classes.dex */
public final class Store$boolean$1 implements Store.Delegate<Boolean> {
    public final /* synthetic */ boolean $defaultValue;
    public final /* synthetic */ String $key;
    public final /* synthetic */ Store this$0;

    public Store$boolean$1(Store store, String str, boolean z) {
        this.this$0 = store;
        this.$key = str;
        this.$defaultValue = z;
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public Boolean getValue(Object obj, KProperty kProperty) {
        return Boolean.valueOf(this.this$0.provider.getBoolean(this.$key, this.$defaultValue));
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public void setValue(Object obj, KProperty kProperty, Boolean bool) {
        this.this$0.provider.setBoolean(this.$key, bool.booleanValue());
    }
}
