package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: ProfilesDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class ProfilesDesign$adapter$1 extends FunctionReferenceImpl implements Function1<Profile, Unit> {
    public ProfilesDesign$adapter$1(ProfilesDesign profilesDesign) {
        super(1, profilesDesign, ProfilesDesign.class, "requestActive", "requestActive(Lcom/tidalab/v2board/clash/service/model/Profile;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Profile profile) {
        ((ProfilesDesign) this.receiver).requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Active(profile));
        return Unit.INSTANCE;
    }
}
