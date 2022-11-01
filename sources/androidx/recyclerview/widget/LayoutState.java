package androidx.recyclerview.widget;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class LayoutState {
    public int mAvailable;
    public int mCurrentPosition;
    public boolean mInfinite;
    public int mItemDirection;
    public int mLayoutDirection;
    public boolean mStopInFocusable;
    public boolean mRecycle = true;
    public int mStartLine = 0;
    public int mEndLine = 0;

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("LayoutState{mAvailable=");
        outline13.append(this.mAvailable);
        outline13.append(", mCurrentPosition=");
        outline13.append(this.mCurrentPosition);
        outline13.append(", mItemDirection=");
        outline13.append(this.mItemDirection);
        outline13.append(", mLayoutDirection=");
        outline13.append(this.mLayoutDirection);
        outline13.append(", mStartLine=");
        outline13.append(this.mStartLine);
        outline13.append(", mEndLine=");
        outline13.append(this.mEndLine);
        outline13.append('}');
        return outline13.toString();
    }
}
