package com.facebook.react.modules.camera;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64OutputStream;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@ReactModule(name = ImageStoreManager.NAME)
/* loaded from: classes.dex */
public class ImageStoreManager extends ReactContextBaseJavaModule {
    private static final int BUFFER_SIZE = 8192;
    public static final String NAME = "ImageStoreManager";

    /* loaded from: classes.dex */
    public class GetBase64Task extends GuardedAsyncTask<Void, Void> {
        public final Callback mError;
        public final Callback mSuccess;
        public final String mUri;

        public GetBase64Task(ReactContext reactContext, String str, Callback callback, Callback callback2, AnonymousClass1 r6) {
            super(reactContext);
            this.mUri = str;
            this.mSuccess = callback;
            this.mError = callback2;
        }

        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void[] voidArr) {
            try {
                InputStream openInputStream = ImageStoreManager.this.getReactApplicationContext().getContentResolver().openInputStream(Uri.parse(this.mUri));
                try {
                    this.mSuccess.invoke(ImageStoreManager.this.convertInputStreamToBase64OutputStream(openInputStream));
                } catch (IOException e) {
                    this.mError.invoke(e.getMessage());
                }
                ImageStoreManager.closeQuietly(openInputStream);
            } catch (FileNotFoundException e2) {
                this.mError.invoke(e2.getMessage());
            }
        }
    }

    public ImageStoreManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Finally extract failed */
    public String convertInputStreamToBase64OutputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 2);
        byte[] bArr = new byte[BUFFER_SIZE];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    base64OutputStream.write(bArr, 0, read);
                } else {
                    closeQuietly(base64OutputStream);
                    return byteArrayOutputStream.toString();
                }
            } catch (Throwable th) {
                closeQuietly(base64OutputStream);
                throw th;
            }
        }
    }

    @ReactMethod
    public void getBase64ForTag(String str, Callback callback, Callback callback2) {
        new GetBase64Task(getReactApplicationContext(), str, callback, callback2, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }
}
