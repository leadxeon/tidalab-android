package androidx.core.view;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AccessibilityDelegateCompat {
    public static final View.AccessibilityDelegate DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate mBridge;
    public final View.AccessibilityDelegate mOriginalDelegate;

    /* loaded from: classes.dex */
    public static final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {
        public final AccessibilityDelegateCompat mCompat;

        public AccessibilityDelegateAdapter(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            this.mCompat = accessibilityDelegateCompat;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.mCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            AccessibilityNodeProviderCompat accessibilityNodeProvider = this.mCompat.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return (AccessibilityNodeProvider) accessibilityNodeProvider.mProvider;
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            int i;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            Boolean bool = new ViewCompat.AccessibilityViewProperty<Boolean>(R.id.tag_screen_reader_focusable, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.2
                @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
                public Boolean frameworkGet(View view2) {
                    return Boolean.valueOf(view2.isScreenReaderFocusable());
                }

                @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
                public void frameworkSet(View view2, Boolean bool2) {
                    view2.setScreenReaderFocusable(bool2.booleanValue());
                }

                @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
                public boolean shouldUpdate(Boolean bool2, Boolean bool3) {
                    return !booleanNullToFalseEquals(bool2, bool3);
                }
            }.get(view);
            boolean booleanValue = bool == null ? false : bool.booleanValue();
            int i2 = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i2 >= 28) {
                accessibilityNodeInfo.setScreenReaderFocusable(booleanValue);
            } else {
                accessibilityNodeInfoCompat.setBooleanProperty(1, booleanValue);
            }
            Boolean bool2 = new ViewCompat.AnonymousClass5(R.id.tag_accessibility_heading, Boolean.class, 28).get(view);
            boolean booleanValue2 = bool2 == null ? false : bool2.booleanValue();
            if (i2 >= 28) {
                accessibilityNodeInfo.setHeading(booleanValue2);
            } else {
                accessibilityNodeInfoCompat.setBooleanProperty(2, booleanValue2);
            }
            CharSequence accessibilityPaneTitle = ViewCompat.getAccessibilityPaneTitle(view);
            if (i2 >= 28) {
                accessibilityNodeInfo.setPaneTitle(accessibilityPaneTitle);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", accessibilityPaneTitle);
            }
            CharSequence charSequence = new ViewCompat.AnonymousClass4(R.id.tag_state_description, CharSequence.class, 64, 30).get(view);
            if (i2 < 30) {
                z = false;
            }
            if (z) {
                accessibilityNodeInfo.setStateDescription(charSequence);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
            }
            this.mCompat.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            CharSequence text = accessibilityNodeInfo.getText();
            if (i2 < 26) {
                accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
                accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
                accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
                accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
                SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
                if (sparseArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                        if (((WeakReference) sparseArray.valueAt(i3)).get() == null) {
                            arrayList.add(Integer.valueOf(i3));
                        }
                    }
                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                        sparseArray.remove(((Integer) arrayList.get(i4)).intValue());
                    }
                }
                ClickableSpan[] clickableSpans = AccessibilityNodeInfoCompat.getClickableSpans(text);
                if (clickableSpans != null && clickableSpans.length > 0) {
                    accessibilityNodeInfoCompat.getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R.id.accessibility_action_clickable_span);
                    SparseArray sparseArray2 = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
                    if (sparseArray2 == null) {
                        sparseArray2 = new SparseArray();
                        view.setTag(R.id.tag_accessibility_clickable_spans, sparseArray2);
                    }
                    for (int i5 = 0; i5 < clickableSpans.length; i5++) {
                        ClickableSpan clickableSpan = clickableSpans[i5];
                        int i6 = 0;
                        while (true) {
                            if (i6 >= sparseArray2.size()) {
                                i = AccessibilityNodeInfoCompat.sClickableSpanId;
                                AccessibilityNodeInfoCompat.sClickableSpanId = i + 1;
                                break;
                            } else if (clickableSpan.equals((ClickableSpan) ((WeakReference) sparseArray2.valueAt(i6)).get())) {
                                i = sparseArray2.keyAt(i6);
                                break;
                            } else {
                                i6++;
                            }
                        }
                        sparseArray2.put(i, new WeakReference(clickableSpans[i5]));
                        ClickableSpan clickableSpan2 = clickableSpans[i5];
                        Spanned spanned = (Spanned) text;
                        accessibilityNodeInfoCompat.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan2)));
                        accessibilityNodeInfoCompat.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan2)));
                        accessibilityNodeInfoCompat.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan2)));
                        accessibilityNodeInfoCompat.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
                    }
                }
            }
            List list = (List) view.getTag(R.id.tag_accessibility_actions);
            if (list == null) {
                list = Collections.emptyList();
            }
            for (int i7 = 0; i7 < list.size(); i7++) {
                accessibilityNodeInfoCompat.addAction((AccessibilityNodeInfoCompat.AccessibilityActionCompat) list.get(i7));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.mCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.mCompat.performAccessibilityAction(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.mCompat.sendAccessibilityEvent(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }

    public AccessibilityDelegateCompat() {
        this.mOriginalDelegate = DEFAULT_DELEGATE;
        this.mBridge = new AccessibilityDelegateAdapter(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.mOriginalDelegate.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean performAccessibilityAction(android.view.View r9, int r10, android.os.Bundle r11) {
        /*
            r8 = this;
            r0 = 2131296703(0x7f0901bf, float:1.821133E38)
            java.lang.Object r0 = r9.getTag(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto L_0x000f
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x000f:
            r1 = 0
            r2 = 0
        L_0x0011:
            int r3 = r0.size()
            if (r2 >= r3) goto L_0x0070
            java.lang.Object r3 = r0.get(r2)
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r3 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r3
            int r4 = r3.getId()
            if (r4 != r10) goto L_0x006d
            androidx.core.view.accessibility.AccessibilityViewCommand r0 = r3.mCommand
            if (r0 == 0) goto L_0x0070
            r0 = 0
            java.lang.Class<? extends androidx.core.view.accessibility.AccessibilityViewCommand$CommandArguments> r2 = r3.mViewCommandArgumentClass
            if (r2 == 0) goto L_0x0066
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: Exception -> 0x0040
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r4)     // Catch: Exception -> 0x0040
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: Exception -> 0x0040
            java.lang.Object r2 = r2.newInstance(r4)     // Catch: Exception -> 0x0040
            androidx.core.view.accessibility.AccessibilityViewCommand$CommandArguments r2 = (androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments) r2     // Catch: Exception -> 0x0040
            java.util.Objects.requireNonNull(r2)     // Catch: Exception -> 0x003e
            goto L_0x0065
        L_0x003e:
            r0 = move-exception
            goto L_0x0044
        L_0x0040:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
        L_0x0044:
            java.lang.Class<? extends androidx.core.view.accessibility.AccessibilityViewCommand$CommandArguments> r4 = r3.mViewCommandArgumentClass
            if (r4 != 0) goto L_0x004b
            java.lang.String r4 = "null"
            goto L_0x004f
        L_0x004b:
            java.lang.String r4 = r4.getName()
        L_0x004f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed to execute command with argument class ViewCommandArgument: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "A11yActionCompat"
            android.util.Log.e(r5, r4, r0)
        L_0x0065:
            r0 = r2
        L_0x0066:
            androidx.core.view.accessibility.AccessibilityViewCommand r2 = r3.mCommand
            boolean r0 = r2.perform(r9, r0)
            goto L_0x0071
        L_0x006d:
            int r2 = r2 + 1
            goto L_0x0011
        L_0x0070:
            r0 = 0
        L_0x0071:
            if (r0 != 0) goto L_0x0079
            android.view.View$AccessibilityDelegate r0 = r8.mOriginalDelegate
            boolean r0 = r0.performAccessibilityAction(r9, r10, r11)
        L_0x0079:
            if (r0 != 0) goto L_0x00ca
            r2 = 2131296271(0x7f09000f, float:1.8210454E38)
            if (r10 != r2) goto L_0x00ca
            r10 = -1
            java.lang.String r0 = "ACCESSIBILITY_CLICKABLE_SPAN_ID"
            int r10 = r11.getInt(r0, r10)
            r11 = 2131296704(0x7f0901c0, float:1.8211332E38)
            java.lang.Object r11 = r9.getTag(r11)
            android.util.SparseArray r11 = (android.util.SparseArray) r11
            r0 = 1
            if (r11 == 0) goto L_0x00c9
            java.lang.Object r10 = r11.get(r10)
            java.lang.ref.WeakReference r10 = (java.lang.ref.WeakReference) r10
            if (r10 == 0) goto L_0x00c9
            java.lang.Object r10 = r10.get()
            android.text.style.ClickableSpan r10 = (android.text.style.ClickableSpan) r10
            if (r10 == 0) goto L_0x00c2
            android.view.accessibility.AccessibilityNodeInfo r11 = r9.createAccessibilityNodeInfo()
            java.lang.CharSequence r11 = r11.getText()
            android.text.style.ClickableSpan[] r11 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.getClickableSpans(r11)
            r2 = 0
        L_0x00b0:
            if (r11 == 0) goto L_0x00c2
            int r3 = r11.length
            if (r2 >= r3) goto L_0x00c2
            r3 = r11[r2]
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L_0x00bf
            r11 = 1
            goto L_0x00c3
        L_0x00bf:
            int r2 = r2 + 1
            goto L_0x00b0
        L_0x00c2:
            r11 = 0
        L_0x00c3:
            if (r11 == 0) goto L_0x00c9
            r10.onClick(r9)
            r1 = 1
        L_0x00c9:
            r0 = r1
        L_0x00ca:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.AccessibilityDelegateCompat.performAccessibilityAction(android.view.View, int, android.os.Bundle):boolean");
    }

    public void sendAccessibilityEvent(View view, int i) {
        this.mOriginalDelegate.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public AccessibilityDelegateCompat(View.AccessibilityDelegate accessibilityDelegate) {
        this.mOriginalDelegate = accessibilityDelegate;
        this.mBridge = new AccessibilityDelegateAdapter(this);
    }
}
