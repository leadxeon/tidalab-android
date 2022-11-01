package androidx.core.widget;

import android.os.Build;
/* loaded from: classes.dex */
public interface AutoSizeableTextView {
    public static final boolean PLATFORM_SUPPORTS_AUTOSIZE;

    static {
        PLATFORM_SUPPORTS_AUTOSIZE = Build.VERSION.SDK_INT >= 27;
    }
}
