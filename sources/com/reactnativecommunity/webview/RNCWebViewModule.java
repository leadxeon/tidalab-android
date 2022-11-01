package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
@ReactModule(name = "RNCWebView")
/* loaded from: classes.dex */
public class RNCWebViewModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int FILE_DOWNLOAD_PERMISSION_REQUEST = 1;
    public static final String MODULE_NAME = "RNCWebView";
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    public static final ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock = new ShouldOverrideUrlLoadingLock();
    private DownloadManager.Request downloadRequest;
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private File outputImage;
    private File outputVideo;
    private PermissionListener webviewFileDownloaderPermissionListener = new PermissionListener() { // from class: com.reactnativecommunity.webview.RNCWebViewModule.1
        @Override // com.facebook.react.modules.core.PermissionListener
        public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (i != 1) {
                return false;
            }
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(RNCWebViewModule.this.getCurrentActivity().getApplicationContext(), "Cannot download files as permission was denied. Please provide permission to write to storage, in order to download files.", 1).show();
            } else if (RNCWebViewModule.this.downloadRequest != null) {
                RNCWebViewModule.this.downloadFile();
            }
            return true;
        }
    };

    /* loaded from: classes.dex */
    public enum MimeType {
        DEFAULT("*/*"),
        IMAGE("image"),
        VIDEO("video");
        
        public final String value;

        MimeType(String str) {
            this.value = str;
        }
    }

    /* loaded from: classes.dex */
    public static class ShouldOverrideUrlLoadingLock {
        public int nextLockIdentifier = 1;
        public final HashMap<Integer, AtomicReference<ShouldOverrideCallbackState>> shouldOverrideLocks = new HashMap<>();

        /* loaded from: classes.dex */
        public enum ShouldOverrideCallbackState {
            UNDECIDED,
            SHOULD_OVERRIDE,
            DO_NOT_OVERRIDE
        }

        public synchronized void removeLock(Integer num) {
            this.shouldOverrideLocks.remove(num);
        }
    }

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    private Boolean acceptsImages(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", HttpUrl.FRAGMENT_ENCODE_SET));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains("image"));
    }

    private Boolean acceptsVideo(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return Boolean.FALSE;
        }
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", HttpUrl.FRAGMENT_ENCODE_SET));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains("video"));
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (noAcceptTypesSet(strArr).booleanValue()) {
            return new String[]{"*/*"};
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str.matches("\\.\\w+")) {
                String mimeTypeFromExtension = getMimeTypeFromExtension(str.replace(".", HttpUrl.FRAGMENT_ENCODE_SET));
                if (mimeTypeFromExtension != null) {
                    strArr2[i] = mimeTypeFromExtension;
                } else {
                    strArr2[i] = str;
                }
            } else {
                strArr2[i] = str;
            }
        }
        return strArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.File getCapturedFile(com.reactnativecommunity.webview.RNCWebViewModule.MimeType r7) throws java.io.IOException {
        /*
            r6 = this;
            int r7 = r7.ordinal()
            r0 = 1
            java.lang.String r1 = ""
            if (r7 == r0) goto L_0x0016
            r0 = 2
            if (r7 == r0) goto L_0x000f
            r7 = r1
            r0 = r7
            goto L_0x0022
        L_0x000f:
            java.lang.String r7 = android.os.Environment.DIRECTORY_MOVIES
            java.lang.String r0 = "video-"
            java.lang.String r1 = ".mp4"
            goto L_0x001c
        L_0x0016:
            java.lang.String r7 = android.os.Environment.DIRECTORY_PICTURES
            java.lang.String r0 = "image-"
            java.lang.String r1 = ".jpg"
        L_0x001c:
            r5 = r1
            r1 = r7
            r7 = r5
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0022:
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r1)
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r2.append(r3)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r3 >= r4) goto L_0x0048
            java.io.File r7 = android.os.Environment.getExternalStoragePublicDirectory(r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r7, r2)
            goto L_0x0055
        L_0x0048:
            com.facebook.react.bridge.ReactApplicationContext r0 = r6.getReactApplicationContext()
            r2 = 0
            java.io.File r0 = r0.getExternalFilesDir(r2)
            java.io.File r0 = java.io.File.createTempFile(r1, r7, r0)
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewModule.getCapturedFile(com.reactnativecommunity.webview.RNCWebViewModule$MimeType):java.io.File");
    }

    private Intent getFileChooserIntent(String str) {
        String str2 = str.isEmpty() ? "*/*" : str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", HttpUrl.FRAGMENT_ENCODE_SET));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    private Uri getOutputUri(File file) {
        if (Build.VERSION.SDK_INT < 23) {
            return Uri.fromFile(file);
        }
        String packageName = getReactApplicationContext().getPackageName();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        return FileProvider.getPathStrategy(reactApplicationContext, packageName + ".fileprovider").getUriForFile(file);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }

    private Intent getPhotoIntent() {
        Intent intent;
        Exception e;
        Uri outputUri;
        try {
            File capturedFile = getCapturedFile(MimeType.IMAGE);
            this.outputImage = capturedFile;
            outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
        } catch (IOException | IllegalArgumentException e2) {
            e = e2;
            intent = null;
        }
        try {
            intent.putExtra("output", outputUri);
        } catch (IOException e3) {
            e = e3;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        } catch (IllegalArgumentException e4) {
            e = e4;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    private Uri[] getSelectedFiles(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = intent.getClipData().getItemAt(i2).getUri();
            }
            return uriArr;
        } else if (intent.getData() == null || i != -1) {
            return null;
        } else {
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        }
    }

    private Intent getVideoIntent() {
        Intent intent;
        Exception e;
        Uri outputUri;
        try {
            File capturedFile = getCapturedFile(MimeType.VIDEO);
            this.outputVideo = capturedFile;
            outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
        } catch (IOException | IllegalArgumentException e2) {
            e = e2;
            intent = null;
        }
        try {
            intent.putExtra("output", outputUri);
        } catch (IOException e3) {
            e = e3;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        } catch (IllegalArgumentException e4) {
            e = e4;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    private Boolean noAcceptTypesSet(String[] strArr) {
        boolean z = true;
        if (!(strArr.length == 0 || (strArr.length == 1 && strArr[0] != null && strArr[0].length() == 0))) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void downloadFile() {
        ((DownloadManager) getCurrentActivity().getBaseContext().getSystemService("download")).enqueue(this.downloadRequest);
        Toast.makeText(getCurrentActivity().getApplicationContext(), "Downloading", 1).show();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCWebView";
    }

    public boolean grantFileDownloaderPermissions() {
        int i = Build.VERSION.SDK_INT;
        if (i > 28) {
            return true;
        }
        boolean z = ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (!z && i >= 23) {
            getPermissionAwareActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1, this.webviewFileDownloaderPermissionListener);
        }
        return z;
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        promise.resolve(Boolean.TRUE);
    }

    public boolean needsCameraPermission() {
        try {
            if (Arrays.asList(getCurrentActivity().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 4096).requestedPermissions).contains("android.permission.CAMERA")) {
                if (ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.CAMERA") != 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.filePathCallback != null || this.filePathCallbackLegacy != null) {
            File file = this.outputImage;
            boolean z = file != null && file.length() > 0;
            File file2 = this.outputVideo;
            boolean z2 = file2 != null && file2.length() > 0;
            if (i != 1) {
                if (i == 3) {
                    if (i2 != -1) {
                        this.filePathCallbackLegacy.onReceiveValue(null);
                    } else if (z) {
                        this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputImage));
                    } else if (z2) {
                        this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputVideo));
                    } else {
                        this.filePathCallbackLegacy.onReceiveValue(intent.getData());
                    }
                }
            } else if (i2 != -1) {
                ValueCallback<Uri[]> valueCallback = this.filePathCallback;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else if (z) {
                this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputImage)});
            } else if (z2) {
                this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputVideo)});
            } else {
                this.filePathCallback.onReceiveValue(getSelectedFiles(intent, i2));
            }
            File file3 = this.outputImage;
            if (file3 != null && !z) {
                file3.delete();
            }
            File file4 = this.outputVideo;
            if (file4 != null && !z2) {
                file4.delete();
            }
            this.filePathCallback = null;
            this.filePathCallbackLegacy = null;
            this.outputImage = null;
            this.outputVideo = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void onShouldStartLoadWithRequestCallback(boolean z, int i) {
        AtomicReference<ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState> atomicReference;
        ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock2 = shouldOverrideUrlLoadingLock;
        Integer valueOf = Integer.valueOf(i);
        synchronized (shouldOverrideUrlLoadingLock2) {
            atomicReference = shouldOverrideUrlLoadingLock2.shouldOverrideLocks.get(valueOf);
        }
        if (atomicReference != null) {
            synchronized (atomicReference) {
                atomicReference.set(z ? ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.DO_NOT_OVERRIDE : ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE);
                atomicReference.notify();
            }
        }
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.downloadRequest = request;
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallbackLegacy = valueCallback;
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), HttpUrl.FRAGMENT_ENCODE_SET);
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
            arrayList.add(photoIntent);
        }
        if (acceptsVideo(str).booleanValue() && (videoIntent = getVideoIntent()) != null) {
            arrayList.add(videoIntent);
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    private Boolean acceptsImages(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, "*/*").booleanValue() || arrayContainsString(acceptedMimeType, "image").booleanValue());
    }

    private Boolean acceptsVideo(String[] strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return Boolean.FALSE;
        }
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, "*/*").booleanValue() || arrayContainsString(acceptedMimeType, "video").booleanValue());
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, String[] strArr, boolean z) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallback = valueCallback;
        ArrayList arrayList = new ArrayList();
        if (!needsCameraPermission()) {
            if (acceptsImages(strArr).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
                arrayList.add(photoIntent);
            }
            if (acceptsVideo(strArr).booleanValue() && (videoIntent = getVideoIntent()) != null) {
                arrayList.add(videoIntent);
            }
        }
        Intent fileChooserIntent = getFileChooserIntent(strArr, z);
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INTENT", fileChooserIntent);
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (intent.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(intent, 1);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
        return true;
    }
}
