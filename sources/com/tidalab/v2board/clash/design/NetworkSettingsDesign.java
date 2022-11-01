package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsCommonBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.preference.OnChangedListener;
import com.tidalab.v2board.clash.design.preference.Preference;
import com.tidalab.v2board.clash.design.preference.ScreenKt$preferenceScreen$impl$1;
import com.tidalab.v2board.clash.design.preference.SwitchKt;
import com.tidalab.v2board.clash.design.preference.SwitchKt$switch$impl$1;
import com.tidalab.v2board.clash.design.preference.SwitchPreference;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.AccessControlMode;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NetworkSettingsDesign.kt */
/* loaded from: classes.dex */
public final class NetworkSettingsDesign extends Design<Request> {
    public final DesignSettingsCommonBinding binding;

    /* compiled from: NetworkSettingsDesign.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.NetworkSettingsDesign$1", f = "NetworkSettingsDesign.kt", l = {127}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.NetworkSettingsDesign$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object showToast;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                NetworkSettingsDesign networkSettingsDesign = NetworkSettingsDesign.this;
                ToastDuration toastDuration = ToastDuration.Indefinite;
                this.label = 1;
                showToast = networkSettingsDesign.showToast((int) R.string.options_unavailable, toastDuration, (r5 & 4) != 0 ? Design$showToast$2.INSTANCE : null, this);
                if (showToast == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: NetworkSettingsDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        StartAccessControlList
    }

    public NetworkSettingsDesign(Context context, final UiStore uiStore, final ServiceStore serviceStore, boolean z) {
        super(context);
        DesignSettingsCommonBinding inflate = DesignSettingsCommonBinding.inflate(LayoutInflater.from(context), InputKt.getRoot(context), false);
        this.binding = inflate;
        inflate.setSurface(this.surface);
        InputKt.applyFrom(inflate.activityBarLayout, context);
        InputKt.bindAppBarElevation(inflate.scrollRoot, inflate.activityBarLayout);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ScreenKt$preferenceScreen$impl$1 screenKt$preferenceScreen$impl$1 = new ScreenKt$preferenceScreen$impl$1(this, context, linearLayout);
        ArrayList arrayList = new ArrayList();
        SwitchPreference switchPreference = SwitchKt.m10switch(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(uiStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$vpn$1
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return Boolean.valueOf(((UiStore) this.receiver).getEnableVpn());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                UiStore uiStore2 = (UiStore) this.receiver;
                uiStore2.enableVpn$delegate.setValue(uiStore2, UiStore.$$delegatedProperties[0], Boolean.valueOf(((Boolean) obj).booleanValue()));
            }
        }, Integer.valueOf((int) R.drawable.ic_baseline_vpn_lock), Integer.valueOf((int) R.string.route_system_traffic), Integer.valueOf((int) R.string.routing_via_vpn_service), new NetworkSettingsDesign$screen$1$vpn$2(arrayList, uiStore));
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.vpn_service_options);
        SwitchKt.switch$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$1
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return Boolean.valueOf(((ServiceStore) this.receiver).getBypassPrivateNetwork());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                serviceStore2.bypassPrivateNetwork$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[1], Boolean.valueOf(((Boolean) obj).booleanValue()));
            }
        }, null, Integer.valueOf((int) R.string.bypass_private_network), Integer.valueOf((int) R.string.bypass_private_network_summary), new NetworkSettingsDesign$screen$1$2(arrayList), 2);
        SwitchKt.switch$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$3
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                return Boolean.valueOf(((Boolean) serviceStore2.dnsHijacking$delegate.getValue(serviceStore2, ServiceStore.$$delegatedProperties[4])).booleanValue());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                serviceStore2.dnsHijacking$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[4], Boolean.valueOf(((Boolean) obj).booleanValue()));
            }
        }, null, Integer.valueOf((int) R.string.dns_hijacking), Integer.valueOf((int) R.string.dns_hijacking_summary), new NetworkSettingsDesign$screen$1$4(arrayList), 2);
        SwitchKt.switch$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$5
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                return Boolean.valueOf(((Boolean) serviceStore2.blockLoopback$delegate.getValue(serviceStore2, ServiceStore.$$delegatedProperties[6])).booleanValue());
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                serviceStore2.blockLoopback$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[6], Boolean.valueOf(((Boolean) obj).booleanValue()));
            }
        }, null, Integer.valueOf((int) R.string.block_loopback), Integer.valueOf((int) R.string.block_loopback_summary), new NetworkSettingsDesign$screen$1$6(arrayList), 2);
        if (Build.VERSION.SDK_INT >= 29) {
            SwitchKt.switch$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$7
                @Override // kotlin.reflect.KMutableProperty0
                public Object get() {
                    ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                    return Boolean.valueOf(((Boolean) serviceStore2.systemProxy$delegate.getValue(serviceStore2, ServiceStore.$$delegatedProperties[5])).booleanValue());
                }

                @Override // kotlin.reflect.KMutableProperty0
                public void set(Object obj) {
                    ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                    serviceStore2.systemProxy$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[5], Boolean.valueOf(((Boolean) obj).booleanValue()));
                }
            }, null, Integer.valueOf((int) R.string.system_proxy), Integer.valueOf((int) R.string.system_proxy_summary), new NetworkSettingsDesign$screen$1$8(arrayList), 2);
        }
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(serviceStore) { // from class: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$9
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                return (AccessControlMode) serviceStore2.accessControlMode$delegate.getValue(serviceStore2, ServiceStore.$$delegatedProperties[2]);
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ServiceStore serviceStore2 = (ServiceStore) this.receiver;
                serviceStore2.accessControlMode$delegate.setValue(serviceStore2, ServiceStore.$$delegatedProperties[2], (AccessControlMode) obj);
            }
        }, AccessControlMode.values(), new Integer[]{Integer.valueOf((int) R.string.allow_all_apps), Integer.valueOf((int) R.string.allow_selected_apps), Integer.valueOf((int) R.string.deny_selected_apps)}, R.string.access_control_mode, null, new NetworkSettingsDesign$screen$1$10(arrayList), 16);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.access_control_packages, null, Integer.valueOf((int) R.string.access_control_packages_summary), new NetworkSettingsDesign$screen$1$11(arrayList, this), 2);
        if (z) {
            ((SwitchKt$switch$impl$1) switchPreference).setEnabled(false);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Preference) it.next()).setEnabled(false);
            }
        } else {
            OnChangedListener onChangedListener = ((SwitchKt$switch$impl$1) switchPreference).listener;
            if (onChangedListener != null) {
                onChangedListener.onChanged();
            }
        }
        Unit unit = Unit.INSTANCE;
        inflate.content.addView(screenKt$preferenceScreen$impl$1.$root);
        if (z) {
            InputKt.launch$default(this, null, null, new AnonymousClass1(null), 3, null);
        }
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
