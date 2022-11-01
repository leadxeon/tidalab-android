package com.facebook.react.uimanager;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> implements BaseViewManagerInterface<T> {
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String STATE_BUSY = "busy";
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_EXPANDED = "expanded";
    private static final String STATE_MIXED = "mixed";
    public static final Map<String, Integer> sStateDescription;
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = (float) Math.sqrt(5.0d);
    private static MatrixMathHelper$MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper$MatrixDecompositionContext();
    private static double[] sTransformDecompositionArray = new double[16];

    static {
        HashMap hashMap = new HashMap();
        sStateDescription = hashMap;
        hashMap.put(STATE_BUSY, Integer.valueOf((int) R.string.state_busy_description));
        hashMap.put(STATE_EXPANDED, Integer.valueOf((int) R.string.state_expanded_description));
        hashMap.put("collapsed", Integer.valueOf((int) R.string.state_collapsed_description));
    }

    private void logUnsupportedPropertyWarning(String str) {
        FLog.w("ReactNative", "%s doesn't support property '%s'", getName(), str);
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        view.setRotation(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setRotationX(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setRotationY(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
    }

    private static float sanitizeFloatPropertyValue(float f) {
        if (f >= -3.4028235E38f && f <= Float.MAX_VALUE) {
            return f;
        }
        if (f < -3.4028235E38f || f == Float.NEGATIVE_INFINITY) {
            return -3.4028235E38f;
        }
        if (f > Float.MAX_VALUE || f == Float.POSITIVE_INFINITY) {
            return Float.MAX_VALUE;
        }
        if (Float.isNaN(f)) {
            return CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        }
        throw new IllegalStateException("Invalid float property value: " + f);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00e3 A[LOOP:2: B:30:0x00e1->B:31:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fb A[LOOP:3: B:33:0x00f9->B:34:0x00fb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void setTransformProperty(android.view.View r26, com.facebook.react.bridge.ReadableArray r27) {
        /*
            Method dump skipped, instructions count: 792
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BaseViewManager.setTransformProperty(android.view.View, com.facebook.react.bridge.ReadableArray):void");
    }

    private void updateViewAccessibility(T t) {
        int i = ReactAccessibilityDelegate.sCounter;
        if (ViewCompat.getAccessibilityDelegateInternal(t) != null) {
            return;
        }
        if (t.getTag(R.id.accessibility_role) != null || t.getTag(R.id.accessibility_state) != null || t.getTag(R.id.accessibility_actions) != null) {
            ViewCompat.setAccessibilityDelegate(t, new ReactAccessibilityDelegate());
        }
    }

    private void updateViewContentDescription(T t) {
        Dynamic dynamic;
        String str = (String) t.getTag(R.id.accessibility_label);
        ReadableMap readableMap = (ReadableMap) t.getTag(R.id.accessibility_state);
        String str2 = (String) t.getTag(R.id.accessibility_hint);
        ArrayList arrayList = new ArrayList();
        ReadableMap readableMap2 = (ReadableMap) t.getTag(R.id.accessibility_value);
        if (str != null) {
            arrayList.add(str);
        }
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                Dynamic dynamic2 = readableMap.getDynamic(nextKey);
                if (nextKey.equals(STATE_CHECKED) && dynamic2.getType() == ReadableType.String && dynamic2.asString().equals(STATE_MIXED)) {
                    arrayList.add(t.getContext().getString(R.string.state_mixed_description));
                } else if (nextKey.equals(STATE_BUSY) && dynamic2.getType() == ReadableType.Boolean && dynamic2.asBoolean()) {
                    arrayList.add(t.getContext().getString(R.string.state_busy_description));
                } else if (nextKey.equals(STATE_EXPANDED) && dynamic2.getType() == ReadableType.Boolean) {
                    arrayList.add(t.getContext().getString(dynamic2.asBoolean() ? R.string.state_expanded_description : R.string.state_collapsed_description));
                }
            }
        }
        if (readableMap2 != null && readableMap2.hasKey("text") && (dynamic = readableMap2.getDynamic("text")) != null && dynamic.getType() == ReadableType.String) {
            arrayList.add(dynamic.asString());
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (arrayList.size() > 0) {
            t.setContentDescription(TextUtils.join(", ", arrayList));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("topAccessibilityAction", R$style.of("registrationName", "onAccessibilityAction"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }

    @ReactProp(name = "accessibilityActions")
    public void setAccessibilityActions(T t, ReadableArray readableArray) {
        if (readableArray != null) {
            t.setTag(R.id.accessibility_actions, readableArray);
        }
    }

    @ReactProp(name = "accessibilityHint")
    public void setAccessibilityHint(T t, String str) {
        t.setTag(R.id.accessibility_hint, str);
        updateViewContentDescription(t);
    }

    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(T t, String str) {
        t.setTag(R.id.accessibility_label, str);
        updateViewContentDescription(t);
    }

    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(T t, String str) {
        if (str == null || str.equals("none")) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            t.setAccessibilityLiveRegion(0);
        } else if (str.equals("polite")) {
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            t.setAccessibilityLiveRegion(1);
        } else if (str.equals("assertive")) {
            AtomicInteger atomicInteger3 = ViewCompat.sNextGeneratedId;
            t.setAccessibilityLiveRegion(2);
        }
    }

    @ReactProp(name = "accessibilityRole")
    public void setAccessibilityRole(T t, String str) {
        if (str != null) {
            ReactAccessibilityDelegate.AccessibilityRole[] values = ReactAccessibilityDelegate.AccessibilityRole.values();
            for (int i = 0; i < 27; i++) {
                ReactAccessibilityDelegate.AccessibilityRole accessibilityRole = values[i];
                if (accessibilityRole.name().equalsIgnoreCase(str)) {
                    t.setTag(R.id.accessibility_role, accessibilityRole);
                    return;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid accessibility role value: ", str));
        }
    }

    @ReactProp(name = "accessibilityValue")
    public void setAccessibilityValue(T t, ReadableMap readableMap) {
        if (readableMap != null) {
            t.setTag(R.id.accessibility_value, readableMap);
            if (readableMap.hasKey("text")) {
                updateViewContentDescription(t);
            }
        }
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(T t, int i) {
        t.setBackgroundColor(i);
    }

    public void setBorderBottomLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderBottomLeftRadius");
    }

    public void setBorderBottomRightRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderBottomRightRadius");
    }

    public void setBorderRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderRadius");
    }

    public void setBorderTopLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderTopLeftRadius");
    }

    public void setBorderTopRightRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderTopRightRadius");
    }

    @ReactProp(name = "elevation")
    public void setElevation(T t, float f) {
        float pixelFromDIP = PixelUtil.toPixelFromDIP(f);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        t.setElevation(pixelFromDIP);
    }

    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(T t, String str) {
        if (str == null || str.equals("auto")) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            t.setImportantForAccessibility(0);
        } else if (str.equals("yes")) {
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            t.setImportantForAccessibility(1);
        } else if (str.equals("no")) {
            AtomicInteger atomicInteger3 = ViewCompat.sNextGeneratedId;
            t.setImportantForAccessibility(2);
        } else if (str.equals("no-hide-descendants")) {
            AtomicInteger atomicInteger4 = ViewCompat.sNextGeneratedId;
            t.setImportantForAccessibility(4);
        }
    }

    @ReactProp(name = "nativeID")
    public void setNativeId(T t, String str) {
        t.setTag(R.id.view_tag_native_id, str);
        List<ReactFindViewUtil.OnViewFoundListener> list = ReactFindViewUtil.mOnViewFoundListeners;
        Object tag = t.getTag(R.id.view_tag_native_id);
        String str2 = tag instanceof String ? (String) tag : null;
        if (str2 != null) {
            Iterator<ReactFindViewUtil.OnViewFoundListener> it = ReactFindViewUtil.mOnViewFoundListeners.iterator();
            while (it.hasNext()) {
                ReactFindViewUtil.OnViewFoundListener next = it.next();
                if (str2.equals(next.getNativeId())) {
                    next.onViewFound(t);
                    it.remove();
                }
            }
            for (Map.Entry<ReactFindViewUtil.OnMultipleViewsFoundListener, Set<String>> entry : ReactFindViewUtil.mOnMultipleViewsFoundListener.entrySet()) {
                Set<String> value = entry.getValue();
                if (value != null && value.contains(str2)) {
                    entry.getKey().onViewFound(t, str2);
                }
            }
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(T t, float f) {
        t.setAlpha(f);
    }

    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(T t, boolean z) {
        t.setLayerType(z ? 2 : 0, null);
    }

    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(T t, float f) {
        t.setRotation(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(T t, float f) {
        t.setScaleX(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(T t, float f) {
        t.setScaleY(f);
    }

    @ReactProp(name = "testID")
    public void setTestId(T t, String str) {
        t.setTag(R.id.react_test_id, str);
        t.setTag(str);
    }

    @ReactProp(name = "transform")
    public void setTransform(T t, ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @ReactProp(defaultFloat = CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, name = "translateX")
    @Deprecated
    public void setTranslateX(T t, float f) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(defaultFloat = CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, name = "translateY")
    @Deprecated
    public void setTranslateY(T t, float f) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(name = "accessibilityState")
    public void setViewState(T t, ReadableMap readableMap) {
        if (readableMap != null) {
            t.setTag(R.id.accessibility_state, readableMap);
            t.setSelected(false);
            t.setEnabled(true);
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (nextKey.equals(STATE_BUSY) || nextKey.equals(STATE_EXPANDED) || (nextKey.equals(STATE_CHECKED) && readableMap.getType(STATE_CHECKED) == ReadableType.String)) {
                    updateViewContentDescription(t);
                    return;
                } else if (t.isAccessibilityFocused()) {
                    t.sendAccessibilityEvent(1);
                }
            }
        }
    }

    @ReactProp(name = "zIndex")
    public void setZIndex(T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        ViewParent parent = t.getParent();
        if (parent instanceof ReactZIndexedViewGroup) {
            ((ReactZIndexedViewGroup) parent).updateDrawingOrder();
        }
    }
}
