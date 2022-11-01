package com.facebook.react.uimanager;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.views.art.ARTSurfaceViewShadowNode;
import com.facebook.react.views.text.ReactRawTextShadowNode;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.yoga.YogaConfigJNIBase;
import com.facebook.yoga.YogaConfigJNIFinalizer;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNative;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaNodeJNIFinalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public class ReactShadowNodeImpl implements ReactShadowNode<ReactShadowNodeImpl> {
    public static final YogaConfigJNIBase sYogaConfig;
    public ArrayList<ReactShadowNodeImpl> mChildren;
    public Integer mHeightMeasureSpec;
    public boolean mIsLayoutOnly;
    public ReactShadowNodeImpl mLayoutParent;
    public ArrayList<ReactShadowNodeImpl> mNativeChildren;
    public ReactShadowNodeImpl mNativeParent;
    public final float[] mPadding;
    public ReactShadowNodeImpl mParent;
    public int mReactTag;
    public int mRootTag;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mScreenX;
    public int mScreenY;
    public boolean mShouldNotifyOnLayout;
    public ThemedReactContext mThemedContext;
    public String mViewClassName;
    public Integer mWidthMeasureSpec;
    public YogaNode mYogaNode;
    public boolean mNodeUpdated = true;
    public int mTotalNativeChildren = 0;
    public final boolean[] mPaddingIsPercent = new boolean[9];
    public final Spacing mDefaultPadding = new Spacing(0.0f);

    static {
        if (R$style.YOGA_CONFIG == null) {
            YogaConfigJNIFinalizer yogaConfigJNIFinalizer = new YogaConfigJNIFinalizer();
            R$style.YOGA_CONFIG = yogaConfigJNIFinalizer;
            YogaNative.jni_YGConfigSetPointScaleFactorJNI(yogaConfigJNIFinalizer.mNativePointer, 0.0f);
            YogaNative.jni_YGConfigSetUseLegacyStretchBehaviourJNI(R$style.YOGA_CONFIG.mNativePointer, true);
        }
        sYogaConfig = R$style.YOGA_CONFIG;
    }

    public ReactShadowNodeImpl() {
        float[] fArr = new float[9];
        this.mPadding = fArr;
        if (!isVirtual()) {
            YogaNode acquire = YogaNodePool.get().acquire();
            acquire = acquire == null ? new YogaNodeJNIFinalizer(sYogaConfig) : acquire;
            this.mYogaNode = acquire;
            acquire.setData(this);
            Arrays.fill(fArr, Float.NaN);
            return;
        }
        this.mYogaNode = null;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void addNativeChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int i) {
        ReactShadowNodeImpl reactShadowNodeImpl2 = reactShadowNodeImpl;
        boolean z = false;
        R$dimen.assertCondition(getNativeKind$enumunboxing$() == 1);
        if (reactShadowNodeImpl2.getNativeKind$enumunboxing$() != 3) {
            z = true;
        }
        R$dimen.assertCondition(z);
        if (this.mNativeChildren == null) {
            this.mNativeChildren = new ArrayList<>(4);
        }
        this.mNativeChildren.add(i, reactShadowNodeImpl2);
        reactShadowNodeImpl2.mNativeParent = this;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void calculateLayout() {
        this.mYogaNode.calculateLayout(Float.NaN, Float.NaN);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public Iterable<? extends ReactShadowNode> calculateLayoutOnChildren() {
        if (isVirtualAnchor()) {
            return null;
        }
        return this.mChildren;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void dirty() {
        if (!isVirtual()) {
            this.mYogaNode.dirty();
            return;
        }
        ReactShadowNodeImpl reactShadowNodeImpl = this.mParent;
        if (reactShadowNodeImpl != null) {
            reactShadowNodeImpl.dirty();
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public boolean dispatchUpdates(float f, float f2, UIViewOperationQueue uIViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        if (this.mNodeUpdated) {
            onCollectExtraUpdates(uIViewOperationQueue);
        }
        if (!hasNewLayout()) {
            return false;
        }
        float layoutX = getLayoutX();
        float layoutY = getLayoutY();
        float f3 = f + layoutX;
        int round = Math.round(f3);
        float f4 = f2 + layoutY;
        int round2 = Math.round(f4);
        int round3 = Math.round(f3 + this.mYogaNode.getLayoutWidth());
        int round4 = Math.round(f4 + this.mYogaNode.getLayoutHeight());
        int round5 = Math.round(layoutX);
        int round6 = Math.round(layoutY);
        int i = round3 - round;
        int i2 = round4 - round2;
        boolean z = (round5 == this.mScreenX && round6 == this.mScreenY && i == this.mScreenWidth && i2 == this.mScreenHeight) ? false : true;
        this.mScreenX = round5;
        this.mScreenY = round6;
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
        if (z) {
            if (nativeViewHierarchyOptimizer != null) {
                nativeViewHierarchyOptimizer.applyLayoutBase(this);
            } else {
                uIViewOperationQueue.enqueueUpdateLayout(this.mParent.mReactTag, this.mReactTag, round5, round6, i, i2);
            }
        }
        return z;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void dispose() {
        YogaNode yogaNode = this.mYogaNode;
        if (yogaNode != null) {
            yogaNode.reset();
            YogaNodePool.get().release(this.mYogaNode);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final int getChildCount() {
        ArrayList<ReactShadowNodeImpl> arrayList = this.mChildren;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public Integer getHeightMeasureSpec() {
        return this.mHeightMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public ReactShadowNodeImpl getLayoutParent() {
        ReactShadowNodeImpl reactShadowNodeImpl = this.mLayoutParent;
        return reactShadowNodeImpl != null ? reactShadowNodeImpl : this.mNativeParent;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final float getLayoutX() {
        return this.mYogaNode.getLayoutX();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final float getLayoutY() {
        return this.mYogaNode.getLayoutY();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final int getNativeChildCount() {
        ArrayList<ReactShadowNodeImpl> arrayList = this.mNativeChildren;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getNativeKind$enumunboxing$() {
        if (isVirtual() || this.mIsLayoutOnly) {
            return 3;
        }
        return this instanceof ReactTextShadowNode ? 2 : 1;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getNativeOffsetForChild(ReactShadowNodeImpl reactShadowNodeImpl) {
        ReactShadowNodeImpl reactShadowNodeImpl2 = reactShadowNodeImpl;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= getChildCount()) {
                break;
            }
            ReactShadowNodeImpl childAt = getChildAt(i);
            if (reactShadowNodeImpl2 == childAt) {
                z = true;
                break;
            }
            i2 += childAt.getTotalNativeNodeContributionToParent();
            i++;
        }
        if (z) {
            return i2;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Child ");
        outline13.append(reactShadowNodeImpl2.mReactTag);
        outline13.append(" was not a child of ");
        outline13.append(this.mReactTag);
        throw new RuntimeException(outline13.toString());
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public ReactShadowNodeImpl getNativeParent() {
        return this.mNativeParent;
    }

    public final float getPadding(int i) {
        return this.mYogaNode.getLayoutPadding(YogaEdge.fromInt(i));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public ReactShadowNodeImpl getParent() {
        return this.mParent;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final int getReactTag() {
        return this.mReactTag;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final int getRootTag() {
        R$dimen.assertCondition(this.mRootTag != 0);
        return this.mRootTag;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getScreenX() {
        return this.mScreenX;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int getScreenY() {
        return this.mScreenY;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final ThemedReactContext getThemedContext() {
        ThemedReactContext themedReactContext = this.mThemedContext;
        R$dimen.assertNotNull(themedReactContext);
        return themedReactContext;
    }

    public final int getTotalNativeNodeContributionToParent() {
        int nativeKind$enumunboxing$ = getNativeKind$enumunboxing$();
        if (nativeKind$enumunboxing$ == 3) {
            return this.mTotalNativeChildren;
        }
        if (nativeKind$enumunboxing$ == 2) {
            return 1 + this.mTotalNativeChildren;
        }
        return 1;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final String getViewClass() {
        String str = this.mViewClassName;
        R$dimen.assertNotNull(str);
        return str;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public Integer getWidthMeasureSpec() {
        return this.mWidthMeasureSpec;
    }

    public final boolean hasNewLayout() {
        YogaNode yogaNode = this.mYogaNode;
        return yogaNode != null && yogaNode.hasNewLayout();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final boolean hasUpdates() {
        if (!this.mNodeUpdated && !hasNewLayout()) {
            YogaNode yogaNode = this.mYogaNode;
            if (!(yogaNode != null && yogaNode.isDirty())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int indexOf(ReactShadowNodeImpl reactShadowNodeImpl) {
        ReactShadowNodeImpl reactShadowNodeImpl2 = reactShadowNodeImpl;
        ArrayList<ReactShadowNodeImpl> arrayList = this.mChildren;
        if (arrayList == null) {
            return -1;
        }
        return arrayList.indexOf(reactShadowNodeImpl2);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public int indexOfNativeChild(ReactShadowNodeImpl reactShadowNodeImpl) {
        R$dimen.assertNotNull(this.mNativeChildren);
        return this.mNativeChildren.indexOf(reactShadowNodeImpl);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public boolean isDescendantOf(ReactShadowNodeImpl reactShadowNodeImpl) {
        ReactShadowNodeImpl reactShadowNodeImpl2 = reactShadowNodeImpl;
        for (ReactShadowNodeImpl reactShadowNodeImpl3 = this.mParent; reactShadowNodeImpl3 != null; reactShadowNodeImpl3 = reactShadowNodeImpl3.mParent) {
            if (reactShadowNodeImpl3 == reactShadowNodeImpl2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final boolean isLayoutOnly() {
        return this.mIsLayoutOnly;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return this instanceof ReactRawTextShadowNode;
    }

    public boolean isVirtualAnchor() {
        return this instanceof ARTSurfaceViewShadowNode;
    }

    public boolean isYogaLeafNode() {
        return this.mYogaNode.isMeasureDefined();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void markUpdateSeen() {
        YogaNode yogaNode;
        this.mNodeUpdated = false;
        if (hasNewLayout() && (yogaNode = this.mYogaNode) != null) {
            yogaNode.markLayoutSeen();
        }
    }

    public void markUpdated() {
        if (!this.mNodeUpdated) {
            this.mNodeUpdated = true;
            ReactShadowNodeImpl reactShadowNodeImpl = this.mParent;
            if (reactShadowNodeImpl != null) {
                reactShadowNodeImpl.markUpdated();
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void removeAllNativeChildren() {
        ArrayList<ReactShadowNodeImpl> arrayList = this.mNativeChildren;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.mNativeChildren.get(size).mNativeParent = null;
            }
            this.mNativeChildren.clear();
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void removeAndDisposeAllChildren() {
        if (getChildCount() != 0) {
            int i = 0;
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (this.mYogaNode != null && !isYogaLeafNode()) {
                    this.mYogaNode.removeChildAt(childCount);
                }
                ReactShadowNodeImpl childAt = getChildAt(childCount);
                childAt.mParent = null;
                i += childAt.getTotalNativeNodeContributionToParent();
                childAt.dispose();
            }
            ArrayList<ReactShadowNodeImpl> arrayList = this.mChildren;
            R$dimen.assertNotNull(arrayList);
            arrayList.clear();
            markUpdated();
            this.mTotalNativeChildren -= i;
            updateNativeChildrenCountInParent(-i);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public ReactShadowNodeImpl removeChildAt(int i) {
        ArrayList<ReactShadowNodeImpl> arrayList = this.mChildren;
        if (arrayList != null) {
            ReactShadowNodeImpl remove = arrayList.remove(i);
            remove.mParent = null;
            if (this.mYogaNode != null && !isYogaLeafNode()) {
                this.mYogaNode.removeChildAt(i);
            }
            markUpdated();
            int totalNativeNodeContributionToParent = remove.getTotalNativeNodeContributionToParent();
            this.mTotalNativeChildren -= totalNativeNodeContributionToParent;
            updateNativeChildrenCountInParent(-totalNativeNodeContributionToParent);
            return remove;
        }
        throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport.outline4("Index ", i, " out of bounds: node has no children"));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public ReactShadowNodeImpl removeNativeChildAt(int i) {
        R$dimen.assertNotNull(this.mNativeChildren);
        ReactShadowNodeImpl remove = this.mNativeChildren.remove(i);
        remove.mNativeParent = null;
        return remove;
    }

    public void setDefaultPadding(int i, float f) {
        this.mDefaultPadding.set(i, f);
        updatePadding();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void setIsLayoutOnly(boolean z) {
        boolean z2 = true;
        R$dimen.assertCondition(this.mParent == null, "Must remove from no opt parent first");
        R$dimen.assertCondition(this.mNativeParent == null, "Must remove from native parent first");
        if (getNativeChildCount() != 0) {
            z2 = false;
        }
        R$dimen.assertCondition(z2, "Must remove all native children first");
        this.mIsLayoutOnly = z;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setLayoutParent(ReactShadowNodeImpl reactShadowNodeImpl) {
        this.mLayoutParent = reactShadowNodeImpl;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object obj) {
    }

    public void setMargin(int i, float f) {
        this.mYogaNode.setMargin(YogaEdge.fromInt(i), f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setMeasureSpecs(int i, int i2) {
        this.mWidthMeasureSpec = Integer.valueOf(i);
        this.mHeightMeasureSpec = Integer.valueOf(i2);
    }

    public void setPadding(int i, float f) {
        this.mPadding[i] = f;
        this.mPaddingIsPercent[i] = false;
        updatePadding();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setReactTag(int i) {
        this.mReactTag = i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void setRootTag(int i) {
        this.mRootTag = i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setStyleHeight(float f) {
        this.mYogaNode.setHeight(f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setStyleWidth(float f) {
        this.mYogaNode.setWidth(f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        this.mThemedContext = themedReactContext;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void setViewClassName(String str) {
        this.mViewClassName = str;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final boolean shouldNotifyOnLayout() {
        return this.mShouldNotifyOnLayout;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[");
        outline13.append(this.mViewClassName);
        outline13.append(" ");
        return GeneratedOutlineSupport.outline10(outline13, this.mReactTag, "]");
    }

    public final void updateNativeChildrenCountInParent(int i) {
        if (getNativeKind$enumunboxing$() != 1) {
            for (ReactShadowNodeImpl reactShadowNodeImpl = this.mParent; reactShadowNodeImpl != null; reactShadowNodeImpl = reactShadowNodeImpl.mParent) {
                reactShadowNodeImpl.mTotalNativeChildren += i;
                if (reactShadowNodeImpl.getNativeKind$enumunboxing$() == 1) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updatePadding() {
        /*
            r4 = this;
            r0 = 0
        L_0x0001:
            r1 = 8
            if (r0 > r1) goto L_0x00b6
            if (r0 == 0) goto L_0x0062
            r2 = 2
            if (r0 == r2) goto L_0x0062
            r2 = 4
            if (r0 == r2) goto L_0x0062
            r2 = 5
            if (r0 != r2) goto L_0x0011
            goto L_0x0062
        L_0x0011:
            r2 = 1
            if (r0 == r2) goto L_0x0033
            r2 = 3
            if (r0 != r2) goto L_0x0018
            goto L_0x0033
        L_0x0018:
            float[] r1 = r4.mPadding
            r1 = r1[r0]
            boolean r1 = com.facebook.react.R$style.isUndefined(r1)
            if (r1 == 0) goto L_0x0091
            com.facebook.yoga.YogaNode r1 = r4.mYogaNode
            com.facebook.yoga.YogaEdge r2 = com.facebook.yoga.YogaEdge.fromInt(r0)
            com.facebook.react.uimanager.Spacing r3 = r4.mDefaultPadding
            float[] r3 = r3.mSpacing
            r3 = r3[r0]
            r1.setPadding(r2, r3)
            goto L_0x00b2
        L_0x0033:
            float[] r2 = r4.mPadding
            r2 = r2[r0]
            boolean r2 = com.facebook.react.R$style.isUndefined(r2)
            if (r2 == 0) goto L_0x0091
            float[] r2 = r4.mPadding
            r3 = 7
            r2 = r2[r3]
            boolean r2 = com.facebook.react.R$style.isUndefined(r2)
            if (r2 == 0) goto L_0x0091
            float[] r2 = r4.mPadding
            r1 = r2[r1]
            boolean r1 = com.facebook.react.R$style.isUndefined(r1)
            if (r1 == 0) goto L_0x0091
            com.facebook.yoga.YogaNode r1 = r4.mYogaNode
            com.facebook.yoga.YogaEdge r2 = com.facebook.yoga.YogaEdge.fromInt(r0)
            com.facebook.react.uimanager.Spacing r3 = r4.mDefaultPadding
            float[] r3 = r3.mSpacing
            r3 = r3[r0]
            r1.setPadding(r2, r3)
            goto L_0x00b2
        L_0x0062:
            float[] r2 = r4.mPadding
            r2 = r2[r0]
            boolean r2 = com.facebook.react.R$style.isUndefined(r2)
            if (r2 == 0) goto L_0x0091
            float[] r2 = r4.mPadding
            r3 = 6
            r2 = r2[r3]
            boolean r2 = com.facebook.react.R$style.isUndefined(r2)
            if (r2 == 0) goto L_0x0091
            float[] r2 = r4.mPadding
            r1 = r2[r1]
            boolean r1 = com.facebook.react.R$style.isUndefined(r1)
            if (r1 == 0) goto L_0x0091
            com.facebook.yoga.YogaNode r1 = r4.mYogaNode
            com.facebook.yoga.YogaEdge r2 = com.facebook.yoga.YogaEdge.fromInt(r0)
            com.facebook.react.uimanager.Spacing r3 = r4.mDefaultPadding
            float[] r3 = r3.mSpacing
            r3 = r3[r0]
            r1.setPadding(r2, r3)
            goto L_0x00b2
        L_0x0091:
            boolean[] r1 = r4.mPaddingIsPercent
            boolean r1 = r1[r0]
            if (r1 == 0) goto L_0x00a5
            com.facebook.yoga.YogaNode r1 = r4.mYogaNode
            com.facebook.yoga.YogaEdge r2 = com.facebook.yoga.YogaEdge.fromInt(r0)
            float[] r3 = r4.mPadding
            r3 = r3[r0]
            r1.setPaddingPercent(r2, r3)
            goto L_0x00b2
        L_0x00a5:
            com.facebook.yoga.YogaNode r1 = r4.mYogaNode
            com.facebook.yoga.YogaEdge r2 = com.facebook.yoga.YogaEdge.fromInt(r0)
            float[] r3 = r4.mPadding
            r3 = r3[r0]
            r1.setPadding(r2, r3)
        L_0x00b2:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ReactShadowNodeImpl.updatePadding():void");
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final void updateProperties(ReactStylesDiffMap reactStylesDiffMap) {
        Map<Class<?>, ViewManagerPropertyUpdater.ViewManagerSetter<?, ?>> map = ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP;
        ViewManagerPropertyUpdater.ShadowNodeSetter findNodeSetter = ViewManagerPropertyUpdater.findNodeSetter(getClass());
        Iterator<Map.Entry<String, Object>> entryIterator = reactStylesDiffMap.mBackingMap.getEntryIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            findNodeSetter.setProperty(this, next.getKey(), next.getValue());
        }
    }

    public void addChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int i) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<>(4);
        }
        this.mChildren.add(i, reactShadowNodeImpl);
        reactShadowNodeImpl.mParent = this;
        if (this.mYogaNode != null && !isYogaLeafNode()) {
            YogaNode yogaNode = reactShadowNodeImpl.mYogaNode;
            if (yogaNode != null) {
                this.mYogaNode.addChildAt(yogaNode, i);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '");
                outline13.append(reactShadowNodeImpl.toString());
                outline13.append("' to a '");
                outline13.append(toString());
                outline13.append("')");
                throw new RuntimeException(outline13.toString());
            }
        }
        markUpdated();
        int totalNativeNodeContributionToParent = reactShadowNodeImpl.getTotalNativeNodeContributionToParent();
        this.mTotalNativeChildren += totalNativeNodeContributionToParent;
        updateNativeChildrenCountInParent(totalNativeNodeContributionToParent);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public void calculateLayout(float f, float f2) {
        this.mYogaNode.calculateLayout(f, f2);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNode
    public final ReactShadowNodeImpl getChildAt(int i) {
        ArrayList<ReactShadowNodeImpl> arrayList = this.mChildren;
        if (arrayList != null) {
            return arrayList.get(i);
        }
        throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport.outline4("Index ", i, " out of bounds: node has no children"));
    }
}
