package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResult;
/* loaded from: classes.dex */
public final class ActivityResultContracts$StartActivityForResult extends ActivityResultContract<Intent, ActivityResult> {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, Intent intent) {
        return intent;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public ActivityResult parseResult(int i, Intent intent) {
        return new ActivityResult(i, intent);
    }
}
