package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.TextAttributes;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ReactEditText extends EditText {
    public static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    public boolean mContainsImages;
    public ContentSizeWatcher mContentSizeWatcher;
    public final InputMethodManager mInputMethodManager;
    public String mReturnKeyType;
    public SelectionWatcher mSelectionWatcher;
    public boolean mDetectScrollMovement = false;
    public boolean mOnKeyPress = false;
    public boolean mTypefaceDirty = false;
    public String mFontFamily = null;
    public int mFontWeight = -1;
    public int mFontStyle = -1;
    public JavaOnlyMap mAttributedString = null;
    public StateWrapper mStateWrapper = null;
    public boolean mDisableTextDiffing = false;
    public ReactViewBackgroundManager mReactBackgroundManager = new ReactViewBackgroundManager(this);
    public int mDefaultGravityHorizontal = getGravity() & 8388615;
    public int mDefaultGravityVertical = getGravity() & 112;
    public int mNativeEventCount = 0;
    public int mMostRecentEventCount = 0;
    public boolean mIsSettingTextFromJS = false;
    public boolean mShouldAllowFocus = false;
    public Boolean mBlurOnSubmit = null;
    public boolean mDisableFullscreen = false;
    public ArrayList<TextWatcher> mListeners = null;
    public TextWatcherDelegator mTextWatcherDelegator = null;
    public int mStagedInputType = getInputType();
    public final InternalKeyListener mKeyListener = new InternalKeyListener();
    public ScrollWatcher mScrollWatcher = null;
    public TextAttributes mTextAttributes = new TextAttributes();

    /* loaded from: classes.dex */
    public static class InternalKeyListener implements KeyListener {
        public int mInputType = 0;

        @Override // android.text.method.KeyListener
        public void clearMetaKeyState(View view, Editable editable, int i) {
            ReactEditText.sKeyListener.clearMetaKeyState(view, editable, i);
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return this.mInputType;
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyDown(view, editable, i, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyOther(view, editable, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyUp(view, editable, i, keyEvent);
        }
    }

    /* loaded from: classes.dex */
    public class TextWatcherDelegator implements TextWatcher {
        public TextWatcherDelegator(AnonymousClass1 r2) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && (arrayList = reactEditText.mListeners) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().afterTextChanged(editable);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && (arrayList = reactEditText.mListeners) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().beforeTextChanged(charSequence, i, i2, i3);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ArrayList<TextWatcher> arrayList;
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && (arrayList = reactEditText.mListeners) != null) {
                Iterator<TextWatcher> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onTextChanged(charSequence, i, i2, i3);
                }
            }
            ReactEditText.this.onContentSizeChange();
        }
    }

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        Object systemService = getContext().getSystemService("input_method");
        R$dimen.assertNotNull(systemService);
        this.mInputMethodManager = (InputMethodManager) systemService;
        applyTextAttributes();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 && i <= 27) {
            setLayerType(1, null);
        }
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.facebook.react.views.textinput.ReactEditText.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                if (i2 != 16) {
                    return super.performAccessibilityAction(view, i2, bundle);
                }
                ReactEditText reactEditText = ReactEditText.this;
                reactEditText.mShouldAllowFocus = true;
                reactEditText.requestFocus();
                ReactEditText.this.mShouldAllowFocus = false;
                return true;
            }
        });
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator(null);
        }
        return this.mTextWatcherDelegator;
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(textWatcher);
    }

    public void applyTextAttributes() {
        setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
        float effectiveLetterSpacing = this.mTextAttributes.getEffectiveLetterSpacing();
        if (!Float.isNaN(effectiveLetterSpacing)) {
            setLetterSpacing(effectiveLetterSpacing);
        }
    }

    @Override // android.view.View
    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    public boolean getBlurOnSubmit() {
        Boolean bool = this.mBlurOnSubmit;
        if (bool == null) {
            return !isMultiline();
        }
        return bool.booleanValue();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    public int getStagedInputType() {
        return this.mStagedInputType;
    }

    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.view.View
    public boolean isLayoutRequested() {
        return false;
    }

    public boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ba A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void maybeSetText(com.facebook.react.views.text.ReactTextUpdate r14) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.maybeSetText(com.facebook.react.views.text.ReactTextUpdate):void");
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onAttachedToWindow();
            }
        }
    }

    public final void onContentSizeChange() {
        ContentSizeWatcher contentSizeWatcher = this.mContentSizeWatcher;
        if (contentSizeWatcher != null) {
            ReactTextInputManager.ReactContentSizeWatcher reactContentSizeWatcher = (ReactTextInputManager.ReactContentSizeWatcher) contentSizeWatcher;
            int width = reactContentSizeWatcher.mEditText.getWidth();
            int height = reactContentSizeWatcher.mEditText.getHeight();
            if (reactContentSizeWatcher.mEditText.getLayout() != null) {
                width = reactContentSizeWatcher.mEditText.getCompoundPaddingRight() + reactContentSizeWatcher.mEditText.getLayout().getWidth() + reactContentSizeWatcher.mEditText.getCompoundPaddingLeft();
                height = reactContentSizeWatcher.mEditText.getCompoundPaddingBottom() + reactContentSizeWatcher.mEditText.getLayout().getHeight() + reactContentSizeWatcher.mEditText.getCompoundPaddingTop();
            }
            if (!(width == reactContentSizeWatcher.mPreviousContentWidth && height == reactContentSizeWatcher.mPreviousContentHeight)) {
                reactContentSizeWatcher.mPreviousContentHeight = height;
                reactContentSizeWatcher.mPreviousContentWidth = width;
                reactContentSizeWatcher.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(reactContentSizeWatcher.mEditText.getId(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height)));
            }
        }
        if (this.mStateWrapper == null) {
            ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).setViewLocalData(getId(), new ReactTextInputLocalData(this));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = (ReactContext) getContext();
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.mOnKeyPress) {
            onCreateInputConnection = new ReactEditTextInputConnectionWrapper(onCreateInputConnection, reactContext, this);
        }
        if (isMultiline() && getBlurOnSubmit()) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onDetachedFromWindow();
            }
        }
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onFinishTemporaryDetach();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        SelectionWatcher selectionWatcher;
        super.onFocusChanged(z, i, rect);
        if (z && (selectionWatcher = this.mSelectionWatcher) != null) {
            ((ReactTextInputManager.ReactSelectionWatcher) selectionWatcher).onSelectionChanged(getSelectionStart(), getSelectionEnd());
        }
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 66 || isMultiline()) {
            return super.onKeyUp(i, keyEvent);
        }
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onContentSizeChange();
    }

    @Override // android.widget.TextView, android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollWatcher scrollWatcher = this.mScrollWatcher;
        if (scrollWatcher != null) {
            ReactTextInputManager.ReactScrollWatcher reactScrollWatcher = (ReactTextInputManager.ReactScrollWatcher) scrollWatcher;
            if (reactScrollWatcher.mPreviousHoriz != i || reactScrollWatcher.mPreviousVert != i2) {
                reactScrollWatcher.mEventDispatcher.dispatchEvent(ScrollEvent.obtain(reactScrollWatcher.mReactEditText.getId(), ScrollEventType.SCROLL, i, i2, 0.0f, 0.0f, 0, 0, reactScrollWatcher.mReactEditText.getWidth(), reactScrollWatcher.mReactEditText.getHeight()));
                reactScrollWatcher.mPreviousHoriz = i;
                reactScrollWatcher.mPreviousVert = i2;
            }
        }
    }

    @Override // android.widget.TextView
    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (this.mSelectionWatcher != null && hasFocus()) {
            ((ReactTextInputManager.ReactSelectionWatcher) this.mSelectionWatcher).onSelectionChanged(i, i2);
        }
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onStartTemporaryDetach();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mDetectScrollMovement = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2 && this.mDetectScrollMovement) {
            if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.mDetectScrollMovement = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    @Override // android.view.View
    public boolean requestFocus(int i, Rect rect) {
        if (isFocused()) {
            return true;
        }
        if (!this.mShouldAllowFocus) {
            return false;
        }
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(i, rect);
        if (getShowSoftInputOnFocus()) {
            this.mInputMethodManager.showSoftInput(this, 0);
        }
        return requestFocus;
    }

    public void setAllowFontScaling(boolean z) {
        TextAttributes textAttributes = this.mTextAttributes;
        if (textAttributes.mAllowFontScaling != z) {
            textAttributes.mAllowFontScaling = z;
            applyTextAttributes();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBlurOnSubmit(Boolean bool) {
        this.mBlurOnSubmit = bool;
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderStyle(String str) {
        this.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setDisableFullscreenUI(boolean z) {
        this.mDisableFullscreen = z;
        updateImeOptions();
    }

    public void setFontFamily(String str) {
        this.mFontFamily = str;
        this.mTypefaceDirty = true;
    }

    public void setFontSize(float f) {
        this.mTextAttributes.mFontSize = f;
        applyTextAttributes();
    }

    public void setFontStyle(String str) {
        int parseFontStyle = R$style.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            this.mTypefaceDirty = true;
        }
    }

    public void setFontWeight(String str) {
        int parseFontWeight = R$style.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            this.mTypefaceDirty = true;
        }
    }

    public void setGravityHorizontal(int i) {
        if (i == 0) {
            i = this.mDefaultGravityHorizontal;
        }
        setGravity(i | (getGravity() & (-8) & (-8388616)));
    }

    public void setGravityVertical(int i) {
        if (i == 0) {
            i = this.mDefaultGravityVertical;
        }
        setGravity(i | (getGravity() & (-113)));
    }

    @Override // android.widget.TextView
    public void setInputType(int i) {
        Typeface typeface = getTypeface();
        super.setInputType(i);
        this.mStagedInputType = i;
        setTypeface(typeface);
        if (isMultiline()) {
            setSingleLine(false);
        }
        InternalKeyListener internalKeyListener = this.mKeyListener;
        internalKeyListener.mInputType = i;
        setKeyListener(internalKeyListener);
    }

    public void setLetterSpacingPt(float f) {
        this.mTextAttributes.mLetterSpacing = f;
        applyTextAttributes();
    }

    public void setMaxFontSizeMultiplier(float f) {
        TextAttributes textAttributes = this.mTextAttributes;
        if (f != textAttributes.mMaxFontSizeMultiplier) {
            textAttributes.setMaxFontSizeMultiplier(f);
            applyTextAttributes();
        }
    }

    public void setMostRecentEventCount(int i) {
        this.mMostRecentEventCount = i;
    }

    public void setOnKeyPress(boolean z) {
        this.mOnKeyPress = z;
    }

    public void setReturnKeyType(String str) {
        this.mReturnKeyType = str;
        updateImeOptions();
    }

    public void setScrollWatcher(ScrollWatcher scrollWatcher) {
        this.mScrollWatcher = scrollWatcher;
    }

    @Override // android.widget.EditText
    public void setSelection(int i, int i2) {
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            super.setSelection(i, i2);
        }
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    public void setStagedInputType(int i) {
        this.mStagedInputType = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateImeOptions() {
        /*
            r9 = this;
            java.lang.String r0 = r9.mReturnKeyType
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 6
            if (r0 == 0) goto L_0x0070
            r0.hashCode()
            r7 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1273775369: goto L_0x0058;
                case -906336856: goto L_0x004d;
                case 3304: goto L_0x0042;
                case 3089282: goto L_0x0037;
                case 3377907: goto L_0x002c;
                case 3387192: goto L_0x0021;
                case 3526536: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0062
        L_0x0016:
            java.lang.String r8 = "send"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x001f
            goto L_0x0062
        L_0x001f:
            r7 = 6
            goto L_0x0062
        L_0x0021:
            java.lang.String r8 = "none"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x002a
            goto L_0x0062
        L_0x002a:
            r7 = 5
            goto L_0x0062
        L_0x002c:
            java.lang.String r8 = "next"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0035
            goto L_0x0062
        L_0x0035:
            r7 = 4
            goto L_0x0062
        L_0x0037:
            java.lang.String r8 = "done"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0040
            goto L_0x0062
        L_0x0040:
            r7 = 3
            goto L_0x0062
        L_0x0042:
            java.lang.String r8 = "go"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x004b
            goto L_0x0062
        L_0x004b:
            r7 = 2
            goto L_0x0062
        L_0x004d:
            java.lang.String r8 = "search"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0056
            goto L_0x0062
        L_0x0056:
            r7 = 1
            goto L_0x0062
        L_0x0058:
            java.lang.String r8 = "previous"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r7 = 0
        L_0x0062:
            switch(r7) {
                case 0: goto L_0x006e;
                case 1: goto L_0x006c;
                case 2: goto L_0x006a;
                case 3: goto L_0x0070;
                case 4: goto L_0x0071;
                case 5: goto L_0x0068;
                case 6: goto L_0x0066;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x0070
        L_0x0066:
            r1 = 4
            goto L_0x0071
        L_0x0068:
            r1 = 1
            goto L_0x0071
        L_0x006a:
            r1 = 2
            goto L_0x0071
        L_0x006c:
            r1 = 3
            goto L_0x0071
        L_0x006e:
            r1 = 7
            goto L_0x0071
        L_0x0070:
            r1 = 6
        L_0x0071:
            boolean r0 = r9.mDisableFullscreen
            if (r0 == 0) goto L_0x007c
            r0 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r0 | r1
            r9.setImeOptions(r0)
            goto L_0x007f
        L_0x007c:
            r9.setImeOptions(r1)
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.updateImeOptions():void");
    }

    @Override // android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }
}
