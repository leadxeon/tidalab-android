package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tidalab.v2board.clash.foss.R;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes.dex */
public class DaysOfWeekAdapter extends BaseAdapter {
    public static final int CALENDAR_DAY_STYLE;
    public final Calendar calendar;
    public final int daysInWeek;
    public final int firstDayOfWeek;

    static {
        CALENDAR_DAY_STYLE = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public DaysOfWeekAdapter() {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        this.calendar = utcCalendar;
        this.daysInWeek = utcCalendar.getMaximum(7);
        this.firstDayOfWeek = utcCalendar.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.daysInWeek;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        int i2 = this.daysInWeek;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.firstDayOfWeek;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        Calendar calendar = this.calendar;
        int i2 = i + this.firstDayOfWeek;
        int i3 = this.daysInWeek;
        if (i2 > i3) {
            i2 -= i3;
        }
        calendar.set(7, i2);
        textView.setText(this.calendar.getDisplayName(7, CALENDAR_DAY_STYLE, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R.string.mtrl_picker_day_of_week_column_header), this.calendar.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
