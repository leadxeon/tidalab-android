package com.facebook.react.views.swiperefresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidSwipeRefreshLayoutManagerDelegate;
import java.util.Map;
@ReactModule(name = SwipeRefreshLayoutManager.REACT_CLASS)
/* loaded from: classes.dex */
public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout> {
    public static final String REACT_CLASS = "AndroidSwipeRefreshLayout";
    private final ViewManagerDelegate<ReactSwipeRefreshLayout> mDelegate = new AndroidSwipeRefreshLayoutManagerDelegate(this);

    /* renamed from: com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements SwipeRefreshLayout.OnRefreshListener {
        public final /* synthetic */ ThemedReactContext val$reactContext;
        public final /* synthetic */ ReactSwipeRefreshLayout val$view;

        public AnonymousClass1(SwipeRefreshLayoutManager swipeRefreshLayoutManager, ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
            this.val$reactContext = themedReactContext;
            this.val$view = reactSwipeRefreshLayout;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactSwipeRefreshLayout> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("topRefresh", R$style.of("registrationName", "onRefresh"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return R$style.of("SIZE", R$style.of("DEFAULT", 1, "LARGE", 0));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public void setNativeRefreshing(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        reactSwipeRefreshLayout.setOnRefreshListener(new AnonymousClass1(this, themedReactContext, reactSwipeRefreshLayout));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactSwipeRefreshLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactSwipeRefreshLayout(themedReactContext);
    }

    public void receiveCommand(ReactSwipeRefreshLayout reactSwipeRefreshLayout, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativeRefreshing") && readableArray != null) {
            setRefreshing(reactSwipeRefreshLayout, readableArray.getBoolean(0));
        }
    }

    @ReactProp(customType = "ColorArray", name = "colors")
    public void setColors(ReactSwipeRefreshLayout reactSwipeRefreshLayout, ReadableArray readableArray) {
        if (readableArray != null) {
            int[] iArr = new int[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                iArr[i] = readableArray.getInt(i);
            }
            reactSwipeRefreshLayout.setColorSchemeColors(iArr);
            return;
        }
        reactSwipeRefreshLayout.setColorSchemeColors(new int[0]);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        reactSwipeRefreshLayout.setEnabled(z);
    }

    @ReactProp(customType = "Color", name = "progressBackgroundColor")
    public void setProgressBackgroundColor(ReactSwipeRefreshLayout reactSwipeRefreshLayout, Integer num) {
        reactSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(num == null ? 0 : num.intValue());
    }

    @ReactProp(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(ReactSwipeRefreshLayout reactSwipeRefreshLayout, float f) {
        reactSwipeRefreshLayout.setProgressViewOffset(f);
    }

    @ReactProp(name = "refreshing")
    public void setRefreshing(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        reactSwipeRefreshLayout.setRefreshing(z);
    }

    public void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, int i) {
        reactSwipeRefreshLayout.setSize(i);
    }

    @ReactProp(name = "size")
    public void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, Dynamic dynamic) {
        if (dynamic.isNull()) {
            reactSwipeRefreshLayout.setSize(1);
        } else if (dynamic.getType() == ReadableType.Number) {
            reactSwipeRefreshLayout.setSize(dynamic.asInt());
        } else if (dynamic.getType() == ReadableType.String) {
            String asString = dynamic.asString();
            if (asString.equals("default")) {
                reactSwipeRefreshLayout.setSize(1);
            } else if (asString.equals("large")) {
                reactSwipeRefreshLayout.setSize(0);
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Size must be 'default' or 'large', received: ", asString));
            }
        } else {
            throw new IllegalArgumentException("Size must be 'default' or 'large'");
        }
    }
}
