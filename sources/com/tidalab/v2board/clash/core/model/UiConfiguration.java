package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.Serializable;
/* compiled from: UiConfiguration.kt */
@Serializable
/* loaded from: classes.dex */
public final class UiConfiguration implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);

    /* compiled from: UiConfiguration.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<UiConfiguration> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public UiConfiguration createFromParcel(Parcel parcel) {
            return (UiConfiguration) UiConfiguration$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public UiConfiguration[] newArray(int i) {
            return new UiConfiguration[i];
        }
    }

    public UiConfiguration() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        UiConfiguration$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }

    public UiConfiguration(int i) {
        if ((i & 0) != 0) {
            UiConfiguration$$serializer uiConfiguration$$serializer = UiConfiguration$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 0, UiConfiguration$$serializer.descriptor);
        }
    }
}
