package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import com.tidalab.v2board.clash.foss.R;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static TooltipCompatHandler sActiveHandler;
    public static TooltipCompatHandler sPendingHandler;
    public final View mAnchor;
    public int mAnchorX;
    public int mAnchorY;
    public boolean mFromTouch;
    public final int mHoverSlop;
    public TooltipPopup mPopup;
    public final CharSequence mTooltipText;
    public final Runnable mShowRunnable = new Runnable() { // from class: androidx.appcompat.widget.TooltipCompatHandler.1
        @Override // java.lang.Runnable
        public void run() {
            TooltipCompatHandler.this.show(false);
        }
    };
    public final Runnable mHideRunnable = new Runnable() { // from class: androidx.appcompat.widget.TooltipCompatHandler.2
        @Override // java.lang.Runnable
        public void run() {
            TooltipCompatHandler.this.hide();
        }
    };

    public TooltipCompatHandler(View view, CharSequence charSequence) {
        int i;
        this.mAnchor = view;
        this.mTooltipText = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        Method method = ViewConfigurationCompat.sGetScaledScrollFactorMethod;
        if (Build.VERSION.SDK_INT >= 28) {
            i = viewConfiguration.getScaledHoverSlop();
        } else {
            i = viewConfiguration.getScaledTouchSlop() / 2;
        }
        this.mHoverSlop = i;
        clearAnchorPos();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void setPendingHandler(TooltipCompatHandler tooltipCompatHandler) {
        TooltipCompatHandler tooltipCompatHandler2 = sPendingHandler;
        if (tooltipCompatHandler2 != null) {
            tooltipCompatHandler2.mAnchor.removeCallbacks(tooltipCompatHandler2.mShowRunnable);
        }
        sPendingHandler = tooltipCompatHandler;
        if (tooltipCompatHandler != null) {
            tooltipCompatHandler.mAnchor.postDelayed(tooltipCompatHandler.mShowRunnable, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void clearAnchorPos() {
        this.mAnchorX = Integer.MAX_VALUE;
        this.mAnchorY = Integer.MAX_VALUE;
    }

    public void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            TooltipPopup tooltipPopup = this.mPopup;
            if (tooltipPopup != null) {
                tooltipPopup.hide();
                this.mPopup = null;
                clearAnchorPos();
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (sPendingHandler == this) {
            setPendingHandler(null);
        }
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        boolean z;
        if (this.mPopup != null && this.mFromTouch) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                clearAnchorPos();
                hide();
            }
        } else if (this.mAnchor.isEnabled() && this.mPopup == null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (Math.abs(x - this.mAnchorX) > this.mHoverSlop || Math.abs(y - this.mAnchorY) > this.mHoverSlop) {
                this.mAnchorX = x;
                this.mAnchorY = y;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                setPendingHandler(this);
            }
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.mAnchorX = view.getWidth() / 2;
        this.mAnchorY = view.getHeight() / 2;
        show(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        hide();
    }

    public void show(boolean z) {
        int i;
        int i2;
        long j;
        int i3;
        long j2;
        View view = this.mAnchor;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (view.isAttachedToWindow()) {
            setPendingHandler(null);
            TooltipCompatHandler tooltipCompatHandler = sActiveHandler;
            if (tooltipCompatHandler != null) {
                tooltipCompatHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = z;
            TooltipPopup tooltipPopup = new TooltipPopup(this.mAnchor.getContext());
            this.mPopup = tooltipPopup;
            View view2 = this.mAnchor;
            int i4 = this.mAnchorX;
            int i5 = this.mAnchorY;
            boolean z2 = this.mFromTouch;
            CharSequence charSequence = this.mTooltipText;
            if (tooltipPopup.mContentView.getParent() != null) {
                tooltipPopup.hide();
            }
            tooltipPopup.mMessageView.setText(charSequence);
            WindowManager.LayoutParams layoutParams = tooltipPopup.mLayoutParams;
            layoutParams.token = view2.getApplicationWindowToken();
            int dimensionPixelOffset = tooltipPopup.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
            if (view2.getWidth() < dimensionPixelOffset) {
                i4 = view2.getWidth() / 2;
            }
            if (view2.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = tooltipPopup.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
                i = i5 + dimensionPixelOffset2;
                i2 = i5 - dimensionPixelOffset2;
            } else {
                i = view2.getHeight();
                i2 = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = tooltipPopup.mContext.getResources().getDimensionPixelOffset(z2 ? R.dimen.tooltip_y_offset_touch : R.dimen.tooltip_y_offset_non_touch);
            View rootView = view2.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                Context context = view2.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else if (context instanceof Activity) {
                        rootView = ((Activity) context).getWindow().getDecorView();
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
            } else {
                rootView.getWindowVisibleDisplayFrame(tooltipPopup.mTmpDisplayFrame);
                Rect rect = tooltipPopup.mTmpDisplayFrame;
                if (rect.left < 0 && rect.top < 0) {
                    Resources resources = tooltipPopup.mContext.getResources();
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    tooltipPopup.mTmpDisplayFrame.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen(tooltipPopup.mTmpAppPos);
                view2.getLocationOnScreen(tooltipPopup.mTmpAnchorPos);
                int[] iArr = tooltipPopup.mTmpAnchorPos;
                int i6 = iArr[0];
                int[] iArr2 = tooltipPopup.mTmpAppPos;
                iArr[0] = i6 - iArr2[0];
                iArr[1] = iArr[1] - iArr2[1];
                layoutParams.x = (iArr[0] + i4) - (rootView.getWidth() / 2);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                tooltipPopup.mContentView.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredHeight = tooltipPopup.mContentView.getMeasuredHeight();
                int[] iArr3 = tooltipPopup.mTmpAnchorPos;
                int i7 = ((iArr3[1] + i2) - dimensionPixelOffset3) - measuredHeight;
                int i8 = iArr3[1] + i + dimensionPixelOffset3;
                if (z2) {
                    if (i7 >= 0) {
                        layoutParams.y = i7;
                    } else {
                        layoutParams.y = i8;
                    }
                } else if (measuredHeight + i8 <= tooltipPopup.mTmpDisplayFrame.height()) {
                    layoutParams.y = i8;
                } else {
                    layoutParams.y = i7;
                }
            }
            ((WindowManager) tooltipPopup.mContext.getSystemService("window")).addView(tooltipPopup.mContentView, tooltipPopup.mLayoutParams);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                j = 2500;
            } else {
                if ((this.mAnchor.getWindowSystemUiVisibility() & 1) == 1) {
                    j2 = 3000;
                    i3 = ViewConfiguration.getLongPressTimeout();
                } else {
                    j2 = 15000;
                    i3 = ViewConfiguration.getLongPressTimeout();
                }
                j = j2 - i3;
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
            this.mAnchor.postDelayed(this.mHideRunnable, j);
        }
    }
}
