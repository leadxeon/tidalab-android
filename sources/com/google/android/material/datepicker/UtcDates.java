package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public class UtcDates {
    public static AtomicReference<TimeSource> timeSourceRef = new AtomicReference<>();

    public static long canonicalYearMonthDay(long j) {
        Calendar utcCalendar = getUtcCalendar();
        utcCalendar.setTimeInMillis(j);
        return getDayCopy(utcCalendar).getTimeInMillis();
    }

    public static Calendar getDayCopy(Calendar calendar) {
        Calendar utcCalendarOf = getUtcCalendarOf(calendar);
        Calendar utcCalendar = getUtcCalendar();
        utcCalendar.set(utcCalendarOf.get(1), utcCalendarOf.get(2), utcCalendarOf.get(5));
        return utcCalendar;
    }

    public static TimeZone getTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    public static Calendar getTodayCalendar() {
        TimeSource timeSource = timeSourceRef.get();
        if (timeSource == null) {
            timeSource = TimeSource.SYSTEM_TIME_SOURCE;
        }
        TimeZone timeZone = timeSource.fixedTimeZone;
        Calendar instance = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = timeSource.fixedTimeMs;
        if (l != null) {
            instance.setTimeInMillis(l.longValue());
        }
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.setTimeZone(getTimeZone());
        return instance;
    }

    public static Calendar getUtcCalendar() {
        return getUtcCalendarOf(null);
    }

    public static Calendar getUtcCalendarOf(Calendar calendar) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        if (calendar == null) {
            instance.clear();
        } else {
            instance.setTimeInMillis(calendar.getTimeInMillis());
        }
        return instance;
    }
}
