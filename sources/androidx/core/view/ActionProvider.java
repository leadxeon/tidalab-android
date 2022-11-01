package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public abstract class ActionProvider {
    public final Context mContext;
    public VisibilityListener mVisibilityListener;

    /* loaded from: classes.dex */
    public interface VisibilityListener {
    }

    public ActionProvider(Context context) {
        this.mContext = context;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem menuItem) {
        return onCreateActionView();
    }

    public boolean onPerformDefaultAction() {
        return false;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void setVisibilityListener(VisibilityListener visibilityListener) {
        if (this.mVisibilityListener != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            outline13.append(getClass().getSimpleName());
            outline13.append(" instance while it is still in use somewhere else?");
            Log.w("ActionProvider(support)", outline13.toString());
        }
        this.mVisibilityListener = visibilityListener;
    }
}
