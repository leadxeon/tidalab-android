package com.facebook.react.uimanager;

import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.ViewManagersPropertyCache;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class ViewManagerPropertyUpdater {
    public static final Map<Class<?>, ViewManagerSetter<?, ?>> VIEW_MANAGER_SETTER_MAP = new HashMap();
    public static final Map<Class<?>, ShadowNodeSetter<?>> SHADOW_NODE_SETTER_MAP = new HashMap();

    /* loaded from: classes.dex */
    public static class FallbackShadowNodeSetter<T extends ReactShadowNode> implements ShadowNodeSetter<T> {
        public final Map<String, ViewManagersPropertyCache.PropSetter> mPropSetters;

        public FallbackShadowNodeSetter(Class cls, AnonymousClass1 r2) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForShadowNodeClass(cls);
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.Settable
        public void getProperties(Map<String, String> map) {
            for (ViewManagersPropertyCache.PropSetter propSetter : this.mPropSetters.values()) {
                map.put(propSetter.mPropName, propSetter.mPropType);
            }
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.ShadowNodeSetter
        public void setProperty(ReactShadowNode reactShadowNode, String str, Object obj) {
            ViewManagersPropertyCache.PropSetter propSetter = this.mPropSetters.get(str);
            if (propSetter != null) {
                try {
                    Integer num = propSetter.mIndex;
                    if (num == null) {
                        Object[] objArr = ViewManagersPropertyCache.PropSetter.SHADOW_ARGS;
                        objArr[0] = propSetter.getValueOrDefault(obj);
                        propSetter.mSetter.invoke(reactShadowNode, objArr);
                        Arrays.fill(objArr, (Object) null);
                    } else {
                        Object[] objArr2 = ViewManagersPropertyCache.PropSetter.SHADOW_GROUP_ARGS;
                        objArr2[0] = num;
                        objArr2[1] = propSetter.getValueOrDefault(obj);
                        propSetter.mSetter.invoke(reactShadowNode, objArr2);
                        Arrays.fill(objArr2, (Object) null);
                    }
                } catch (Throwable th) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error while updating prop ");
                    outline13.append(propSetter.mPropName);
                    FLog.e(ViewManager.class, outline13.toString(), th);
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Error while updating property '");
                    outline132.append(propSetter.mPropName);
                    outline132.append("' in shadow node of type: ");
                    outline132.append(reactShadowNode.getViewClass());
                    throw new JSApplicationIllegalArgumentException(outline132.toString(), th);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class FallbackViewManagerSetter<T extends ViewManager, V extends View> implements ViewManagerSetter<T, V> {
        public final Map<String, ViewManagersPropertyCache.PropSetter> mPropSetters;

        public FallbackViewManagerSetter(Class cls, AnonymousClass1 r2) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForViewManagerClass(cls);
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.Settable
        public void getProperties(Map<String, String> map) {
            for (ViewManagersPropertyCache.PropSetter propSetter : this.mPropSetters.values()) {
                map.put(propSetter.mPropName, propSetter.mPropType);
            }
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.ViewManagerSetter
        public void setProperty(T t, V v, String str, Object obj) {
            ViewManagersPropertyCache.PropSetter propSetter = this.mPropSetters.get(str);
            if (propSetter != null) {
                try {
                    Integer num = propSetter.mIndex;
                    if (num == null) {
                        Object[] objArr = ViewManagersPropertyCache.PropSetter.VIEW_MGR_ARGS;
                        objArr[0] = v;
                        objArr[1] = propSetter.getValueOrDefault(obj);
                        propSetter.mSetter.invoke(t, objArr);
                        Arrays.fill(objArr, (Object) null);
                    } else {
                        Object[] objArr2 = ViewManagersPropertyCache.PropSetter.VIEW_MGR_GROUP_ARGS;
                        objArr2[0] = v;
                        objArr2[1] = num;
                        objArr2[2] = propSetter.getValueOrDefault(obj);
                        propSetter.mSetter.invoke(t, objArr2);
                        Arrays.fill(objArr2, (Object) null);
                    }
                } catch (Throwable th) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error while updating prop ");
                    outline13.append(propSetter.mPropName);
                    FLog.e(ViewManager.class, outline13.toString(), th);
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Error while updating property '");
                    outline132.append(propSetter.mPropName);
                    outline132.append("' of a view managed by: ");
                    outline132.append(t.getName());
                    throw new JSApplicationIllegalArgumentException(outline132.toString(), th);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface Settable {
        void getProperties(Map<String, String> map);
    }

    /* loaded from: classes.dex */
    public interface ShadowNodeSetter<T extends ReactShadowNode> extends Settable {
        void setProperty(T t, String str, Object obj);
    }

    /* loaded from: classes.dex */
    public interface ViewManagerSetter<T extends ViewManager, V extends View> extends Settable {
        void setProperty(T t, V v, String str, Object obj);
    }

    public static <T> T findGeneratedSetter(Class<?> cls) {
        Throwable e;
        String name = cls.getName();
        try {
            return (T) Class.forName(name + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException unused) {
            FLog.w("ViewManagerPropertyUpdater", "Could not find generated setter for " + cls);
            return null;
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(GeneratedOutlineSupport.outline8("Unable to instantiate methods getter for ", name), e);
        } catch (InstantiationException e3) {
            e = e3;
            throw new RuntimeException(GeneratedOutlineSupport.outline8("Unable to instantiate methods getter for ", name), e);
        }
    }

    public static <T extends ViewManager, V extends View> ViewManagerSetter<T, V> findManagerSetter(Class<? extends ViewManager> cls) {
        Map<Class<?>, ViewManagerSetter<?, ?>> map = VIEW_MANAGER_SETTER_MAP;
        ViewManagerSetter<?, ?> viewManagerSetter = map.get(cls);
        if (viewManagerSetter == null) {
            viewManagerSetter = (ViewManagerSetter) findGeneratedSetter(cls);
            if (viewManagerSetter == null) {
                viewManagerSetter = new FallbackViewManagerSetter<>(cls, null);
            }
            map.put(cls, viewManagerSetter);
        }
        return viewManagerSetter;
    }

    public static <T extends ReactShadowNode> ShadowNodeSetter<T> findNodeSetter(Class<? extends ReactShadowNode> cls) {
        Map<Class<?>, ShadowNodeSetter<?>> map = SHADOW_NODE_SETTER_MAP;
        ShadowNodeSetter<?> shadowNodeSetter = map.get(cls);
        if (shadowNodeSetter == null) {
            shadowNodeSetter = (ShadowNodeSetter) findGeneratedSetter(cls);
            if (shadowNodeSetter == null) {
                shadowNodeSetter = new FallbackShadowNodeSetter<>(cls, null);
            }
            map.put(cls, shadowNodeSetter);
        }
        return shadowNodeSetter;
    }
}
