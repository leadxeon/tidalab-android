package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class TypefaceCompatBaseImpl {
    @SuppressLint({"BanConcurrentHashMap"})
    public ConcurrentHashMap<Long, FontResourcesParserCompat$FontFamilyFilesResourceEntry> mFontFamilies = new ConcurrentHashMap<>();

    /* renamed from: androidx.core.graphics.TypefaceCompatBaseImpl$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements StyleExtractor<FontsContractCompat$FontInfo> {
        public AnonymousClass1(TypefaceCompatBaseImpl typefaceCompatBaseImpl) {
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public int getWeight(FontsContractCompat$FontInfo fontsContractCompat$FontInfo) {
            return fontsContractCompat$FontInfo.mWeight;
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public boolean isItalic(FontsContractCompat$FontInfo fontsContractCompat$FontInfo) {
            return fontsContractCompat$FontInfo.mItalic;
        }
    }

    /* loaded from: classes.dex */
    public interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    public static <T> T findBestFont(T[] tArr, int i, StyleExtractor<T> styleExtractor) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = (Math.abs(styleExtractor.getWeight(t2) - i2) * 2) + (styleExtractor.isItalic(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat$FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry, Resources resources, int i) {
        throw null;
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        throw null;
    }

    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = AppOpsManagerCompat.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!AppOpsManagerCompat.copyToFile(tempFile, inputStream)) {
                return null;
            }
            return Typeface.createFromFile(tempFile.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        File tempFile = AppOpsManagerCompat.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!AppOpsManagerCompat.copyToFile(tempFile, resources, i)) {
                return null;
            }
            return Typeface.createFromFile(tempFile.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }
}
