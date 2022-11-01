package com.facebook.react.uimanager;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.CoreModulesPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class ViewManagerRegistry {
    public final UIManagerModule.ViewManagerResolver mViewManagerResolver;
    public final Map<String, ViewManager> mViewManagers;

    public ViewManagerRegistry(UIManagerModule.ViewManagerResolver viewManagerResolver) {
        this.mViewManagers = new HashMap();
        this.mViewManagerResolver = viewManagerResolver;
    }

    public ViewManager get(String str) {
        ViewManager viewManager = this.mViewManagers.get(str);
        if (viewManager != null) {
            return viewManager;
        }
        if (this.mViewManagerResolver != null) {
            ViewManager viewManagerFromResolver = getViewManagerFromResolver(str);
            if (viewManagerFromResolver != null) {
                return viewManagerFromResolver;
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport.outline8("ViewManagerResolver returned null for ", str));
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport.outline8("No ViewManager found for class ", str));
    }

    public final ViewManager getViewManagerFromResolver(String str) {
        ViewManager viewManager;
        ViewManager createViewManager;
        ReactInstanceManager reactInstanceManager = CoreModulesPackage.this.mReactInstanceManager;
        synchronized (reactInstanceManager.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) reactInstanceManager.getCurrentReactContext();
            viewManager = null;
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (reactInstanceManager.mPackages) {
                    Iterator<ReactPackage> it = reactInstanceManager.mPackages.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ReactPackage next = it.next();
                        if ((next instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) next).createViewManager(reactApplicationContext, str)) != null) {
                            viewManager = createViewManager;
                            break;
                        }
                    }
                }
            }
        }
        if (viewManager != null) {
            this.mViewManagers.put(str, viewManager);
        }
        return viewManager;
    }

    public ViewManagerRegistry(List<ViewManager> list) {
        HashMap hashMap = new HashMap();
        for (ViewManager viewManager : list) {
            hashMap.put(viewManager.getName(), viewManager);
        }
        this.mViewManagers = hashMap;
        this.mViewManagerResolver = null;
    }
}
