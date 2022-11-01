package com.google.android.material.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate, Shapeable {
    public ChipDrawable chipDrawable;
    public boolean closeIconFocused;
    public boolean closeIconHovered;
    public boolean closeIconPressed;
    public boolean deferredCheckedValue;
    public boolean ensureMinTouchTargetSize;
    public InsetDrawable insetBackgroundDrawable;
    public int lastLayoutDirection;
    public int minTouchTargetSize;
    public CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
    public View.OnClickListener onCloseIconClickListener;
    public RippleDrawable ripple;
    public final ChipTouchHelper touchHelper;
    public static final Rect EMPTY_BOUNDS = new Rect();
    public static final int[] SELECTED_STATE = {16842913};
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public final Rect rect = new Rect();
    public final RectF rectF = new RectF();
    public final TextAppearanceFontCallback fontCallback = new TextAppearanceFontCallback() { // from class: com.google.android.material.chip.Chip.1
        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i) {
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(Typeface typeface, boolean z) {
            CharSequence charSequence;
            Chip chip = Chip.this;
            ChipDrawable chipDrawable = chip.chipDrawable;
            if (chipDrawable.shouldDrawText) {
                charSequence = chipDrawable.text;
            } else {
                charSequence = chip.getText();
            }
            chip.setText(charSequence);
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    };

    /* loaded from: classes.dex */
    public class ChipTouchHelper extends ExploreByTouchHelper {
        public ChipTouchHelper(Chip chip) {
            super(chip);
        }

        public int getVirtualViewAt(float f, float f2) {
            Chip chip = Chip.this;
            Rect rect = Chip.EMPTY_BOUNDS;
            return (!chip.hasCloseIcon() || !Chip.this.getCloseIconTouchBounds().contains(f, f2)) ? 0 : 1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> list) {
            boolean z = false;
            list.add(0);
            Chip chip = Chip.this;
            Rect rect = Chip.EMPTY_BOUNDS;
            if (chip.hasCloseIcon()) {
                Chip chip2 = Chip.this;
                ChipDrawable chipDrawable = chip2.chipDrawable;
                if (chipDrawable != null && chipDrawable.closeIconVisible) {
                    z = true;
                }
                if (z && chip2.onCloseIconClickListener != null) {
                    list.add(1);
                }
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            boolean z = false;
            if (i2 == 16) {
                if (i == 0) {
                    return Chip.this.performClick();
                }
                if (i == 1) {
                    Chip chip = Chip.this;
                    chip.playSoundEffect(0);
                    View.OnClickListener onClickListener = chip.onCloseIconClickListener;
                    if (onClickListener != null) {
                        onClickListener.onClick(chip);
                        z = true;
                    }
                    chip.touchHelper.sendEventForVirtualView(1, 1);
                }
            }
            return z;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.mInfo.setCheckable(Chip.this.isCheckable());
            accessibilityNodeInfoCompat.mInfo.setClickable(Chip.this.isClickable());
            if (Chip.this.isCheckable() || Chip.this.isClickable()) {
                accessibilityNodeInfoCompat.mInfo.setClassName(Chip.this.isCheckable() ? "android.widget.CompoundButton" : "android.widget.Button");
            } else {
                accessibilityNodeInfoCompat.mInfo.setClassName("android.view.View");
            }
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityNodeInfoCompat.mInfo.setText(text);
            } else {
                accessibilityNodeInfoCompat.mInfo.setContentDescription(text);
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            String str = HttpUrl.FRAGMENT_ENCODE_SET;
            if (i == 1) {
                CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
                if (closeIconContentDescription != null) {
                    accessibilityNodeInfoCompat.mInfo.setContentDescription(closeIconContentDescription);
                } else {
                    CharSequence text = Chip.this.getText();
                    Context context = Chip.this.getContext();
                    Object[] objArr = new Object[1];
                    if (!TextUtils.isEmpty(text)) {
                        str = text;
                    }
                    objArr[0] = str;
                    accessibilityNodeInfoCompat.mInfo.setContentDescription(context.getString(R.string.mtrl_chip_close_icon_content_description, objArr).trim());
                }
                accessibilityNodeInfoCompat.mInfo.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat.mInfo.setEnabled(Chip.this.isEnabled());
                return;
            }
            accessibilityNodeInfoCompat.mInfo.setContentDescription(str);
            accessibilityNodeInfoCompat.mInfo.setBoundsInParent(Chip.EMPTY_BOUNDS);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
            if (i == 1) {
                Chip chip = Chip.this;
                chip.closeIconFocused = z;
                chip.refreshDrawableState();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.chipStyle, 2131886751), attributeSet, R.attr.chipStyle);
        int resourceId;
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", AppStateModule.APP_STATE_BACKGROUND) != null) {
                Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            } else if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                Log.w("Chip", "Chip text must be vertically center and start aligned");
            }
        }
        ChipDrawable chipDrawable = new ChipDrawable(context2, attributeSet, R.attr.chipStyle, 2131886751);
        Context context3 = chipDrawable.context;
        int[] iArr = R$styleable.Chip;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context3, attributeSet, iArr, R.attr.chipStyle, 2131886751, new int[0]);
        chipDrawable.isShapeThemingEnabled = obtainStyledAttributes.hasValue(37);
        ColorStateList colorStateList = R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 24);
        if (chipDrawable.chipSurfaceColor != colorStateList) {
            chipDrawable.chipSurfaceColor = colorStateList;
            chipDrawable.onStateChange(chipDrawable.getState());
        }
        chipDrawable.setChipBackgroundColor(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 11));
        chipDrawable.setChipMinHeight(obtainStyledAttributes.getDimension(19, 0.0f));
        if (obtainStyledAttributes.hasValue(12)) {
            chipDrawable.setChipCornerRadius(obtainStyledAttributes.getDimension(12, 0.0f));
        }
        chipDrawable.setChipStrokeColor(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 22));
        chipDrawable.setChipStrokeWidth(obtainStyledAttributes.getDimension(23, 0.0f));
        chipDrawable.setRippleColor(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 36));
        chipDrawable.setText(obtainStyledAttributes.getText(5));
        TextAppearance textAppearance = (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) ? null : new TextAppearance(chipDrawable.context, resourceId);
        textAppearance.textSize = obtainStyledAttributes.getDimension(1, textAppearance.textSize);
        chipDrawable.textDrawableHelper.setTextAppearance(textAppearance, chipDrawable.context);
        int i = obtainStyledAttributes.getInt(3, 0);
        if (i == 1) {
            chipDrawable.truncateAt = TextUtils.TruncateAt.START;
        } else if (i == 2) {
            chipDrawable.truncateAt = TextUtils.TruncateAt.MIDDLE;
        } else if (i == 3) {
            chipDrawable.truncateAt = TextUtils.TruncateAt.END;
        }
        chipDrawable.setChipIconVisible(obtainStyledAttributes.getBoolean(18, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") != null)) {
            chipDrawable.setChipIconVisible(obtainStyledAttributes.getBoolean(15, false));
        }
        chipDrawable.setChipIcon(R$style.getDrawable(chipDrawable.context, obtainStyledAttributes, 14));
        if (obtainStyledAttributes.hasValue(17)) {
            chipDrawable.setChipIconTint(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 17));
        }
        chipDrawable.setChipIconSize(obtainStyledAttributes.getDimension(16, -1.0f));
        chipDrawable.setCloseIconVisible(obtainStyledAttributes.getBoolean(31, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") != null)) {
            chipDrawable.setCloseIconVisible(obtainStyledAttributes.getBoolean(26, false));
        }
        chipDrawable.setCloseIcon(R$style.getDrawable(chipDrawable.context, obtainStyledAttributes, 25));
        chipDrawable.setCloseIconTint(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 30));
        chipDrawable.setCloseIconSize(obtainStyledAttributes.getDimension(28, 0.0f));
        chipDrawable.setCheckable(obtainStyledAttributes.getBoolean(6, false));
        chipDrawable.setCheckedIconVisible(obtainStyledAttributes.getBoolean(10, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") != null)) {
            chipDrawable.setCheckedIconVisible(obtainStyledAttributes.getBoolean(8, false));
        }
        chipDrawable.setCheckedIcon(R$style.getDrawable(chipDrawable.context, obtainStyledAttributes, 7));
        if (obtainStyledAttributes.hasValue(9)) {
            chipDrawable.setCheckedIconTint(R$style.getColorStateList(chipDrawable.context, obtainStyledAttributes, 9));
        }
        chipDrawable.showMotionSpec = MotionSpec.createFromAttribute(chipDrawable.context, obtainStyledAttributes, 39);
        chipDrawable.hideMotionSpec = MotionSpec.createFromAttribute(chipDrawable.context, obtainStyledAttributes, 33);
        chipDrawable.setChipStartPadding(obtainStyledAttributes.getDimension(21, 0.0f));
        chipDrawable.setIconStartPadding(obtainStyledAttributes.getDimension(35, 0.0f));
        chipDrawable.setIconEndPadding(obtainStyledAttributes.getDimension(34, 0.0f));
        chipDrawable.setTextStartPadding(obtainStyledAttributes.getDimension(41, 0.0f));
        chipDrawable.setTextEndPadding(obtainStyledAttributes.getDimension(40, 0.0f));
        chipDrawable.setCloseIconStartPadding(obtainStyledAttributes.getDimension(29, 0.0f));
        chipDrawable.setCloseIconEndPadding(obtainStyledAttributes.getDimension(27, 0.0f));
        chipDrawable.setChipEndPadding(obtainStyledAttributes.getDimension(13, 0.0f));
        chipDrawable.maxWidth = obtainStyledAttributes.getDimensionPixelSize(4, Integer.MAX_VALUE);
        obtainStyledAttributes.recycle();
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, R.attr.chipStyle, 2131886751);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, R.attr.chipStyle, 2131886751, new int[0]);
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr, R.attr.chipStyle, 2131886751);
        this.ensureMinTouchTargetSize = obtainStyledAttributes2.getBoolean(32, false);
        this.minTouchTargetSize = (int) Math.ceil(obtainStyledAttributes2.getDimension(20, (float) Math.ceil(R$style.dpToPx(getContext(), 48))));
        obtainStyledAttributes2.recycle();
        setChipDrawable(chipDrawable);
        chipDrawable.setElevation(getElevation());
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, R.attr.chipStyle, 2131886751);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, R.attr.chipStyle, 2131886751, new int[0]);
        TypedArray obtainStyledAttributes3 = context2.obtainStyledAttributes(attributeSet, iArr, R.attr.chipStyle, 2131886751);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(R$style.getColorStateList(context2, obtainStyledAttributes3, 2));
        }
        boolean hasValue = obtainStyledAttributes3.hasValue(37);
        obtainStyledAttributes3.recycle();
        this.touchHelper = new ChipTouchHelper(this);
        updateAccessibilityDelegate();
        if (!hasValue) {
            setOutlineProvider(new ViewOutlineProvider() { // from class: com.google.android.material.chip.Chip.2
                @Override // android.view.ViewOutlineProvider
                @TargetApi(21)
                public void getOutline(View view, Outline outline) {
                    ChipDrawable chipDrawable2 = Chip.this.chipDrawable;
                    if (chipDrawable2 != null) {
                        chipDrawable2.getOutline(outline);
                    } else {
                        outline.setAlpha(0.0f);
                    }
                }
            });
        }
        setChecked(this.deferredCheckedValue);
        setText(chipDrawable.text);
        setEllipsize(chipDrawable.truncateAt);
        updateTextPaintDrawState();
        if (!this.chipDrawable.shouldDrawText) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        updatePaddingInternal();
        if (this.ensureMinTouchTargetSize) {
            setMinHeight(this.minTouchTargetSize);
        }
        this.lastLayoutDirection = getLayoutDirection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if (hasCloseIcon() && this.onCloseIconClickListener != null) {
            ChipDrawable chipDrawable = this.chipDrawable;
            chipDrawable.calculateCloseIconTouchBounds(chipDrawable.getBounds(), this.rectF);
        }
        return this.rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.rect.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.rect;
    }

    private TextAppearance getTextAppearance() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.textDrawableHelper.textAppearance;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.closeIconHovered != z) {
            this.closeIconHovered = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.closeIconPressed != z) {
            this.closeIconPressed = z;
            refreshDrawableState();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ab, code lost:
        if (r1 != Integer.MIN_VALUE) goto L_0x00ad;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchHoverEvent(android.view.MotionEvent r11) {
        /*
            r10 = this;
            java.lang.Class<androidx.customview.widget.ExploreByTouchHelper> r0 = androidx.customview.widget.ExploreByTouchHelper.class
            java.lang.String r1 = "Unable to send Accessibility Exit event"
            java.lang.String r2 = "Chip"
            int r3 = r11.getAction()
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 10
            r6 = 0
            r7 = 1
            if (r3 != r5) goto L_0x005a
            java.lang.String r3 = "mHoveredVirtualViewId"
            java.lang.reflect.Field r3 = r0.getDeclaredField(r3)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r3.setAccessible(r7)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            com.google.android.material.chip.Chip$ChipTouchHelper r8 = r10.touchHelper     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.Object r3 = r3.get(r8)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            int r3 = r3.intValue()     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            if (r3 == r4) goto L_0x005a
            java.lang.String r3 = "updateHoveredVirtualView"
            java.lang.Class[] r8 = new java.lang.Class[r7]     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r8[r6] = r9     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r3, r8)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r0.setAccessible(r7)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            com.google.android.material.chip.Chip$ChipTouchHelper r3 = r10.touchHelper     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r8[r6] = r9     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r0.invoke(r3, r8)     // Catch: NoSuchFieldException -> 0x0047, InvocationTargetException -> 0x004c, IllegalAccessException -> 0x0051, NoSuchMethodException -> 0x0056
            r0 = 1
            goto L_0x005b
        L_0x0047:
            r0 = move-exception
            android.util.Log.e(r2, r1, r0)
            goto L_0x005a
        L_0x004c:
            r0 = move-exception
            android.util.Log.e(r2, r1, r0)
            goto L_0x005a
        L_0x0051:
            r0 = move-exception
            android.util.Log.e(r2, r1, r0)
            goto L_0x005a
        L_0x0056:
            r0 = move-exception
            android.util.Log.e(r2, r1, r0)
        L_0x005a:
            r0 = 0
        L_0x005b:
            if (r0 != 0) goto L_0x00b8
            com.google.android.material.chip.Chip$ChipTouchHelper r0 = r10.touchHelper
            android.view.accessibility.AccessibilityManager r1 = r0.mManager
            boolean r1 = r1.isEnabled()
            if (r1 == 0) goto L_0x00af
            android.view.accessibility.AccessibilityManager r1 = r0.mManager
            boolean r1 = r1.isTouchExplorationEnabled()
            if (r1 != 0) goto L_0x0070
            goto L_0x00af
        L_0x0070:
            int r1 = r11.getAction()
            r2 = 7
            r3 = 256(0x100, float:3.59E-43)
            r8 = 128(0x80, float:1.794E-43)
            if (r1 == r2) goto L_0x0092
            r2 = 9
            if (r1 == r2) goto L_0x0092
            if (r1 == r5) goto L_0x0082
            goto L_0x00af
        L_0x0082:
            int r1 = r0.mHoveredVirtualViewId
            if (r1 == r4) goto L_0x00af
            if (r1 != r4) goto L_0x0089
            goto L_0x00ad
        L_0x0089:
            r0.mHoveredVirtualViewId = r4
            r0.sendEventForVirtualView(r4, r8)
            r0.sendEventForVirtualView(r1, r3)
            goto L_0x00ad
        L_0x0092:
            float r1 = r11.getX()
            float r2 = r11.getY()
            int r1 = r0.getVirtualViewAt(r1, r2)
            int r2 = r0.mHoveredVirtualViewId
            if (r2 != r1) goto L_0x00a3
            goto L_0x00ab
        L_0x00a3:
            r0.mHoveredVirtualViewId = r1
            r0.sendEventForVirtualView(r1, r8)
            r0.sendEventForVirtualView(r2, r3)
        L_0x00ab:
            if (r1 == r4) goto L_0x00af
        L_0x00ad:
            r0 = 1
            goto L_0x00b0
        L_0x00af:
            r0 = 0
        L_0x00b0:
            if (r0 != 0) goto L_0x00b8
            boolean r11 = super.dispatchHoverEvent(r11)
            if (r11 == 0) goto L_0x00b9
        L_0x00b8:
            r6 = 1
        L_0x00b9:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ChipTouchHelper chipTouchHelper = this.touchHelper;
        Objects.requireNonNull(chipTouchHelper);
        boolean z = false;
        int i = 0;
        z = false;
        z = false;
        z = false;
        z = false;
        z = false;
        if (keyEvent.getAction() != 1) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                int i2 = 66;
                if (keyCode != 66) {
                    switch (keyCode) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                            if (keyEvent.hasNoModifiers()) {
                                if (keyCode == 19) {
                                    i2 = 33;
                                } else if (keyCode == 21) {
                                    i2 = 17;
                                } else if (keyCode != 22) {
                                    i2 = 130;
                                }
                                int repeatCount = keyEvent.getRepeatCount() + 1;
                                z = false;
                                while (i < repeatCount && chipTouchHelper.moveFocus(i2, null)) {
                                    i++;
                                    z = true;
                                }
                                break;
                            }
                            break;
                    }
                }
                if (keyEvent.hasNoModifiers() && keyEvent.getRepeatCount() == 0) {
                    int i3 = chipTouchHelper.mKeyboardFocusedVirtualViewId;
                    if (i3 != Integer.MIN_VALUE) {
                        chipTouchHelper.onPerformActionForVirtualView(i3, 16, null);
                    }
                    z = true;
                }
            } else if (keyEvent.hasNoModifiers()) {
                z = chipTouchHelper.moveFocus(2, null);
            } else if (keyEvent.hasModifiers(1)) {
                z = chipTouchHelper.moveFocus(1, null);
            }
        }
        if (!z || this.touchHelper.mKeyboardFocusedVirtualViewId == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [int, boolean] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drawableStateChanged() {
        /*
            r4 = this;
            super.drawableStateChanged()
            com.google.android.material.chip.ChipDrawable r0 = r4.chipDrawable
            r1 = 0
            if (r0 == 0) goto L_0x006e
            android.graphics.drawable.Drawable r0 = r0.closeIcon
            boolean r0 = com.google.android.material.chip.ChipDrawable.isStateful(r0)
            if (r0 == 0) goto L_0x006e
            com.google.android.material.chip.ChipDrawable r0 = r4.chipDrawable
            boolean r2 = r4.isEnabled()
            boolean r3 = r4.closeIconFocused
            if (r3 == 0) goto L_0x001c
            int r2 = r2 + 1
        L_0x001c:
            boolean r3 = r4.closeIconHovered
            if (r3 == 0) goto L_0x0022
            int r2 = r2 + 1
        L_0x0022:
            boolean r3 = r4.closeIconPressed
            if (r3 == 0) goto L_0x0028
            int r2 = r2 + 1
        L_0x0028:
            boolean r3 = r4.isChecked()
            if (r3 == 0) goto L_0x0030
            int r2 = r2 + 1
        L_0x0030:
            int[] r2 = new int[r2]
            boolean r3 = r4.isEnabled()
            if (r3 == 0) goto L_0x003e
            r3 = 16842910(0x101009e, float:2.3694E-38)
            r2[r1] = r3
            r1 = 1
        L_0x003e:
            boolean r3 = r4.closeIconFocused
            if (r3 == 0) goto L_0x0049
            r3 = 16842908(0x101009c, float:2.3693995E-38)
            r2[r1] = r3
            int r1 = r1 + 1
        L_0x0049:
            boolean r3 = r4.closeIconHovered
            if (r3 == 0) goto L_0x0054
            r3 = 16843623(0x1010367, float:2.3696E-38)
            r2[r1] = r3
            int r1 = r1 + 1
        L_0x0054:
            boolean r3 = r4.closeIconPressed
            if (r3 == 0) goto L_0x005f
            r3 = 16842919(0x10100a7, float:2.3694026E-38)
            r2[r1] = r3
            int r1 = r1 + 1
        L_0x005f:
            boolean r3 = r4.isChecked()
            if (r3 == 0) goto L_0x006a
            r3 = 16842913(0x10100a1, float:2.369401E-38)
            r2[r1] = r3
        L_0x006a:
            boolean r1 = r0.setCloseIconState(r2)
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r4.invalidate()
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.drawableStateChanged():void");
    }

    public boolean ensureAccessibleTouchTarget(int i) {
        this.minTouchTargetSize = i;
        if (!this.ensureMinTouchTargetSize) {
            if (this.insetBackgroundDrawable != null) {
                removeBackgroundInset();
            } else {
                int[] iArr = RippleUtils.PRESSED_STATE_SET;
                updateFrameworkRippleBackground();
            }
            return false;
        }
        int max = Math.max(0, i - ((int) this.chipDrawable.chipMinHeight));
        int max2 = Math.max(0, i - this.chipDrawable.getIntrinsicWidth());
        if (max2 > 0 || max > 0) {
            int i2 = max2 > 0 ? max2 / 2 : 0;
            int i3 = max > 0 ? max / 2 : 0;
            if (this.insetBackgroundDrawable != null) {
                Rect rect = new Rect();
                this.insetBackgroundDrawable.getPadding(rect);
                if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                    int[] iArr2 = RippleUtils.PRESSED_STATE_SET;
                    updateFrameworkRippleBackground();
                    return true;
                }
            }
            if (getMinHeight() != i) {
                setMinHeight(i);
            }
            if (getMinWidth() != i) {
                setMinWidth(i);
            }
            this.insetBackgroundDrawable = new InsetDrawable((Drawable) this.chipDrawable, i2, i3, i2, i3);
            int[] iArr3 = RippleUtils.PRESSED_STATE_SET;
            updateFrameworkRippleBackground();
            return true;
        }
        if (this.insetBackgroundDrawable != null) {
            removeBackgroundInset();
        } else {
            int[] iArr4 = RippleUtils.PRESSED_STATE_SET;
            updateFrameworkRippleBackground();
        }
        return false;
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.insetBackgroundDrawable;
        return insetDrawable == null ? this.chipDrawable : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.checkedIcon;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.checkedIconTint;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipBackgroundColor;
        }
        return null;
    }

    public float getChipCornerRadius() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return Math.max(0.0f, chipDrawable.getChipCornerRadius());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public float getChipEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipEndPadding;
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        Drawable drawable;
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable == null || (drawable = chipDrawable.chipIcon) == null) {
            return null;
        }
        return AppOpsManagerCompat.unwrap(drawable);
    }

    public float getChipIconSize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipIconSize;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipIconTint;
        }
        return null;
    }

    public float getChipMinHeight() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipMinHeight;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipStartPadding;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipStrokeColor;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.chipStrokeWidth;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getCloseIcon();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.closeIconContentDescription;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.closeIconEndPadding;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.closeIconSize;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.closeIconStartPadding;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.closeIconTint;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.truncateAt;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        ChipTouchHelper chipTouchHelper = this.touchHelper;
        if (chipTouchHelper.mKeyboardFocusedVirtualViewId == 1 || chipTouchHelper.mAccessibilityFocusedVirtualViewId == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public MotionSpec getHideMotionSpec() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.hideMotionSpec;
        }
        return null;
    }

    public float getIconEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.iconEndPadding;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.iconStartPadding;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.rippleColor;
        }
        return null;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.chipDrawable.drawableState.shapeAppearanceModel;
    }

    public MotionSpec getShowMotionSpec() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.showMotionSpec;
        }
        return null;
    }

    public float getTextEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.textEndPadding;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.textStartPadding;
        }
        return 0.0f;
    }

    public final boolean hasCloseIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return (chipDrawable == null || chipDrawable.getCloseIcon() == null) ? false : true;
    }

    public boolean isCheckable() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null && chipDrawable.checkable;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        R$style.setParentAbsoluteElevation(this, this.chipDrawable);
    }

    @Override // com.google.android.material.chip.ChipDrawable.Delegate
    public void onChipDrawableSizeChange() {
        ensureAccessibleTouchTarget(this.minTouchTargetSize);
        requestLayout();
        invalidateOutline();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, SELECTED_STATE);
        }
        if (isCheckable()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        ChipTouchHelper chipTouchHelper = this.touchHelper;
        int i2 = chipTouchHelper.mKeyboardFocusedVirtualViewId;
        if (i2 != Integer.MIN_VALUE) {
            chipTouchHelper.clearKeyboardFocusForVirtualView(i2);
        }
        if (z) {
            chipTouchHelper.moveFocus(i, rect);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isCheckable() || isClickable()) {
            accessibilityNodeInfo.setClassName(isCheckable() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            accessibilityNodeInfo.setClassName("android.view.View");
        }
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.singleLine) {
                int i2 = 0;
                i = 0;
                while (true) {
                    if (i2 >= chipGroup.getChildCount()) {
                        i = -1;
                        break;
                    }
                    if (chipGroup.getChildAt(i2) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i2)) == this) {
                            break;
                        }
                        i++;
                    }
                    i2++;
                }
            } else {
                i = -1;
            }
            Object tag = getTag(R.id.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(!(tag instanceof Integer) ? -1 : ((Integer) tag).intValue(), 1, i, 1, false, isChecked()).mInfo);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (!getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), RNCWebViewManager.COMMAND_CLEAR_HISTORY);
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.lastLayoutDirection != i) {
            this.lastLayoutDirection = i;
            updatePaddingInternal();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r0 != 3) goto L_0x004c;
     */
    @Override // android.widget.TextView, android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0045
            if (r0 == r3) goto L_0x002b
            r4 = 2
            if (r0 == r4) goto L_0x0021
            r1 = 3
            if (r0 == r1) goto L_0x0040
            goto L_0x004c
        L_0x0021:
            boolean r0 = r5.closeIconPressed
            if (r0 == 0) goto L_0x004c
            if (r1 != 0) goto L_0x004a
            r5.setCloseIconPressed(r2)
            goto L_0x004a
        L_0x002b:
            boolean r0 = r5.closeIconPressed
            if (r0 == 0) goto L_0x0040
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.onCloseIconClickListener
            if (r0 == 0) goto L_0x0039
            r0.onClick(r5)
        L_0x0039:
            com.google.android.material.chip.Chip$ChipTouchHelper r0 = r5.touchHelper
            r0.sendEventForVirtualView(r3, r3)
            r0 = 1
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            r5.setCloseIconPressed(r2)
            goto L_0x004d
        L_0x0045:
            if (r1 == 0) goto L_0x004c
            r5.setCloseIconPressed(r3)
        L_0x004a:
            r0 = 1
            goto L_0x004d
        L_0x004c:
            r0 = 0
        L_0x004d:
            if (r0 != 0) goto L_0x0055
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L_0x0056
        L_0x0055:
            r2 = 1
        L_0x0056:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void removeBackgroundInset() {
        if (this.insetBackgroundDrawable != null) {
            this.insetBackgroundDrawable = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            int[] iArr = RippleUtils.PRESSED_STATE_SET;
            updateFrameworkRippleBackground();
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.ripple) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.ripple) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckable(z);
        }
    }

    public void setCheckableResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckable(chipDrawable.context.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable == null) {
            this.deferredCheckedValue = z;
        } else if (chipDrawable.checkable) {
            boolean isChecked = isChecked();
            super.setChecked(z);
            if (isChecked != z && (onCheckedChangeListener = this.onCheckedChangeListenerInternal) != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIcon(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIcon(AppCompatResources.getDrawable(chipDrawable.context, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconTint(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconTint(AppCompatResources.getColorStateList(chipDrawable.context, i));
        }
    }

    public void setCheckedIconVisible(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconVisible(chipDrawable.context.getResources().getBoolean(i));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.chipBackgroundColor != colorStateList) {
            chipDrawable.chipBackgroundColor = colorStateList;
            chipDrawable.onStateChange(chipDrawable.getState());
        }
    }

    public void setChipBackgroundColorResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipBackgroundColor(AppCompatResources.getColorStateList(chipDrawable.context, i));
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipCornerRadius(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipCornerRadius(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(ChipDrawable chipDrawable) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != chipDrawable) {
            if (chipDrawable2 != null) {
                chipDrawable2.delegate = new WeakReference<>(null);
            }
            this.chipDrawable = chipDrawable;
            chipDrawable.shouldDrawText = false;
            Objects.requireNonNull(chipDrawable);
            chipDrawable.delegate = new WeakReference<>(this);
            ensureAccessibleTouchTarget(this.minTouchTargetSize);
        }
    }

    public void setChipEndPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.chipEndPadding != f) {
            chipDrawable.chipEndPadding = f;
            chipDrawable.invalidateSelf();
            chipDrawable.onSizeChange();
        }
    }

    public void setChipEndPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipEndPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIcon(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIcon(AppCompatResources.getDrawable(chipDrawable.context, i));
        }
    }

    public void setChipIconSize(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconSize(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconSize(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconTint(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconTint(AppCompatResources.getColorStateList(chipDrawable.context, i));
        }
    }

    public void setChipIconVisible(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconVisible(chipDrawable.context.getResources().getBoolean(i));
        }
    }

    public void setChipMinHeight(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.chipMinHeight != f) {
            chipDrawable.chipMinHeight = f;
            chipDrawable.invalidateSelf();
            chipDrawable.onSizeChange();
        }
    }

    public void setChipMinHeightResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipMinHeight(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipStartPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.chipStartPadding != f) {
            chipDrawable.chipStartPadding = f;
            chipDrawable.invalidateSelf();
            chipDrawable.onSizeChange();
        }
    }

    public void setChipStartPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStartPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeColor(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeColor(AppCompatResources.getColorStateList(chipDrawable.context, i));
        }
    }

    public void setChipStrokeWidth(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeWidth(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeWidth(chipDrawable.context.getResources().getDimension(i));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIcon(drawable);
        }
        updateAccessibilityDelegate();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.closeIconContentDescription != charSequence) {
            BidiFormatter instance = BidiFormatter.getInstance();
            chipDrawable.closeIconContentDescription = instance.unicodeWrap(charSequence, instance.mDefaultTextDirectionHeuristicCompat, true);
            chipDrawable.invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconEndPadding(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconEndPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIcon(AppCompatResources.getDrawable(chipDrawable.context, i));
        }
        updateAccessibilityDelegate();
    }

    public void setCloseIconSize(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconSize(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconSize(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconStartPadding(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconStartPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconTint(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconTint(AppCompatResources.getColorStateList(chipDrawable.context, i));
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = chipDrawable.drawableState;
            if (materialShapeDrawableState.elevation != f) {
                materialShapeDrawableState.elevation = f;
                chipDrawable.updateZ();
            }
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.chipDrawable != null) {
            if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
                super.setEllipsize(truncateAt);
                ChipDrawable chipDrawable = this.chipDrawable;
                if (chipDrawable != null) {
                    chipDrawable.truncateAt = truncateAt;
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.ensureMinTouchTargetSize = z;
        ensureAccessibleTouchTarget(this.minTouchTargetSize);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.hideMotionSpec = motionSpec;
        }
    }

    public void setHideMotionSpecResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.hideMotionSpec = MotionSpec.createFromResource(chipDrawable.context, i);
        }
    }

    public void setIconEndPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconEndPadding(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconEndPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconStartPadding(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconStartPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.chipDrawable != null) {
            super.setLayoutDirection(i);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i <= 1) {
            super.setLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i <= 1) {
            super.setMaxLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.maxWidth = i;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i <= 1) {
            super.setMinLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListenerInternal = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.onCloseIconClickListener = onClickListener;
        updateAccessibilityDelegate();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setRippleColor(colorStateList);
        }
        if (!this.chipDrawable.useCompatRipple) {
            updateFrameworkRippleBackground();
        }
    }

    public void setRippleColorResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setRippleColor(AppCompatResources.getColorStateList(chipDrawable.context, i));
            if (!this.chipDrawable.useCompatRipple) {
                updateFrameworkRippleBackground();
            }
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        ChipDrawable chipDrawable = this.chipDrawable;
        chipDrawable.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        chipDrawable.invalidateSelf();
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.showMotionSpec = motionSpec;
        }
    }

    public void setShowMotionSpecResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.showMotionSpec = MotionSpec.createFromResource(chipDrawable.context, i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            if (charSequence == null) {
                charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
            }
            super.setText(chipDrawable.shouldDrawText ? null : charSequence, bufferType);
            ChipDrawable chipDrawable2 = this.chipDrawable;
            if (chipDrawable2 != null) {
                chipDrawable2.setText(charSequence);
            }
        }
    }

    public void setTextAppearance(TextAppearance textAppearance) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.textDrawableHelper.setTextAppearance(textAppearance, chipDrawable.context);
        }
        updateTextPaintDrawState();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.textEndPadding != f) {
            chipDrawable.textEndPadding = f;
            chipDrawable.invalidateSelf();
            chipDrawable.onSizeChange();
        }
    }

    public void setTextEndPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextEndPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public void setTextStartPadding(float f) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null && chipDrawable.textStartPadding != f) {
            chipDrawable.textStartPadding = f;
            chipDrawable.invalidateSelf();
            chipDrawable.onSizeChange();
        }
    }

    public void setTextStartPaddingResource(int i) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextStartPadding(chipDrawable.context.getResources().getDimension(i));
        }
    }

    public final void updateAccessibilityDelegate() {
        if (hasCloseIcon()) {
            ChipDrawable chipDrawable = this.chipDrawable;
            if ((chipDrawable != null && chipDrawable.closeIconVisible) && this.onCloseIconClickListener != null) {
                ViewCompat.setAccessibilityDelegate(this, this.touchHelper);
                return;
            }
        }
        ViewCompat.setAccessibilityDelegate(this, null);
    }

    public final void updateFrameworkRippleBackground() {
        this.ripple = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.chipDrawable.rippleColor), getBackgroundDrawable(), null);
        this.chipDrawable.setUseCompatRipple(false);
        RippleDrawable rippleDrawable = this.ripple;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setBackground(rippleDrawable);
        updatePaddingInternal();
    }

    public final void updatePaddingInternal() {
        ChipDrawable chipDrawable;
        if (!TextUtils.isEmpty(getText()) && (chipDrawable = this.chipDrawable) != null) {
            int calculateCloseIconWidth = (int) (chipDrawable.calculateCloseIconWidth() + chipDrawable.chipEndPadding + chipDrawable.textEndPadding);
            ChipDrawable chipDrawable2 = this.chipDrawable;
            int calculateChipIconWidth = (int) (chipDrawable2.calculateChipIconWidth() + chipDrawable2.chipStartPadding + chipDrawable2.textStartPadding);
            if (this.insetBackgroundDrawable != null) {
                Rect rect = new Rect();
                this.insetBackgroundDrawable.getPadding(rect);
                calculateChipIconWidth += rect.left;
                calculateCloseIconWidth += rect.right;
            }
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            setPaddingRelative(calculateChipIconWidth, paddingTop, calculateCloseIconWidth, paddingBottom);
        }
    }

    public final void updateTextPaintDrawState() {
        TextPaint paint = getPaint();
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            paint.drawableState = chipDrawable.getState();
        }
        TextAppearance textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.updateDrawState(getContext(), paint, this.fontCallback);
        }
    }

    public void setCloseIconVisible(boolean z) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconVisible(z);
        }
        updateAccessibilityDelegate();
    }

    public void setCheckedIconVisible(boolean z) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconVisible(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconVisible(z);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.textDrawableHelper.setTextAppearance(new TextAppearance(chipDrawable.context, i), chipDrawable.context);
        }
        updateTextPaintDrawState();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.textDrawableHelper.setTextAppearance(new TextAppearance(chipDrawable.context, i), chipDrawable.context);
        }
        updateTextPaintDrawState();
    }
}
