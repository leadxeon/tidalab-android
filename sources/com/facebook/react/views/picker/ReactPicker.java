package com.facebook.react.views.picker;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;
import com.facebook.react.views.picker.ReactPickerManager;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
/* loaded from: classes.dex */
public class ReactPicker extends AppCompatSpinner {
    public List<ReactPickerItem> mItems;
    public int mMode;
    public OnSelectListener mOnSelectListener;
    public List<ReactPickerItem> mStagedItems;
    public Integer mStagedPrimaryTextColor;
    public Integer mStagedSelection;
    public final AdapterView.OnItemSelectedListener mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            OnSelectListener onSelectListener = ReactPicker.this.mOnSelectListener;
            if (onSelectListener != null) {
                ReactPickerManager.PickerEventEmitter pickerEventEmitter = (ReactPickerManager.PickerEventEmitter) onSelectListener;
                pickerEventEmitter.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(pickerEventEmitter.mReactPicker.getId(), i));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            OnSelectListener onSelectListener = ReactPicker.this.mOnSelectListener;
            if (onSelectListener != null) {
                ReactPickerManager.PickerEventEmitter pickerEventEmitter = (ReactPickerManager.PickerEventEmitter) onSelectListener;
                pickerEventEmitter.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(pickerEventEmitter.mReactPicker.getId(), -1));
            }
        }
    };
    public final Runnable measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
        @Override // java.lang.Runnable
        public void run() {
            ReactPicker reactPicker = ReactPicker.this;
            reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
            ReactPicker reactPicker2 = ReactPicker.this;
            reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
        }
    };

    /* loaded from: classes.dex */
    public interface OnSelectListener {
    }

    public ReactPicker(Context context, int i) {
        super(context, null, R.attr.spinnerStyle, i);
        this.mMode = 0;
        this.mMode = i;
    }

    public int getMode() {
        return this.mMode;
    }

    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    @Override // android.widget.AbsSpinner, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public void setImmediateSelection(int i) {
        if (i != getSelectedItemPosition()) {
            setOnItemSelectedListener(null);
            setSelection(i, false);
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setStagedItems(List<ReactPickerItem> list) {
        this.mStagedItems = list;
    }

    public void setStagedPrimaryTextColor(Integer num) {
        this.mStagedPrimaryTextColor = num;
    }

    public void setStagedSelection(int i) {
        this.mStagedSelection = Integer.valueOf(i);
    }
}
