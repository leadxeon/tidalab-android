package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: PropertiesDesign.kt */
/* loaded from: classes.dex */
public final class PropertiesDesign extends Design<Request> {
    public final DesignPropertiesBinding binding;

    /* compiled from: PropertiesDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: PropertiesDesign.kt */
        /* loaded from: classes.dex */
        public static final class BrowseFiles extends Request {
            public static final BrowseFiles INSTANCE = new BrowseFiles();

            public BrowseFiles() {
                super(null);
            }
        }

        /* compiled from: PropertiesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Commit extends Request {
            public static final Commit INSTANCE = new Commit();

            public Commit() {
                super(null);
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public PropertiesDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignPropertiesBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignPropertiesBinding designPropertiesBinding = (DesignPropertiesBinding) ViewDataBinding.inflateInternal(from, R.layout.design_properties, root, false, null);
        this.binding = designPropertiesBinding;
        designPropertiesBinding.setSelf(this);
        InputKt.applyFrom(designPropertiesBinding.activityBarLayout, context);
        designPropertiesBinding.tips.setText(InputKt.getHtml(context, R.string.tips_properties));
        InputKt.bindAppBarElevation(designPropertiesBinding.scrollRoot, designPropertiesBinding.activityBarLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object withProcessing(kotlin.jvm.functions.Function2<? super kotlin.jvm.functions.Function2<? super com.tidalab.v2board.clash.core.model.FetchStatus, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$1 r0 = (com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$1 r0 = new com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$1
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r4) goto L_0x002e
            java.lang.Object r7 = r0.L$0
            com.tidalab.v2board.clash.design.PropertiesDesign r7 = (com.tidalab.v2board.clash.design.PropertiesDesign) r7
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x002c
            goto L_0x0052
        L_0x002c:
            r8 = move-exception
            goto L_0x005d
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding r8 = r6.binding     // Catch: all -> 0x005a
            r8.setProcessing(r4)     // Catch: all -> 0x005a
            android.content.Context r8 = r6.context     // Catch: all -> 0x005a
            com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2 r2 = new com.tidalab.v2board.clash.design.PropertiesDesign$withProcessing$2     // Catch: all -> 0x005a
            r5 = 0
            r2.<init>(r7, r6, r5)     // Catch: all -> 0x005a
            r0.L$0 = r6     // Catch: all -> 0x005a
            r0.label = r4     // Catch: all -> 0x005a
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withModelProgressBar(r8, r2, r0)     // Catch: all -> 0x005a
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            r7 = r6
        L_0x0052:
            com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding r7 = r7.binding
            r7.setProcessing(r3)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x005a:
            r7 = move-exception
            r8 = r7
            r7 = r6
        L_0x005d:
            com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding r7 = r7.binding
            r7.setProcessing(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.PropertiesDesign.withProcessing(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
