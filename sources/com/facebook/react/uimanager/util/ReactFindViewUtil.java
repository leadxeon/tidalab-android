package com.facebook.react.uimanager.util;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class ReactFindViewUtil {
    public static final List<OnViewFoundListener> mOnViewFoundListeners = new ArrayList();
    public static final Map<OnMultipleViewsFoundListener, Set<String>> mOnMultipleViewsFoundListener = new HashMap();

    /* loaded from: classes.dex */
    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    /* loaded from: classes.dex */
    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }
}
