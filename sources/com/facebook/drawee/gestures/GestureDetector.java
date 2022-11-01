package com.facebook.drawee.gestures;
/* loaded from: classes.dex */
public class GestureDetector {
    public long mActionDownTime;
    public float mActionDownX;
    public float mActionDownY;
    public ClickListener mClickListener;
    public boolean mIsCapturingGesture;
    public boolean mIsClickCandidate;
    public final float mSingleTapSlopPx;

    /* loaded from: classes.dex */
    public interface ClickListener {
    }
}
