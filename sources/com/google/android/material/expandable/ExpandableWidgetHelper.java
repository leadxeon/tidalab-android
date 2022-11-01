package com.google.android.material.expandable;

import android.view.View;
/* loaded from: classes.dex */
public final class ExpandableWidgetHelper {
    public boolean expanded = false;
    public int expandedComponentIdHint = 0;
    public final View widget;

    public ExpandableWidgetHelper(ExpandableWidget expandableWidget) {
        this.widget = (View) expandableWidget;
    }
}
