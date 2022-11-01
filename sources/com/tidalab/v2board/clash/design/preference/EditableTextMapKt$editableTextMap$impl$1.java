package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: EditableTextMap.kt */
/* loaded from: classes.dex */
public final class EditableTextMapKt$editableTextMap$impl$1 implements EditableTextMapPreference<K, V>, ClickablePreference {
    public final /* synthetic */ ClickablePreference $$delegate_0;
    public final /* synthetic */ Integer $icon;
    public final /* synthetic */ PreferenceScreen $this_editableTextMap;
    public final /* synthetic */ int $title;
    public Map<K, ? extends V> map;
    public CharSequence placeholder;

    public EditableTextMapKt$editableTextMap$impl$1(PreferenceScreen preferenceScreen, int i, Integer num) {
        this.$this_editableTextMap = preferenceScreen;
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

    public void setMap(Map<K, ? extends V> map) {
        this.map = map;
        if (map == 0) {
            this.$$delegate_0.setSummary(this.placeholder);
        } else if (map.isEmpty()) {
            this.$$delegate_0.setSummary(this.$this_editableTextMap.getContext().getString(R.string.empty));
        } else {
            this.$$delegate_0.setSummary(this.$this_editableTextMap.getContext().getString(R.string.format_elements, Integer.valueOf(map.size())));
        }
    }

    @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
    public void setSummary(CharSequence charSequence) {
        this.$$delegate_0.setSummary(charSequence);
    }
}
