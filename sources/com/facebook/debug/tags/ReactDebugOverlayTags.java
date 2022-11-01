package com.facebook.debug.tags;

import android.graphics.Color;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
/* loaded from: classes.dex */
public class ReactDebugOverlayTags {
    public static final DebugOverlayTag RN_CORE = new DebugOverlayTag("RN Core", "Tag for React Native Core", -16777216);
    public static final DebugOverlayTag BRIDGE_CALLS = new DebugOverlayTag("Bridge Calls", "JS to Java calls (warning: this is spammy)", -65281);
    public static final DebugOverlayTag NATIVE_MODULE = new DebugOverlayTag("Native Module", "Native Module init", Color.rgb(128, 0, 128));
    public static final DebugOverlayTag UI_MANAGER = new DebugOverlayTag("UI Manager", "UI Manager View Operations (requires restart\nwarning: this is spammy)", -16711681);
    public static final DebugOverlayTag FABRIC_UI_MANAGER = new DebugOverlayTag("FabricUIManager", "Fabric UI Manager View Operations", -16711681);

    static {
        Color.rgb(156, 39, 176);
        Color.rgb(255, 153, 0);
    }
}
