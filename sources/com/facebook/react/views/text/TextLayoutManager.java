package com.facebook.react.views.text;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.LruCache;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class TextLayoutManager {
    public static final TextPaint sTextPaintInstance = new TextPaint(1);
    public static final Object sSpannableCacheLock = new Object();
    public static LruCache<String, Spannable> sSpannableCache = new LruCache<>(100);

    /* loaded from: classes.dex */
    public static class SetSpanOperation {
        public int end;
        public int start;
        public ReactSpan what;

        public SetSpanOperation(int i, int i2, ReactSpan reactSpan) {
            this.start = i;
            this.end = i2;
            this.what = reactSpan;
        }
    }

    public static Spannable getOrCreateSpannableForText(Context context, ReadableMap readableMap) {
        String obj = readableMap.toString();
        synchronized (sSpannableCacheLock) {
            Spannable spannable = sSpannableCache.get(obj);
            if (spannable != null) {
                return spannable;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            ArrayList arrayList = new ArrayList();
            ReadableArray array = readableMap.getArray("fragments");
            int size = array.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = array.getMap(i);
                int length = spannableStringBuilder.length();
                TextAttributeProps textAttributeProps = new TextAttributeProps(new ReactStylesDiffMap(map.getMap("textAttributes")));
                spannableStringBuilder.append((CharSequence) TextTransform.apply(map.getString("string"), textAttributeProps.mTextTransform));
                int length2 = spannableStringBuilder.length();
                if (length2 >= length) {
                    if (textAttributeProps.mIsColorSet) {
                        arrayList.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(textAttributeProps.mColor)));
                    }
                    if (textAttributeProps.mIsBackgroundColorSet) {
                        arrayList.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(textAttributeProps.mBackgroundColor)));
                    }
                    if (!Float.isNaN(textAttributeProps.getLetterSpacing())) {
                        arrayList.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(textAttributeProps.getLetterSpacing())));
                    }
                    arrayList.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(textAttributeProps.mFontSize)));
                    if (textAttributeProps.mFontStyle == -1 && textAttributeProps.mFontWeight == -1 && textAttributeProps.mFontFamily == null) {
                        array = array;
                    } else {
                        array = array;
                        arrayList.add(new SetSpanOperation(length, length2, new CustomStyleSpan(textAttributeProps.mFontStyle, textAttributeProps.mFontWeight, textAttributeProps.mFontFeatureSettings, textAttributeProps.mFontFamily, context.getAssets())));
                    }
                    if (textAttributeProps.mIsUnderlineTextDecorationSet) {
                        arrayList.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                    }
                    if (textAttributeProps.mIsLineThroughTextDecorationSet) {
                        arrayList.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                    }
                    if (!(textAttributeProps.mTextShadowOffsetDx == 0.0f && textAttributeProps.mTextShadowOffsetDy == 0.0f)) {
                        arrayList.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(textAttributeProps.mTextShadowOffsetDx, textAttributeProps.mTextShadowOffsetDy, textAttributeProps.mTextShadowRadius, textAttributeProps.mTextShadowColor)));
                    }
                    if (!Float.isNaN(textAttributeProps.getEffectiveLineHeight())) {
                        arrayList.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(textAttributeProps.getEffectiveLineHeight())));
                    }
                    arrayList.add(new SetSpanOperation(length, length2, new ReactTagSpan(map.getInt("reactTag"))));
                } else {
                    array = array;
                }
            }
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                SetSpanOperation setSpanOperation = (SetSpanOperation) it.next();
                int i3 = setSpanOperation.start;
                spannableStringBuilder.setSpan(setSpanOperation.what, i3, setSpanOperation.end, ((i3 == 0 ? 18 : 34) & (-16711681)) | ((i2 << 16) & 16711680));
                i2++;
            }
            synchronized (sSpannableCacheLock) {
                sSpannableCache.put(obj, spannableStringBuilder);
            }
            return spannableStringBuilder;
        }
    }
}
