package com.facebook.react.animated;

import java.util.List;
/* loaded from: classes.dex */
public abstract class AnimatedNode {
    public List<AnimatedNode> mChildren;
    public int mActiveIncomingNodes = 0;
    public int mBFSColor = 0;
    public int mTag = -1;

    public void onAttachedToNode(AnimatedNode animatedNode) {
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
    }

    public void update() {
    }
}
