package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.View;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.util.Locale;
import java.util.Map;
@ReactModule(name = ReactViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactViewManager extends ReactClippingViewManager<ReactViewGroup> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    private static final String HOTSPOT_UPDATE_KEY = "hotspotUpdate";
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3, 4, 5};

    private void handleHotspotUpdate(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() != 2) {
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
        }
        reactViewGroup.drawableHotspotChanged(PixelUtil.toPixelFromDIP(readableArray.getDouble(0)), PixelUtil.toPixelFromDIP(readableArray.getDouble(1)));
    }

    private void handleSetPressed(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() != 1) {
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
        }
        reactViewGroup.setPressed(readableArray.getBoolean(0));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return R$style.of(HOTSPOT_UPDATE_KEY, 1, "setPressed", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultInt = -1, name = "nextFocusDown")
    public void nextFocusDown(ReactViewGroup reactViewGroup, int i) {
        reactViewGroup.setNextFocusDownId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusForward")
    public void nextFocusForward(ReactViewGroup reactViewGroup, int i) {
        reactViewGroup.setNextFocusForwardId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusLeft")
    public void nextFocusLeft(ReactViewGroup reactViewGroup, int i) {
        reactViewGroup.setNextFocusLeftId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusRight")
    public void nextFocusRight(ReactViewGroup reactViewGroup, int i) {
        reactViewGroup.setNextFocusRightId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusUp")
    public void nextFocusUp(ReactViewGroup reactViewGroup, int i) {
        reactViewGroup.setNextFocusUpId(i);
    }

    @ReactProp(name = "accessible")
    public void setAccessible(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setFocusable(z);
    }

    @ReactProp(name = "backfaceVisibility")
    public void setBackfaceVisibility(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setBackfaceVisibility(str);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor", "borderStartColor", "borderEndColor"})
    public void setBorderColor(ReactViewGroup reactViewGroup, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & 16777215;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactViewGroup.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius", "borderTopStartRadius", "borderTopEndRadius", "borderBottomStartRadius", "borderBottomEndRadius"})
    public void setBorderRadius(ReactViewGroup reactViewGroup, int i, float f) {
        if (!R$style.isUndefined(f) && f < 0.0f) {
            f = Float.NaN;
        }
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactViewGroup.setBorderRadius(f);
        } else {
            reactViewGroup.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth", "borderStartWidth", "borderEndWidth"})
    public void setBorderWidth(ReactViewGroup reactViewGroup, int i, float f) {
        if (!R$style.isUndefined(f) && f < 0.0f) {
            f = Float.NaN;
        }
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactViewGroup.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(ReactViewGroup reactViewGroup, boolean z) {
    }

    @ReactProp(name = "focusable")
    public void setFocusable(final ReactViewGroup reactViewGroup, boolean z) {
        if (z) {
            reactViewGroup.setOnClickListener(new View.OnClickListener(this) { // from class: com.facebook.react.views.view.ReactViewManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ((UIManagerModule) ((ReactContext) reactViewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ViewGroupClickEvent(reactViewGroup.getId()));
                }
            });
            reactViewGroup.setFocusable(true);
            return;
        }
        reactViewGroup.setOnClickListener(null);
        reactViewGroup.setClickable(false);
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        if (readableMap == null) {
            reactViewGroup.setHitSlopRect(null);
            return;
        }
        int i = 0;
        int pixelFromDIP = readableMap.hasKey("left") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("left")) : 0;
        int pixelFromDIP2 = readableMap.hasKey("top") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("top")) : 0;
        int pixelFromDIP3 = readableMap.hasKey("right") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("right")) : 0;
        if (readableMap.hasKey("bottom")) {
            i = (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("bottom"));
        }
        reactViewGroup.setHitSlopRect(new Rect(pixelFromDIP, pixelFromDIP2, pixelFromDIP3, i));
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        reactViewGroup.setTranslucentBackgroundDrawable(readableMap == null ? null : ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap));
    }

    @ReactProp(name = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        reactViewGroup.setForeground(readableMap == null ? null : ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap));
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setNeedsOffscreenAlphaCompositing(z);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setOverflow(str);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(ReactViewGroup reactViewGroup, String str) {
        if (str == null) {
            reactViewGroup.setPointerEvents(PointerEvents.AUTO);
        } else {
            reactViewGroup.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "hasTVPreferredFocus")
    public void setTVPreferredFocus(ReactViewGroup reactViewGroup, boolean z) {
        if (z) {
            reactViewGroup.setFocusable(true);
            reactViewGroup.setFocusableInTouchMode(true);
            reactViewGroup.requestFocus();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactViewGroup(themedReactContext);
    }

    public void setOpacity(ReactViewGroup reactViewGroup, float f) {
        reactViewGroup.setOpacityIfPossible(f);
    }

    public void setTransform(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        super.setTransform((ReactViewManager) reactViewGroup, readableArray);
        reactViewGroup.setBackfaceVisibilityDependantOpacity();
    }

    public void receiveCommand(ReactViewGroup reactViewGroup, int i, ReadableArray readableArray) {
        if (i == 1) {
            handleHotspotUpdate(reactViewGroup, readableArray);
        } else if (i == 2) {
            handleSetPressed(reactViewGroup, readableArray);
        }
    }

    public void receiveCommand(ReactViewGroup reactViewGroup, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setPressed")) {
            handleSetPressed(reactViewGroup, readableArray);
        } else if (str.equals(HOTSPOT_UPDATE_KEY)) {
            handleHotspotUpdate(reactViewGroup, readableArray);
        }
    }
}
