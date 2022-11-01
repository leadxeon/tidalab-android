package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.text.TextInlineImageSpan;
/* loaded from: classes.dex */
public class FrescoBasedReactTextInlineImageSpan extends TextInlineImageSpan {
    public final Object mCallerContext;
    public Drawable mDrawable;
    public final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    public final DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    public ReadableMap mHeaders;
    public int mHeight;
    public TextView mTextView;
    public int mTintColor;
    public Uri mUri;
    public int mWidth;

    public FrescoBasedReactTextInlineImageSpan(Resources resources, int i, int i2, int i3, Uri uri, ReadableMap readableMap, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.mDraweeHolder = new DraweeHolder<>(new GenericDraweeHierarchy(new GenericDraweeHierarchyBuilder(resources)));
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
        this.mTintColor = i3;
        this.mUri = uri == null ? Uri.EMPTY : uri;
        this.mHeaders = readableMap;
        this.mWidth = (int) PixelUtil.toPixelFromDIP(i2);
        this.mHeight = (int) PixelUtil.toPixelFromDIP(i);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [REQUEST, com.facebook.react.modules.fresco.ReactNetworkImageRequest] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // android.text.style.ReplacementSpan
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r1, java.lang.CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, android.graphics.Paint r9) {
        /*
            r0 = this;
            android.graphics.drawable.Drawable r2 = r0.mDrawable
            if (r2 != 0) goto L_0x0052
            android.net.Uri r2 = r0.mUri
            com.facebook.imagepipeline.request.ImageRequestBuilder r2 = com.facebook.imagepipeline.request.ImageRequestBuilder.newBuilderWithSource(r2)
            com.facebook.react.bridge.ReadableMap r3 = r0.mHeaders
            com.facebook.react.modules.fresco.ReactNetworkImageRequest r4 = new com.facebook.react.modules.fresco.ReactNetworkImageRequest
            r4.<init>(r2, r3)
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.mDraweeControllerBuilder
            r2.init()
            com.facebook.drawee.view.DraweeHolder<com.facebook.drawee.generic.GenericDraweeHierarchy> r3 = r0.mDraweeHolder
            com.facebook.drawee.interfaces.DraweeController r3 = r3.mController
            r2.mOldController = r3
            java.lang.Object r3 = r0.mCallerContext
            r2.mCallerContext = r3
            r2.mImageRequest = r4
            com.facebook.drawee.controller.AbstractDraweeController r2 = r2.build()
            com.facebook.drawee.view.DraweeHolder<com.facebook.drawee.generic.GenericDraweeHierarchy> r3 = r0.mDraweeHolder
            r3.setController(r2)
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r2 = r0.mDraweeControllerBuilder
            r2.init()
            com.facebook.drawee.view.DraweeHolder<com.facebook.drawee.generic.GenericDraweeHierarchy> r2 = r0.mDraweeHolder
            android.graphics.drawable.Drawable r2 = r2.getTopLevelDrawable()
            r0.mDrawable = r2
            int r3 = r0.mWidth
            int r4 = r0.mHeight
            r6 = 0
            r2.setBounds(r6, r6, r3, r4)
            int r2 = r0.mTintColor
            if (r2 == 0) goto L_0x004b
            android.graphics.drawable.Drawable r3 = r0.mDrawable
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_IN
            r3.setColorFilter(r2, r4)
        L_0x004b:
            android.graphics.drawable.Drawable r2 = r0.mDrawable
            android.widget.TextView r3 = r0.mTextView
            r2.setCallback(r3)
        L_0x0052:
            r1.save()
            float r2 = r9.descent()
            float r3 = r9.ascent()
            float r2 = r2 - r3
            int r2 = (int) r2
            float r3 = r9.descent()
            int r3 = (int) r3
            int r7 = r7 + r3
            int r2 = r2 / 2
            int r7 = r7 - r2
            android.graphics.drawable.Drawable r2 = r0.mDrawable
            android.graphics.Rect r2 = r2.getBounds()
            int r2 = r2.bottom
            android.graphics.drawable.Drawable r3 = r0.mDrawable
            android.graphics.Rect r3 = r3.getBounds()
            int r3 = r3.top
            int r2 = r2 - r3
            int r2 = r2 / 2
            int r7 = r7 - r2
            float r2 = (float) r7
            r1.translate(r5, r2)
            android.graphics.drawable.Drawable r2 = r0.mDrawable
            r2.draw(r1)
            r1.restore()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageSpan.draw(android.graphics.Canvas, java.lang.CharSequence, int, int, float, int, int, int, android.graphics.Paint):void");
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public Drawable getDrawable() {
        return this.mDrawable;
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public int getHeight() {
        return this.mHeight;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            int i3 = -this.mHeight;
            fontMetricsInt.ascent = i3;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = i3;
            fontMetricsInt.bottom = 0;
        }
        return this.mWidth;
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onAttachedToWindow() {
        this.mDraweeHolder.onAttach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onDetachedFromWindow() {
        this.mDraweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onFinishTemporaryDetach() {
        this.mDraweeHolder.onAttach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onStartTemporaryDetach() {
        this.mDraweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void setTextView(TextView textView) {
        this.mTextView = textView;
    }
}
