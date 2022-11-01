package com.facebook.react.views.picker;

import android.widget.SpinnerAdapter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.picker.ReactPicker;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker> {

    /* loaded from: classes.dex */
    public static class PickerEventEmitter implements ReactPicker.OnSelectListener {
        public final EventDispatcher mEventDispatcher;
        public final ReactPicker mReactPicker;

        public PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
            this.mReactPicker = reactPicker;
            this.mEventDispatcher = eventDispatcher;
        }
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker reactPicker, Integer num) {
        reactPicker.setStagedPrimaryTextColor(num);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker reactPicker, boolean z) {
        reactPicker.setEnabled(z);
    }

    @ReactProp(name = DialogModule.KEY_ITEMS)
    public void setItems(ReactPicker reactPicker, ReadableArray readableArray) {
        ArrayList arrayList;
        if (readableArray == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(readableArray.size());
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList2.add(new ReactPickerItem(readableArray.getMap(i)));
            }
            arrayList = arrayList2;
        }
        reactPicker.setStagedItems(arrayList);
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, String str) {
        reactPicker.setPrompt(str);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, ReactPicker reactPicker) {
        reactPicker.setOnSelectListener(new PickerEventEmitter(reactPicker, ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    public void onAfterUpdateTransaction(ReactPicker reactPicker) {
        super.onAfterUpdateTransaction((ReactPickerManager) reactPicker);
        reactPicker.setOnItemSelectedListener(null);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        int selectedItemPosition = reactPicker.getSelectedItemPosition();
        List<ReactPickerItem> list = reactPicker.mStagedItems;
        if (!(list == null || list == reactPicker.mItems)) {
            reactPicker.mItems = list;
            reactPicker.mStagedItems = null;
            if (reactPickerAdapter == null) {
                reactPickerAdapter = new ReactPickerAdapter(reactPicker.getContext(), reactPicker.mItems);
                reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter);
            } else {
                reactPickerAdapter.clear();
                reactPickerAdapter.addAll(reactPicker.mItems);
                reactPickerAdapter.notifyDataSetChanged();
            }
        }
        Integer num = reactPicker.mStagedSelection;
        if (!(num == null || num.intValue() == selectedItemPosition)) {
            reactPicker.setSelection(reactPicker.mStagedSelection.intValue(), false);
            reactPicker.mStagedSelection = null;
        }
        Integer num2 = reactPicker.mStagedPrimaryTextColor;
        if (!(num2 == null || reactPickerAdapter == null || num2 == reactPickerAdapter.mPrimaryTextColor)) {
            reactPickerAdapter.mPrimaryTextColor = num2;
            reactPickerAdapter.notifyDataSetChanged();
            reactPicker.mStagedPrimaryTextColor = null;
        }
        reactPicker.setOnItemSelectedListener(reactPicker.mItemSelectedListener);
    }

    public void receiveCommand(ReactPicker reactPicker, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativeSelectedPosition") && readableArray != null) {
            reactPicker.setImmediateSelection(readableArray.getInt(0));
        }
    }
}
