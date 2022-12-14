package com.facebook.react.views.text;

import android.text.TextUtils;
import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
/* loaded from: classes.dex */
public abstract class ReactTextAnchorViewManager<T extends View, C extends ReactBaseTextShadowNode> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};

    @ReactProp(name = "adjustsFontSizeToFit")
    public void setAdjustFontSizeToFit(ReactTextView reactTextView, boolean z) {
        reactTextView.setAdjustFontSizeToFit(z);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & 16777215;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactTextView.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
            return;
        }
        reactTextView.mReactBackgroundManager.getOrCreateReactViewBackground().setRadius(f, i - 1);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, String str) {
        reactTextView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactTextView.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderWidth(SPACING_TYPES[i], f);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactProp(name = "dataDetectorType")
    public void setDataDetectorType(ReactTextView reactTextView, String str) {
        char c;
        switch (str.hashCode()) {
            case -1192969641:
                if (str.equals("phoneNumber")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 96673:
                if (str.equals("all")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3321850:
                if (str.equals("link")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3387192:
                if (str.equals("none")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 96619420:
                if (str.equals("email")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            reactTextView.setLinkifyMask(4);
        } else if (c == 1) {
            reactTextView.setLinkifyMask(1);
        } else if (c == 2) {
            reactTextView.setLinkifyMask(2);
        } else if (c != 3) {
            reactTextView.setLinkifyMask(0);
        } else {
            reactTextView.setLinkifyMask(15);
        }
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactTextView reactTextView, boolean z) {
        reactTextView.setEnabled(!z);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView reactTextView, String str) {
        if (str == null || str.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
        } else if (str.equals("head")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
        } else if (str.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else if (str.equals("clip")) {
            reactTextView.setEllipsizeLocation(null);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid ellipsizeMode: ", str));
        }
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView reactTextView, boolean z) {
        reactTextView.setIncludeFontPadding(z);
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView reactTextView, boolean z) {
        reactTextView.setNotifyOnInlineViewLayout(z);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView reactTextView, boolean z) {
        reactTextView.setTextIsSelectable(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView reactTextView, Integer num) {
        if (num == null) {
            reactTextView.setHighlightColor(R$style.getDefaultTextAttribute(reactTextView.getContext(), 16842905).getDefaultColor());
        } else {
            reactTextView.setHighlightColor(num.intValue());
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView reactTextView, String str) {
        if (str == null || "auto".equals(str)) {
            reactTextView.setGravityVertical(0);
        } else if ("top".equals(str)) {
            reactTextView.setGravityVertical(48);
        } else if ("bottom".equals(str)) {
            reactTextView.setGravityVertical(80);
        } else if ("center".equals(str)) {
            reactTextView.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textAlignVertical: ", str));
        }
    }
}
