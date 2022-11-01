package com.facebook.react.turbomodule.core.interfaces;

import java.util.Collection;
/* loaded from: classes.dex */
public interface TurboModuleRegistry {
    TurboModule getModule(String str);

    Collection<TurboModule> getModules();

    boolean hasModule(String str);
}
