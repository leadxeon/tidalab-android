package com.facebook.react.views.picker;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidDropdownPickerManagerDelegate;
@ReactModule(name = ReactDropdownPickerManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactDropdownPickerManager extends ReactPickerManager {
    public static final String REACT_CLASS = "AndroidDropdownPicker";
    private final ViewManagerDelegate<ReactPicker> mDelegate = new AndroidDropdownPickerManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactPicker> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(customType = "Color", name = "color")
    public /* bridge */ /* synthetic */ void setColor(View view, Integer num) {
        super.setColor((ReactPicker) view, num);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public /* bridge */ /* synthetic */ void setEnabled(View view, boolean z) {
        super.setEnabled((ReactPicker) view, z);
    }

    @ReactProp(name = DialogModule.KEY_ITEMS)
    public /* bridge */ /* synthetic */ void setItems(View view, ReadableArray readableArray) {
        super.setItems((ReactPicker) view, readableArray);
    }

    @ReactProp(name = "prompt")
    public /* bridge */ /* synthetic */ void setPrompt(View view, String str) {
        super.setPrompt((ReactPicker) view, str);
    }

    @ReactProp(name = "selected")
    public /* bridge */ /* synthetic */ void setSelected(View view, int i) {
        super.setSelected((ReactPicker) view, i);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactPicker createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactPicker(themedReactContext, 1);
    }
}
