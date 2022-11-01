package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.core.app.AppOpsManagerCompat;
/* loaded from: classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {
    public final Activity mActivity;
    public final Context mContext;
    public final FragmentManager mFragmentManager = new FragmentManagerImpl();
    public final Handler mHandler;

    public FragmentHostCallback(FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.mActivity = fragmentActivity;
        AppOpsManagerCompat.checkNotNull(fragmentActivity, "context == null");
        this.mContext = fragmentActivity;
        AppOpsManagerCompat.checkNotNull(handler, "handler == null");
        this.mHandler = handler;
    }

    public abstract E onGetHost();

    public abstract LayoutInflater onGetLayoutInflater();

    public abstract boolean onShouldSaveFragmentState(Fragment fragment);

    public abstract void onSupportInvalidateOptionsMenu();
}
