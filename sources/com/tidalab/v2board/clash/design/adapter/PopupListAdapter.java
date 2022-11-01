package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
/* compiled from: PopupListAdapter.kt */
/* loaded from: classes.dex */
public final class PopupListAdapter extends BaseAdapter {
    public final int colorControlNormal;
    public final int colorOnPrimary;
    public final int colorPrimary;
    public final Context context;
    public final int selected;
    public final List<CharSequence> texts;

    /* JADX WARN: Multi-variable type inference failed */
    public PopupListAdapter(Context context, List<? extends CharSequence> list, int i) {
        this.context = context;
        this.texts = list;
        this.selected = i;
        this.colorPrimary = InputKt.resolveThemedColor(context, R.attr.colorPrimary);
        this.colorOnPrimary = InputKt.resolveThemedColor(context, R.attr.colorOnPrimary);
        this.colorControlNormal = InputKt.resolveThemedColor(context, R.attr.colorControlNormal);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.texts.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.texts.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return this.texts.get(i).hashCode();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(17367043, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(16908308);
        textView.setText(this.texts.get(i));
        if (i == this.selected) {
            textView.setBackgroundColor(Color.argb(200, Color.red(this.colorPrimary), Color.green(this.colorPrimary), Color.blue(this.colorPrimary)));
            textView.setTextColor(this.colorOnPrimary);
        } else {
            textView.setBackgroundColor(0);
            textView.setTextColor(this.colorControlNormal);
        }
        return view;
    }
}
