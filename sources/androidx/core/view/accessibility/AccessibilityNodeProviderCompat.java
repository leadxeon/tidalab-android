package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class AccessibilityNodeProviderCompat {
    public final Object mProvider;

    /* loaded from: classes.dex */
    public static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        public final AccessibilityNodeProviderCompat mCompat;

        public AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            this.mCompat = accessibilityNodeProviderCompat;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            AccessibilityNodeInfoCompat createAccessibilityNodeInfo = this.mCompat.createAccessibilityNodeInfo(i);
            if (createAccessibilityNodeInfo == null) {
                return null;
            }
            return createAccessibilityNodeInfo.mInfo;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            Objects.requireNonNull(this.mCompat);
            return null;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.mCompat.performAction(i, i2, bundle);
        }
    }

    /* loaded from: classes.dex */
    public static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        public AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            super(accessibilityNodeProviderCompat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            AccessibilityNodeInfoCompat findFocus = this.mCompat.findFocus(i);
            if (findFocus == null) {
                return null;
            }
            return findFocus.mInfo;
        }
    }

    /* loaded from: classes.dex */
    public static class AccessibilityNodeProviderApi26 extends AccessibilityNodeProviderApi19 {
        public AccessibilityNodeProviderApi26(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            super(accessibilityNodeProviderCompat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            Objects.requireNonNull(this.mCompat);
        }
    }

    public AccessibilityNodeProviderCompat() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mProvider = new AccessibilityNodeProviderApi26(this);
        } else {
            this.mProvider = new AccessibilityNodeProviderApi19(this);
        }
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int i) {
        return null;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return false;
    }

    public AccessibilityNodeProviderCompat(Object obj) {
        this.mProvider = obj;
    }
}
