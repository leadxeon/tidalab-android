package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public enum TextProperties$TextDecoration {
    None("none"),
    Underline("underline"),
    Overline("overline"),
    LineThrough("line-through"),
    Blink("blink");
    
    public static final Map<String, TextProperties$TextDecoration> decorationToEnum = new HashMap();
    public final String decoration;

    static {
        TextProperties$TextDecoration[] values = values();
        for (int i = 0; i < 5; i++) {
            TextProperties$TextDecoration textProperties$TextDecoration = values[i];
            decorationToEnum.put(textProperties$TextDecoration.decoration, textProperties$TextDecoration);
        }
    }

    TextProperties$TextDecoration(String str) {
        this.decoration = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.decoration;
    }
}
