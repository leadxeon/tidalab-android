package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsCommonBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.preference.ScreenKt$preferenceScreen$impl$1;
import com.tidalab.v2board.clash.design.preference.SwitchKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HelpDesign.kt */
/* loaded from: classes.dex */
public final class HelpDesign extends Design<Unit> {
    public final DesignSettingsCommonBinding binding;

    public HelpDesign(Context context, Function1<? super Uri, Unit> function1) {
        super(context);
        DesignSettingsCommonBinding inflate = DesignSettingsCommonBinding.inflate(LayoutInflater.from(context), InputKt.getRoot(context), false);
        this.binding = inflate;
        inflate.setSurface(this.surface);
        InputKt.applyFrom(inflate.activityBarLayout, context);
        InputKt.bindAppBarElevation(inflate.scrollRoot, inflate.activityBarLayout);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ScreenKt$preferenceScreen$impl$1 screenKt$preferenceScreen$impl$1 = new ScreenKt$preferenceScreen$impl$1(this, context, linearLayout);
        SwitchKt.tips$default(screenKt$preferenceScreen$impl$1, R.string.tips_help, null, 2);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.document);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.clash_wiki, null, Integer.valueOf((int) R.string.clash_wiki_url), new HelpDesign$screen$1$1(function1, context), 2);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.feedback);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.github_issues, null, Integer.valueOf((int) R.string.github_issues_url), new HelpDesign$screen$1$2(function1, context), 2);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.sources);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.clash_for_android, null, Integer.valueOf((int) R.string.github_url), new HelpDesign$screen$1$3(function1, context), 2);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.clash_core, null, Integer.valueOf((int) R.string.clash_core_url), new HelpDesign$screen$1$4(function1, context), 2);
        if (Intrinsics.areEqual(PathParser.getPreferredLocale(context.getResources().getConfiguration()).getLanguage(), "zh")) {
            InputKt.category(screenKt$preferenceScreen$impl$1, R.string.donate);
            InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.donate, null, Integer.valueOf((int) R.string.donate_url), new HelpDesign$screen$1$5(function1, context), 2);
        }
        Unit unit = Unit.INSTANCE;
        inflate.content.addView(linearLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
