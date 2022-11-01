package com.facebook.react.uimanager.events;

import android.util.SparseArray;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes.dex */
public class ReactEventEmitter implements RCTEventEmitter {
    private static final String TAG = "ReactEventEmitter";
    private final SparseArray<RCTEventEmitter> mEventEmitters = new SparseArray<>();
    private final ReactApplicationContext mReactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
    }

    private RCTEventEmitter getEventEmitter(int i) {
        RCTEventEmitter rCTEventEmitter = this.mEventEmitters.get(R$style.getUIManagerType(i));
        if (rCTEventEmitter != null) {
            return rCTEventEmitter;
        }
        int i2 = FLog.$r8$clinit;
        return (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, WritableMap writableMap) {
        getEventEmitter(i).receiveEvent(i, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        R$dimen.assertCondition(writableArray.size() > 0);
        getEventEmitter(writableArray.getMap(0).getInt("target")).receiveTouches(str, writableArray, writableArray2);
    }

    public void register(int i, RCTEventEmitter rCTEventEmitter) {
        this.mEventEmitters.put(i, rCTEventEmitter);
    }

    public void unregister(int i) {
        this.mEventEmitters.remove(i);
    }
}
