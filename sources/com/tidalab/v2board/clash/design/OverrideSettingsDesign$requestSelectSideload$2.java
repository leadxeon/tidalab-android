package com.tidalab.v2board.clash.design;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.adapter.SideloadProviderAdapter;
import com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBinding;
import com.tidalab.v2board.clash.design.dialog.FullScreenDialog;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.HttpUrl;
/* compiled from: OverrideSettingsDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2", f = "OverrideSettingsDesign.kt", l = {400}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class OverrideSettingsDesign$requestSelectSideload$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    public final /* synthetic */ List<AppInfo> $apps;
    public final /* synthetic */ String $initial;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public final /* synthetic */ OverrideSettingsDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsDesign$requestSelectSideload$2(OverrideSettingsDesign overrideSettingsDesign, List<AppInfo> list, String str, Continuation<? super OverrideSettingsDesign$requestSelectSideload$2> continuation) {
        super(2, continuation);
        this.this$0 = overrideSettingsDesign;
        this.$apps = list;
        this.$initial = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OverrideSettingsDesign$requestSelectSideload$2(this.this$0, this.$apps, this.$initial, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return new OverrideSettingsDesign$requestSelectSideload$2(this.this$0, this.$apps, this.$initial, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            OverrideSettingsDesign overrideSettingsDesign = this.this$0;
            List<AppInfo> list = this.$apps;
            final String str = this.$initial;
            this.L$0 = overrideSettingsDesign;
            this.L$1 = list;
            this.L$2 = str;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            LayoutInflater from = LayoutInflater.from(overrideSettingsDesign.context);
            ViewGroup root = InputKt.getRoot(overrideSettingsDesign.context);
            int i2 = DialogPreferenceListBinding.$r8$clinit;
            DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
            DialogPreferenceListBinding dialogPreferenceListBinding = (DialogPreferenceListBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_preference_list, root, false, null);
            final SideloadProviderAdapter sideloadProviderAdapter = new SideloadProviderAdapter(overrideSettingsDesign.context, list, str);
            final FullScreenDialog fullScreenDialog = new FullScreenDialog(overrideSettingsDesign.context);
            fullScreenDialog.setContentView(dialogPreferenceListBinding.mRoot);
            dialogPreferenceListBinding.setSurface(fullScreenDialog.surface);
            dialogPreferenceListBinding.titleView.setText(overrideSettingsDesign.context.getString(R.string.sideload_geoip));
            dialogPreferenceListBinding.newView.setVisibility(4);
            InputKt.applyLinearAdapter(dialogPreferenceListBinding.mainList, overrideSettingsDesign.context, sideloadProviderAdapter);
            dialogPreferenceListBinding.resetView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2$1$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cancellableContinuationImpl.resumeWith(HttpUrl.FRAGMENT_ENCODE_SET);
                    fullScreenDialog.dismiss();
                }
            });
            dialogPreferenceListBinding.cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2$1$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenDialog.this.dismiss();
                }
            });
            dialogPreferenceListBinding.okView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2$1$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cancellableContinuationImpl.resumeWith(sideloadProviderAdapter.selectedPackageName);
                    fullScreenDialog.dismiss();
                }
            });
            fullScreenDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2$1$4
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    if (!cancellableContinuationImpl.isCompleted()) {
                        cancellableContinuationImpl.resumeWith(str);
                    }
                }
            });
            fullScreenDialog.show();
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            String str2 = (String) this.L$2;
            List list2 = (List) this.L$1;
            OverrideSettingsDesign overrideSettingsDesign2 = (OverrideSettingsDesign) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
