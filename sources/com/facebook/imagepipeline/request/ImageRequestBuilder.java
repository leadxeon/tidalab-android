package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Objects;
/* loaded from: classes.dex */
public class ImageRequestBuilder {
    public RequestListener mRequestListener;
    public Uri mSourceUri = null;
    public ImageRequest.RequestLevel mLowestPermittedRequestLevel = ImageRequest.RequestLevel.FULL_FETCH;
    public ResizeOptions mResizeOptions = null;
    public RotationOptions mRotationOptions = null;
    public ImageDecodeOptions mImageDecodeOptions = ImageDecodeOptions.DEFAULTS;
    public ImageRequest.CacheChoice mCacheChoice = ImageRequest.CacheChoice.DEFAULT;
    public boolean mProgressiveRenderingEnabled = false;
    public boolean mLocalThumbnailPreviewsEnabled = false;
    public Priority mRequestPriority = Priority.HIGH;
    public Postprocessor mPostprocessor = null;
    public boolean mDiskCacheEnabled = true;
    public boolean mMemoryCacheEnabled = true;
    public Boolean mDecodePrefetches = null;
    public BytesRange mBytesRange = null;

    /* loaded from: classes.dex */
    public static class BuilderException extends RuntimeException {
        public BuilderException(String str) {
            super(GeneratedOutlineSupport.outline8("Invalid request builder: ", str));
        }
    }

    public static ImageRequestBuilder newBuilderWithSource(Uri uri) {
        ImageRequestBuilder imageRequestBuilder = new ImageRequestBuilder();
        Objects.requireNonNull(uri);
        imageRequestBuilder.mSourceUri = uri;
        return imageRequestBuilder;
    }

    public ImageRequest build() {
        Uri uri = this.mSourceUri;
        if (uri != null) {
            if ("res".equals(UriUtil.getSchemeOrNull(uri))) {
                if (!this.mSourceUri.isAbsolute()) {
                    throw new BuilderException("Resource URI path must be absolute.");
                } else if (!this.mSourceUri.getPath().isEmpty()) {
                    try {
                        Integer.parseInt(this.mSourceUri.getPath().substring(1));
                    } catch (NumberFormatException unused) {
                        throw new BuilderException("Resource URI path must be a resource id.");
                    }
                } else {
                    throw new BuilderException("Resource URI must not be empty");
                }
            }
            if (!"asset".equals(UriUtil.getSchemeOrNull(this.mSourceUri)) || this.mSourceUri.isAbsolute()) {
                return new ImageRequest(this);
            }
            throw new BuilderException("Asset URI path must be absolute.");
        }
        throw new BuilderException("Source must be set!");
    }
}
