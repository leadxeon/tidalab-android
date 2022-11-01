package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public class ActionBarBackgroundDrawable extends Drawable {
    public final ActionBarContainer mContainer;

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.mContainer = actionBarContainer;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mSplitBackground;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.mBackground;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.mContainer;
        Drawable drawable3 = actionBarContainer2.mStackedBackground;
        if (drawable3 != null && actionBarContainer2.mIsStacked) {
            drawable3.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mSplitBackground;
            if (drawable != null) {
                drawable.getOutline(outline);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.mBackground;
        if (drawable2 != null) {
            drawable2.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
