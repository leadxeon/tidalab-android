package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: EditableTextList.kt */
/* loaded from: classes.dex */
public final class EditableTextListKt$editableTextList$impl$1 implements EditableTextListPreference<T>, ClickablePreference {
    public final /* synthetic */ ClickablePreference $$delegate_0;
    public final /* synthetic */ Integer $icon;
    public final /* synthetic */ PreferenceScreen $this_editableTextList;
    public final /* synthetic */ int $title;
    public List<? extends T> list;
    public CharSequence placeholder;

    public EditableTextListKt$editableTextList$impl$1(PreferenceScreen preferenceScreen, int i, Integer num) {
        this.$this_editableTextList = preferenceScreen;
        this.$title = i;
        this.$icon = num;
        this.$$delegate_0 = InputKt.clickable$default(preferenceScreen, i, num, null, null, 12);
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void clicked(Function0<Unit> function0) {
        this.$$delegate_0.clicked(function0);
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

    public void setList(List<? extends T> list) {
        this.list = list;
        if (list == 0) {
            this.$$delegate_0.setSummary(this.placeholder);
        } else if (list.isEmpty()) {
            this.$$delegate_0.setSummary(this.$this_editableTextList.getContext().getString(R.string.empty));
        } else {
            this.$$delegate_0.setSummary(this.$this_editableTextList.getContext().getString(R.string.format_elements, Integer.valueOf(list.size())));
        }
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void setSummary(CharSequence charSequence) {
        this.$$delegate_0.setSummary(charSequence);
    }
}
