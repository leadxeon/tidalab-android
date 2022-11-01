package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.foss.R;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes.dex */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    public final MaterialCalendar<?> materialCalendar;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public YearGridAdapter(MaterialCalendar<?> materialCalendar) {
        this.materialCalendar = materialCalendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.materialCalendar.calendarConstraints.yearSpan;
    }

    public int getPositionForYear(int i) {
        return i - this.materialCalendar.calendarConstraints.start.year;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = viewHolder;
        final int i2 = this.materialCalendar.calendarConstraints.start.year + i;
        String string = viewHolder2.textView.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        viewHolder2.textView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(i2)));
        viewHolder2.textView.setContentDescription(String.format(string, Integer.valueOf(i2)));
        CalendarStyle calendarStyle = this.materialCalendar.calendarStyle;
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        CalendarItemStyle calendarItemStyle = todayCalendar.get(1) == i2 ? calendarStyle.todayYear : calendarStyle.year;
        for (Long l : this.materialCalendar.dateSelector.getSelectedDays()) {
            todayCalendar.setTimeInMillis(l.longValue());
            if (todayCalendar.get(1) == i2) {
                calendarItemStyle = calendarStyle.selectedYear;
            }
        }
        calendarItemStyle.styleItem(viewHolder2.textView);
        viewHolder2.textView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.YearGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Month create = Month.create(i2, YearGridAdapter.this.materialCalendar.current.month);
                CalendarConstraints calendarConstraints = YearGridAdapter.this.materialCalendar.calendarConstraints;
                if (create.compareTo(calendarConstraints.start) < 0) {
                    create = calendarConstraints.start;
                } else if (create.compareTo(calendarConstraints.end) > 0) {
                    create = calendarConstraints.end;
                }
                YearGridAdapter.this.materialCalendar.setCurrentMonth(create);
                YearGridAdapter.this.materialCalendar.setSelector$enumunboxing$(1);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }
}
