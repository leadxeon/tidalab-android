package com.facebook.react.bridge;

import android.os.Trace;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeModule;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
@DoNotStrip
/* loaded from: classes.dex */
public class JavaModuleWrapper {
    private final JSInstance mJSInstance;
    private final ModuleHolder mModuleHolder;
    private final ArrayList<NativeModule.NativeMethod> mMethods = new ArrayList<>();
    private final ArrayList<MethodDescriptor> mDescs = new ArrayList<>();

    @DoNotStrip
    /* loaded from: classes.dex */
    public class MethodDescriptor {
        @DoNotStrip
        public Method method;
        @DoNotStrip
        public String name;
        @DoNotStrip
        public String signature;
        @DoNotStrip
        public String type;

        public MethodDescriptor() {
        }
    }

    public JavaModuleWrapper(JSInstance jSInstance, ModuleHolder moduleHolder) {
        this.mJSInstance = jSInstance;
        this.mModuleHolder = moduleHolder;
    }

    @DoNotStrip
    private void findMethods() {
        Method[] declaredMethods;
        Trace.beginSection("findMethods");
        HashSet hashSet = new HashSet();
        Class<?> cls = this.mModuleHolder.getModule().getClass();
        Class<? super Object> superclass = cls.getSuperclass();
        if (ReactModuleWithSpec.class.isAssignableFrom(superclass)) {
            cls = superclass;
        }
        for (Method method : cls.getDeclaredMethods()) {
            ReactMethod reactMethod = (ReactMethod) method.getAnnotation(ReactMethod.class);
            if (reactMethod != null) {
                String name = method.getName();
                if (!hashSet.contains(name)) {
                    MethodDescriptor methodDescriptor = new MethodDescriptor();
                    JavaMethodWrapper javaMethodWrapper = new JavaMethodWrapper(this, method, reactMethod.isBlockingSynchronousMethod());
                    methodDescriptor.name = name;
                    String type = javaMethodWrapper.getType();
                    methodDescriptor.type = type;
                    if (type == BaseJavaModule.METHOD_TYPE_SYNC) {
                        methodDescriptor.signature = javaMethodWrapper.getSignature();
                        methodDescriptor.method = method;
                    }
                    this.mMethods.add(javaMethodWrapper);
                    this.mDescs.add(methodDescriptor);
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Java Module ");
                    outline13.append(getName());
                    outline13.append(" method name already registered: ");
                    outline13.append(name);
                    throw new IllegalArgumentException(outline13.toString());
                }
            }
        }
        Trace.endSection();
    }

    @DoNotStrip
    public NativeMap getConstants() {
        if (!this.mModuleHolder.getHasConstants()) {
            return null;
        }
        String name = getName();
        ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_START, name);
        BaseJavaModule module = getModule();
        Trace.beginSection("module.getConstants");
        Map<String, Object> constants = module.getConstants();
        Trace.endSection();
        Trace.beginSection("create WritableNativeMap");
        ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_START, name);
        try {
            return Arguments.makeNativeMap(constants);
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_END, name);
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_END, name);
        }
    }

    @DoNotStrip
    public List<MethodDescriptor> getMethodDescriptors() {
        if (this.mDescs.isEmpty()) {
            findMethods();
        }
        return this.mDescs;
    }

    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getName();
    }

    @DoNotStrip
    public void invoke(int i, ReadableNativeArray readableNativeArray) {
        ArrayList<NativeModule.NativeMethod> arrayList = this.mMethods;
        if (arrayList != null && i < arrayList.size()) {
            this.mMethods.get(i).invoke(this.mJSInstance, readableNativeArray);
        }
    }
}
