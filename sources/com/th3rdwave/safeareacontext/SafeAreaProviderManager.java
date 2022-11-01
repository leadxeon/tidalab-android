package com.th3rdwave.safeareacontext;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.horcrux.svg.PathParser;
import com.th3rdwave.safeareacontext.SafeAreaProvider;
import java.util.Map;
/* loaded from: classes.dex */
public class SafeAreaProviderManager extends ViewGroupManager<SafeAreaProvider> {
    private final ReactApplicationContext mContext;

    public SafeAreaProviderManager(ReactApplicationContext reactApplicationContext) {
        this.mContext = reactApplicationContext;
    }

    private Map<String, Object> getInitialWindowMetrics() {
        ViewGroup viewGroup;
        View findViewById;
        Activity currentActivity = this.mContext.getCurrentActivity();
        if (currentActivity == null || (viewGroup = (ViewGroup) currentActivity.getWindow().getDecorView()) == null || (findViewById = viewGroup.findViewById(16908290)) == null) {
            return null;
        }
        EdgeInsets safeAreaInsets = PathParser.getSafeAreaInsets(viewGroup);
        Rect frame = PathParser.getFrame(viewGroup, findViewById);
        if (safeAreaInsets == null || frame == null) {
            return null;
        }
        return R$style.of("insets", R$style.of("top", Float.valueOf(PixelUtil.toDIPFromPixel(safeAreaInsets.top)), "right", Float.valueOf(PixelUtil.toDIPFromPixel(safeAreaInsets.right)), "bottom", Float.valueOf(PixelUtil.toDIPFromPixel(safeAreaInsets.bottom)), "left", Float.valueOf(PixelUtil.toDIPFromPixel(safeAreaInsets.left))), "frame", R$style.of("x", Float.valueOf(PixelUtil.toDIPFromPixel(frame.x)), "y", Float.valueOf(PixelUtil.toDIPFromPixel(frame.y)), "width", Float.valueOf(PixelUtil.toDIPFromPixel(frame.width)), "height", Float.valueOf(PixelUtil.toDIPFromPixel(frame.height))));
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("topInsetsChange", R$style.of("registrationName", "onInsetsChange"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return R$style.of("initialWindowMetrics", getInitialWindowMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCSafeAreaProvider";
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, SafeAreaProvider safeAreaProvider) {
        final EventDispatcher eventDispatcher = ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        safeAreaProvider.setOnInsetsChangeListener(new SafeAreaProvider.OnInsetsChangeListener(this) { // from class: com.th3rdwave.safeareacontext.SafeAreaProviderManager.1
            @Override // com.th3rdwave.safeareacontext.SafeAreaProvider.OnInsetsChangeListener
            public void onInsetsChange(SafeAreaProvider safeAreaProvider2, EdgeInsets edgeInsets, Rect rect) {
                eventDispatcher.dispatchEvent(new InsetsChangeEvent(safeAreaProvider2.getId(), edgeInsets, rect));
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public SafeAreaProvider createViewInstance(ThemedReactContext themedReactContext) {
        return new SafeAreaProvider(themedReactContext);
    }
}
