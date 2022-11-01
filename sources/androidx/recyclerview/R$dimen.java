package androidx.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Trace;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.webkit.internal.ConditionallySupportedFeature;
import androidx.webkit.internal.WebSettingsAdapter;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator$LAZY_COMPAT_CONVERTER_HOLDER;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.common.file.FileUtils$FileDeleteException;
import com.facebook.common.file.FileUtils$RenameException;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.drawee.drawable.ArrayDrawable;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.DrawableProperties;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.TransformAwareDrawable;
import com.facebook.drawee.drawable.TransformCallback;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.soloader.SoLoader;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatcherExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcherImpl;
import okhttp3.HttpUrl;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;
/* loaded from: classes.dex */
public final class R$dimen {
    public static boolean sInitialized;
    public static Method sIsTagEnabledMethod;
    public static ScheduledExecutorService sJobStarterExecutor;
    public static long sTraceTagApp;

    public static int adjustByteCount(int i, int i2, int i3) {
        return Math.min(Math.max(0, i3 - i), i2);
    }

    public static byte[] asciiBytes(String str) {
        Objects.requireNonNull(str);
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static void assertCondition(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public static <T> T assertNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkBounds(int i, int i2, int i3, int i4, int i5) {
        boolean z = true;
        checkArgument(i4 >= 0);
        checkArgument(i >= 0);
        checkArgument(i3 >= 0);
        checkArgument(i + i4 <= i5);
        if (i3 + i4 > i2) {
            z = false;
        }
        checkArgument(z);
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static boolean compare(byte[] bArr, String str) {
        if (bArr.length != str.length()) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (str.charAt(i) != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static int computeScrollExtent(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1;
        }
        return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view));
    }

    public static int computeScrollOffset(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        int i;
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(layoutManager.getPosition(view), layoutManager.getPosition(view2));
        int max = Math.max(layoutManager.getPosition(view), layoutManager.getPosition(view2));
        if (z2) {
            i = Math.max(0, (state.getItemCount() - max) - 1);
        } else {
            i = Math.max(0, min);
        }
        if (!z) {
            return i;
        }
        return Math.round((i * (Math.abs(orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1))) + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(view)));
    }

    public static int computeScrollRange(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return state.getItemCount();
        }
        return (int) (((orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1)) * state.getItemCount());
    }

    public static void copyProperties(Drawable drawable, Drawable drawable2) {
        if (drawable != null && drawable != drawable2) {
            drawable.setBounds(drawable2.getBounds());
            drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
            drawable.setLevel(drawable2.getLevel());
            drawable.setVisible(drawable2.isVisible(), false);
            drawable.setState(drawable2.getState());
        }
    }

