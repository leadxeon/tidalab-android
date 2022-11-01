package androidx.appcompat.widget;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.InputConnectionCompat$OnCommitContentListener;
import androidx.core.view.inputmethod.InputContentInfoCompat;
/* loaded from: classes.dex */
public class AppCompatReceiveContentHelper$1 implements InputConnectionCompat$OnCommitContentListener {
    public final /* synthetic */ View val$view;

    public AppCompatReceiveContentHelper$1(View view) {
        this.val$view = view;
    }

    public boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 25 && (i & 1) != 0) {
            try {
                inputContentInfoCompat.mImpl.requestPermission();
                InputContentInfo inputContentInfo = (InputContentInfo) inputContentInfoCompat.mImpl.getInputContentInfo();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo);
            } catch (Exception e) {
                Log.w("ReceiveContent", "Can't insert content from IME; requestPermission() failed", e);
                return false;
            }
        }
        ContentInfoCompat.Builder builder = new ContentInfoCompat.Builder(new ClipData(inputContentInfoCompat.mImpl.getDescription(), new ClipData.Item(inputContentInfoCompat.mImpl.getContentUri())), 2);
        builder.mLinkUri = inputContentInfoCompat.mImpl.getLinkUri();
        builder.mExtras = bundle;
        return ViewCompat.performReceiveContent(this.val$view, new ContentInfoCompat(builder)) == null;
    }
}
