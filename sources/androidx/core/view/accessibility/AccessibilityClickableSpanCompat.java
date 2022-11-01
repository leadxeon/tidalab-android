package androidx.core.view.accessibility;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
/* loaded from: classes.dex */
public final class AccessibilityClickableSpanCompat extends ClickableSpan {
    public final int mClickableSpanActionId;
    public final AccessibilityNodeInfoCompat mNodeInfoCompat;
    public final int mOriginalClickableSpanId;

    public AccessibilityClickableSpanCompat(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i2) {
        this.mOriginalClickableSpanId = i;
        this.mNodeInfoCompat = accessibilityNodeInfoCompat;
        this.mClickableSpanActionId = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.mOriginalClickableSpanId);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mNodeInfoCompat;
        accessibilityNodeInfoCompat.mInfo.performAction(this.mClickableSpanActionId, bundle);
    }
}
