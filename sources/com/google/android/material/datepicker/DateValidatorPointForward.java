package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;
/* loaded from: classes.dex */
public class DateValidatorPointForward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointForward> CREATOR = new Parcelable.Creator<DateValidatorPointForward>() { // from class: com.google.android.material.datepicker.DateValidatorPointForward.1
        @Override // android.os.Parcelable.Creator
        public DateValidatorPointForward createFromParcel(Parcel parcel) {
            return new DateValidatorPointForward(parcel.readLong(), null);
        }

        @Override // android.os.Parcelable.Creator
        public DateValidatorPointForward[] newArray(int i) {
            return new DateValidatorPointForward[i];
        }
    };
    public final long point;

    public DateValidatorPointForward(long j) {
        this.point = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DateValidatorPointForward) && this.point == ((DateValidatorPointForward) obj).point;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.point)});
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean isValid(long j) {
        return j >= this.point;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.point);
    }

    public DateValidatorPointForward(long j, AnonymousClass1 r3) {
        this.point = j;
    }
}
