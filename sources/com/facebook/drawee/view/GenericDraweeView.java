package com.facebook.drawee.view;

import android.content.Context;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
/* loaded from: classes.dex */
public class GenericDraweeView extends DraweeView<GenericDraweeHierarchy> {
    public GenericDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context);
        setHierarchy(genericDraweeHierarchy);
    }
}
