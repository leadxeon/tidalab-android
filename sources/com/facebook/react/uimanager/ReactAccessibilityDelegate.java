package com.facebook.react.uimanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.util.HashMap;
import okhttp3.HttpUrl;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class ReactAccessibilityDelegate extends AccessibilityDelegateCompat {
    public static final HashMap<String, Integer> sActionIdMap;
    public static int sCounter = 1056964608;
    public final HashMap<Integer, String> mAccessibilityActionsMap = new HashMap<>();
    public Handler mHandler = new Handler(this) { // from class: com.facebook.react.uimanager.ReactAccessibilityDelegate.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ((View) message.obj).sendAccessibilityEvent(4);
        }
    };

    /* loaded from: classes.dex */
    public enum AccessibilityRole {
        NONE,
        BUTTON,
        LINK,
        SEARCH,
        IMAGE,
        IMAGEBUTTON,
        KEYBOARDKEY,
        TEXT,
        ADJUSTABLE,
        SUMMARY,
        HEADER,
        ALERT,
        CHECKBOX,
        COMBOBOX,
        MENU,
        MENUBAR,
        MENUITEM,
        PROGRESSBAR,
        RADIO,
        RADIOGROUP,
        SCROLLBAR,
        SPINBUTTON,
        SWITCH,
        TAB,
        TABLIST,
        TIMER,
        TOOLBAR;

        public static String getValue(AccessibilityRole accessibilityRole) {
            switch (accessibilityRole.ordinal()) {
                case 0:
                case 2:
                case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                case 10:
                case 11:
                case 13:
                case 14:
                case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                case 17:
                case 19:
                case 20:
                case 23:
                case 24:
                case 25:
                case 26:
                    return "android.view.View";
                case 1:
                    return "android.widget.Button";
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    return "android.widget.EditText";
                case 4:
                    return "android.widget.ImageView";
                case 5:
                    return "android.widget.ImageButon";
                case 6:
                    return "android.inputmethodservice.Keyboard$Key";
                case 7:
                    return "android.widget.TextView";
                case 8:
                    return "android.widget.SeekBar";
                case 12:
                    return "android.widget.CheckBox";
                case 18:
                    return "android.widget.RadioButton";
                case 21:
                    return "android.widget.SpinButton";
                case 22:
                    return "android.widget.Switch";
                default:
                    throw new IllegalArgumentException("Invalid accessibility role value: " + accessibilityRole);
            }
        }
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        sActionIdMap = hashMap;
        hashMap.put("activate", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()));
        hashMap.put("longpress", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId()));
        hashMap.put("increment", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId()));
        hashMap.put("decrement", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()));
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (readableMap != null && readableMap.hasKey("min") && readableMap.hasKey("now") && readableMap.hasKey("max")) {
            Dynamic dynamic = readableMap.getDynamic("min");
            Dynamic dynamic2 = readableMap.getDynamic("now");
            Dynamic dynamic3 = readableMap.getDynamic("max");
            if (dynamic != null) {
                ReadableType type = dynamic.getType();
                ReadableType readableType = ReadableType.Number;
                if (type == readableType && dynamic2 != null && dynamic2.getType() == readableType && dynamic3 != null && dynamic3.getType() == readableType) {
                    int asInt = dynamic.asInt();
                    int asInt2 = dynamic2.asInt();
                    int asInt3 = dynamic3.asInt();
                    if (asInt3 > asInt && asInt2 >= asInt && asInt3 >= asInt2) {
                        accessibilityEvent.setItemCount(asInt3 - asInt);
                        accessibilityEvent.setCurrentItemIndex(asInt2);
                    }
                }
            }
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
        AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(R.id.accessibility_role);
        if (accessibilityRole != null) {
            Context context = view.getContext();
            accessibilityNodeInfoCompat.mInfo.setClassName(AccessibilityRole.getValue(accessibilityRole));
            if (accessibilityRole.equals(AccessibilityRole.LINK)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.link_description));
                if (accessibilityNodeInfoCompat.getContentDescription() != null) {
                    SpannableString spannableString = new SpannableString(accessibilityNodeInfoCompat.getContentDescription());
                    spannableString.setSpan(new URLSpan(HttpUrl.FRAGMENT_ENCODE_SET), 0, spannableString.length(), 0);
                    accessibilityNodeInfoCompat.mInfo.setContentDescription(spannableString);
                }
                if (accessibilityNodeInfoCompat.getText() != null) {
                    SpannableString spannableString2 = new SpannableString(accessibilityNodeInfoCompat.getText());
                    spannableString2.setSpan(new URLSpan(HttpUrl.FRAGMENT_ENCODE_SET), 0, spannableString2.length(), 0);
                    accessibilityNodeInfoCompat.mInfo.setText(spannableString2);
                }
            } else if (accessibilityRole.equals(AccessibilityRole.SEARCH)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.search_description));
            } else if (accessibilityRole.equals(AccessibilityRole.IMAGE)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.image_description));
            } else if (accessibilityRole.equals(AccessibilityRole.IMAGEBUTTON)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.imagebutton_description));
                accessibilityNodeInfoCompat.mInfo.setClickable(true);
            } else if (accessibilityRole.equals(AccessibilityRole.BUTTON)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.button_description));
                accessibilityNodeInfoCompat.mInfo.setClickable(true);
            } else if (accessibilityRole.equals(AccessibilityRole.SUMMARY)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.summary_description));
            } else if (accessibilityRole.equals(AccessibilityRole.HEADER)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.header_description));
                accessibilityNodeInfoCompat.setCollectionItemInfo(new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(0, 1, 0, 1, true)));
            } else if (accessibilityRole.equals(AccessibilityRole.ALERT)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.alert_description));
            } else if (accessibilityRole.equals(AccessibilityRole.COMBOBOX)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.combobox_description));
            } else if (accessibilityRole.equals(AccessibilityRole.MENU)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menu_description));
            } else if (accessibilityRole.equals(AccessibilityRole.MENUBAR)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menubar_description));
            } else if (accessibilityRole.equals(AccessibilityRole.MENUITEM)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menuitem_description));
            } else if (accessibilityRole.equals(AccessibilityRole.PROGRESSBAR)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.progressbar_description));
            } else if (accessibilityRole.equals(AccessibilityRole.RADIOGROUP)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.radiogroup_description));
            } else if (accessibilityRole.equals(AccessibilityRole.SCROLLBAR)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.scrollbar_description));
            } else if (accessibilityRole.equals(AccessibilityRole.SPINBUTTON)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.spinbutton_description));
            } else if (accessibilityRole.equals(AccessibilityRole.TAB)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.rn_tab_description));
            } else if (accessibilityRole.equals(AccessibilityRole.TABLIST)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.tablist_description));
            } else if (accessibilityRole.equals(AccessibilityRole.TIMER)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.timer_description));
            } else if (accessibilityRole.equals(AccessibilityRole.TOOLBAR)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.toolbar_description));
            }
        }
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_state);
        if (readableMap != null) {
            Context context2 = view.getContext();
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                Dynamic dynamic = readableMap.getDynamic(nextKey);
                if (nextKey.equals("selected") && dynamic.getType() == ReadableType.Boolean) {
                    accessibilityNodeInfoCompat.mInfo.setSelected(dynamic.asBoolean());
                } else if (nextKey.equals("disabled") && dynamic.getType() == ReadableType.Boolean) {
                    accessibilityNodeInfoCompat.mInfo.setEnabled(!dynamic.asBoolean());
                } else if (nextKey.equals("checked") && dynamic.getType() == ReadableType.Boolean) {
                    boolean asBoolean = dynamic.asBoolean();
                    accessibilityNodeInfoCompat.mInfo.setCheckable(true);
                    accessibilityNodeInfoCompat.mInfo.setChecked(asBoolean);
                    if (accessibilityNodeInfoCompat.getClassName().equals(AccessibilityRole.getValue(AccessibilityRole.SWITCH))) {
                        accessibilityNodeInfoCompat.mInfo.setText(context2.getString(asBoolean ? R.string.state_on_description : R.string.state_off_description));
                    }
                }
            }
        }
        ReadableArray readableArray = (ReadableArray) view.getTag(R.id.accessibility_actions);
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                ReadableMap map = readableArray.getMap(i);
                if (map.hasKey("name")) {
                    int i2 = sCounter;
                    String string = map.hasKey("label") ? map.getString("label") : null;
                    HashMap<String, Integer> hashMap = sActionIdMap;
                    if (hashMap.containsKey(map.getString("name"))) {
                        i2 = hashMap.get(map.getString("name")).intValue();
                    } else {
                        sCounter++;
                    }
                    this.mAccessibilityActionsMap.put(Integer.valueOf(i2), map.getString("name"));
                    accessibilityNodeInfoCompat.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, string).mAction);
                } else {
                    throw new IllegalArgumentException("Unknown accessibility action.");
                }
            }
        }
        ReadableMap readableMap2 = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (readableMap2 != null && readableMap2.hasKey("min") && readableMap2.hasKey("now") && readableMap2.hasKey("max")) {
            Dynamic dynamic2 = readableMap2.getDynamic("min");
            Dynamic dynamic3 = readableMap2.getDynamic("now");
            Dynamic dynamic4 = readableMap2.getDynamic("max");
            if (dynamic2 != null) {
                ReadableType type = dynamic2.getType();
                ReadableType readableType = ReadableType.Number;
                if (type == readableType && dynamic3 != null && dynamic3.getType() == readableType && dynamic4 != null && dynamic4.getType() == readableType) {
                    int asInt = dynamic2.asInt();
                    int asInt2 = dynamic3.asInt();
                    int asInt3 = dynamic4.asInt();
                    if (asInt3 > asInt && asInt2 >= asInt && asInt3 >= asInt2) {
                        accessibilityNodeInfoCompat.mInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, asInt, asInt3, asInt2));
                    }
                }
            }
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (!this.mAccessibilityActionsMap.containsKey(Integer.valueOf(i))) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("actionName", this.mAccessibilityActionsMap.get(Integer.valueOf(i)));
        ReactContext reactContext = (ReactContext) view.getContext();
        if (reactContext.hasActiveCatalystInstance()) {
            ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "topAccessibilityAction", createMap);
        } else {
            ReactSoftException.logSoftException("ReactAccessibilityDelegate", new ReactNoCrashSoftException("Cannot get RCTEventEmitter, no CatalystInstance"));
        }
        AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(R.id.accessibility_role);
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (accessibilityRole != AccessibilityRole.ADJUSTABLE || (i != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId() && i != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId())) {
            return true;
        }
        if (readableMap != null && !readableMap.hasKey("text")) {
            if (this.mHandler.hasMessages(1, view)) {
                this.mHandler.removeMessages(1, view);
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, view), 200L);
        }
        return super.performAccessibilityAction(view, i, bundle);
    }
}
