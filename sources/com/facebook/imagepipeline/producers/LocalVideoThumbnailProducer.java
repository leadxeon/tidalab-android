package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    public final ContentResolver mContentResolver;
    public final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    public static String access$000(LocalVideoThumbnailProducer localVideoThumbnailProducer, ImageRequest imageRequest) {
        String[] strArr;
        String str;
        Uri uri;
        Objects.requireNonNull(localVideoThumbnailProducer);
        Uri uri2 = imageRequest.mSourceUri;
        if (UriUtil.isLocalFileUri(uri2)) {
            return imageRequest.getSourceFile().getPath();
        }
        if (UriUtil.isLocalContentUri(uri2)) {
            if ("com.android.providers.media.documents".equals(uri2.getAuthority())) {
                String documentId = DocumentsContract.getDocumentId(uri2);
                str = "_id=?";
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                strArr = new String[]{documentId.split(":")[1]};
            } else {
                uri = uri2;
                str = null;
                strArr = null;
            }
            Cursor query = localVideoThumbnailProducer.mContentResolver.query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        return query.getString(query.getColumnIndexOrThrow("_data"));
                    }
                } finally {
                    query.close();
                }
            }
            if (query != null) {
            }
        }
        return null;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        final RequestListener listener = producerContext.getListener();
        final String id = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable<CloseableReference<CloseableImage>> statefulProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, listener, "VideoThumbnailProducer", id) { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.1
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void disposeResult(CloseableReference<CloseableImage> closeableReference) {
                CloseableReference<CloseableImage> closeableReference2 = closeableReference;
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference2 != null) {
                    closeableReference2.close();
                }
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map getExtraMapOnSuccess(CloseableReference<CloseableImage> closeableReference) {
                return ImmutableMap.of("createdThumbnail", String.valueOf(closeableReference != null));
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public CloseableReference<CloseableImage> getResult() throws Exception {
                String str;
                Bitmap bitmap;
                int i;
                try {
                    str = LocalVideoThumbnailProducer.access$000(LocalVideoThumbnailProducer.this, imageRequest);
                } catch (IllegalArgumentException unused) {
                    str = null;
                }
                if (str != null) {
                    ResizeOptions resizeOptions = imageRequest.mResizeOptions;
                    int i2 = 2048;
                    if ((resizeOptions != null ? resizeOptions.width : 2048) <= 96) {
                        if (resizeOptions != null) {
                            i2 = resizeOptions.height;
                        }
                        if (i2 <= 96) {
                            i = 3;
                            bitmap = ThumbnailUtils.createVideoThumbnail(str, i);
                        }
                    }
                    i = 1;
                    bitmap = ThumbnailUtils.createVideoThumbnail(str, i);
                } else {
                    try {
                        ParcelFileDescriptor openFileDescriptor = LocalVideoThumbnailProducer.this.mContentResolver.openFileDescriptor(imageRequest.mSourceUri, "r");
                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                        mediaMetadataRetriever.setDataSource(openFileDescriptor.getFileDescriptor());
                        bitmap = mediaMetadataRetriever.getFrameAtTime(-1L);
                    } catch (FileNotFoundException unused2) {
                        bitmap = null;
                    }
                }
                if (bitmap == null) {
                    return null;
                }
                return CloseableReference.of(new CloseableStaticBitmap(bitmap, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0));
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void onFailure(Exception exc) {
                super.onFailure(exc);
                listener.onUltimateProducerReached(id, "VideoThumbnailProducer", false);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void onSuccess(CloseableReference<CloseableImage> closeableReference) {
                CloseableReference<CloseableImage> closeableReference2 = closeableReference;
                super.onSuccess(closeableReference2);
                listener.onUltimateProducerReached(id, "VideoThumbnailProducer", closeableReference2 != null);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(statefulProducerRunnable);
    }
}
