package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: FetchStatus.kt */
@Serializable
/* loaded from: classes.dex */
public final class FetchStatus implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final Action action;
    public final List<String> args;
    public final int max;
    public final int progress;

    /* compiled from: FetchStatus.kt */
    /* loaded from: classes.dex */
    public enum Action {
        FetchConfiguration,
        FetchProviders,
        Verifying
    }

    /* compiled from: FetchStatus.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<FetchStatus> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public FetchStatus createFromParcel(Parcel parcel) {
            return (FetchStatus) FetchStatus$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public FetchStatus[] newArray(int i) {
            return new FetchStatus[i];
        }
    }

    public FetchStatus(int i, Action action, List list, int i2, int i3) {
        if (15 != (i & 15)) {
            FetchStatus$$serializer fetchStatus$$serializer = FetchStatus$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 15, FetchStatus$$serializer.descriptor);
        }
        this.action = action;
        this.args = list;
        this.progress = i2;
        this.max = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FetchStatus)) {
            return false;
        }
        FetchStatus fetchStatus = (FetchStatus) obj;
        return this.action == fetchStatus.action && Intrinsics.areEqual(this.args, fetchStatus.args) && this.progress == fetchStatus.progress && this.max == fetchStatus.max;
    }

    public int hashCode() {
        return ((((this.args.hashCode() + (this.action.hashCode() * 31)) * 31) + this.progress) * 31) + this.max;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FetchStatus(action=");
        outline13.append(this.action);
        outline13.append(", args=");
        outline13.append(this.args);
        outline13.append(", progress=");
        outline13.append(this.progress);
        outline13.append(", max=");
        outline13.append(this.max);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        FetchStatus$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }
}
