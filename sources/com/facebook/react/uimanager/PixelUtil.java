package com.facebook.react.uimanager;

import android.os.Trace;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.facebook.react.CoreModulesPackage;
import com.facebook.react.R$style;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class PixelUtil {
    public static Map<String, Object> createConstants(UIManagerModule.ViewManagerResolver viewManagerResolver) {
        ArrayList arrayList;
        List<String> viewManagerNames;
        Map<String, Object> constants = R$style.getConstants();
        ReactInstanceManager reactInstanceManager = CoreModulesPackage.this.mReactInstanceManager;
        Objects.requireNonNull(reactInstanceManager);
        Trace.beginSection("ReactInstanceManager.getViewManagerNames");
        synchronized (reactInstanceManager.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) reactInstanceManager.getCurrentReactContext();
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (reactInstanceManager.mPackages) {
                    HashSet hashSet = new HashSet();
                    for (ReactPackage reactPackage : reactInstanceManager.mPackages) {
                        reactPackage.getClass().getSimpleName();
                        if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(reactApplicationContext)) != null) {
                            hashSet.addAll(viewManagerNames);
                        }
                    }
                    Trace.endSection();
                    arrayList = new ArrayList(hashSet);
                }
            }
            arrayList = null;
        }
        ((HashMap) constants).put("ViewManagerNames", arrayList);
        ((HashMap) constants).put("LazyViewManagersEnabled", Boolean.TRUE);
        return constants;
    }

    public static Map<String, Object> createConstantsForViewManager(ViewManager viewManager, Map map, Map map2, Map map3, Map map4) {
        HashMap hashMap = new HashMap();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            recursiveMerge(map3, exportedCustomBubblingEventTypeConstants);
            recursiveMerge(exportedCustomBubblingEventTypeConstants, null);
            hashMap.put("bubblingEventTypes", exportedCustomBubblingEventTypeConstants);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants != null) {
            recursiveMerge(map4, exportedCustomDirectEventTypeConstants);
            recursiveMerge(exportedCustomDirectEventTypeConstants, null);
            hashMap.put("directEventTypes", exportedCustomDirectEventTypeConstants);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            hashMap.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            hashMap.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            hashMap.put("NativeProps", nativeProps);
        }
        return hashMap;
    }

    public static void recursiveMerge(Map map, Map map2) {
        if (!(map == null || map2 == null || map2.isEmpty())) {
            for (Object obj : map2.keySet()) {
                Object obj2 = map2.get(obj);
                Object obj3 = map.get(obj);
                if (obj3 == null || !(obj2 instanceof Map) || !(obj3 instanceof Map)) {
                    map.put(obj, obj2);
                } else {
                    recursiveMerge((Map) obj3, (Map) obj2);
                }
            }
        }
    }

    public static float toDIPFromPixel(float f) {
        return f / R$style.sWindowDisplayMetrics.density;
    }

    public static float toPixelFromDIP(double d) {
        return toPixelFromDIP((float) d);
    }

    public static float toPixelFromSP(float f) {
        return toPixelFromSP(f, Float.NaN);
    }

    public static float toPixelFromDIP(float f) {
        return TypedValue.applyDimension(1, f, R$style.sWindowDisplayMetrics);
    }

    public static float toPixelFromSP(float f, float f2) {
        DisplayMetrics displayMetrics = R$style.sWindowDisplayMetrics;
        float f3 = displayMetrics.scaledDensity;
        float f4 = displayMetrics.density;
        float f5 = f3 / f4;
        if (f2 >= 1.0f && f2 < f5) {
            f3 = f4 * f2;
        }
        return f * f3;
    }

    public static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        Map<String, Object> constants = R$style.getConstants();
        Map<? extends String, ? extends Object> bubblingEventTypeConstants = R$style.getBubblingEventTypeConstants();
        Map<? extends String, ? extends Object> directEventTypeConstants = R$style.getDirectEventTypeConstants();
        if (map != null) {
            map.putAll(bubblingEventTypeConstants);
        }
        if (map2 != null) {
            map2.putAll(directEventTypeConstants);
        }
        for (ViewManager viewManager : list) {
            String name = viewManager.getName();
            Map<String, Object> createConstantsForViewManager = createConstantsForViewManager(viewManager, null, null, map, map2);
            if (!((HashMap) createConstantsForViewManager).isEmpty()) {
                ((HashMap) constants).put(name, createConstantsForViewManager);
            }
        }
        HashMap hashMap = (HashMap) constants;
        hashMap.put("genericBubblingEventTypes", bubblingEventTypeConstants);
        hashMap.put("genericDirectEventTypes", directEventTypeConstants);
        return constants;
    }
}
