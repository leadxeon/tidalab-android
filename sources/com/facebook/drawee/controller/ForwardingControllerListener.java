package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ForwardingControllerListener<INFO> implements ControllerListener<INFO> {
    public final List<ControllerListener<? super INFO>> mListeners = new ArrayList(2);

    public synchronized void addListener(ControllerListener<? super INFO> controllerListener) {
        this.mListeners.add(controllerListener);
    }

    public final synchronized void onException(String str, Throwable th) {
        Log.e("FdingControllerListener", str, th);
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFailure(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFailure(str, th);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFailure", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFinalImageSet(String str, INFO info, Animatable animatable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFinalImageSet(str, info, animatable);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageFailed(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageFailed(str, th);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String str, INFO info) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageSet(str, info);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onRelease(String str) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onRelease(str);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onRelease", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onSubmit(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onSubmit(str, obj);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onSubmit", e);
            }
        }
    }
}
