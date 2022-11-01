package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class ViewUtilsBase {
    public static boolean sSetFrameFetched;
    public static Method sSetFrameMethod;
    public static Field sViewFlagsField;
    public static boolean sViewFlagsFieldFetched;

    public void clearNonTransitionAlpha(View view) {
        throw null;
    }

    public float getTransitionAlpha(View view) {
        throw null;
    }

    public void saveNonTransitionAlpha(View view) {
        throw null;
    }

    public void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        if (!sSetFrameFetched) {
            try {
                Class cls = Integer.TYPE;
                Method declaredMethod = View.class.getDeclaredMethod("setFrame", cls, cls, cls, cls);
                sSetFrameMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsBase", "Failed to retrieve setFrame method", e);
            }
            sSetFrameFetched = true;
        }
        Method method = sSetFrameMethod;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void setTransitionAlpha(View view, float f) {
        throw null;
    }

    public void setTransitionVisibility(View view, int i) {
        if (!sViewFlagsFieldFetched) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                sViewFlagsField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            sViewFlagsFieldFetched = true;
        }
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void transformMatrixToGlobal(View view, Matrix matrix) {
        throw null;
    }

    public void transformMatrixToLocal(View view, Matrix matrix) {
        throw null;
    }
}
