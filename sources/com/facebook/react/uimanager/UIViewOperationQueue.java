package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.os.Trace;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.debug.DidJSUpdateUiDuringFrameDetector;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class UIViewOperationQueue {
    public long mCreateViewCount;
    public final DispatchUIFrameCallback mDispatchUIFrameCallback;
    public final NativeViewHierarchyManager mNativeViewHierarchyManager;
    public long mNonBatchedExecutionTotalTime;
    public long mProfiledBatchBatchedExecutionTime;
    public long mProfiledBatchCommitEndTime;
    public long mProfiledBatchCommitStartTime;
    public long mProfiledBatchDispatchViewUpdatesTime;
    public long mProfiledBatchLayoutTime;
    public long mProfiledBatchNonBatchedExecutionTime;
    public long mProfiledBatchRunEndTime;
    public long mProfiledBatchRunStartTime;
    public final ReactApplicationContext mReactApplicationContext;
    public long mThreadCpuTime;
    public long mUpdatePropertiesOperationCount;
    public NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;
    public final int[] mMeasureBuffer = new int[4];
    public final Object mDispatchRunnablesLock = new Object();
    public final Object mNonBatchedOperationsLock = new Object();
    public ArrayList<UIOperation> mOperations = new ArrayList<>();
    public ArrayList<Runnable> mDispatchUIRunnables = new ArrayList<>();
    public ArrayDeque<UIOperation> mNonBatchedOperations = new ArrayDeque<>();
    public boolean mIsDispatchUIFrameCallbackEnqueued = false;
    public boolean mIsInIllegalUIState = false;
    public boolean mIsProfilingNextBatch = false;

    /* loaded from: classes.dex */
    public final class ChangeJSResponderOperation extends ViewOperation {
        public final boolean mBlockNativeResponder;
        public final boolean mClearResponder;
        public final int mInitialTag;

        public ChangeJSResponderOperation(int i, int i2, boolean z, boolean z2) {
            super(UIViewOperationQueue.this, i);
            this.mInitialTag = i2;
            this.mClearResponder = z;
            this.mBlockNativeResponder = z2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            if (!this.mClearResponder) {
                NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
                int i = this.mTag;
                int i2 = this.mInitialTag;
                boolean z = this.mBlockNativeResponder;
                synchronized (nativeViewHierarchyManager) {
                    if (!z) {
                        nativeViewHierarchyManager.mJSResponderHandler.setJSResponder(i2, null);
                        return;
                    }
                    View view = nativeViewHierarchyManager.mTagsToViews.get(i);
                    if (i2 == i || !(view instanceof ViewParent)) {
                        if (nativeViewHierarchyManager.mRootTags.get(i)) {
                            SoftAssertions.assertUnreachable("Cannot block native responder on " + i + " that is a root view");
                        }
                        nativeViewHierarchyManager.mJSResponderHandler.setJSResponder(i2, view.getParent());
                        return;
                    }
                    nativeViewHierarchyManager.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
                    return;
                }
            }
            JSResponderHandler jSResponderHandler = UIViewOperationQueue.this.mNativeViewHierarchyManager.mJSResponderHandler;
            jSResponderHandler.mCurrentJSResponder = -1;
            ViewParent viewParent = jSResponderHandler.mViewParentBlockingNativeResponder;
            if (viewParent != null) {
                viewParent.requestDisallowInterceptTouchEvent(false);
                jSResponderHandler.mViewParentBlockingNativeResponder = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class ConfigureLayoutAnimationOperation implements UIOperation {
        public final Callback mAnimationComplete;
        public final ReadableMap mConfig;

        public ConfigureLayoutAnimationOperation(ReadableMap readableMap, Callback callback, AnonymousClass1 r4) {
            this.mConfig = readableMap;
            this.mAnimationComplete = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            ReadableMap readableMap = this.mConfig;
            final Callback callback = this.mAnimationComplete;
            final LayoutAnimationController layoutAnimationController = nativeViewHierarchyManager.mLayoutAnimator;
            if (readableMap == null) {
                layoutAnimationController.reset();
                return;
            }
            int i = 0;
            layoutAnimationController.mShouldAnimateLayout = false;
            if (readableMap.hasKey("duration")) {
                i = readableMap.getInt("duration");
            }
            LayoutAnimationType layoutAnimationType = LayoutAnimationType.CREATE;
            if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType))) {
                layoutAnimationController.mLayoutCreateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType)), i);
                layoutAnimationController.mShouldAnimateLayout = true;
            }
            LayoutAnimationType layoutAnimationType2 = LayoutAnimationType.UPDATE;
            if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType2))) {
                layoutAnimationController.mLayoutUpdateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType2)), i);
                layoutAnimationController.mShouldAnimateLayout = true;
            }
            LayoutAnimationType layoutAnimationType3 = LayoutAnimationType.DELETE;
            if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType3))) {
                layoutAnimationController.mLayoutDeleteAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType3)), i);
                layoutAnimationController.mShouldAnimateLayout = true;
            }
            if (layoutAnimationController.mShouldAnimateLayout && callback != null) {
                layoutAnimationController.mCompletionRunnable = new Runnable(layoutAnimationController, callback) { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.1
                    public final /* synthetic */ Callback val$completionCallback;

                    {
                        this.val$completionCallback = callback;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.val$completionCallback.invoke(Boolean.TRUE);
                    }
                };
            }
        }
    }

    /* loaded from: classes.dex */
    public final class CreateViewOperation extends ViewOperation {
        public final String mClassName;
        public final ReactStylesDiffMap mInitialProps;
        public final ThemedReactContext mThemedContext;

        public CreateViewOperation(ThemedReactContext themedReactContext, int i, String str, ReactStylesDiffMap reactStylesDiffMap) {
            super(UIViewOperationQueue.this, i);
            this.mThemedContext = themedReactContext;
            this.mClassName = str;
            this.mInitialProps = reactStylesDiffMap;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            int i = this.mTag;
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            ThemedReactContext themedReactContext = this.mThemedContext;
            String str = this.mClassName;
            ReactStylesDiffMap reactStylesDiffMap = this.mInitialProps;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                ViewManager viewManager = nativeViewHierarchyManager.mViewManagers.get(str);
                View createView = viewManager.createView(themedReactContext, null, null, nativeViewHierarchyManager.mJSResponderHandler);
                nativeViewHierarchyManager.mTagsToViews.put(i, createView);
                nativeViewHierarchyManager.mTagsToViewManagers.put(i, viewManager);
                createView.setId(i);
                if (reactStylesDiffMap != null) {
                    viewManager.updateProperties(createView, reactStylesDiffMap);
                }
                Trace.endSection();
            }
        }
    }

    /* loaded from: classes.dex */
    public final class DismissPopupMenuOperation implements UIOperation {
        public DismissPopupMenuOperation(AnonymousClass1 r2) {
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            PopupMenu popupMenu = UIViewOperationQueue.this.mNativeViewHierarchyManager.mPopupMenu;
            if (popupMenu != null) {
                popupMenu.dismiss();
            }
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public final class DispatchCommandOperation extends ViewOperation {
        public final ReadableArray mArgs;
        public final int mCommand;

        public DispatchCommandOperation(int i, int i2, ReadableArray readableArray) {
            super(UIViewOperationQueue.this, i);
            this.mCommand = i2;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            int i2 = this.mCommand;
            ReadableArray readableArray = this.mArgs;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                View view = nativeViewHierarchyManager.mTagsToViews.get(i);
                if (view != null) {
                    nativeViewHierarchyManager.resolveViewManager(i).receiveCommand((ViewManager) view, i2, readableArray);
                } else {
                    throw new IllegalViewOperationException("Trying to send command to a non-existing view with tag " + i);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public final class DispatchStringCommandOperation extends ViewOperation {
        public final ReadableArray mArgs;
        public final String mCommand;

        public DispatchStringCommandOperation(int i, String str, ReadableArray readableArray) {
            super(UIViewOperationQueue.this, i);
            this.mCommand = str;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            String str = this.mCommand;
            ReadableArray readableArray = this.mArgs;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                View view = nativeViewHierarchyManager.mTagsToViews.get(i);
                if (view != null) {
                    nativeViewHierarchyManager.resolveViewManager(i).receiveCommand((ViewManager) view, str, readableArray);
                } else {
                    throw new IllegalViewOperationException("Trying to send command to a non-existing view with tag " + i);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class DispatchUIFrameCallback extends GuardedFrameCallback {
        public final int mMinTimeLeftInFrameForNonBatchedOperationMs;

        public DispatchUIFrameCallback(ReactContext reactContext, int i, AnonymousClass1 r4) {
            super(reactContext);
            this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
        }

        public final void dispatchPendingNonBatchedOperations(long j) {
            UIOperation pollFirst;
            while (16 - ((System.nanoTime() - j) / 1000000) >= this.mMinTimeLeftInFrameForNonBatchedOperationMs) {
                synchronized (UIViewOperationQueue.this.mNonBatchedOperationsLock) {
                    if (!UIViewOperationQueue.this.mNonBatchedOperations.isEmpty()) {
                        pollFirst = UIViewOperationQueue.this.mNonBatchedOperations.pollFirst();
                    } else {
                        return;
                    }
                }
                try {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    pollFirst.execute();
                    UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                    uIViewOperationQueue.mNonBatchedExecutionTotalTime = (SystemClock.uptimeMillis() - uptimeMillis) + uIViewOperationQueue.mNonBatchedExecutionTotalTime;
                } catch (Exception e) {
                    UIViewOperationQueue.this.mIsInIllegalUIState = true;
                    throw e;
                }
            }
        }

        /* JADX WARN: Finally extract failed */
        @Override // com.facebook.react.uimanager.GuardedFrameCallback
        public void doFrameGuarded(long j) {
            if (UIViewOperationQueue.this.mIsInIllegalUIState) {
                FLog.w("ReactNative", "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            Trace.beginSection("dispatchNonBatchedUIOperations");
            try {
                dispatchPendingNonBatchedOperations(j);
                Trace.endSection();
                UIViewOperationQueue.this.flushPendingBatches();
                ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(2, this);
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    public final class FindTargetForTouchOperation implements UIOperation {
        public final Callback mCallback;
        public final int mReactTag;
        public final float mTargetX;
        public final float mTargetY;

        public FindTargetForTouchOperation(int i, float f, float f2, Callback callback, AnonymousClass1 r6) {
            this.mReactTag = i;
            this.mTargetX = f;
            this.mTargetY = f2;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            int findTargetTagAndCoordinatesForTouch;
            try {
                UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                uIViewOperationQueue.mNativeViewHierarchyManager.measure(this.mReactTag, uIViewOperationQueue.mMeasureBuffer);
                UIViewOperationQueue uIViewOperationQueue2 = UIViewOperationQueue.this;
                int[] iArr = uIViewOperationQueue2.mMeasureBuffer;
                float f = iArr[0];
                float f2 = iArr[1];
                NativeViewHierarchyManager nativeViewHierarchyManager = uIViewOperationQueue2.mNativeViewHierarchyManager;
                int i = this.mReactTag;
                float f3 = this.mTargetX;
                float f4 = this.mTargetY;
                synchronized (nativeViewHierarchyManager) {
                    UiThreadUtil.assertOnUiThread();
                    View view = nativeViewHierarchyManager.mTagsToViews.get(i);
                    if (view != null) {
                        findTargetTagAndCoordinatesForTouch = TouchTargetHelper.findTargetTagAndCoordinatesForTouch(f3, f4, (ViewGroup) view, TouchTargetHelper.mEventCoords, null);
                    } else {
                        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
                    }
                }
                try {
                    UIViewOperationQueue uIViewOperationQueue3 = UIViewOperationQueue.this;
                    uIViewOperationQueue3.mNativeViewHierarchyManager.measure(findTargetTagAndCoordinatesForTouch, uIViewOperationQueue3.mMeasureBuffer);
                    this.mCallback.invoke(Integer.valueOf(findTargetTagAndCoordinatesForTouch), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0] - f)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1] - f2)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
                } catch (IllegalViewOperationException unused) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException unused2) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class ManageChildrenOperation extends ViewOperation {
        public final int[] mIndicesToDelete;
        public final int[] mIndicesToRemove;
        public final int[] mTagsToDelete;
        public final ViewAtIndex[] mViewsToAdd;

        public ManageChildrenOperation(int i, int[] iArr, ViewAtIndex[] viewAtIndexArr, int[] iArr2, int[] iArr3) {
            super(UIViewOperationQueue.this, i);
            this.mIndicesToRemove = iArr;
            this.mViewsToAdd = viewAtIndexArr;
            this.mTagsToDelete = iArr2;
            this.mIndicesToDelete = iArr3;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            boolean z;
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            int[] iArr = this.mIndicesToRemove;
            ViewAtIndex[] viewAtIndexArr = this.mViewsToAdd;
            int[] iArr2 = this.mTagsToDelete;
            int[] iArr3 = this.mIndicesToDelete;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                SparseIntArray sparseIntArray = nativeViewHierarchyManager.mTagsToPendingIndicesToDelete.get(i);
                if (sparseIntArray == null) {
                    sparseIntArray = new SparseIntArray();
                    nativeViewHierarchyManager.mTagsToPendingIndicesToDelete.put(i, sparseIntArray);
                }
                ViewGroup viewGroup = (ViewGroup) nativeViewHierarchyManager.mTagsToViews.get(i);
                ViewGroupManager viewGroupManager = (ViewGroupManager) nativeViewHierarchyManager.resolveViewManager(i);
                if (viewGroup != null) {
                    int childCount = viewGroupManager.getChildCount(viewGroup);
                    if (iArr != null) {
                        int length = iArr.length - 1;
                        while (length >= 0) {
                            int i2 = iArr[length];
                            if (i2 < 0) {
                                throw new IllegalViewOperationException("Trying to remove a negative view index:" + i2 + " view tag: " + i + "\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                            } else if (i2 >= viewGroupManager.getChildCount(viewGroup)) {
                                if (!nativeViewHierarchyManager.mRootTags.get(i) || viewGroupManager.getChildCount(viewGroup) != 0) {
                                    throw new IllegalViewOperationException("Trying to remove a view index above child count " + i2 + " view tag: " + i + "\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                                }
                                return;
                            } else if (i2 < childCount) {
                                int i3 = i2;
                                for (int i4 = 0; i4 <= i2; i4++) {
                                    i3 += sparseIntArray.get(i4);
                                }
                                View childAt = viewGroupManager.getChildAt(viewGroup, i3);
                                if (nativeViewHierarchyManager.mLayoutAnimationEnabled && nativeViewHierarchyManager.mLayoutAnimator.shouldAnimateLayout(childAt)) {
                                    int id = childAt.getId();
                                    if (iArr2 != null) {
                                        for (int i5 : iArr2) {
                                            if (i5 == id) {
                                                z = true;
                                                break;
                                            }
                                        }
                                    }
                                    z = false;
                                    if (z) {
                                        length--;
                                        childCount = i2;
                                    }
                                }
                                viewGroupManager.removeViewAt(viewGroup, i3);
                                length--;
                                childCount = i2;
                            } else {
                                throw new IllegalViewOperationException("Trying to remove an out of order view index:" + i2 + " view tag: " + i + "\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                            }
                        }
                    }
                    if (iArr2 != null) {
                        for (int i6 = 0; i6 < iArr2.length; i6++) {
                            int i7 = iArr2[i6];
                            int i8 = iArr3[i6];
                            View view = nativeViewHierarchyManager.mTagsToViews.get(i7);
                            if (view != null) {
                                if (!nativeViewHierarchyManager.mLayoutAnimationEnabled || !nativeViewHierarchyManager.mLayoutAnimator.shouldAnimateLayout(view)) {
                                    iArr3 = iArr3;
                                    sparseIntArray = sparseIntArray;
                                    viewGroupManager = viewGroupManager;
                                    nativeViewHierarchyManager.dropView(view);
                                } else {
                                    sparseIntArray.put(i8, sparseIntArray.get(i8, 0) + 1);
                                    iArr3 = iArr3;
                                    sparseIntArray = sparseIntArray;
                                    viewGroupManager = viewGroupManager;
                                    nativeViewHierarchyManager.mLayoutAnimator.deleteView(view, new NativeViewHierarchyManager.AnonymousClass1(viewGroupManager, viewGroup, view, sparseIntArray, i8));
                                }
                            } else {
                                throw new IllegalViewOperationException("Trying to destroy unknown view tag: " + i7 + "\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                            }
                        }
                    }
                    if (viewAtIndexArr != null) {
                        int i9 = 0;
                        while (i9 < viewAtIndexArr.length) {
                            ViewAtIndex viewAtIndex = viewAtIndexArr[i9];
                            View view2 = nativeViewHierarchyManager.mTagsToViews.get(viewAtIndex.mTag);
                            if (view2 != null) {
                                int i10 = viewAtIndex.mIndex;
                                int i11 = i10;
                                int i12 = 0;
                                while (i12 <= i10) {
                                    i11 += sparseIntArray.get(i12);
                                    i12++;
                                    sparseIntArray = sparseIntArray;
                                }
                                viewGroupManager.addView(viewGroup, view2, i11);
                                i9++;
                                sparseIntArray = sparseIntArray;
                            } else {
                                throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag + "\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                            }
                        }
                    }
                    return;
                }
                throw new IllegalViewOperationException("Trying to manageChildren view with tag " + i + " which doesn't exist\n detail: " + NativeViewHierarchyManager.constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
            }
        }
    }

    /* loaded from: classes.dex */
    public final class MeasureInWindowOperation implements UIOperation {
        public final Callback mCallback;
        public final int mReactTag;

        public MeasureInWindowOperation(int i, Callback callback, AnonymousClass1 r4) {
            this.mReactTag = i;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                uIViewOperationQueue.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, uIViewOperationQueue.mMeasureBuffer);
                this.mCallback.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class MeasureOperation implements UIOperation {
        public final Callback mCallback;
        public final int mReactTag;

        public MeasureOperation(int i, Callback callback, AnonymousClass1 r4) {
            this.mReactTag = i;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                uIViewOperationQueue.mNativeViewHierarchyManager.measure(this.mReactTag, uIViewOperationQueue.mMeasureBuffer);
                float dIPFromPixel = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0]);
                float dIPFromPixel2 = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1]);
                this.mCallback.invoke(0, 0, Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])), Float.valueOf(dIPFromPixel), Float.valueOf(dIPFromPixel2));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class RemoveRootViewOperation extends ViewOperation {
        public RemoveRootViewOperation(int i) {
            super(UIViewOperationQueue.this, i);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                if (!nativeViewHierarchyManager.mRootTags.get(i)) {
                    SoftAssertions.assertUnreachable("View with tag " + i + " is not registered as a root view");
                }
                nativeViewHierarchyManager.dropView(nativeViewHierarchyManager.mTagsToViews.get(i));
                nativeViewHierarchyManager.mRootTags.delete(i);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class SendAccessibilityEvent extends ViewOperation {
        public final int mEventType;

        public SendAccessibilityEvent(int i, int i2, AnonymousClass1 r4) {
            super(UIViewOperationQueue.this, i);
            this.mEventType = i2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            int i2 = this.mEventType;
            View view = nativeViewHierarchyManager.mTagsToViews.get(i);
            if (view != null) {
                view.sendAccessibilityEvent(i2);
                return;
            }
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline3("Could not find view with tag ", i));
        }
    }

    /* loaded from: classes.dex */
    public class SetLayoutAnimationEnabledOperation implements UIOperation {
        public final boolean mEnabled;

        public SetLayoutAnimationEnabledOperation(boolean z, AnonymousClass1 r3) {
            this.mEnabled = z;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.mLayoutAnimationEnabled = this.mEnabled;
        }
    }

    /* loaded from: classes.dex */
    public final class ShowPopupMenuOperation extends ViewOperation {
        public final Callback mError;
        public final ReadableArray mItems;
        public final Callback mSuccess;

        public ShowPopupMenuOperation(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
            super(UIViewOperationQueue.this, i);
            this.mItems = readableArray;
            this.mError = callback;
            this.mSuccess = callback2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            ReadableArray readableArray = this.mItems;
            Callback callback = this.mSuccess;
            Callback callback2 = this.mError;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                View view = nativeViewHierarchyManager.mTagsToViews.get(i);
                if (view == null) {
                    callback2.invoke("Can't display popup. Could not find view with tag " + i);
                    return;
                }
                View view2 = nativeViewHierarchyManager.mTagsToViews.get(i);
                if (view2 != null) {
                    PopupMenu popupMenu = new PopupMenu((ThemedReactContext) view2.getContext(), view);
                    nativeViewHierarchyManager.mPopupMenu = popupMenu;
                    Menu menu = popupMenu.getMenu();
                    for (int i2 = 0; i2 < readableArray.size(); i2++) {
                        menu.add(0, 0, i2, readableArray.getString(i2));
                    }
                    NativeViewHierarchyManager.PopupMenuCallbackHandler popupMenuCallbackHandler = new NativeViewHierarchyManager.PopupMenuCallbackHandler(callback, null);
                    nativeViewHierarchyManager.mPopupMenu.setOnMenuItemClickListener(popupMenuCallbackHandler);
                    nativeViewHierarchyManager.mPopupMenu.setOnDismissListener(popupMenuCallbackHandler);
                    nativeViewHierarchyManager.mPopupMenu.show();
                    return;
                }
                throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
            }
        }
    }

    /* loaded from: classes.dex */
    public class UIBlockOperation implements UIOperation {
        public final UIBlock mBlock;

        public UIBlockOperation(UIBlock uIBlock) {
            this.mBlock = uIBlock;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mBlock.execute(UIViewOperationQueue.this.mNativeViewHierarchyManager);
        }
    }

    /* loaded from: classes.dex */
    public interface UIOperation {
        void execute();
    }

    /* loaded from: classes.dex */
    public final class UpdateLayoutOperation extends ViewOperation {
        public final int mHeight;
        public final int mParentTag;
        public final int mWidth;
        public final int mX;
        public final int mY;

        public UpdateLayoutOperation(int i, int i2, int i3, int i4, int i5, int i6) {
            super(UIViewOperationQueue.this, i2);
            this.mParentTag = i;
            this.mX = i3;
            this.mY = i4;
            this.mWidth = i5;
            this.mHeight = i6;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            int i = this.mTag;
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i2 = this.mParentTag;
            int i3 = this.mX;
            int i4 = this.mY;
            int i5 = this.mWidth;
            int i6 = this.mHeight;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                View resolveView = nativeViewHierarchyManager.resolveView(i);
                resolveView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
                ViewParent parent = resolveView.getParent();
                if (parent instanceof RootView) {
                    parent.requestLayout();
                }
                if (!nativeViewHierarchyManager.mRootTags.get(i2)) {
                    ViewManager viewManager = nativeViewHierarchyManager.mTagsToViewManagers.get(i2);
                    if (viewManager instanceof IViewManagerWithChildren) {
                        IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
                        if (iViewManagerWithChildren != null && !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                            nativeViewHierarchyManager.updateLayout(resolveView, i3, i4, i5, i6);
                        }
                    } else {
                        throw new IllegalViewOperationException("Trying to use view with tag " + i2 + " as a parent, but its Manager doesn't implement IViewManagerWithChildren");
                    }
                } else {
                    nativeViewHierarchyManager.updateLayout(resolveView, i3, i4, i5, i6);
                }
                Trace.endSection();
            }
        }
    }

    /* loaded from: classes.dex */
    public final class UpdatePropertiesOperation extends ViewOperation {
        public final ReactStylesDiffMap mProps;

        public UpdatePropertiesOperation(int i, ReactStylesDiffMap reactStylesDiffMap, AnonymousClass1 r4) {
            super(UIViewOperationQueue.this, i);
            this.mProps = reactStylesDiffMap;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateProperties(this.mTag, this.mProps);
        }
    }

    /* loaded from: classes.dex */
    public final class UpdateViewExtraData extends ViewOperation {
        public final Object mExtraData;

        public UpdateViewExtraData(int i, Object obj) {
            super(UIViewOperationQueue.this, i);
            this.mExtraData = obj;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            NativeViewHierarchyManager nativeViewHierarchyManager = UIViewOperationQueue.this.mNativeViewHierarchyManager;
            int i = this.mTag;
            Object obj = this.mExtraData;
            synchronized (nativeViewHierarchyManager) {
                UiThreadUtil.assertOnUiThread();
                nativeViewHierarchyManager.resolveViewManager(i).updateExtraData(nativeViewHierarchyManager.resolveView(i), obj);
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class ViewOperation implements UIOperation {
        public int mTag;

        public ViewOperation(UIViewOperationQueue uIViewOperationQueue, int i) {
            this.mTag = i;
        }
    }

    public UIViewOperationQueue(ReactApplicationContext reactApplicationContext, NativeViewHierarchyManager nativeViewHierarchyManager, int i) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext, i == -1 ? 8 : i, null);
        this.mReactApplicationContext = reactApplicationContext;
    }

    public void dispatchViewUpdates(final int i, final long j, final long j2) {
        final ArrayList<UIOperation> arrayList;
        final ArrayDeque<UIOperation> arrayDeque;
        try {
            final long uptimeMillis = SystemClock.uptimeMillis();
            final long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            if (!this.mOperations.isEmpty()) {
                ArrayList<UIOperation> arrayList2 = this.mOperations;
                this.mOperations = new ArrayList<>();
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            synchronized (this.mNonBatchedOperationsLock) {
                if (!this.mNonBatchedOperations.isEmpty()) {
                    ArrayDeque<UIOperation> arrayDeque2 = this.mNonBatchedOperations;
                    this.mNonBatchedOperations = new ArrayDeque<>();
                    arrayDeque = arrayDeque2;
                } else {
                    arrayDeque = null;
                }
            }
            NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener = this.mViewHierarchyUpdateDebugListener;
            if (notThreadSafeViewHierarchyUpdateDebugListener != null) {
                DidJSUpdateUiDuringFrameDetector didJSUpdateUiDuringFrameDetector = (DidJSUpdateUiDuringFrameDetector) notThreadSafeViewHierarchyUpdateDebugListener;
                synchronized (didJSUpdateUiDuringFrameDetector) {
                    didJSUpdateUiDuringFrameDetector.mViewHierarchyUpdateEnqueuedEvents.add(System.nanoTime());
                }
            }
            Runnable runnable = new Runnable() { // from class: com.facebook.react.uimanager.UIViewOperationQueue.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            long uptimeMillis2 = SystemClock.uptimeMillis();
                            ArrayDeque arrayDeque3 = arrayDeque;
                            if (arrayDeque3 != null) {
                                Iterator it = arrayDeque3.iterator();
                                while (it.hasNext()) {
                                    ((UIOperation) it.next()).execute();
                                }
                            }
                            ArrayList arrayList3 = arrayList;
                            if (arrayList3 != null) {
                                Iterator it2 = arrayList3.iterator();
                                while (it2.hasNext()) {
                                    ((UIOperation) it2.next()).execute();
                                }
                            }
                            UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                            if (uIViewOperationQueue.mIsProfilingNextBatch && uIViewOperationQueue.mProfiledBatchCommitStartTime == 0) {
                                uIViewOperationQueue.mProfiledBatchCommitStartTime = j;
                                uIViewOperationQueue.mProfiledBatchCommitEndTime = SystemClock.uptimeMillis();
                                UIViewOperationQueue uIViewOperationQueue2 = UIViewOperationQueue.this;
                                uIViewOperationQueue2.mProfiledBatchLayoutTime = j2;
                                uIViewOperationQueue2.mProfiledBatchDispatchViewUpdatesTime = uptimeMillis;
                                uIViewOperationQueue2.mProfiledBatchRunStartTime = uptimeMillis2;
                                uIViewOperationQueue2.mProfiledBatchRunEndTime = uIViewOperationQueue2.mProfiledBatchCommitEndTime;
                                uIViewOperationQueue2.mThreadCpuTime = currentThreadTimeMillis;
                            }
                            UIViewOperationQueue.this.mNativeViewHierarchyManager.mLayoutAnimator.reset();
                            NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener2 = UIViewOperationQueue.this.mViewHierarchyUpdateDebugListener;
                            if (notThreadSafeViewHierarchyUpdateDebugListener2 != null) {
                                DidJSUpdateUiDuringFrameDetector didJSUpdateUiDuringFrameDetector2 = (DidJSUpdateUiDuringFrameDetector) notThreadSafeViewHierarchyUpdateDebugListener2;
                                synchronized (didJSUpdateUiDuringFrameDetector2) {
                                    didJSUpdateUiDuringFrameDetector2.mViewHierarchyUpdateFinishedEvents.add(System.nanoTime());
                                }
                            }
                        } catch (Exception e) {
                            UIViewOperationQueue.this.mIsInIllegalUIState = true;
                            throw e;
                        }
                    } finally {
                        Trace.endSection();
                    }
                }
            };
            synchronized (this.mDispatchRunnablesLock) {
                Trace.endSection();
                this.mDispatchUIRunnables.add(runnable);
            }
            if (!this.mIsDispatchUIFrameCallbackEnqueued) {
                UiThreadUtil.runOnUiThread(new GuardedRunnable(this.mReactApplicationContext) { // from class: com.facebook.react.uimanager.UIViewOperationQueue.2
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIViewOperationQueue.this.flushPendingBatches();
                    }
                });
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public void enqueueCreateView(ThemedReactContext themedReactContext, int i, String str, ReactStylesDiffMap reactStylesDiffMap) {
        synchronized (this.mNonBatchedOperationsLock) {
            this.mCreateViewCount++;
            this.mNonBatchedOperations.addLast(new CreateViewOperation(themedReactContext, i, str, reactStylesDiffMap));
        }
    }

    public void enqueueManageChildren(int i, int[] iArr, ViewAtIndex[] viewAtIndexArr, int[] iArr2, int[] iArr3) {
        this.mOperations.add(new ManageChildrenOperation(i, iArr, viewAtIndexArr, iArr2, iArr3));
    }

    public void enqueueUpdateExtraData(int i, Object obj) {
        this.mOperations.add(new UpdateViewExtraData(i, obj));
    }

    public void enqueueUpdateLayout(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mOperations.add(new UpdateLayoutOperation(i, i2, i3, i4, i5, i6));
    }

    public final void flushPendingBatches() {
        if (this.mIsInIllegalUIState) {
            FLog.w("ReactNative", "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        synchronized (this.mDispatchRunnablesLock) {
            if (!this.mDispatchUIRunnables.isEmpty()) {
                ArrayList<Runnable> arrayList = this.mDispatchUIRunnables;
                this.mDispatchUIRunnables = new ArrayList<>();
                long uptimeMillis = SystemClock.uptimeMillis();
                Iterator<Runnable> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                if (this.mIsProfilingNextBatch) {
                    this.mProfiledBatchBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis;
                    this.mProfiledBatchNonBatchedExecutionTime = this.mNonBatchedExecutionTotalTime;
                    this.mIsProfilingNextBatch = false;
                }
                this.mNonBatchedExecutionTotalTime = 0L;
            }
        }
    }
}
