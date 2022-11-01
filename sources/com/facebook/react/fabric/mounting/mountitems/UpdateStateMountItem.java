package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.uimanager.StateWrapper;
/* loaded from: classes.dex */
public class UpdateStateMountItem implements MountItem {
    public final int mReactTag;
    public final StateWrapper mStateWrapper;

    public UpdateStateMountItem(int i, StateWrapper stateWrapper) {
        this.mReactTag = i;
        this.mStateWrapper = stateWrapper;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline10(new StringBuilder("UpdateStateMountItem ["), this.mReactTag, "]");
    }
}
