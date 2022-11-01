package com.facebook.imagepipeline.request;

import android.net.Uri;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.media.MimeTypeMapWrapper;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.listener.RequestListener;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes.dex */
public class ImageRequest {
    public final BytesRange mBytesRange;
    public final CacheChoice mCacheChoice;
    public final Boolean mDecodePrefetches;
    public final ImageDecodeOptions mImageDecodeOptions;
    public final boolean mIsDiskCacheEnabled;
    public final boolean mIsMemoryCacheEnabled;
    public final boolean mLocalThumbnailPreviewsEnabled;
    public final RequestLevel mLowestPermittedRequestLevel;
    public final Postprocessor mPostprocessor;
    public final boolean mProgressiveRenderingEnabled;
    public final RequestListener mRequestListener;
    public final Priority mRequestPriority;
    public final ResizeOptions mResizeOptions;
    public final RotationOptions mRotationOptions;
    public File mSourceFile;
    public final Uri mSourceUri;
    public final int mSourceUriType;

    /* loaded from: classes.dex */
    public enum CacheChoice {
        SMALL,
        DEFAULT
    }

    /* loaded from: classes.dex */
    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        public int mValue;

        RequestLevel(int i) {
            this.mValue = i;
        }
    }

    public ImageRequest(ImageRequestBuilder imageRequestBuilder) {
        this.mCacheChoice = imageRequestBuilder.mCacheChoice;
        Uri uri = imageRequestBuilder.mSourceUri;
        this.mSourceUri = uri;
        boolean z = true;
        int i = -1;
        if (uri != null) {
            if (UriUtil.isNetworkUri(uri)) {
                i = 0;
            } else if (UriUtil.isLocalFileUri(uri)) {
                String path = uri.getPath();
                Map<String, String> map = MediaUtils.ADDITIONAL_ALLOWED_MIME_TYPES;
                int lastIndexOf = path.lastIndexOf(46);
                String str = null;
                String substring = (lastIndexOf < 0 || lastIndexOf == path.length() + (-1)) ? null : path.substring(lastIndexOf + 1);
                if (substring != null) {
                    String lowerCase = substring.toLowerCase(Locale.US);
                    str = MimeTypeMapWrapper.sExtensionToMimeTypeMap.get(lowerCase);
                    str = str == null ? MimeTypeMapWrapper.sMimeTypeMap.getMimeTypeFromExtension(lowerCase) : str;
                    if (str == null) {
                        str = MediaUtils.ADDITIONAL_ALLOWED_MIME_TYPES.get(lowerCase);
                    }
                }
                i = str != null && str.startsWith("video/") ? 2 : 3;
            } else if (UriUtil.isLocalContentUri(uri)) {
                i = 4;
            } else if ("asset".equals(UriUtil.getSchemeOrNull(uri))) {
                i = 5;
            } else if ("res".equals(UriUtil.getSchemeOrNull(uri))) {
                i = 6;
            } else if ("data".equals(UriUtil.getSchemeOrNull(uri))) {
                i = 7;
            } else if ("android.resource".equals(UriUtil.getSchemeOrNull(uri))) {
                i = 8;
            }
        }
        this.mSourceUriType = i;
        this.mProgressiveRenderingEnabled = imageRequestBuilder.mProgressiveRenderingEnabled;
        this.mLocalThumbnailPreviewsEnabled = imageRequestBuilder.mLocalThumbnailPreviewsEnabled;
        this.mImageDecodeOptions = imageRequestBuilder.mImageDecodeOptions;
        this.mResizeOptions = imageRequestBuilder.mResizeOptions;
        RotationOptions rotationOptions = imageRequestBuilder.mRotationOptions;
        this.mRotationOptions = rotationOptions == null ? RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE : rotationOptions;
        this.mBytesRange = imageRequestBuilder.mBytesRange;
        this.mRequestPriority = imageRequestBuilder.mRequestPriority;
        this.mLowestPermittedRequestLevel = imageRequestBuilder.mLowestPermittedRequestLevel;
        this.mIsDiskCacheEnabled = (!imageRequestBuilder.mDiskCacheEnabled || !UriUtil.isNetworkUri(imageRequestBuilder.mSourceUri)) ? false : z;
        this.mIsMemoryCacheEnabled = imageRequestBuilder.mMemoryCacheEnabled;
        this.mDecodePrefetches = imageRequestBuilder.mDecodePrefetches;
        this.mPostprocessor = imageRequestBuilder.mPostprocessor;
        this.mRequestListener = imageRequestBuilder.mRequestListener;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageRequest)) {
            return false;
        }
        ImageRequest imageRequest = (ImageRequest) obj;
        if (!R$dimen.equal(this.mSourceUri, imageRequest.mSourceUri) || !R$dimen.equal(this.mCacheChoice, imageRequest.mCacheChoice) || !R$dimen.equal(this.mSourceFile, imageRequest.mSourceFile) || !R$dimen.equal(this.mBytesRange, imageRequest.mBytesRange) || !R$dimen.equal(this.mImageDecodeOptions, imageRequest.mImageDecodeOptions) || !R$dimen.equal(this.mResizeOptions, imageRequest.mResizeOptions) || !R$dimen.equal(this.mRotationOptions, imageRequest.mRotationOptions)) {
            return false;
        }
        Postprocessor postprocessor = this.mPostprocessor;
        CacheKey cacheKey = null;
        CacheKey postprocessorCacheKey = postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null;
        Postprocessor postprocessor2 = imageRequest.mPostprocessor;
        if (postprocessor2 != null) {
            cacheKey = postprocessor2.getPostprocessorCacheKey();
        }
        return R$dimen.equal(postprocessorCacheKey, cacheKey);
    }

    public synchronized File getSourceFile() {
        if (this.mSourceFile == null) {
            this.mSourceFile = new File(this.mSourceUri.getPath());
        }
        return this.mSourceFile;
    }

    public int hashCode() {
        Postprocessor postprocessor = this.mPostprocessor;
        return Arrays.hashCode(new Object[]{this.mCacheChoice, this.mSourceUri, this.mSourceFile, this.mBytesRange, this.mImageDecodeOptions, this.mResizeOptions, this.mRotationOptions, postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null, null});
    }

    public String toString() {
        Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
        stringHelper.addHolder("uri", this.mSourceUri);
        stringHelper.addHolder("cacheChoice", this.mCacheChoice);
        stringHelper.addHolder("decodeOptions", this.mImageDecodeOptions);
        stringHelper.addHolder("postprocessor", this.mPostprocessor);
        stringHelper.addHolder("priority", this.mRequestPriority);
        stringHelper.addHolder("resizeOptions", this.mResizeOptions);
        stringHelper.addHolder("rotationOptions", this.mRotationOptions);
        stringHelper.addHolder("bytesRange", this.mBytesRange);
        stringHelper.addHolder("resizingAllowedOverride", null);
        return stringHelper.toString();
    }
}
