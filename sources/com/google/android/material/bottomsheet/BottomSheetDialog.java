package com.google.android.material.bottomsheet;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class BottomSheetDialog extends AppCompatDialog {
    public BottomSheetBehavior<FrameLayout> behavior;
    public BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    public boolean cancelable;
    public boolean canceledOnTouchOutside;
    public boolean canceledOnTouchOutsideSet;
    public FrameLayout container;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BottomSheetDialog(android.content.Context r5) {
        /*
            r4 = this;
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.res.Resources$Theme r1 = r5.getTheme()
            r2 = 2130968673(0x7f040061, float:1.7546006E38)
            r3 = 1
            boolean r1 = r1.resolveAttribute(r2, r0, r3)
            if (r1 == 0) goto L_0x0016
            int r0 = r0.resourceId
            goto L_0x0019
        L_0x0016:
            r0 = 2131886522(0x7f1201ba, float:1.9407625E38)
        L_0x0019:
            r4.<init>(r5, r0)
            r4.cancelable = r3
            r4.canceledOnTouchOutside = r3
            com.google.android.material.bottomsheet.BottomSheetDialog$4 r5 = new com.google.android.material.bottomsheet.BottomSheetDialog$4
            r5.<init>()
            r4.bottomSheetCallback = r5
            androidx.appcompat.app.AppCompatDelegate r5 = r4.getDelegate()
            r5.requestWindowFeature(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetDialog.<init>(android.content.Context):void");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        getBehavior();
        super.cancel();
    }

    public final FrameLayout ensureContainerAndBehavior() {
        if (this.container == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
            this.container = frameLayout;
            ViewGroup.LayoutParams layoutParams = ((FrameLayout) frameLayout.findViewById(R.id.design_bottom_sheet)).getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior;
                if (behavior instanceof BottomSheetBehavior) {
                    BottomSheetBehavior<FrameLayout> bottomSheetBehavior = (BottomSheetBehavior) behavior;
                    this.behavior = bottomSheetBehavior;
                    BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = this.bottomSheetCallback;
                    if (!bottomSheetBehavior.callbacks.contains(bottomSheetCallback)) {
                        bottomSheetBehavior.callbacks.add(bottomSheetCallback);
                    }
                    this.behavior.setHideable(this.cancelable);
                } else {
                    throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
                }
            } else {
                throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
            }
        }
        return this.container;
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        if (this.behavior == null) {
            ensureContainerAndBehavior();
        }
        return this.behavior;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
        if (bottomSheetBehavior != null && bottomSheetBehavior.state == 5) {
            bottomSheetBehavior.setState(4);
        }
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.cancelable != z) {
            this.cancelable = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(int i) {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    public final View wrapInBottomSheet(int i, View view, ViewGroup.LayoutParams layoutParams) {
        ensureContainerAndBehavior();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.container.findViewById(R.id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, (ViewGroup) coordinatorLayout, false);
        }
        FrameLayout frameLayout = (FrameLayout) this.container.findViewById(R.id.design_bottom_sheet);
        frameLayout.removeAllViews();
        if (layoutParams == null) {
            frameLayout.addView(view);
        } else {
            frameLayout.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                if (bottomSheetDialog.cancelable && bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = BottomSheetDialog.this;
                    if (!bottomSheetDialog2.canceledOnTouchOutsideSet) {
                        TypedArray obtainStyledAttributes = bottomSheetDialog2.getContext().obtainStyledAttributes(new int[]{16843611});
                        bottomSheetDialog2.canceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
                        obtainStyledAttributes.recycle();
                        bottomSheetDialog2.canceledOnTouchOutsideSet = true;
                    }
                    if (bottomSheetDialog2.canceledOnTouchOutside) {
                        BottomSheetDialog.this.cancel();
                    }
                }
            }
        });
        ViewCompat.setAccessibilityDelegate(frameLayout, new AccessibilityDelegateCompat() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat.mInfo);
                if (BottomSheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat.mInfo.addAction(1048576);
                    accessibilityNodeInfoCompat.mInfo.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat.mInfo.setDismissable(false);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view2, int i2, Bundle bundle) {
                if (i2 == 1048576) {
                    BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                    if (bottomSheetDialog.cancelable) {
                        bottomSheetDialog.cancel();
                        return true;
                    }
                }
                return super.performAccessibilityAction(view2, i2, bundle);
            }
        });
        frameLayout.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                return true;
            }
        });
        return this.container;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }
}
