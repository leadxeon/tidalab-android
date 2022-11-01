package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsCommonBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.Behavior;
import com.tidalab.v2board.clash.design.model.DarkMode;
import com.tidalab.v2board.clash.design.preference.ScreenKt$preferenceScreen$impl$1;
import com.tidalab.v2board.clash.design.preference.SwitchKt;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import kotlin.Unit;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
/* compiled from: AppSettingsDesign.kt */
/* loaded from: classes.dex */
public final class AppSettingsDesign extends Design<Request> {
    public final DesignSettingsCommonBinding binding;

    /* compiled from: AppSettingsDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        ReCreateAllActivities
    }

    public AppSettingsDesign(Context context, final UiStore uiStore, final ServiceStore serviceStore, final Behavior behavior, boolean z) {
        super(context);
        DesignSettingsCommonBinding inflate = DesignSettingsCommonBinding.inflate(LayoutInflater.from(context), InputKt.getRoot(context), false);
        this.binding = inflate;
        inflate.setSurface(this.surface);
        InputKt.applyFrom(inflate.activityBarLayout, context);
        InputKt.bindAppBarElevation(inflate.scrollRoot, inflate.activityBarLayout);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ScreenKt$preferenceScreen$impl$1 screenKt$preferenceScreen$impl$1 = new ScreenKt$preferenceScreen$impl$1(this, context, linearLayout);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.behavior);
        SwitchKt.switch$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(behavior) { // from class: com.tidalab.v2board.clash.design.AppSettingsDesign$screen$1$1
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return Boolean.valueOf(((Behavior) this.receiver).getAutoRestart());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((Behavior) this.receiver).setAutoRestart(((Boolean) obj).booleanValue());
            }
        }, Integer.valueOf((int) R.drawable.ic_baseline_restore), Integer.valueOf((int) R.string.auto_restart), Integer.valueOf((int) R.string.allow_clash_auto_restart), null, 16);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.interface_);
        InputKt.selectableList(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(uiStore) { // from class: com.tidalab.v2board.clash.design.AppSettingsDesign$screen$1$2
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                UiStore uiStore2 = (UiStore) this.receiver;
                return (DarkMode) uiStore2.darkMode$delegate.getValue(uiStore2, UiStore.$$delegatedProperties[1]);
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                UiStore uiStore2 = (UiStore) this.receiver;
                uiStore2.darkMode$delegate.setValue(uiStore2, UiStore.$$delegatedProperties[1], (DarkMode) obj);
            }
        }, DarkMode.values(), new Integer[]{Integer.valueOf((int) R.string.follow_system_android_10), Integer.valueOf((int) R.string.always_light), Integer.valueOf((int) R.string.always_dark)}, R.string.dark_mode, Integer.valueOf((int) R.drawable.ic_baseline_brightness_4), new AppSettingsDesign$screen$1$3(this));
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.service);
        SwitchKt.m10switch(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.AppSettingsDesign$screen$1$4
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return Boolean.valueOf(((ServiceStore) this.receiver).getDynamicNotification());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                serviceStore2.dynamicNotification$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[7], Boolean.valueOf(((Boolean) obj).booleanValue()));
            }
        }, Integer.valueOf((int) R.drawable.ic_baseline_domain), Integer.valueOf((int) R.string.show_traffic), Integer.valueOf((int) R.string.show_traffic_summary), new AppSettingsDesign$screen$1$5(z));
        Unit unit = Unit.INSTANCE;
        inflate.content.addView(screenKt$preferenceScreen$impl$1.$root);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
