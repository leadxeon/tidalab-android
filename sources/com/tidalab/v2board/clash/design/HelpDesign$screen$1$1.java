package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.net.Uri;
import com.tidalab.v2board.clash.design.preference.ClickablePreference;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: HelpDesign.kt */
/* loaded from: classes.dex */
public final class HelpDesign$screen$1$1 extends Lambda implements Function1<ClickablePreference, Unit> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ Function1<Uri, Unit> $openLink;

    /* compiled from: HelpDesign.kt */
    /* renamed from: com.tidalab.v2board.clash.design.HelpDesign$screen$1$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ Function1<Uri, Unit> $openLink;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super Uri, Unit> function1, Context context) {
            super(0);
            this.$openLink = function1;
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            this.$openLink.invoke(Uri.parse(this.$context.getString(R.string.clash_wiki_url)));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HelpDesign$screen$1$1(Function1<? super Uri, Unit> function1, Context context) {
        super(1);
        this.$openLink = function1;
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClickablePreference clickablePreference) {
        clickablePreference.clicked(new AnonymousClass1(this.$openLink, this.$context));
        return Unit.INSTANCE;
    }
}
