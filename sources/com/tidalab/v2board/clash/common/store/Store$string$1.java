package com.tidalab.v2board.clash.common.store;

import com.tidalab.v2board.clash.common.store.Store;
import kotlin.reflect.KProperty;
/* compiled from: Store.kt */
/* loaded from: classes.dex */
public final class Store$string$1 implements Store.Delegate<String> {
    public final /* synthetic */ String $defaultValue;
    public final /* synthetic */ String $key;
    public final /* synthetic */ Store this$0;

    public Store$string$1(Store store, String str, String str2) {
        this.this$0 = store;
        this.$key = str;
        this.$defaultValue = str2;
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public String getValue(Object obj, KProperty kProperty) {
        return this.this$0.provider.getString(this.$key, this.$defaultValue);
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public void setValue(Object obj, KProperty kProperty, String str) {
        this.this$0.provider.setString(this.$key, str);
    }
}
