package com.facebook.imagepipeline.systrace;

import java.util.Objects;
/* loaded from: classes.dex */
public class FrescoSystrace {
    public static volatile Systrace sInstance;

    /* loaded from: classes.dex */
    public interface Systrace {
    }

    public static Systrace getInstance() {
        if (sInstance == null) {
            synchronized (FrescoSystrace.class) {
                if (sInstance == null) {
                    sInstance = new DefaultFrescoSystrace();
                }
            }
        }
        return sInstance;
    }

    public static boolean isTracing() {
        Objects.requireNonNull(getInstance());
        return false;
    }
}
