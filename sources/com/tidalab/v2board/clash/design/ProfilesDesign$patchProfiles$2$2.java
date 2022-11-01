package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProfilesDesign.kt */
/* loaded from: classes.dex */
public final class ProfilesDesign$patchProfiles$2$2 extends Lambda implements Function1<Profile, Object> {
    public static final ProfilesDesign$patchProfiles$2$2 INSTANCE = new ProfilesDesign$patchProfiles$2$2();

    public ProfilesDesign$patchProfiles$2$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Profile profile) {
        return profile.uuid;
    }
}
