package com.facebook.react.modules.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.facebook.react.bridge.ContextBaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import okhttp3.HttpUrl;
@ReactModule(name = ClipboardModule.NAME)
/* loaded from: classes.dex */
public class ClipboardModule extends ContextBaseJavaModule {
    public static final String NAME = "Clipboard";

    public ClipboardModule(Context context) {
        super(context);
    }

    private ClipboardManager getClipboardService() {
        Context context = getContext();
        getContext();
        return (ClipboardManager) context.getSystemService("clipboard");
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getString(Promise promise) {
        try {
            ClipboardManager clipboardService = getClipboardService();
            ClipData primaryClip = clipboardService.getPrimaryClip();
            if (primaryClip == null || primaryClip.getItemCount() < 1) {
                promise.resolve(HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                ClipData.Item itemAt = clipboardService.getPrimaryClip().getItemAt(0);
                promise.resolve(HttpUrl.FRAGMENT_ENCODE_SET + ((Object) itemAt.getText()));
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setString(String str) {
        getClipboardService().setPrimaryClip(ClipData.newPlainText(null, str));
    }
}
