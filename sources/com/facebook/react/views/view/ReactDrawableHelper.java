package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.TypedValue;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
/* loaded from: classes.dex */
public class ReactDrawableHelper {
    public static final TypedValue sResolveOutValue = new TypedValue();

    @TargetApi(21)
    public static Drawable createDrawableFromJSDescription(Context context, ReadableMap readableMap) {
        int i;
        String string = readableMap.getString("type");
        if ("ThemeAttrAndroid".equals(string)) {
            String string2 = readableMap.getString("attribute");
            SoftAssertions.assertNotNull(string2);
            int identifier = context.getResources().getIdentifier(string2, "attr", "android");
            if (identifier != 0) {
                Resources.Theme theme = context.getTheme();
                TypedValue typedValue = sResolveOutValue;
                if (theme.resolveAttribute(identifier, typedValue, true)) {
                    return context.getResources().getDrawable(typedValue.resourceId, context.getTheme());
                }
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline9("Attribute ", string2, " couldn't be resolved into a drawable"));
            }
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline9("Attribute ", string2, " couldn't be found in the resource list"));
        } else if ("RippleAndroid".equals(string)) {
            if (!readableMap.hasKey("color") || readableMap.isNull("color")) {
                Resources.Theme theme2 = context.getTheme();
                TypedValue typedValue2 = sResolveOutValue;
                if (theme2.resolveAttribute(16843820, typedValue2, true)) {
                    i = context.getResources().getColor(typedValue2.resourceId);
                } else {
                    throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
                }
            } else {
                i = readableMap.getInt("color");
            }
            return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{i}), null, (!readableMap.hasKey("borderless") || readableMap.isNull("borderless") || !readableMap.getBoolean("borderless")) ? new ColorDrawable(-1) : null);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid type for android drawable: ", string));
        }
    }
}
