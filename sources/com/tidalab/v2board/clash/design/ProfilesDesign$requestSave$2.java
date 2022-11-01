package com.tidalab.v2board.clash.design;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProfilesDesign.kt */
/* loaded from: classes.dex */
public final class ProfilesDesign$requestSave$2 extends Lambda implements Function1<Snackbar, Unit> {
    public final /* synthetic */ Profile $profile;
    public final /* synthetic */ ProfilesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesDesign$requestSave$2(ProfilesDesign profilesDesign, Profile profile) {
        super(1);
        this.this$0 = profilesDesign;
        this.$profile = profile;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Snackbar snackbar) {
        final ProfilesDesign profilesDesign = this.this$0;
        final Profile profile = this.$profile;
        snackbar.setAction(R.string.edit, new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$ProfilesDesign$requestSave$2$l7CJbo6KOl_g2Eq6ji55oBa5OJg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfilesDesign.this.requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Edit(profile));
            }
        });
        return Unit.INSTANCE;
    }
}
