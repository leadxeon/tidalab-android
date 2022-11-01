package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.AppCompatEditText;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.Locale;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class TextInputEditText extends AppCompatEditText {
    public final Rect parentRect = new Rect();
    public boolean textInputLayoutFocusedRectEnabled;

    public TextInputEditText(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.editTextStyle, 0), attributeSet, R.attr.editTextStyle);
        int[] iArr = R$styleable.TextInputEditText;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, R.attr.editTextStyle, 2131886715);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, R.attr.editTextStyle, 2131886715, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.editTextStyle, 2131886715);
        setTextInputLayoutFocusedRectEnabled(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null) {
            return textInputLayout.getHint();
        }
        return null;
    }

    private TextInputLayout getTextInputLayout() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        super.getFocusedRect(rect);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null && this.textInputLayoutFocusedRectEnabled && rect != null) {
            textInputLayout.getFocusedRect(this.parentRect);
            rect.bottom = this.parentRect.bottom;
        }
    }

    @Override // android.view.View
    public boolean getGlobalVisibleRect(Rect rect, Point point) {
        boolean globalVisibleRect = super.getGlobalVisibleRect(rect, point);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!(textInputLayout == null || !this.textInputLayoutFocusedRectEnabled || rect == null)) {
            textInputLayout.getGlobalVisibleRect(this.parentRect, point);
            rect.bottom = this.parentRect.bottom;
        }
        return globalVisibleRect;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout == null || !textInputLayout.isProvidingHint) {
            return super.getHint();
        }
        return textInputLayout.getHint();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null && textInputLayout.isProvidingHint && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint(HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && editorInfo.hintText == null) {
            editorInfo.hintText = getHintFromLayout();
        }
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (Build.VERSION.SDK_INT < 23 && textInputLayout != null) {
            Editable text = getText();
            CharSequence hint = textInputLayout.getHint();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            setLabelFor(R.id.textinput_helper_text);
            String str = HttpUrl.FRAGMENT_ENCODE_SET;
            String charSequence = z2 ? hint.toString() : str;
            if (z) {
                StringBuilder sb = new StringBuilder();
                sb.append((Object) text);
                if (!TextUtils.isEmpty(charSequence)) {
                    str = GeneratedOutlineSupport.outline8(", ", charSequence);
                }
                sb.append(str);
                str = sb.toString();
            } else if (!TextUtils.isEmpty(charSequence)) {
                str = charSequence;
            }
            accessibilityNodeInfo.setText(str);
        }
    }

    @Override // android.view.View
    public boolean requestRectangleOnScreen(Rect rect) {
        boolean requestRectangleOnScreen = super.requestRectangleOnScreen(rect);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null && this.textInputLayoutFocusedRectEnabled) {
            this.parentRect.set(0, textInputLayout.getHeight() - getResources().getDimensionPixelOffset(R.dimen.mtrl_edittext_rectangle_top_offset), textInputLayout.getWidth(), textInputLayout.getHeight());
            textInputLayout.requestRectangleOnScreen(this.parentRect, true);
        }
        return requestRectangleOnScreen;
    }

    public void setTextInputLayoutFocusedRectEnabled(boolean z) {
        this.textInputLayoutFocusedRectEnabled = z;
    }
}
