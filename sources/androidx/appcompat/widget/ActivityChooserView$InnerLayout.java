package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
/* loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {
    public static final int[] TINT_ATTRS = {16842964};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable;
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, TINT_ATTRS);
        if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) {
            drawable = obtainStyledAttributes.getDrawable(0);
        } else {
            drawable = AppCompatResources.getDrawable(context, resourceId);
        }
        setBackgroundDrawable(drawable);
        obtainStyledAttributes.recycle();
    }
}
