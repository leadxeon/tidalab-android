package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import java.util.LinkedHashSet;
import java.util.Set;
/* compiled from: ObservableScrollView.kt */
/* loaded from: classes.dex */
public final class ObservableScrollView extends ScrollView {
    public final Set<OnScrollChangedListener> scrollChangedListeners = new LinkedHashSet();

    /* compiled from: ObservableScrollView.kt */
    /* loaded from: classes.dex */
    public interface OnScrollChangedListener {
        void onChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        for (OnScrollChangedListener onScrollChangedListener : this.scrollChangedListeners) {
            onScrollChangedListener.onChanged(this, i, i2, i3, i4);
        }
    }
}
