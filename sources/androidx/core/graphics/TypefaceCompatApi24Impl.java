package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
/* loaded from: classes.dex */
public class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
    public static final Method sAddFontWeightStyle;
    public static final Method sCreateFromFamiliesWithDefault;
    public static final Class<?> sFontFamily;
    public static final Constructor<?> sFontFamilyCtor;

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            Class<?> cls2 = Integer.TYPE;
            method = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            method2 = null;
            method = null;
        }
        sFontFamilyCtor = constructor;
        sFontFamily = cls;
        sAddFontWeightStyle = method;
        sCreateFromFamiliesWithDefault = method2;
    }

    public static boolean addFontWeightStyle(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) sAddFontWeightStyle.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance(sFontFamily, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat$FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry, Resources resources, int i) {
        Object obj;
        FontResourcesParserCompat$FontFileResourceEntry[] fontResourcesParserCompat$FontFileResourceEntryArr;
        MappedByteBuffer mappedByteBuffer;
        FileInputStream fileInputStream;
        try {
            obj = sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        for (FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry : fontResourcesParserCompat$FontFamilyFilesResourceEntry.mEntries) {
            int i2 = fontResourcesParserCompat$FontFileResourceEntry.mResourceId;
            File tempFile = AppOpsManagerCompat.getTempFile(context);
            if (tempFile != null) {
                try {
                    if (AppOpsManagerCompat.copyToFile(tempFile, resources, i2)) {
                        try {
                            fileInputStream = new FileInputStream(tempFile);
                        } catch (IOException unused2) {
                            mappedByteBuffer = null;
                        }
                        try {
                            FileChannel channel = fileInputStream.getChannel();
                            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                            fileInputStream.close();
                            if (!(mappedByteBuffer == null && addFontWeightStyle(obj, mappedByteBuffer, fontResourcesParserCompat$FontFileResourceEntry.mTtcIndex, fontResourcesParserCompat$FontFileResourceEntry.mWeight, fontResourcesParserCompat$FontFileResourceEntry.mItalic))) {
                                return null;
                            }
                        } finally {
                            break;
                        }
                    }
                } finally {
                    tempFile.delete();
                }
            }
            mappedByteBuffer = null;
            if (mappedByteBuffer == null) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(obj);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        Object obj;
        try {
            obj = sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        for (FontsContractCompat$FontInfo fontsContractCompat$FontInfo : fontsContractCompat$FontInfoArr) {
            Uri uri = fontsContractCompat$FontInfo.mUri;
            ByteBuffer byteBuffer = (ByteBuffer) simpleArrayMap.get(uri);
            if (byteBuffer == null) {
                byteBuffer = AppOpsManagerCompat.mmap(context, cancellationSignal, uri);
                simpleArrayMap.put(uri, byteBuffer);
            }
            if (byteBuffer == null || !addFontWeightStyle(obj, byteBuffer, fontsContractCompat$FontInfo.mTtcIndex, fontsContractCompat$FontInfo.mWeight, fontsContractCompat$FontInfo.mItalic)) {
                return null;
            }
        }
        Typeface createFromFamiliesWithDefault = createFromFamiliesWithDefault(obj);
        if (createFromFamiliesWithDefault == null) {
            return null;
        }
        return Typeface.create(createFromFamiliesWithDefault, i);
    }
}
