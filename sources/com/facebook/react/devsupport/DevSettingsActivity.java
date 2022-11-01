package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DevSettingsActivity extends PreferenceActivity {
    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getApplication().getResources().getString(R.string.catalyst_settings_title));
        addPreferencesFromResource(R.xml.rn_dev_preferences);
    }
}
