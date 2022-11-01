package com.horcrux.svg;

import android.app.ActivityThread;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.core.content.ContextCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.dialog.DialogModule;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.th3rdwave.safeareacontext.EdgeInsets;
import com.th3rdwave.safeareacontext.Rect;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.common.util.TickerKt$ticker$1;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import okhttp3.internal.ws.WebSocketProtocol;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class PathParser {
    public static ArrayList<PathElement> elements;
    public static int i;
    public static int l;
    public static Path mPath;
    public static boolean mPenDown;
    public static float mPenDownX;
    public static float mPenDownY;
    public static float mPenX;
    public static float mPenY;
    public static float mPivotX;
    public static float mPivotY;
    public static float mScale;
    public static String s;

    /* JADX WARN: Removed duplicated region for block: B:53:0x01d9 A[LOOP:0: B:52:0x01d7->B:53:0x01d9, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void arcTo(float r26, float r27, float r28, boolean r29, boolean r30, float r31, float r32) {
        /*
            Method dump skipped, instructions count: 655
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.arcTo(float, float, float, boolean, boolean, float, float):void");
    }

    public static String buildKeySelection(int i2) {
        String[] strArr = new String[i2];
        Arrays.fill(strArr, "?");
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("key IN ("), TextUtils.join(", ", strArr), ")");
    }

    public static final <T extends Parcelable> List<T> createListFromParcelSlice(Parcelable.Creator<T> creator, Parcel parcel, int i2, int i3) {
        int readInt = parcel.readInt();
        IBinder readStrongBinder = parcel.readStrongBinder();
        ArrayList arrayList = new ArrayList(readInt);
        int i4 = 0;
        while (i4 < readInt) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInt(i4);
                obtain.writeInt(i3);
                if (readStrongBinder.transact(10, obtain, obtain2, i2)) {
                    int readInt2 = obtain2.readInt();
                    for (int i5 = 0; i5 < readInt2; i5++) {
                        arrayList.add(creator.createFromParcel(obtain2));
                    }
                    i4 += readInt2;
                    if (readInt2 == 0) {
                    }
                }
                break;
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
        return arrayList;
    }

    public static void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        setPenDown();
        mPenX = f5;
        mPenY = f6;
        Path path = mPath;
        float f7 = mScale;
        path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f6 * f7);
        elements.add(new PathElement(1, new Point[]{new Point(f, f2), new Point(f3, f4), new Point(f5, f6)}));
    }

    public static void deepMergeInto(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject2.optJSONObject(next);
            JSONObject optJSONObject2 = jSONObject.optJSONObject(next);
            if (optJSONObject == null || optJSONObject2 == null) {
                jSONObject.put(next, jSONObject2.get(next));
            } else {
                deepMergeInto(optJSONObject2, optJSONObject);
                jSONObject.put(next, optJSONObject2);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static double fromRelative(String str, double d, double d2, double d3) {
        double doubleValue;
        String trim = str.trim();
        int length = trim.length();
        int i2 = length - 1;
        if (length == 0 || trim.equals("normal")) {
            return 0.0d;
        }
        if (trim.codePointAt(i2) == 37) {
            return (Double.valueOf(trim.substring(0, i2)).doubleValue() / 100.0d) * d;
        }
        int i3 = length - 2;
        if (i3 > 0) {
            String substring = trim.substring(i3);
            d3 = 1.0d;
            substring.hashCode();
            char c = 65535;
            switch (substring.hashCode()) {
                case 3178:
                    if (substring.equals("cm")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3240:
                    if (substring.equals("em")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3365:
                    if (substring.equals("in")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3488:
                    if (substring.equals("mm")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3571:
                    if (substring.equals("pc")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3588:
                    if (substring.equals("pt")) {
                        c = 5;
                        break;
                    }
                    break;
                case 3592:
                    if (substring.equals("px")) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    d3 = 35.43307d;
                    length = i3;
                    break;
                case 1:
                    length = i3;
                    break;
                case 2:
                    d3 = 90.0d;
                    length = i3;
                    break;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    d3 = 3.543307d;
                    length = i3;
                    break;
                case 4:
                    d3 = 15.0d;
                    length = i3;
                    break;
                case 5:
                    d3 = 1.25d;
                    length = i3;
                    break;
                case 6:
                    length = i3;
                    break;
            }
            doubleValue = Double.valueOf(trim.substring(0, length)).doubleValue() * d3;
        } else {
            doubleValue = Double.valueOf(trim).doubleValue();
        }
        return doubleValue * d2;
    }

    public static final int getColorCompat(Context context, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i2);
        }
        return context.getResources().getColor(i2);
    }

    public static final ComponentName getComponentName(KClass<?> kClass) {
        return new ComponentName(Global.INSTANCE.getApplication().getPackageName(), InputKt.getJavaClass(kClass).getName());
    }

    public static final String getCurrentProcessName(Application application) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return ActivityThread.currentProcessName();
        } catch (Throwable th) {
            Log.w("ClashForAndroid", Intrinsics.stringPlus("Resolve process name: ", th), null);
            return application.getPackageName();
        }
    }

    public static WritableMap getDBError(String str) {
        return getError(null, "Database Error");
    }

    public static final Drawable getDrawableCompat(Context context, int i2) {
        Object obj = ContextCompat.sLock;
        return ContextCompat.Api21Impl.getDrawable(context, i2);
    }

    public static WritableMap getError(String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DialogModule.KEY_MESSAGE, str2);
        if (str != null) {
            createMap.putString("key", str);
        }
        return createMap;
    }

    public static Rect getFrame(ViewGroup viewGroup, View view) {
        if (view.getParent() == null) {
            return null;
        }
        android.graphics.Rect rect = new android.graphics.Rect();
        view.getDrawingRect(rect);
        try {
            viewGroup.offsetDescendantRectToMyCoords(view, rect);
            return new Rect(rect.left, rect.top, view.getWidth(), view.getHeight());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final Intent getIntent(KClass<?> kClass) {
        return new Intent(Global.INSTANCE.getApplication(), InputKt.getJavaClass(kClass));
    }

    public static WritableMap getInvalidKeyError(String str) {
        return getError(null, "Invalid key");
    }

    public static WritableMap getInvalidValueError(String str) {
        return getError(null, "Invalid Value");
    }

    public static float getLastPointerX(MotionEvent motionEvent, boolean z) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (z) {
            float f = 0.0f;
            int pointerCount = motionEvent.getPointerCount();
            int i2 = 0;
            for (int i3 = 0; i3 < pointerCount; i3++) {
                if (i3 != actionIndex) {
                    f = motionEvent.getX(i3) + rawX + f;
                    i2++;
                }
            }
            return f / i2;
        }
        int pointerCount2 = motionEvent.getPointerCount() - 1;
        if (pointerCount2 == actionIndex) {
            pointerCount2--;
        }
        return motionEvent.getX(pointerCount2) + rawX;
    }

    public static float getLastPointerY(MotionEvent motionEvent, boolean z) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (z) {
            float f = 0.0f;
            int pointerCount = motionEvent.getPointerCount();
            int i2 = 0;
            for (int i3 = 0; i3 < pointerCount; i3++) {
                if (i3 != actionIndex) {
                    f = motionEvent.getY(i3) + rawY + f;
                    i2++;
                }
            }
            return f / i2;
        }
        int pointerCount2 = motionEvent.getPointerCount() - 1;
        if (pointerCount2 == actionIndex) {
            pointerCount2--;
        }
        return motionEvent.getY(pointerCount2) + rawY;
    }

    public static final Locale getPreferredLocale(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static EdgeInsets getSafeAreaInsets(View view) {
        EdgeInsets edgeInsets;
        if (view.getHeight() == 0) {
            return null;
        }
        View rootView = view.getRootView();
        if (Build.VERSION.SDK_INT >= 23) {
            WindowInsets rootWindowInsets = rootView.getRootWindowInsets();
            edgeInsets = rootWindowInsets == null ? null : new EdgeInsets(rootWindowInsets.getSystemWindowInsetTop(), rootWindowInsets.getSystemWindowInsetRight(), Math.min(rootWindowInsets.getSystemWindowInsetBottom(), rootWindowInsets.getStableInsetBottom()), rootWindowInsets.getSystemWindowInsetLeft());
        } else {
            android.graphics.Rect rect = new android.graphics.Rect();
            rootView.getWindowVisibleDisplayFrame(rect);
            edgeInsets = new EdgeInsets(rect.top, rootView.getWidth() - rect.right, rootView.getHeight() - rect.bottom, rect.left);
        }
        if (edgeInsets == null) {
            return null;
        }
        android.graphics.Rect rect2 = new android.graphics.Rect();
        view.getGlobalVisibleRect(rect2);
        edgeInsets.top = Math.max(edgeInsets.top - rect2.top, 0.0f);
        edgeInsets.left = Math.max(edgeInsets.left - rect2.left, 0.0f);
        edgeInsets.bottom = Math.max(Math.min((view.getHeight() + rect2.top) - rootView.getHeight(), 0.0f) + edgeInsets.bottom, 0.0f);
        edgeInsets.right = Math.max(Math.min((view.getWidth() + rect2.left) - rootView.getWidth(), 0.0f) + edgeInsets.right, 0.0f);
        return edgeInsets;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Matrix getTransform(android.graphics.RectF r22, android.graphics.RectF r23, java.lang.String r24, int r25) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.getTransform(android.graphics.RectF, android.graphics.RectF, java.lang.String, int):android.graphics.Matrix");
    }

    public static final UUID getUuid(Intent intent) {
        String schemeSpecificPart;
        Uri data = intent.getData();
        if (data == null || !Intrinsics.areEqual(data.getScheme(), "uuid")) {
            data = null;
        }
        if (data == null || (schemeSpecificPart = data.getSchemeSpecificPart()) == null) {
            return null;
        }
        return UUID.fromString(schemeSpecificPart);
    }

    public static void line(float f, float f2) {
        lineTo(f + mPenX, f2 + mPenY);
    }

    public static void lineTo(float f, float f2) {
        setPenDown();
        mPenX = f;
        mPivotX = f;
        mPenY = f2;
        mPivotY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.lineTo(f * f3, f3 * f2);
        elements.add(new PathElement(4, new Point[]{new Point(f, f2)}));
    }

    public static boolean mergeImpl(SQLiteDatabase sQLiteDatabase, String str, String str2) throws JSONException {
        String str3;
        Cursor query = sQLiteDatabase.query("catalystLocalStorage", new String[]{"value"}, "key=?", new String[]{str}, null, null, null);
        try {
            if (!query.moveToFirst()) {
                query.close();
                str3 = null;
            } else {
                str3 = query.getString(0);
            }
            if (str3 != null) {
                JSONObject jSONObject = new JSONObject(str3);
                deepMergeInto(jSONObject, new JSONObject(str2));
                str2 = jSONObject.toString();
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("value", str2);
            return -1 != sQLiteDatabase.insertWithOnConflict("catalystLocalStorage", null, contentValues, 5);
        } finally {
            query.close();
        }
    }

    public static void moveTo(float f, float f2) {
        mPenX = f;
        mPivotX = f;
        mPenDownX = f;
        mPenY = f2;
        mPivotY = f2;
        mPenDownY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.moveTo(f * f3, f3 * f2);
        elements.add(new PathElement(3, new Point[]{new Point(f, f2)}));
    }

    public static final InetSocketAddress parseInetSocketAddress(String str) {
        URL url = new URL(Intrinsics.stringPlus("https://", str));
        return new InetSocketAddress(InetAddress.getByName(url.getHost()), url.getPort());
    }

    public static boolean parse_flag() {
        skip_spaces();
        char charAt = s.charAt(i);
        if (charAt == '0' || charAt == '1') {
            int i2 = i + 1;
            i = i2;
            if (i2 < l && s.charAt(i2) == ',') {
                i++;
            }
            skip_spaces();
            return charAt == '1';
        }
        throw new Error(String.format("Unexpected flag '%c' (i=%d, s=%s)", Character.valueOf(charAt), Integer.valueOf(i), s));
    }

    public static float parse_list_number() {
        int i2;
        char charAt;
        if (i != l) {
            skip_spaces();
            int i3 = i;
            if (i3 != l) {
                char charAt2 = s.charAt(i3);
                if (charAt2 == '-' || charAt2 == '+') {
                    int i4 = i + 1;
                    i = i4;
                    charAt2 = s.charAt(i4);
                }
                if (charAt2 >= '0' && charAt2 <= '9') {
                    skip_digits();
                    int i5 = i;
                    if (i5 < l) {
                        charAt2 = s.charAt(i5);
                    }
                } else if (charAt2 != '.') {
                    throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt2), Integer.valueOf(i), s));
                }
                if (charAt2 == '.') {
                    i++;
                    skip_digits();
                    int i6 = i;
                    if (i6 < l) {
                        charAt2 = s.charAt(i6);
                    }
                }
                if ((charAt2 == 'e' || charAt2 == 'E') && (i2 = i + 1) < l && (charAt = s.charAt(i2)) != 'm' && charAt != 'x') {
                    int i7 = i + 1;
                    i = i7;
                    char charAt3 = s.charAt(i7);
                    if (charAt3 == '+' || charAt3 == '-') {
                        i++;
                        skip_digits();
                    } else if (charAt3 < '0' || charAt3 > '9') {
                        throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt3), Integer.valueOf(i), s));
                    } else {
                        skip_digits();
                    }
                }
                String substring = s.substring(i3, i);
                float parseFloat = Float.parseFloat(substring);
                if (Float.isInfinite(parseFloat) || Float.isNaN(parseFloat)) {
                    throw new Error(String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", substring, Integer.valueOf(i3), Integer.valueOf(i), s));
                }
                skip_spaces();
                int i8 = i;
                if (i8 < l && s.charAt(i8) == ',') {
                    i++;
                }
                return parseFloat;
            }
            throw new Error(String.format("Unexpected end (s=%s)", s));
        }
        throw new Error(String.format("Unexpected end (s=%s)", s));
    }

    public static int pendingIntentFlags$default(int i2, boolean z, int i3) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        return (Build.VERSION.SDK_INT <= 23 || !z) ? i2 : i2 | 67108864;
    }

    public static void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        float f5 = f * 2.0f;
        float f6 = f2 * 2.0f;
        float f7 = (mPenX + f5) / 3.0f;
        float f8 = (mPenY + f6) / 3.0f;
        cubicTo(f7, f8, (f3 + f5) / 3.0f, (f4 + f6) / 3.0f, f3, f4);
    }

    public static final long scaleTraffic(long j) {
        long j2;
        long j3 = (j >>> 30) & 3;
        long j4 = j & 1073741823;
        if (j3 == 0) {
            return j4;
        }
        if (j3 == 1) {
            j2 = 1024;
        } else {
            if (j3 == 2) {
                j2 = 1024;
            } else if (j3 == 3) {
                j2 = 1024;
                j4 *= j2;
            } else {
                throw new IllegalArgumentException("invalid value type");
            }
            j4 *= j2;
        }
        return j4 * j2;
    }

    public static void setPenDown() {
        if (!mPenDown) {
            mPenDownX = mPenX;
            mPenDownY = mPenY;
            mPenDown = true;
        }
    }

    public static final void setSystemBarsTranslucentCompat(Window window, boolean z) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 30) {
            window.setDecorFitsSystemWindows(!z);
        } else {
            View decorView = window.getDecorView();
            if (z) {
                i2 = window.getDecorView().getSystemUiVisibility() | 1024 | 256 | 512;
            } else {
                i2 = window.getDecorView().getSystemUiVisibility() & (-1793);
            }
            decorView.setSystemUiVisibility(i2);
        }
        if (i3 < 28) {
            return;
        }
        if (z) {
            window.getAttributes().layoutInDisplayCutoutMode = 1;
        } else {
            window.getAttributes().layoutInDisplayCutoutMode = 0;
        }
    }

    public static final Intent setUUID(Intent intent, UUID uuid) {
        intent.setData(Uri.fromParts("uuid", String.valueOf(uuid), null));
        return intent;
    }

    public static void skip_digits() {
        while (true) {
            int i2 = i;
            if (i2 < l && Character.isDigit(s.charAt(i2))) {
                i++;
            } else {
                return;
            }
        }
    }

    public static void skip_spaces() {
        while (true) {
            int i2 = i;
            if (i2 < l && Character.isWhitespace(s.charAt(i2))) {
                i++;
            } else {
                return;
            }
        }
    }

    public static void smoothCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        cubicTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2, f3, f4);
    }

    public static void smoothQuadraticBezierCurveTo(float f, float f2) {
        quadraticBezierCurveTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2);
    }

    public static final void startForegroundServiceCompat(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public static final Channel<Long> ticker(CoroutineScope coroutineScope, long j) {
        Channel<Long> Channel$default = InputKt.Channel$default(0, null, null, 6);
        InputKt.launch$default(coroutineScope, null, null, new TickerKt$ticker$1(Channel$default, j, null), 3, null);
        return Channel$default;
    }

    public static int toMatrixData(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }

    public static final String trafficString(long j) {
        if (j > 107374182400L) {
            long j2 = 1024;
            long j3 = ((j / j2) / j2) / j2;
            StringBuilder sb = new StringBuilder();
            long j4 = 100;
            sb.append(j3 / j4);
            sb.append('.');
            sb.append(j3 % j4);
            sb.append(" GiB");
            return sb.toString();
        } else if (j > 104857600) {
            long j5 = 1024;
            long j6 = (j / j5) / j5;
            StringBuilder sb2 = new StringBuilder();
            long j7 = 100;
            sb2.append(j6 / j7);
            sb2.append('.');
            sb2.append(j6 % j7);
            sb2.append(" MiB");
            return sb2.toString();
        } else if (j > 102400) {
            long j8 = j / 1024;
            StringBuilder sb3 = new StringBuilder();
            long j9 = 100;
            sb3.append(j8 / j9);
            sb3.append('.');
            sb3.append(j8 % j9);
            sb3.append(" KiB");
            return sb3.toString();
        } else {
            return j + " Bytes";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static double fromRelative(SVGLength sVGLength, double d, double d2, double d3, double d4) {
        double d5;
        if (sVGLength == null) {
            return d2;
        }
        int i2 = sVGLength.unit;
        double d6 = sVGLength.value;
        d4 = 1.0d;
        switch (SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i2)) {
            case 1:
            case 5:
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 2:
                d5 = (d6 / 100.0d) * d;
                break;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 4:
                d4 /= 2.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 6:
                d4 = 35.43307d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 7:
                d4 = 3.543307d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 8:
                d4 = 90.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                d4 = 1.25d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 10:
                d4 = 15.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            default:
                d5 = d6 * d3;
                break;
        }
        return d5 + d2;
    }
}
