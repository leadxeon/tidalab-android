package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: SelectableList.kt */
/* loaded from: classes.dex */
public final class SelectableListKt$selectableList$impl$1 implements SelectableListPreference<T>, ClickablePreference {
    public final /* synthetic */ ClickablePreference $$delegate_0;
    public final /* synthetic */ Integer $icon;
    public final /* synthetic */ PreferenceScreen $this_selectableList;
    public final /* synthetic */ int $title;
    public final /* synthetic */ Integer[] $valuesText;
    public OnChangedListener listener;
    public int selected;

    public SelectableListKt$selectableList$impl$1(PreferenceScreen preferenceScreen, int i, Integer num, Integer[] numArr) {
        this.$this_selectableList = preferenceScreen;
        this.$title = i;
        this.$icon = num;
        this.$valuesText = numArr;
        this.$$delegate_0 = InputKt.clickable$default(preferenceScreen, i, num, null, null, 12);
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void clicked(Function0<Unit> function0) {
        this.$$delegate_0.clicked(function0);
    }

    @Override // com.tidalab.v2board.clash.design.preference.SelectableListPreference
    public OnChangedListener getListener() {
        return this.listener;
    }

    public int getSelected() {
        return this.selected;
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public CharSequence getTitle() {
        return this.$$delegate_0.getTitle();
    }

    @Override // com.tidalab.v2board.clash.design.preference.Preference
    public View getView() {
        return this.$$delegate_0.getView();
    }

    @Override // com.tidalab.v2board.clash.design.preference.Preference
    public void setEnabled(boolean z) {
        this.$$delegate_0.setEnabled(z);
    }

    @Override // com.tidalab.v2board.clash.design.preference.SelectableListPreference
    public void setListener(OnChangedListener onChangedListener) {
        this.listener = onChangedListener;
    }

    @Override // com.tidalab.v2board.clash.design.preference.SelectableListPreference
    public void setSelected(int i) {
        this.selected = i;
        this.$$delegate_0.setSummary(this.$this_selectableList.getContext().getText(this.$valuesText[i].intValue()));
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void setSummary(CharSequence charSequence) {
        this.$$delegate_0.setSummary(charSequence);
    }
}
