package com.facebook.drawee.view;

import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class SimpleDraweeView extends GenericDraweeView {
    public static final /* synthetic */ int $r8$clinit = 0;

    public AbstractDraweeControllerBuilder getControllerBuilder() {
        return null;
    }

    public void setActualImageResource(int i) {
        Uri uri = UriUtil.LOCAL_CONTACT_IMAGE_URI;
        new Uri.Builder().scheme("res").path(String.valueOf(i)).build();
        throw null;
    }

    public void setImageRequest(ImageRequest imageRequest) {
        throw null;
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        throw null;
    }

    public void setImageURI(String str) {
        if (str != null) {
            Uri.parse(str);
        }
        throw null;
    }
}
