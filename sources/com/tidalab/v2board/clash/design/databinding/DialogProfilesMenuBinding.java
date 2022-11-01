package com.tidalab.v2board.clash.design.databinding;

import android.app.Dialog;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.service.model.Profile;
/* loaded from: classes.dex */
public abstract class DialogProfilesMenuBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ProfilesDesign mMaster;
    public Profile mProfile;
    public Dialog mSelf;

    public DialogProfilesMenuBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public abstract void setMaster(ProfilesDesign profilesDesign);

    public abstract void setProfile(Profile profile);

    public abstract void setSelf(Dialog dialog);
}
