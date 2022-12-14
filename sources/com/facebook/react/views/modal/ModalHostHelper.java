package com.facebook.react.views.modal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class ModalHostHelper {
    public static final Point MIN_POINT = new Point();
    public static final Point MAX_POINT = new Point();
    public static final Point SIZE_POINT = new Point();

    public static Point getModalHostSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        R$dimen.assertNotNull(windowManager);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = MIN_POINT;
        Point point2 = MAX_POINT;
        defaultDisplay.getCurrentSizeRange(point, point2);
        Point point3 = SIZE_POINT;
        defaultDisplay.getSize(point3);
        int i = 0;
        boolean z = context.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (z && identifier > 0) {
            i = (int) resources.getDimension(identifier);
        }
        if (point3.x < point3.y) {
            return new Point(point.x, point2.y + i);
        }
        return new Point(point2.x, point.y + i);
    }
}
