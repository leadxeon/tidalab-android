package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    public static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final FocusStrategy$BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new AnonymousClass1();
    public static final FocusStrategy$CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy$CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() { // from class: androidx.customview.widget.ExploreByTouchHelper.2
    };
    public final View mHost;
    public final AccessibilityManager mManager;
    public MyNodeProvider mNodeProvider;
    public final Rect mTempScreenRect = new Rect();
    public final Rect mTempParentRect = new Rect();
    public final Rect mTempVisibleRect = new Rect();
    public final int[] mTempGlobalRect = new int[2];
    public int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* renamed from: androidx.customview.widget.ExploreByTouchHelper$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass1 implements FocusStrategy$BoundsAdapter<AccessibilityNodeInfoCompat> {
        public void obtainBounds(Object obj, Rect rect) {
            ((AccessibilityNodeInfoCompat) obj).mInfo.getBoundsInParent(rect);
        }
    }

    /* loaded from: classes.dex */
    public class MyNodeProvider extends AccessibilityNodeProviderCompat {
        public MyNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i).mInfo));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int i) {
            int i2 = i == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i2).mInfo));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int i, int i2, Bundle bundle) {
            int i3;
            ExploreByTouchHelper exploreByTouchHelper = ExploreByTouchHelper.this;
            if (i != -1) {
                boolean z = true;
                if (i2 == 1) {
                    return exploreByTouchHelper.requestKeyboardFocusForVirtualView(i);
                }
                if (i2 == 2) {
                    return exploreByTouchHelper.clearKeyboardFocusForVirtualView(i);
                }
                if (i2 == 64) {
                    if (!exploreByTouchHelper.mManager.isEnabled() || !exploreByTouchHelper.mManager.isTouchExplorationEnabled() || (i3 = exploreByTouchHelper.mAccessibilityFocusedVirtualViewId) == i) {
                        z = false;
                    } else {
                        if (i3 != Integer.MIN_VALUE) {
                            exploreByTouchHelper.clearAccessibilityFocus(i3);
                        }
                        exploreByTouchHelper.mAccessibilityFocusedVirtualViewId = i;
                        exploreByTouchHelper.mHost.invalidate();
                        exploreByTouchHelper.sendEventForVirtualView(i, 32768);
                    }
                    return z;
                } else if (i2 != 128) {
                    return exploreByTouchHelper.onPerformActionForVirtualView(i, i2, bundle);
                } else {
                    return exploreByTouchHelper.clearAccessibilityFocus(i);
                }
            } else {
                View view = exploreByTouchHelper.mHost;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                return view.performAccessibilityAction(i2, bundle);
            }
        }
    }

    public ExploreByTouchHelper(View view) {
        if (view != null) {
            this.mHost = view;
            this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    public final boolean clearAccessibilityFocus(int i) {
        if (this.mAccessibilityFocusedVirtualViewId != i) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i, 65536);
        return true;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i, false);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final AccessibilityNodeInfoCompat createNodeForChild(int i) {
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(obtain);
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        Rect rect = INVALID_PARENT_BOUNDS;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        accessibilityNodeInfoCompat.setParent(this.mHost);
        onPopulateNodeForVirtualView(i, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat.getText() == null && accessibilityNodeInfoCompat.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        if (!this.mTempParentRect.equals(rect)) {
            int actions = accessibilityNodeInfoCompat.getActions();
            if ((actions & 64) != 0) {
                throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else if ((actions & 128) == 0) {
                obtain.setPackageName(this.mHost.getContext().getPackageName());
                View view = this.mHost;
                accessibilityNodeInfoCompat.mVirtualDescendantId = i;
                obtain.setSource(view, i);
                boolean z = false;
                if (this.mAccessibilityFocusedVirtualViewId == i) {
                    obtain.setAccessibilityFocused(true);
                    obtain.addAction(128);
                } else {
                    obtain.setAccessibilityFocused(false);
                    obtain.addAction(64);
                }
                boolean z2 = this.mKeyboardFocusedVirtualViewId == i;
                if (z2) {
                    obtain.addAction(2);
                } else if (accessibilityNodeInfoCompat.isFocusable()) {
                    obtain.addAction(1);
                }
                obtain.setFocused(z2);
                this.mHost.getLocationOnScreen(this.mTempGlobalRect);
                obtain.getBoundsInScreen(this.mTempScreenRect);
                if (this.mTempScreenRect.equals(rect)) {
                    obtain.getBoundsInParent(this.mTempScreenRect);
                    if (accessibilityNodeInfoCompat.mParentVirtualDescendantId != -1) {
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                        for (int i2 = accessibilityNodeInfoCompat.mParentVirtualDescendantId; i2 != -1; i2 = accessibilityNodeInfoCompat2.mParentVirtualDescendantId) {
                            View view2 = this.mHost;
                            accessibilityNodeInfoCompat2.mParentVirtualDescendantId = -1;
                            accessibilityNodeInfoCompat2.mInfo.setParent(view2, -1);
                            accessibilityNodeInfoCompat2.mInfo.setBoundsInParent(INVALID_PARENT_BOUNDS);
                            onPopulateNodeForVirtualView(i2, accessibilityNodeInfoCompat2);
                            accessibilityNodeInfoCompat2.mInfo.getBoundsInParent(this.mTempParentRect);
                            Rect rect2 = this.mTempScreenRect;
                            Rect rect3 = this.mTempParentRect;
                            rect2.offset(rect3.left, rect3.top);
                        }
                        accessibilityNodeInfoCompat2.mInfo.recycle();
                    }
                    this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                }
                if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                    this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                    if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                        accessibilityNodeInfoCompat.mInfo.setBoundsInScreen(this.mTempScreenRect);
                        Rect rect4 = this.mTempScreenRect;
                        if (rect4 != null && !rect4.isEmpty() && this.mHost.getWindowVisibility() == 0) {
                            ViewParent parent = this.mHost.getParent();
                            while (true) {
                                if (parent instanceof View) {
                                    View view3 = (View) parent;
                                    if (view3.getAlpha() <= 0.0f || view3.getVisibility() != 0) {
                                        break;
                                    }
                                    parent = view3.getParent();
                                } else if (parent != null) {
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            accessibilityNodeInfoCompat.mInfo.setVisibleToUser(true);
                        }
                    }
                }
                return accessibilityNodeInfoCompat;
            } else {
                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
        } else {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    public abstract void getVisibleVirtualViews(List<Integer> list);

    /* JADX WARN: Code restructure failed: missing block: B:60:0x012f, code lost:
        if (r13 < ((r17 * r17) + ((r12 * 13) * r12))) goto L_0x0131;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x013b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean moveFocus(int r20, android.graphics.Rect r21) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ExploreByTouchHelper.moveFocus(int, android.graphics.Rect):boolean");
    }

    public AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i) {
        if (i != -1) {
            return createNodeForChild(i);
        }
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.mHost);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(obtain);
        View view = this.mHost;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        view.onInitializeAccessibilityNodeInfo(obtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (obtain.getChildCount() <= 0 || arrayList.size() <= 0) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                accessibilityNodeInfoCompat.mInfo.addChild(this.mHost, ((Integer) arrayList.get(i2)).intValue());
            }
            return accessibilityNodeInfoCompat;
        }
        throw new RuntimeException("Views cannot have both real and virtual children");
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    public abstract boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle);

    public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int i2;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i2 = this.mKeyboardFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        this.mKeyboardFocusedVirtualViewId = i;
        onVirtualViewKeyboardFocusChanged(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        ViewParent parent;
        AccessibilityEvent accessibilityEvent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        if (i != -1) {
            accessibilityEvent = AccessibilityEvent.obtain(i2);
            AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
            accessibilityEvent.getText().add(obtainAccessibilityNodeInfo.getText());
            accessibilityEvent.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
            accessibilityEvent.setScrollable(obtainAccessibilityNodeInfo.mInfo.isScrollable());
            accessibilityEvent.setPassword(obtainAccessibilityNodeInfo.mInfo.isPassword());
            accessibilityEvent.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
            accessibilityEvent.setChecked(obtainAccessibilityNodeInfo.mInfo.isChecked());
            if (!accessibilityEvent.getText().isEmpty() || accessibilityEvent.getContentDescription() != null) {
                accessibilityEvent.setClassName(obtainAccessibilityNodeInfo.getClassName());
                accessibilityEvent.setSource(this.mHost, i);
                accessibilityEvent.setPackageName(this.mHost.getContext().getPackageName());
            } else {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
        } else {
            accessibilityEvent = AccessibilityEvent.obtain(i2);
            this.mHost.onInitializeAccessibilityEvent(accessibilityEvent);
        }
        return parent.requestSendAccessibilityEvent(this.mHost, accessibilityEvent);
    }

    public final void updateHoveredVirtualView(int i) {
        int i2 = this.mHoveredVirtualViewId;
        if (i2 != i) {
            this.mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(i2, 256);
        }
    }
}
