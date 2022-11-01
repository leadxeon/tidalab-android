package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@DoNotStrip
@Deprecated
/* loaded from: classes.dex */
public class ReactSoftException {
    private static final List<ReactSoftExceptionListener> sListeners = new CopyOnWriteArrayList();

    /* loaded from: classes.dex */
    public interface ReactSoftExceptionListener {
        void logSoftException(String str, Throwable th);
    }

    @DoNotStrip
    public static void addListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (!list.contains(reactSoftExceptionListener)) {
            list.add(reactSoftExceptionListener);
        }
    }

    @DoNotStrip
    public static void clearListeners() {
        sListeners.clear();
    }

    @DoNotStrip
    public static void logSoftException(String str, Throwable th) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (list.size() > 0) {
            for (ReactSoftExceptionListener reactSoftExceptionListener : list) {
                reactSoftExceptionListener.logSoftException(str, th);
            }
            return;
        }
        FLog.e(str, "Unhandled SoftException", th);
    }

    @DoNotStrip
    public static void removeListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        sListeners.remove(reactSoftExceptionListener);
    }
}
