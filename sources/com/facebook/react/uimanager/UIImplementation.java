package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.os.Trace;
import android.view.View;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewManager;
import java.util.Objects;
/* loaded from: classes.dex */
public class UIImplementation {
    public final EventDispatcher mEventDispatcher;
    public final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    public final UIViewOperationQueue mOperationsQueue;
    public final ReactApplicationContext mReactContext;
    public final ShadowNodeRegistry mShadowNodeRegistry;
    public final ViewManagerRegistry mViewManagers;
    public Object uiImplementationThreadLock = new Object();
    public final int[] mMeasureBuffer = new int[4];
    public long mLastCalculateLayoutTime = 0;

    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int i) {
        UIViewOperationQueue uIViewOperationQueue = new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry), i);
        ShadowNodeRegistry shadowNodeRegistry = new ShadowNodeRegistry();
        this.mShadowNodeRegistry = shadowNodeRegistry;
        this.mReactContext = reactApplicationContext;
        this.mViewManagers = viewManagerRegistry;
        this.mOperationsQueue = uIViewOperationQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(uIViewOperationQueue, shadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    public void applyUpdatesRecursive(ReactShadowNode reactShadowNode, float f, float f2) {
        if (reactShadowNode.hasUpdates()) {
            Iterable<? extends ReactShadowNode> calculateLayoutOnChildren = reactShadowNode.calculateLayoutOnChildren();
            if (calculateLayoutOnChildren != null) {
                for (ReactShadowNode reactShadowNode2 : calculateLayoutOnChildren) {
                    applyUpdatesRecursive(reactShadowNode2, reactShadowNode.getLayoutX() + f, reactShadowNode.getLayoutY() + f2);
                }
            }
            int reactTag = reactShadowNode.getReactTag();
            if (!this.mShadowNodeRegistry.isRootNode(reactTag) && reactShadowNode.dispatchUpdates(f, f2, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && reactShadowNode.shouldNotifyOnLayout()) {
                this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(reactTag, reactShadowNode.getScreenX(), reactShadowNode.getScreenY(), reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight()));
            }
            reactShadowNode.markUpdateSeen();
        }
    }

    public final void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode reactShadowNode) {
        ViewManager viewManager = this.mViewManagers.get(reactShadowNode.getViewClass());
        R$dimen.assertNotNull(viewManager);
        ViewManager viewManager2 = viewManager;
        if (!(viewManager2 instanceof IViewManagerWithChildren)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Trying to use view ");
            outline13.append(reactShadowNode.getViewClass());
            outline13.append(" as a parent, but its Manager doesn't extends ViewGroupManager");
            throw new IllegalViewOperationException(outline13.toString());
        } else if (((IViewManagerWithChildren) viewManager2).needsCustomLayoutForChildren()) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (");
            outline132.append(reactShadowNode.getViewClass());
            outline132.append("). Use measure instead.");
            throw new IllegalViewOperationException(outline132.toString());
        }
    }

    public final void assertViewExists(int i, String str) {
        ShadowNodeRegistry shadowNodeRegistry = this.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        if (shadowNodeRegistry.mTagsToCSSNodes.get(i) == null) {
            throw new IllegalViewOperationException("Unable to execute operation " + str + " on view with tag: " + i + ", since the view does not exists");
        }
    }

    public void calculateRootLayout(ReactShadowNode reactShadowNode) {
        reactShadowNode.getReactTag();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            int intValue = reactShadowNode.getWidthMeasureSpec().intValue();
            int intValue2 = reactShadowNode.getHeightMeasureSpec().intValue();
            float f = Float.NaN;
            float size = View.MeasureSpec.getMode(intValue) == 0 ? Float.NaN : View.MeasureSpec.getSize(intValue);
            if (View.MeasureSpec.getMode(intValue2) != 0) {
                f = View.MeasureSpec.getSize(intValue2);
            }
            reactShadowNode.calculateLayout(size, f);
        } finally {
            Trace.endSection();
            this.mLastCalculateLayoutTime = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    public void dispatchViewUpdates(int i) {
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            updateViewHierarchy();
            this.mNativeViewHierarchyOptimizer.mTagsWithLayoutVisited.clear();
            this.mOperationsQueue.dispatchViewUpdates(i, uptimeMillis, this.mLastCalculateLayoutTime);
        } finally {
            Trace.endSection();
        }
    }

    public void handleCreateView(ReactShadowNode reactShadowNode, ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer = this.mNativeViewHierarchyOptimizer;
            ThemedReactContext themedContext = reactShadowNode.getThemedContext();
            Objects.requireNonNull(nativeViewHierarchyOptimizer);
            reactShadowNode.setIsLayoutOnly(reactShadowNode.getViewClass().equals(ReactViewManager.REACT_CLASS) && NativeViewHierarchyOptimizer.isLayoutOnlyAndCollapsable(reactStylesDiffMap));
            if (reactShadowNode.getNativeKind$enumunboxing$() != 3) {
                nativeViewHierarchyOptimizer.mUIViewOperationQueue.enqueueCreateView(themedContext, reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
        if (r26 == null) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
        if (r11 != r26.size()) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        throw new com.facebook.react.uimanager.IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void manageChildren(int r22, com.facebook.react.bridge.ReadableArray r23, com.facebook.react.bridge.ReadableArray r24, com.facebook.react.bridge.ReadableArray r25, com.facebook.react.bridge.ReadableArray r26, com.facebook.react.bridge.ReadableArray r27) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIImplementation.manageChildren(int, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray):void");
    }

    public final void measureLayout(int i, int i2, int[] iArr) {
        ShadowNodeRegistry shadowNodeRegistry = this.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        ShadowNodeRegistry shadowNodeRegistry2 = this.mShadowNodeRegistry;
        shadowNodeRegistry2.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode2 = shadowNodeRegistry2.mTagsToCSSNodes.get(i2);
        if (reactShadowNode == null || reactShadowNode2 == null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Tag ");
            if (reactShadowNode != null) {
                i = i2;
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport.outline10(outline13, i, " does not exist"));
        }
        if (reactShadowNode != reactShadowNode2) {
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                if (parent == null) {
                    throw new IllegalViewOperationException(GeneratedOutlineSupport.outline5("Tag ", i2, " is not an ancestor of tag ", i));
                }
            }
        }
        measureLayoutRelativeToVerifiedAncestor(reactShadowNode, reactShadowNode2, iArr);
    }

    public final void measureLayoutRelativeToParent(int i, int[] iArr) {
        ShadowNodeRegistry shadowNodeRegistry = this.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode != null) {
            ReactShadowNode parent = reactShadowNode.getParent();
            if (parent != null) {
                measureLayoutRelativeToVerifiedAncestor(reactShadowNode, parent, iArr);
                return;
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport.outline4("View with tag ", i, " doesn't have a parent!"));
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport.outline4("No native view for tag ", i, " exists!"));
    }

    public final void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int[] iArr) {
        int i;
        int i2;
        if (reactShadowNode != reactShadowNode2) {
            i2 = Math.round(reactShadowNode.getLayoutX());
            i = Math.round(reactShadowNode.getLayoutY());
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                R$dimen.assertNotNull(parent);
                assertNodeDoesNotNeedCustomLayoutForChildren(parent);
                i2 += Math.round(parent.getLayoutX());
                i += Math.round(parent.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        } else {
            i2 = 0;
            i = 0;
        }
        iArr[0] = i2;
        iArr[1] = i;
        iArr[2] = reactShadowNode.getScreenWidth();
        iArr[3] = reactShadowNode.getScreenHeight();
    }

    public final void notifyOnBeforeLayoutRecursive(ReactShadowNode reactShadowNode) {
        if (reactShadowNode.hasUpdates()) {
            for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
                notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(i));
            }
            reactShadowNode.onBeforeLayout(this.mNativeViewHierarchyOptimizer);
        }
    }

    public final void removeShadowNodeRecursive(ReactShadowNode reactShadowNode) {
        reactShadowNode.removeAllNativeChildren();
        ShadowNodeRegistry shadowNodeRegistry = this.mShadowNodeRegistry;
        int reactTag = reactShadowNode.getReactTag();
        shadowNodeRegistry.mThreadAsserter.assertNow();
        if (!shadowNodeRegistry.mRootTags.get(reactTag)) {
            shadowNodeRegistry.mTagsToCSSNodes.remove(reactTag);
            int childCount = reactShadowNode.getChildCount();
            while (true) {
                childCount--;
                if (childCount >= 0) {
                    removeShadowNodeRecursive(reactShadowNode.getChildAt(childCount));
                } else {
                    reactShadowNode.removeAndDisposeAllChildren();
                    return;
                }
            }
        } else {
            throw new IllegalViewOperationException(GeneratedOutlineSupport.outline4("Trying to remove root node ", reactTag, " without using removeRootNode!"));
        }
    }

    public void updateViewHierarchy() {
        Trace.beginSection("UIImplementation.updateViewHierarchy");
        int i = 0;
        while (true) {
            try {
                ShadowNodeRegistry shadowNodeRegistry = this.mShadowNodeRegistry;
                shadowNodeRegistry.mThreadAsserter.assertNow();
                if (i < shadowNodeRegistry.mRootTags.size()) {
                    ShadowNodeRegistry shadowNodeRegistry2 = this.mShadowNodeRegistry;
                    shadowNodeRegistry2.mThreadAsserter.assertNow();
                    ReactShadowNode node = this.mShadowNodeRegistry.getNode(shadowNodeRegistry2.mRootTags.keyAt(i));
                    if (!(node.getWidthMeasureSpec() == null || node.getHeightMeasureSpec() == null)) {
                        node.getReactTag();
                        notifyOnBeforeLayoutRecursive(node);
                        Trace.endSection();
                        calculateRootLayout(node);
                        node.getReactTag();
                        applyUpdatesRecursive(node, 0.0f, 0.0f);
                        Trace.endSection();
                    }
                    i++;
                } else {
                    Trace.endSection();
                    return;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }
}
