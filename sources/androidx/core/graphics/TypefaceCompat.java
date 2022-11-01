package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat$FontRequestCallback;
import java.lang.reflect.Method;
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class TypefaceCompat {
    public static final LruCache<String, Typeface> sTypefaceCache;
    public static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    /* loaded from: classes.dex */
    public static class ResourcesCallbackAdapter extends FontsContractCompat$FontRequestCallback {
        public ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback fontCallback) {
            this.mFontCallback = fontCallback;
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        } else if (i >= 28) {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        } else if (i >= 26) {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        } else {
            if (i >= 24) {
                Method method = TypefaceCompatApi24Impl.sAddFontWeightStyle;
                if (method == null) {
                    Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
                }
                if (method != null) {
                    sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
                }
            }
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        }
        sTypefaceCache = new LruCache<>(16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r0.equals(r4) == false) goto L_0x0028;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface createFromResourcesFamilyXml(final android.content.Context r6, androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry r7, android.content.res.Resources r8, int r9, final int r10, androidx.core.content.res.ResourcesCompat.FontCallback r11, android.os.Handler r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(android.content.Context, androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry, android.content.res.Resources, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, str, i2);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    public static String createResourceUid(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
