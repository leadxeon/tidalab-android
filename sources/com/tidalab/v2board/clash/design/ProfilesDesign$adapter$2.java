package com.tidalab.v2board.clash.design;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBinding;
import com.tidalab.v2board.clash.design.dialog.AppBottomSheetDialog;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: ProfilesDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class ProfilesDesign$adapter$2 extends FunctionReferenceImpl implements Function1<Profile, Unit> {
    public ProfilesDesign$adapter$2(ProfilesDesign profilesDesign) {
        super(1, profilesDesign, ProfilesDesign.class, "showMenu", "showMenu(Lcom/tidalab/v2board/clash/service/model/Profile;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Profile profile) {
        Profile profile2 = profile;
        ProfilesDesign profilesDesign = (ProfilesDesign) this.receiver;
        Objects.requireNonNull(profilesDesign);
        AppBottomSheetDialog appBottomSheetDialog = new AppBottomSheetDialog(profilesDesign.context);
        LayoutInflater from = LayoutInflater.from(profilesDesign.context);
        Window window = appBottomSheetDialog.getWindow();
        View decorView = window == null ? null : window.getDecorView();
        int i = DialogProfilesMenuBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DialogProfilesMenuBinding dialogProfilesMenuBinding = (DialogProfilesMenuBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_profiles_menu, (ViewGroup) decorView, false, null);
        dialogProfilesMenuBinding.setMaster(profilesDesign);
        dialogProfilesMenuBinding.setSelf(appBottomSheetDialog);
        dialogProfilesMenuBinding.setProfile(profile2);
        appBottomSheetDialog.setContentView(dialogProfilesMenuBinding.mRoot);
        appBottomSheetDialog.show();
        return Unit.INSTANCE;
    }
}
