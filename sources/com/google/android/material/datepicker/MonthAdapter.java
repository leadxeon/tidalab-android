package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MonthAdapter extends BaseAdapter {
    public static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    public final CalendarConstraints calendarConstraints;
    public CalendarStyle calendarStyle;
    public final DateSelector<?> dateSelector;
    public final Month month;
    public Collection<Long> previouslySelectedDates;

    public MonthAdapter(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.month = month;
        this.dateSelector = dateSelector;
        this.calendarConstraints = calendarConstraints;
        this.previouslySelectedDates = dateSelector.getSelectedDays();
    }

    public int dayToPosition(int i) {
        return firstPositionInMonth() + (i - 1);
    }

    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return firstPositionInMonth() + this.month.daysInMonth;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.month.daysInWeek;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0119  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(int r9, android.view.View r10, android.view.ViewGroup r11) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    public final void updateSelectedState(TextView textView, long j) {
        CalendarItemStyle calendarItemStyle;
        if (textView != null) {
            boolean z = false;
            if (this.calendarConstraints.validator.isValid(j)) {
                textView.setEnabled(true);
                Iterator<Long> it = this.dateSelector.getSelectedDays().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (UtcDates.canonicalYearMonthDay(j) == UtcDates.canonicalYearMonthDay(it.next().longValue())) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    calendarItemStyle = this.calendarStyle.selectedDay;
                } else if (UtcDates.getTodayCalendar().getTimeInMillis() == j) {
                    calendarItemStyle = this.calendarStyle.todayDay;
                } else {
                    calendarItemStyle = this.calendarStyle.day;
                }
            } else {
                textView.setEnabled(false);
                calendarItemStyle = this.calendarStyle.invalidDay;
            }
            calendarItemStyle.styleItem(textView);
        }
    }

    public final void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.create(j).equals(this.month)) {
            Calendar dayCopy = UtcDates.getDayCopy(this.month.firstOfMonth);
            dayCopy.setTimeInMillis(j);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter2().dayToPosition(dayCopy.get(5)) - materialCalendarGridView.getFirstVisiblePosition()), j);
        }
    }

    @Override // android.widget.Adapter
    public Long getItem(int i) {
        if (i < this.month.daysFromStartOfWeekToFirstOfMonth() || i > lastPositionInMonth()) {
            return null;
        }
        Month month = this.month;
        Calendar dayCopy = UtcDates.getDayCopy(month.firstOfMonth);
        dayCopy.set(5, (i - month.daysFromStartOfWeekToFirstOfMonth()) + 1);
        return Long.valueOf(dayCopy.getTimeInMillis());
    }
}
