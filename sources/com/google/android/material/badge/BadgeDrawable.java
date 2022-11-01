package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    public WeakReference<View> anchorViewRef;
    public float badgeCenterX;
    public float badgeCenterY;
    public final float badgeRadius;
    public final float badgeWidePadding;
    public final float badgeWithTextRadius;
    public final WeakReference<Context> contextRef;
    public float cornerRadius;
    public WeakReference<FrameLayout> customBadgeParentRef;
    public float halfBadgeHeight;
    public float halfBadgeWidth;
    public int maxBadgeNumber;
    public final SavedState savedState;
    public final TextDrawableHelper textDrawableHelper;
    public final Rect badgeBounds = new Rect();
    public final MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();

    public BadgeDrawable(Context context) {
        TextAppearance textAppearance;
        Context context2;
        WeakReference<Context> weakReference = new WeakReference<>(context);
        this.contextRef = weakReference;
        ThemeEnforcement.checkTheme(context, ThemeEnforcement.MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
        Resources resources = context.getResources();
        this.badgeRadius = resources.getDimensionPixelSize(R.dimen.mtrl_badge_radius);
        this.badgeWidePadding = resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding);
        this.badgeWithTextRadius = resources.getDimensionPixelSize(R.dimen.mtrl_badge_with_text_radius);
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        textDrawableHelper.textPaint.setTextAlign(Paint.Align.CENTER);
        this.savedState = new SavedState(context);
        Context context3 = weakReference.get();
        if (context3 != null && textDrawableHelper.textAppearance != (textAppearance = new TextAppearance(context3, 2131886473)) && (context2 = weakReference.get()) != null) {
            textDrawableHelper.setTextAppearance(textAppearance, context2);
            updateCenterAndBounds();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && this.savedState.alpha != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasNumber()) {
                Rect rect = new Rect();
                String badgeText = getBadgeText();
                this.textDrawableHelper.textPaint.getTextBounds(badgeText, 0, badgeText.length(), rect);
                canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + (rect.height() / 2), this.textDrawableHelper.textPaint);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.savedState.alpha;
    }

    public final String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return NumberFormat.getInstance().format(getNumber());
        }
        Context context = this.contextRef.get();
        return context == null ? HttpUrl.FRAGMENT_ENCODE_SET : context.getString(R.string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.maxBadgeNumber), "+");
    }

    public FrameLayout getCustomBadgeParent() {
        WeakReference<FrameLayout> weakReference = this.customBadgeParentRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public int getNumber() {
        if (!hasNumber()) {
            return 0;
        }
        return this.savedState.number;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean hasNumber() {
        return this.savedState.number != -1;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.savedState.alpha = i;
        this.textDrawableHelper.textPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void updateBadgeCoordinates(View view, FrameLayout frameLayout) {
        this.anchorViewRef = new WeakReference<>(view);
        this.customBadgeParentRef = new WeakReference<>(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public final void updateCenterAndBounds() {
        float f;
        float f2;
        Context context = this.contextRef.get();
        WeakReference<View> weakReference = this.anchorViewRef;
        FrameLayout frameLayout = null;
        View view = weakReference != null ? weakReference.get() : null;
        if (context != null && view != null) {
            Rect rect = new Rect();
            rect.set(this.badgeBounds);
            Rect rect2 = new Rect();
            view.getDrawingRect(rect2);
            WeakReference<FrameLayout> weakReference2 = this.customBadgeParentRef;
            if (weakReference2 != null) {
                frameLayout = weakReference2.get();
            }
            if (frameLayout != null) {
                frameLayout.offsetDescendantRectToMyCoords(view, rect2);
            }
            SavedState savedState = this.savedState;
            int i = savedState.badgeGravity;
            if (i == 8388691 || i == 8388693) {
                this.badgeCenterY = rect2.bottom - savedState.verticalOffset;
            } else {
                this.badgeCenterY = rect2.top + savedState.verticalOffset;
            }
            if (getNumber() <= 9) {
                float f3 = !hasNumber() ? this.badgeRadius : this.badgeWithTextRadius;
                this.cornerRadius = f3;
                this.halfBadgeHeight = f3;
                this.halfBadgeWidth = f3;
            } else {
                float f4 = this.badgeWithTextRadius;
                this.cornerRadius = f4;
                this.halfBadgeHeight = f4;
                this.halfBadgeWidth = (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.badgeWidePadding;
            }
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(hasNumber() ? R.dimen.mtrl_badge_text_horizontal_edge_offset : R.dimen.mtrl_badge_horizontal_edge_offset);
            int i2 = this.savedState.badgeGravity;
            if (i2 == 8388659 || i2 == 8388691) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (view.getLayoutDirection() == 0) {
                    f = (rect2.left - this.halfBadgeWidth) + dimensionPixelSize + this.savedState.horizontalOffset;
                } else {
                    f = ((rect2.right + this.halfBadgeWidth) - dimensionPixelSize) - this.savedState.horizontalOffset;
                }
                this.badgeCenterX = f;
            } else {
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                if (view.getLayoutDirection() == 0) {
                    f2 = ((rect2.right + this.halfBadgeWidth) - dimensionPixelSize) - this.savedState.horizontalOffset;
                } else {
                    f2 = (rect2.left - this.halfBadgeWidth) + dimensionPixelSize + this.savedState.horizontalOffset;
                }
                this.badgeCenterX = f2;
            }
            Rect rect3 = this.badgeBounds;
            float f5 = this.badgeCenterX;
            float f6 = this.badgeCenterY;
            float f7 = this.halfBadgeWidth;
            float f8 = this.halfBadgeHeight;
            rect3.set((int) (f5 - f7), (int) (f6 - f8), (int) (f5 + f7), (int) (f6 + f8));
            MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
            materialShapeDrawable.drawableState.shapeAppearanceModel = materialShapeDrawable.drawableState.shapeAppearanceModel.withCornerSize(this.cornerRadius);
            materialShapeDrawable.invalidateSelf();
            if (!rect.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.badge.BadgeDrawable.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int alpha;
        public int backgroundColor;
        public int badgeGravity;
        public int badgeTextColor;
        public int contentDescriptionExceedsMaxBadgeNumberRes;
        public CharSequence contentDescriptionNumberless;
        public int contentDescriptionQuantityStrings;
        public int horizontalOffset;
        public boolean isVisible;
        public int maxCharacterCount;
        public int number;
        public int verticalOffset;

        public SavedState(Context context) {
            this.alpha = 255;
            this.number = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(2131886473, R$styleable.TextAppearance);
            obtainStyledAttributes.getDimension(0, 0.0f);
            ColorStateList colorStateList = R$style.getColorStateList(context, obtainStyledAttributes, 3);
            R$style.getColorStateList(context, obtainStyledAttributes, 4);
            R$style.getColorStateList(context, obtainStyledAttributes, 5);
            obtainStyledAttributes.getInt(2, 0);
            obtainStyledAttributes.getInt(1, 1);
            int i = !obtainStyledAttributes.hasValue(12) ? 10 : 12;
            obtainStyledAttributes.getResourceId(i, 0);
            obtainStyledAttributes.getString(i);
            obtainStyledAttributes.getBoolean(14, false);
            R$style.getColorStateList(context, obtainStyledAttributes, 6);
            obtainStyledAttributes.getFloat(7, 0.0f);
            obtainStyledAttributes.getFloat(8, 0.0f);
            obtainStyledAttributes.getFloat(9, 0.0f);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(2131886473, R$styleable.MaterialTextAppearance);
            obtainStyledAttributes2.hasValue(0);
            obtainStyledAttributes2.getFloat(0, 0.0f);
            obtainStyledAttributes2.recycle();
            this.badgeTextColor = colorStateList.getDefaultColor();
            this.contentDescriptionNumberless = context.getString(R.string.mtrl_badge_numberless_content_description);
            this.contentDescriptionQuantityStrings = R.plurals.mtrl_badge_content_description;
            this.contentDescriptionExceedsMaxBadgeNumberRes = R.string.mtrl_exceed_max_badge_number_content_description;
            this.isVisible = true;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.backgroundColor);
            parcel.writeInt(this.badgeTextColor);
            parcel.writeInt(this.alpha);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeString(this.contentDescriptionNumberless.toString());
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeInt(this.badgeGravity);
            parcel.writeInt(this.horizontalOffset);
            parcel.writeInt(this.verticalOffset);
            parcel.writeInt(this.isVisible ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.alpha = 255;
            this.number = -1;
            this.backgroundColor = parcel.readInt();
            this.badgeTextColor = parcel.readInt();
            this.alpha = parcel.readInt();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = parcel.readInt();
            this.horizontalOffset = parcel.readInt();
            this.verticalOffset = parcel.readInt();
            this.isVisible = parcel.readInt() != 0;
        }
    }
}
