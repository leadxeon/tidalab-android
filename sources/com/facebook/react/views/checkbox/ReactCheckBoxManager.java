package com.facebook.react.views.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.TypedValue;
import android.widget.CompoundButton;
import androidx.appcompat.widget.TintContextWrapper;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public class ReactCheckBoxManager extends SimpleViewManager<ReactCheckBox> {
    private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new CompoundButton.OnCheckedChangeListener() { // from class: com.facebook.react.views.checkbox.ReactCheckBoxManager.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ReactContext reactContext;
            Context context = compoundButton.getContext();
            if (context instanceof TintContextWrapper) {
                reactContext = (ReactContext) ((TintContextWrapper) context).getBaseContext();
            } else {
                reactContext = (ReactContext) compoundButton.getContext();
            }
            ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactCheckBoxEvent(compoundButton.getId(), z));
        }
    };
    public static final String REACT_CLASS = "AndroidCheckBox";

    private static int getIdentifier(Context context, String str) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }

    private static int getThemeColor(Context context, String str) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(getIdentifier(context, str), typedValue, true);
        return typedValue.data;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactCheckBox reactCheckBox, boolean z) {
        reactCheckBox.setEnabled(z);
    }

    @ReactProp(name = "on")
    public void setOn(ReactCheckBox reactCheckBox, boolean z) {
        reactCheckBox.setOnCheckedChangeListener(null);
        reactCheckBox.setOn(z);
        reactCheckBox.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    @ReactProp(name = "tintColors")
    public void setTintColors(ReactCheckBox reactCheckBox, ReadableMap readableMap) {
        int i;
        int i2;
        if (readableMap == null || !readableMap.hasKey("true")) {
            i = getThemeColor(reactCheckBox.getContext(), "colorAccent");
        } else {
            i = readableMap.getInt("true");
        }
        if (readableMap == null || !readableMap.hasKey("false")) {
            i2 = getThemeColor(reactCheckBox.getContext(), "colorPrimaryDark");
        } else {
            i2 = readableMap.getInt("false");
        }
        reactCheckBox.setButtonTintList(new ColorStateList(new int[][]{new int[]{16842912}, new int[]{-16842912}}, new int[]{i, i2}));
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, ReactCheckBox reactCheckBox) {
        reactCheckBox.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactCheckBox createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactCheckBox(themedReactContext);
    }

    public void receiveCommand(ReactCheckBox reactCheckBox, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativeValue") && readableArray != null) {
            setOn(reactCheckBox, readableArray.getBoolean(0));
        }
    }
}
