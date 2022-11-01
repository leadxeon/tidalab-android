package com.tidalab.v2board.clash.design.preference;
/* compiled from: SelectableList.kt */
/* loaded from: classes.dex */
public interface SelectableListPreference<T> extends ClickablePreference {
    OnChangedListener getListener();

    void setListener(OnChangedListener onChangedListener);

    void setSelected(int i);
}
