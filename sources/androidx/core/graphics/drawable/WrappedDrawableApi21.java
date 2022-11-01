package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    public static Method sIsProjectedDrawableMethod;

    public WrappedDrawableApi21(Drawable drawable) {
        super(drawable);
        findAndCacheIsProjectedDrawableMethod();
    }

    public final void findAndCacheIsProjectedDrawableMethod() {
        if (sIsProjectedDrawableMethod == null) {
            try {
                sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14
    public boolean isCompatTintEnabled() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.mDrawable;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        Drawable drawable = this.mDrawable;
        if (!(drawable == null || (method = sIsProjectedDrawableMethod) == null)) {
            try {
                return ((Boolean) method.invoke(drawable, new Object[0])).booleanValue();
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        this.mDrawable.setHotspot(f, f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.mDrawable.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public void setTint(int i) {
        if (isCompatTintEnabled()) {
            super.setTint(i);
        } else {
            this.mDrawable.setTint(i);
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (isCompatTintEnabled()) {
            this.mState.mTint = colorStateList;
            updateTint(getState());
            return;
        }
        this.mDrawable.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (isCompatTintEnabled()) {
            this.mState.mTintMode = mode;
            updateTint(getState());
            return;
        }
        this.mDrawable.setTintMode(mode);
    }

    public WrappedDrawableApi21(WrappedDrawableState wrappedDrawableState, Resources resources) {
        super(wrappedDrawableState, resources);
        findAndCacheIsProjectedDrawableMethod();
    }
}
