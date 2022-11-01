package com.facebook.react.module.model;
/* loaded from: classes.dex */
public class ReactModuleInfo {
    public final boolean mCanOverrideExistingModule;
    public String mClassName;
    public final boolean mHasConstants;
    public final boolean mIsCxxModule;
    public final boolean mIsTurboModule;
    public final String mName;
    public final boolean mNeedsEagerInit;

    public ReactModuleInfo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.mName = str;
        this.mClassName = str2;
        this.mCanOverrideExistingModule = z;
        this.mNeedsEagerInit = z2;
        this.mHasConstants = z3;
        this.mIsCxxModule = z4;
        this.mIsTurboModule = z5;
    }
}
