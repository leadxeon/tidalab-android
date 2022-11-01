package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.timepicker.ClockHandView;
import com.tidalab.v2board.clash.foss.R;
import java.util.Arrays;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    public final int clockHandPadding;
    public final ClockHandView clockHandView;
    public float currentHandRotation;
    public final int[] gradientColors;
    public final ColorStateList textColor;
    public final SparseArray<TextView> textViewPool;
    public final AccessibilityDelegateCompat valueAccessibilityDelegate;
    public String[] values;
    public final Rect textViewRect = new Rect();
    public final RectF scratch = new RectF();
    public final float[] gradientPositions = {0.0f, 0.9f, 1.0f};

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        SparseArray<TextView> sparseArray = new SparseArray<>();
        this.textViewPool = sparseArray;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, R.attr.materialClockStyle, 2131886821);
        Resources resources = getResources();
        ColorStateList colorStateList = R$style.getColorStateList(context, obtainStyledAttributes, 1);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.clockHandView = clockHandView;
        this.clockHandPadding = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView.listeners.add(this);
        int defaultColor = AppCompatResources.getColorStateList(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList2 = R$style.getColorStateList(context, obtainStyledAttributes, 0);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.timepicker.ClockFaceView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView clockFaceView = ClockFaceView.this;
                int height = ((ClockFaceView.this.getHeight() / 2) - clockFaceView.clockHandView.selectorRadius) - clockFaceView.clockHandPadding;
                if (height != clockFaceView.radius) {
                    clockFaceView.radius = height;
                    clockFaceView.updateLayoutParams();
                    ClockHandView clockHandView2 = clockFaceView.clockHandView;
                    clockHandView2.circleRadius = clockFaceView.radius;
                    clockHandView2.invalidate();
                }
                return true;
            }
        });
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.material.timepicker.ClockFaceView.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                int intValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
                if (intValue > 0) {
                    TextView textView = ClockFaceView.this.textViewPool.get(intValue - 1);
                    if (Build.VERSION.SDK_INT >= 22) {
                        accessibilityNodeInfoCompat.mInfo.setTraversalAfter(textView);
                    }
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, intValue, 1, false, view.isSelected()));
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, HttpUrl.FRAGMENT_ENCODE_SET);
        this.values = strArr;
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        for (int i = 0; i < Math.max(this.values.length, size); i++) {
            TextView textView = this.textViewPool.get(i);
            if (i >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    this.textViewPool.put(i, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.values[i]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i));
                ViewCompat.setAccessibilityDelegate(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
            }
        }
    }

    public final void findIntersectingTextView() {
        RectF rectF = this.clockHandView.selectorBox;
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView textView = this.textViewPool.get(i);
            if (textView != null) {
                textView.getDrawingRect(this.textViewRect);
                this.textViewRect.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                offsetDescendantRectToMyCoords(textView, this.textViewRect);
                this.scratch.set(this.textViewRect);
                textView.getPaint().setShader(!RectF.intersects(rectF, this.scratch) ? null : new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, 0.5f * rectF.width(), this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP));
                textView.invalidate();
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.values.length, false, 1).mInfo);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        findIntersectingTextView();
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f, boolean z) {
        if (Math.abs(this.currentHandRotation - f) > 0.001f) {
            this.currentHandRotation = f;
            findIntersectingTextView();
        }
    }
}
