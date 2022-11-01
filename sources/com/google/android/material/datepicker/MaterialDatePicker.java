package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.BackStackRecord;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R$style;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.tidalab.v2board.clash.foss.R;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class MaterialDatePicker<S> extends DialogFragment {
    public static final /* synthetic */ int $r8$clinit = 0;
    public MaterialShapeDrawable background;
    public MaterialCalendar<S> calendar;
    public CalendarConstraints calendarConstraints;
    public Button confirmButton;
    public DateSelector<S> dateSelector;
    public boolean fullscreen;
    public TextView headerSelectionText;
    public CheckableImageButton headerToggleButton;
    public int inputMode;
    public int overrideThemeResId;
    public PickerFragment<S> pickerFragment;
    public CharSequence titleText;
    public int titleTextResId;
    public final LinkedHashSet<MaterialPickerOnPositiveButtonClickListener<? super S>> onPositiveButtonClickListeners = new LinkedHashSet<>();
    public final LinkedHashSet<View.OnClickListener> onNegativeButtonClickListeners = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnCancelListener> onCancelListeners = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnDismissListener> onDismissListeners = new LinkedHashSet<>();

    public static int getPaddedPickerWidth(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        todayCalendar.set(5, 1);
        Calendar dayCopy = UtcDates.getDayCopy(todayCalendar);
        dayCopy.get(2);
        dayCopy.get(1);
        int maximum = dayCopy.getMaximum(7);
        dayCopy.getActualMaximum(5);
        dayCopy.getTimeInMillis();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width) * maximum;
        return ((maximum - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding)) + dimensionPixelSize + (dimensionPixelOffset * 2);
    }

    public static boolean isFullscreen(Context context) {
        return readMaterialCalendarStyleBoolean(context, 16843277);
    }

    public static boolean readMaterialCalendarStyleBoolean(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$style.resolveOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{i});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.onCancelListeners.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = this.mArguments;
        }
        this.overrideThemeResId = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.dateSelector = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.titleTextResId = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.titleText = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.inputMode = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Context requireContext = requireContext();
        Context requireContext2 = requireContext();
        int i = this.overrideThemeResId;
        if (i == 0) {
            i = this.dateSelector.getDefaultThemeResId(requireContext2);
        }
        Dialog dialog = new Dialog(requireContext, i);
        Context context = dialog.getContext();
        this.fullscreen = isFullscreen(context);
        int resolveOrThrow = R$style.resolveOrThrow(context, R.attr.colorSurface, MaterialDatePicker.class.getCanonicalName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, R.attr.materialCalendarStyle, 2131886770);
        this.background = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(context);
        this.background.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        MaterialShapeDrawable materialShapeDrawable2 = this.background;
        View decorView = dialog.getWindow().getDecorView();
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        materialShapeDrawable2.setElevation(decorView.getElevation());
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.fullscreen ? R.layout.mtrl_picker_fullscreen : R.layout.mtrl_picker_dialog, viewGroup);
        Context context = inflate.getContext();
        if (this.fullscreen) {
            inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -2));
        } else {
            View findViewById = inflate.findViewById(R.id.mtrl_calendar_main_pane);
            View findViewById2 = inflate.findViewById(R.id.mtrl_calendar_frame);
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -1));
            Resources resources = requireContext().getResources();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
            int i = MonthAdapter.MAXIMUM_WEEKS;
            findViewById2.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding) * (i - 1)) + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * i) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text);
        this.headerSelectionText = textView;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        textView.setAccessibilityLiveRegion(1);
        this.headerToggleButton = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
        CharSequence charSequence = this.titleText;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.titleTextResId);
        }
        this.headerToggleButton.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.headerToggleButton;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, AppCompatResources.getDrawable(context, R.drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(context, R.drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.headerToggleButton.setChecked(this.inputMode != 0);
        ViewCompat.setAccessibilityDelegate(this.headerToggleButton, null);
        updateToggleContentDescription(this.headerToggleButton);
        this.headerToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
                materialDatePicker.confirmButton.setEnabled(materialDatePicker.dateSelector.isSelectionComplete());
                MaterialDatePicker.this.headerToggleButton.toggle();
                MaterialDatePicker materialDatePicker2 = MaterialDatePicker.this;
                materialDatePicker2.updateToggleContentDescription(materialDatePicker2.headerToggleButton);
                MaterialDatePicker.this.startPickerFragment();
            }
        });
        this.confirmButton = (Button) inflate.findViewById(R.id.confirm_button);
        if (this.dateSelector.isSelectionComplete()) {
            this.confirmButton.setEnabled(true);
        } else {
            this.confirmButton.setEnabled(false);
        }
        this.confirmButton.setTag("CONFIRM_BUTTON_TAG");
        this.confirmButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator<MaterialPickerOnPositiveButtonClickListener<? super S>> it = MaterialDatePicker.this.onPositiveButtonClickListeners.iterator();
                while (it.hasNext()) {
                    it.next().onPositiveButtonClick((S) MaterialDatePicker.this.dateSelector.getSelection());
                }
                MaterialDatePicker.this.dismissInternal(false, false);
            }
        });
        Button button = (Button) inflate.findViewById(R.id.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator<View.OnClickListener> it = MaterialDatePicker.this.onNegativeButtonClickListeners.iterator();
                while (it.hasNext()) {
                    it.next().onClick(view);
                }
                MaterialDatePicker.this.dismissInternal(false, false);
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.onDismissListeners.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.mView;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.overrideThemeResId);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.dateSelector);
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder(this.calendarConstraints);
        Month month = this.calendar.current;
        if (month != null) {
            builder.openAt = Long.valueOf(month.timeInMillis);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", builder.validator);
        Month create = Month.create(builder.start);
        Month create2 = Month.create(builder.end);
        CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = builder.openAt;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(create, create2, dateValidator, l == null ? null : Month.create(l.longValue()), null));
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.titleTextResId);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.titleText);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.fullscreen) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.background);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.background, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(requireDialog(), rect));
        }
        startPickerFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.pickerFragment.onSelectionChangedListeners.clear();
        this.mCalled = true;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public final void startPickerFragment() {
        PickerFragment<S> pickerFragment;
        Context requireContext = requireContext();
        int i = this.overrideThemeResId;
        if (i == 0) {
            i = this.dateSelector.getDefaultThemeResId(requireContext);
        }
        DateSelector<S> dateSelector = this.dateSelector;
        CalendarConstraints calendarConstraints = this.calendarConstraints;
        MaterialCalendar<S> materialCalendar = new MaterialCalendar<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.openAt);
        materialCalendar.setArguments(bundle);
        this.calendar = materialCalendar;
        if (this.headerToggleButton.isChecked()) {
            DateSelector<S> dateSelector2 = this.dateSelector;
            CalendarConstraints calendarConstraints2 = this.calendarConstraints;
            pickerFragment = new MaterialTextInputPicker<>();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", i);
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelector2);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            pickerFragment.setArguments(bundle2);
        } else {
            pickerFragment = this.calendar;
        }
        this.pickerFragment = pickerFragment;
        updateHeader();
        BackStackRecord backStackRecord = new BackStackRecord(getChildFragmentManager());
        backStackRecord.doAddOp(R.id.mtrl_calendar_frame, this.pickerFragment, null, 2);
        backStackRecord.disallowAddToBackStack();
        backStackRecord.mManager.execSingleAction(backStackRecord, false);
        this.pickerFragment.addOnSelectionChangedListener(new OnSelectionChangedListener<S>() { // from class: com.google.android.material.datepicker.MaterialDatePicker.3
            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public void onSelectionChanged(S s) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
                int i2 = MaterialDatePicker.$r8$clinit;
                materialDatePicker.updateHeader();
                MaterialDatePicker materialDatePicker2 = MaterialDatePicker.this;
                materialDatePicker2.confirmButton.setEnabled(materialDatePicker2.dateSelector.isSelectionComplete());
            }
        });
    }

    public final void updateHeader() {
        String selectionDisplayString = this.dateSelector.getSelectionDisplayString(getContext());
        this.headerSelectionText.setContentDescription(String.format(getString(R.string.mtrl_picker_announce_current_selection), selectionDisplayString));
        this.headerSelectionText.setText(selectionDisplayString);
    }

    public final void updateToggleContentDescription(CheckableImageButton checkableImageButton) {
        String str;
        if (this.headerToggleButton.isChecked()) {
            str = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode);
        } else {
            str = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode);
        }
        this.headerToggleButton.setContentDescription(str);
    }
}
