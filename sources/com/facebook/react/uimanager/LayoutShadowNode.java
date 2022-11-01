package com.facebook.react.uimanager;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import com.reactnativecommunity.webview.RNCWebViewManager;
/* loaded from: classes.dex */
public class LayoutShadowNode extends ReactShadowNodeImpl {
    public final MutableYogaValue mTempYogaValue = new MutableYogaValue(null);

    /* loaded from: classes.dex */
    public static class MutableYogaValue {
        public int unit;
        public float value;

        public MutableYogaValue(AnonymousClass1 r1) {
        }

        public void setFromDynamic(Dynamic dynamic) {
            if (dynamic.isNull()) {
                this.unit = 1;
                this.value = Float.NaN;
            } else if (dynamic.getType() == ReadableType.String) {
                String asString = dynamic.asString();
                if (asString.equals("auto")) {
                    this.unit = 4;
                    this.value = Float.NaN;
                } else if (asString.endsWith("%")) {
                    this.unit = 3;
                    this.value = Float.parseFloat(asString.substring(0, asString.length() - 1));
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Unknown value: ", asString));
                }
            } else {
                this.unit = 2;
                this.value = PixelUtil.toPixelFromDIP(dynamic.asDouble());
            }
        }
    }

    public final int maybeTransformLeftRightToStartEnd(int i) {
        if (!I18nUtil.getInstance().doLeftAndRightSwapInRTL(getThemedContext())) {
            return i;
        }
        if (i == 0) {
            return 4;
        }
        if (i != 2) {
            return i;
        }
        return 5;
    }

