package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class ReactFontManager {
    public static final String[] EXTENSIONS = {HttpUrl.FRAGMENT_ENCODE_SET, "_bold", "_italic", "_bold_italic"};
    public static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    public static ReactFontManager sReactFontManagerInstance;
    public final Map<String, FontFamily> mFontCache = new HashMap();
    public final Map<String, Typeface> mCustomTypefaceCache = new HashMap();

    public Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        int i3 = 0;
        boolean z = false;
        if (this.mCustomTypefaceCache.containsKey(str)) {
            Typeface typeface = this.mCustomTypefaceCache.get(str);
            if (Build.VERSION.SDK_INT < 28 || i2 < 100 || i2 > 1000) {
                return Typeface.create(typeface, i);
            }
            if ((i & 2) != 0) {
                z = true;
            }
            return Typeface.create(typeface, i2, z);
        }
        FontFamily fontFamily = this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily(null);
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface2 = fontFamily.mTypefaceSparseArray.get(i);
        if (typeface2 == null) {
            String str2 = EXTENSIONS[i];
            String[] strArr = FILE_EXTENSIONS;
            int length = strArr.length;
            while (true) {
                if (i3 >= length) {
                    typeface2 = Typeface.create(str, i);
                    break;
                }
                String str3 = strArr[i3];
                try {
                    typeface2 = Typeface.createFromAsset(assetManager, "fonts/" + str + str2 + str3);
                    break;
                } catch (RuntimeException unused) {
                    i3++;
                }
            }
            if (typeface2 != null) {
                fontFamily.mTypefaceSparseArray.put(i, typeface2);
            }
        }
        return typeface2;
    }

    /* loaded from: classes.dex */
    public static class FontFamily {
        public SparseArray<Typeface> mTypefaceSparseArray = new SparseArray<>(4);

        public FontFamily() {
        }

        public FontFamily(AnonymousClass1 r2) {
        }
    }
}
