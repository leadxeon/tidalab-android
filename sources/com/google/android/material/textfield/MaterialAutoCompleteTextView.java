package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.Locale;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public final AccessibilityManager accessibilityManager;
    public final ListPopupWindow modalListPopup;
    public final Rect tempRect = new Rect();

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.autoCompleteTextViewStyle, 0), attributeSet, R.attr.autoCompleteTextViewStyle);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialAutoCompleteTextView, R.attr.autoCompleteTextViewStyle, 2131886641, new int[0]);
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getInt(0, 0) == 0) {
            setKeyListener(null);
        }
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2, null, R.attr.listPopupWindowStyle, 0);
        this.modalListPopup = listPopupWindow;
        listPopupWindow.setModal(true);
        listPopupWindow.mDropDownAnchorView = this;
        listPopupWindow.mPopup.setInputMethodMode(2);
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.mItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.google.android.material.textfield.MaterialAutoCompleteTextView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Object obj;
                view = null;
                if (i < 0) {
                    ListPopupWindow listPopupWindow2 = MaterialAutoCompleteTextView.this.modalListPopup;
                    if (!listPopupWindow2.isShowing()) {
                        obj = null;
                    } else {
                        obj = listPopupWindow2.mDropDownList.getSelectedItem();
                    }
                } else {
                    obj = MaterialAutoCompleteTextView.this.getAdapter().getItem(i);
                }
                MaterialAutoCompleteTextView.access$100(MaterialAutoCompleteTextView.this, obj);
                AdapterView.OnItemClickListener onItemClickListener = MaterialAutoCompleteTextView.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        ListPopupWindow listPopupWindow3 = MaterialAutoCompleteTextView.this.modalListPopup;
                        if (listPopupWindow3.isShowing()) {
                            view = listPopupWindow3.mDropDownList.getSelectedView();
                        }
                        ListPopupWindow listPopupWindow4 = MaterialAutoCompleteTextView.this.modalListPopup;
                        i = !listPopupWindow4.isShowing() ? -1 : listPopupWindow4.mDropDownList.getSelectedItemPosition();
                        ListPopupWindow listPopupWindow5 = MaterialAutoCompleteTextView.this.modalListPopup;
                        j = !listPopupWindow5.isShowing() ? Long.MIN_VALUE : listPopupWindow5.mDropDownList.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(MaterialAutoCompleteTextView.this.modalListPopup.mDropDownList, view, i, j);
                }
                MaterialAutoCompleteTextView.this.modalListPopup.dismiss();
            }
        };
        obtainStyledAttributes.recycle();
    }

    public static void access$100(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
    }

    public final TextInputLayout findTextInputLayoutAncestor() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor == null || !findTextInputLayoutAncestor.isProvidingHint) {
            return super.getHint();
        }
        return findTextInputLayoutAncestor.getHint();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null && findTextInputLayoutAncestor.isProvidingHint && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
            int i3 = 0;
            if (!(adapter == null || findTextInputLayoutAncestor == null)) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                ListPopupWindow listPopupWindow = this.modalListPopup;
                int min = Math.min(adapter.getCount(), Math.max(0, !listPopupWindow.isShowing() ? -1 : listPopupWindow.mDropDownList.getSelectedItemPosition()) + 15);
                View view = null;
                int i4 = 0;
                for (int max = Math.max(0, min - 15); max < min; max++) {
                    int itemViewType = adapter.getItemViewType(max);
                    if (itemViewType != i3) {
                        view = null;
                        i3 = itemViewType;
                    }
                    view = adapter.getView(max, view, findTextInputLayoutAncestor);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    i4 = Math.max(i4, view.getMeasuredWidth());
                }
                Drawable background = this.modalListPopup.getBackground();
                if (background != null) {
                    background.getPadding(this.tempRect);
                    Rect rect = this.tempRect;
                    i4 += rect.left + rect.right;
                }
                i3 = findTextInputLayoutAncestor.getEndIconView().getMeasuredWidth() + i4;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, i3), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.modalListPopup.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.modalListPopup.show();
        }
    }
}
