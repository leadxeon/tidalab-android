package androidx.transition;

import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ViewUtils {
    public static final ViewUtilsBase IMPL;
    public static final Property<View, Float> TRANSITION_ALPHA;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            IMPL = new ViewUtilsApi29();
        } else if (i >= 23) {
            IMPL = new ViewUtilsApi23();
        } else if (i >= 22) {
            IMPL = new ViewUtilsApi22();
        } else {
            IMPL = new ViewUtilsApi21();
        }
        TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") { // from class: androidx.transition.ViewUtils.1
            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(ViewUtils.getTransitionAlpha(view));
            }

            @Override // android.util.Property
            public void set(View view, Float f) {
                float floatValue = f.floatValue();
                ViewUtils.IMPL.setTransitionAlpha(view, floatValue);
            }
        };
        new Property<View, Rect>(Rect.class, "clipBounds") { // from class: androidx.transition.ViewUtils.2
            @Override // android.util.Property
            public Rect get(View view) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                return view.getClipBounds();
            }

            @Override // android.util.Property
            public void set(View view, Rect rect) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view.setClipBounds(rect);
            }
        };
    }

    public static float getTransitionAlpha(View view) {
        return IMPL.getTransitionAlpha(view);
    }

    public static void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        IMPL.setLeftTopRightBottom(view, i, i2, i3, i4);
    }
}
