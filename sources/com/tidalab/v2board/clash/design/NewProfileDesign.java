package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.adapter.ProfileProviderAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignNewProfileBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NewProfileDesign.kt */
/* loaded from: classes.dex */
public final class NewProfileDesign extends Design<Request> {
    public final ProfileProviderAdapter adapter;
    public final DesignNewProfileBinding binding;

    /* compiled from: NewProfileDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: NewProfileDesign.kt */
        /* loaded from: classes.dex */
        public static final class Create extends Request {
            public final ProfileProvider provider;

            public Create(ProfileProvider profileProvider) {
                super(null);
                this.provider = profileProvider;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Create) && Intrinsics.areEqual(this.provider, ((Create) obj).provider);
            }

            public int hashCode() {
                return this.provider.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Create(provider=");
                outline13.append(this.provider);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: NewProfileDesign.kt */
        /* loaded from: classes.dex */
        public static final class OpenDetail extends Request {
            public final ProfileProvider.External provider;

            public OpenDetail(ProfileProvider.External external) {
                super(null);
                this.provider = external;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OpenDetail) && Intrinsics.areEqual(this.provider, ((OpenDetail) obj).provider);
            }

            public int hashCode() {
                return this.provider.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("OpenDetail(provider=");
                outline13.append(this.provider);
                outline13.append(')');
                return outline13.toString();
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public NewProfileDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignNewProfileBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignNewProfileBinding designNewProfileBinding = (DesignNewProfileBinding) ViewDataBinding.inflateInternal(from, R.layout.design_new_profile, root, false, null);
        this.binding = designNewProfileBinding;
        ProfileProviderAdapter profileProviderAdapter = new ProfileProviderAdapter(context, new NewProfileDesign$adapter$1(this), new NewProfileDesign$adapter$2(this));
        this.adapter = profileProviderAdapter;
        designNewProfileBinding.setSelf(this);
        InputKt.applyFrom(designNewProfileBinding.activityBarLayout, context);
        AppRecyclerView appRecyclerView = designNewProfileBinding.mainList.recyclerList;
        InputKt.bindAppBarElevation(appRecyclerView, designNewProfileBinding.activityBarLayout);
        InputKt.applyLinearAdapter(appRecyclerView, context, profileProviderAdapter);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object patchProviders(java.util.List<? extends com.tidalab.v2board.clash.design.model.ProfileProvider> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$1 r0 = (com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$1 r0 = new com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$1
            r0.<init>(r8, r10)
        L_0x0018:
            r6 = r0
            java.lang.Object r10 = r6.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0034
            if (r1 != r2) goto L_0x002c
            java.lang.Object r9 = r6.L$0
            com.tidalab.v2board.clash.design.adapter.ProfileProviderAdapter r9 = (com.tidalab.v2board.clash.design.adapter.ProfileProviderAdapter) r9
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x004f
        L_0x002c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0034:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            com.tidalab.v2board.clash.design.adapter.ProfileProviderAdapter r1 = r8.adapter
            com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$2$1 r10 = new com.tidalab.v2board.clash.design.NewProfileDesign$patchProviders$2$1
            r10.<init>(r1)
            r4 = 0
            r5 = 0
            r7 = 12
            r6.L$0 = r1
            r6.label = r2
            r2 = r10
            r3 = r9
            java.lang.Object r9 = com.tidalab.v2board.clash.design.dialog.InputKt.patchDataSet$default(r1, r2, r3, r4, r5, r6, r7)
            if (r9 != r0) goto L_0x004f
            return r0
        L_0x004f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.NewProfileDesign.patchProviders(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
