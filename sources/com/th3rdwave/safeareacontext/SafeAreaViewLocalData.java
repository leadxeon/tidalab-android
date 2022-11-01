package com.th3rdwave.safeareacontext;

import java.util.EnumSet;
/* loaded from: classes.dex */
public class SafeAreaViewLocalData {
    public EnumSet<SafeAreaViewEdges> mEdges;
    public EdgeInsets mInsets;
    public SafeAreaViewMode mMode;

    public SafeAreaViewLocalData(EdgeInsets edgeInsets, SafeAreaViewMode safeAreaViewMode, EnumSet<SafeAreaViewEdges> enumSet) {
        this.mInsets = edgeInsets;
        this.mMode = safeAreaViewMode;
        this.mEdges = enumSet;
    }
}
