package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.List;
@DoNotStrip
/* loaded from: classes.dex */
public abstract class YogaNodeJNIBase extends YogaNode implements Cloneable {
    @DoNotStrip
    private float[] arr;
    public List<YogaNodeJNIBase> mChildren;
    public Object mData;
    public boolean mHasNewLayout;
    @DoNotStrip
    private int mLayoutDirection;
    public YogaMeasureFunction mMeasureFunction;
    public long mNativePointer;
    public YogaNodeJNIBase mOwner;

    public YogaNodeJNIBase(long j) {
        this.arr = null;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        if (j != 0) {
            this.mNativePointer = j;
            return;
        }
        throw new IllegalStateException("Failed to allocate native memory");
    }

    @DoNotStrip
    private final long replaceChild(YogaNodeJNIBase yogaNodeJNIBase, int i) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list != null) {
            list.remove(i);
            this.mChildren.add(i, yogaNodeJNIBase);
            yogaNodeJNIBase.mOwner = this;
            return yogaNodeJNIBase.mNativePointer;
        }
        throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
    }

    @Override // com.facebook.yoga.YogaNode
    public void addChildAt(YogaNode yogaNode, int i) {
        YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) yogaNode;
        if (yogaNodeJNIBase.mOwner == null) {
            if (this.mChildren == null) {
                this.mChildren = new ArrayList(4);
            }
            this.mChildren.add(i, yogaNodeJNIBase);
            yogaNodeJNIBase.mOwner = this;
            YogaNative.jni_YGNodeInsertChildJNI(this.mNativePointer, yogaNodeJNIBase.mNativePointer, i);
            return;
        }
        throw new IllegalStateException("Child already has a parent, it must be removed first.");
    }

    @DoNotStrip
    public final float baseline(float f, float f2) {
        throw null;
    }

    @Override // com.facebook.yoga.YogaNode
    public void calculateLayout(float f, float f2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        for (int i = 0; i < arrayList.size(); i++) {
            List<YogaNodeJNIBase> list = ((YogaNodeJNIBase) arrayList.get(i)).mChildren;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        YogaNodeJNIBase[] yogaNodeJNIBaseArr = (YogaNodeJNIBase[]) arrayList.toArray(new YogaNodeJNIBase[arrayList.size()]);
        long[] jArr = new long[yogaNodeJNIBaseArr.length];
        for (int i2 = 0; i2 < yogaNodeJNIBaseArr.length; i2++) {
            jArr[i2] = yogaNodeJNIBaseArr[i2].mNativePointer;
        }
        YogaNative.jni_YGNodeCalculateLayoutJNI(this.mNativePointer, f, f2, jArr, yogaNodeJNIBaseArr);
    }

    @Override // com.facebook.yoga.YogaNode
    public void dirty() {
        YogaNative.jni_YGNodeMarkDirtyJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public YogaValue getHeight() {
        long jni_YGNodeStyleGetHeightJNI = YogaNative.jni_YGNodeStyleGetHeightJNI(this.mNativePointer);
        return new YogaValue(Float.intBitsToFloat((int) jni_YGNodeStyleGetHeightJNI), (int) (jni_YGNodeStyleGetHeightJNI >> 32));
    }

    @Override // com.facebook.yoga.YogaNode
    public YogaDirection getLayoutDirection() {
        float[] fArr = this.arr;
        int i = fArr != null ? (int) fArr[5] : this.mLayoutDirection;
        if (i == 0) {
            return YogaDirection.INHERIT;
        }
        if (i == 1) {
            return YogaDirection.LTR;
        }
        if (i == 2) {
            return YogaDirection.RTL;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown enum value: ", i));
    }

    @Override // com.facebook.yoga.YogaNode
    public float getLayoutHeight() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[2];
        }
        return 0.0f;
    }

    @Override // com.facebook.yoga.YogaNode
    public float getLayoutPadding(YogaEdge yogaEdge) {
        YogaDirection yogaDirection = YogaDirection.RTL;
        float[] fArr = this.arr;
        if (fArr == null) {
            return 0.0f;
        }
        int i = 0;
        if ((((int) fArr[0]) & 2) != 2) {
            return 0.0f;
        }
        if ((((int) fArr[0]) & 1) != 1) {
            i = 4;
        }
        int i2 = 10 - i;
        int ordinal = yogaEdge.ordinal();
        if (ordinal == 0) {
            return this.arr[i2];
        }
        if (ordinal == 1) {
            return this.arr[i2 + 1];
        }
        if (ordinal == 2) {
            return this.arr[i2 + 2];
        }
        if (ordinal == 3) {
            return this.arr[i2 + 3];
        }
        if (ordinal == 4) {
            return getLayoutDirection() == yogaDirection ? this.arr[i2 + 2] : this.arr[i2];
        }
        if (ordinal == 5) {
            return getLayoutDirection() == yogaDirection ? this.arr[i2] : this.arr[i2 + 2];
        }
        throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
    }

    @Override // com.facebook.yoga.YogaNode
    public float getLayoutWidth() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[1];
        }
        return 0.0f;
    }

    @Override // com.facebook.yoga.YogaNode
    public float getLayoutX() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[3];
        }
        return 0.0f;
    }

    @Override // com.facebook.yoga.YogaNode
    public float getLayoutY() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[4];
        }
        return 0.0f;
    }

    @Override // com.facebook.yoga.YogaNode
    public YogaValue getWidth() {
        long jni_YGNodeStyleGetWidthJNI = YogaNative.jni_YGNodeStyleGetWidthJNI(this.mNativePointer);
        return new YogaValue(Float.intBitsToFloat((int) jni_YGNodeStyleGetWidthJNI), (int) (jni_YGNodeStyleGetWidthJNI >> 32));
    }

    @Override // com.facebook.yoga.YogaNode
    public boolean hasNewLayout() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return (((int) fArr[0]) & 16) == 16;
        }
        return this.mHasNewLayout;
    }

    @Override // com.facebook.yoga.YogaNode
    public boolean isDirty() {
        return YogaNative.jni_YGNodeIsDirtyJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    @Override // com.facebook.yoga.YogaNode
    public void markLayoutSeen() {
        float[] fArr = this.arr;
        if (fArr != null) {
            fArr[0] = ((int) fArr[0]) & (-17);
        }
        this.mHasNewLayout = false;
    }

    @DoNotStrip
    public final long measure(float f, int i, float f2, int i2) {
        YogaMeasureFunction yogaMeasureFunction = this.mMeasureFunction;
        if (yogaMeasureFunction != null) {
            return yogaMeasureFunction.measure(this, f, YogaMeasureMode.fromInt(i), f2, YogaMeasureMode.fromInt(i2));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    @Override // com.facebook.yoga.YogaNode
    public YogaNode removeChildAt(int i) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list != null) {
            YogaNodeJNIBase remove = list.remove(i);
            remove.mOwner = null;
            YogaNative.jni_YGNodeRemoveChildJNI(this.mNativePointer, remove.mNativePointer);
            return remove;
        }
        throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
    }

    @Override // com.facebook.yoga.YogaNode
    public void reset() {
        this.mMeasureFunction = null;
        this.arr = null;
        this.mHasNewLayout = true;
        this.mLayoutDirection = 0;
        YogaNative.jni_YGNodeResetJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setAlignContent(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignContentJNI(this.mNativePointer, yogaAlign.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setAlignItems(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignItemsJNI(this.mNativePointer, yogaAlign.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setAlignSelf(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignSelfJNI(this.mNativePointer, yogaAlign.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setAspectRatio(float f) {
        YogaNative.jni_YGNodeStyleSetAspectRatioJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setBorder(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetBorderJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setData(Object obj) {
        this.mData = obj;
    }

    @Override // com.facebook.yoga.YogaNode
    public void setDirection(YogaDirection yogaDirection) {
        YogaNative.jni_YGNodeStyleSetDirectionJNI(this.mNativePointer, yogaDirection.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setDisplay(YogaDisplay yogaDisplay) {
        YogaNative.jni_YGNodeStyleSetDisplayJNI(this.mNativePointer, yogaDisplay.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlex(float f) {
        YogaNative.jni_YGNodeStyleSetFlexJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexBasis(float f) {
        YogaNative.jni_YGNodeStyleSetFlexBasisJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexBasisAuto() {
        YogaNative.jni_YGNodeStyleSetFlexBasisAutoJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexBasisPercent(float f) {
        YogaNative.jni_YGNodeStyleSetFlexBasisPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexDirection(YogaFlexDirection yogaFlexDirection) {
        YogaNative.jni_YGNodeStyleSetFlexDirectionJNI(this.mNativePointer, yogaFlexDirection.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexGrow(float f) {
        YogaNative.jni_YGNodeStyleSetFlexGrowJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setFlexShrink(float f) {
        YogaNative.jni_YGNodeStyleSetFlexShrinkJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setHeight(float f) {
        YogaNative.jni_YGNodeStyleSetHeightJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setHeightAuto() {
        YogaNative.jni_YGNodeStyleSetHeightAutoJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetHeightPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setJustifyContent(YogaJustify yogaJustify) {
        YogaNative.jni_YGNodeStyleSetJustifyContentJNI(this.mNativePointer, yogaJustify.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMargin(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetMarginJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMarginAuto(YogaEdge yogaEdge) {
        YogaNative.jni_YGNodeStyleSetMarginAutoJNI(this.mNativePointer, yogaEdge.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMarginPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetMarginPercentJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMaxHeight(float f) {
        YogaNative.jni_YGNodeStyleSetMaxHeightJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMaxHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMaxHeightPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMaxWidth(float f) {
        YogaNative.jni_YGNodeStyleSetMaxWidthJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMaxWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMaxWidthPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction) {
        this.mMeasureFunction = yogaMeasureFunction;
        YogaNative.jni_YGNodeSetHasMeasureFuncJNI(this.mNativePointer, yogaMeasureFunction != null);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMinHeight(float f) {
        YogaNative.jni_YGNodeStyleSetMinHeightJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMinHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMinHeightPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMinWidth(float f) {
        YogaNative.jni_YGNodeStyleSetMinWidthJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setMinWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMinWidthPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setOverflow(YogaOverflow yogaOverflow) {
        YogaNative.jni_YGNodeStyleSetOverflowJNI(this.mNativePointer, yogaOverflow.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setPadding(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPaddingJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setPaddingPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPaddingPercentJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setPosition(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPositionJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setPositionPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPositionPercentJNI(this.mNativePointer, yogaEdge.mIntValue, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setPositionType(YogaPositionType yogaPositionType) {
        YogaNative.jni_YGNodeStyleSetPositionTypeJNI(this.mNativePointer, yogaPositionType.mIntValue);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setWidth(float f) {
        YogaNative.jni_YGNodeStyleSetWidthJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setWidthAuto() {
        YogaNative.jni_YGNodeStyleSetWidthAutoJNI(this.mNativePointer);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetWidthPercentJNI(this.mNativePointer, f);
    }

    @Override // com.facebook.yoga.YogaNode
    public void setWrap(YogaWrap yogaWrap) {
        YogaNative.jni_YGNodeStyleSetFlexWrapJNI(this.mNativePointer, yogaWrap.mIntValue);
    }

    public YogaNodeJNIBase() {
        this(YogaNative.jni_YGNodeNewJNI());
    }
}
