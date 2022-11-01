package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.NewProfileDesign;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: NewProfileDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class NewProfileDesign$adapter$1 extends FunctionReferenceImpl implements Function1<ProfileProvider, Unit> {
    public NewProfileDesign$adapter$1(NewProfileDesign newProfileDesign) {
        super(1, newProfileDesign, NewProfileDesign.class, "requestCreate", "requestCreate(Lcom/tidalab/v2board/clash/design/model/ProfileProvider;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ProfileProvider profileProvider) {
        ((NewProfileDesign) this.receiver).requests.mo14trySendJP2dKIU(new NewProfileDesign.Request.Create(profileProvider));
        return Unit.INSTANCE;
    }
}
