package com.tidalab.v2board.clash;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tidalab.v2board.clash.common.store.Store;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.design.model.AppInfoSort;
import com.tidalab.v2board.clash.design.store.UiStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlin.sequences.TransformingSequence;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$loadApps$2", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$loadApps$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AppInfo>>, Object> {
    public final /* synthetic */ Set<String> $selected;
    public final /* synthetic */ AccessControlActivity this$0;

    /* compiled from: AccessControlActivity.kt */
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<PackageInfo, Boolean> {
        public final /* synthetic */ AccessControlActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AccessControlActivity accessControlActivity) {
            super(1);
            this.this$0 = accessControlActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public Boolean invoke(PackageInfo packageInfo) {
            return Boolean.valueOf(!Intrinsics.areEqual(packageInfo.packageName, this.this$0.getPackageName()));
        }
    }

    /* compiled from: AccessControlActivity.kt */
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<PackageInfo, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public Boolean invoke(PackageInfo packageInfo) {
            Boolean bool;
            PackageInfo packageInfo2 = packageInfo;
            boolean z = true;
            if (!Intrinsics.areEqual(packageInfo2.packageName, "android")) {
                String[] strArr = packageInfo2.requestedPermissions;
                if (strArr == null) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(ArraysKt___ArraysKt.indexOf(strArr, "android.permission.INTERNET") >= 0);
                }
                if (!Intrinsics.areEqual(bool, Boolean.TRUE)) {
                    z = false;
                }
            }
            return Boolean.valueOf(z);
        }
    }

    /* compiled from: AccessControlActivity.kt */
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass3 extends Lambda implements Function1<PackageInfo, Boolean> {
        public final /* synthetic */ boolean $systemApp;
        public final /* synthetic */ AccessControlActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(boolean z, AccessControlActivity accessControlActivity) {
            super(1);
            this.$systemApp = z;
            this.this$0 = accessControlActivity;
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
            if (((r5.applicationInfo.flags & 1) != 0) == false) goto L_0x001b;
         */
        @Override // kotlin.jvm.functions.Function1
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Boolean invoke(android.content.pm.PackageInfo r5) {
            /*
                r4 = this;
                android.content.pm.PackageInfo r5 = (android.content.pm.PackageInfo) r5
                boolean r0 = r4.$systemApp
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L_0x001b
                com.tidalab.v2board.clash.AccessControlActivity r0 = r4.this$0
                int r3 = com.tidalab.v2board.clash.AccessControlActivity.$r8$clinit
                java.util.Objects.requireNonNull(r0)
                android.content.pm.ApplicationInfo r5 = r5.applicationInfo
                int r5 = r5.flags
                r5 = r5 & r2
                if (r5 == 0) goto L_0x0018
                r5 = 1
                goto L_0x0019
            L_0x0018:
                r5 = 0
            L_0x0019:
                if (r5 != 0) goto L_0x001c
            L_0x001b:
                r1 = 1
            L_0x001c:
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2.AnonymousClass3.invoke(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: AccessControlActivity.kt */
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass4 extends Lambda implements Function1<PackageInfo, AppInfo> {
        public final /* synthetic */ PackageManager $pm;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(PackageManager packageManager) {
            super(1);
            this.$pm = packageManager;
        }

        @Override // kotlin.jvm.functions.Function1
        public AppInfo invoke(PackageInfo packageInfo) {
            return InputKt.toAppInfo(packageInfo, this.$pm);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$loadApps$2(AccessControlActivity accessControlActivity, Set<String> set, Continuation<? super AccessControlActivity$loadApps$2> continuation) {
        super(2, continuation);
        this.this$0 = accessControlActivity;
        this.$selected = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlActivity$loadApps$2(this.this$0, this.$selected, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AppInfo>> continuation) {
        return new AccessControlActivity$loadApps$2(this.this$0, this.$selected, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final Comparator comparator;
        InputKt.throwOnFailure(obj);
        UiStore uiStore = this.this$0.getUiStore();
        Store.Delegate delegate = uiStore.accessControlReverse$delegate;
        KProperty<?>[] kPropertyArr = UiStore.$$delegatedProperties;
        boolean booleanValue = ((Boolean) delegate.getValue(uiStore, kPropertyArr[7])).booleanValue();
        UiStore uiStore2 = this.this$0.getUiStore();
        final AppInfoSort appInfoSort = (AppInfoSort) uiStore2.accessControlSort$delegate.getValue(uiStore2, kPropertyArr[6]);
        UiStore uiStore3 = this.this$0.getUiStore();
        boolean booleanValue2 = ((Boolean) uiStore3.accessControlSystemApp$delegate.getValue(uiStore3, kPropertyArr[8])).booleanValue();
        final Set<String> set = this.$selected;
        final Comparator<T> accessControlActivity$loadApps$2$invokeSuspend$$inlined$compareByDescending$1 = new Comparator<T>() { // from class: com.tidalab.v2board.clash.AccessControlActivity$loadApps$2$invokeSuspend$$inlined$compareByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return InputKt.compareValues(Boolean.valueOf(set.contains(((AppInfo) t2).packageName)), Boolean.valueOf(set.contains(((AppInfo) t).packageName)));
            }
        };
        if (booleanValue) {
            comparator = new Comparator<T>() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenDescending$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compare = accessControlActivity$loadApps$2$invokeSuspend$$inlined$compareByDescending$1.compare(t, t2);
                    return compare != 0 ? compare : appInfoSort.compare(t2, t);
                }
            };
        } else {
            comparator = new Comparator<T>() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$then$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compare = accessControlActivity$loadApps$2$invokeSuspend$$inlined$compareByDescending$1.compare(t, t2);
                    return compare != 0 ? compare : appInfoSort.compare(t, t2);
                }
            };
        }
        PackageManager packageManager = this.this$0.getPackageManager();
        final TransformingSequence transformingSequence = new TransformingSequence(InputKt.filter(InputKt.filter(InputKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(packageManager.getInstalledPackages(4096)), new AnonymousClass1(this.this$0)), AnonymousClass2.INSTANCE), new AnonymousClass3(booleanValue2, this.this$0)), new AnonymousClass4(packageManager));
        return InputKt.toList(new Sequence<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$sortedWith$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                List mutableList = InputKt.toMutableList(transformingSequence);
                Comparator comparator2 = comparator;
                ArrayList arrayList = (ArrayList) mutableList;
                if (arrayList.size() > 1) {
                    Collections.sort(mutableList, comparator2);
                }
                return arrayList.iterator();
            }
        });
    }
}
