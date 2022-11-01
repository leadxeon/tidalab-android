package androidx.activity.result.contract;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContract;
@TargetApi(19)
/* loaded from: classes.dex */
public class ActivityResultContracts$CreateDocument extends ActivityResultContract<String, Uri> {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, String str) {
        return new Intent("android.intent.action.CREATE_DOCUMENT").setType("*/*").putExtra("android.intent.extra.TITLE", str);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context context, String str) {
        return null;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public Uri parseResult(int i, Intent intent) {
        if (intent == null || i != -1) {
            return null;
        }
        return intent.getData();
    }
}
