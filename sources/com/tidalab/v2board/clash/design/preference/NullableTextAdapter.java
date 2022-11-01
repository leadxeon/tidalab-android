package com.tidalab.v2board.clash.design.preference;

import kotlin.text.StringsKt__IndentKt;
import okhttp3.HttpUrl;
/* compiled from: Value.kt */
/* loaded from: classes.dex */
public interface NullableTextAdapter<T> {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Value.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final NullableTextAdapter<Integer> Port = new NullableTextAdapter<Integer>() { // from class: com.tidalab.v2board.clash.design.preference.NullableTextAdapter$Companion$Port$1
            @Override // com.tidalab.v2board.clash.design.preference.NullableTextAdapter
            public String from(Integer num) {
                Integer num2 = num;
                if (num2 == null) {
                    return null;
                }
                return num2.intValue() > 0 ? num2.toString() : HttpUrl.FRAGMENT_ENCODE_SET;
            }

            @Override // com.tidalab.v2board.clash.design.preference.NullableTextAdapter
            public Integer to(String str) {
                if (str == null) {
                    return null;
                }
                Integer intOrNull = StringsKt__IndentKt.toIntOrNull(str);
                if (intOrNull == null) {
                    return 0;
                }
                return intOrNull;
            }
        };
        public static final NullableTextAdapter<String> String = new NullableTextAdapter<String>() { // from class: com.tidalab.v2board.clash.design.preference.NullableTextAdapter$Companion$String$1
            @Override // com.tidalab.v2board.clash.design.preference.NullableTextAdapter
            public String from(String str) {
                return str;
            }

            @Override // com.tidalab.v2board.clash.design.preference.NullableTextAdapter
            public String to(String str) {
                return str;
            }
        };
    }

    String from(T t);

    T to(String str);
}
