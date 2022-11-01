package com.tidalab.v2board.clash.design.model;

import android.graphics.drawable.Drawable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.Provider$$ExternalSynthetic0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AppInfo.kt */
/* loaded from: classes.dex */
public final class AppInfo {
    public final Drawable icon;
    public final long installTime;
    public final String label;
    public final String packageName;
    public final long updateDate;

    public AppInfo(String str, String str2, Drawable drawable, long j, long j2) {
        this.packageName = str;
        this.label = str2;
        this.icon = drawable;
        this.installTime = j;
        this.updateDate = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) obj;
        return Intrinsics.areEqual(this.packageName, appInfo.packageName) && Intrinsics.areEqual(this.label, appInfo.label) && Intrinsics.areEqual(this.icon, appInfo.icon) && this.installTime == appInfo.installTime && this.updateDate == appInfo.updateDate;
    }

    public int hashCode() {
        int outline1 = GeneratedOutlineSupport.outline1(this.label, this.packageName.hashCode() * 31, 31);
        int m0 = Provider$$ExternalSynthetic0.m0(this.installTime);
        return Provider$$ExternalSynthetic0.m0(this.updateDate) + ((m0 + ((this.icon.hashCode() + outline1) * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("AppInfo(packageName=");
        outline13.append(this.packageName);
        outline13.append(", label=");
        outline13.append(this.label);
        outline13.append(", icon=");
        outline13.append(this.icon);
        outline13.append(", installTime=");
        outline13.append(this.installTime);
        outline13.append(", updateDate=");
        outline13.append(this.updateDate);
        outline13.append(')');
        return outline13.toString();
    }
}
