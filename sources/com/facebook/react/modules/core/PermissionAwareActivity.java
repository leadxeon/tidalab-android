package com.facebook.react.modules.core;
/* loaded from: classes.dex */
public interface PermissionAwareActivity {
    void requestPermissions(String[] strArr, int i, PermissionListener permissionListener);

    boolean shouldShowRequestPermissionRationale(String str);
}
