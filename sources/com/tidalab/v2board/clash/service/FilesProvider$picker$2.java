package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.service.document.Picker;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: FilesProvider.kt */
/* loaded from: classes.dex */
public final class FilesProvider$picker$2 extends Lambda implements Function0<Picker> {
    public final /* synthetic */ FilesProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesProvider$picker$2(FilesProvider filesProvider) {
        super(0);
        this.this$0 = filesProvider;
    }

    @Override // kotlin.jvm.functions.Function0
    public Picker invoke() {
        return new Picker(this.this$0.getContext());
    }
}
