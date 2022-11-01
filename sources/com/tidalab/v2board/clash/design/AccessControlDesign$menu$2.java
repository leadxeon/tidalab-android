package com.tidalab.v2board.clash.design;

import android.content.Context;
import com.tidalab.v2board.clash.design.component.AccessControlMenu;
import com.tidalab.v2board.clash.design.store.UiStore;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: AccessControlDesign.kt */
/* loaded from: classes.dex */
public final class AccessControlDesign$menu$2 extends Lambda implements Function0<AccessControlMenu> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ UiStore $uiStore;
    public final /* synthetic */ AccessControlDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlDesign$menu$2(Context context, AccessControlDesign accessControlDesign, UiStore uiStore) {
        super(0);
        this.$context = context;
        this.this$0 = accessControlDesign;
        this.$uiStore = uiStore;
    }

    @Override // kotlin.jvm.functions.Function0
    public AccessControlMenu invoke() {
        Context context = this.$context;
        AccessControlDesign accessControlDesign = this.this$0;
        return new AccessControlMenu(context, accessControlDesign.binding.menuView, this.$uiStore, accessControlDesign.requests);
    }
}
