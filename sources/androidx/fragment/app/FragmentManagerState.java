package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: androidx.fragment.app.FragmentManagerState.1
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    };
    public ArrayList<FragmentState> mActive;
    public ArrayList<String> mAdded;
    public BackStackState[] mBackStack;
    public int mBackStackIndex;
    public ArrayList<FragmentManager.LaunchedFragmentInfo> mLaunchedFragments;
    public String mPrimaryNavActiveWho;
    public ArrayList<String> mResultKeys;
    public ArrayList<Bundle> mResults;

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
        this.mResultKeys = new ArrayList<>();
        this.mResults = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mActive);
        parcel.writeStringList(this.mAdded);
        parcel.writeTypedArray(this.mBackStack, i);
        parcel.writeInt(this.mBackStackIndex);
        parcel.writeString(this.mPrimaryNavActiveWho);
        parcel.writeStringList(this.mResultKeys);
        parcel.writeTypedList(this.mResults);
        parcel.writeTypedList(this.mLaunchedFragments);
    }

    public FragmentManagerState(Parcel parcel) {
        this.mPrimaryNavActiveWho = null;
        this.mResultKeys = new ArrayList<>();
        this.mResults = new ArrayList<>();
        this.mActive = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.mAdded = parcel.createStringArrayList();
        this.mBackStack = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.mBackStackIndex = parcel.readInt();
        this.mPrimaryNavActiveWho = parcel.readString();
        this.mResultKeys = parcel.createStringArrayList();
        this.mResults = parcel.createTypedArrayList(Bundle.CREATOR);
        this.mLaunchedFragments = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
