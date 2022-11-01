package com.facebook.react.views.progressbar;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.react.R$style;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class ProgressBarShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    public String mStyle = ReactProgressBarViewManager.DEFAULT_STYLE;
    public final SparseIntArray mHeight = new SparseIntArray();
    public final SparseIntArray mWidth = new SparseIntArray();
    public final Set<Integer> mMeasured = new HashSet();

    public ProgressBarShadowNode() {
        this.mYogaNode.setMeasureFunction(this);
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        int styleFromString = ReactProgressBarViewManager.getStyleFromString(this.mStyle);
        if (!this.mMeasured.contains(Integer.valueOf(styleFromString))) {
            ProgressBar createProgressBar = ReactProgressBarViewManager.createProgressBar(getThemedContext(), styleFromString);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
            createProgressBar.measure(makeMeasureSpec, makeMeasureSpec);
            this.mHeight.put(styleFromString, createProgressBar.getMeasuredHeight());
            this.mWidth.put(styleFromString, createProgressBar.getMeasuredWidth());
            this.mMeasured.add(Integer.valueOf(styleFromString));
        }
        return R$style.make(this.mWidth.get(styleFromString), this.mHeight.get(styleFromString));
    }

    @ReactProp(name = ReactProgressBarViewManager.PROP_STYLE)
    public void setStyle(String str) {
        if (str == null) {
            str = ReactProgressBarViewManager.DEFAULT_STYLE;
        }
        this.mStyle = str;
    }
}
