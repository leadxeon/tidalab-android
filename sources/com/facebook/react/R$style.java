package com.facebook.react;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.network.ReactCookieJarContainer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.TouchEventType;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper$ScrollCommandHandler;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper$ScrollToCommandData;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper$ScrollToEndCommandData;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.ReactFontManager;
import com.facebook.soloader.MinElf$ElfError;
import com.facebook.yoga.YogaConfigJNIBase;
import com.facebook.yoga.YogaMeasureMode;
import com.github.kr328.kaidl.KaidlScope;
import com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$1;
import com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$context$1;
import com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$finializer$1;
import com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.Job;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.ByteString;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class R$style {
    public static YogaConfigJNIBase YOGA_CONFIG;
    public static DisplayMetrics sScreenDisplayMetrics;
    public static DisplayMetrics sWindowDisplayMetrics;
    public static Boolean zzgp;
    public static Boolean zzgq;
    public static Boolean zzgs;

    public static Typeface applyStyles(Typeface typeface, int i, int i2, String str, AssetManager assetManager) {
        int i3 = 0;
        int style = typeface == null ? 0 : typeface.getStyle();
        if (i2 == 1 || ((style & 1) != 0 && i2 == -1)) {
            i3 = 1;
        }
        if (i == 2 || ((style & 2) != 0 && i == -1)) {
            i3 |= 2;
        }
        if (str != null) {
            if (ReactFontManager.sReactFontManagerInstance == null) {
                ReactFontManager.sReactFontManagerInstance = new ReactFontManager();
            }
            typeface = ReactFontManager.sReactFontManagerInstance.getTypeface(str, i3, i2, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, i3);
        }
        return typeface != null ? typeface : Typeface.defaultFromStyle(i3);
    }

    public static final void assertExplicitMeasureSpec(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0 || mode2 == 0) {
            throw new IllegalStateException("A catalyst view must have an explicit width and height given to it. This should normally happen as part of the standard catalyst UI framework.");
        }
    }

    public static String buildKeySelection(int i) {
        String[] strArr = new String[i];
        Arrays.fill(strArr, "?");
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("key IN ("), TextUtils.join(", ", strArr), ")");
    }

    public static <K, V> MapBuilder$Builder<K, V> builder() {
        return new MapBuilder$Builder<>(null);
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkHandlerThread(Handler handler) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException("Must be called on the handler thread");
        }
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static OkHttpClient createClient(Context context) {
        return createClientBuilder().cache(new Cache(new File(context.getCacheDir(), "http-cache"), 10485760)).build();
    }

    public static OkHttpClient.Builder createClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient.Builder cookieJar = builder.connectTimeout(0L, timeUnit).readTimeout(0L, timeUnit).writeTimeout(0L, timeUnit).cookieJar(new ReactCookieJarContainer());
        try {
            Security.insertProviderAt((Provider) Class.forName("org.conscrypt.OpenSSLProvider").newInstance(), 1);
        } catch (Exception unused) {
        }
        return cookieJar;
    }

    public static <T extends Parcelable> T createParcelable(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        T createFromParcel = creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + readSize);
        return createFromParcel;
    }

    public static String createString(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + readSize);
        return readString;
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

    public static double determinant(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        double d5 = dArr[4];
        double d6 = dArr[5];
        double d7 = dArr[6];
        double d8 = dArr[7];
        double d9 = dArr[8];
        double d10 = dArr[9];
        double d11 = dArr[10];
        double d12 = dArr[11];
        double d13 = dArr[12];
        double d14 = dArr[13];
        double d15 = dArr[14];
        double d16 = dArr[15];
        double d17 = d4 * d7;
        double d18 = d3 * d8;
        double d19 = d4 * d6;
        double d20 = d2 * d8;
        double d21 = (d20 * d11 * d13) + ((((d17 * d10) * d13) - ((d18 * d10) * d13)) - ((d19 * d11) * d13));
        double d22 = d3 * d6;
        double d23 = (d22 * d12 * d13) + d21;
        double d24 = d2 * d7;
        double d25 = d4 * d5;
        double d26 = d25 * d11 * d14;
        double d27 = d8 * d;
        double d28 = d3 * d5;
        double d29 = ((d26 + (((d18 * d9) * d14) + ((d23 - ((d24 * d12) * d13)) - ((d17 * d9) * d14)))) - ((d27 * d11) * d14)) - ((d28 * d12) * d14);
        double d30 = d7 * d;
        double d31 = d2 * d5;
        double d32 = d * d6;
        return (d32 * d11 * d16) + (((((d28 * d10) * d16) + (((d24 * d9) * d16) + (((((d31 * d12) * d15) + (((d27 * d10) * d15) + (((((d19 * d9) * d15) + (((d30 * d12) * d14) + d29)) - ((d20 * d9) * d15)) - ((d25 * d10) * d15)))) - ((d12 * d32) * d15)) - ((d22 * d9) * d16)))) - ((d30 * d10) * d16)) - ((d31 * d11) * d16));
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    dumbDeleteRecursive(file2);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }

    public static void emitScrollEvent(ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2) {
        View childAt = viewGroup.getChildAt(0);
        if (childAt != null) {
            ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(viewGroup.getId(), scrollEventType, viewGroup.getScrollX(), viewGroup.getScrollY(), f, f2, childAt.getWidth(), childAt.getHeight(), viewGroup.getWidth(), viewGroup.getHeight()));
        }
    }

    public static void ensureAtEnd(Parcel parcel, int i) {
        if (parcel.dataPosition() != i) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(i);
            throw new SafeParcelReader$ParseException(sb.toString(), parcel);
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static String[] extract_DT_NEEDED(FileChannel fileChannel) throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        long j12;
        long j13;
        long j14;
        int i = 8;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fileChannel, allocate, 0L) == 1179403647) {
            read(fileChannel, allocate, 1, 4L);
            boolean z = ((short) (allocate.get() & 255)) == 1;
            read(fileChannel, allocate, 1, 5L);
            if (((short) (allocate.get() & 255)) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            if (z) {
                j = getu32(fileChannel, allocate, 28L);
            } else {
                read(fileChannel, allocate, 8, 32L);
                j = allocate.getLong();
            }
            if (z) {
                read(fileChannel, allocate, 2, 44L);
                j2 = allocate.getShort() & 65535;
            } else {
                read(fileChannel, allocate, 2, 56L);
                j2 = allocate.getShort() & 65535;
            }
            read(fileChannel, allocate, 2, z ? 42L : 54L);
            int i2 = allocate.getShort() & 65535;
            if (j2 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                if (z) {
                    j14 = getu32(fileChannel, allocate, 32L);
                } else {
                    read(fileChannel, allocate, 8, 40L);
                    j14 = allocate.getLong();
                }
                if (z) {
                    j2 = getu32(fileChannel, allocate, j14 + 28);
                } else {
                    j2 = getu32(fileChannel, allocate, j14 + 44);
                }
            }
            long j15 = j;
            long j16 = 0;
            while (true) {
                if (j16 >= j2) {
                    j3 = 0;
                    break;
                }
                if (z) {
                    j13 = getu32(fileChannel, allocate, j15 + 0);
                } else {
                    j13 = getu32(fileChannel, allocate, j15 + 0);
                }
                if (j13 != 2) {
                    j15 += i2;
                    j16++;
                } else if (z) {
                    j3 = getu32(fileChannel, allocate, j15 + 4);
                } else {
                    read(fileChannel, allocate, 8, j15 + 8);
                    j3 = allocate.getLong();
                }
            }
            long j17 = 0;
            if (j3 != 0) {
                long j18 = j3;
                long j19 = 0;
                int i3 = 0;
                while (true) {
                    long j20 = j18 + j17;
                    if (z) {
                        j4 = getu32(fileChannel, allocate, j20);
                    } else {
                        read(fileChannel, allocate, i, j20);
                        j4 = allocate.getLong();
                    }
                    if (j4 == 1) {
                        j5 = j3;
                        if (i3 != Integer.MAX_VALUE) {
                            i3++;
                        } else {
                            throw new MinElf$ElfError("malformed DT_NEEDED section");
                        }
                    } else {
                        j5 = j3;
                        if (j4 == 5) {
                            if (z) {
                                j19 = getu32(fileChannel, allocate, j18 + 4);
                            } else {
                                read(fileChannel, allocate, 8, j18 + 8);
                                j19 = allocate.getLong();
                            }
                        }
                    }
                    long j21 = 16;
                    j18 += z ? 8L : 16L;
                    long j22 = 0;
                    if (j4 != 0) {
                        j17 = 0;
                        j3 = j5;
                        i = 8;
                    } else if (j19 != 0) {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= j2) {
                                j6 = 0;
                                break;
                            }
                            if (z) {
                                j9 = getu32(fileChannel, allocate, j + j22);
                            } else {
                                j9 = getu32(fileChannel, allocate, j + j22);
                            }
                            if (j9 == 1) {
                                if (z) {
                                    j10 = getu32(fileChannel, allocate, j + 8);
                                } else {
                                    read(fileChannel, allocate, 8, j + j21);
                                    j10 = allocate.getLong();
                                }
                                if (z) {
                                    j2 = j2;
                                    j11 = getu32(fileChannel, allocate, j + 20);
                                } else {
                                    j2 = j2;
                                    read(fileChannel, allocate, 8, j + 40);
                                    j11 = allocate.getLong();
                                }
                                if (j10 <= j19 && j19 < j11 + j10) {
                                    if (z) {
                                        j12 = getu32(fileChannel, allocate, j + 4);
                                    } else {
                                        read(fileChannel, allocate, 8, j + 8);
                                        j12 = allocate.getLong();
                                    }
                                    j6 = j12 + (j19 - j10);
                                }
                            } else {
                                j2 = j2;
                            }
                            j += i2;
                            i4++;
                            j21 = 16;
                            j22 = 0;
                        }
                        long j23 = 0;
                        if (j6 != 0) {
                            String[] strArr = new String[i3];
                            int i5 = 0;
                            while (true) {
                                long j24 = j5 + j23;
                                if (z) {
                                    j7 = getu32(fileChannel, allocate, j24);
                                } else {
                                    read(fileChannel, allocate, 8, j24);
                                    j7 = allocate.getLong();
                                }
                                if (j7 == 1) {
                                    if (z) {
                                        j8 = getu32(fileChannel, allocate, j5 + 4);
                                    } else {
                                        read(fileChannel, allocate, 8, j5 + 8);
                                        j8 = allocate.getLong();
                                    }
                                    long j25 = j8 + j6;
                                    StringBuilder sb = new StringBuilder();
                                    while (true) {
                                        j25++;
                                        read(fileChannel, allocate, 1, j25);
                                        short s = (short) (allocate.get() & 255);
                                        if (s == 0) {
                                            break;
                                        }
                                        sb.append((char) s);
                                    }
                                    strArr[i5] = sb.toString();
                                    if (i5 != Integer.MAX_VALUE) {
                                        i5++;
                                    } else {
                                        throw new MinElf$ElfError("malformed DT_NEEDED section");
                                    }
                                }
                                j5 += z ? 8L : 16L;
                                if (j7 != 0) {
                                    j23 = 0;
                                } else if (i5 == i3) {
                                    return strArr;
                                } else {
                                    throw new MinElf$ElfError("malformed DT_NEEDED section");
                                }
                            }
                        } else {
                            throw new MinElf$ElfError("did not find file offset of DT_STRTAB table");
                        }
                    } else {
                        throw new MinElf$ElfError("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new MinElf$ElfError("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new MinElf$ElfError("file is not ELF");
        }
    }

    public static boolean floatsEqual(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) && Float.isNaN(f2) : Math.abs(f2 - f) < 1.0E-5f;
    }

    public static void fsyncRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    fsyncRecursive(file2);
                }
                return;
            }
            throw new IOException("cannot list directory " + file);
        } else if (!file.getPath().endsWith("_lock")) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        randomAccessFile.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public static Map getBubblingEventTypeConstants() {
        MapBuilder$Builder builder = builder();
        builder.put("topChange", of("phasedRegistrationNames", of("bubbled", "onChange", "captured", "onChangeCapture")));
        builder.put("topSelect", of("phasedRegistrationNames", of("bubbled", "onSelect", "captured", "onSelectCapture")));
        builder.put(TouchEventType.getJSEventName(TouchEventType.START), of("phasedRegistrationNames", of("bubbled", "onTouchStart", "captured", "onTouchStartCapture")));
        builder.put(TouchEventType.getJSEventName(TouchEventType.MOVE), of("phasedRegistrationNames", of("bubbled", "onTouchMove", "captured", "onTouchMoveCapture")));
        builder.put(TouchEventType.getJSEventName(TouchEventType.END), of("phasedRegistrationNames", of("bubbled", "onTouchEnd", "captured", "onTouchEndCapture")));
        builder.put(TouchEventType.getJSEventName(TouchEventType.CANCEL), of("phasedRegistrationNames", of("bubbled", "onTouchCancel", "captured", "onTouchCancelCapture")));
        return builder.build();
    }

    public static Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("UIView", of("ContentMode", of("ScaleAspectFit", Integer.valueOf(ImageView.ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ImageView.ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ImageView.ScaleType.CENTER_INSIDE.ordinal()))));
        hashMap.put("StyleConstants", of("PointerEventsValues", of("none", 0, "boxNone", 1, "boxOnly", 2, "unspecified", 3)));
        hashMap.put("PopupMenu", of(DialogModule.ACTION_DISMISSED, DialogModule.ACTION_DISMISSED, "itemSelected", "itemSelected"));
        hashMap.put("AccessibilityEventTypes", of("typeWindowStateChanged", 32, "typeViewFocused", 8, "typeViewClicked", 1));
        return hashMap;
    }

    public static WritableMap getDBError(String str) {
        return getError(null, "Database Error");
    }

    public static Pair<View, Integer> getDeepestLeaf(View view) {
        LinkedList linkedList = new LinkedList();
        Pair<View, Integer> pair = new Pair<>(view, 1);
        linkedList.add(pair);
        while (!linkedList.isEmpty()) {
            Pair<View, Integer> pair2 = (Pair) linkedList.poll();
            if (((Integer) pair2.second).intValue() > ((Integer) pair.second).intValue()) {
                pair = pair2;
            }
            Object obj = pair2.first;
            if (obj instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) obj;
                Integer valueOf = Integer.valueOf(((Integer) pair2.second).intValue() + 1);
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    linkedList.add(new Pair(viewGroup.getChildAt(i), valueOf));
                }
            }
        }
        return pair;
    }

    public static ColorStateList getDefaultTextAttribute(Context context, int i) {
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(new int[]{i});
            ColorStateList colorStateList = typedArray.getColorStateList(0);
            typedArray.recycle();
            return colorStateList;
        } catch (Throwable th) {
            if (typedArray != null) {
                typedArray.recycle();
            }
            throw th;
        }
    }

    public static Map getDirectEventTypeConstants() {
        MapBuilder$Builder builder = builder();
        builder.put("topContentSizeChange", of("registrationName", "onContentSizeChange"));
        builder.put("topLayout", of("registrationName", "onLayout"));
        builder.put("topLoadingError", of("registrationName", "onLoadingError"));
        builder.put("topLoadingFinish", of("registrationName", "onLoadingFinish"));
        builder.put("topLoadingStart", of("registrationName", "onLoadingStart"));
        builder.put("topSelectionChange", of("registrationName", "onSelectionChange"));
        builder.put("topMessage", of("registrationName", "onMessage"));
        builder.put("topClick", of("registrationName", "onClick"));
        builder.put("topScrollBeginDrag", of("registrationName", "onScrollBeginDrag"));
        builder.put("topScrollEndDrag", of("registrationName", "onScrollEndDrag"));
        builder.put("topScroll", of("registrationName", "onScroll"));
        builder.put("topMomentumScrollBegin", of("registrationName", "onMomentumScrollBegin"));
        builder.put("topMomentumScrollEnd", of("registrationName", "onMomentumScrollEnd"));
        return builder.build();
    }

    public static InputStream getDownloadFileInputStream(Context context, Uri uri) throws IOException {
        File createTempFile = File.createTempFile("RequestBodyUtil", "temp", context.getApplicationContext().getCacheDir());
        createTempFile.deleteOnExit();
        InputStream openStream = new URL(uri.toString()).openStream();
        try {
            ReadableByteChannel newChannel = Channels.newChannel(openStream);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            try {
                fileOutputStream.getChannel().transferFrom(newChannel, 0L, Long.MAX_VALUE);
                FileInputStream fileInputStream = new FileInputStream(createTempFile);
                newChannel.close();
                return fileInputStream;
            } finally {
                fileOutputStream.close();
            }
        } finally {
            openStream.close();
        }
    }

    public static RequestBody getEmptyBody(String str) {
        if (str.equals(RNCWebViewManager.HTTP_METHOD_POST) || str.equals("PUT") || str.equals("PATCH")) {
            return RequestBody.create((MediaType) null, ByteString.EMPTY);
        }
        return null;
    }

    public static WritableMap getError(String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DialogModule.KEY_MESSAGE, str2);
        if (str != null) {
            createMap.putString("key", str);
        }
        return createMap;
    }

    public static InputStream getFileInputStream(Context context, String str) {
        try {
            Uri parse = Uri.parse(str);
            if (parse.getScheme().startsWith("http")) {
                return getDownloadFileInputStream(context, parse);
            }
            return context.getContentResolver().openInputStream(parse);
        } catch (Exception e) {
            FLog.e("ReactNative", "Could not retrieve file for contentUri " + str, e);
            return null;
        }
    }

    public static WritableMap getInvalidKeyError(String str) {
        return getError(null, "Invalid key");
    }

    public static WritableMap getInvalidValueError(String str) {
        return getError(null, "Invalid Value");
    }

    public static int getMeasureSpec(float f, YogaMeasureMode yogaMeasureMode) {
        if (yogaMeasureMode == YogaMeasureMode.EXACTLY) {
            return View.MeasureSpec.makeMeasureSpec((int) f, 1073741824);
        }
        if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
            return View.MeasureSpec.makeMeasureSpec((int) f, Integer.MIN_VALUE);
        }
        return View.MeasureSpec.makeMeasureSpec(0, 0);
    }

    public static Map<String, Object> getPhysicalPixelsMap(DisplayMetrics displayMetrics, double d) {
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(displayMetrics.widthPixels));
        hashMap.put("height", Integer.valueOf(displayMetrics.heightPixels));
        hashMap.put("scale", Float.valueOf(displayMetrics.density));
        hashMap.put("fontScale", Double.valueOf(d));
        hashMap.put("densityDpi", Integer.valueOf(displayMetrics.densityDpi));
        return hashMap;
    }

    public static WritableNativeMap getPhysicalPixelsNativeMap(DisplayMetrics displayMetrics, double d) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble("scale", displayMetrics.density);
        writableNativeMap.putDouble("fontScale", d);
        writableNativeMap.putDouble("densityDpi", displayMetrics.densityDpi);
        return writableNativeMap;
    }

    public static RootView getRootView(View view) {
        while (!(view instanceof RootView)) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return null;
            }
            R$dimen.assertCondition(parent instanceof View);
            view = (View) parent;
        }
        return (RootView) view;
    }

    public static UIManager getUIManager(ReactContext reactContext, int i) {
        if (!reactContext.hasActiveCatalystInstance()) {
            ReactSoftException.logSoftException("UIManagerHelper", new RuntimeException("Cannot get UIManager: no active Catalyst instance"));
            return null;
        }
        CatalystInstance catalystInstance = reactContext.getCatalystInstance();
        if (i == 2) {
            return (UIManager) catalystInstance.getJSIModule(JSIModuleType.UIManager);
        }
        return (UIManager) catalystInstance.getNativeModule(UIManagerModule.class);
    }

    public static int getUIManagerType(int i) {
        return i % 2 == 0 ? 2 : 1;
    }

    public static YogaMeasureMode getYogaMeasureMode(float f, float f2) {
        if (f == f2) {
            return YogaMeasureMode.EXACTLY;
        }
        if (Float.isInfinite(f2)) {
            return YogaMeasureMode.UNDEFINED;
        }
        return YogaMeasureMode.AT_MOST;
    }

    public static float getYogaSize(float f, float f2) {
        if (f == f2) {
            return PixelUtil.toPixelFromDIP(f2);
        }
        if (Float.isInfinite(f2)) {
            return Float.POSITIVE_INFINITY;
        }
        return PixelUtil.toPixelFromDIP(f2);
    }

    public static long getu32(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 4, j);
        return byteBuffer.getInt() & 4294967295L;
    }

    public static void initDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        sWindowDisplayMetrics = displayMetrics;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        R$dimen.assertNotNull(windowManager, "WindowManager is null!");
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics2);
        sScreenDisplayMetrics = displayMetrics2;
    }

    public static void initDisplayMetricsIfNotInitialized(Context context) {
        if (sScreenDisplayMetrics == null) {
            initDisplayMetrics(context);
        }
    }

    public static double[] inverse(double[] dArr) {
        double determinant = determinant(dArr);
        if (isZero(determinant)) {
            return dArr;
        }
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        double d5 = dArr[4];
        double d6 = dArr[5];
        double d7 = dArr[6];
        double d8 = dArr[7];
        double d9 = dArr[8];
        double d10 = dArr[9];
        double d11 = dArr[10];
        double d12 = dArr[11];
        double d13 = dArr[12];
        double d14 = dArr[13];
        double d15 = dArr[14];
        double d16 = dArr[15];
        double d17 = d7 * d12;
        double d18 = d8 * d11;
        double d19 = d8 * d10;
        double d20 = d6 * d12;
        double d21 = d7 * d10;
        double d22 = (((d19 * d15) + ((d17 * d14) - (d18 * d14))) - (d20 * d15)) - (d21 * d16);
        double d23 = d6 * d11;
        double d24 = d4 * d11;
        double d25 = d3 * d12;
        double d26 = d4 * d10;
        double d27 = d2 * d12;
        double d28 = d3 * d10;
        double d29 = (d28 * d16) + (d27 * d15) + (((d24 * d14) - (d25 * d14)) - (d26 * d15));
        double d30 = d2 * d11;
        double d31 = d3 * d8;
        double d32 = d4 * d7;
        double d33 = d4 * d6;
        double d34 = d2 * d8;
        double d35 = d3 * d6;
        double d36 = d2 * d7;
        double d37 = (d18 * d13) - (d17 * d13);
        double d38 = d8 * d9;
        double d39 = d5 * d12;
        double d40 = (d39 * d15) + (d37 - (d38 * d15));
        double d41 = d7 * d9;
        double d42 = (d41 * d16) + d40;
        double d43 = d5 * d11;
        double d44 = (d25 * d13) - (d24 * d13);
        double d45 = d4 * d9;
        double d46 = (d45 * d15) + d44;
        double d47 = d * d12;
        double d48 = d3 * d9;
        double d49 = d * d11;
        double d50 = d4 * d5;
        double d51 = d8 * d;
        double d52 = d3 * d5;
        double d53 = d7 * d;
        double d54 = d6 * d9;
        double d55 = (((d38 * d14) + ((d20 * d13) - (d19 * d13))) - (d39 * d14)) - (d54 * d16);
        double d56 = d5 * d10;
        double d57 = d2 * d9;
        double d58 = (d57 * d16) + (d47 * d14) + (((d26 * d13) - (d27 * d13)) - (d45 * d14));
        double d59 = d * d10;
        double d60 = d2 * d5;
        double d61 = d * d6;
        return new double[]{((d23 * d16) + d22) / determinant, (d29 - (d30 * d16)) / determinant, ((d36 * d16) + ((((d33 * d15) + ((d31 * d14) - (d32 * d14))) - (d34 * d15)) - (d35 * d16))) / determinant, (((d35 * d12) + ((d34 * d11) + (((d32 * d10) - (d31 * d10)) - (d33 * d11)))) - (d36 * d12)) / determinant, (d42 - (d43 * d16)) / determinant, ((d49 * d16) + ((d46 - (d47 * d15)) - (d48 * d16))) / determinant, (((d52 * d16) + ((d51 * d15) + (((d32 * d13) - (d31 * d13)) - (d50 * d15)))) - (d53 * d16)) / determinant, ((d53 * d12) + ((((d50 * d11) + ((d31 * d9) - (d32 * d9))) - (d51 * d11)) - (d52 * d12))) / determinant, ((d56 * d16) + d55) / determinant, (d58 - (d59 * d16)) / determinant, ((d16 * d61) + ((((d50 * d14) + ((d34 * d13) - (d33 * d13))) - (d51 * d14)) - (d60 * d16))) / determinant, (((d60 * d12) + ((d51 * d10) + (((d33 * d9) - (d34 * d9)) - (d50 * d10)))) - (d12 * d61)) / determinant, (((d54 * d15) + ((d43 * d14) + (((d21 * d13) - (d23 * d13)) - (d41 * d14)))) - (d56 * d15)) / determinant, ((d59 * d15) + ((((d48 * d14) + ((d30 * d13) - (d28 * d13))) - (d49 * d14)) - (d57 * d15))) / determinant, (((d60 * d15) + ((d14 * d53) + (((d35 * d13) - (d13 * d36)) - (d52 * d14)))) - (d15 * d61)) / determinant, ((d61 * d11) + ((((d52 * d10) + ((d36 * d9) - (d35 * d9))) - (d53 * d10)) - (d60 * d11))) / determinant};
    }

    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isUndefined(float f) {
        return Float.compare(f, Float.NaN) == 0;
    }

    @TargetApi(20)
    public static boolean isWearable(Context context) {
        if (zzgp == null) {
            zzgp = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzgp.booleanValue();
    }

    @TargetApi(26)
    public static boolean isWearableWithoutPlayStore(Context context) {
        if (isWearable(context)) {
            if (Build.VERSION.SDK_INT >= 24) {
                if (zzgq == null) {
                    zzgq = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
                }
                if (!zzgq.booleanValue() || isAtLeastO()) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < 1.0E-5d;
    }

    public static long make(float f, float f2) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        return Float.floatToRawIntBits(f2) | (floatToRawIntBits << 32);
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

    public static int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public static void multiplyVectorByMatrix(double[] dArr, double[] dArr2, double[] dArr3) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        dArr3[0] = (dArr2[12] * d4) + (dArr2[8] * d3) + (dArr2[4] * d2) + (dArr2[0] * d);
        dArr3[1] = (dArr2[13] * d4) + (dArr2[9] * d3) + (dArr2[5] * d2) + (dArr2[1] * d);
        dArr3[2] = (dArr2[14] * d4) + (dArr2[10] * d3) + (dArr2[6] * d2) + (dArr2[2] * d);
        double d5 = d * dArr2[3];
        double d6 = d3 * dArr2[11];
        dArr3[3] = (d4 * dArr2[15]) + d6 + (d2 * dArr2[7]) + d5;
    }

    public static void notifyNativeGestureStarted(View view, MotionEvent motionEvent) {
        getRootView(view).onChildStartedNativeGesture(motionEvent);
    }

    public static <K, V> Map<K, V> of(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    public static void onRequestError(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str, Throwable th) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushString(str);
        if (th != null && th.getClass() == SocketTimeoutException.class) {
            createArray.pushBoolean(true);
        }
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
        }
    }

    public static void onRequestSuccess(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushNull();
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
        }
    }

    public static int parseFontStyle(String str) {
        if ("italic".equals(str)) {
            return 2;
        }
        return "normal".equals(str) ? 0 : -1;
    }

    public static String parseFontVariant(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                char c = 65535;
                switch (string.hashCode()) {
                    case -1195362251:
                        if (string.equals("proportional-nums")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1061392823:
                        if (string.equals("lining-nums")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -771984547:
                        if (string.equals("tabular-nums")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -659678800:
                        if (string.equals("oldstyle-nums")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1183323111:
                        if (string.equals("small-caps")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        arrayList.add("'pnum'");
                        continue;
                    case 1:
                        arrayList.add("'lnum'");
                        continue;
                    case 2:
                        arrayList.add("'tnum'");
                        continue;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        arrayList.add("'onum'");
                        continue;
                    case 4:
                        arrayList.add("'smcp'");
                        continue;
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static int parseFontWeight(String str) {
        int charAt = (str == null || str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') ? -1 : (str.charAt(0) - '0') * 100;
        if (charAt == -1) {
            charAt = 0;
        }
        if (charAt == 700 || "bold".equals(str)) {
            return 1;
        }
        if (charAt == 400 || "normal".equals(str)) {
            return 0;
        }
        return charAt;
    }

    public static int parseOverScrollMode(String str) {
        if (str == null || str.equals("auto")) {
            return 1;
        }
        if (str.equals("always")) {
            return 0;
        }
        if (str.equals("never")) {
            return 2;
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("wrong overScrollMode: ", str));
    }

    public static void read(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new MinElf$ElfError("ELF file truncated");
    }

    public static int readInt(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return parcel.readInt();
    }

    public static int readSize(Parcel parcel, int i) {
        return (i & (-65536)) != -65536 ? (i >> 16) & Settings.DEFAULT_INITIAL_WINDOW_SIZE : parcel.readInt();
    }

    public static <T> void receiveCommand(ReactScrollViewCommandHelper$ScrollCommandHandler<T> reactScrollViewCommandHelper$ScrollCommandHandler, T t, int i, ReadableArray readableArray) {
        R$dimen.assertNotNull(reactScrollViewCommandHelper$ScrollCommandHandler);
        R$dimen.assertNotNull(t);
        R$dimen.assertNotNull(readableArray);
        if (i == 1) {
            scrollTo(reactScrollViewCommandHelper$ScrollCommandHandler, t, readableArray);
        } else if (i == 2) {
            reactScrollViewCommandHelper$ScrollCommandHandler.scrollToEnd(t, new ReactScrollViewCommandHelper$ScrollToEndCommandData(readableArray.getBoolean(0)));
        } else if (i == 3) {
            reactScrollViewCommandHelper$ScrollCommandHandler.flashScrollIndicators(t);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), reactScrollViewCommandHelper$ScrollCommandHandler.getClass().getSimpleName()));
        }
    }

    public static void resetIdentityMatrix(double[] dArr) {
        dArr[14] = 0.0d;
        dArr[13] = 0.0d;
        dArr[12] = 0.0d;
        dArr[11] = 0.0d;
        dArr[9] = 0.0d;
        dArr[8] = 0.0d;
        dArr[7] = 0.0d;
        dArr[6] = 0.0d;
        dArr[4] = 0.0d;
        dArr[3] = 0.0d;
        dArr[2] = 0.0d;
        dArr[1] = 0.0d;
        dArr[15] = 1.0d;
        dArr[10] = 1.0d;
        dArr[5] = 1.0d;
        dArr[0] = 1.0d;
    }

    public static double roundTo3Places(double d) {
        return Math.round(d * 1000.0d) * 0.001d;
    }

    public static <T> void scrollTo(ReactScrollViewCommandHelper$ScrollCommandHandler<T> reactScrollViewCommandHelper$ScrollCommandHandler, T t, ReadableArray readableArray) {
        reactScrollViewCommandHelper$ScrollCommandHandler.scrollTo(t, new ReactScrollViewCommandHelper$ScrollToCommandData(Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), readableArray.getBoolean(2)));
    }

    public static void skipUnknownField(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + readSize(parcel, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v3, types: [com.github.kr328.kaidl.SuspendTransactionKt$suspendTransact$2$1, T] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.github.kr328.kaidl.SuspendTransactionKt$suspendTransact$finalizer$1, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object suspendTransact(android.os.IBinder r9, int r10, android.os.Parcel r11, android.os.Parcel r12, kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.R$style.suspendTransact(android.os.IBinder, int, android.os.Parcel, android.os.Parcel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r10v2, types: [T, com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$1] */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$finializer$1] */
    public static final void suspendTransaction(Parcel parcel, Parcel parcel2, Function2<? super Parcel, ? super Continuation<? super Unit>, ? extends Object> function2) {
        IBinder readStrongBinder = parcel.readStrongBinder();
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = SuspendTransactionKt$suspendTransaction$finializer$1.INSTANCE;
        final Job launch$default = InputKt.launch$default(KaidlScope.INSTANCE, null, null, new SuspendTransactionKt$suspendTransaction$job$1(function2, readStrongBinder, ref$ObjectRef, null), 3, null);
        SuspendTransactionKt$suspendTransaction$context$1 suspendTransactionKt$suspendTransaction$context$1 = new SuspendTransactionKt$suspendTransaction$context$1(launch$default);
        IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.github.kr328.kaidl.-$$Lambda$SuspendTransactionKt$Nsqit_c55se93MnGUuUxFXzm9Zg
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                InputKt.cancel$default(Job.this, null, 1, null);
            }
        };
        ref$ObjectRef.element = new SuspendTransactionKt$suspendTransaction$1(readStrongBinder, deathRecipient);
        readStrongBinder.linkToDeath(deathRecipient, 0);
        parcel2.writeNoException();
        parcel2.writeStrongBinder(suspendTransactionKt$suspendTransaction$context$1);
    }

    public static float[] toFloatArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        float[] fArr = new float[readableArray.size()];
        toFloatArray(readableArray, fArr);
        return fArr;
    }

    public static double[] transpose(double[] dArr) {
        return new double[]{dArr[0], dArr[4], dArr[8], dArr[12], dArr[1], dArr[5], dArr[9], dArr[13], dArr[2], dArr[6], dArr[10], dArr[14], dArr[3], dArr[7], dArr[11], dArr[15]};
    }

    public static double[] v3Combine(double[] dArr, double[] dArr2, double d, double d2) {
        return new double[]{(dArr2[0] * d2) + (dArr[0] * d), (dArr2[1] * d2) + (dArr[1] * d), (d2 * dArr2[2]) + (d * dArr[2])};
    }

    public static double[] v3Cross(double[] dArr, double[] dArr2) {
        return new double[]{(dArr[1] * dArr2[2]) - (dArr[2] * dArr2[1]), (dArr[2] * dArr2[0]) - (dArr[0] * dArr2[2]), (dArr[0] * dArr2[1]) - (dArr[1] * dArr2[0])};
    }

    public static double v3Dot(double[] dArr, double[] dArr2) {
        return (dArr[2] * dArr2[2]) + (dArr[1] * dArr2[1]) + (dArr[0] * dArr2[0]);
    }

    public static double v3Length(double[] dArr) {
        return Math.sqrt((dArr[2] * dArr[2]) + (dArr[1] * dArr[1]) + (dArr[0] * dArr[0]));
    }

    public static double[] v3Normalize(double[] dArr, double d) {
        if (isZero(d)) {
            d = v3Length(dArr);
        }
        double d2 = 1.0d / d;
        return new double[]{dArr[0] * d2, dArr[1] * d2, dArr[2] * d2};
    }

    public static int validateObjectHeader(Parcel parcel) {
        int readInt = parcel.readInt();
        int readSize = readSize(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if ((65535 & readInt) != 20293) {
            String valueOf = String.valueOf(Integer.toHexString(readInt));
            throw new SafeParcelReader$ParseException(valueOf.length() != 0 ? "Expected object header. Got 0x".concat(valueOf) : new String("Expected object header. Got 0x"), parcel);
        }
        int i = readSize + dataPosition;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("Size read is invalid start=");
        sb.append(dataPosition);
        sb.append(" end=");
        sb.append(i);
        throw new SafeParcelReader$ParseException(sb.toString(), parcel);
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int zza = zza(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int zza = zza(parcel, i);
            parcel.writeString(str);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static int zza(Parcel parcel, int i) {
        parcel.writeInt(i | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void zzb(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | (-65536));
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }

    public static long make(int i, int i2) {
        return make(i, i2);
    }

    public static <K, V> Map<K, V> of(K k, V v, K k2, V v2) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        return hashMap;
    }

    public static int toFloatArray(ReadableArray readableArray, float[] fArr) {
        int length = readableArray.size() > fArr.length ? fArr.length : readableArray.size();
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) readableArray.getDouble(i);
        }
        return readableArray.size();
    }

    public static void zza(Parcel parcel, int i, int i2) {
        int readSize = readSize(parcel, i);
        if (readSize != i2) {
            String hexString = Integer.toHexString(readSize);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(readSize);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new SafeParcelReader$ParseException(sb.toString(), parcel);
        }
    }

    public static void zzb(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static <K, V> Map<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        hashMap.put(k3, v3);
        return hashMap;
    }

    public static <K, V> Map<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        hashMap.put(k3, v3);
        hashMap.put(k4, v4);
        return hashMap;
    }

    public static <T> void receiveCommand(ReactScrollViewCommandHelper$ScrollCommandHandler<T> reactScrollViewCommandHelper$ScrollCommandHandler, T t, String str, ReadableArray readableArray) {
        R$dimen.assertNotNull(reactScrollViewCommandHelper$ScrollCommandHandler);
        R$dimen.assertNotNull(t);
        R$dimen.assertNotNull(readableArray);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -402165208:
                if (str.equals("scrollTo")) {
                    c = 0;
                    break;
                }
                break;
            case 28425985:
                if (str.equals("flashScrollIndicators")) {
                    c = 1;
                    break;
                }
                break;
            case 2055114131:
                if (str.equals("scrollToEnd")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                scrollTo(reactScrollViewCommandHelper$ScrollCommandHandler, t, readableArray);
                return;
            case 1:
                reactScrollViewCommandHelper$ScrollCommandHandler.flashScrollIndicators(t);
                return;
            case 2:
                reactScrollViewCommandHelper$ScrollCommandHandler.scrollToEnd(t, new ReactScrollViewCommandHelper$ScrollToEndCommandData(readableArray.getBoolean(0)));
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %s received by %s.", str, reactScrollViewCommandHelper$ScrollCommandHandler.getClass().getSimpleName()));
        }
    }
}
