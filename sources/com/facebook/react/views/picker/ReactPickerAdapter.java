package com.facebook.react.views.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.recyclerview.R$dimen;
import java.util.List;
/* loaded from: classes.dex */
public class ReactPickerAdapter extends ArrayAdapter<ReactPickerItem> {
    public final LayoutInflater mInflater;
    public Integer mPrimaryTextColor;

    public ReactPickerAdapter(Context context, List<ReactPickerItem> list) {
        super(context, 0, list);
        Object systemService = context.getSystemService("layout_inflater");
        R$dimen.assertNotNull(systemService);
        this.mInflater = (LayoutInflater) systemService;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, true);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, false);
    }

    public final View getView(int i, View view, ViewGroup viewGroup, boolean z) {
        Integer num;
        ReactPickerItem item = getItem(i);
        boolean z2 = false;
        if (view == null) {
            view = this.mInflater.inflate(z ? 17367049 : 17367048, viewGroup, false);
            view.setTag(((TextView) view).getTextColors());
            z2 = true;
        }
        TextView textView = (TextView) view;
        textView.setText(item.label);
        if (z || (num = this.mPrimaryTextColor) == null) {
            Integer num2 = item.color;
            if (num2 != null) {
                textView.setTextColor(num2.intValue());
            } else if (textView.getTag() != null && !z2) {
                textView.setTextColor((ColorStateList) textView.getTag());
            }
        } else {
            textView.setTextColor(num.intValue());
        }
        return textView;
    }
}
