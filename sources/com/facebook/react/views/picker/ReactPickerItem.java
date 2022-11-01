package com.facebook.react.views.picker;

import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ReactPickerItem {
    public final Integer color;
    public final String label;

    public ReactPickerItem(ReadableMap readableMap) {
        this.label = readableMap.getString("label");
        if (!readableMap.hasKey("color") || readableMap.isNull("color")) {
            this.color = null;
        } else {
            this.color = Integer.valueOf(readableMap.getInt("color"));
        }
    }
}
