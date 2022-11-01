package com.facebook.react.uimanager.layoutanimation;
/* loaded from: classes.dex */
public enum LayoutAnimationType {
    CREATE,
    UPDATE,
    DELETE;

    public static String toString(LayoutAnimationType layoutAnimationType) {
        int ordinal = layoutAnimationType.ordinal();
        if (ordinal == 0) {
            return "create";
        }
        if (ordinal == 1) {
            return "update";
        }
        if (ordinal == 2) {
            return "delete";
        }
        throw new IllegalArgumentException("Unsupported LayoutAnimationType: " + layoutAnimationType);
    }
}
