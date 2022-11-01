package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class ImageSource {
    public boolean isResource;
    public double mSize;
    public String mSource;
    public Uri mUri;

    public ImageSource(Context context, String str, double d, double d2) {
        Uri uri;
        this.mSource = str;
        this.mSize = d * d2;
        try {
            uri = Uri.parse(str);
            if (uri.getScheme() == null) {
                this.isResource = true;
                uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
            }
        } catch (Exception unused) {
            this.isResource = true;
            uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
        }
        this.mUri = uri;
    }

    public Uri getUri() {
        Uri uri = this.mUri;
        R$dimen.assertNotNull(uri);
        return uri;
    }

    public ImageSource(Context context, String str) {
        this(context, str, 0.0d, 0.0d);
    }
}
