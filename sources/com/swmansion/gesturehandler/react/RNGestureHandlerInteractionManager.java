package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.gesturehandler.GestureHandler;
/* loaded from: classes.dex */
public class RNGestureHandlerInteractionManager {
    public SparseArray<int[]> mWaitForRelations = new SparseArray<>();
    public SparseArray<int[]> mSimultaneousRelations = new SparseArray<>();

    public void configureInteractions(GestureHandler gestureHandler, ReadableMap readableMap) {
        gestureHandler.mInteractionController = this;
        if (readableMap.hasKey("waitFor")) {
            this.mWaitForRelations.put(gestureHandler.mTag, convertHandlerTagsArray(readableMap, "waitFor"));
        }
        if (readableMap.hasKey("simultaneousHandlers")) {
            this.mSimultaneousRelations.put(gestureHandler.mTag, convertHandlerTagsArray(readableMap, "simultaneousHandlers"));
        }
    }

    public final int[] convertHandlerTagsArray(ReadableMap readableMap, String str) {
        ReadableArray array = readableMap.getArray(str);
        int size = array.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = array.getInt(i);
        }
        return iArr;
    }
}
