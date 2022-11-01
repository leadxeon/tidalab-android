package androidx.appcompat.widget;

import android.content.res.Resources;
import android.widget.SpinnerAdapter;
/* loaded from: classes.dex */
public interface ThemedSpinnerAdapter extends SpinnerAdapter {
    Resources.Theme getDropDownViewTheme();

    void setDropDownViewTheme(Resources.Theme theme);
}
