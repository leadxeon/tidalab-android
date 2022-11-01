package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.NewProfileDesign;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: NewProfileDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class NewProfileDesign$adapter$2 extends FunctionReferenceImpl implements Function1<ProfileProvider, Boolean> {
    public NewProfileDesign$adapter$2(NewProfileDesign newProfileDesign) {
        super(1, newProfileDesign, NewProfileDesign.class, "requestDetail", "requestDetail(Lcom/tidalab/v2board/clash/design/model/ProfileProvider;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Boolean invoke(ProfileProvider profileProvider) {
        boolean z;
        ProfileProvider profileProvider2 = profileProvider;
        NewProfileDesign newProfileDesign = (NewProfileDesign) this.receiver;
        Objects.requireNonNull(newProfileDesign);
        if (!(profileProvider2 instanceof ProfileProvider.External)) {
            z = false;
        } else {
            newProfileDesign.requests.mo14trySendJP2dKIU(new NewProfileDesign.Request.OpenDetail((ProfileProvider.External) profileProvider2));
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
