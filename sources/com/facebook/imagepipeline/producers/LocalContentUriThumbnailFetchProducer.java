package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer<EncodedImage> {
    public final ContentResolver mContentResolver;
    public static final String[] PROJECTION = {"_id", "_data"};
    public static final String[] THUMBNAIL_PROJECTION = {"_data"};
    public static final Rect MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, 384);
    public static final Rect MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);

    public LocalContentUriThumbnailFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    public static int getRotationAngle(String str) {
        if (str != null) {
            try {
                return R$dimen.getAutoRotateAngleFromOrientation(new ExifInterface(str).getAttributeInt("Orientation", 1));
            } catch (IOException e) {
                Object[] objArr = {str};
                String simpleName = LocalContentUriThumbnailFetchProducer.class.getSimpleName();
                String formatString = FLog.formatString("Unable to retrieve thumbnail rotation for %s", objArr);
                String outline9 = GeneratedOutlineSupport.outline9("unknown", ":", simpleName);
                StringBuilder outline14 = GeneratedOutlineSupport.outline14(formatString, '\n');
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                outline14.append(stringWriter.toString());
                Log.println(6, outline9, outline14.toString());
            }
        }
        return 0;
    }

    @Override // com.facebook.imagepipeline.producers.ThumbnailProducer
    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        Rect rect = MINI_THUMBNAIL_DIMENSIONS;
        return R$dimen.isImageBigEnough(rect.width(), rect.height(), resizeOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004e A[RETURN] */
    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.imagepipeline.image.EncodedImage getEncodedImage(com.facebook.imagepipeline.request.ImageRequest r8) throws java.io.IOException {
        /*
            r7 = this;
            android.net.Uri r1 = r8.mSourceUri
            boolean r0 = com.facebook.common.util.UriUtil.isLocalCameraUri(r1)
            r6 = 0
            if (r0 == 0) goto L_0x0054
            com.facebook.imagepipeline.common.ResizeOptions r8 = r8.mResizeOptions
            android.content.ContentResolver r0 = r7.mContentResolver
            java.lang.String[] r2 = com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer.PROJECTION
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x001a
        L_0x0018:
            r8 = r6
            goto L_0x004c
        L_0x001a:
            int r1 = r0.getCount()     // Catch: all -> 0x004f
            if (r1 != 0) goto L_0x0024
        L_0x0020:
            r0.close()
            goto L_0x0018
        L_0x0024:
            r0.moveToFirst()     // Catch: all -> 0x004f
            java.lang.String r1 = "_data"
            int r1 = r0.getColumnIndex(r1)     // Catch: all -> 0x004f
            java.lang.String r1 = r0.getString(r1)     // Catch: all -> 0x004f
            if (r8 == 0) goto L_0x0020
            java.lang.String r2 = "_id"
            int r2 = r0.getColumnIndex(r2)     // Catch: all -> 0x004f
            int r2 = r0.getInt(r2)     // Catch: all -> 0x004f
            com.facebook.imagepipeline.image.EncodedImage r8 = r7.getThumbnail(r8, r2)     // Catch: all -> 0x004f
            if (r8 == 0) goto L_0x0020
            int r1 = getRotationAngle(r1)     // Catch: all -> 0x004f
            r8.mRotationAngle = r1     // Catch: all -> 0x004f
            r0.close()
        L_0x004c:
            if (r8 == 0) goto L_0x0054
            return r8
        L_0x004f:
            r8 = move-exception
            r0.close()
            throw r8
        L_0x0054:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer.getEncodedImage(com.facebook.imagepipeline.request.ImageRequest):com.facebook.imagepipeline.image.EncodedImage");
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "LocalContentUriThumbnailFetchProducer";
    }

    public final EncodedImage getThumbnail(ResizeOptions resizeOptions, int i) throws IOException {
        int i2;
        Throwable th;
        Rect rect = MICRO_THUMBNAIL_DIMENSIONS;
        if (R$dimen.isImageBigEnough(rect.width(), rect.height(), resizeOptions)) {
            i2 = 3;
        } else {
            Rect rect2 = MINI_THUMBNAIL_DIMENSIONS;
            i2 = R$dimen.isImageBigEnough(rect2.width(), rect2.height(), resizeOptions) ? 1 : 0;
        }
        Cursor cursor = null;
        if (i2 == 0) {
            return null;
        }
        try {
            Cursor queryMiniThumbnail = MediaStore.Images.Thumbnails.queryMiniThumbnail(this.mContentResolver, i, i2, THUMBNAIL_PROJECTION);
            if (queryMiniThumbnail == null) {
                if (queryMiniThumbnail != null) {
                    queryMiniThumbnail.close();
                }
                return null;
            }
            try {
                queryMiniThumbnail.moveToFirst();
                if (queryMiniThumbnail.getCount() > 0) {
                    String string = queryMiniThumbnail.getString(queryMiniThumbnail.getColumnIndex("_data"));
                    if (new File(string).exists()) {
                        EncodedImage byteBufferBackedEncodedImage = getByteBufferBackedEncodedImage(new FileInputStream(string), string == null ? -1 : (int) new File(string).length());
                        queryMiniThumbnail.close();
                        return byteBufferBackedEncodedImage;
                    }
                }
                queryMiniThumbnail.close();
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = queryMiniThumbnail;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
