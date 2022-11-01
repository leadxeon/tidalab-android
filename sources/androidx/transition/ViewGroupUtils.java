package androidx.transition;

import android.os.Build;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public class ViewGroupUtils {
    public static boolean sTryHiddenSuppressLayout = true;

    public static void suppressLayout(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
        } else if (sTryHiddenSuppressLayout) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSuppressLayout = false;
            }
        }
    }
}
