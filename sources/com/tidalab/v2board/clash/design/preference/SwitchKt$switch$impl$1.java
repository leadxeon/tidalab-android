package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBinding;
/* compiled from: Switch.kt */
/* loaded from: classes.dex */
public final class SwitchKt$switch$impl$1 implements SwitchPreference {
    public final /* synthetic */ PreferenceSwitchBinding $binding;
    public OnChangedListener listener;

    public SwitchKt$switch$impl$1(PreferenceSwitchBinding preferenceSwitchBinding) {
        this.$binding = preferenceSwitchBinding;
    }

    @Override // com.tidalab.v2board.clash.design.preference.Preference
    public View getView() {
        return this.$binding.mRoot;
    }

    @Override // com.tidalab.v2board.clash.design.preference.Preference
    public void setEnabled(boolean z) {
        this.$binding.mRoot.setEnabled(z);
        this.$binding.mRoot.setFocusable(z);
        this.$binding.mRoot.setClickable(z);
        this.$binding.mRoot.setAlpha(z ? 1.0f : 0.33f);
    }

    @Override // com.tidalab.v2board.clash.design.preference.SwitchPreference
    public void setListener(OnChangedListener onChangedListener) {
        this.listener = onChangedListener;
    }
}