    public static boolean deleteContents(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                z &= deleteRecursively(file2);
            }
        }
        return z;
    }

    public static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            deleteContents(file);
        }
        return file.delete();
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int determineSampleSize(com.facebook.imagepipeline.common.RotationOptions r14, com.facebook.imagepipeline.common.ResizeOptions r15, com.facebook.imagepipeline.image.EncodedImage r16, int r17) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.R$dimen.determineSampleSize(com.facebook.imagepipeline.common.RotationOptions, com.facebook.imagepipeline.common.ResizeOptions, com.facebook.imagepipeline.image.EncodedImage, int):int");
    }

    public static synchronized void ensure() {
        synchronized (R$dimen.class) {
            if (!sInitialized) {
                SoLoader.loadLibrary("native-imagetranscoder");
                sInitialized = true;
            }
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static InvocationHandler fetchGlueProviderFactoryImpl() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        ClassLoader classLoader;
        if (Build.VERSION.SDK_INT >= 28) {
            classLoader = WebView.getWebViewClassLoader();
        } else {
            try {
                Method declaredMethod = WebView.class.getDeclaredMethod("getFactory", new Class[0]);
                declaredMethod.setAccessible(true);
                classLoader = declaredMethod.invoke(null, new Object[0]).getClass().getClassLoader();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3);
            }
        }
        return (InvocationHandler) Class.forName("org.chromium.support_lib_glue.SupportLibReflectionUtil", false, classLoader).getDeclaredMethod("createWebViewProviderFactory", new Class[0]).invoke(null, new Object[0]);
    }

    public static String format(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder((objArr.length * 16) + valueOf.length());
        int i = 0;
        int i2 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append(valueOf.substring(i2, indexOf));
            i++;
            sb.append(objArr[i]);
            i2 = indexOf + 2;
        }
        sb.append(valueOf.substring(i2));
        if (i < objArr.length) {
            sb.append(" [");
            int i3 = i + 1;
            sb.append(objArr[i]);
            while (i3 < objArr.length) {
                sb.append(", ");
                i3++;
                sb.append(objArr[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static int get2BytesAsInt(InputStream inputStream) throws IOException {
        return ((((byte) inputStream.read()) << 8) & 65280) | (((byte) inputStream.read()) & 255);
    }

    public static int getAcceptableSize(int i) {
        return (int) (i * 1.3333334f);
    }

    public static ScaleTypeDrawable getActiveScaleTypeDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) drawable;
        }
        if (drawable instanceof DrawableParent) {
            return getActiveScaleTypeDrawable(((DrawableParent) drawable).getDrawable());
        }
        if (drawable instanceof ArrayDrawable) {
            ArrayDrawable arrayDrawable = (ArrayDrawable) drawable;
            int length = arrayDrawable.mLayers.length;
            for (int i = 0; i < length; i++) {
                ScaleTypeDrawable activeScaleTypeDrawable = getActiveScaleTypeDrawable(arrayDrawable.getDrawable(i));
                if (activeScaleTypeDrawable != null) {
                    return activeScaleTypeDrawable;
                }
            }
        }
        return null;
    }

    public static WebSettingsAdapter getAdapter(WebSettings webSettings) {
        return new WebSettingsAdapter((WebSettingsBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebSettingsBoundaryInterface.class, WebViewGlueCommunicator$LAZY_COMPAT_CONVERTER_HOLDER.INSTANCE.mImpl.convertSettings(webSettings)));
    }

    public static int getAutoRotateAngleFromOrientation(int i) {
        if (i == 3) {
            return 180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    public static int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            columnIndex = cursor.getColumnIndex("`" + str + "`");
            if (columnIndex < 0) {
                if (Build.VERSION.SDK_INT <= 25 && str.length() != 0) {
                    String[] columnNames = cursor.getColumnNames();
                    String outline8 = GeneratedOutlineSupport.outline8(".", str);
                    String outline9 = GeneratedOutlineSupport.outline9(".", str, "`");
                    for (int i = 0; i < columnNames.length; i++) {
                        String str3 = columnNames[i];
                        if (str3.length() >= str.length() + 2 && (str3.endsWith(outline8) || (str3.charAt(0) == '`' && str3.endsWith(outline9)))) {
                            columnIndex = i;
                            break;
                        }
                    }
                }
                columnIndex = -1;
            }
        }
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            str2 = Arrays.toString(cursor.getColumnNames());
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + str2);
    }

    public static int getInt(InputStream inputStream) throws IOException {
        return ((((byte) inputStream.read()) << 24) & (-16777216)) | ((((byte) inputStream.read()) << 16) & 16711680) | ((((byte) inputStream.read()) << 8) & 65280) | (((byte) inputStream.read()) & 255);
    }

    public static final CoroutineDispatcher getQueryDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> map = roomDatabase.mBackingFieldMap;
        Object obj = map.get("QueryDispatcher");
        if (obj == null) {
            Executor executor = roomDatabase.mQueryExecutor;
            if (executor instanceof DispatcherExecutor) {
                DispatcherExecutor dispatcherExecutor = (DispatcherExecutor) executor;
            }
            obj = new ExecutorCoroutineDispatcherImpl(executor);
            map.put("QueryDispatcher", obj);
        }
        return (CoroutineDispatcher) obj;
    }

    public static List<String> getResourceIds(CacheKey cacheKey) {
        try {
            if (cacheKey instanceof MultiCacheKey) {
                List<CacheKey> list = ((MultiCacheKey) cacheKey).mCacheKeys;
                ArrayList arrayList = new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(secureHashKey(list.get(i)));
                }
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(secureHashKey(cacheKey));
            return arrayList2;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final CoroutineDispatcher getTransactionDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> map = roomDatabase.mBackingFieldMap;
        Object obj = map.get("TransactionDispatcher");
        if (obj == null) {
            Executor executor = roomDatabase.mTransactionExecutor;
            if (executor instanceof DispatcherExecutor) {
                DispatcherExecutor dispatcherExecutor = (DispatcherExecutor) executor;
            }
            obj = new ExecutorCoroutineDispatcherImpl(executor);
            map.put("TransactionDispatcher", obj);
        }
        return (CoroutineDispatcher) obj;
    }

    public static Pair<Integer, Integer> getVP8Dimension(InputStream inputStream) throws IOException {
        inputStream.skip(7L);
        short read = (short) (inputStream.read() & 255);
        short read2 = (short) (inputStream.read() & 255);
        short read3 = (short) (inputStream.read() & 255);
        if (read == 157 && read2 == 1 && read3 == 42) {
            return new Pair<>(Integer.valueOf(get2BytesAsInt(inputStream)), Integer.valueOf(get2BytesAsInt(inputStream)));
        }
        return null;
    }

    public static Pair<Integer, Integer> getVP8LDimension(InputStream inputStream) throws IOException {
        getInt(inputStream);
        if (((byte) (inputStream.read() & 255)) != 47) {
            return null;
        }
        int read = ((byte) inputStream.read()) & 255;
        return new Pair<>(Integer.valueOf(((((byte) inputStream.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf(((((((byte) inputStream.read()) & 255) & 15) << 10) | ((((byte) inputStream.read()) & 255) << 2) | ((read & 192) >> 6)) + 1));
    }

    public static int hashCode(int i, int i2) {
        return ((i + 31) * 31) + i2;
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable th) {
        SimpleDataSource simpleDataSource = new SimpleDataSource();
        simpleDataSource.setFailure(th);
        return simpleDataSource;
    }

    @SuppressLint({"NewApi"})
    public static boolean isEnabled() {
        try {
            if (sIsTagEnabledMethod == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (sIsTagEnabledMethod == null) {
                sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                throw new RuntimeException(cause);
            }
            Log.v("Trace", "Unable to call isTagEnabled via reflection", e);
            return false;
        }
    }

    public static boolean isFeatureSupported(String str) {
        HashSet hashSet = new HashSet();
        WebViewFeatureInternal[] values = WebViewFeatureInternal.values();
        for (int i = 0; i < 45; i++) {
            hashSet.add(values[i]);
        }
        HashSet hashSet2 = new HashSet();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ConditionallySupportedFeature conditionallySupportedFeature = (ConditionallySupportedFeature) it.next();
            if (conditionallySupportedFeature.getPublicFeatureName().equals(str)) {
                hashSet2.add(conditionallySupportedFeature);
            }
        }
        if (!hashSet2.isEmpty()) {
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                if (((ConditionallySupportedFeature) it2.next()).isSupported()) {
                    return true;
                }
            }
            return false;
        }
        throw new RuntimeException(GeneratedOutlineSupport.outline8("Unknown feature ", str));
    }

    public static boolean isImageBigEnough(int i, int i2, ResizeOptions resizeOptions) {
        return resizeOptions == null ? ((float) getAcceptableSize(i)) >= 2048.0f && getAcceptableSize(i2) >= 2048 : getAcceptableSize(i) >= resizeOptions.width && getAcceptableSize(i2) >= resizeOptions.height;
    }

    public static void mkdirs(File file) throws FileUtils$CreateDirectoryException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (!file.delete()) {
                    throw new FileUtils$CreateDirectoryException(file.getAbsolutePath(), new FileUtils$FileDeleteException(file.getAbsolutePath()));
                }
            } else {
                return;
            }
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new FileUtils$CreateDirectoryException(file.getAbsolutePath());
        }
    }

    public static int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public static <T> ObjectAnimator ofPointF(T t, Property<T, PointF> property, Path path) {
        return ObjectAnimator.ofObject(t, property, (TypeConverter) null, path);
    }

    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i + i3, i2 - i3);
                if (read == -1) {
                    break;
                }
                i3 += read;
            }
            return i3;
        }
        throw new IndexOutOfBoundsException("len is negative");
    }

    public static int read3Bytes(InputStream inputStream) throws IOException {
        return ((((byte) (inputStream.read() & 255)) << 16) & 16711680) | ((((byte) (inputStream.read() & 255)) << 8) & 65280) | (((byte) (inputStream.read() & 255)) & 255);
    }

    public static int readPackedInt(InputStream inputStream, int i, boolean z) throws IOException {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int read = inputStream.read();
            if (read != -1) {
                if (z) {
                    i2 = (read & 255) << (i4 * 8);
                } else {
                    i3 <<= 8;
                    i2 = read & 255;
                }
                i3 |= i2;
            } else {
                throw new IOException("no more bytes");
            }
        }
        return i3;
    }

    public static void rename(File file, File file2) throws FileUtils$RenameException {
        Objects.requireNonNull(file);
        file2.delete();
        if (!file.renameTo(file2)) {
            final Throwable th = null;
            if (file2.exists()) {
                th = new FileUtils$FileDeleteException(file2.getAbsolutePath());
            } else if (!file.getParentFile().exists()) {
                final String absolutePath = file.getAbsolutePath();
                th = new FileNotFoundException(absolutePath) { // from class: com.facebook.common.file.FileUtils$ParentDirNotFoundException
                };
            } else if (!file.exists()) {
                th = new FileNotFoundException(file.getAbsolutePath());
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown error renaming ");
            outline13.append(file.getAbsolutePath());
            outline13.append(" to ");
            outline13.append(file2.getAbsolutePath());
            final String sb = outline13.toString();
            throw new IOException(sb, th) { // from class: com.facebook.common.file.FileUtils$RenameException
                {
                    initCause(th);
                }
            };
        }
    }

    public static String secureHashKey(CacheKey cacheKey) throws UnsupportedEncodingException {
        byte[] bytes = cacheKey.getUriString().getBytes(RNCWebViewManager.HTML_ENCODING);
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bytes, 0, bytes.length);
            return Base64.encodeToString(instance.digest(), 11);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setCallbacks(Drawable drawable, Drawable.Callback callback, TransformCallback transformCallback) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof TransformAwareDrawable) {
                ((TransformAwareDrawable) drawable).setTransformCallback(transformCallback);
            }
        }
    }

    public static void setDrawableProperties(Drawable drawable, DrawableProperties drawableProperties) {
        if (drawable != null && drawableProperties != null) {
            int i = drawableProperties.mAlpha;
            if (i != -1) {
                drawable.setAlpha(i);
            }
            if (drawableProperties.mIsSetColorFilter) {
                drawable.setColorFilter(drawableProperties.mColorFilter);
            }
            int i2 = drawableProperties.mDither;
            boolean z = false;
            if (i2 != -1) {
                drawable.setDither(i2 != 0);
            }
            int i3 = drawableProperties.mFilterBitmap;
            if (i3 != -1) {
                if (i3 != 0) {
                    z = true;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }

    public static long skip(InputStream inputStream, long j) throws IOException {
        checkArgument(j >= 0);
        long j2 = j;
        while (j2 > 0) {
            long skip = inputStream.skip(j2);
            if (skip <= 0) {
                if (inputStream.read() == -1) {
                    return j - j2;
                }
                skip = 1;
            }
            j2 -= skip;
        }
        return j;
    }

    public static boolean startsWithPattern(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr);
        Objects.requireNonNull(bArr2);
        if (bArr2.length > bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Objects$ToStringHelper toStringHelper(Object obj) {
        String replaceAll = obj.getClass().getName().replaceAll("\\$[0-9]+", "\\$");
        int lastIndexOf = replaceAll.lastIndexOf(36);
        if (lastIndexOf == -1) {
            lastIndexOf = replaceAll.lastIndexOf(46);
        }
        return new Objects$ToStringHelper(replaceAll.substring(lastIndexOf + 1), null);
    }

    public static void walkFileTree(File file, FileTreeVisitor fileTreeVisitor) {
        fileTreeVisitor.preVisitDirectory(file);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    walkFileTree(file2, fileTreeVisitor);
                } else {
                    fileTreeVisitor.visitFile(file2);
                }
            }
        }
        fileTreeVisitor.postVisitDirectory(file);
    }

    public static void assertCondition(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static <T> T assertNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new AssertionError(str);
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static boolean isImageBigEnough(EncodedImage encodedImage, ResizeOptions resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        encodedImage.parseMetaDataIfNeeded();
        int i = encodedImage.mRotationAngle;
        if (i == 90 || i == 270) {
            encodedImage.parseMetaDataIfNeeded();
            int i2 = encodedImage.mHeight;
            encodedImage.parseMetaDataIfNeeded();
            return isImageBigEnough(i2, encodedImage.mWidth, resizeOptions);
        }
        encodedImage.parseMetaDataIfNeeded();
        int i3 = encodedImage.mWidth;
        encodedImage.parseMetaDataIfNeeded();
        return isImageBigEnough(i3, encodedImage.mHeight, resizeOptions);
    }
}
