package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.adapter.ProfileAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignProfilesBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProfilesDesign.kt */
/* loaded from: classes.dex */
public final class ProfilesDesign extends Design<Request> {
    public final ProfileAdapter adapter;
    public final DesignProfilesBinding binding;

    /* compiled from: ProfilesDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Active extends Request {
            public final Profile profile;

            public Active(Profile profile) {
                super(null);
                this.profile = profile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Active) && Intrinsics.areEqual(this.profile, ((Active) obj).profile);
            }

            public int hashCode() {
                return this.profile.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Active(profile=");
                outline13.append(this.profile);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Create extends Request {
            public static final Create INSTANCE = new Create();

            public Create() {
                super(null);
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Delete extends Request {
            public final Profile profile;

            public Delete(Profile profile) {
                super(null);
                this.profile = profile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Delete) && Intrinsics.areEqual(this.profile, ((Delete) obj).profile);
            }

            public int hashCode() {
                return this.profile.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Delete(profile=");
                outline13.append(this.profile);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Duplicate extends Request {
            public final Profile profile;

            public Duplicate(Profile profile) {
                super(null);
                this.profile = profile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Duplicate) && Intrinsics.areEqual(this.profile, ((Duplicate) obj).profile);
            }

            public int hashCode() {
                return this.profile.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Duplicate(profile=");
                outline13.append(this.profile);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Edit extends Request {
            public final Profile profile;

            public Edit(Profile profile) {
                super(null);
                this.profile = profile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Edit) && Intrinsics.areEqual(this.profile, ((Edit) obj).profile);
            }

            public int hashCode() {
                return this.profile.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Edit(profile=");
                outline13.append(this.profile);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class Update extends Request {
            public final Profile profile;

            public Update(Profile profile) {
                super(null);
                this.profile = profile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Update) && Intrinsics.areEqual(this.profile, ((Update) obj).profile);
            }

            public int hashCode() {
                return this.profile.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Update(profile=");
                outline13.append(this.profile);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProfilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class UpdateAll extends Request {
            public static final UpdateAll INSTANCE = new UpdateAll();

            public UpdateAll() {
                super(null);
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ProfilesDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignProfilesBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignProfilesBinding designProfilesBinding = (DesignProfilesBinding) ViewDataBinding.inflateInternal(from, R.layout.design_profiles, root, false, null);
        this.binding = designProfilesBinding;
        ProfileAdapter profileAdapter = new ProfileAdapter(context, new ProfilesDesign$adapter$1(this), new ProfilesDesign$adapter$2(this));
        this.adapter = profileAdapter;
        designProfilesBinding.setSelf(this);
        InputKt.applyFrom(designProfilesBinding.activityBarLayout, context);
        AppRecyclerView appRecyclerView = designProfilesBinding.mainList.recyclerList;
        InputKt.bindAppBarElevation(appRecyclerView, designProfilesBinding.activityBarLayout);
        InputKt.applyLinearAdapter(appRecyclerView, context, profileAdapter);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a2 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object patchProfiles(java.util.List<com.tidalab.v2board.clash.service.model.Profile> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$1 r0 = (com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$1 r0 = new com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$1
            r0.<init>(r12, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r9 = 3
            r10 = 2
            r2 = 1
            r11 = 0
            if (r1 == 0) goto L_0x004f
            if (r1 == r2) goto L_0x003f
            if (r1 == r10) goto L_0x0037
            if (r1 != r9) goto L_0x002f
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            goto L_0x00a3
        L_0x002f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0037:
            java.lang.Object r13 = r0.L$0
            com.tidalab.v2board.clash.design.ProfilesDesign r13 = (com.tidalab.v2board.clash.design.ProfilesDesign) r13
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            goto L_0x0089
        L_0x003f:
            java.lang.Object r13 = r0.L$2
            com.tidalab.v2board.clash.design.adapter.ProfileAdapter r13 = (com.tidalab.v2board.clash.design.adapter.ProfileAdapter) r13
            java.lang.Object r13 = r0.L$1
            java.util.List r13 = (java.util.List) r13
            java.lang.Object r1 = r0.L$0
            com.tidalab.v2board.clash.design.ProfilesDesign r1 = (com.tidalab.v2board.clash.design.ProfilesDesign) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            goto L_0x0070
        L_0x004f:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            com.tidalab.v2board.clash.design.adapter.ProfileAdapter r1 = r12.adapter
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$2$1 r14 = new com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$2$1
            r14.<init>(r1)
            r4 = 0
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$2$2 r5 = com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$2$2.INSTANCE
            r7 = 4
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r1
            r0.label = r2
            r2 = r14
            r3 = r13
            r6 = r0
            java.lang.Object r14 = com.tidalab.v2board.clash.design.dialog.InputKt.patchDataSet$default(r1, r2, r3, r4, r5, r6, r7)
            if (r14 != r8) goto L_0x006f
            return r8
        L_0x006f:
            r1 = r12
        L_0x0070:
            kotlinx.coroutines.Dispatchers r14 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.Default
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$updatable$1 r2 = new com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$updatable$1
            r2.<init>(r13, r11)
            r0.L$0 = r1
            r0.L$1 = r11
            r0.L$2 = r11
            r0.label = r10
            java.lang.Object r14 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r14, r2, r0)
            if (r14 != r8) goto L_0x0088
            return r8
        L_0x0088:
            r13 = r1
        L_0x0089:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            kotlinx.coroutines.Dispatchers r1 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$3 r2 = new com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$3
            r2.<init>(r13, r14, r11)
            r0.L$0 = r11
            r0.label = r9
            java.lang.Object r13 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r1, r2, r0)
            if (r13 != r8) goto L_0x00a3
            return r8
        L_0x00a3:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.ProfilesDesign.patchProfiles(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
