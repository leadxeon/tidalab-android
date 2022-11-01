package com.tidalab.v2board.clash.design.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.foss.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ProfileProvider.kt */
/* loaded from: classes.dex */
public abstract class ProfileProvider {

    /* compiled from: ProfileProvider.kt */
    /* loaded from: classes.dex */
    public static final class External extends ProfileProvider {
        public final Drawable icon;
        public final Intent intent;
        public final String name;
        public final String summary;

        public External(String str, String str2, Drawable drawable, Intent intent) {
            super(null);
            this.name = str;
            this.summary = str2;
            this.icon = drawable;
            this.intent = intent;
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public Drawable getIcon() {
            return this.icon;
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getName() {
            return this.name;
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getSummary() {
            return this.summary;
        }
    }

    /* compiled from: ProfileProvider.kt */
    /* loaded from: classes.dex */
    public static final class File extends ProfileProvider {
        public final Context context;

        public File(Context context) {
            super(null);
            this.context = context;
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public Drawable getIcon() {
            return PathParser.getDrawableCompat(this.context, R.drawable.ic_baseline_attach_file);
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getName() {
            return this.context.getString(R.string.file);
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getSummary() {
            return this.context.getString(R.string.import_from_file);
        }
    }

    /* compiled from: ProfileProvider.kt */
    /* loaded from: classes.dex */
    public static final class Url extends ProfileProvider {
        public final Context context;

        public Url(Context context) {
            super(null);
            this.context = context;
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public Drawable getIcon() {
            return PathParser.getDrawableCompat(this.context, R.drawable.ic_baseline_cloud_download);
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getName() {
            return this.context.getString(R.string.url);
        }

        @Override // com.tidalab.v2board.clash.design.model.ProfileProvider
        public String getSummary() {
            return this.context.getString(R.string.import_from_url);
        }
    }

    public ProfileProvider() {
    }

    public abstract Drawable getIcon();

    public abstract String getName();

    public abstract String getSummary();

    public ProfileProvider(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
