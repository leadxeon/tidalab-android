package com.facebook.react.modules.core;

import android.util.JsonWriter;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.JsonWriterHelper;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.util.JSStackTrace;
import java.io.IOException;
import java.io.StringWriter;
import okhttp3.HttpUrl;
@ReactModule(name = ExceptionsManagerModule.NAME)
/* loaded from: classes.dex */
public class ExceptionsManagerModule extends ReactContextBaseJavaModule {
    public static final String NAME = "ExceptionsManager";
    private final DevSupportManager mDevSupportManager;

    public ExceptionsManagerModule(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    @ReactMethod
    public void dismissRedbox() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.hideRedboxDialog();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void reportException(ReadableMap readableMap) {
        String string = readableMap.hasKey(DialogModule.KEY_MESSAGE) ? readableMap.getString(DialogModule.KEY_MESSAGE) : HttpUrl.FRAGMENT_ENCODE_SET;
        ReadableArray array = readableMap.hasKey("stack") ? readableMap.getArray("stack") : Arguments.createArray();
        int i = readableMap.hasKey("id") ? readableMap.getInt("id") : -1;
        boolean z = false;
        boolean z2 = readableMap.hasKey("isFatal") ? readableMap.getBoolean("isFatal") : false;
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            if (readableMap.getMap("extraData") != null && readableMap.getMap("extraData").hasKey("suppressRedBox")) {
                z = readableMap.getMap("extraData").getBoolean("suppressRedBox");
            }
            if (!z) {
                this.mDevSupportManager.showNewJSError(string, array, i);
                return;
            }
            return;
        }
        if (readableMap.getType("extraData") != ReadableType.Null) {
            try {
                StringWriter stringWriter = new StringWriter();
                JsonWriter jsonWriter = new JsonWriter(stringWriter);
                JsonWriterHelper.value(jsonWriter, readableMap.getDynamic("extraData"));
                jsonWriter.close();
                stringWriter.close();
                stringWriter.toString();
            } catch (IOException unused) {
            }
        }
        if (!z2) {
            FLog.e("ReactNative", JSStackTrace.format(string, array));
            return;
        }
        throw new JavascriptException(JSStackTrace.format(string, array));
    }

    @ReactMethod
    public void reportFatalException(String str, ReadableArray readableArray, int i) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(DialogModule.KEY_MESSAGE, str);
        javaOnlyMap.putArray("stack", readableArray);
        javaOnlyMap.putInt("id", i);
        javaOnlyMap.putBoolean("isFatal", true);
        reportException(javaOnlyMap);
    }

    @ReactMethod
    public void reportSoftException(String str, ReadableArray readableArray, int i) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(DialogModule.KEY_MESSAGE, str);
        javaOnlyMap.putArray("stack", readableArray);
        javaOnlyMap.putInt("id", i);
        javaOnlyMap.putBoolean("isFatal", false);
        reportException(javaOnlyMap);
    }

    @ReactMethod
    public void updateExceptionMessage(String str, ReadableArray readableArray, int i) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.updateJSError(str, readableArray, i);
        }
    }
}
