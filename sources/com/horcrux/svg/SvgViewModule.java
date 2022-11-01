package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Base64;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import java.io.ByteArrayOutputStream;
/* loaded from: classes.dex */
public class SvgViewModule extends ReactContextBaseJavaModule {

    /* renamed from: com.horcrux.svg.SvgViewModule$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int val$attempt;
        public final /* synthetic */ ReadableMap val$options;
        public final /* synthetic */ Callback val$successCallback;
        public final /* synthetic */ int val$tag;

        public AnonymousClass1(int i, ReadableMap readableMap, Callback callback, int i2) {
            this.val$tag = i;
            this.val$options = readableMap;
            this.val$successCallback = callback;
            this.val$attempt = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            SvgView svgViewByTag = SvgViewManager.getSvgViewByTag(this.val$tag);
            if (svgViewByTag == null) {
                SvgViewManager.runWhenViewIsAvailable(this.val$tag, new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SvgView svgViewByTag2 = SvgViewManager.getSvgViewByTag(AnonymousClass1.this.val$tag);
                        if (svgViewByTag2 != null) {
                            svgViewByTag2.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass1 r0 = AnonymousClass1.this;
                                    SvgViewModule.toDataURL(r0.val$tag, r0.val$options, r0.val$successCallback, r0.val$attempt + 1);
                                }
                            });
                        }
                    }
                });
            } else if (!svgViewByTag.mRendered) {
                svgViewByTag.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1 r0 = AnonymousClass1.this;
                        SvgViewModule.toDataURL(r0.val$tag, r0.val$options, r0.val$successCallback, r0.val$attempt + 1);
                    }
                });
            } else {
                ReadableMap readableMap = this.val$options;
                if (readableMap != null) {
                    Callback callback = this.val$successCallback;
                    Bitmap createBitmap = Bitmap.createBitmap(readableMap.getInt("width"), this.val$options.getInt("height"), Bitmap.Config.ARGB_8888);
                    svgViewByTag.clearChildCache();
                    svgViewByTag.drawChildren(new Canvas(createBitmap));
                    svgViewByTag.clearChildCache();
                    svgViewByTag.invalidate();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    createBitmap.recycle();
                    callback.invoke(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                    return;
                }
                Callback callback2 = this.val$successCallback;
                Bitmap createBitmap2 = Bitmap.createBitmap(svgViewByTag.getWidth(), svgViewByTag.getHeight(), Bitmap.Config.ARGB_8888);
                svgViewByTag.clearChildCache();
                svgViewByTag.drawChildren(new Canvas(createBitmap2));
                svgViewByTag.clearChildCache();
                svgViewByTag.invalidate();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                createBitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
                createBitmap2.recycle();
                callback2.invoke(Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 0));
            }
        }
    }

    public SvgViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void toDataURL(int i, ReadableMap readableMap, Callback callback, int i2) {
        UiThreadUtil.runOnUiThread(new AnonymousClass1(i, readableMap, callback, i2));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSVGSvgViewManager";
    }

    @ReactMethod
    public void toDataURL(int i, ReadableMap readableMap, Callback callback) {
        toDataURL(i, readableMap, callback, 0);
    }
}
