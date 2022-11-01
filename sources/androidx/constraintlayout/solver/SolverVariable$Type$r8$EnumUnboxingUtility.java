package androidx.constraintlayout.solver;

import java.util.Objects;
/* loaded from: classes.dex */
public /* synthetic */ class SolverVariable$Type$r8$EnumUnboxingUtility {
    public static /* synthetic */ int[] $VALUES$field$androidx$constraintlayout$solver$SolverVariable$Type;
    public static /* synthetic */ int[] $VALUES$field$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;
    public static /* synthetic */ int[] $VALUES$field$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType;
    public static /* synthetic */ int[] $VALUES$field$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType;
    public static /* synthetic */ int[] $VALUES$field$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy;
    public static /* synthetic */ int[] $VALUES$field$androidx$room$RoomDatabase$JournalMode;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$cache$common$CacheErrorLogger$CacheErrorCategory;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$cache$common$CacheEventListener$EvictionReason;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$common$statfs$StatFsHelper$StorageType;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$common$util$TriState;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$datasource$AbstractDataSource$DataSourceStatus;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$drawee$drawable$RoundedCornersDrawable$Type;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$drawee$generic$RoundingParams$RoundingMethod;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$imagepipeline$producers$JobScheduler$JobState;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$modules$core$ReactChoreographer$CallbackType;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$modules$datepicker$DatePickerMode;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$modules$timepicker$TimePickerMode;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$uimanager$NativeKind;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderRadiusLocation;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$soloader$MinElf$ISA;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$systrace$Systrace$EventScope;
    public static /* synthetic */ int[] $VALUES$field$com$facebook$yoga$YogaUnit;
    public static /* synthetic */ int[] $VALUES$field$com$google$android$material$datepicker$MaterialCalendar$CalendarSelector;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$Brush$BrushType;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$Brush$BrushUnits;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$ElementType;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$RNSVGMarkerType;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$SVGLength$UnitType;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$FontStyle;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$FontVariantLigatures;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextAnchor;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextLengthAdjust;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextPathMethod;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextPathMidLine;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextPathSide;
    public static /* synthetic */ int[] $VALUES$field$com$horcrux$svg$TextProperties$TextPathSpacing;
    public static /* synthetic */ int[] $VALUES$field$com$learnium$RNDeviceInfo$DeviceType;
    public static /* synthetic */ int[] $VALUES$field$com$swmansion$gesturehandler$PointerEventsConfig;
    public static /* synthetic */ int[] $VALUES$field$kotlin$collections$State;
    public static /* synthetic */ int[] $VALUES$field$kotlin$io$FileWalkDirection;

    public static synchronized /* synthetic */ int[] $VALUES$method$com$facebook$react$modules$core$ReactChoreographer$CallbackType() {
        int[] iArr;
        synchronized (SolverVariable$Type$r8$EnumUnboxingUtility.class) {
            if ($VALUES$field$com$facebook$react$modules$core$ReactChoreographer$CallbackType == null) {
                $VALUES$field$com$facebook$react$modules$core$ReactChoreographer$CallbackType = $enumboxing$values(5);
            }
            iArr = $VALUES$field$com$facebook$react$modules$core$ReactChoreographer$CallbackType;
        }
        return iArr;
    }

    public static /* synthetic */ int $enumboxing$ordinal(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static /* synthetic */ int[] $enumboxing$values(int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            iArr[i2] = i3;
            i2 = i3;
        }
        return iArr;
    }

    public static int[] com$facebook$react$modules$core$ReactChoreographer$CallbackType$s$values() {
        return (int[]) $VALUES$method$com$facebook$react$modules$core$ReactChoreographer$CallbackType().clone();
    }

    public static /* synthetic */ String getEnum$name$$com$swmansion$gesturehandler$PointerEventsConfig(int i) {
        if (i == 1) {
            return "NONE";
        }
        if (i == 2) {
            return "BOX_NONE";
        }
        if (i == 3) {
            return "BOX_ONLY";
        }
        if (i == 4) {
            return "AUTO";
        }
        throw null;
    }

    public static /* synthetic */ String getvalue$$com$learnium$RNDeviceInfo$DeviceType(int i) {
        if (i == 1) {
            return "Handset";
        }
        if (i == 2) {
            return "Tablet";
        }
        if (i == 3) {
            return "Tv";
        }
        if (i == 4) {
            return "unknown";
        }
        throw null;
    }

    public static /* synthetic */ int valueOfcom$facebook$react$modules$datepicker$DatePickerMode(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("CALENDAR")) {
            return 1;
        }
        if (str.equals("SPINNER")) {
            return 2;
        }
        if (str.equals("DEFAULT")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.facebook.react.modules.datepicker.DatePickerMode.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$facebook$react$modules$timepicker$TimePickerMode(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("CLOCK")) {
            return 1;
        }
        if (str.equals("SPINNER")) {
            return 2;
        }
        if (str.equals("DEFAULT")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.facebook.react.modules.timepicker.TimePickerMode.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("SOLID")) {
            return 1;
        }
        if (str.equals("DASHED")) {
            return 2;
        }
        if (str.equals("DOTTED")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.facebook.react.views.view.ReactViewBackgroundDrawable.BorderStyle.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$FontStyle(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("normal")) {
            return 1;
        }
        if (str.equals("italic")) {
            return 2;
        }
        if (str.equals("oblique")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.FontStyle.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$FontVariantLigatures(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("normal")) {
            return 1;
        }
        if (str.equals("none")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.FontVariantLigatures.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextAnchor(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("start")) {
            return 1;
        }
        if (str.equals("middle")) {
            return 2;
        }
        if (str.equals("end")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextAnchor.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextLengthAdjust(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("spacing")) {
            return 1;
        }
        if (str.equals("spacingAndGlyphs")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextLengthAdjust.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextPathMethod(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("align")) {
            return 1;
        }
        if (str.equals("stretch")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextPathMethod.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextPathMidLine(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("sharp")) {
            return 1;
        }
        if (str.equals("smooth")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextPathMidLine.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextPathSide(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("left")) {
            return 1;
        }
        if (str.equals("right")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextPathSide.".concat(str));
    }

    public static /* synthetic */ int valueOfcom$horcrux$svg$TextProperties$TextPathSpacing(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("auto")) {
            return 1;
        }
        if (str.equals("exact")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant com.horcrux.svg.TextProperties.TextPathSpacing.".concat(str));
    }
}
