package org.chromium.support_lib_boundary;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public interface WebSettingsBoundaryInterface {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ForceDarkBehavior {
        public static final int FORCE_DARK_ONLY = 0;
        public static final int MEDIA_QUERY_ONLY = 1;
        public static final int PREFER_MEDIA_QUERY_OVER_FORCE_DARK = 2;
    }

    int getDisabledActionModeMenuItems();

    int getForceDark();

    int getForceDarkBehavior();

    boolean getOffscreenPreRaster();

    boolean getSafeBrowsingEnabled();

    boolean getWillSuppressErrorPage();

    void setDisabledActionModeMenuItems(int i);

    void setForceDark(int i);

    void setForceDarkBehavior(int i);

    void setOffscreenPreRaster(boolean z);

    void setSafeBrowsingEnabled(boolean z);

    void setWillSuppressErrorPage(boolean z);
}
