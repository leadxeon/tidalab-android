package com.facebook.react.uimanager;

import android.util.SparseBooleanArray;
import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class NativeViewHierarchyOptimizer {
    public final ShadowNodeRegistry mShadowNodeRegistry;
    public final SparseBooleanArray mTagsWithLayoutVisited = new SparseBooleanArray();
    public final UIViewOperationQueue mUIViewOperationQueue;

    /* loaded from: classes.dex */
    public static class NodeIndexPair {
        public final int index;
        public final ReactShadowNode node;

        public NodeIndexPair(ReactShadowNode reactShadowNode, int i) {
            this.node = reactShadowNode;
            this.index = i;
        }
    }

    public NativeViewHierarchyOptimizer(UIViewOperationQueue uIViewOperationQueue, ShadowNodeRegistry shadowNodeRegistry) {
        this.mUIViewOperationQueue = uIViewOperationQueue;
        this.mShadowNodeRegistry = shadowNodeRegistry;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0192, code lost:
        if (r3.getInt("borderBottomColor") == 0) goto L_0x01cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01a1, code lost:
        if (r3.getDouble("borderTopWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ae, code lost:
        if (r3.getInt("borderTopColor") == 0) goto L_0x01cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x01bd, code lost:
        if (r3.getDouble("borderRightWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01ca, code lost:
        if (r3.getInt("borderRightColor") == 0) goto L_0x01cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
        if ("box-none".equals(r3) == false) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x010c, code lost:
        if (r3.getInt("backgroundColor") != 0) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0122, code lost:
        if (r3.getDouble("borderWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0132, code lost:
        if (r3.getDouble("borderWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0146, code lost:
        if ("visible".equals(r3.getString("overflow")) == false) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0156, code lost:
        if (r3.getDouble("borderLeftWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0164, code lost:
        if (r3.getInt("borderLeftColor") == 0) goto L_0x01cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0176, code lost:
        if (r3.getDouble("opacity") != 1.0d) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0185, code lost:
        if (r3.getDouble("borderBottomWidth") != 0.0d) goto L_0x01ce;
     */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01d3 A[LOOP:0: B:14:0x002a->B:123:0x01d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isLayoutOnlyAndCollapsable(com.facebook.react.uimanager.ReactStylesDiffMap r18) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.NativeViewHierarchyOptimizer.isLayoutOnlyAndCollapsable(com.facebook.react.uimanager.ReactStylesDiffMap):boolean");
    }

    public final void addGrandchildren(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i) {
        R$dimen.assertCondition(reactShadowNode2.getNativeKind$enumunboxing$() != 1);
        for (int i2 = 0; i2 < reactShadowNode2.getChildCount(); i2++) {
            ReactShadowNode childAt = reactShadowNode2.getChildAt(i2);
            R$dimen.assertCondition(childAt.getNativeParent() == null);
            int nativeChildCount = reactShadowNode.getNativeChildCount();
            if (childAt.getNativeKind$enumunboxing$() == 3) {
                addGrandchildren(reactShadowNode, childAt, i);
            } else {
                addNativeChild(reactShadowNode, childAt, i);
            }
            i += reactShadowNode.getNativeChildCount() - nativeChildCount;
        }
    }

    public final void addNativeChild(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i) {
        reactShadowNode.addNativeChildAt(reactShadowNode2, i);
        this.mUIViewOperationQueue.enqueueManageChildren(reactShadowNode.getReactTag(), null, new ViewAtIndex[]{new ViewAtIndex(reactShadowNode2.getReactTag(), i)}, null, null);
        if (reactShadowNode2.getNativeKind$enumunboxing$() != 1) {
            addGrandchildren(reactShadowNode, reactShadowNode2, i + 1);
        }
    }

    public final void addNodeToNode(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i) {
        NodeIndexPair nodeIndexPair;
        int nativeOffsetForChild = reactShadowNode.getNativeOffsetForChild(reactShadowNode.getChildAt(i));
        if (reactShadowNode.getNativeKind$enumunboxing$() != 1) {
            while (true) {
                if (reactShadowNode.getNativeKind$enumunboxing$() == 1) {
                    nodeIndexPair = new NodeIndexPair(reactShadowNode, nativeOffsetForChild);
                    break;
                }
                ReactShadowNode parent = reactShadowNode.getParent();
                if (parent == null) {
                    nodeIndexPair = null;
                    break;
                } else {
                    nativeOffsetForChild = nativeOffsetForChild + (reactShadowNode.getNativeKind$enumunboxing$() == 2 ? 1 : 0) + parent.getNativeOffsetForChild(reactShadowNode);
                    reactShadowNode = parent;
                }
            }
            if (nodeIndexPair != null) {
                ReactShadowNode reactShadowNode3 = nodeIndexPair.node;
                nativeOffsetForChild = nodeIndexPair.index;
                reactShadowNode = reactShadowNode3;
            } else {
                return;
            }
        }
        if (reactShadowNode2.getNativeKind$enumunboxing$() != 3) {
            addNativeChild(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        } else {
            addGrandchildren(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        }
    }

    public final void applyLayoutBase(ReactShadowNode reactShadowNode) {
        int reactTag = reactShadowNode.getReactTag();
        if (!this.mTagsWithLayoutVisited.get(reactTag)) {
            this.mTagsWithLayoutVisited.put(reactTag, true);
            int screenX = reactShadowNode.getScreenX();
            int screenY = reactShadowNode.getScreenY();
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != null && parent.getNativeKind$enumunboxing$() != 1; parent = parent.getParent()) {
                if (!parent.isVirtual()) {
                    screenX = Math.round(parent.getLayoutX()) + screenX;
                    screenY = Math.round(parent.getLayoutY()) + screenY;
                }
            }
            applyLayoutRecursive(reactShadowNode, screenX, screenY);
        }
    }

    public final void applyLayoutRecursive(ReactShadowNode reactShadowNode, int i, int i2) {
        if (reactShadowNode.getNativeKind$enumunboxing$() == 3 || reactShadowNode.getNativeParent() == null) {
            for (int i3 = 0; i3 < reactShadowNode.getChildCount(); i3++) {
                ReactShadowNode childAt = reactShadowNode.getChildAt(i3);
                int reactTag = childAt.getReactTag();
                if (!this.mTagsWithLayoutVisited.get(reactTag)) {
                    this.mTagsWithLayoutVisited.put(reactTag, true);
                    applyLayoutRecursive(childAt, childAt.getScreenX() + i, childAt.getScreenY() + i2);
                }
            }
            return;
        }
        this.mUIViewOperationQueue.enqueueUpdateLayout(reactShadowNode.getLayoutParent().getReactTag(), reactShadowNode.getReactTag(), i, i2, reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight());
    }

    public void handleManageChildren(ReactShadowNode reactShadowNode, int[] iArr, ViewAtIndex[] viewAtIndexArr, int[] iArr2) {
        boolean z;
        for (int i : iArr) {
            int i2 = 0;
            while (true) {
                if (i2 >= iArr2.length) {
                    z = false;
                    break;
                } else if (iArr2[i2] == i) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            removeNodeFromParent(this.mShadowNodeRegistry.getNode(i), z);
        }
        for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
            addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(viewAtIndex.mTag), viewAtIndex.mIndex);
        }
    }

    public final void removeNodeFromParent(ReactShadowNode reactShadowNode, boolean z) {
        if (reactShadowNode.getNativeKind$enumunboxing$() != 1) {
            for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
                removeNodeFromParent(reactShadowNode.getChildAt(childCount), z);
            }
        }
        ReactShadowNode nativeParent = reactShadowNode.getNativeParent();
        if (nativeParent != null) {
            int indexOfNativeChild = nativeParent.indexOfNativeChild(reactShadowNode);
            nativeParent.removeNativeChildAt(indexOfNativeChild);
            this.mUIViewOperationQueue.enqueueManageChildren(nativeParent.getReactTag(), new int[]{indexOfNativeChild}, null, z ? new int[]{reactShadowNode.getReactTag()} : null, z ? new int[]{indexOfNativeChild} : null);
        }
    }

    public final void transitionLayoutOnlyViewToNativeView(ReactShadowNode reactShadowNode, ReactStylesDiffMap reactStylesDiffMap) {
        ReactShadowNode parent = reactShadowNode.getParent();
        if (parent == null) {
            reactShadowNode.setIsLayoutOnly(false);
            return;
        }
        int indexOf = parent.indexOf(reactShadowNode);
        parent.removeChildAt(indexOf);
        removeNodeFromParent(reactShadowNode, false);
        reactShadowNode.setIsLayoutOnly(false);
        this.mUIViewOperationQueue.enqueueCreateView(reactShadowNode.getThemedContext(), reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        parent.addChildAt(reactShadowNode, indexOf);
        addNodeToNode(parent, reactShadowNode, indexOf);
        for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
            addNodeToNode(reactShadowNode, reactShadowNode.getChildAt(i), i);
        }
        R$dimen.assertCondition(this.mTagsWithLayoutVisited.size() == 0);
        applyLayoutBase(reactShadowNode);
        for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
            applyLayoutBase(reactShadowNode.getChildAt(i2));
        }
        this.mTagsWithLayoutVisited.clear();
    }
}
