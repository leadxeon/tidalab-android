package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.Serializable;
/* compiled from: TunnelState.kt */
@Serializable
/* loaded from: classes.dex */
public final class TunnelState implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final Mode mode;

    /* compiled from: TunnelState.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<TunnelState> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public TunnelState createFromParcel(Parcel parcel) {
            return (TunnelState) TunnelState$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public TunnelState[] newArray(int i) {
            return new TunnelState[i];
        }
    }

    /* compiled from: TunnelState.kt */
    @Serializable
    /* loaded from: classes.dex */
    public enum Mode {
        Direct,
        Global,
        Rule,
        Script
    }

    public TunnelState(int i, Mode mode) {
        if (1 != (i & 1)) {
            TunnelState$$serializer tunnelState$$serializer = TunnelState$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 1, TunnelState$$serializer.descriptor);
        }
        this.mode = mode;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TunnelState) && this.mode == ((TunnelState) obj).mode;
    }

    public int hashCode() {
        return this.mode.hashCode();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TunnelState(mode=");
        outline13.append(this.mode);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        TunnelState$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }
}
