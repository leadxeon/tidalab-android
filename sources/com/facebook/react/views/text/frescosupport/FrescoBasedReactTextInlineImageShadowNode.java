package com.facebook.react.views.text.frescosupport;

import android.net.Uri;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.TextInlineImageSpan;
/* loaded from: classes.dex */
public class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {
    public final Object mCallerContext;
    public final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    public ReadableMap mHeaders;
    public Uri mUri;
    public float mWidth = Float.NaN;
    public float mHeight = Float.NaN;
    public int mTintColor = 0;

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    @Override // com.facebook.react.views.text.ReactTextInlineImageShadowNode
    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(getThemedContext().getResources(), (int) Math.ceil(this.mHeight), (int) Math.ceil(this.mWidth), this.mTintColor, this.mUri, this.mHeaders, this.mDraweeControllerBuilder, this.mCallerContext);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setHeight(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mHeight = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based height");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r1.getScheme() == null) goto L_0x0023;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006f  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "src")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSource(com.facebook.react.bridge.ReadableArray r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x0016
            int r1 = r4.size()
            if (r1 != 0) goto L_0x000a
            goto L_0x0016
        L_0x000a:
            r1 = 0
            com.facebook.react.bridge.ReadableMap r4 = r4.getMap(r1)
            java.lang.String r1 = "uri"
            java.lang.String r4 = r4.getString(r1)
            goto L_0x0017
        L_0x0016:
            r4 = r0
        L_0x0017:
            if (r4 == 0) goto L_0x006b
            android.net.Uri r1 = android.net.Uri.parse(r4)     // Catch: Exception -> 0x0023
            java.lang.String r2 = r1.getScheme()     // Catch: Exception -> 0x0025
            if (r2 != 0) goto L_0x0026
        L_0x0023:
            r1 = r0
            goto L_0x0026
        L_0x0025:
        L_0x0026:
            if (r1 != 0) goto L_0x006a
            com.facebook.react.uimanager.ThemedReactContext r1 = r3.getThemedContext()
            boolean r2 = r4.isEmpty()
            if (r2 == 0) goto L_0x0033
            goto L_0x006b
        L_0x0033:
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r4 = r4.toLowerCase(r0)
            java.lang.String r0 = "-"
            java.lang.String r2 = "_"
            java.lang.String r4 = r4.replace(r0, r2)
            android.content.res.Resources r0 = r1.getResources()
            java.lang.String r1 = r1.getPackageName()
            java.lang.String r2 = "drawable"
            int r4 = r0.getIdentifier(r4, r2, r1)
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            java.lang.String r1 = "res"
            android.net.Uri$Builder r0 = r0.scheme(r1)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            android.net.Uri$Builder r4 = r0.path(r4)
            android.net.Uri r4 = r4.build()
            r0 = r4
            goto L_0x006b
        L_0x006a:
            r0 = r1
        L_0x006b:
            android.net.Uri r4 = r3.mUri
            if (r0 == r4) goto L_0x0072
            r3.markUpdated()
        L_0x0072:
            r3.mUri = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageShadowNode.setSource(com.facebook.react.bridge.ReadableArray):void");
    }

    @ReactProp(name = "tintColor")
    public void setTintColor(int i) {
        this.mTintColor = i;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setWidth(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mWidth = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based width");
    }
}
