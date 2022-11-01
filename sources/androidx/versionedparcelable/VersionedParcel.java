package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.collection.ArrayMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class VersionedParcel {
    public final ArrayMap<String, Class> mParcelizerCache;
    public final ArrayMap<String, Method> mReadCache;
    public final ArrayMap<String, Method> mWriteCache;

    public VersionedParcel(ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        this.mReadCache = arrayMap;
        this.mWriteCache = arrayMap2;
        this.mParcelizerCache = arrayMap3;
    }

    public abstract void closeField();

    public abstract VersionedParcel createSubParcel();

    public final Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        Class orDefault = this.mParcelizerCache.getOrDefault(cls.getName(), null);
        if (orDefault != null) {
            return orDefault;
        }
        Class<?> cls2 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.mParcelizerCache.put(cls.getName(), cls2);
        return cls2;
    }

    public final Method getReadMethod(String str) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method orDefault = this.mReadCache.getOrDefault(str, null);
        if (orDefault != null) {
            return orDefault;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
        this.mReadCache.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method getWriteMethod(Class cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method orDefault = this.mWriteCache.getOrDefault(cls.getName(), null);
        if (orDefault != null) {
            return orDefault;
        }
        Class findParcelClass = findParcelClass(cls);
        System.currentTimeMillis();
        Method declaredMethod = findParcelClass.getDeclaredMethod("write", cls, VersionedParcel.class);
        this.mWriteCache.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean readBoolean();

    public abstract byte[] readByteArray();

    public abstract CharSequence readCharSequence();

    public abstract boolean readField(int i);

    public abstract int readInt();

    public int readInt(int i, int i2) {
        return !readField(i2) ? i : readInt();
    }

    public abstract <T extends Parcelable> T readParcelable();

    public <T extends Parcelable> T readParcelable(T t, int i) {
        return !readField(i) ? t : (T) readParcelable();
    }

    public abstract String readString();

    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        try {
            return (T) ((VersionedParcelable) getReadMethod(readString).invoke(null, createSubParcel()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    public abstract void setOutputField(int i);

    public abstract void writeBoolean(boolean z);

    public abstract void writeByteArray(byte[] bArr);

    public abstract void writeCharSequence(CharSequence charSequence);

    public abstract void writeInt(int i);

    public abstract void writeParcelable(Parcelable parcelable);

    public abstract void writeString(String str);

    /* JADX WARN: Multi-variable type inference failed */
    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
            VersionedParcel createSubParcel = createSubParcel();
            try {
                getWriteMethod(versionedParcelable.getClass()).invoke(null, versionedParcelable, createSubParcel);
                createSubParcel.closeField();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (InvocationTargetException e4) {
                if (e4.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e4.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e5);
        }
    }
}
