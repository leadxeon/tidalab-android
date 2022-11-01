package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.tidalab.v2board.clash.foss.R;
import java.util.Calendar;
/* loaded from: classes.dex */
public final class MaterialCalendar<S> extends PickerFragment<S> {
    public static final /* synthetic */ int $r8$clinit = 0;
    public CalendarConstraints calendarConstraints;
    public int calendarSelector;
    public CalendarStyle calendarStyle;
    public Month current;
    public DateSelector<S> dateSelector;
    public View dayFrame;
    public RecyclerView recyclerView;
    public int themeResId;
    public View yearFrame;
    public RecyclerView yearSelector;

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 implements OnDayClickListener {
        public AnonymousClass3() {
        }
    }

    /* loaded from: classes.dex */
    public interface OnDayClickListener {
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> onSelectionChangedListener) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.recyclerView.getLayoutManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = this.mArguments;
        }
        this.themeResId = bundle.getInt("THEME_RES_ID_KEY");
        this.dateSelector = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.current = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.calendarConstraints.start;
        if (MaterialDatePicker.isFullscreen(contextThemeWrapper)) {
            i2 = R.layout.mtrl_calendar_vertical;
            i = 1;
        } else {
            i2 = R.layout.mtrl_calendar_horizontal;
            i = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new AccessibilityDelegateCompat(this) { // from class: com.google.android.material.datepicker.MaterialCalendar.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                accessibilityNodeInfoCompat.setCollectionInfo(null);
            }
        });
        gridView.setAdapter((ListAdapter) new DaysOfWeekAdapter());
        gridView.setNumColumns(month.daysInWeek);
        gridView.setEnabled(false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.recyclerView.setLayoutManager(new SmoothCalendarLayoutManager(getContext(), i, false) { // from class: com.google.android.material.datepicker.MaterialCalendar.2
            @Override // androidx.recyclerview.widget.LinearLayoutManager
            public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
                if (i == 0) {
                    iArr[0] = MaterialCalendar.this.recyclerView.getWidth();
                    iArr[1] = MaterialCalendar.this.recyclerView.getWidth();
                    return;
                }
                iArr[0] = MaterialCalendar.this.recyclerView.getHeight();
                iArr[1] = MaterialCalendar.this.recyclerView.getHeight();
            }
        });
        this.recyclerView.setTag("MONTHS_VIEW_GROUP_TAG");
        final MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.dateSelector, this.calendarConstraints, new AnonymousClass3());
        this.recyclerView.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.yearSelector = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.yearSelector.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.google.android.material.datepicker.MaterialCalendar.4
                public final Calendar startItem = UtcDates.getUtcCalendar();
                public final Calendar endItem = UtcDates.getUtcCalendar();

                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                    int i3;
                    if ((recyclerView2.getAdapter() instanceof YearGridAdapter) && (recyclerView2.getLayoutManager() instanceof GridLayoutManager)) {
                        YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView2.getAdapter();
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView2.getLayoutManager();
                        for (Pair<Long, Long> pair : MaterialCalendar.this.dateSelector.getSelectedRanges()) {
                            Long l = pair.first;
                            if (!(l == null || pair.second == null)) {
                                this.startItem.setTimeInMillis(l.longValue());
                                this.endItem.setTimeInMillis(pair.second.longValue());
                                int positionForYear = yearGridAdapter.getPositionForYear(this.startItem.get(1));
                                int positionForYear2 = yearGridAdapter.getPositionForYear(this.endItem.get(1));
                                View findViewByPosition = gridLayoutManager.findViewByPosition(positionForYear);
                                View findViewByPosition2 = gridLayoutManager.findViewByPosition(positionForYear2);
                                int i4 = gridLayoutManager.mSpanCount;
                                int i5 = positionForYear / i4;
                                int i6 = positionForYear2 / i4;
                                for (int i7 = i5; i7 <= i6; i7++) {
                                    View findViewByPosition3 = gridLayoutManager.findViewByPosition(gridLayoutManager.mSpanCount * i7);
                                    if (findViewByPosition3 != null) {
                                        int top = findViewByPosition3.getTop() + MaterialCalendar.this.calendarStyle.year.insets.top;
                                        int bottom = findViewByPosition3.getBottom() - MaterialCalendar.this.calendarStyle.year.insets.bottom;
                                        int width = i7 == i5 ? (findViewByPosition.getWidth() / 2) + findViewByPosition.getLeft() : 0;
                                        if (i7 == i6) {
                                            i3 = (findViewByPosition2.getWidth() / 2) + findViewByPosition2.getLeft();
                                        } else {
                                            i3 = recyclerView2.getWidth();
                                        }
                                        canvas.drawRect(width, top, i3, bottom, MaterialCalendar.this.calendarStyle.rangeFill);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            final MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.5
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    String str;
                    this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                    if (MaterialCalendar.this.dayFrame.getVisibility() == 0) {
                        str = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_year_selection);
                    } else {
                        str = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_day_selection);
                    }
                    accessibilityNodeInfoCompat.setHintText(str);
                }
            });
            MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(R.id.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) inflate.findViewById(R.id.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.yearFrame = inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
            this.dayFrame = inflate.findViewById(R.id.mtrl_calendar_day_selector_frame);
            setSelector$enumunboxing$(1);
            materialButton.setText(this.current.getLongName(inflate.getContext()));
            this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.6
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i3) {
                    if (i3 == 0) {
                        recyclerView2.announceForAccessibility(materialButton.getText());
                    }
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i3, int i4) {
                    int i5;
                    if (i3 < 0) {
                        i5 = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition();
                    } else {
                        i5 = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition();
                    }
                    MaterialCalendar.this.current = monthsPagerAdapter.getPageMonth(i5);
                    MaterialButton materialButton4 = materialButton;
                    MonthsPagerAdapter monthsPagerAdapter2 = monthsPagerAdapter;
                    materialButton4.setText(monthsPagerAdapter2.calendarConstraints.start.monthsLater(i5).getLongName(monthsPagerAdapter2.context));
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MaterialCalendar materialCalendar = MaterialCalendar.this;
                    int i3 = materialCalendar.calendarSelector;
                    if (i3 == 2) {
                        materialCalendar.setSelector$enumunboxing$(1);
                    } else if (i3 == 1) {
                        materialCalendar.setSelector$enumunboxing$(2);
                    }
                }
            });
            materialButton3.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int findFirstVisibleItemPosition = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition() + 1;
                    if (findFirstVisibleItemPosition < MaterialCalendar.this.recyclerView.getAdapter().getItemCount()) {
                        MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(findFirstVisibleItemPosition));
                    }
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int findLastVisibleItemPosition = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition() - 1;
                    if (findLastVisibleItemPosition >= 0) {
                        MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(findLastVisibleItemPosition));
                    }
                }
            });
        }
        if (!MaterialDatePicker.isFullscreen(contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.recyclerView);
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter.getPosition(this.current));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.current);
    }

    public final void postSmoothRecyclerViewScroll(final int i) {
        this.recyclerView.post(new Runnable() { // from class: com.google.android.material.datepicker.MaterialCalendar.10
            @Override // java.lang.Runnable
            public void run() {
                MaterialCalendar.this.recyclerView.smoothScrollToPosition(i);
            }
        });
    }

    public void setCurrentMonth(Month month) {
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        int monthsUntil = monthsPagerAdapter.calendarConstraints.start.monthsUntil(month);
        int position = monthsUntil - monthsPagerAdapter.getPosition(this.current);
        boolean z = true;
        boolean z2 = Math.abs(position) > 3;
        if (position <= 0) {
            z = false;
        }
        this.current = month;
        if (z2 && z) {
            this.recyclerView.scrollToPosition(monthsUntil - 3);
            postSmoothRecyclerViewScroll(monthsUntil);
        } else if (z2) {
            this.recyclerView.scrollToPosition(monthsUntil + 3);
            postSmoothRecyclerViewScroll(monthsUntil);
        } else {
            postSmoothRecyclerViewScroll(monthsUntil);
        }
    }

    public void setSelector$enumunboxing$(int i) {
        this.calendarSelector = i;
        if (i == 2) {
            this.yearSelector.getLayoutManager().scrollToPosition(((YearGridAdapter) this.yearSelector.getAdapter()).getPositionForYear(this.current.year));
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
        } else if (i == 1) {
            this.yearFrame.setVisibility(8);
            this.dayFrame.setVisibility(0);
            setCurrentMonth(this.current);
        }
    }
}
