package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerRegistry;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class RNGestureHandlerRegistry implements GestureHandlerRegistry {
    public final SparseArray<GestureHandler> mHandlers = new SparseArray<>();
    public final SparseArray<Integer> mAttachedTo = new SparseArray<>();
    public final SparseArray<ArrayList<GestureHandler>> mHandlersForView = new SparseArray<>();

    public synchronized boolean attachHandlerToView(int i, int i2) {
        GestureHandler gestureHandler = this.mHandlers.get(i);
        if (gestureHandler == null) {
            return false;
        }
        detachHandler(gestureHandler);
        registerHandlerForViewWithTag(i2, gestureHandler);
        return true;
    }

    public final synchronized void detachHandler(final GestureHandler gestureHandler) {
        Integer num = this.mAttachedTo.get(gestureHandler.mTag);
        if (num != null) {
            this.mAttachedTo.remove(gestureHandler.mTag);
            ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(num.intValue());
            if (arrayList != null) {
                arrayList.remove(gestureHandler);
                if (arrayList.size() == 0) {
                    this.mHandlersForView.remove(num.intValue());
                }
            }
        }
        if (gestureHandler.mView != null) {
            UiThreadUtil.runOnUiThread(new Runnable(this) { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerRegistry.1
                @Override // java.lang.Runnable
                public void run() {
                    gestureHandler.cancel();
                }
            });
        }
    }

    public synchronized void dropHandler(int i) {
        GestureHandler gestureHandler = this.mHandlers.get(i);
        if (gestureHandler != null) {
            detachHandler(gestureHandler);
            this.mHandlers.remove(i);
        }
    }

    public final synchronized void registerHandlerForViewWithTag(int i, GestureHandler gestureHandler) {
        if (this.mAttachedTo.get(gestureHandler.mTag) == null) {
            this.mAttachedTo.put(gestureHandler.mTag, Integer.valueOf(i));
            ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(i);
            if (arrayList == null) {
                ArrayList<GestureHandler> arrayList2 = new ArrayList<>(1);
                arrayList2.add(gestureHandler);
                this.mHandlersForView.put(i, arrayList2);
            } else {
                arrayList.add(gestureHandler);
            }
        } else {
            throw new IllegalStateException("Handler " + gestureHandler + " already attached");
        }
    }
}
