package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@ReactModule(name = ImageEditingManager.NAME)
/* loaded from: classes.dex */
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    public static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList("file://", "content://");
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = {"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};

    /* loaded from: classes.dex */
    public static class CleanTask extends GuardedAsyncTask<Void, Void> {
        public final Context mContext;

        public CleanTask(ReactContext reactContext, AnonymousClass1 r2) {
            super(reactContext);
            this.mContext = reactContext;
        }

        public final void cleanDirectory(File file) {
            File[] listFiles = file.listFiles(new FilenameFilter(this) { // from class: com.facebook.react.modules.camera.ImageEditingManager.CleanTask.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.delete();
                }
            }
        }

        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void[] voidArr) {
            cleanDirectory(this.mContext.getCacheDir());
            File externalCacheDir = this.mContext.getExternalCacheDir();
            if (externalCacheDir != null) {
                cleanDirectory(externalCacheDir);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class CropTask extends GuardedAsyncTask<Void, Void> {
        public final Context mContext;
        public final Callback mError;
        public final int mHeight;
        public final Callback mSuccess;
        public final String mUri;
        public final int mWidth;
        public final int mX;
        public final int mY;
        public int mTargetWidth = 0;
        public int mTargetHeight = 0;

        public CropTask(ReactContext reactContext, String str, int i, int i2, int i3, int i4, Callback callback, Callback callback2, AnonymousClass1 r9) {
            super(reactContext);
            if (i < 0 || i2 < 0 || i3 <= 0 || i4 <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
            }
            this.mContext = reactContext;
            this.mUri = str;
            this.mX = i;
            this.mY = i2;
            this.mWidth = i3;
            this.mHeight = i4;
            this.mSuccess = callback;
            this.mError = callback2;
        }

        /* JADX WARN: Finally extract failed */
        public final Bitmap cropAndResize(int i, int i2, BitmapFactory.Options options) throws IOException {
            float f;
            float f2;
            float f3;
            float f4;
            R$dimen.assertNotNull(options);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            InputStream openBitmapInputStream = openBitmapInputStream();
            try {
                BitmapFactory.decodeStream(openBitmapInputStream, null, options2);
                openBitmapInputStream.close();
                int i3 = this.mWidth;
                float f5 = i3;
                int i4 = this.mHeight;
                float f6 = i4;
                float f7 = i;
                float f8 = i2;
                float f9 = f7 / f8;
                if (f5 / f6 > f9) {
                    f = f9 * f6;
                    f4 = ((f5 - f) / 2.0f) + this.mX;
                    f3 = this.mY;
                    f2 = f8 / f6;
                } else {
                    float f10 = f5 / f9;
                    f4 = this.mX;
                    f3 = ((f6 - f10) / 2.0f) + this.mY;
                    f2 = f7 / f5;
                    f = f5;
                    f6 = f10;
                }
                options.inSampleSize = ImageEditingManager.getDecodeSampleSize(i3, i4, i, i2);
                options2.inJustDecodeBounds = false;
                InputStream openBitmapInputStream2 = openBitmapInputStream();
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openBitmapInputStream2, null, options);
                    if (decodeStream != null) {
                        openBitmapInputStream2.close();
                        float f11 = f2 * options.inSampleSize;
                        Matrix matrix = new Matrix();
                        matrix.setScale(f11, f11);
                        return Bitmap.createBitmap(decodeStream, (int) Math.floor(f4 / options.inSampleSize), (int) Math.floor(f3 / options.inSampleSize), (int) Math.floor(f / options.inSampleSize), (int) Math.floor(f6 / options.inSampleSize), matrix, true);
                    }
                    throw new IOException("Cannot decode bitmap: " + this.mUri);
                } catch (Throwable th) {
                    openBitmapInputStream2.close();
                    throw th;
                }
            } catch (Throwable th2) {
                openBitmapInputStream.close();
                throw th2;
            }
        }

        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void[] voidArr) {
            Bitmap bitmap;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i = this.mTargetWidth;
                if (i > 0 && this.mTargetHeight > 0) {
                    bitmap = cropAndResize(i, this.mTargetHeight, options);
                } else {
                    InputStream openBitmapInputStream = openBitmapInputStream();
                    BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(openBitmapInputStream, false);
                    int i2 = this.mX;
                    int i3 = this.mY;
                    Bitmap decodeRegion = newInstance.decodeRegion(new Rect(i2, i3, this.mWidth + i2, this.mHeight + i3), options);
                    openBitmapInputStream.close();
                    newInstance.recycle();
                    bitmap = decodeRegion;
                }
                String str = options.outMimeType;
                if (str == null || str.isEmpty()) {
                    throw new IOException("Could not determine MIME type");
                }
                File createTempFile = ImageEditingManager.createTempFile(this.mContext, str);
                ImageEditingManager.writeCompressedBitmapToFile(bitmap, str, createTempFile);
                if (str.equals("image/jpeg")) {
                    ImageEditingManager.copyExif(this.mContext, Uri.parse(this.mUri), createTempFile);
                }
                this.mSuccess.invoke(Uri.fromFile(createTempFile).toString());
            } catch (Exception e) {
                this.mError.invoke(e.getMessage());
            }
        }

        public final InputStream openBitmapInputStream() throws IOException {
            InputStream inputStream;
            if (ImageEditingManager.isLocalUri(this.mUri)) {
                inputStream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
            } else {
                inputStream = new URL(this.mUri).openConnection().getInputStream();
            }
            if (inputStream != null) {
                return inputStream;
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Cannot open bitmap: ");
            outline13.append(this.mUri);
            throw new IOException(outline13.toString());
        }
    }

    public ImageEditingManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        new CleanTask(getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void copyExif(Context context, Uri uri, File file) throws IOException {
        String[] strArr;
        File fileFromUri = getFileFromUri(context, uri);
        if (fileFromUri == null) {
            FLog.w("ReactNative", "Couldn't get real path for uri: " + uri);
            return;
        }
        ExifInterface exifInterface = new ExifInterface(fileFromUri.getAbsolutePath());
        ExifInterface exifInterface2 = new ExifInterface(file.getAbsolutePath());
        for (String str : EXIF_ATTRIBUTES) {
            String attribute = exifInterface.getAttribute(str);
            if (attribute != null) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.saveAttributes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File createTempFile(Context context, String str) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null || (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
            externalCacheDir = cacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(str), externalCacheDir);
    }

    private static Bitmap.CompressFormat getCompressFormatForType(String str) {
        if ("image/png".equals(str)) {
            return Bitmap.CompressFormat.PNG;
        }
        if ("image/webp".equals(str)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDecodeSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i3 || i > i4) {
            int i6 = i2 / 2;
            int i7 = i / 2;
            while (i7 / i5 >= i3 && i6 / i5 >= i4) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static String getFileExtensionForType(String str) {
        return "image/png".equals(str) ? ".png" : "image/webp".equals(str) ? ".webp" : ".jpg";
    }

    private static File getFileFromUri(Context context, Uri uri) {
        Cursor query;
        if (uri.getScheme().equals("file")) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals("content") || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                String string = query.getString(0);
                if (!TextUtils.isEmpty(string)) {
                    return new File(string);
                }
            }
            return null;
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isLocalUri(String str) {
        for (String str2 : LOCAL_URI_PREFIXES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeCompressedBitmapToFile(Bitmap bitmap, String str, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(getCompressFormatForType(str), COMPRESS_QUALITY, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    @ReactMethod
    public void cropImage(String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        ReadableMap readableMap2 = null;
        ReadableMap map = readableMap.hasKey("offset") ? readableMap.getMap("offset") : null;
        if (readableMap.hasKey("size")) {
            readableMap2 = readableMap.getMap("size");
        }
        if (map == null || readableMap2 == null || !map.hasKey("x") || !map.hasKey("y") || !readableMap2.hasKey("width") || !readableMap2.hasKey("height")) {
            throw new JSApplicationIllegalArgumentException("Please specify offset and size");
        } else if (str == null || str.isEmpty()) {
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        } else {
            CropTask cropTask = new CropTask(getReactApplicationContext(), str, (int) map.getDouble("x"), (int) map.getDouble("y"), (int) readableMap2.getDouble("width"), (int) readableMap2.getDouble("height"), callback, callback2, null);
            if (readableMap.hasKey("displaySize")) {
                ReadableMap map2 = readableMap.getMap("displaySize");
                int i = (int) map2.getDouble("width");
                int i2 = (int) map2.getDouble("height");
                if (i <= 0 || i2 <= 0) {
                    throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", Integer.valueOf(i), Integer.valueOf(i2)));
                }
                cropTask.mTargetWidth = i;
                cropTask.mTargetHeight = i2;
            }
            cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        new CleanTask(getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
