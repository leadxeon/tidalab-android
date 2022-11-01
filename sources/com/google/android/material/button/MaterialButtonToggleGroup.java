package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final String LOG_TAG = MaterialButtonToggleGroup.class.getSimpleName();
    public int checkedId;
    public Integer[] childOrder;
    public boolean selectionRequired;
    public boolean singleSelection;
    public final List<CornerData> originalCornerData = new ArrayList();
    public final CheckedStateTracker checkedStateTracker = new CheckedStateTracker(null);
    public final PressedStateTracker pressedStateTracker = new PressedStateTracker(null);
    public final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners = new LinkedHashSet<>();
    public final Comparator<MaterialButton> childOrderComparator = new Comparator<MaterialButton>() { // from class: com.google.android.material.button.MaterialButtonToggleGroup.1
        @Override // java.util.Comparator
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            MaterialButton materialButton3 = materialButton;
            MaterialButton materialButton4 = materialButton2;
            int compareTo = Boolean.valueOf(materialButton3.isChecked()).compareTo(Boolean.valueOf(materialButton4.isChecked()));
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = Boolean.valueOf(materialButton3.isPressed()).compareTo(Boolean.valueOf(materialButton4.isPressed()));
            return compareTo2 != 0 ? compareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton3)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton4)));
        }
    };
    public boolean skipCheckedStateTracker = false;

    /* loaded from: classes.dex */
    public class CheckedStateTracker implements MaterialButton.OnCheckedChangeListener {
        public CheckedStateTracker(AnonymousClass1 r2) {
        }

        @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
        public void onCheckedChanged(MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
            if (!materialButtonToggleGroup.skipCheckedStateTracker) {
                if (materialButtonToggleGroup.singleSelection) {
                    materialButtonToggleGroup.checkedId = z ? materialButton.getId() : -1;
                }
                if (MaterialButtonToggleGroup.this.updateCheckedStates(materialButton.getId(), z)) {
                    MaterialButtonToggleGroup.this.dispatchOnButtonChecked(materialButton.getId(), materialButton.isChecked());
                }
                MaterialButtonToggleGroup.this.invalidate();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class CornerData {
        public static final CornerSize noCorner = new AbsoluteCornerSize(0.0f);
        public CornerSize bottomLeft;
        public CornerSize bottomRight;
        public CornerSize topLeft;
        public CornerSize topRight;

        public CornerData(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
            this.topLeft = cornerSize;
            this.topRight = cornerSize3;
            this.bottomRight = cornerSize4;
            this.bottomLeft = cornerSize2;
        }
    }

    /* loaded from: classes.dex */
    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    /* loaded from: classes.dex */
    public class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        public PressedStateTracker(AnonymousClass1 r2) {
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.materialButtonToggleGroupStyle, 2131886769), attributeSet, R.attr.materialButtonToggleGroupStyle);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, R.attr.materialButtonToggleGroupStyle, 2131886769, new int[0]);
        setSingleSelection(obtainStyledAttributes.getBoolean(2, false));
        this.checkedId = obtainStyledAttributes.getResourceId(0, -1);
        this.selectionRequired = obtainStyledAttributes.getBoolean(1, false);
        setChildrenDrawingOrderEnabled(true);
        obtainStyledAttributes.recycle();
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setImportantForAccessibility(1);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (isChildVisible(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && isChildVisible(i2)) {
                i++;
            }
        }
        return i;
    }

    private void setCheckedId(int i) {
        this.checkedId = i;
        dispatchOnButtonChecked(i, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            materialButton.setId(View.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.onCheckedChangeListeners.add(this.checkedStateTracker);
        materialButton.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            updateCheckedStates(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        ShapeAppearanceModel shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.originalCornerData.add(new CornerData(shapeAppearanceModel.topLeftCornerSize, shapeAppearanceModel.bottomLeftCornerSize, shapeAppearanceModel.topRightCornerSize, shapeAppearanceModel.bottomRightCornerSize));
        ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() { // from class: com.google.android.material.button.MaterialButtonToggleGroup.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat.mInfo);
                MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
                String str = MaterialButtonToggleGroup.LOG_TAG;
                Objects.requireNonNull(materialButtonToggleGroup);
                int i2 = -1;
                if (view2 instanceof MaterialButton) {
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        if (i3 >= materialButtonToggleGroup.getChildCount()) {
                            break;
                        } else if (materialButtonToggleGroup.getChildAt(i3) == view2) {
                            i2 = i4;
                            break;
                        } else {
                            if ((materialButtonToggleGroup.getChildAt(i3) instanceof MaterialButton) && materialButtonToggleGroup.isChildVisible(i3)) {
                                i4++;
                            }
                            i3++;
                        }
                    }
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, i2, 1, false, ((MaterialButton) view2).isChecked()));
            }
        });
    }

    public final void adjustChildMarginsAndUpdateLayout() {
        LinearLayout.LayoutParams layoutParams;
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex != -1) {
            for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
                MaterialButton childButton = getChildButton(i);
                int min = Math.min(childButton.getStrokeWidth(), getChildButton(i - 1).getStrokeWidth());
                ViewGroup.LayoutParams layoutParams2 = childButton.getLayoutParams();
                if (layoutParams2 instanceof LinearLayout.LayoutParams) {
                    layoutParams = (LinearLayout.LayoutParams) layoutParams2;
                } else {
                    layoutParams = new LinearLayout.LayoutParams(layoutParams2.width, layoutParams2.height);
                }
                if (getOrientation() == 0) {
                    layoutParams.setMarginEnd(0);
                    layoutParams.setMarginStart(-min);
                    layoutParams.topMargin = 0;
                } else {
                    layoutParams.bottomMargin = 0;
                    layoutParams.topMargin = -min;
                    layoutParams.setMarginStart(0);
                }
                childButton.setLayoutParams(layoutParams);
            }
            if (!(getChildCount() == 0 || firstVisibleChildIndex == -1)) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(firstVisibleChildIndex)).getLayoutParams();
                if (getOrientation() == 1) {
                    layoutParams3.topMargin = 0;
                    layoutParams3.bottomMargin = 0;
                    return;
                }
                layoutParams3.setMarginEnd(0);
                layoutParams3.setMarginStart(0);
                layoutParams3.leftMargin = 0;
                layoutParams3.rightMargin = 0;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(getChildButton(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void dispatchOnButtonChecked(int i, boolean z) {
        Iterator<OnButtonCheckedListener> it = this.onButtonCheckedListeners.iterator();
        while (it.hasNext()) {
            it.next().onButtonChecked(this, i, z);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton childButton = getChildButton(i);
            if (childButton.isChecked()) {
                arrayList.add(Integer.valueOf(childButton.getId()));
            }
        }
        return arrayList;
    }

    public final MaterialButton getChildButton(int i) {
        return (MaterialButton) getChildAt(i);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.childOrder;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w(LOG_TAG, "Child order wasn't updated");
        return i2;
    }

    public final boolean isChildVisible(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            setCheckedStateForView(i, true);
            updateCheckedStates(i, true);
            setCheckedId(i);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getVisibleButtonCount(), false, this.singleSelection ? 1 : 2).mInfo);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.onCheckedChangeListeners.remove(this.checkedStateTracker);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
    }

    public final void setCheckedStateForView(int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton) findViewById).setChecked(z);
            this.skipCheckedStateTracker = false;
        }
    }

    public void setSelectionRequired(boolean z) {
        this.selectionRequired = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.singleSelection != z) {
            this.singleSelection = z;
            this.skipCheckedStateTracker = true;
            for (int i = 0; i < getChildCount(); i++) {
                MaterialButton childButton = getChildButton(i);
                childButton.setChecked(false);
                dispatchOnButtonChecked(childButton.getId(), false);
            }
            this.skipCheckedStateTracker = false;
            setCheckedId(-1);
        }
    }

    public final boolean updateCheckedStates(int i, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (!this.selectionRequired || !checkedButtonIds.isEmpty()) {
            if (z && this.singleSelection) {
                checkedButtonIds.remove(Integer.valueOf(i));
                for (Integer num : checkedButtonIds) {
                    int intValue = num.intValue();
                    setCheckedStateForView(intValue, false);
                    dispatchOnButtonChecked(intValue, false);
                }
            }
            return true;
        }
        setCheckedStateForView(i, true);
        this.checkedId = i;
        return false;
    }

    public void updateChildShapes() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton childButton = getChildButton(i);
            if (childButton.getVisibility() != 8) {
                ShapeAppearanceModel shapeAppearanceModel = childButton.getShapeAppearanceModel();
                Objects.requireNonNull(shapeAppearanceModel);
                ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(shapeAppearanceModel);
                CornerData cornerData = this.originalCornerData.get(i);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z = getOrientation() == 0;
                    if (i == firstVisibleChildIndex) {
                        if (!z) {
                            CornerSize cornerSize = cornerData.topLeft;
                            CornerSize cornerSize2 = CornerData.noCorner;
                            cornerData = new CornerData(cornerSize, cornerSize2, cornerData.topRight, cornerSize2);
                        } else if (R$style.isLayoutRtl(this)) {
                            CornerSize cornerSize3 = CornerData.noCorner;
                            cornerData = new CornerData(cornerSize3, cornerSize3, cornerData.topRight, cornerData.bottomRight);
                        } else {
                            CornerSize cornerSize4 = cornerData.topLeft;
                            CornerSize cornerSize5 = cornerData.bottomLeft;
                            CornerSize cornerSize6 = CornerData.noCorner;
                            cornerData = new CornerData(cornerSize4, cornerSize5, cornerSize6, cornerSize6);
                        }
                    } else if (i != lastVisibleChildIndex) {
                        cornerData = null;
                    } else if (!z) {
                        CornerSize cornerSize7 = CornerData.noCorner;
                        cornerData = new CornerData(cornerSize7, cornerData.bottomLeft, cornerSize7, cornerData.bottomRight);
                    } else if (R$style.isLayoutRtl(this)) {
                        CornerSize cornerSize8 = cornerData.topLeft;
                        CornerSize cornerSize9 = cornerData.bottomLeft;
                        CornerSize cornerSize10 = CornerData.noCorner;
                        cornerData = new CornerData(cornerSize8, cornerSize9, cornerSize10, cornerSize10);
                    } else {
                        CornerSize cornerSize11 = CornerData.noCorner;
                        cornerData = new CornerData(cornerSize11, cornerSize11, cornerData.topRight, cornerData.bottomRight);
                    }
                }
                if (cornerData == null) {
                    builder.setAllCornerSizes(0.0f);
                } else {
                    builder.topLeftCornerSize = cornerData.topLeft;
                    builder.bottomLeftCornerSize = cornerData.bottomLeft;
                    builder.topRightCornerSize = cornerData.topRight;
                    builder.bottomRightCornerSize = cornerData.bottomRight;
                }
                childButton.setShapeAppearanceModel(builder.build());
            }
        }
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
