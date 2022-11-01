package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {
    public static int sClickableSpanId;
    public final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId = -1;
    public int mVirtualDescendantId = -1;

    /* loaded from: classes.dex */
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public final Object mAction;
        public final AccessibilityViewCommand mCommand;
        public final int mId;
        public final Class<? extends AccessibilityViewCommand.CommandArguments> mViewCommandArgumentClass;
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);

        static {
            new AccessibilityNodeInfo.AccessibilityAction(4, null);
            new AccessibilityNodeInfo.AccessibilityAction(8, null);
            new AccessibilityNodeInfo.AccessibilityAction(64, null);
            new AccessibilityNodeInfo.AccessibilityAction(128, null);
            new AccessibilityNodeInfo.AccessibilityAction(256, null);
            new AccessibilityNodeInfo.AccessibilityAction(512, null);
            new AccessibilityNodeInfo.AccessibilityAction(1024, null);
            new AccessibilityNodeInfo.AccessibilityAction(2048, null);
            new AccessibilityNodeInfo.AccessibilityAction(Http2.INITIAL_MAX_FRAME_SIZE, null);
            new AccessibilityNodeInfo.AccessibilityAction(32768, null);
            new AccessibilityNodeInfo.AccessibilityAction(65536, null);
            new AccessibilityNodeInfo.AccessibilityAction(131072, null);
            new AccessibilityNodeInfo.AccessibilityAction(2097152, null);
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908342, null);
            }
            if ((i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908343, null);
            }
            ACTION_SCROLL_UP = new AccessibilityActionCompat(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, 16908344, null, null, null);
            if ((i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908345, null);
            }
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, 16908346, null, null, null);
            if ((i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908347, null);
            }
            if ((i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908358, null);
            }
            if ((i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908359, null);
            }
            if ((i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908360, null);
            }
            if ((i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908361, null);
            }
            if ((i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908348, null);
            }
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(i >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, 16908349, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            if ((i >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908354, null);
            }
            if ((i >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908356, null);
            }
            if ((i >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908357, null);
            }
            if ((i >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908362, null);
            }
            if ((i >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(16908372, null);
            }
        }

        public AccessibilityActionCompat(int i, CharSequence charSequence) {
            this(null, i, charSequence, null, null);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof AccessibilityActionCompat)) {
                return false;
            }
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) obj;
            Object obj2 = this.mAction;
            return obj2 == null ? accessibilityActionCompat.mAction == null : obj2.equals(accessibilityActionCompat.mAction);
        }

        public int getId() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
        }

        public CharSequence getLabel() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
        }

        public int hashCode() {
            Object obj = this.mAction;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public AccessibilityActionCompat(Object obj, int i, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand, Class<? extends AccessibilityViewCommand.CommandArguments> cls) {
            this.mId = i;
            this.mCommand = accessibilityViewCommand;
            if (obj == null) {
                this.mAction = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
            } else {
                this.mAction = obj;
            }
            this.mViewCommandArgumentClass = cls;
        }
    }

    /* loaded from: classes.dex */
    public static class CollectionInfoCompat {
        public final Object mInfo;

        public CollectionInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public static CollectionInfoCompat obtain(int i, int i2, boolean z, int i3) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
        }
    }

    /* loaded from: classes.dex */
    public static class CollectionItemInfoCompat {
        public final Object mInfo;

        public CollectionItemInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
        }
    }

    public AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    public static String getActionSymbolicName(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                return "ACTION_CLICK";
            case WebSocketProtocol.B0_FLAG_RSV2 /* 32 */:
                return "ACTION_LONG_CLICK";
            case WebSocketProtocol.B0_FLAG_RSV1 /* 64 */:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case Http2.INITIAL_MAX_FRAME_SIZE /* 16384 */:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case 16908354:
                return "ACTION_MOVE_WINDOW";
            case 16908372:
                return "ACTION_IME_ENTER";
            default:
                switch (i) {
                    case 16908342:
                        return "ACTION_SHOW_ON_SCREEN";
                    case 16908343:
                        return "ACTION_SCROLL_TO_POSITION";
                    case 16908344:
                        return "ACTION_SCROLL_UP";
                    case 16908345:
                        return "ACTION_SCROLL_LEFT";
                    case 16908346:
                        return "ACTION_SCROLL_DOWN";
                    case 16908347:
                        return "ACTION_SCROLL_RIGHT";
                    case 16908348:
                        return "ACTION_CONTEXT_CLICK";
                    case 16908349:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i) {
                            case 16908356:
                                return "ACTION_SHOW_TOOLTIP";
                            case 16908357:
                                return "ACTION_HIDE_TOOLTIP";
                            case 16908358:
                                return "ACTION_PAGE_UP";
                            case 16908359:
                                return "ACTION_PAGE_DOWN";
                            case 16908360:
                                return "ACTION_PAGE_LEFT";
                            case 16908361:
                                return "ACTION_PAGE_RIGHT";
                            case 16908362:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static ClickableSpan[] getClickableSpans(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            if (accessibilityNodeInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
            return false;
        }
        return this.mVirtualDescendantId == accessibilityNodeInfoCompat.mVirtualDescendantId && this.mParentVirtualDescendantId == accessibilityNodeInfoCompat.mParentVirtualDescendantId;
    }

    public final List<Integer> extrasIntList(String str) {
        ArrayList<Integer> integerArrayList = this.mInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mInfo.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public Bundle getExtras() {
        return this.mInfo.getExtras();
    }

    public CharSequence getText() {
        if (!(!extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty())) {
            return this.mInfo.getText();
        }
        List<Integer> extrasIntList = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> extrasIntList2 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> extrasIntList3 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> extrasIntList4 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
        for (int i = 0; i < extrasIntList.size(); i++) {
            spannableString.setSpan(new AccessibilityClickableSpanCompat(extrasIntList4.get(i).intValue(), this, getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), extrasIntList.get(i).intValue(), extrasIntList2.get(i).intValue(), extrasIntList3.get(i).intValue());
        }
        return spannableString;
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityActionCompat) {
        return this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
    }

    public final void setBooleanProperty(int i, boolean z) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i2 = extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i);
            if (!z) {
                i = 0;
            }
            extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    public void setCollectionInfo(Object obj) {
        this.mInfo.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) obj).mInfo);
    }

    public void setCollectionItemInfo(Object obj) {
        this.mInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) obj).mInfo);
    }

    public void setHintText(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence);
        } else {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public void setParent(View view) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(view);
    }

    public void setRoleDescription(CharSequence charSequence) {
        this.mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.ArrayList] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.accessibility.AccessibilityNodeInfoCompat.toString():java.lang.String");
    }
}
