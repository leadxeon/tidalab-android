package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextAttributeProps;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.TextTransform;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.internal.http2.Http2;
@ReactModule(name = ReactTextInputManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode> {
    private static final int AUTOCAPITALIZE_FLAGS = 28672;
    private static final int BLUR_TEXT_INPUT = 2;
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    private static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final int KEYBOARD_TYPE_FLAGS = 12339;
    private static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    private static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    private static final int PASSWORD_VISIBILITY_FLAG = 16;
    public static final String REACT_CLASS = "AndroidTextInput";
    private static final int SET_MOST_RECENT_EVENT_COUNT = 3;
    private static final int SET_TEXT_AND_SELECTION = 4;
    public static final String TAG = "ReactTextInputManager";
    private static final int UNSET = -1;
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];

    /* loaded from: classes.dex */
    public class ReactContentSizeWatcher implements ContentSizeWatcher {
        public ReactEditText mEditText;
        public EventDispatcher mEventDispatcher;
        public int mPreviousContentWidth = 0;
        public int mPreviousContentHeight = 0;

        public ReactContentSizeWatcher(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.mEditText = reactEditText;
            this.mEventDispatcher = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }

    /* loaded from: classes.dex */
    public class ReactScrollWatcher implements ScrollWatcher {
        public EventDispatcher mEventDispatcher;
        public int mPreviousHoriz;
        public int mPreviousVert;
        public ReactEditText mReactEditText;

        public ReactScrollWatcher(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            this.mEventDispatcher = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }

    /* loaded from: classes.dex */
    public class ReactSelectionWatcher implements SelectionWatcher {
        public EventDispatcher mEventDispatcher;
        public int mPreviousSelectionEnd;
        public int mPreviousSelectionStart;
        public ReactEditText mReactEditText;

        public ReactSelectionWatcher(ReactTextInputManager reactTextInputManager, ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            this.mEventDispatcher = ((UIManagerModule) ((ReactContext) reactEditText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        public void onSelectionChanged(int i, int i2) {
            int min = Math.min(i, i2);
            int max = Math.max(i, i2);
            if (this.mPreviousSelectionStart != min || this.mPreviousSelectionEnd != max) {
                this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mReactEditText.getId(), min, max));
                this.mPreviousSelectionStart = min;
                this.mPreviousSelectionEnd = max;
            }
        }
    }

    /* loaded from: classes.dex */
    public class ReactTextInputTextWatcher implements TextWatcher {
        public ReactEditText mEditText;
        public EventDispatcher mEventDispatcher;
        public String mPreviousText = null;

        public ReactTextInputTextWatcher(ReactTextInputManager reactTextInputManager, ReactContext reactContext, ReactEditText reactEditText) {
            this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.mEditText = reactEditText;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.mPreviousText = charSequence.toString();
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int i4 = i;
            int i5 = i2;
            if (!this.mEditText.mDisableTextDiffing) {
                if (!(i3 == 0 && i5 == 0)) {
                    R$dimen.assertNotNull(this.mPreviousText);
                    int i6 = i4 + i3;
                    String substring = charSequence.toString().substring(i4, i6);
                    int i7 = i4 + i5;
                    String substring2 = this.mPreviousText.substring(i4, i7);
                    if (i3 != i5 || !substring.equals(substring2)) {
                        JavaOnlyMap javaOnlyMap = this.mEditText.mAttributedString;
                        if (javaOnlyMap != null && javaOnlyMap.hasKey("fragments")) {
                            String charSequence2 = charSequence.subSequence(i4, i6).toString();
                            String string = javaOnlyMap.getString("string");
                            StringBuilder sb = new StringBuilder();
                            sb.append(string.substring(0, i4));
                            sb.append(charSequence2);
                            sb.append(string.length() > i7 ? string.substring(i7) : HttpUrl.FRAGMENT_ENCODE_SET);
                            javaOnlyMap.putString("string", sb.toString());
                            int i8 = 0;
                            boolean z = false;
                            int i9 = 0;
                            for (JavaOnlyArray javaOnlyArray = (JavaOnlyArray) javaOnlyMap.getArray("fragments"); i8 < javaOnlyArray.size() && !z; javaOnlyArray = javaOnlyArray) {
                                JavaOnlyMap javaOnlyMap2 = (JavaOnlyMap) javaOnlyArray.getMap(i8);
                                String string2 = javaOnlyMap2.getString("string");
                                int length = string2.length() + i9;
                                if (length < i4) {
                                    i9 = length;
                                    substring2 = substring2;
                                } else {
                                    int i10 = i4 - i9;
                                    int length2 = string2.length() - i10;
                                    i9 = length;
                                    StringBuilder sb2 = new StringBuilder();
                                    substring2 = substring2;
                                    sb2.append(string2.substring(0, i10));
                                    sb2.append(charSequence2);
                                    sb2.append(string2.substring(Math.min(i5, length2) + i10));
                                    javaOnlyMap2.putString("string", sb2.toString());
                                    if (length2 < i5) {
                                        i4 += length2;
                                        i5 -= length2;
                                        charSequence2 = HttpUrl.FRAGMENT_ENCODE_SET;
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                }
                                i8++;
                            }
                        }
                        if (!(this.mEditText.mStateWrapper == null || javaOnlyMap == null)) {
                            WritableMap writableNativeMap = new WritableNativeMap();
                            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                            WritableNativeArray writableNativeArray = new WritableNativeArray();
                            for (int i11 = 0; i11 < javaOnlyMap.getArray("fragments").size(); i11++) {
                                ReadableMap map = javaOnlyMap.getArray("fragments").getMap(i11);
                                WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                                writableNativeMap3.putDouble("reactTag", map.getInt("reactTag"));
                                writableNativeMap3.putString("string", map.getString("string"));
                                writableNativeArray.pushMap(writableNativeMap3);
                            }
                            writableNativeMap2.putString("string", javaOnlyMap.getString("string"));
                            writableNativeMap2.putArray("fragments", writableNativeArray);
                            ReactEditText reactEditText = this.mEditText;
                            int i12 = reactEditText.mNativeEventCount + 1;
                            reactEditText.mNativeEventCount = i12;
                            writableNativeMap.putInt("mostRecentEventCount", i12);
                            writableNativeMap.putMap("textChanged", writableNativeMap2);
                            this.mEditText.mStateWrapper.updateState(writableNativeMap);
                        }
                        EventDispatcher eventDispatcher = this.mEventDispatcher;
                        int id = this.mEditText.getId();
                        String charSequence3 = charSequence.toString();
                        ReactEditText reactEditText2 = this.mEditText;
                        int i13 = reactEditText2.mNativeEventCount + 1;
                        reactEditText2.mNativeEventCount = i13;
                        eventDispatcher.dispatchEvent(new ReactTextChangedEvent(id, charSequence3, i13));
                        this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mEditText.getId(), substring, substring2, i4, i4 + i5));
                    }
                }
            }
        }
    }

    private static void checkPasswordType(ReactEditText reactEditText) {
        if ((reactEditText.getStagedInputType() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (reactEditText.getStagedInputType() & 128) != 0) {
            updateStagedInputTypeFlag(reactEditText, 128, 16);
        }
    }

    private ReactTextUpdate getReactTextUpdate(String str, int i, int i2, int i3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) TextTransform.apply(str, TextTransform.UNSET));
        return new ReactTextUpdate(spannableStringBuilder, i, false, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, i2, i3);
    }

    private int getTextBreakStrategy(String str) {
        if (str == null) {
            return 1;
        }
        if (!str.equals("balanced")) {
            return !str.equals("simple") ? 1 : 0;
        }
        return 2;
    }

    private void setAutofillHints(ReactEditText reactEditText, String... strArr) {
        if (Build.VERSION.SDK_INT >= 26) {
            reactEditText.setAutofillHints(strArr);
        }
    }

    private static void updateStagedInputTypeFlag(ReactEditText reactEditText, int i, int i2) {
        reactEditText.setStagedInputType(((~i) & reactEditText.getStagedInputType()) | i2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return R$style.of("focusTextInput", 1, "blurTextInput", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("topSubmitEditing", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture")));
        builder.put("topEndEditing", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onEndEditing", "captured", "onEndEditingCapture")));
        builder.put("topTextInput", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onTextInput", "captured", "onTextInputCapture")));
        builder.put("topFocus", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onFocus", "captured", "onFocusCapture")));
        builder.put("topBlur", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onBlur", "captured", "onBlurCapture")));
        builder.put("topKeyPress", R$style.of("phasedRegistrationNames", R$style.of("bubbled", "onKeyPress", "captured", "onKeyPressCapture")));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), R$style.of("registrationName", "onScroll"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedViewConstants() {
        return R$style.of("AutoCapitalizationType", R$style.of("none", 0, "characters", 4096, "words", 8192, "sentences", Integer.valueOf((int) Http2.INITIAL_MAX_FRAME_SIZE)));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(ReactEditText reactEditText, boolean z) {
        reactEditText.setAllowFontScaling(z);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText reactEditText, Dynamic dynamic) {
        ReadableType type = dynamic.getType();
        ReadableType readableType = ReadableType.Number;
        int i = Http2.INITIAL_MAX_FRAME_SIZE;
        if (type == readableType) {
            i = dynamic.asInt();
        } else if (dynamic.getType() == ReadableType.String) {
            String asString = dynamic.asString();
            if (asString.equals("none")) {
                i = 0;
            } else if (asString.equals("characters")) {
                i = 4096;
            } else if (asString.equals("words")) {
                i = 8192;
            } else {
                asString.equals("sentences");
            }
        }
        updateStagedInputTypeFlag(reactEditText, AUTOCAPITALIZE_FLAGS, i);
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText reactEditText, Boolean bool) {
        int i;
        if (bool != null) {
            i = bool.booleanValue() ? 32768 : 524288;
        } else {
            i = 0;
        }
        updateStagedInputTypeFlag(reactEditText, 557056, i);
    }

    @ReactProp(name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText reactEditText, Boolean bool) {
        reactEditText.setBlurOnSubmit(bool);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactEditText reactEditText, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & 16777215;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactEditText.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactEditText reactEditText, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactEditText.setBorderRadius(f);
            return;
        }
        reactEditText.mReactBackgroundManager.getOrCreateReactViewBackground().setRadius(f, i + UNSET);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText reactEditText, String str) {
        reactEditText.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactEditText reactEditText, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactEditText.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactProp(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText reactEditText, boolean z) {
        reactEditText.setCursorVisible(!z);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setTextColor(R$style.getDefaultTextAttribute(reactEditText.getContext(), 16842904));
        } else {
            reactEditText.setTextColor(num.intValue());
        }
    }

    @ReactProp(defaultBoolean = false, name = "contextMenuHidden")
    public void setContextMenuHidden(ReactEditText reactEditText, final boolean z) {
        reactEditText.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: com.facebook.react.views.textinput.ReactTextInputManager.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return z;
            }
        });
    }

    @ReactProp(customType = "Color", name = "cursorColor")
    public void setCursorColor(ReactEditText reactEditText, Integer num) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i = declaredField.getInt(reactEditText);
            if (i != 0) {
                Context context = reactEditText.getContext();
                Object obj = ContextCompat.sLock;
                Drawable drawable = ContextCompat.Api21Impl.getDrawable(context, i);
                if (num != null) {
                    drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                }
                Drawable[] drawableArr = {drawable, drawable};
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj2 = declaredField2.get(reactEditText);
                Field declaredField3 = obj2.getClass().getDeclaredField("mCursorDrawable");
                declaredField3.setAccessible(true);
                declaredField3.set(obj2, drawableArr);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText reactEditText, boolean z) {
        reactEditText.setDisableFullscreenUI(z);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText reactEditText, boolean z) {
        reactEditText.setEnabled(z);
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText reactEditText, String str) {
        reactEditText.setFontFamily(str);
    }

    @ReactProp(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(ReactEditText reactEditText, float f) {
        reactEditText.setFontSize(f);
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText reactEditText, String str) {
        reactEditText.setFontStyle(str);
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText reactEditText, String str) {
        reactEditText.setFontWeight(str);
    }

    @ReactProp(name = "importantForAutofill")
    public void setImportantForAutofill(ReactEditText reactEditText, String str) {
        int i;
        if ("no".equals(str)) {
            i = 2;
        } else if ("noExcludeDescendants".equals(str)) {
            i = 8;
        } else if ("yes".equals(str)) {
            i = 1;
        } else {
            i = "yesExcludeDescendants".equals(str) ? 4 : 0;
        }
        setImportantForAutofill(reactEditText, i);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactEditText reactEditText, boolean z) {
        reactEditText.setIncludeFontPadding(z);
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText reactEditText, String str) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(reactEditText.getContext(), str), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText reactEditText, int i) {
        reactEditText.setCompoundDrawablePadding(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "keyboardType")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setKeyboardType(com.facebook.react.views.textinput.ReactEditText r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "numeric"
            boolean r0 = r0.equalsIgnoreCase(r5)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0010
            r2 = 12290(0x3002, float:1.7222E-41)
            r5 = 12290(0x3002, float:1.7222E-41)
        L_0x000e:
            r2 = 0
            goto L_0x0059
        L_0x0010:
            java.lang.String r0 = "number-pad"
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto L_0x001b
            r2 = 2
            r5 = 2
            goto L_0x000e
        L_0x001b:
            java.lang.String r0 = "decimal-pad"
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto L_0x0028
            r2 = 8194(0x2002, float:1.1482E-41)
            r5 = 8194(0x2002, float:1.1482E-41)
            goto L_0x000e
        L_0x0028:
            java.lang.String r0 = "email-address"
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto L_0x0035
            r2 = 33
            r5 = 33
            goto L_0x000e
        L_0x0035:
            java.lang.String r0 = "phone-pad"
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto L_0x0040
            r2 = 3
            r5 = 3
            goto L_0x000e
        L_0x0040:
            java.lang.String r0 = "visible-password"
            boolean r5 = r0.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x004d
            r2 = 144(0x90, float:2.02E-43)
            r5 = 144(0x90, float:2.02E-43)
            goto L_0x000e
        L_0x004d:
            int r5 = r4.getStagedInputType()
            r5 = r5 & 28672(0x7000, float:4.0178E-41)
            if (r5 == 0) goto L_0x0057
            r5 = 1
            goto L_0x0059
        L_0x0057:
            r5 = 1
            goto L_0x000e
        L_0x0059:
            if (r2 == 0) goto L_0x005c
            goto L_0x005e
        L_0x005c:
            r1 = 12339(0x3033, float:1.729E-41)
        L_0x005e:
            updateStagedInputTypeFlag(r4, r1, r5)
            checkPasswordType(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactTextInputManager.setKeyboardType(com.facebook.react.views.textinput.ReactEditText, java.lang.String):void");
    }

    @ReactProp(defaultFloat = 0.0f, name = "letterSpacing")
    public void setLetterSpacing(ReactEditText reactEditText, float f) {
        reactEditText.setLetterSpacingPt(f);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxFontSizeMultiplier")
    public void setMaxFontSizeMultiplier(ReactEditText reactEditText, float f) {
        reactEditText.setMaxFontSizeMultiplier(f);
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(ReactEditText reactEditText, Integer num) {
        InputFilter[] filters = reactEditText.getFilters();
        filters = EMPTY_FILTERS;
        if (num == null) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < filters.length; i++) {
                    if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[i]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    filters = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int i2 = 0; i2 < filters.length; i2++) {
                if (filters[i2] instanceof InputFilter.LengthFilter) {
                    filters[i2] = new InputFilter.LengthFilter(num.intValue());
                    z = true;
                }
            }
            if (!z) {
                InputFilter[] inputFilterArr = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(num.intValue());
                filters = inputFilterArr;
            }
        } else {
            filters = new InputFilter[]{new InputFilter.LengthFilter(num.intValue())};
        }
        reactEditText.setFilters(filters);
    }

    @ReactProp(defaultInt = 0, name = "mostRecentEventCount")
    public void setMostRecentEventCount(ReactEditText reactEditText, int i) {
        reactEditText.setMostRecentEventCount(i);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText reactEditText, boolean z) {
        int i = 0;
        int i2 = z ? 0 : 131072;
        if (z) {
            i = 131072;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(ReactEditText reactEditText, int i) {
        reactEditText.setLines(i);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setContentSizeWatcher(new ReactContentSizeWatcher(this, reactEditText));
        } else {
            reactEditText.setContentSizeWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onKeyPress")
    public void setOnKeyPress(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnKeyPress(z);
    }

    @ReactProp(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setScrollWatcher(new ReactScrollWatcher(this, reactEditText));
        } else {
            reactEditText.setScrollWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setSelectionWatcher(new ReactSelectionWatcher(this, reactEditText));
        } else {
            reactEditText.setSelectionWatcher(null);
        }
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(ReactEditText reactEditText, String str) {
        reactEditText.setHint(str);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHintTextColor(R$style.getDefaultTextAttribute(reactEditText.getContext(), 16842906));
        } else {
            reactEditText.setHintTextColor(num.intValue());
        }
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText reactEditText, String str) {
        reactEditText.setImeActionLabel(str, IME_ACTION_ID);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText reactEditText, String str) {
        reactEditText.setReturnKeyType(str);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText reactEditText, boolean z) {
        int i = 0;
        int i2 = z ? 0 : 144;
        if (z) {
            i = 128;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
        checkPasswordType(reactEditText);
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setSelectAllOnFocus(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHighlightColor(R$style.getDefaultTextAttribute(reactEditText.getContext(), 16842905).getDefaultColor());
        } else {
            reactEditText.setHighlightColor(num.intValue());
        }
        setCursorColor(reactEditText, num);
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText reactEditText, String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                reactEditText.setJustificationMode(1);
            }
            reactEditText.setGravityHorizontal(3);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            reactEditText.setJustificationMode(0);
        }
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityHorizontal(0);
        } else if ("left".equals(str)) {
            reactEditText.setGravityHorizontal(3);
        } else if ("right".equals(str)) {
            reactEditText.setGravityHorizontal(5);
        } else if ("center".equals(str)) {
            reactEditText.setGravityHorizontal(1);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textAlign: ", str));
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactEditText reactEditText, String str) {
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityVertical(0);
        } else if ("top".equals(str)) {
            reactEditText.setGravityVertical(48);
        } else if ("bottom".equals(str)) {
            reactEditText.setGravityVertical(80);
        } else if ("center".equals(str)) {
            reactEditText.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textAlignVertical: ", str));
        }
    }

    @ReactProp(name = "autoCompleteType")
    public void setTextContentType(ReactEditText reactEditText, String str) {
        if (str == null) {
            setImportantForAutofill(reactEditText, 2);
        } else if ("username".equals(str)) {
            setAutofillHints(reactEditText, "username");
        } else if ("password".equals(str)) {
            setAutofillHints(reactEditText, "password");
        } else if ("email".equals(str)) {
            setAutofillHints(reactEditText, "emailAddress");
        } else if ("name".equals(str)) {
            setAutofillHints(reactEditText, "name");
        } else if ("tel".equals(str)) {
            setAutofillHints(reactEditText, "phone");
        } else if ("street-address".equals(str)) {
            setAutofillHints(reactEditText, "postalAddress");
        } else if ("postal-code".equals(str)) {
            setAutofillHints(reactEditText, "postalCode");
        } else if ("cc-number".equals(str)) {
            setAutofillHints(reactEditText, "creditCardNumber");
        } else if ("cc-csc".equals(str)) {
            setAutofillHints(reactEditText, "creditCardSecurityCode");
        } else if ("cc-exp".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationDate");
        } else if ("cc-exp-month".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationMonth");
        } else if ("cc-exp-year".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationYear");
        } else if ("off".equals(str)) {
            setImportantForAutofill(reactEditText, 2);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid autoCompleteType: ", str));
        }
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText reactEditText, Integer num) {
        Drawable background = reactEditText.getBackground();
        if (background.getConstantState() != null) {
            try {
                background = background.mutate();
            } catch (NullPointerException e) {
                FLog.e(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", e);
            }
        }
        if (num == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(defaultBoolean = true, name = "showSoftInputOnFocus")
    public void showKeyboardOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setShowSoftInputOnFocus(z);
    }

    public void addEventEmitters(final ThemedReactContext themedReactContext, final ReactEditText reactEditText) {
        reactEditText.addTextChangedListener(new ReactTextInputTextWatcher(this, themedReactContext, reactEditText));
        reactEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.facebook.react.views.textinput.ReactTextInputManager.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                EventDispatcher eventDispatcher = ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
                if (z) {
                    eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(reactEditText.getId()));
                    return;
                }
                eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(reactEditText.getId()));
                eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(reactEditText.getId(), reactEditText.getText().toString()));
            }
        });
        reactEditText.setOnEditorActionListener(new TextView.OnEditorActionListener(this) { // from class: com.facebook.react.views.textinput.ReactTextInputManager.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i & 255) == 0 && i != 0) {
                    return true;
                }
                boolean blurOnSubmit = reactEditText.getBlurOnSubmit();
                boolean isMultiline = reactEditText.isMultiline();
                ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactTextInputSubmitEditingEvent(reactEditText.getId(), reactEditText.getText().toString()));
                if (blurOnSubmit) {
                    reactEditText.clearFocus();
                }
                return blurOnSubmit || !isMultiline || i == 5 || i == 7;
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactEditText createViewInstance(ThemedReactContext themedReactContext) {
        ReactEditText reactEditText = new ReactEditText(themedReactContext);
        reactEditText.setInputType(reactEditText.getInputType() & (-131073));
        reactEditText.setReturnKeyType("done");
        return reactEditText;
    }

    public void onAfterUpdateTransaction(ReactEditText reactEditText) {
        super.onAfterUpdateTransaction((ReactTextInputManager) reactEditText);
        if (reactEditText.mTypefaceDirty) {
            reactEditText.mTypefaceDirty = false;
            reactEditText.setTypeface(R$style.applyStyles(reactEditText.getTypeface(), reactEditText.mFontStyle, reactEditText.mFontWeight, reactEditText.mFontFamily, reactEditText.getContext().getAssets()));
        }
        if (reactEditText.getInputType() != reactEditText.mStagedInputType) {
            int selectionStart = reactEditText.getSelectionStart();
            int selectionEnd = reactEditText.getSelectionEnd();
            reactEditText.setInputType(reactEditText.mStagedInputType);
            reactEditText.setSelection(selectionStart, selectionEnd);
        }
    }

    public void setPadding(ReactEditText reactEditText, int i, int i2, int i3, int i4) {
        reactEditText.setPadding(i, i2, i3, i4);
    }

    public void updateExtraData(ReactEditText reactEditText, Object obj) {
        int i;
        if (obj instanceof ReactTextUpdate) {
            ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
            int i2 = (int) reactTextUpdate.mPaddingLeft;
            int i3 = (int) reactTextUpdate.mPaddingTop;
            int i4 = (int) reactTextUpdate.mPaddingRight;
            int i5 = (int) reactTextUpdate.mPaddingBottom;
            if (!(i2 == UNSET && i3 == UNSET && i4 == UNSET && i5 == UNSET)) {
                if (i2 == UNSET) {
                    i2 = reactEditText.getPaddingLeft();
                }
                if (i3 == UNSET) {
                    i3 = reactEditText.getPaddingTop();
                }
                if (i4 == UNSET) {
                    i4 = reactEditText.getPaddingRight();
                }
                if (i5 == UNSET) {
                    i5 = reactEditText.getPaddingBottom();
                }
                reactEditText.setPadding(i2, i3, i4, i5);
            }
            if (reactTextUpdate.mContainsImages) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.mText, reactEditText);
            }
            reactEditText.maybeSetText(reactTextUpdate);
            int i6 = reactTextUpdate.mSelectionStart;
            if (i6 != UNSET && (i = reactTextUpdate.mSelectionEnd) != UNSET) {
                reactEditText.setSelection(i6, i);
            }
        }
    }

    public Object updateState(ReactEditText reactEditText, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        ReadableNativeMap state = stateWrapper.getState();
        ReadableNativeMap map = state.getMap("attributedString");
        ReadableNativeMap map2 = state.getMap("paragraphAttributes");
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(reactEditText.getContext(), map);
        TextAttributeProps textAttributeProps = new TextAttributeProps(reactStylesDiffMap);
        int textBreakStrategy = getTextBreakStrategy(map2.getString("textBreakStrategy"));
        reactEditText.mStateWrapper = stateWrapper;
        ReactTextUpdate reactTextUpdate = new ReactTextUpdate(orCreateSpannableForText, state.getInt("mostRecentEventCount"), false, textAttributeProps.mTextAlign, textBreakStrategy, 0);
        reactTextUpdate.mAttributedString = map;
        return reactTextUpdate;
    }

    public void receiveCommand(ReactEditText reactEditText, int i, ReadableArray readableArray) {
        if (i == 1) {
            receiveCommand(reactEditText, "focus", readableArray);
        } else if (i == 2) {
            receiveCommand(reactEditText, "blur", readableArray);
        } else if (i == 3) {
            receiveCommand(reactEditText, "setMostRecentEventCount", readableArray);
        } else if (i == 4) {
            receiveCommand(reactEditText, "setTextAndSelection", readableArray);
        }
    }

    private void setImportantForAutofill(ReactEditText reactEditText, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            reactEditText.setImportantForAutofill(i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void receiveCommand(ReactEditText reactEditText, String str, ReadableArray readableArray) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1699362314:
                if (str.equals("blurTextInput")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3027047:
                if (str.equals("blur")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 97604824:
                if (str.equals("focus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1427010500:
                if (str.equals("setTextAndSelection")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1690703013:
                if (str.equals("focusTextInput")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1813033077:
                if (str.equals("setMostRecentEventCount")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                reactEditText.clearFocus();
                return;
            case 2:
            case 4:
                reactEditText.mShouldAllowFocus = true;
                reactEditText.requestFocus();
                reactEditText.mShouldAllowFocus = false;
                return;
            case 3:
                int i = readableArray.getInt(0);
                reactEditText.setMostRecentEventCount(i);
                if (i != UNSET) {
                    String string = readableArray.getString(1);
                    int i2 = readableArray.getInt(2);
                    int i3 = readableArray.getInt(3);
                    if (i3 == UNSET) {
                        i3 = i2;
                    }
                    ReactTextUpdate reactTextUpdate = getReactTextUpdate(string, i, i2, i3);
                    reactEditText.mIsSettingTextFromJS = true;
                    reactEditText.maybeSetText(reactTextUpdate);
                    reactEditText.mIsSettingTextFromJS = false;
                    return;
                }
                return;
            case 5:
                reactEditText.setMostRecentEventCount(readableArray.getInt(0));
                return;
            default:
                return;
        }
    }
}
