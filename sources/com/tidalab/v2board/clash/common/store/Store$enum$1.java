package com.tidalab.v2board.clash.common.store;

import com.tidalab.v2board.clash.common.store.Store;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* JADX WARN: Incorrect field signature: TT; */
/* JADX WARN: Incorrect field signature: [TT; */
/* compiled from: Store.kt */
/* loaded from: classes.dex */
public final class Store$enum$1 implements Store.Delegate<T> {
    public final /* synthetic */ Enum $defaultValue;
    public final /* synthetic */ String $key;
    public final /* synthetic */ Enum[] $values;
    public final /* synthetic */ Store this$0;

    /* JADX WARN: Incorrect types in method signature: (Lcom/tidalab/v2board/clash/common/store/Store;Ljava/lang/String;TT;[TT;)V */
    public Store$enum$1(Store store, String str, Enum r3, Enum[] enumArr) {
        this.this$0 = store;
        this.$key = str;
        this.$defaultValue = r3;
        this.$values = enumArr;
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public Object getValue(Object obj, KProperty kProperty) {
        Enum r2;
        String string = this.this$0.provider.getString(this.$key, this.$defaultValue.name());
        Enum[] enumArr = this.$values;
        int length = enumArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                r2 = null;
                break;
            }
            r2 = enumArr[i];
            if (Intrinsics.areEqual(string, r2.name())) {
                break;
            }
            i++;
        }
        return r2 == null ? this.$defaultValue : r2;
    }

    @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
    public void setValue(Object obj, KProperty kProperty, Object obj2) {
        this.this$0.provider.setString(this.$key, ((Enum) obj2).name());
    }
}
