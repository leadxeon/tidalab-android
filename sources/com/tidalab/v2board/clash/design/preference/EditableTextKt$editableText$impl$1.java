package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: EditableText.kt */
/* loaded from: classes.dex */
public final class EditableTextKt$editableText$impl$1 implements EditableTextPreference, ClickablePreference {
    public final /* synthetic */ ClickablePreference $$delegate_0;
    public final /* synthetic */ Integer $icon;
    public final /* synthetic */ PreferenceScreen $this_editableText;
    public final /* synthetic */ int $title;
    public CharSequence empty;
    public CharSequence placeholder;
    public String text;

    public EditableTextKt$editableText$impl$1(PreferenceScreen preferenceScreen, int i, Integer num) {
        this.$this_editableText = preferenceScreen;
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

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void setSummary(CharSequence charSequence) {
        this.$$delegate_0.setSummary(charSequence);
    }

    public void setText(String str) {
        this.text = str;
        if (str == null) {
            this.$$delegate_0.setSummary(this.placeholder);
            return;
        }
        if (str.length() == 0) {
            this.$$delegate_0.setSummary(this.empty);
        } else {
            this.$$delegate_0.setSummary(str);
        }
    }
}
