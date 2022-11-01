package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new Parcelable.Creator<CalendarConstraints>() { // from class: com.google.android.material.datepicker.CalendarConstraints.1
        @Override // android.os.Parcelable.Creator
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        public CalendarConstraints[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    };
    public final Month end;
    public final int monthSpan;
    public Month openAt;
    public final Month start;
    public final DateValidator validator;
    public final int yearSpan;

    /* loaded from: classes.dex */
    public static final class Builder {
        public long end;
        public Long openAt;
        public long start;
        public DateValidator validator;
        public static final long DEFAULT_START = UtcDates.canonicalYearMonthDay(Month.create(1900, 0).timeInMillis);
        public static final long DEFAULT_END = UtcDates.canonicalYearMonthDay(Month.create(2100, 11).timeInMillis);

        public Builder(CalendarConstraints calendarConstraints) {
            this.start = DEFAULT_START;
            this.end = DEFAULT_END;
            this.validator = new DateValidatorPointForward(Long.MIN_VALUE);
            this.start = calendarConstraints.start.timeInMillis;
            this.end = calendarConstraints.end.timeInMillis;
            this.openAt = Long.valueOf(calendarConstraints.openAt.timeInMillis);
            this.validator = calendarConstraints.validator;
        }
    }

    /* loaded from: classes.dex */
    public interface DateValidator extends Parcelable {
        boolean isValid(long j);
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, AnonymousClass1 r5) {
        this.start = month;
        this.end = month2;
        this.openAt = month3;
        this.validator = dateValidator;
        if (month3 != null && month.firstOfMonth.compareTo(month3.firstOfMonth) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (month3 == null || month3.firstOfMonth.compareTo(month2.firstOfMonth) <= 0) {
            this.monthSpan = month.monthsUntil(month2) + 1;
            this.yearSpan = (month2.year - month.year) + 1;
        } else {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.start.equals(calendarConstraints.start) && this.end.equals(calendarConstraints.end) && Objects.equals(this.openAt, calendarConstraints.openAt) && this.validator.equals(calendarConstraints.validator);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.openAt, this.validator});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.start, 0);
        parcel.writeParcelable(this.end, 0);
        parcel.writeParcelable(this.openAt, 0);
        parcel.writeParcelable(this.validator, 0);
    }
}
