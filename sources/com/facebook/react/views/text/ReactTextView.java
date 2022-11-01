package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintContextWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.views.view.ReactViewBackgroundManager;
/* loaded from: classes.dex */
public class ReactTextView extends AppCompatTextView implements ReactCompoundView {
    public static final ViewGroup.LayoutParams EMPTY_LAYOUT_PARAMS = new ViewGroup.LayoutParams(0, 0);
    public boolean mContainsImages;
    public boolean mNotifyOnInlineViewLayout;
    public Spannable mSpanned;
    public int mTextAlign = 0;
    public int mNumberOfLines = Integer.MAX_VALUE;
    public TextUtils.TruncateAt mEllipsizeLocation = TextUtils.TruncateAt.END;
    public boolean mAdjustsFontSizeToFit = false;
    public int mLinkifyMaskType = 0;
    public ReactViewBackgroundManager mReactBackgroundManager = new ReactViewBackgroundManager(this);
    public int mDefaultGravityHorizontal = getGravity() & 8388615;
    public int mDefaultGravityVertical = getGravity() & 112;

    public ReactTextView(Context context) {
        super(context, null);
    }

    private ReactContext getReactContext() {
        Context context = getContext();
        if (context instanceof TintContextWrapper) {
            context = ((TintContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }

    public Spannable getSpanned() {
        return this.mSpanned;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final WritableMap inlineViewJson(int i, int i2, int i3, int i4, int i5, int i6) {
        WritableMap createMap = Arguments.createMap();
        if (i == 8) {
            createMap.putString("visibility", "gone");
            createMap.putInt("index", i2);
        } else if (i == 0) {
            createMap.putString("visibility", "visible");
            createMap.putInt("index", i2);
            createMap.putDouble("left", PixelUtil.toDIPFromPixel(i3));
            createMap.putDouble("top", PixelUtil.toDIPFromPixel(i4));
            createMap.putDouble("right", PixelUtil.toDIPFromPixel(i5));
            createMap.putDouble("bottom", PixelUtil.toDIPFromPixel(i6));
        } else {
            createMap.putString("visibility", "unknown");
            createMap.putInt("index", i2);
        }
        return createMap;
    }

    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onAttachedToWindow();
            }
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onDetachedFromWindow();
            }
        }
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onFinishTemporaryDetach();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
        if (r2 < (r11.getEllipsisStart(r3) + r11.getLineStart(r3))) goto L_0x0078;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d9, code lost:
        if (r4 != false) goto L_0x00db;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0147 A[SYNTHETIC] */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r22, int r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextView.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onStartTemporaryDetach();
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float f, float f2) {
        int i;
        CharSequence text = getText();
        int id = getId();
        int i2 = (int) f;
        int i3 = (int) f2;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i3);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i2 >= lineLeft && i2 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, i2);
                ReactTagSpan[] reactTagSpanArr = (ReactTagSpan[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, ReactTagSpan.class);
                if (reactTagSpanArr != null) {
                    int length = text.length();
                    for (int i4 = 0; i4 < reactTagSpanArr.length; i4++) {
                        int spanStart = spanned.getSpanStart(reactTagSpanArr[i4]);
                        int spanEnd = spanned.getSpanEnd(reactTagSpanArr[i4]);
                        if (spanEnd > offsetForHorizontal && (i = spanEnd - spanStart) <= length) {
                            id = reactTagSpanArr[i4].mReactTag;
                            length = i;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Crash in HorizontalMeasurementProvider: ");
                outline13.append(e.getMessage());
                FLog.e("ReactNative", outline13.toString());
            }
        }
        return id;
    }

    public void setAdjustFontSizeToFit(boolean z) {
        this.mAdjustsFontSizeToFit = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderStyle(String str) {
        this.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.mEllipsizeLocation = truncateAt;
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

    public void setLinkifyMask(int i) {
        this.mLinkifyMaskType = i;
    }

    public void setNotifyOnInlineViewLayout(boolean z) {
        this.mNotifyOnInlineViewLayout = z;
    }

    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = i;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        setSingleLine(z);
        setMaxLines(this.mNumberOfLines);
    }

    public void setSpanned(Spannable spannable) {
        this.mSpanned = spannable;
    }

    public void setText(ReactTextUpdate reactTextUpdate) {
        int i;
        this.mContainsImages = reactTextUpdate.mContainsImages;
        if (getLayoutParams() == null) {
            setLayoutParams(EMPTY_LAYOUT_PARAMS);
        }
        Spannable spannable = reactTextUpdate.mText;
        int i2 = this.mLinkifyMaskType;
        if (i2 > 0) {
            Linkify.addLinks(spannable, i2);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
        setText(spannable);
        float f = reactTextUpdate.mPaddingLeft;
        float f2 = reactTextUpdate.mPaddingTop;
        float f3 = reactTextUpdate.mPaddingRight;
        float f4 = reactTextUpdate.mPaddingBottom;
        if (!(f == -1.0f || f4 == -1.0f || f3 == -1.0f || i == 0)) {
            setPadding((int) Math.floor(f), (int) Math.floor(f2), (int) Math.floor(f3), (int) Math.floor(f4));
        }
        int i3 = reactTextUpdate.mTextAlign;
        if (this.mTextAlign != i3) {
            this.mTextAlign = i3;
        }
        setGravityHorizontal(this.mTextAlign);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 23) {
            int breakStrategy = getBreakStrategy();
            int i5 = reactTextUpdate.mTextBreakStrategy;
            if (breakStrategy != i5) {
                setBreakStrategy(i5);
            }
        }
        if (i4 >= 26) {
            int justificationMode = getJustificationMode();
            int i6 = reactTextUpdate.mJustificationMode;
            if (justificationMode != i6) {
                setJustificationMode(i6);
            }
        }
        requestLayout();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }
}
