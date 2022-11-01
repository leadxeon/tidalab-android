package com.tidalab.v2board.clash.design.preference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBinding;
import com.tidalab.v2board.clash.design.databinding.PreferenceTipsBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: Switch.kt */
/* loaded from: classes.dex */
public final class SwitchKt {
    /* renamed from: switch */
    public static final SwitchPreference m10switch(PreferenceScreen preferenceScreen, KMutableProperty0<Boolean> kMutableProperty0, Integer num, Integer num2, Integer num3, Function1<? super SwitchPreference, Unit> function1) {
        LayoutInflater from = LayoutInflater.from(preferenceScreen.getContext());
        ViewGroup root = preferenceScreen.getRoot();
        int i = PreferenceSwitchBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        PreferenceSwitchBinding preferenceSwitchBinding = (PreferenceSwitchBinding) ViewDataBinding.inflateInternal(from, R.layout.preference_switch, root, false, null);
        SwitchKt$switch$impl$1 switchKt$switch$impl$1 = new SwitchKt$switch$impl$1(preferenceSwitchBinding);
        if (num != null) {
            preferenceSwitchBinding.iconView.setBackground(PathParser.getDrawableCompat(preferenceScreen.getContext(), num.intValue()));
        }
        if (num2 != null) {
            preferenceSwitchBinding.titleView.setText(preferenceScreen.getContext().getString(num2.intValue()));
        }
        if (num3 != null) {
            preferenceSwitchBinding.summaryView.setText(preferenceScreen.getContext().getString(num3.intValue()));
        }
        function1.invoke(switchKt$switch$impl$1);
        InputKt.addElement(preferenceScreen, switchKt$switch$impl$1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        InputKt.launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new SwitchKt$switch$2(preferenceSwitchBinding, kMutableProperty0, preferenceScreen, switchKt$switch$impl$1, null), 2, null);
        return switchKt$switch$impl$1;
    }

    public static /* synthetic */ SwitchPreference switch$default(PreferenceScreen preferenceScreen, KMutableProperty0 kMutableProperty0, Integer num, Integer num2, Integer num3, Function1 function1, int i) {
        Integer num4 = (i & 2) != 0 ? null : num;
        Integer num5 = (i & 4) != 0 ? null : num2;
        Integer num6 = (i & 8) != 0 ? null : num3;
        if ((i & 16) != 0) {
            function1 = SwitchKt$switch$1.INSTANCE;
        }
        return m10switch(preferenceScreen, kMutableProperty0, num4, num5, num6, function1);
    }

    public static TipsPreference tips$default(PreferenceScreen preferenceScreen, int i, Function1 function1, int i2) {
        TipsKt$tips$1 tipsKt$tips$1 = (i2 & 2) != 0 ? TipsKt$tips$1.INSTANCE : null;
        LayoutInflater from = LayoutInflater.from(preferenceScreen.getContext());
        ViewGroup root = InputKt.getRoot(preferenceScreen.getContext());
        int i3 = PreferenceTipsBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        final PreferenceTipsBinding preferenceTipsBinding = (PreferenceTipsBinding) ViewDataBinding.inflateInternal(from, R.layout.preference_tips, root, false, null);
        TipsPreference tipsKt$tips$impl$1 = new TipsPreference() { // from class: com.tidalab.v2board.clash.design.preference.TipsKt$tips$impl$1
            @Override // com.tidalab.v2board.clash.design.preference.Preference
            public View getView() {
                return PreferenceTipsBinding.this.mRoot;
            }

            @Override // com.tidalab.v2board.clash.design.preference.Preference
            public void setEnabled(boolean z) {
                PreferenceTipsBinding.this.mRoot.setEnabled(z);
            }
        };
        preferenceTipsBinding.tips.setText(InputKt.getHtml(preferenceScreen.getContext(), i));
        Objects.requireNonNull(tipsKt$tips$1);
        Unit unit = Unit.INSTANCE;
        InputKt.addElement(preferenceScreen, tipsKt$tips$impl$1);
        return tipsKt$tips$impl$1;
    }
}
