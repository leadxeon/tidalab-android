package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.internal.cache.DiskLruCache;
@ReactModule(name = CameraRollManager.NAME)
/* loaded from: classes.dex */
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final String NAME = "CameraRollManager";
    private static final String[] PROJECTION = {"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude", "_data"};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";
    private static final String SELECTION_MEDIA_SIZE = "_size < ?";

    /* loaded from: classes.dex */
    public static class GetMediaTask extends GuardedAsyncTask<Void, Void> {
        public final String mAfter;
        public final String mAssetType;
        public final Context mContext;
        public final int mFirst;
        public final String mGroupName;
        public final Integer mMaxSize;
        public final ReadableArray mMimeTypes;
        public final Promise mPromise;

        public GetMediaTask(ReactContext reactContext, int i, String str, String str2, ReadableArray readableArray, String str3, Integer num, Promise promise, AnonymousClass1 r9) {
            super(reactContext);
            this.mContext = reactContext;
            this.mFirst = i;
            this.mAfter = str;
            this.mGroupName = str2;
            this.mMimeTypes = readableArray;
            this.mPromise = promise;
            this.mAssetType = str3;
            this.mMaxSize = num;
        }

        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void[] voidArr) {
            StringBuilder sb = new StringBuilder(DiskLruCache.VERSION_1);
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.mAfter)) {
                sb.append(" AND datetaken < ?");
                arrayList.add(this.mAfter);
            }
            if (!TextUtils.isEmpty(this.mGroupName)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.mGroupName);
            }
            if (this.mMaxSize != null) {
                sb.append(" AND _size < ?");
                arrayList.add(this.mMaxSize.toString());
            }
            String str = this.mAssetType;
            str.hashCode();
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1905167199:
                    if (str.equals(CameraRollManager.ASSET_TYPE_PHOTOS)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1732810888:
                    if (str.equals(CameraRollManager.ASSET_TYPE_VIDEOS)) {
                        c = 1;
                        break;
                    }
                    break;
                case 65921:
                    if (str.equals(CameraRollManager.ASSET_TYPE_ALL)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    sb.append(" AND media_type = 1");
                    break;
                case 1:
                    sb.append(" AND media_type = 3");
                    break;
                case 2:
                    sb.append(" AND media_type IN (3,1)");
                    break;
                default:
                    Promise promise = this.mPromise;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid filter option: '");
                    outline13.append(this.mAssetType);
                    outline13.append("'. Expected one of '");
                    outline13.append(CameraRollManager.ASSET_TYPE_PHOTOS);
                    outline13.append("', '");
                    outline13.append(CameraRollManager.ASSET_TYPE_VIDEOS);
                    outline13.append("' or '");
                    outline13.append(CameraRollManager.ASSET_TYPE_ALL);
                    outline13.append("'.");
                    promise.reject(CameraRollManager.ERROR_UNABLE_TO_FILTER, outline13.toString());
                    return;
            }
            ReadableArray readableArray = this.mMimeTypes;
            if (readableArray != null && readableArray.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i = 0; i < this.mMimeTypes.size(); i++) {
                    sb.append("?,");
                    arrayList.add(this.mMimeTypes.getString(i));
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            try {
                Uri contentUri = MediaStore.Files.getContentUri("external");
                String[] strArr = CameraRollManager.PROJECTION;
                String sb2 = sb.toString();
                String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
                Cursor query = contentResolver.query(contentUri, strArr, sb2, strArr2, "datetaken DESC, date_modified DESC LIMIT " + (this.mFirst + 1));
                if (query == null) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get media");
                } else {
                    CameraRollManager.putEdges(contentResolver, query, writableNativeMap, this.mFirst);
                    CameraRollManager.putPageInfo(query, writableNativeMap, this.mFirst);
                    query.close();
                    this.mPromise.resolve(writableNativeMap);
                }
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need READ_EXTERNAL_STORAGE permission", e);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        public final Context mContext;
        public final Promise mPromise;
        public final Uri mUri;

        public SaveToCameraRoll(ReactContext reactContext, Uri uri, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUri = uri;
            this.mPromise = promise;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(8:2|(2:93|3)|(4:8|98|10|(2:12|(1:103)(3:96|16|108))(22:19|(1:21)(1:22)|23|(2:26|24)|99|27|28|94|29|(2:32|30)|100|33|(2:36|34)|101|37|38|(2:91|40)|43|(1:105)|87|64|109))|9|98|10|(0)(0)|(1:(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0121, code lost:
            r3 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0122, code lost:
            r2 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0126, code lost:
            r3 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0127, code lost:
            r2 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x015a, code lost:
            r3 = r4;
            r2 = r2;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0057 A[Catch: all -> 0x0121, IOException -> 0x0126, TRY_LEAVE, TryCatch #12 {IOException -> 0x0126, all -> 0x0121, blocks: (B:10:0x0048, B:12:0x0057, B:19:0x0073, B:21:0x0089, B:24:0x009f, B:26:0x00a5, B:27:0x00c5), top: B:98:0x0048 }] */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0073 A[Catch: all -> 0x0121, IOException -> 0x0126, TRY_ENTER, TryCatch #12 {IOException -> 0x0126, all -> 0x0121, blocks: (B:10:0x0048, B:12:0x0057, B:19:0x0073, B:21:0x0089, B:24:0x009f, B:26:0x00a5, B:27:0x00c5), top: B:98:0x0048 }] */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void doInBackgroundGuarded(java.lang.Void[] r14) {
            /*
                Method dump skipped, instructions count: 382
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.camera.CameraRollManager.SaveToCameraRoll.doInBackgroundGuarded(java.lang.Object[]):void");
        }
    }

    public CameraRollManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i, int i2, int i3) {
        writableMap.putString("type", cursor.getString(i));
        writableMap.putString("group_name", cursor.getString(i2));
        writableMap.putDouble("timestamp", cursor.getLong(i3) / 1000.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i) {
        int i2;
        ReadableArray writableNativeArray = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("mime_type");
        int columnIndex3 = cursor.getColumnIndex("bucket_display_name");
        int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5 = cursor.getColumnIndex("width");
        int columnIndex6 = cursor.getColumnIndex("height");
        int columnIndex7 = cursor.getColumnIndex("longitude");
        int columnIndex8 = cursor.getColumnIndex("latitude");
        int columnIndex9 = cursor.getColumnIndex("_data");
        int i3 = i;
        int i4 = 0;
        while (i4 < i3 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex, columnIndex5, columnIndex6, columnIndex9, columnIndex2)) {
                putBasicNodeInfo(cursor, writableNativeMap2, columnIndex2, columnIndex3, columnIndex4);
                putLocationInfo(cursor, writableNativeMap2, columnIndex7, columnIndex8);
                writableNativeMap.putMap("node", writableNativeMap2);
                writableNativeArray = writableNativeArray;
                writableNativeArray.pushMap(writableNativeMap);
                i2 = i4;
            } else {
                writableNativeArray = writableNativeArray;
                i2 = i4 - 1;
            }
            cursor.moveToNext();
            i4 = i2 + 1;
            i3 = i;
            columnIndex8 = columnIndex8;
            columnIndex7 = columnIndex7;
            columnIndex = columnIndex;
            columnIndex5 = columnIndex5;
        }
        writableMap.putArray("edges", writableNativeArray);
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("file://");
        outline13.append(cursor.getString(i4));
        Uri parse = Uri.parse(outline13.toString());
        writableNativeMap.putString("uri", parse.toString());
        float f = cursor.getInt(i2);
        float f2 = cursor.getInt(i3);
        String string = cursor.getString(i5);
        if (string != null && string.startsWith("video")) {
            try {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openAssetFileDescriptor.getFileDescriptor());
                if (f <= 0.0f || f2 <= 0.0f) {
                    try {
                        f = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                        f2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                    } catch (NumberFormatException e) {
                        FLog.e("ReactNative", "Number format exception occurred while trying to fetch video metadata for " + parse.toString(), e);
                        mediaMetadataRetriever.release();
                        openAssetFileDescriptor.close();
                        return false;
                    }
                }
                writableNativeMap.putInt("playableDuration", Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / RNCWebViewManager.COMMAND_CLEAR_FORM_DATA);
                mediaMetadataRetriever.release();
                openAssetFileDescriptor.close();
            } catch (Exception e2) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Could not get video metadata for ");
                outline132.append(parse.toString());
                FLog.e("ReactNative", outline132.toString(), e2);
                return false;
            }
        }
        if (f <= 0.0f || f2 <= 0.0f) {
            try {
                AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(parse, "r");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(openAssetFileDescriptor2.getFileDescriptor(), null, options);
                f = options.outWidth;
                f2 = options.outHeight;
                openAssetFileDescriptor2.close();
            } catch (IOException e3) {
                StringBuilder outline133 = GeneratedOutlineSupport.outline13("Could not get width/height for ");
                outline133.append(parse.toString());
                FLog.e("ReactNative", outline133.toString(), e3);
                return false;
            }
        }
        writableNativeMap.putDouble("width", f);
        writableNativeMap.putDouble("height", f2);
        writableMap.putMap("image", writableNativeMap);
        return true;
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i, int i2) {
        double d = cursor.getDouble(i);
        double d2 = cursor.getDouble(i2);
        if (d > 0.0d || d2 > 0.0d) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("longitude", d);
            writableNativeMap.putDouble("latitude", d2);
            writableMap.putMap("location", writableNativeMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i < cursor.getCount());
        if (i < cursor.getCount()) {
            cursor.moveToPosition(i - 1);
            writableNativeMap.putString("end_cursor", cursor.getString(cursor.getColumnIndex("datetaken")));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        int i = readableMap.getInt("first");
        String string = readableMap.hasKey("after") ? readableMap.getString("after") : null;
        String string2 = readableMap.hasKey("groupName") ? readableMap.getString("groupName") : null;
        String string3 = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_PHOTOS;
        Integer valueOf = readableMap.hasKey("maxSize") ? Integer.valueOf(readableMap.getInt("maxSize")) : null;
        ReadableArray array = readableMap.hasKey("mimeTypes") ? readableMap.getArray("mimeTypes") : null;
        if (!readableMap.hasKey("groupTypes")) {
            new GetMediaTask(getReactApplicationContext(), i, string, string2, array, string3, valueOf, promise, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
    }

    @ReactMethod
    public void saveToCameraRoll(String str, String str2, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(str), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
