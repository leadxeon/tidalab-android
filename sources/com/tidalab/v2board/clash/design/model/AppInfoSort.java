package com.tidalab.v2board.clash.design.model;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Comparator;
/* compiled from: AppInfoSort.kt */
/* loaded from: classes.dex */
public enum AppInfoSort implements Comparator<AppInfo> {
    Label(new Comparator<T>() { // from class: com.tidalab.v2board.clash.design.model.AppInfoSort$special$$inlined$compareBy$1
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return InputKt.compareValues(((AppInfo) t).label, ((AppInfo) t2).label);
        }
    }),
    PackageName(new Comparator<T>() { // from class: com.tidalab.v2board.clash.design.model.AppInfoSort$special$$inlined$compareBy$2
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return InputKt.compareValues(((AppInfo) t).packageName, ((AppInfo) t2).packageName);
        }
    }),
    InstallTime(new Comparator<T>() { // from class: com.tidalab.v2board.clash.design.model.AppInfoSort$special$$inlined$compareBy$3
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return InputKt.compareValues(Long.valueOf(((AppInfo) t).installTime), Long.valueOf(((AppInfo) t2).installTime));
        }
    }),
    UpdateTime(new Comparator<T>() { // from class: com.tidalab.v2board.clash.design.model.AppInfoSort$special$$inlined$compareBy$4
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return InputKt.compareValues(Long.valueOf(((AppInfo) t).updateDate), Long.valueOf(((AppInfo) t2).updateDate));
        }
    });
    
    public final /* synthetic */ Comparator<AppInfo> $$delegate_0;

    AppInfoSort(Comparator comparator) {
        this.$$delegate_0 = comparator;
    }

    @Override // java.util.Comparator
    public int compare(AppInfo appInfo, AppInfo appInfo2) {
        return this.$$delegate_0.compare(appInfo, appInfo2);
    }
}
