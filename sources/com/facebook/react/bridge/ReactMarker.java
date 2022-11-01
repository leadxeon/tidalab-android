package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@DoNotStrip
/* loaded from: classes.dex */
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new CopyOnWriteArrayList();
    private static final List<FabricMarkerListener> sFabricMarkerListeners = new CopyOnWriteArrayList();

    /* loaded from: classes.dex */
    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j);
    }

    /* loaded from: classes.dex */
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i);
    }

    @DoNotStrip
    public static void addFabricListener(FabricMarkerListener fabricMarkerListener) {
        List<FabricMarkerListener> list = sFabricMarkerListeners;
        if (!list.contains(fabricMarkerListener)) {
            list.add(fabricMarkerListener);
        }
    }

    @DoNotStrip
    public static void addListener(MarkerListener markerListener) {
        List<MarkerListener> list = sListeners;
        if (!list.contains(markerListener)) {
            list.add(markerListener);
        }
    }

    @DoNotStrip
    public static void clearFabricMarkerListeners() {
        sFabricMarkerListeners.clear();
    }

    @DoNotStrip
    public static void clearMarkerListeners() {
        sListeners.clear();
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j) {
        for (FabricMarkerListener fabricMarkerListener : sFabricMarkerListeners) {
            fabricMarkerListener.logFabricMarker(reactMarkerConstants, str, i, j);
        }
    }

    @DoNotStrip
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @DoNotStrip
    public static void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        sFabricMarkerListeners.remove(fabricMarkerListener);
    }

    @DoNotStrip
    public static void removeListener(MarkerListener markerListener) {
        sListeners.remove(markerListener);
    }

    @DoNotStrip
    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i, -1L);
    }

    @DoNotStrip
    public static void logMarker(String str, String str2) {
        logMarker(str, str2, 0);
    }

    @DoNotStrip
    public static void logMarker(String str, String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i);
        for (MarkerListener markerListener : sListeners) {
            markerListener.logMarker(reactMarkerConstants, str, i);
        }
    }
}
