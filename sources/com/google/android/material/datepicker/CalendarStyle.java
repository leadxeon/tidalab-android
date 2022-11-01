package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public final class CalendarStyle {
    public final CalendarItemStyle day;
    public final CalendarItemStyle invalidDay;
    public final Paint rangeFill;
    public final CalendarItemStyle selectedDay;
    public final CalendarItemStyle selectedYear;
    public final CalendarItemStyle todayDay;
    public final CalendarItemStyle todayYear;
    public final CalendarItemStyle year;

    public CalendarStyle(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$style.resolveOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), R$styleable.MaterialCalendar);
        this.day = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(3, 0));
        this.invalidDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(1, 0));
        this.selectedDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(2, 0));
        this.todayDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(4, 0));
        ColorStateList colorStateList = R$style.getColorStateList(context, obtainStyledAttributes, 6);
        this.year = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(8, 0));
        this.selectedYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(7, 0));
        this.todayYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(9, 0));
        Paint paint = new Paint();
        this.rangeFill = paint;
        paint.setColor(colorStateList.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
