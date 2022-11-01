package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public boolean interceptingEvents;
    public OnDismissListener listener;
    public ViewDragHelper viewDragHelper;
    public int swipeDirection = 2;
    public float dragDismissThreshold = 0.5f;
    public float alphaStartSwipeDistance = 0.0f;
    public float alphaEndSwipeDistance = 0.5f;
    public final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1
        public int activePointerId = -1;
        public int originalCapturedViewLeft;

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int i3;
            int i4;
            int width;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            boolean z = view.getLayoutDirection() == 1;
            int i5 = SwipeDismissBehavior.this.swipeDirection;
            if (i5 == 0) {
                if (z) {
                    i3 = this.originalCapturedViewLeft - view.getWidth();
                    i4 = this.originalCapturedViewLeft;
                } else {
                    i3 = this.originalCapturedViewLeft;
                    width = view.getWidth();
                    i4 = width + i3;
                }
            } else if (i5 != 1) {
                i3 = this.originalCapturedViewLeft - view.getWidth();
                i4 = view.getWidth() + this.originalCapturedViewLeft;
            } else if (z) {
                i3 = this.originalCapturedViewLeft;
                width = view.getWidth();
                i4 = width + i3;
            } else {
                i3 = this.originalCapturedViewLeft - view.getWidth();
                i4 = this.originalCapturedViewLeft;
            }
            return Math.min(Math.max(i3, i), i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
            if (onDismissListener != null) {
                BaseTransientBottomBar.AnonymousClass9 r0 = (BaseTransientBottomBar.AnonymousClass9) onDismissListener;
                Objects.requireNonNull(r0);
                if (i == 0) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                } else if (i == 1 || i == 2) {
                    SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                }
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = (view.getWidth() * SwipeDismissBehavior.this.alphaStartSwipeDistance) + this.originalCapturedViewLeft;
            float width2 = (view.getWidth() * SwipeDismissBehavior.this.alphaEndSwipeDistance) + this.originalCapturedViewLeft;
            float f = i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - ((f - width) / (width2 - width)), 1.0f));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
            if (java.lang.Math.abs(r8.getLeft() - r7.originalCapturedViewLeft) >= java.lang.Math.round(r8.getWidth() * r7.this$0.dragDismissThreshold)) goto L_0x002c;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onViewReleased(android.view.View r8, float r9, float r10) {
            /*
                r7 = this;
                r10 = -1
                r7.activePointerId = r10
                int r10 = r8.getWidth()
                r0 = 0
                r1 = 0
                r2 = 1
                int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r3 == 0) goto L_0x003c
                java.util.concurrent.atomic.AtomicInteger r4 = androidx.core.view.ViewCompat.sNextGeneratedId
                int r4 = r8.getLayoutDirection()
                if (r4 != r2) goto L_0x0018
                r4 = 1
                goto L_0x0019
            L_0x0018:
                r4 = 0
            L_0x0019:
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r5 = r5.swipeDirection
                r6 = 2
                if (r5 != r6) goto L_0x0021
                goto L_0x002c
            L_0x0021:
                if (r5 != 0) goto L_0x0030
                if (r4 == 0) goto L_0x002a
                int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r9 >= 0) goto L_0x002e
                goto L_0x002c
            L_0x002a:
                if (r3 <= 0) goto L_0x002e
            L_0x002c:
                r9 = 1
                goto L_0x0059
            L_0x002e:
                r9 = 0
                goto L_0x0059
            L_0x0030:
                if (r5 != r2) goto L_0x002e
                if (r4 == 0) goto L_0x0037
                if (r3 <= 0) goto L_0x002e
                goto L_0x003b
            L_0x0037:
                int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r9 >= 0) goto L_0x002e
            L_0x003b:
                goto L_0x002c
            L_0x003c:
                int r9 = r8.getLeft()
                int r0 = r7.originalCapturedViewLeft
                int r9 = r9 - r0
                int r0 = r8.getWidth()
                float r0 = (float) r0
                com.google.android.material.behavior.SwipeDismissBehavior r3 = com.google.android.material.behavior.SwipeDismissBehavior.this
                float r3 = r3.dragDismissThreshold
                float r0 = r0 * r3
                int r0 = java.lang.Math.round(r0)
                int r9 = java.lang.Math.abs(r9)
                if (r9 < r0) goto L_0x002e
                goto L_0x002c
            L_0x0059:
                if (r9 == 0) goto L_0x0068
                int r9 = r8.getLeft()
                int r0 = r7.originalCapturedViewLeft
                if (r9 >= r0) goto L_0x0065
                int r0 = r0 - r10
                goto L_0x0066
            L_0x0065:
                int r0 = r0 + r10
            L_0x0066:
                r1 = 1
                goto L_0x006a
            L_0x0068:
                int r0 = r7.originalCapturedViewLeft
            L_0x006a:
                com.google.android.material.behavior.SwipeDismissBehavior r9 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.ViewDragHelper r9 = r9.viewDragHelper
                int r10 = r8.getTop()
                boolean r9 = r9.settleCapturedViewAt(r0, r10)
                if (r9 == 0) goto L_0x0085
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r9 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                com.google.android.material.behavior.SwipeDismissBehavior r10 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r9.<init>(r8, r1)
                java.util.concurrent.atomic.AtomicInteger r10 = androidx.core.view.ViewCompat.sNextGeneratedId
                r8.postOnAnimation(r9)
                goto L_0x0092
            L_0x0085:
                if (r1 == 0) goto L_0x0092
                com.google.android.material.behavior.SwipeDismissBehavior r9 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$OnDismissListener r9 = r9.listener
                if (r9 == 0) goto L_0x0092
                com.google.android.material.snackbar.BaseTransientBottomBar$9 r9 = (com.google.android.material.snackbar.BaseTransientBottomBar.AnonymousClass9) r9
                r9.onDismiss(r8)
            L_0x0092:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            int i2 = this.activePointerId;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    };

    /* loaded from: classes.dex */
    public interface OnDismissListener {
    }

    /* loaded from: classes.dex */
    public class SettleRunnable implements Runnable {
        public final boolean dismiss;
        public final View view;

        public SettleRunnable(View view, boolean z) {
            this.view = view;
            this.dismiss = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.viewDragHelper;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                View view = this.view;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view.postOnAnimation(this);
            } else if (this.dismiss && (onDismissListener = SwipeDismissBehavior.this.listener) != null) {
                ((BaseTransientBottomBar.AnonymousClass9) onDismissListener).onDismiss(this.view);
            }
        }
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    public boolean canSwipeDismissView(View view) {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!z) {
            return false;
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        return this.viewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (v.getImportantForAccessibility() == 0) {
            v.setImportantForAccessibility(1);
            ViewCompat.removeActionWithId(1048576, v);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(v, 0);
            if (canSwipeDismissView(v)) {
                ViewCompat.replaceAccessibilityAction(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new AccessibilityViewCommand() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.2
                    @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                    public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                        boolean z = false;
                        if (!SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                            return false;
                        }
                        AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                        boolean z2 = view.getLayoutDirection() == 1;
                        int i2 = SwipeDismissBehavior.this.swipeDirection;
                        if ((i2 == 0 && z2) || (i2 == 1 && !z2)) {
                            z = true;
                        }
                        int width = view.getWidth();
                        if (z) {
                            width = -width;
                        }
                        ViewCompat.offsetLeftAndRight(view, width);
                        view.setAlpha(0.0f);
                        OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
                        if (onDismissListener != null) {
                            ((BaseTransientBottomBar.AnonymousClass9) onDismissListener).onDismiss(view);
                        }
                        return true;
                    }
                });
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper == null) {
            return false;
        }
        viewDragHelper.processTouchEvent(motionEvent);
        return true;
    }
}
