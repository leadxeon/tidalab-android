package com.tidalab.v2board.clash.design.preference;
/* compiled from: Value.kt */
/* loaded from: classes.dex */
public interface TextAdapter<T> {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Value.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final TextAdapter<String> String = new TextAdapter<String>() { // from class: com.tidalab.v2board.clash.design.preference.TextAdapter$Companion$String$1
            @Override // com.tidalab.v2board.clash.design.preference.TextAdapter
            public String from(String str) {
                return str;
            }

            @Override // com.tidalab.v2board.clash.design.preference.TextAdapter
            public String to(String str) {
                return str;
            }
        };
    }

    String from(T t);

    T to(String str);
}
