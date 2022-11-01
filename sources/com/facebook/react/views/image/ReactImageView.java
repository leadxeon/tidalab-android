package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class ReactImageView extends GenericDraweeView {
    public int mBackgroundColor;
    public RoundedColorDrawable mBackgroundImageDrawable;
    public int mBorderColor;
    public float[] mBorderCornerRadii;
    public float mBorderRadius;
    public float mBorderWidth;
    public ImageSource mCachedImageSource;
    public final Object mCallerContext;
    public ControllerListener mControllerForTesting;
    public ControllerListener mControllerListener;
    public Drawable mDefaultImageDrawable;
    public final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    public int mFadeDurationMs;
    public GlobalImageLoadListener mGlobalImageLoadListener;
    public ReadableMap mHeaders;
    public ImageSource mImageSource;
    public boolean mIsDirty;
    public IterativeBoxBlurPostProcessor mIterativeBoxBlurPostProcessor;
    public Drawable mLoadingImageDrawable;
    public int mOverlayColor;
    public boolean mProgressiveRenderingEnabled;
    public ImageResizeMethod mResizeMethod;
    public final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    public ScalingUtils$ScaleType mScaleType;
    public final List<ImageSource> mSources;
    public Shader.TileMode mTileMode;
    public final TilePostprocessor mTilePostprocessor;
    public static float[] sComputedCornerRadii = new float[4];
    public static final Matrix sMatrix = new Matrix();
    public static final Matrix sInverse = new Matrix();
    public static final Matrix sTileMatrix = new Matrix();

    /* loaded from: classes.dex */
    public class RoundedCornerPostprocessor extends BasePostprocessor {
        public RoundedCornerPostprocessor(AnonymousClass1 r2) {
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor
        public void process(Bitmap bitmap, Bitmap bitmap2) {
            ReactImageView reactImageView = ReactImageView.this;
            float[] fArr = ReactImageView.sComputedCornerRadii;
            reactImageView.cornerRadii(ReactImageView.sComputedCornerRadii);
            bitmap.setHasAlpha(true);
            if (!R$style.floatsEqual(ReactImageView.sComputedCornerRadii[0], 0.0f) || !R$style.floatsEqual(ReactImageView.sComputedCornerRadii[1], 0.0f) || !R$style.floatsEqual(ReactImageView.sComputedCornerRadii[2], 0.0f) || !R$style.floatsEqual(ReactImageView.sComputedCornerRadii[3], 0.0f)) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(bitmap2, tileMode, tileMode));
                Canvas canvas = new Canvas(bitmap);
                float[] fArr2 = ReactImageView.sComputedCornerRadii;
                ScalingUtils$ScaleType scalingUtils$ScaleType = ReactImageView.this.mScaleType;
                Matrix matrix = ReactImageView.sMatrix;
                ((ScalingUtils$AbstractScaleType) scalingUtils$ScaleType).getTransform(matrix, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), bitmap2.getWidth(), bitmap2.getHeight(), 0.0f, 0.0f);
                Matrix matrix2 = ReactImageView.sInverse;
                matrix.invert(matrix2);
                float[] fArr3 = {matrix2.mapRadius(fArr2[0]), fArr3[0], matrix2.mapRadius(fArr2[1]), fArr3[2], matrix2.mapRadius(fArr2[2]), fArr3[4], matrix2.mapRadius(fArr2[3]), fArr3[6]};
                Path path = new Path();
                path.addRoundRect(new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight()), fArr3, Path.Direction.CW);
                canvas.drawPath(path, paint);
                return;
            }
            super.process(bitmap, bitmap2);
        }
    }

    /* loaded from: classes.dex */
    public class TilePostprocessor extends BasePostprocessor {
        public TilePostprocessor(AnonymousClass1 r2) {
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
        public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ScalingUtils$ScaleType scalingUtils$ScaleType = ReactImageView.this.mScaleType;
            Matrix matrix = ReactImageView.sTileMatrix;
            ((ScalingUtils$AbstractScaleType) scalingUtils$ScaleType).getTransform(matrix, rect, bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Shader.TileMode tileMode = ReactImageView.this.mTileMode;
            BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            int width = ReactImageView.this.getWidth();
            int height = ReactImageView.this.getHeight();
            Objects.requireNonNull(platformBitmapFactory);
            CloseableReference<Bitmap> createBitmapInternal = platformBitmapFactory.createBitmapInternal(width, height, Bitmap.Config.ARGB_8888);
            try {
                new Canvas(createBitmapInternal.get()).drawRect(rect, paint);
                CloseableReference<Bitmap> clone = createBitmapInternal.clone();
                createBitmapInternal.close();
                return clone;
            } catch (Throwable th) {
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (createBitmapInternal != null) {
                    createBitmapInternal.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ReactImageView(android.content.Context r5, com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6, com.facebook.react.views.image.GlobalImageLoadListener r7, java.lang.Object r8) {
        /*
            r4 = this;
            com.facebook.drawee.generic.GenericDraweeHierarchyBuilder r0 = new com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
            android.content.res.Resources r1 = r5.getResources()
            r0.<init>(r1)
            com.facebook.drawee.generic.RoundingParams r1 = new com.facebook.drawee.generic.RoundingParams
            r1.<init>()
            float[] r2 = r1.mCornersRadii
            if (r2 != 0) goto L_0x0018
            r2 = 8
            float[] r2 = new float[r2]
            r1.mCornersRadii = r2
        L_0x0018:
            float[] r2 = r1.mCornersRadii
            r3 = 0
            java.util.Arrays.fill(r2, r3)
            r0.mRoundingParams = r1
            com.facebook.drawee.generic.GenericDraweeHierarchy r1 = new com.facebook.drawee.generic.GenericDraweeHierarchy
            r1.<init>(r0)
            r4.<init>(r5, r1)
            com.facebook.react.views.image.ImageResizeMethod r5 = com.facebook.react.views.image.ImageResizeMethod.AUTO
            r4.mResizeMethod = r5
            r5 = 0
            r4.mBackgroundColor = r5
            r5 = 2143289344(0x7fc00000, float:NaN)
            r4.mBorderRadius = r5
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.CLAMP
            r4.mTileMode = r5
            r5 = -1
            r4.mFadeDurationMs = r5
            int r5 = com.facebook.drawee.drawable.ScalingUtils$ScaleType.$r8$clinit
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r5 = com.facebook.drawee.drawable.ScalingUtils$ScaleTypeCenterCrop.INSTANCE
            r4.mScaleType = r5
            r4.mDraweeControllerBuilder = r6
            com.facebook.react.views.image.ReactImageView$RoundedCornerPostprocessor r5 = new com.facebook.react.views.image.ReactImageView$RoundedCornerPostprocessor
            r6 = 0
            r5.<init>(r6)
            r4.mRoundedCornerPostprocessor = r5
            com.facebook.react.views.image.ReactImageView$TilePostprocessor r5 = new com.facebook.react.views.image.ReactImageView$TilePostprocessor
            r5.<init>(r6)
            r4.mTilePostprocessor = r5
            r4.mGlobalImageLoadListener = r7
            r4.mCallerContext = r8
            java.util.LinkedList r5 = new java.util.LinkedList
            r5.<init>()
            r4.mSources = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.<init>(android.content.Context, com.facebook.drawee.controller.AbstractDraweeControllerBuilder, com.facebook.react.views.image.GlobalImageLoadListener, java.lang.Object):void");
    }

    public final void cornerRadii(float[] fArr) {
        float f = !R$style.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
        float[] fArr2 = this.mBorderCornerRadii;
        fArr[0] = (fArr2 == null || R$style.isUndefined(fArr2[0])) ? f : this.mBorderCornerRadii[0];
        float[] fArr3 = this.mBorderCornerRadii;
        fArr[1] = (fArr3 == null || R$style.isUndefined(fArr3[1])) ? f : this.mBorderCornerRadii[1];
        float[] fArr4 = this.mBorderCornerRadii;
        fArr[2] = (fArr4 == null || R$style.isUndefined(fArr4[2])) ? f : this.mBorderCornerRadii[2];
        float[] fArr5 = this.mBorderCornerRadii;
        if (fArr5 != null && !R$style.isUndefined(fArr5[3])) {
            f = this.mBorderCornerRadii[3];
        }
        fArr[3] = f;
    }

    public final boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final boolean isTiled() {
        return this.mTileMode != Shader.TileMode.CLAMP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02ae A[EDGE_INSN: B:189:0x02ae->B:139:0x02ae ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0106 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0107  */
    /* JADX WARN: Type inference failed for: r1v13, types: [REQUEST, com.facebook.imagepipeline.request.ImageRequest] */
    /* JADX WARN: Type inference failed for: r7v6, types: [REQUEST, com.facebook.react.modules.fresco.ReactNetworkImageRequest] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void maybeUpdateView() {
        /*
            Method dump skipped, instructions count: 935
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.maybeUpdateView():void");
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            this.mIsDirty = this.mIsDirty || hasMultipleSources() || isTiled();
            maybeUpdateView();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (this.mBackgroundColor != i) {
            this.mBackgroundColor = i;
            this.mBackgroundImageDrawable = new RoundedColorDrawable(i);
            this.mIsDirty = true;
        }
    }

    public void setBlurRadius(float f) {
        int pixelFromDIP = (int) PixelUtil.toPixelFromDIP(f);
        if (pixelFromDIP == 0) {
            this.mIterativeBoxBlurPostProcessor = null;
        } else {
            this.mIterativeBoxBlurPostProcessor = new IterativeBoxBlurPostProcessor(pixelFromDIP);
        }
        this.mIsDirty = true;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        this.mIsDirty = true;
    }

    public void setBorderRadius(float f) {
        if (!R$style.floatsEqual(this.mBorderRadius, f)) {
            this.mBorderRadius = f;
            this.mIsDirty = true;
        }
    }

    public void setBorderWidth(float f) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(f);
        this.mIsDirty = true;
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.mControllerForTesting = controllerListener;
        this.mIsDirty = true;
        maybeUpdateView();
    }

    public void setDefaultSource(String str) {
        ResourceDrawableIdHelper instance = ResourceDrawableIdHelper.getInstance();
        Context context = getContext();
        int resourceDrawableId = instance.getResourceDrawableId(context, str);
        this.mDefaultImageDrawable = resourceDrawableId > 0 ? context.getResources().getDrawable(resourceDrawableId) : null;
        this.mIsDirty = true;
    }

    public void setFadeDuration(int i) {
        this.mFadeDurationMs = i;
    }

    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    public void setLoadingIndicatorSource(String str) {
        ResourceDrawableIdHelper instance = ResourceDrawableIdHelper.getInstance();
        Context context = getContext();
        int resourceDrawableId = instance.getResourceDrawableId(context, str);
        AutoRotateDrawable autoRotateDrawable = null;
        Drawable drawable = resourceDrawableId > 0 ? context.getResources().getDrawable(resourceDrawableId) : null;
        if (drawable != null) {
            autoRotateDrawable = new AutoRotateDrawable(drawable, RNCWebViewManager.COMMAND_CLEAR_FORM_DATA);
        }
        this.mLoadingImageDrawable = autoRotateDrawable;
        this.mIsDirty = true;
    }

    public void setOverlayColor(int i) {
        this.mOverlayColor = i;
        this.mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
    }

    public void setResizeMethod(ImageResizeMethod imageResizeMethod) {
        this.mResizeMethod = imageResizeMethod;
        this.mIsDirty = true;
    }

    public void setScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mScaleType = scalingUtils$ScaleType;
        this.mIsDirty = true;
    }

    public void setShouldNotifyLoadEvents(boolean z) {
        if (!z) {
            this.mControllerListener = null;
        } else {
            final EventDispatcher eventDispatcher = ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.mControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.facebook.react.views.image.ReactImageView.1
                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFailure(String str, Throwable th) {
                    eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 1, true, th.getMessage()));
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFinalImageSet(String str, Object obj, Animatable animatable) {
                    ImageInfo imageInfo = (ImageInfo) obj;
                    if (imageInfo != null) {
                        eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 2, ReactImageView.this.mImageSource.mSource, imageInfo.getWidth(), imageInfo.getHeight()));
                        eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 3));
                    }
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onSubmit(String str, Object obj) {
                    eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 4));
                }
            };
        }
        this.mIsDirty = true;
    }

    public void setSource(ReadableArray readableArray) {
        this.mSources.clear();
        if (readableArray == null || readableArray.size() == 0) {
            this.mSources.add(new ImageSource(getContext(), "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="));
        } else {
            if (readableArray.size() == 1) {
                ImageSource imageSource = new ImageSource(getContext(), readableArray.getMap(0).getString("uri"));
                this.mSources.add(imageSource);
                Uri.EMPTY.equals(imageSource.getUri());
            } else {
                for (int i = 0; i < readableArray.size(); i++) {
                    ReadableMap map = readableArray.getMap(i);
                    ImageSource imageSource2 = new ImageSource(getContext(), map.getString("uri"), map.getDouble("width"), map.getDouble("height"));
                    this.mSources.add(imageSource2);
                    Uri.EMPTY.equals(imageSource2.getUri());
                }
            }
        }
        this.mIsDirty = true;
    }

    public void setTileMode(Shader.TileMode tileMode) {
        this.mTileMode = tileMode;
        this.mIsDirty = true;
    }
}
