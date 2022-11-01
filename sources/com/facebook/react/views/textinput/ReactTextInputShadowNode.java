package com.facebook.react.views.textinput;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.concurrent.atomic.AtomicInteger;
@TargetApi(23)
/* loaded from: classes.dex */
public class ReactTextInputShadowNode extends ReactBaseTextShadowNode implements YogaMeasureFunction {
    public EditText mInternalEditText;
    public ReactTextInputLocalData mLocalData;
    public int mMostRecentEventCount = -1;
    public String mText = null;
    public String mPlaceholder = null;
    public int mSelectionStart = -1;
    public int mSelectionEnd = -1;

    public ReactTextInputShadowNode() {
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        this.mYogaNode.setMeasureFunction(this);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public boolean isYogaLeafNode() {
        return true;
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        EditText editText = this.mInternalEditText;
        R$dimen.assertNotNull(editText);
        ReactTextInputLocalData reactTextInputLocalData = this.mLocalData;
        if (reactTextInputLocalData != null) {
            editText.setText(reactTextInputLocalData.mText);
            editText.setTextSize(0, reactTextInputLocalData.mTextSize);
            editText.setMinLines(reactTextInputLocalData.mMinLines);
            editText.setMaxLines(reactTextInputLocalData.mMaxLines);
            editText.setInputType(reactTextInputLocalData.mInputType);
            editText.setHint(reactTextInputLocalData.mPlaceholder);
            if (Build.VERSION.SDK_INT >= 23) {
                editText.setBreakStrategy(reactTextInputLocalData.mBreakStrategy);
            }
        } else {
            editText.setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
            int i = this.mNumberOfLines;
            if (i != -1) {
                editText.setLines(i);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                int breakStrategy = editText.getBreakStrategy();
                int i2 = this.mTextBreakStrategy;
                if (breakStrategy != i2) {
                    editText.setBreakStrategy(i2);
                }
            }
        }
        editText.setHint(this.mPlaceholder);
        editText.measure(R$style.getMeasureSpec(f, yogaMeasureMode), R$style.getMeasureSpec(f2, yogaMeasureMode2));
        return R$style.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        if (this.mMostRecentEventCount != -1) {
            uIViewOperationQueue.enqueueUpdateExtraData(this.mReactTag, new ReactTextUpdate(spannedFromShadowNode(this, this.mText, false, null), this.mMostRecentEventCount, this.mContainsImages, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.mTextAlign, this.mTextBreakStrategy, this.mJustificationMode, this.mSelectionStart, this.mSelectionEnd));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object obj) {
        R$dimen.assertCondition(obj instanceof ReactTextInputLocalData);
        this.mLocalData = (ReactTextInputLocalData) obj;
        dirty();
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int i) {
        this.mMostRecentEventCount = i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void setPadding(int i, float f) {
        this.mPadding[i] = f;
        this.mPaddingIsPercent[i] = false;
        updatePadding();
        markUpdated();
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(String str) {
        this.mPlaceholder = str;
        markUpdated();
    }

    @ReactProp(name = "selection")
    public void setSelection(ReadableMap readableMap) {
        this.mSelectionEnd = -1;
        this.mSelectionStart = -1;
        if (readableMap != null && readableMap.hasKey("start") && readableMap.hasKey("end")) {
            this.mSelectionStart = readableMap.getInt("start");
            this.mSelectionEnd = readableMap.getInt("end");
            markUpdated();
        }
    }

    @ReactProp(name = "text")
    public void setText(String str) {
        this.mText = str;
        if (str != null) {
            if (this.mSelectionStart > str.length()) {
                this.mSelectionStart = str.length();
            }
            if (this.mSelectionEnd > str.length()) {
                this.mSelectionEnd = str.length();
            }
        } else {
            this.mSelectionStart = -1;
            this.mSelectionEnd = -1;
        }
        markUpdated();
    }

    @Override // com.facebook.react.views.text.ReactBaseTextShadowNode
    public void setTextBreakStrategy(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (str == null || "simple".equals(str)) {
                this.mTextBreakStrategy = 0;
            } else if ("highQuality".equals(str)) {
                this.mTextBreakStrategy = 1;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textBreakStrategy: ", str));
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        this.mThemedContext = themedReactContext;
        EditText editText = new EditText(getThemedContext());
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setDefaultPadding(4, editText.getPaddingStart());
        setDefaultPadding(1, editText.getPaddingTop());
        setDefaultPadding(5, editText.getPaddingEnd());
        setDefaultPadding(3, editText.getPaddingBottom());
        this.mInternalEditText = editText;
        editText.setPadding(0, 0, 0, 0);
        this.mInternalEditText.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }
}
