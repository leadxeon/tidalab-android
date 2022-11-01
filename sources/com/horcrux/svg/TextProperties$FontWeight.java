package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public enum TextProperties$FontWeight {
    Normal("normal"),
    Bold("bold"),
    w100("100"),
    w200("200"),
    w300("300"),
    w400("400"),
    w500("500"),
    w600("600"),
    w700("700"),
    w800("800"),
    w900("900"),
    Bolder("bolder"),
    Lighter("lighter");
    
    public static final Map<String, TextProperties$FontWeight> weightToEnum = new HashMap();
    public final String weight;

    static {
        TextProperties$FontWeight[] values = values();
        for (int i = 0; i < 13; i++) {
            TextProperties$FontWeight textProperties$FontWeight = values[i];
            weightToEnum.put(textProperties$FontWeight.weight, textProperties$FontWeight);
        }
    }

    TextProperties$FontWeight(String str) {
        this.weight = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.weight;
    }
}
