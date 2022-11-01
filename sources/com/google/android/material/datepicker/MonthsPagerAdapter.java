package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.tidalab.v2board.clash.foss.R;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {
    public final CalendarConstraints calendarConstraints;
    public final Context context;
    public final DateSelector<?> dateSelector;
    public final int itemHeight;
    public final MaterialCalendar.OnDayClickListener onDayClickListener;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final MaterialCalendarGridView monthGrid;
        public final TextView monthTitle;

        public ViewHolder(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.monthTitle = textView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            new ViewCompat.AnonymousClass5(R.id.tag_accessibility_heading, Boolean.class, 28).set(textView, Boolean.TRUE);
            this.monthGrid = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (!z) {
                textView.setVisibility(8);
            }
        }
    }

    public MonthsPagerAdapter(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.OnDayClickListener onDayClickListener) {
        Month month = calendarConstraints.start;
        Month month2 = calendarConstraints.end;
        Month month3 = calendarConstraints.openAt;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (month3.compareTo(month2) <= 0) {
            int i = MonthAdapter.MAXIMUM_WEEKS;
            int i2 = MaterialCalendar.$r8$clinit;
            int dimensionPixelSize = i * context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
            int dimensionPixelSize2 = MaterialDatePicker.isFullscreen(context) ? context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) : 0;
            this.context = context;
            this.itemHeight = dimensionPixelSize + dimensionPixelSize2;
            this.calendarConstraints = calendarConstraints;
            this.dateSelector = dateSelector;
            this.onDayClickListener = onDayClickListener;
            if (!this.mObservable.hasObservers()) {
                this.mHasStableIds = true;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.calendarConstraints.monthSpan;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.calendarConstraints.start.monthsLater(i).firstOfMonth.getTimeInMillis();
    }

    public Month getPageMonth(int i) {
        return this.calendarConstraints.start.monthsLater(i);
    }

    public int getPosition(Month month) {
        return this.calendarConstraints.start.monthsUntil(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = viewHolder;
        Month monthsLater = this.calendarConstraints.start.monthsLater(i);
        viewHolder2.monthTitle.setText(monthsLater.getLongName(viewHolder2.itemView.getContext()));
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder2.monthGrid.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter2() == null || !monthsLater.equals(materialCalendarGridView.getAdapter2().month)) {
            MonthAdapter monthAdapter = new MonthAdapter(monthsLater, this.dateSelector, this.calendarConstraints);
            materialCalendarGridView.setNumColumns(monthsLater.daysInWeek);
            materialCalendarGridView.setAdapter((ListAdapter) monthAdapter);
        } else {
            materialCalendarGridView.invalidate();
            MonthAdapter adapter = materialCalendarGridView.getAdapter2();
            for (Long l : adapter.previouslySelectedDates) {
                adapter.updateSelectedStateForDate(materialCalendarGridView, l.longValue());
            }
            DateSelector<?> dateSelector = adapter.dateSelector;
            if (dateSelector != null) {
                for (Long l2 : dateSelector.getSelectedDays()) {
                    adapter.updateSelectedStateForDate(materialCalendarGridView, l2.longValue());
                }
                adapter.previouslySelectedDates = adapter.dateSelector.getSelectedDays();
            }
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.datepicker.MonthsPagerAdapter.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                MonthAdapter adapter2 = materialCalendarGridView.getAdapter2();
                if (i2 >= adapter2.firstPositionInMonth() && i2 <= adapter2.lastPositionInMonth()) {
                    MaterialCalendar.OnDayClickListener onDayClickListener = MonthsPagerAdapter.this.onDayClickListener;
                    long longValue = materialCalendarGridView.getAdapter2().getItem(i2).longValue();
                    MaterialCalendar.AnonymousClass3 r1 = (MaterialCalendar.AnonymousClass3) onDayClickListener;
                    if (MaterialCalendar.this.calendarConstraints.validator.isValid(longValue)) {
                        MaterialCalendar.this.dateSelector.select(longValue);
                        Iterator it = MaterialCalendar.this.onSelectionChangedListeners.iterator();
                        while (it.hasNext()) {
                            ((OnSelectionChangedListener) it.next()).onSelectionChanged(MaterialCalendar.this.dateSelector.getSelection());
                        }
                        MaterialCalendar.this.recyclerView.getAdapter().mObservable.notifyChanged();
                        RecyclerView recyclerView = MaterialCalendar.this.yearSelector;
                        if (recyclerView != null) {
                            recyclerView.getAdapter().mObservable.notifyChanged();
                        }
                    }
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!MaterialDatePicker.isFullscreen(viewGroup.getContext())) {
            return new ViewHolder(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.itemHeight));
        return new ViewHolder(linearLayout, true);
    }
}
