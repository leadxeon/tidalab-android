package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
/* loaded from: classes.dex */
public class TintTypedArray {
    public final Context mContext;
    public TypedValue mTypedValue;
    public final TypedArray mWrapped;

    public TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public boolean getBoolean(int i, boolean z) {
        return this.mWrapped.getBoolean(i, z);
    }

    public int getColor(int i, int i2) {
        return this.mWrapped.getColor(i, i2);
    }

    public ColorStateList getColorStateList(int i) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.mContext, resourceId)) == null) ? this.mWrapped.getColorStateList(i) : colorStateList;
    }

    public float getDimension(int i, float f) {
        return this.mWrapped.getDimension(i, f);
    }

    public int getDimensionPixelOffset(int i, int i2) {
        return this.mWrapped.getDimensionPixelOffset(i, i2);
    }

    public int getDimensionPixelSize(int i, int i2) {
        return this.mWrapped.getDimensionPixelSize(i, i2);
    }

    public Drawable getDrawable(int i) {
        int resourceId;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) {
            return this.mWrapped.getDrawable(i);
        }
        return AppCompatResources.getDrawable(this.mContext, resourceId);
    }

    public Drawable getDrawableIfKnown(int i) {
        int resourceId;
        Drawable drawable;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) {
            return null;
        }
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        Context context = this.mContext;
        synchronized (appCompatDrawableManager) {
            drawable = appCompatDrawableManager.mResourceManager.getDrawable(context, resourceId, true);
        }
        return drawable;
    }

    public Typeface getFont(int i, int i2, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        Context context = this.mContext;
        TypedValue typedValue = this.mTypedValue;
        ThreadLocal<TypedValue> threadLocal = ResourcesCompat.sTempTypedValue;
        if (context.isRestricted()) {
            return null;
        }
        return ResourcesCompat.loadFont(context, resourceId, typedValue, i2, fontCallback, null, true, false);
    }

    public int getInt(int i, int i2) {
        return this.mWrapped.getInt(i, i2);
    }

    public int getLayoutDimension(int i, int i2) {
        return this.mWrapped.getLayoutDimension(i, i2);
    }

    public int getResourceId(int i, int i2) {
        return this.mWrapped.getResourceId(i, i2);
    }

    public String getString(int i) {
        return this.mWrapped.getString(i);
    }

    public CharSequence getText(int i) {
        return this.mWrapped.getText(i);
    }

    public boolean hasValue(int i) {
        return this.mWrapped.hasValue(i);
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }
}
