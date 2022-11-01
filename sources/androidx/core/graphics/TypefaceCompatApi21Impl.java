package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.graphics.TypefaceCompatBaseImpl;
import androidx.core.provider.FontsContractCompat$FontInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    public static Method sAddFontWeightStyle = null;
    public static Method sCreateFromFamiliesWithDefault = null;
    public static Class<?> sFontFamily = null;
    public static Constructor<?> sFontFamilyCtor = null;
    public static boolean sHasInitBeenCalled = false;

    public static boolean addFontWeightStyle(Object obj, String str, int i, boolean z) {
        init();
        try {
            return ((Boolean) sAddFontWeightStyle.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {
        Method method;
        Class<?> cls;
        Method method2;
        if (!sHasInitBeenCalled) {
            sHasInitBeenCalled = true;
            Constructor<?> constructor = null;
            try {
                cls = Class.forName("android.graphics.FontFamily");
                Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
                method = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
                method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
                constructor = constructor2;
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
                method2 = null;
                cls = null;
                method = null;
            }
            sFontFamilyCtor = constructor;
            sFontFamily = cls;
            sAddFontWeightStyle = method;
            sCreateFromFamiliesWithDefault = method2;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat$FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat$FontFileResourceEntry[] fontResourcesParserCompat$FontFileResourceEntryArr;
        init();
        try {
            Object newInstance = sFontFamilyCtor.newInstance(new Object[0]);
            for (FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry : fontResourcesParserCompat$FontFamilyFilesResourceEntry.mEntries) {
                File tempFile = AppOpsManagerCompat.getTempFile(context);
                if (tempFile == null) {
                    return null;
                }
                try {
                    if (!AppOpsManagerCompat.copyToFile(tempFile, resources, fontResourcesParserCompat$FontFileResourceEntry.mResourceId)) {
                        return null;
                    }
                    if (!addFontWeightStyle(newInstance, tempFile.getPath(), fontResourcesParserCompat$FontFileResourceEntry.mWeight, fontResourcesParserCompat$FontFileResourceEntry.mItalic)) {
                        return null;
                    }
                    tempFile.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    tempFile.delete();
                }
            }
            init();
            try {
                Object newInstance2 = Array.newInstance(sFontFamily, 1);
                Array.set(newInstance2, 0, newInstance);
                return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, newInstance2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        File file;
        String readlink;
        if (fontsContractCompat$FontInfoArr.length < 1) {
            return null;
        }
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(((FontsContractCompat$FontInfo) TypefaceCompatBaseImpl.findBestFont(fontsContractCompat$FontInfoArr, i, new TypefaceCompatBaseImpl.AnonymousClass1(this))).mUri, "r", cancellationSignal);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                readlink = Os.readlink("/proc/self/fd/" + openFileDescriptor.getFd());
            } catch (ErrnoException unused) {
            }
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                file = new File(readlink);
                if (file != null && file.canRead()) {
                    Typeface createFromFile = Typeface.createFromFile(file);
                    openFileDescriptor.close();
                    return createFromFile;
                }
                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                Typeface createFromInputStream = createFromInputStream(context, fileInputStream);
                fileInputStream.close();
                openFileDescriptor.close();
                return createFromInputStream;
            }
            file = null;
            if (file != null) {
                Typeface createFromFile2 = Typeface.createFromFile(file);
                openFileDescriptor.close();
                return createFromFile2;
            }
            FileInputStream fileInputStream2 = new FileInputStream(openFileDescriptor.getFileDescriptor());
            Typeface createFromInputStream2 = createFromInputStream(context, fileInputStream2);
            fileInputStream2.close();
            openFileDescriptor.close();
            return createFromInputStream2;
        } catch (IOException unused2) {
            return null;
        }
    }
}
