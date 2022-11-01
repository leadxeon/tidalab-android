package com.tidalab.v2board.clash;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Metadata;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.design.OverrideSettingsDesign;
import com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestSelectSideload$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import okhttp3.HttpUrl;
/* compiled from: OverrideSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2", f = "OverrideSettingsActivity.kt", l = {43, 56}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class OverrideSettingsActivity$main$3$2 extends SuspendLambda implements Function2<OverrideSettingsDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ OverrideSettingsDesign $design;
    public final /* synthetic */ ServiceStore $service;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ OverrideSettingsActivity this$0;

    /* compiled from: OverrideSettingsActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1", f = "OverrideSettingsActivity.kt", l = {45}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        public final /* synthetic */ ServiceStore $service;
        public int label;

        /* compiled from: OverrideSettingsActivity.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1$1", f = "OverrideSettingsActivity.kt", l = {}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00121 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object L$0;

            public C00121(Continuation<? super C00121> continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00121 r0 = new C00121(continuation);
                r0.L$0 = obj;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
                IClashManager iClashManager2 = iClashManager;
                Continuation<? super Unit> continuation2 = continuation;
                if (continuation2 != null) {
                    continuation2.getContext();
                }
                Unit unit = Unit.INSTANCE;
                InputKt.throwOnFailure(unit);
                iClashManager2.clearOverride(Clash.OverrideSlot.Persist);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                InputKt.throwOnFailure(obj);
                ((IClashManager) this.L$0).clearOverride(Clash.OverrideSlot.Persist);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ServiceStore serviceStore, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$service = serviceStore;
        }

        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$service, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                C00121 r4 = new C00121(null);
                this.label = 1;
                if (InputKt.withClash$default(null, r4, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ServiceStore serviceStore = this.$service;
            serviceStore.sideloadGeoip$delegate.setValue(serviceStore, ServiceStore.$$delegatedProperties[8], HttpUrl.FRAGMENT_ENCODE_SET);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: OverrideSettingsActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$2", f = "OverrideSettingsActivity.kt", l = {62}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ OverrideSettingsDesign $design;
        public final /* synthetic */ ServiceStore $service;
        public Object L$0;
        public int label;
        public final /* synthetic */ OverrideSettingsActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OverrideSettingsActivity overrideSettingsActivity, ServiceStore serviceStore, OverrideSettingsDesign overrideSettingsDesign, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = overrideSettingsActivity;
            this.$service = serviceStore;
            this.$design = overrideSettingsDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$service, this.$design, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.this$0, this.$service, this.$design, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ServiceStore serviceStore;
            boolean z;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                OverrideSettingsActivity overrideSettingsActivity = this.this$0;
                int i2 = OverrideSettingsActivity.$r8$clinit;
                List<PackageInfo> installedPackages = overrideSettingsActivity.getPackageManager().getInstalledPackages(128);
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = installedPackages.iterator();
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    Bundle bundle = ((PackageInfo) next).applicationInfo.metaData;
                    if (bundle != null) {
                        Metadata metadata = Metadata.INSTANCE;
                        z = bundle.containsKey(Metadata.GEOIP_FILE_NAME);
                    }
                    if (z) {
                        arrayList.add(next);
                    }
                }
                ArrayList arrayList2 = new ArrayList(InputKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(InputKt.toAppInfo((PackageInfo) it2.next(), overrideSettingsActivity.getPackageManager()));
                }
                List plus = ArraysKt___ArraysKt.plus(Collections.singletonList(new AppInfo(HttpUrl.FRAGMENT_ENCODE_SET, overrideSettingsActivity.getString(R.string.use_built_in), PathParser.getDrawableCompat(overrideSettingsActivity, R.drawable.ic_baseline_work), 0L, 0L)), arrayList2);
                String sideloadGeoip = this.$service.getSideloadGeoip();
                ArrayList arrayList3 = (ArrayList) plus;
                if (!arrayList3.isEmpty()) {
                    Iterator it3 = arrayList3.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (Boolean.valueOf(Intrinsics.areEqual(((AppInfo) it3.next()).packageName, sideloadGeoip)).booleanValue()) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                ServiceStore serviceStore2 = this.$service;
                OverrideSettingsDesign overrideSettingsDesign = this.$design;
                if (!z) {
                    sideloadGeoip = HttpUrl.FRAGMENT_ENCODE_SET;
                }
                this.L$0 = serviceStore2;
                this.label = 1;
                Objects.requireNonNull(overrideSettingsDesign);
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                obj = InputKt.withContext(MainDispatcherLoader.dispatcher, new OverrideSettingsDesign$requestSelectSideload$2(overrideSettingsDesign, plus, sideloadGeoip, null), this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                serviceStore = serviceStore2;
            } else if (i == 1) {
                serviceStore = (ServiceStore) this.L$0;
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            serviceStore.sideloadGeoip$delegate.setValue(serviceStore, ServiceStore.$$delegatedProperties[8], (String) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsActivity$main$3$2(OverrideSettingsDesign overrideSettingsDesign, OverrideSettingsActivity overrideSettingsActivity, ServiceStore serviceStore, Continuation<? super OverrideSettingsActivity$main$3$2> continuation) {
        super(2, continuation);
        this.$design = overrideSettingsDesign;
        this.this$0 = overrideSettingsActivity;
        this.$service = serviceStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OverrideSettingsActivity$main$3$2 overrideSettingsActivity$main$3$2 = new OverrideSettingsActivity$main$3$2(this.$design, this.this$0, this.$service, continuation);
        overrideSettingsActivity$main$3$2.L$0 = obj;
        return overrideSettingsActivity$main$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(OverrideSettingsDesign.Request request, Continuation<? super Unit> continuation) {
        OverrideSettingsActivity$main$3$2 overrideSettingsActivity$main$3$2 = new OverrideSettingsActivity$main$3$2(this.$design, this.this$0, this.$service, continuation);
        overrideSettingsActivity$main$3$2.L$0 = request;
        return overrideSettingsActivity$main$3$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a4  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 == r4) goto L_0x001a
            if (r1 != r3) goto L_0x0012
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x00b4
        L_0x0012:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x009c
        L_0x001f:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            com.tidalab.v2board.clash.design.OverrideSettingsDesign$Request r8 = (com.tidalab.v2board.clash.design.OverrideSettingsDesign.Request) r8
            int r8 = r8.ordinal()
            if (r8 == 0) goto L_0x0048
            if (r8 == r4) goto L_0x0030
            goto L_0x00b4
        L_0x0030:
            kotlinx.coroutines.Dispatchers r8 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.IO
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$2 r1 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$2
            com.tidalab.v2board.clash.OverrideSettingsActivity r4 = r7.this$0
            com.tidalab.v2board.clash.service.store.ServiceStore r5 = r7.$service
            com.tidalab.v2board.clash.design.OverrideSettingsDesign r6 = r7.$design
            r1.<init>(r4, r5, r6, r2)
            r7.label = r3
            java.lang.Object r8 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r8, r1, r7)
            if (r8 != r0) goto L_0x00b4
            return r0
        L_0x0048:
            com.tidalab.v2board.clash.design.OverrideSettingsDesign r8 = r7.$design
            r7.label = r4
            java.util.Objects.requireNonNull(r8)
            kotlinx.coroutines.CancellableContinuationImpl r1 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r3 = com.tidalab.v2board.clash.design.dialog.InputKt.intercepted(r7)
            r1.<init>(r3, r4)
            r1.initCancellability()
            com.google.android.material.dialog.MaterialAlertDialogBuilder r3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder
            android.content.Context r8 = r8.context
            r3.<init>(r8)
            r8 = 2131820886(0x7f110156, float:1.92745E38)
            r3.setTitle(r8)
            r8 = 2131820887(0x7f110157, float:1.9274502E38)
            r3.setMessage(r8)
            r8 = 2131820850(0x7f110132, float:1.9274427E38)
            com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$dialog$1 r4 = new com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$dialog$1
            r4.<init>()
            r3.setPositiveButton(r8, r4)
            r8 = 2131820609(0x7f110041, float:1.9273938E38)
            com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$dialog$2 r4 = com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$dialog$2.INSTANCE
            r3.setNegativeButton(r8, r4)
            androidx.appcompat.app.AlertDialog r8 = r3.show()
            com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$1 r3 = new com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$1
            r3.<init>()
            r8.setOnDismissListener(r3)
            com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$2 r3 = new com.tidalab.v2board.clash.design.OverrideSettingsDesign$requestResetConfirm$2$2
            r3.<init>(r8)
            r1.invokeOnCancellation(r3)
            java.lang.Object r8 = r1.getResult()
            if (r8 != r0) goto L_0x009c
            return r0
        L_0x009c:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x00b4
            com.tidalab.v2board.clash.OverrideSettingsActivity r8 = r7.this$0
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1 r0 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2$1
            com.tidalab.v2board.clash.service.store.ServiceStore r1 = r7.$service
            r0.<init>(r1, r2)
            r8.defer = r0
            com.tidalab.v2board.clash.OverrideSettingsActivity r8 = r7.this$0
            r8.finish()
        L_0x00b4:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
