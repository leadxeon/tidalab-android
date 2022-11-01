package com.horcrux.svg;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public enum TextProperties$AlignmentBaseline {
    baseline("baseline"),
    textBottom("text-bottom"),
    alphabetic("alphabetic"),
    ideographic("ideographic"),
    middle("middle"),
    central("central"),
    mathematical("mathematical"),
    textTop("text-top"),
    bottom("bottom"),
    center("center"),
    top("top"),
    textBeforeEdge("text-before-edge"),
    textAfterEdge("text-after-edge"),
    beforeEdge("before-edge"),
    afterEdge("after-edge"),
    hanging("hanging");
    
    public static final Map<String, TextProperties$AlignmentBaseline> alignmentToEnum = new HashMap();
    public final String alignment;

    static {
        TextProperties$AlignmentBaseline[] values = values();
        for (int i = 0; i < 16; i++) {
            TextProperties$AlignmentBaseline textProperties$AlignmentBaseline = values[i];
            alignmentToEnum.put(textProperties$AlignmentBaseline.alignment, textProperties$AlignmentBaseline);
        }
    }

    TextProperties$AlignmentBaseline(String str) {
        this.alignment = str;
    }

    public static TextProperties$AlignmentBaseline getEnum(String str) {
        Map<String, TextProperties$AlignmentBaseline> map = alignmentToEnum;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Unknown String Value: ", str));
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.alignment;
    }
}
