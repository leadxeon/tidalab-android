package kotlin.coroutines.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ModuleNameRetriever;
/* compiled from: ContinuationImpl.kt */
/* loaded from: classes.dex */
public abstract class BaseContinuationImpl implements Continuation<Object>, CoroutineStackFrame, Serializable {
    public final Continuation<Object> completion;

    public BaseContinuationImpl(Continuation<Object> continuation) {
        this.completion = continuation;
    }

    public Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<Object> continuation = this.completion;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    public StackTraceElement getStackTraceElement() {
        int i;
        String str;
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        DebugMetadata debugMetadata = (DebugMetadata) getClass().getAnnotation(DebugMetadata.class);
        String str2 = null;
        if (debugMetadata == null) {
            return null;
        }
        int v = debugMetadata.v();
        if (v <= 1) {
            int i2 = -1;
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                if (!(obj instanceof Integer)) {
                    obj = null;
                }
                Integer num = (Integer) obj;
                i = (num != null ? num.intValue() : 0) - 1;
            } catch (Exception unused) {
                i = -1;
            }
            if (i >= 0) {
                i2 = debugMetadata.l()[i];
            }
            ModuleNameRetriever.Cache cache = ModuleNameRetriever.cache;
            if (cache == null) {
                try {
                    ModuleNameRetriever.Cache cache2 = new ModuleNameRetriever.Cache(Class.class.getDeclaredMethod("getModule", new Class[0]), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
                    ModuleNameRetriever.cache = cache2;
                    cache = cache2;
                } catch (Exception unused2) {
                    cache = ModuleNameRetriever.notOnJava9;
                    ModuleNameRetriever.cache = cache;
                }
            }
            if (!(cache == ModuleNameRetriever.notOnJava9 || (method = cache.getModuleMethod) == null || (invoke = method.invoke(getClass(), new Object[0])) == null || (method2 = cache.getDescriptorMethod) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null)) {
                Method method3 = cache.nameMethod;
                Object invoke3 = method3 != null ? method3.invoke(invoke2, new Object[0]) : null;
                if (invoke3 instanceof String) {
                    str2 = invoke3;
                }
                str2 = str2;
            }
            if (str2 == null) {
                str = debugMetadata.c();
            } else {
                StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, '/');
                outline14.append(debugMetadata.c());
                str = outline14.toString();
            }
            return new StackTraceElement(str, debugMetadata.m(), debugMetadata.f(), i2);
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + v + ". Please update the Kotlin standard library.").toString());
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        BaseContinuationImpl baseContinuationImpl = this;
        while (true) {
            Continuation<Object> continuation = baseContinuationImpl.completion;
            try {
                obj = baseContinuationImpl.invokeSuspend(obj);
                if (obj == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return;
                }
            } catch (Throwable th) {
                obj = new Result.Failure(th);
            }
            baseContinuationImpl.releaseIntercepted();
            if (continuation instanceof BaseContinuationImpl) {
                baseContinuationImpl = (BaseContinuationImpl) continuation;
            } else {
                continuation.resumeWith(obj);
                return;
            }
        }
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        outline13.append(stackTraceElement);
        return outline13.toString();
    }
}
