package androidx.core.app;

import androidx.core.content.ContextCompat;
/* loaded from: classes.dex */
public class ActivityCompat extends ContextCompat {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* loaded from: classes.dex */
    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* loaded from: classes.dex */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }
}
