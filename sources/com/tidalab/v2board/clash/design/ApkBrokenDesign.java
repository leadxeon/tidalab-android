package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsCommonBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.preference.ScreenKt$preferenceScreen$impl$1;
import com.tidalab.v2board.clash.design.preference.SwitchKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ApkBrokenDesign.kt */
/* loaded from: classes.dex */
public final class ApkBrokenDesign extends Design<Request> {
    public final DesignSettingsCommonBinding binding;

    /* compiled from: ApkBrokenDesign.kt */
    /* loaded from: classes.dex */
    public static final class Request {
        public final String url;

        public Request(String str) {
            this.url = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Request) && Intrinsics.areEqual(this.url, ((Request) obj).url);
        }

        public int hashCode() {
            return this.url.hashCode();
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Request(url=");
            outline13.append(this.url);
            outline13.append(')');
            return outline13.toString();
        }
    }

    public ApkBrokenDesign(Context context) {
        super(context);
        DesignSettingsCommonBinding inflate = DesignSettingsCommonBinding.inflate(LayoutInflater.from(context), InputKt.getRoot(context), false);
        this.binding = inflate;
        inflate.setSurface(this.surface);
        InputKt.applyFrom(inflate.activityBarLayout, context);
        InputKt.bindAppBarElevation(inflate.scrollRoot, inflate.activityBarLayout);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ScreenKt$preferenceScreen$impl$1 screenKt$preferenceScreen$impl$1 = new ScreenKt$preferenceScreen$impl$1(this, context, linearLayout);
        SwitchKt.tips$default(screenKt$preferenceScreen$impl$1, R.string.application_broken_tips, null, 2);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.reinstall);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.google_play, null, Integer.valueOf((int) R.string.google_play_url), new $$LambdaGroup$ks$UZTdflfAQlVLgzpSxqSXYOz4Co(0, this, context), 2);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.github_releases, null, Integer.valueOf((int) R.string.github_releases_url), new $$LambdaGroup$ks$UZTdflfAQlVLgzpSxqSXYOz4Co(1, this, context), 2);
        Unit unit = Unit.INSTANCE;
        inflate.content.addView(linearLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
