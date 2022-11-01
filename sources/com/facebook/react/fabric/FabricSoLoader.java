package com.facebook.react.fabric;

import android.os.Trace;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.soloader.SoLoader;
/* loaded from: classes.dex */
public class FabricSoLoader {
    public static volatile boolean sDidInit = false;

    public static void staticInit() {
        if (!sDidInit) {
            Trace.beginSection("FabricSoLoader.staticInit::load:fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START);
            SoLoader.loadLibrary("fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END);
            Trace.endSection();
            sDidInit = true;
        }
    }
}
