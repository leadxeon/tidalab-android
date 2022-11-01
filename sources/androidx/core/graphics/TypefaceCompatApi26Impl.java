package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.graphics.TypefaceCompatBaseImpl;
import androidx.core.provider.FontsContractCompat$FontInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    public final Method mAbortCreation;
    public final Method mAddFontFromAssetManager;
    public final Method mAddFontFromBuffer;
    public final Method mCreateFromFamiliesWithDefault;
    public final Class<?> mFontFamily;
    public final Constructor<?> mFontFamilyCtor;
    public final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Method method;
        Method method2;
        Method method3;
        Constructor<?> constructor;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(new Class[0]);
            method3 = obtainAddFontFromAssetManagerMethod(cls2);
            method2 = obtainAddFontFromBufferMethod(cls2);
            method = cls2.getMethod("freeze", new Class[0]);
            method4 = cls2.getMethod("abortCreation", new Class[0]);
            method5 = obtainCreateFromFamiliesWithDefaultMethod(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to collect necessary methods for class ");
            outline13.append(e.getClass().getName());
            Log.e("TypefaceCompatApi26Impl", outline13.toString(), e);
            method5 = null;
            method4 = null;
            constructor = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.mFontFamily = cls;
        this.mFontFamilyCtor = constructor;
        this.mAddFontFromAssetManager = method3;
        this.mAddFontFromBuffer = method2;
        this.mFreeze = method;
        this.mAbortCreation = method4;
        this.mCreateFromFamiliesWithDefault = method5;
    }

    private Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public final void abortCreation(Object obj) {
        try {
            this.mAbortCreation.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.mAddFontFromAssetManager.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.mFontFamily, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat$FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat$FontFileResourceEntry[] fontResourcesParserCompat$FontFileResourceEntryArr;
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontResourcesParserCompat$FontFamilyFilesResourceEntry, resources, i);
        }
        Object newFamily = newFamily();
        if (newFamily == null) {
            return null;
        }
        for (FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry : fontResourcesParserCompat$FontFamilyFilesResourceEntry.mEntries) {
            if (!addFontFromAssetManager(context, newFamily, fontResourcesParserCompat$FontFileResourceEntry.mFileName, fontResourcesParserCompat$FontFileResourceEntry.mTtcIndex, fontResourcesParserCompat$FontFileResourceEntry.mWeight, fontResourcesParserCompat$FontFileResourceEntry.mItalic ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontResourcesParserCompat$FontFileResourceEntry.mVariationSettings))) {
                abortCreation(newFamily);
                return null;
            }
        }
        if (!freeze(newFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(newFamily);
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        Typeface createFromFamiliesWithDefault;
        boolean z;
        if (fontsContractCompat$FontInfoArr.length < 1) {
            return null;
        }
        if (!isFontFamilyPrivateAPIAvailable()) {
            FontsContractCompat$FontInfo fontsContractCompat$FontInfo = (FontsContractCompat$FontInfo) TypefaceCompatBaseImpl.findBestFont(fontsContractCompat$FontInfoArr, i, new TypefaceCompatBaseImpl.AnonymousClass1(this));
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(fontsContractCompat$FontInfo.mUri, "r", cancellationSignal);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(fontsContractCompat$FontInfo.mWeight).setItalic(fontsContractCompat$FontInfo.mItalic).build();
                openFileDescriptor.close();
                return build;
            } catch (IOException unused) {
                return null;
            }
        } else {
            HashMap hashMap = new HashMap();
            for (FontsContractCompat$FontInfo fontsContractCompat$FontInfo2 : fontsContractCompat$FontInfoArr) {
                if (fontsContractCompat$FontInfo2.mResultCode == 0) {
                    Uri uri = fontsContractCompat$FontInfo2.mUri;
                    if (!hashMap.containsKey(uri)) {
                        hashMap.put(uri, AppOpsManagerCompat.mmap(context, cancellationSignal, uri));
                    }
                }
            }
            Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
            Object newFamily = newFamily();
            if (newFamily == null) {
                return null;
            }
            boolean z2 = false;
            for (FontsContractCompat$FontInfo fontsContractCompat$FontInfo3 : fontsContractCompat$FontInfoArr) {
                ByteBuffer byteBuffer = (ByteBuffer) unmodifiableMap.get(fontsContractCompat$FontInfo3.mUri);
                if (byteBuffer != null) {
                    try {
                        z = ((Boolean) this.mAddFontFromBuffer.invoke(newFamily, byteBuffer, Integer.valueOf(fontsContractCompat$FontInfo3.mTtcIndex), null, Integer.valueOf(fontsContractCompat$FontInfo3.mWeight), Integer.valueOf(fontsContractCompat$FontInfo3.mItalic ? 1 : 0))).booleanValue();
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                        z = false;
                    }
                    if (!z) {
                        abortCreation(newFamily);
                        return null;
                    }
                    z2 = true;
                }
            }
            if (!z2) {
                abortCreation(newFamily);
                return null;
            } else if (freeze(newFamily) && (createFromFamiliesWithDefault = createFromFamiliesWithDefault(newFamily)) != null) {
                return Typeface.create(createFromFamiliesWithDefault, i);
            } else {
                return null;
            }
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, i, str, i2);
        }
        Object newFamily = newFamily();
        if (newFamily == null) {
            return null;
        }
        if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1, null)) {
            abortCreation(newFamily);
            return null;
        } else if (!freeze(newFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(newFamily);
        }
    }

    public final boolean freeze(Object obj) {
        try {
            return ((Boolean) this.mFreeze.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    public Method obtainAddFontFromAssetManagerMethod(Class<?> cls) throws NoSuchMethodException {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public Method obtainAddFontFromBufferMethod(Class<?> cls) throws NoSuchMethodException {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