    @ReactProp(name = "alignContent")
    public void setAlignContent(String str) {
        YogaAlign yogaAlign = YogaAlign.FLEX_START;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setAlignContent(yogaAlign);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c = 4;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setAlignContent(YogaAlign.STRETCH);
                    return;
                case 1:
                    this.mYogaNode.setAlignContent(YogaAlign.BASELINE);
                    return;
                case 2:
                    this.mYogaNode.setAlignContent(YogaAlign.CENTER);
                    return;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    this.mYogaNode.setAlignContent(yogaAlign);
                    return;
                case 4:
                    this.mYogaNode.setAlignContent(YogaAlign.AUTO);
                    return;
                case 5:
                    this.mYogaNode.setAlignContent(YogaAlign.SPACE_BETWEEN);
                    return;
                case 6:
                    this.mYogaNode.setAlignContent(YogaAlign.FLEX_END);
                    return;
                case 7:
                    this.mYogaNode.setAlignContent(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for alignContent: ", str));
            }
        }
    }

    @ReactProp(name = "alignItems")
    public void setAlignItems(String str) {
        YogaAlign yogaAlign = YogaAlign.STRETCH;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setAlignItems(yogaAlign);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c = 4;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setAlignItems(yogaAlign);
                    return;
                case 1:
                    this.mYogaNode.setAlignItems(YogaAlign.BASELINE);
                    return;
                case 2:
                    this.mYogaNode.setAlignItems(YogaAlign.CENTER);
                    return;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    this.mYogaNode.setAlignItems(YogaAlign.FLEX_START);
                    return;
                case 4:
                    this.mYogaNode.setAlignItems(YogaAlign.AUTO);
                    return;
                case 5:
                    this.mYogaNode.setAlignItems(YogaAlign.SPACE_BETWEEN);
                    return;
                case 6:
                    this.mYogaNode.setAlignItems(YogaAlign.FLEX_END);
                    return;
                case 7:
                    this.mYogaNode.setAlignItems(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for alignItems: ", str));
            }
        }
    }

    @ReactProp(name = "alignSelf")
    public void setAlignSelf(String str) {
        YogaAlign yogaAlign = YogaAlign.AUTO;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setAlignSelf(yogaAlign);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c = 4;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setAlignSelf(YogaAlign.STRETCH);
                    return;
                case 1:
                    this.mYogaNode.setAlignSelf(YogaAlign.BASELINE);
                    return;
                case 2:
                    this.mYogaNode.setAlignSelf(YogaAlign.CENTER);
                    return;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    this.mYogaNode.setAlignSelf(YogaAlign.FLEX_START);
                    return;
                case 4:
                    this.mYogaNode.setAlignSelf(yogaAlign);
                    return;
                case 5:
                    this.mYogaNode.setAlignSelf(YogaAlign.SPACE_BETWEEN);
                    return;
                case 6:
                    this.mYogaNode.setAlignSelf(YogaAlign.FLEX_END);
                    return;
                case 7:
                    this.mYogaNode.setAlignSelf(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for alignSelf: ", str));
            }
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "aspectRatio")
    public void setAspectRatio(float f) {
        this.mYogaNode.setAspectRatio(f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"})
    public void setBorderWidths(int i, float f) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.BORDER_SPACING_TYPES[i]);
            this.mYogaNode.setBorder(YogaEdge.fromInt(maybeTransformLeftRightToStartEnd), PixelUtil.toPixelFromDIP(f));
        }
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(boolean z) {
    }

    @ReactProp(name = "display")
    public void setDisplay(String str) {
        YogaDisplay yogaDisplay = YogaDisplay.FLEX;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setDisplay(yogaDisplay);
            } else if (str.equals("flex")) {
                this.mYogaNode.setDisplay(yogaDisplay);
            } else if (!str.equals("none")) {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for display: ", str));
            } else {
                this.mYogaNode.setDisplay(YogaDisplay.NONE);
            }
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flex")
    public void setFlex(float f) {
        if (!isVirtual()) {
            this.mYogaNode.setFlex(f);
        }
    }

    @ReactProp(name = "flexBasis")
    public void setFlexBasis(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setFlexBasis(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setFlexBasisPercent(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 3) {
                this.mYogaNode.setFlexBasisAuto();
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "flexDirection")
    public void setFlexDirection(String str) {
        YogaFlexDirection yogaFlexDirection = YogaFlexDirection.COLUMN;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setFlexDirection(yogaFlexDirection);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1448970769:
                    if (str.equals("row-reverse")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1354837162:
                    if (str.equals("column")) {
                        c = 1;
                        break;
                    }
                    break;
                case 113114:
                    if (str.equals("row")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1272730475:
                    if (str.equals("column-reverse")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setFlexDirection(YogaFlexDirection.ROW_REVERSE);
                    return;
                case 1:
                    this.mYogaNode.setFlexDirection(yogaFlexDirection);
                    return;
                case 2:
                    this.mYogaNode.setFlexDirection(YogaFlexDirection.ROW);
                    return;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    this.mYogaNode.setFlexDirection(YogaFlexDirection.COLUMN_REVERSE);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for flexDirection: ", str));
            }
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    public void setFlexGrow(float f) {
        if (!isVirtual()) {
            this.mYogaNode.setFlexGrow(f);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    public void setFlexShrink(float f) {
        if (!isVirtual()) {
            this.mYogaNode.setFlexShrink(f);
        }
    }

    @ReactProp(name = "flexWrap")
    public void setFlexWrap(String str) {
        YogaWrap yogaWrap = YogaWrap.NO_WRAP;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setWrap(yogaWrap);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1039592053:
                    if (str.equals("nowrap")) {
                        c = 0;
                        break;
                    }
                    break;
                case -749527969:
                    if (str.equals("wrap-reverse")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3657802:
                    if (str.equals("wrap")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setWrap(yogaWrap);
                    return;
                case 1:
                    this.mYogaNode.setWrap(YogaWrap.WRAP_REVERSE);
                    return;
                case 2:
                    this.mYogaNode.setWrap(YogaWrap.WRAP);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for flexWrap: ", str));
            }
        }
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setHeight(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setHeightPercent(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 3) {
                this.mYogaNode.setHeightAuto();
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "justifyContent")
    public void setJustifyContent(String str) {
        YogaJustify yogaJustify = YogaJustify.FLEX_START;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setJustifyContent(yogaJustify);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1364013995:
                    if (str.equals("center")) {
                        c = 0;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c = 1;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2055030478:
                    if (str.equals("space-evenly")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setJustifyContent(YogaJustify.CENTER);
                    return;
                case 1:
                    this.mYogaNode.setJustifyContent(yogaJustify);
                    return;
                case 2:
                    this.mYogaNode.setJustifyContent(YogaJustify.SPACE_BETWEEN);
                    return;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    this.mYogaNode.setJustifyContent(YogaJustify.FLEX_END);
                    return;
                case 4:
                    this.mYogaNode.setJustifyContent(YogaJustify.SPACE_AROUND);
                    return;
                case 5:
                    this.mYogaNode.setJustifyContent(YogaJustify.SPACE_EVENLY);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for justifyContent: ", str));
            }
        }
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginStart", "marginEnd", "marginTop", "marginBottom", "marginLeft", "marginRight"})
    public void setMargins(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                setMargin(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setMarginPercent(YogaEdge.fromInt(maybeTransformLeftRightToStartEnd), this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 3) {
                this.mYogaNode.setMarginAuto(YogaEdge.fromInt(maybeTransformLeftRightToStartEnd));
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setMaxHeight(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setMaxHeightPercent(this.mTempYogaValue.value);
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setMaxWidth(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setMaxWidthPercent(this.mTempYogaValue.value);
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "minHeight")
    public void setMinHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setMinHeight(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setMinHeightPercent(this.mTempYogaValue.value);
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "minWidth")
    public void setMinWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setMinWidth(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setMinWidthPercent(this.mTempYogaValue.value);
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(String str) {
        YogaOverflow yogaOverflow = YogaOverflow.VISIBLE;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setOverflow(yogaOverflow);
                return;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1217487446:
                    if (str.equals("hidden")) {
                        c = 0;
                        break;
                    }
                    break;
                case -907680051:
                    if (str.equals("scroll")) {
                        c = 1;
                        break;
                    }
                    break;
                case 466743410:
                    if (str.equals("visible")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mYogaNode.setOverflow(YogaOverflow.HIDDEN);
                    return;
                case 1:
                    this.mYogaNode.setOverflow(YogaOverflow.SCROLL);
                    return;
                case 2:
                    this.mYogaNode.setOverflow(yogaOverflow);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for overflow: ", str));
            }
        }
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingStart", "paddingEnd", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight"})
    public void setPaddings(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                setPadding(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                float f = this.mTempYogaValue.value;
                this.mPadding[maybeTransformLeftRightToStartEnd] = f;
                this.mPaddingIsPercent[maybeTransformLeftRightToStartEnd] = !R$style.isUndefined(f);
                updatePadding();
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "position")
    public void setPosition(String str) {
        YogaPositionType yogaPositionType = YogaPositionType.RELATIVE;
        if (!isVirtual()) {
            if (str == null) {
                this.mYogaNode.setPositionType(yogaPositionType);
            } else if (str.equals("relative")) {
                this.mYogaNode.setPositionType(yogaPositionType);
            } else if (!str.equals("absolute")) {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("invalid value for position: ", str));
            } else {
                this.mYogaNode.setPositionType(YogaPositionType.ABSOLUTE);
            }
        }
    }

    @ReactPropGroup(names = {"start", "end", "left", "right", "top", "bottom"})
    public void setPositionValues(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(new int[]{4, 5, 0, 2, 1, 3}[i]);
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setPosition(YogaEdge.fromInt(maybeTransformLeftRightToStartEnd), this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setPositionPercent(YogaEdge.fromInt(maybeTransformLeftRightToStartEnd), this.mTempYogaValue.value);
            }
            dynamic.recycle();
        }
    }

    @ReactProp(name = "onLayout")
    public void setShouldNotifyOnLayout(boolean z) {
        this.mShouldNotifyOnLayout = z;
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.setFromDynamic(dynamic);
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mTempYogaValue.unit);
            if ($enumboxing$ordinal == 0 || $enumboxing$ordinal == 1) {
                this.mYogaNode.setWidth(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 2) {
                this.mYogaNode.setWidthPercent(this.mTempYogaValue.value);
            } else if ($enumboxing$ordinal == 3) {
                this.mYogaNode.setWidthAuto();
            }
            dynamic.recycle();
        }
    }
}
