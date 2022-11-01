package com.swmansion.gesturehandler;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.swmansion.gesturehandler.react.RNGestureHandlerInteractionManager;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
/* loaded from: classes.dex */
public class GestureHandlerOrchestrator {
    public final GestureHandlerRegistry mHandlerRegistry;
    public final ViewConfigurationHelper mViewConfigHelper;
    public final ViewGroup mWrapperView;
    public static final PointF sTempPoint = new PointF();
    public static final float[] sMatrixTransformCoords = new float[2];
    public static final Matrix sInverseMatrix = new Matrix();
    public static final float[] sTempCoords = new float[2];
    public static final Comparator<GestureHandler> sHandlersComparator = new Comparator<GestureHandler>() { // from class: com.swmansion.gesturehandler.GestureHandlerOrchestrator.1
        @Override // java.util.Comparator
        public int compare(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            boolean z;
            GestureHandler gestureHandler3 = gestureHandler;
            GestureHandler gestureHandler4 = gestureHandler2;
            boolean z2 = gestureHandler3.mIsActive;
            if ((z2 && gestureHandler4.mIsActive) || ((z = gestureHandler3.mIsAwaiting) && gestureHandler4.mIsAwaiting)) {
                return Integer.signum(gestureHandler4.mActivationIndex - gestureHandler3.mActivationIndex);
            }
            if (z2) {
                return -1;
            }
            if (!gestureHandler4.mIsActive) {
                if (z) {
                    return -1;
                }
                if (!gestureHandler4.mIsAwaiting) {
                    return 0;
                }
            }
            return 1;
        }
    };
    public final GestureHandler[] mGestureHandlers = new GestureHandler[20];
    public final GestureHandler[] mAwaitingHandlers = new GestureHandler[20];
    public final GestureHandler[] mPreparedHandlers = new GestureHandler[20];
    public final GestureHandler[] mHandlersToCancel = new GestureHandler[20];
    public int mGestureHandlersCount = 0;
    public int mAwaitingHandlersCount = 0;
    public boolean mIsHandlingTouch = false;
    public int mHandlingChangeSemaphore = 0;
    public boolean mFinishedHandlersCleanupScheduled = false;
    public int mActivationIndex = 0;
    public float mMinAlphaForTraversal = 0.0f;

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        this.mWrapperView = viewGroup;
        this.mHandlerRegistry = gestureHandlerRegistry;
        this.mViewConfigHelper = viewConfigurationHelper;
    }

    public static boolean isFinished(int i) {
        return i == 3 || i == 1 || i == 5;
    }

    public static boolean isTransformedTouchPointInView(float f, float f2, View view) {
        return f >= 0.0f && f <= ((float) view.getWidth()) && f2 >= 0.0f && f2 < ((float) view.getHeight());
    }

    public static boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        boolean z;
        int i = 0;
        while (true) {
            int[] iArr = gestureHandler.mTrackedPointerIDs;
            if (i < iArr.length) {
                if (iArr[i] != -1 && gestureHandler2.mTrackedPointerIDs[i] != -1) {
                    z = true;
                    break;
                }
                i++;
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            return false;
        }
        if (gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler)) {
            return false;
        }
        if (gestureHandler == gestureHandler2 || (!gestureHandler.mIsAwaiting && gestureHandler.mState != 4)) {
            return true;
        }
        return gestureHandler.shouldBeCancelledBy(gestureHandler2);
    }

    public static boolean shouldHandlerWaitForOther(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        boolean z;
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager;
        int[] iArr;
        if (gestureHandler != gestureHandler2) {
            Objects.requireNonNull(gestureHandler);
            if (!(gestureHandler2 == gestureHandler || (rNGestureHandlerInteractionManager = gestureHandler.mInteractionController) == null || (iArr = rNGestureHandlerInteractionManager.mWaitForRelations.get(gestureHandler.mTag)) == null)) {
                for (int i : iArr) {
                    if (i == gestureHandler2.mTag) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
        return (!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(fArr[0], fArr[1], view);
    }

    public static void transformTouchPointToViewCoords(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f + viewGroup.getScrollX()) - view.getLeft();
        float scrollY = (f2 + viewGroup.getScrollY()) - view.getTop();
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = sMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = sInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            float f3 = fArr[0];
            scrollY = fArr[1];
            scrollX = f3;
        }
        pointF.set(scrollX, scrollY);
    }

    public final void cleanupAwaitingHandlers() {
        int i = 0;
        for (int i2 = 0; i2 < this.mAwaitingHandlersCount; i2++) {
            GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
            if (gestureHandlerArr[i2].mIsAwaiting) {
                i++;
                gestureHandlerArr[i] = gestureHandlerArr[i2];
            }
        }
        this.mAwaitingHandlersCount = i;
    }

    public final void cleanupFinishedHandlers() {
        boolean z = false;
        for (int i = this.mGestureHandlersCount - 1; i >= 0; i--) {
            GestureHandler gestureHandler = this.mGestureHandlers[i];
            if (isFinished(gestureHandler.mState) && !gestureHandler.mIsAwaiting) {
                this.mGestureHandlers[i] = null;
                gestureHandler.mView = null;
                gestureHandler.mOrchestrator = null;
                Arrays.fill(gestureHandler.mTrackedPointerIDs, -1);
                gestureHandler.mTrackedPointersCount = 0;
                gestureHandler.onReset();
                gestureHandler.mIsActive = false;
                gestureHandler.mIsAwaiting = false;
                gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                z = true;
            }
        }
        if (z) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.mGestureHandlersCount; i3++) {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (gestureHandlerArr[i3] != null) {
                    i2++;
                    gestureHandlerArr[i2] = gestureHandlerArr[i3];
                }
            }
            this.mGestureHandlersCount = i2;
        }
        this.mFinishedHandlersCleanupScheduled = false;
    }

    public final void extractCoordsForView(View view, MotionEvent motionEvent, float[] fArr) {
        if (view == this.mWrapperView) {
            fArr[0] = motionEvent.getX();
            fArr[1] = motionEvent.getY();
        } else if (view == null || !(view.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Parent is null? View is no longer in the tree");
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            extractCoordsForView(viewGroup, motionEvent, fArr);
            PointF pointF = sTempPoint;
            transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, view, pointF);
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean extractGestureHandlers(android.view.ViewGroup r9, float[] r10, int r11) {
        /*
            r8 = this;
            int r0 = r9.getChildCount()
            r1 = 1
            int r0 = r0 - r1
        L_0x0006:
            r2 = 0
            if (r0 < 0) goto L_0x00aa
            com.swmansion.gesturehandler.ViewConfigurationHelper r3 = r8.mViewConfigHelper
            com.swmansion.gesturehandler.react.RNViewConfigurationHelper r3 = (com.swmansion.gesturehandler.react.RNViewConfigurationHelper) r3
            java.util.Objects.requireNonNull(r3)
            boolean r3 = r9 instanceof com.facebook.react.views.view.ReactViewGroup
            if (r3 == 0) goto L_0x0030
            r3 = r9
            com.facebook.react.views.view.ReactViewGroup r3 = (com.facebook.react.views.view.ReactViewGroup) r3
            com.facebook.react.uimanager.ViewGroupDrawingOrderHelper r4 = r3.mDrawingOrderHelper
            boolean r4 = r4.shouldEnableCustomDrawingOrder()
            if (r4 == 0) goto L_0x002a
            com.facebook.react.uimanager.ViewGroupDrawingOrderHelper r4 = r3.mDrawingOrderHelper
            int r3 = r3.getChildCount()
            int r3 = r4.getChildDrawingOrder(r3, r0)
            goto L_0x002b
        L_0x002a:
            r3 = r0
        L_0x002b:
            android.view.View r3 = r9.getChildAt(r3)
            goto L_0x0034
        L_0x0030:
            android.view.View r3 = r9.getChildAt(r0)
        L_0x0034:
            int r4 = r3.getVisibility()
            if (r4 != 0) goto L_0x0046
            float r4 = r3.getAlpha()
            float r5 = r8.mMinAlphaForTraversal
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 < 0) goto L_0x0046
            r4 = 1
            goto L_0x0047
        L_0x0046:
            r4 = 0
        L_0x0047:
            if (r4 == 0) goto L_0x00a6
            android.graphics.PointF r4 = com.swmansion.gesturehandler.GestureHandlerOrchestrator.sTempPoint
            r5 = r10[r2]
            r6 = r10[r1]
            transformTouchPointToViewCoords(r5, r6, r9, r3, r4)
            r5 = r10[r2]
            r6 = r10[r1]
            float r7 = r4.x
            r10[r2] = r7
            float r4 = r4.y
            r10[r1] = r4
            boolean r4 = r3 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x008b
            com.swmansion.gesturehandler.ViewConfigurationHelper r4 = r8.mViewConfigHelper
            r7 = r3
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
            com.swmansion.gesturehandler.react.RNViewConfigurationHelper r4 = (com.swmansion.gesturehandler.react.RNViewConfigurationHelper) r4
            java.util.Objects.requireNonNull(r4)
            boolean r4 = r7.getClipChildren()
            if (r4 != 0) goto L_0x0085
            boolean r4 = r7 instanceof com.facebook.react.views.view.ReactViewGroup
            if (r4 == 0) goto L_0x0083
            com.facebook.react.views.view.ReactViewGroup r7 = (com.facebook.react.views.view.ReactViewGroup) r7
            java.lang.String r4 = r7.getOverflow()
            java.lang.String r7 = "hidden"
            boolean r4 = r7.equals(r4)
            goto L_0x0086
        L_0x0083:
            r4 = 0
            goto L_0x0086
        L_0x0085:
            r4 = 1
        L_0x0086:
            if (r4 == 0) goto L_0x0089
            goto L_0x008b
        L_0x0089:
            r4 = 0
            goto L_0x008c
        L_0x008b:
            r4 = 1
        L_0x008c:
            if (r4 == 0) goto L_0x009b
            r4 = r10[r2]
            r7 = r10[r1]
            boolean r4 = isTransformedTouchPointInView(r4, r7, r3)
            if (r4 == 0) goto L_0x0099
            goto L_0x009b
        L_0x0099:
            r3 = 0
            goto L_0x009f
        L_0x009b:
            boolean r3 = r8.traverseWithPointerEvents(r3, r10, r11)
        L_0x009f:
            r10[r2] = r5
            r10[r1] = r6
            if (r3 == 0) goto L_0x00a6
            return r1
        L_0x00a6:
            int r0 = r0 + (-1)
            goto L_0x0006
        L_0x00aa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandlerOrchestrator.extractGestureHandlers(android.view.ViewGroup, float[], int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean recordViewHandlersForPointer(android.view.View r12, float[] r13, int r14) {
        /*
            r11 = this;
            com.swmansion.gesturehandler.GestureHandlerRegistry r0 = r11.mHandlerRegistry
            com.swmansion.gesturehandler.react.RNGestureHandlerRegistry r0 = (com.swmansion.gesturehandler.react.RNGestureHandlerRegistry) r0
            monitor-enter(r0)
            int r1 = r12.getId()     // Catch: all -> 0x00af
            monitor-enter(r0)     // Catch: all -> 0x00af
            android.util.SparseArray<java.util.ArrayList<com.swmansion.gesturehandler.GestureHandler>> r2 = r0.mHandlersForView     // Catch: all -> 0x00ac
            java.lang.Object r1 = r2.get(r1)     // Catch: all -> 0x00ac
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: all -> 0x00ac
            monitor-exit(r0)     // Catch: all -> 0x00af
            monitor-exit(r0)
            r0 = 0
            if (r1 == 0) goto L_0x00ab
            int r2 = r1.size()
            r3 = 0
            r4 = 0
        L_0x001d:
            if (r3 >= r2) goto L_0x00aa
            java.lang.Object r5 = r1.get(r3)
            com.swmansion.gesturehandler.GestureHandler r5 = (com.swmansion.gesturehandler.GestureHandler) r5
            boolean r6 = r5.mEnabled
            r7 = 1
            if (r6 == 0) goto L_0x00a6
            r6 = r13[r0]
            r8 = r13[r7]
            boolean r6 = r5.isWithinBounds(r12, r6, r8)
            if (r6 == 0) goto L_0x00a6
            r4 = 0
        L_0x0035:
            int r6 = r11.mGestureHandlersCount
            r8 = -1
            if (r4 >= r6) goto L_0x0044
            com.swmansion.gesturehandler.GestureHandler[] r6 = r11.mGestureHandlers
            r6 = r6[r4]
            if (r6 != r5) goto L_0x0041
            goto L_0x006d
        L_0x0041:
            int r4 = r4 + 1
            goto L_0x0035
        L_0x0044:
            com.swmansion.gesturehandler.GestureHandler[] r4 = r11.mGestureHandlers
            int r9 = r4.length
            if (r6 >= r9) goto L_0x009e
            int r9 = r6 + 1
            r11.mGestureHandlersCount = r9
            r4[r6] = r5
            r5.mIsActive = r0
            r5.mIsAwaiting = r0
            r4 = 2147483647(0x7fffffff, float:NaN)
            r5.mActivationIndex = r4
            android.view.View r4 = r5.mView
            if (r4 != 0) goto L_0x0096
            com.swmansion.gesturehandler.GestureHandlerOrchestrator r4 = r5.mOrchestrator
            if (r4 != 0) goto L_0x0096
            int[] r4 = r5.mTrackedPointerIDs
            java.util.Arrays.fill(r4, r8)
            r5.mTrackedPointersCount = r0
            r5.mState = r0
            r5.mView = r12
            r5.mOrchestrator = r11
        L_0x006d:
            int[] r4 = r5.mTrackedPointerIDs
            r6 = r4[r14]
            if (r6 != r8) goto L_0x0094
            r6 = 0
        L_0x0074:
            int r8 = r5.mTrackedPointersCount
            if (r6 >= r8) goto L_0x008d
            r8 = 0
        L_0x0079:
            int[] r9 = r5.mTrackedPointerIDs
            int r10 = r9.length
            if (r8 >= r10) goto L_0x0086
            r10 = r9[r8]
            if (r10 != r6) goto L_0x0083
            goto L_0x0086
        L_0x0083:
            int r8 = r8 + 1
            goto L_0x0079
        L_0x0086:
            int r9 = r9.length
            if (r8 != r9) goto L_0x008a
            goto L_0x008d
        L_0x008a:
            int r6 = r6 + 1
            goto L_0x0074
        L_0x008d:
            r4[r14] = r6
            int r4 = r5.mTrackedPointersCount
            int r4 = r4 + r7
            r5.mTrackedPointersCount = r4
        L_0x0094:
            r4 = 1
            goto L_0x00a6
        L_0x0096:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "Already prepared or hasn't been reset"
            r12.<init>(r13)
            throw r12
        L_0x009e:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "Too many recognizers"
            r12.<init>(r13)
            throw r12
        L_0x00a6:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x00aa:
            r0 = r4
        L_0x00ab:
            return r0
        L_0x00ac:
            r12 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00af
            throw r12     // Catch: all -> 0x00af
        L_0x00af:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandlerOrchestrator.recordViewHandlersForPointer(android.view.View, float[], int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r1 == com.facebook.react.uimanager.PointerEvents.BOX_ONLY) goto L_0x0038;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean traverseWithPointerEvents(android.view.View r8, float[] r9, int r10) {
        /*
            r7 = this;
            com.swmansion.gesturehandler.ViewConfigurationHelper r0 = r7.mViewConfigHelper
            com.swmansion.gesturehandler.react.RNViewConfigurationHelper r0 = (com.swmansion.gesturehandler.react.RNViewConfigurationHelper) r0
            java.util.Objects.requireNonNull(r0)
            com.facebook.react.uimanager.PointerEvents r0 = com.facebook.react.uimanager.PointerEvents.AUTO
            boolean r1 = r8 instanceof com.facebook.react.uimanager.ReactPointerEventsView
            if (r1 == 0) goto L_0x0015
            r1 = r8
            com.facebook.react.uimanager.ReactPointerEventsView r1 = (com.facebook.react.uimanager.ReactPointerEventsView) r1
            com.facebook.react.uimanager.PointerEvents r1 = r1.getPointerEvents()
            goto L_0x0016
        L_0x0015:
            r1 = r0
        L_0x0016:
            boolean r2 = r8.isEnabled()
            r3 = 2
            r4 = 1
            r5 = 4
            r6 = 3
            if (r2 != 0) goto L_0x0028
            if (r1 != r0) goto L_0x0023
            goto L_0x0036
        L_0x0023:
            com.facebook.react.uimanager.PointerEvents r0 = com.facebook.react.uimanager.PointerEvents.BOX_ONLY
            if (r1 != r0) goto L_0x0028
            goto L_0x0038
        L_0x0028:
            int r0 = r1.ordinal()
            if (r0 == 0) goto L_0x0038
            if (r0 == r4) goto L_0x0036
            if (r0 == r3) goto L_0x0034
            r0 = 4
            goto L_0x0039
        L_0x0034:
            r0 = 3
            goto L_0x0039
        L_0x0036:
            r0 = 2
            goto L_0x0039
        L_0x0038:
            r0 = 1
        L_0x0039:
            r1 = 0
            if (r0 != r4) goto L_0x003d
            return r1
        L_0x003d:
            if (r0 != r6) goto L_0x004e
            boolean r10 = r7.recordViewHandlersForPointer(r8, r9, r10)
            if (r10 != 0) goto L_0x004d
            boolean r8 = shouldHandlerlessViewBecomeTouchTarget(r8, r9)
            if (r8 == 0) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r4 = 0
        L_0x004d:
            return r4
        L_0x004e:
            if (r0 != r3) goto L_0x005c
            boolean r0 = r8 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x005b
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            boolean r8 = r7.extractGestureHandlers(r8, r9, r10)
            return r8
        L_0x005b:
            return r1
        L_0x005c:
            if (r0 != r5) goto L_0x007c
            boolean r0 = r8 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x006a
            r0 = r8
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            boolean r0 = r7.extractGestureHandlers(r0, r9, r10)
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            boolean r10 = r7.recordViewHandlersForPointer(r8, r9, r10)
            if (r10 != 0) goto L_0x007b
            if (r0 != 0) goto L_0x007b
            boolean r8 = shouldHandlerlessViewBecomeTouchTarget(r8, r9)
            if (r8 == 0) goto L_0x007a
            goto L_0x007b
        L_0x007a:
            r4 = 0
        L_0x007b:
            return r4
        L_0x007c:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Unknown pointer event type: "
            java.lang.StringBuilder r9 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r9)
            java.lang.String r10 = androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility.getEnum$name$$com$swmansion$gesturehandler$PointerEventsConfig(r0)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandlerOrchestrator.traverseWithPointerEvents(android.view.View, float[], int):boolean");
    }

    public final void tryActivate(GestureHandler gestureHandler) {
        boolean z;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.mGestureHandlersCount) {
                z = false;
                break;
            }
            GestureHandler gestureHandler2 = this.mGestureHandlers[i2];
            if (!isFinished(gestureHandler2.mState) && shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            while (true) {
                int i3 = this.mAwaitingHandlersCount;
                if (i >= i3) {
                    GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                    if (i3 < gestureHandlerArr.length) {
                        this.mAwaitingHandlersCount = i3 + 1;
                        gestureHandlerArr[i3] = gestureHandler;
                        gestureHandler.mIsAwaiting = true;
                        int i4 = this.mActivationIndex;
                        this.mActivationIndex = i4 + 1;
                        gestureHandler.mActivationIndex = i4;
                        return;
                    }
                    throw new IllegalStateException("Too many recognizers");
                } else if (this.mAwaitingHandlers[i] != gestureHandler) {
                    i++;
                } else {
                    return;
                }
            }
        } else {
            int i5 = gestureHandler.mState;
            gestureHandler.mIsAwaiting = false;
            gestureHandler.mIsActive = true;
            int i6 = this.mActivationIndex;
            this.mActivationIndex = i6 + 1;
            gestureHandler.mActivationIndex = i6;
            int i7 = 0;
            for (int i8 = 0; i8 < this.mGestureHandlersCount; i8++) {
                GestureHandler gestureHandler3 = this.mGestureHandlers[i8];
                if (shouldHandlerBeCancelledBy(gestureHandler3, gestureHandler)) {
                    i7++;
                    this.mHandlersToCancel[i7] = gestureHandler3;
                }
            }
            for (int i9 = i7 - 1; i9 >= 0; i9--) {
                this.mHandlersToCancel[i9].cancel();
            }
            for (int i10 = this.mAwaitingHandlersCount - 1; i10 >= 0; i10--) {
                GestureHandler gestureHandler4 = this.mAwaitingHandlers[i10];
                if (shouldHandlerBeCancelledBy(gestureHandler4, gestureHandler)) {
                    gestureHandler4.cancel();
                    gestureHandler4.mIsAwaiting = false;
                }
            }
            cleanupAwaitingHandlers();
            gestureHandler.dispatchStateChange(4, 2);
            if (i5 != 4) {
                gestureHandler.dispatchStateChange(5, 4);
                if (i5 != 5) {
                    gestureHandler.dispatchStateChange(0, 5);
                }
            }
            gestureHandler.mIsAwaiting = false;
        }
    }
}
