package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.HttpUrl;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class ImageView extends RenderableView {
    public String mAlign;
    public SVGLength mH;
    public int mImageHeight;
    public int mImageWidth;
    public final AtomicBoolean mLoading = new AtomicBoolean(false);
    public int mMeetOrSlice;
    public SVGLength mW;
    public SVGLength mX;
    public SVGLength mY;
    public String uriString;

    /* renamed from: com.horcrux.svg.ImageView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends BaseBitmapDataSubscriber {
        public AnonymousClass1() {
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            String str;
            ImageView.this.mLoading.set(false);
            Throwable failureCause = dataSource.getFailureCause();
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(FLog.formatString("RNSVG: fetchDecodedImage failed!", new Object[0]), '\n');
            if (failureCause == null) {
                str = HttpUrl.FRAGMENT_ENCODE_SET;
            } else {
                StringWriter stringWriter = new StringWriter();
                failureCause.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            }
            outline14.append(str);
            Log.println(5, "unknown:ReactNative", outline14.toString());
        }
    }

    public ImageView(ReactContext reactContext) {
        super(reactContext);
    }

    public final void doRender(Canvas canvas, Paint paint, Bitmap bitmap, float f) {
        if (this.mImageWidth == 0 || this.mImageHeight == 0) {
            this.mImageWidth = bitmap.getWidth();
            this.mImageHeight = bitmap.getHeight();
        }
        RectF rect = getRect();
        RectF rectF = new RectF(0.0f, 0.0f, this.mImageWidth, this.mImageHeight);
        PathParser.getTransform(rectF, rect, this.mAlign, this.mMeetOrSlice).mapRect(rectF);
        canvas.clipPath(getPath(canvas, paint));
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        this.mCTM.mapRect(rectF);
        setClientRect(rectF);
    }

    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        Uri uri;
        Bitmap underlyingBitmap;
        if (!this.mLoading.get()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ReactContext reactContext = this.mContext;
            String str = this.uriString;
            try {
                uri = Uri.parse(str);
                if (uri.getScheme() == null) {
                    uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactContext, str);
                }
            } catch (Exception unused) {
                uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactContext, str);
            }
            R$dimen.assertNotNull(uri);
            ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri).build();
            Objects.requireNonNull(imagePipeline);
            CloseableReference<CloseableImage> closeableReference = imagePipeline.mBitmapMemoryCache.get(((DefaultCacheKeyFactory) imagePipeline.mCacheKeyFactory).getBitmapCacheKey(build, null));
            try {
                boolean isValid = CloseableReference.isValid(closeableReference);
                if (closeableReference != null) {
                }
                if (isValid) {
                    float f2 = f * this.mOpacity;
                    DataSource<CloseableReference<CloseableImage>> fetchDecodedImage = imagePipeline.fetchDecodedImage(build, this.mContext, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE, null);
                    try {
                        try {
                            closeableReference = fetchDecodedImage.getResult();
                            try {
                                if (closeableReference != null) {
                                    try {
                                        CloseableImage closeableImage = closeableReference.get();
                                        if ((closeableImage instanceof CloseableBitmap) && (underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap()) != null) {
                                            doRender(canvas, paint, underlyingBitmap, f2);
                                        }
                                    } catch (Exception e) {
                                        throw new IllegalStateException(e);
                                    }
                                }
                            } finally {
                                closeableReference.close();
                            }
                        } finally {
                            fetchDecodedImage.close();
                        }
                    } catch (Exception e2) {
                        throw new IllegalStateException(e2);
                    }
                } else {
                    this.mLoading.set(true);
                    DataSource<CloseableReference<CloseableImage>> fetchDecodedImage2 = imagePipeline.fetchDecodedImage(build, this.mContext, ImageRequest.RequestLevel.FULL_FETCH, null);
                    AnonymousClass1 r7 = new AnonymousClass1();
                    if (UiThreadImmediateExecutorService.sInstance == null) {
                        UiThreadImmediateExecutorService.sInstance = new UiThreadImmediateExecutorService();
                    }
                    ((AbstractDataSource) fetchDecodedImage2).subscribe(r7, UiThreadImmediateExecutorService.sInstance);
                }
            } catch (Throwable th) {
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference != null) {
                }
                throw th;
            }
        }
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        ((VirtualView) this).mPath = path;
        path.addRect(getRect(), Path.Direction.CW);
        return ((VirtualView) this).mPath;
    }

    public final RectF getRect() {
        double relativeOnWidth = relativeOnWidth(this.mX);
        double relativeOnHeight = relativeOnHeight(this.mY);
        double relativeOnWidth2 = relativeOnWidth(this.mW);
        double relativeOnHeight2 = relativeOnHeight(this.mH);
        if (relativeOnWidth2 == 0.0d) {
            relativeOnWidth2 = this.mImageWidth * this.mScale;
        }
        if (relativeOnHeight2 == 0.0d) {
            relativeOnHeight2 = this.mImageHeight * this.mScale;
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2));
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @ReactProp(name = "src")
    public void setSrc(ReadableMap readableMap) {
        if (readableMap != null) {
            String string = readableMap.getString("uri");
            this.uriString = string;
            if (string != null && !string.isEmpty()) {
                if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
                    this.mImageWidth = 0;
                    this.mImageHeight = 0;
                } else {
                    this.mImageWidth = readableMap.getInt("width");
                    this.mImageHeight = readableMap.getInt("height");
                }
                if (Uri.parse(this.uriString).getScheme() == null) {
                    ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(this.mContext, this.uriString);
                }
            }
        }
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
        invalidate();
    }
}
