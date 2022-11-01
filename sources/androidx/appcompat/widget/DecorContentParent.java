package androidx.appcompat.widget;

import android.view.Menu;
import android.view.Window;
import androidx.appcompat.view.menu.MenuPresenter;
/* loaded from: classes.dex */
public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int i);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenu(Menu menu, MenuPresenter.Callback callback);

    void setMenuPrepared();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
