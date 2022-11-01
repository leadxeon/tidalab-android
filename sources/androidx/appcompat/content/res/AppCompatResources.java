package androidx.appcompat.content.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.appcompat.widget.ResourceManagerInternal;
import java.util.WeakHashMap;
@SuppressLint({"RestrictedAPI"})
/* loaded from: classes.dex */
public final class AppCompatResources {
    public static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    public static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    public static final Object sColorStateCacheLock = new Object();

    /* loaded from: classes.dex */
    public static class ColorStateListCacheEntry {
        public final Configuration configuration;
        public final ColorStateList value;

        public ColorStateListCacheEntry(ColorStateList colorStateList, Configuration configuration) {
            this.value = colorStateList;
            this.configuration = configuration;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList getColorStateList(android.content.Context r12, int r13) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.content.res.AppCompatResources.getColorStateList(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static Drawable getDrawable(Context context, int i) {
        return ResourceManagerInternal.get().getDrawable(context, i);
    }
}
