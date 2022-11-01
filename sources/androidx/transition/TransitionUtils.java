package androidx.transition;

import android.os.Build;
/* loaded from: classes.dex */
public class TransitionUtils {
    public static final boolean HAS_IS_ATTACHED_TO_WINDOW = true;
    public static final boolean HAS_OVERLAY = true;
    public static final boolean HAS_PICTURE_BITMAP;

    static {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 28) {
            z = false;
        }
        HAS_PICTURE_BITMAP = z;
    }
}
