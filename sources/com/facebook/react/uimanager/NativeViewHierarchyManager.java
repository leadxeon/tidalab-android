package com.facebook.react.uimanager;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.widget.PopupMenu;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.react.uimanager.layoutanimation.LayoutHandlingAnimation;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public class NativeViewHierarchyManager {
    public boolean mLayoutAnimationEnabled;
    public PopupMenu mPopupMenu;
    public final RootViewManager mRootViewManager;
    public final ViewManagerRegistry mViewManagers;
    public final JSResponderHandler mJSResponderHandler = new JSResponderHandler();
    public final LayoutAnimationController mLayoutAnimator = new LayoutAnimationController();
    public final SparseArray<SparseIntArray> mTagsToPendingIndicesToDelete = new SparseArray<>();
    public final int[] mDroppedViewArray = new int[100];
    public final RectF mBoundingBox = new RectF();
    public int mDroppedViewIndex = 0;
    public final SparseArray<View> mTagsToViews = new SparseArray<>();
    public final SparseArray<ViewManager> mTagsToViewManagers = new SparseArray<>();
    public final SparseBooleanArray mRootTags = new SparseBooleanArray();

    /* renamed from: com.facebook.react.uimanager.NativeViewHierarchyManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements LayoutAnimationListener {
        public final /* synthetic */ int val$indexToDelete;
        public final /* synthetic */ SparseIntArray val$pendingIndicesToDelete;
        public final /* synthetic */ ViewGroupManager val$viewManager;
        public final /* synthetic */ View val$viewToDestroy;
        public final /* synthetic */ ViewGroup val$viewToManage;

        public AnonymousClass1(ViewGroupManager viewGroupManager, ViewGroup viewGroup, View view, SparseIntArray sparseIntArray, int i) {
            this.val$viewManager = viewGroupManager;
            this.val$viewToManage = viewGroup;
            this.val$viewToDestroy = view;
            this.val$pendingIndicesToDelete = sparseIntArray;
            this.val$indexToDelete = i;
        }

        public void onAnimationEnd() {
            UiThreadUtil.assertOnUiThread();
            this.val$viewManager.removeView(this.val$viewToManage, this.val$viewToDestroy);
            NativeViewHierarchyManager.this.dropView(this.val$viewToDestroy);
            this.val$pendingIndicesToDelete.put(this.val$indexToDelete, Math.max(0, this.val$pendingIndicesToDelete.get(this.val$indexToDelete, 0) - 1));
        }
    }

    /* loaded from: classes.dex */
    public static class PopupMenuCallbackHandler implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
        public boolean mConsumed = false;
        public final Callback mSuccess;

        public PopupMenuCallbackHandler(Callback callback, AnonymousClass1 r2) {
            this.mSuccess = callback;
        }

        @Override // android.widget.PopupMenu.OnDismissListener
        public void onDismiss(PopupMenu popupMenu) {
            if (!this.mConsumed) {
                this.mSuccess.invoke(DialogModule.ACTION_DISMISSED);
                this.mConsumed = true;
            }
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (this.mConsumed) {
                return false;
            }
            this.mSuccess.invoke("itemSelected", Integer.valueOf(menuItem.getOrder()));
            this.mConsumed = true;
            return true;
        }
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry) {
        RootViewManager rootViewManager = new RootViewManager();
        this.mViewManagers = viewManagerRegistry;
        this.mRootViewManager = rootViewManager;
    }

    public static String constructManageChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, int[] iArr, ViewAtIndex[] viewAtIndexArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("View tag:");
            outline13.append(viewGroup.getId());
            outline13.append("\n");
            sb.append(outline13.toString());
            sb.append("  children(" + viewGroupManager.getChildCount(viewGroup) + "): [\n");
            for (int i = 0; i < viewGroupManager.getChildCount(viewGroup); i += 16) {
                int i2 = 0;
                while (true) {
                    int i3 = i + i2;
                    if (i3 < viewGroupManager.getChildCount(viewGroup) && i2 < 16) {
                        sb.append(viewGroupManager.getChildAt(viewGroup, i3).getId() + ",");
                        i2++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr != null) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("  indicesToRemove(");
            outline132.append(iArr.length);
            outline132.append("): [\n");
            sb.append(outline132.toString());
            for (int i4 = 0; i4 < iArr.length; i4 += 16) {
                int i5 = 0;
                while (true) {
                    int i6 = i4 + i5;
                    if (i6 < iArr.length && i5 < 16) {
                        sb.append(iArr[i6] + ",");
                        i5++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (viewAtIndexArr != null) {
            StringBuilder outline133 = GeneratedOutlineSupport.outline13("  viewsToAdd(");
            outline133.append(viewAtIndexArr.length);
            outline133.append("): [\n");
            sb.append(outline133.toString());
            for (int i7 = 0; i7 < viewAtIndexArr.length; i7 += 16) {
                int i8 = 0;
                while (true) {
                    int i9 = i7 + i8;
                    if (i9 < viewAtIndexArr.length && i8 < 16) {
                        StringBuilder outline134 = GeneratedOutlineSupport.outline13("[");
                        outline134.append(viewAtIndexArr[i9].mIndex);
                        outline134.append(",");
                        outline134.append(viewAtIndexArr[i9].mTag);
                        outline134.append("],");
                        sb.append(outline134.toString());
                        i8++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr2 != null) {
            StringBuilder outline135 = GeneratedOutlineSupport.outline13("  tagsToDelete(");
            outline135.append(iArr2.length);
            outline135.append("): [\n");
            sb.append(outline135.toString());
            for (int i10 = 0; i10 < iArr2.length; i10 += 16) {
                int i11 = 0;
                while (true) {
                    int i12 = i10 + i11;
                    if (i12 < iArr2.length && i11 < 16) {
                        sb.append(iArr2[i12] + ",");
                        i11++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    public final synchronized void addRootViewGroup(int i, View view) {
        if (view.getId() != -1) {
            FLog.e("NativeViewHierarchyManager", "Trying to add a root view with an explicit id (" + view.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.mTagsToViews.put(i, view);
        this.mTagsToViewManagers.put(i, this.mRootViewManager);
        this.mRootTags.put(i, true);
        view.setId(i);
    }

    public final void computeBoundingBox(View view, int[] iArr) {
        this.mBoundingBox.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        RectF rectF = this.mBoundingBox;
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            matrix.mapRect(rectF);
        }
        rectF.offset(view.getLeft(), view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset(-view2.getScrollX(), -view2.getScrollY());
            Matrix matrix2 = view2.getMatrix();
            if (!matrix2.isIdentity()) {
                matrix2.mapRect(rectF);
            }
            rectF.offset(view2.getLeft(), view2.getTop());
            parent = view2.getParent();
        }
        iArr[0] = Math.round(this.mBoundingBox.left);
        iArr[1] = Math.round(this.mBoundingBox.top);
        RectF rectF2 = this.mBoundingBox;
        iArr[2] = Math.round(rectF2.right - rectF2.left);
        RectF rectF3 = this.mBoundingBox;
        iArr[3] = Math.round(rectF3.bottom - rectF3.top);
    }

    public synchronized void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (view != null) {
            if (this.mTagsToViewManagers.get(view.getId()) != null) {
                if (!this.mRootTags.get(view.getId())) {
                    resolveViewManager(view.getId()).onDropViewInstance(view);
                }
                ViewManager viewManager = this.mTagsToViewManagers.get(view.getId());
                if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
                    for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                        View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                        if (childAt == null) {
                            FLog.e("NativeViewHierarchyManager", "Unable to drop null child view");
                        } else if (this.mTagsToViews.get(childAt.getId()) != null) {
                            dropView(childAt);
                        }
                    }
                    viewGroupManager.removeAllViews(viewGroup);
                }
                this.mTagsToPendingIndicesToDelete.remove(view.getId());
                this.mTagsToViews.remove(view.getId());
                this.mTagsToViewManagers.remove(view.getId());
            }
        }
    }

    public synchronized void measure(int i, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            View view2 = (View) R$style.getRootView(view);
            if (view2 != null) {
                computeBoundingBox(view2, iArr);
                int i2 = iArr[0];
                int i3 = iArr[1];
                computeBoundingBox(view, iArr);
                iArr[0] = iArr[0] - i2;
                iArr[1] = iArr[1] - i3;
            } else {
                throw new NoSuchNativeViewException("Native view " + i + " is no longer on screen");
            }
        } else {
            throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
        }
    }

    public synchronized void measureInWindow(int i, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            view.getLocationOnScreen(iArr);
            Resources resources = view.getContext().getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                iArr[1] = iArr[1] - ((int) resources.getDimension(identifier));
            }
            iArr[2] = view.getWidth();
            iArr[3] = view.getHeight();
        } else {
            throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
        }
    }

    public final synchronized View resolveView(int i) {
        View view;
        view = this.mTagsToViews.get(i);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + i + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager resolveViewManager(int i) {
        ViewManager viewManager;
        viewManager = this.mTagsToViewManagers.get(i);
        if (viewManager == null) {
            boolean contains = Arrays.asList(this.mDroppedViewArray).contains(Integer.valueOf(i));
            throw new IllegalViewOperationException("ViewManager for tag " + i + " could not be found.\n View already dropped? " + contains + ".\nLast index " + this.mDroppedViewIndex + " in last 100 views" + this.mDroppedViewArray.toString());
        }
        return viewManager;
    }

    public final void updateLayout(View view, int i, int i2, int i3, int i4) {
        if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(view)) {
            view.layout(i, i2, i3 + i, i4 + i2);
            return;
        }
        final LayoutAnimationController layoutAnimationController = this.mLayoutAnimator;
        Objects.requireNonNull(layoutAnimationController);
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        LayoutHandlingAnimation layoutHandlingAnimation = layoutAnimationController.mLayoutHandlers.get(id);
        if (layoutHandlingAnimation != null) {
            layoutHandlingAnimation.onLayoutUpdate(i, i2, i3, i4);
            return;
        }
        Animation createAnimation = ((view.getWidth() == 0 || view.getHeight() == 0) ? layoutAnimationController.mLayoutCreateAnimation : layoutAnimationController.mLayoutUpdateAnimation).createAnimation(view, i, i2, i3, i4);
        if (createAnimation instanceof LayoutHandlingAnimation) {
            createAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.remove(id);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.put(id, (LayoutHandlingAnimation) animation);
                }
            });
        } else {
            view.layout(i, i2, i3 + i, i4 + i2);
        }
        if (createAnimation != null) {
            long duration = createAnimation.getDuration();
            if (duration > layoutAnimationController.mMaxAnimationDuration) {
                layoutAnimationController.mMaxAnimationDuration = duration;
                layoutAnimationController.scheduleCompletionCallback(duration);
            }
            view.startAnimation(createAnimation);
        }
    }

    public synchronized void updateProperties(int i, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        try {
            ViewManager resolveViewManager = resolveViewManager(i);
            View resolveView = resolveView(i);
            if (reactStylesDiffMap != null) {
                resolveViewManager.updateProperties(resolveView, reactStylesDiffMap);
            }
        } catch (IllegalViewOperationException e) {
            FLog.e("NativeViewHierarchyManager", "Unable to update properties for view tag " + i, e);
        }
    }
}
