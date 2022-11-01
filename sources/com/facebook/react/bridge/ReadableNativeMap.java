package com.facebook.react.bridge;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
@DoNotStrip
/* loaded from: classes.dex */
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static int mJniCallCounter;
    private String[] mKeys;
    private HashMap<String, Object> mLocalMap;
    private HashMap<String, ReadableType> mLocalTypeMap;

    /* renamed from: com.facebook.react.bridge.ReadableNativeMap$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            ReadableType.values();
            int[] iArr = new int[6];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        private final Iterator<String> mIterator;

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mIterator = readableNativeMap.getLocalMap().keySet().iterator();
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public boolean hasNextKey() {
            return this.mIterator.hasNext();
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public String nextKey() {
            return this.mIterator.next();
        }
    }

    static {
        ReactBridge.staticInit();
    }

    public ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    private void checkInstance(String str, Object obj, Class cls) {
        if (obj != null && !cls.isInstance(obj)) {
            StringBuilder outline16 = GeneratedOutlineSupport.outline16("Value for ", str, " cannot be cast from ");
            outline16.append(obj.getClass().getSimpleName());
            outline16.append(" to ");
            outline16.append(cls.getSimpleName());
            throw new UnexpectedNativeTypeException(outline16.toString());
        }
    }

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, Object> getLocalMap() {
        HashMap<String, Object> hashMap = this.mLocalMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                String[] importKeys = importKeys();
                R$dimen.assertNotNull(importKeys);
                this.mKeys = importKeys;
                mJniCallCounter++;
            }
            if (this.mLocalMap == null) {
                Object[] importValues = importValues();
                R$dimen.assertNotNull(importValues);
                Object[] objArr = importValues;
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalMap.put(this.mKeys[i], objArr[i]);
                }
            }
        }
        return this.mLocalMap;
    }

    private HashMap<String, ReadableType> getLocalTypeMap() {
        HashMap<String, ReadableType> hashMap = this.mLocalTypeMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                String[] importKeys = importKeys();
                R$dimen.assertNotNull(importKeys);
                this.mKeys = importKeys;
                mJniCallCounter++;
            }
            if (this.mLocalTypeMap == null) {
                Object[] importTypes = importTypes();
                R$dimen.assertNotNull(importTypes);
                Object[] objArr = importTypes;
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalTypeMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalTypeMap.put(this.mKeys[i], (ReadableType) objArr[i]);
                }
            }
        }
        return this.mLocalTypeMap;
    }

    private Object getNullableValue(String str) {
        if (hasKey(str)) {
            return getLocalMap().get(str);
        }
        throw new NoSuchKeyException(str);
    }

    private Object getValue(String str) {
        if (!hasKey(str) || isNull(str)) {
            throw new NoSuchKeyException(str);
        }
        Object obj = getLocalMap().get(str);
        R$dimen.assertNotNull(obj);
        return obj;
    }

    private native String[] importKeys();

    private native Object[] importTypes();

    private native Object[] importValues();

    public boolean equals(Object obj) {
        if (!(obj instanceof ReadableNativeMap)) {
            return false;
        }
        return getLocalMap().equals(((ReadableNativeMap) obj).getLocalMap());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(String str) {
        return (ReadableArray) getNullableValue(str, ReadableArray.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(String str) {
        return ((Boolean) getValue(str, Boolean.class)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(String str) {
        return ((Double) getValue(str, Double.class)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Dynamic getDynamic(String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        return getLocalMap().entrySet().iterator();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(String str) {
        return ((Double) getValue(str, Double.class)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(String str) {
        return (String) getNullableValue(str, String.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableType getType(String str) {
        if (getLocalTypeMap().containsKey(str)) {
            ReadableType readableType = getLocalTypeMap().get(str);
            R$dimen.assertNotNull(readableType);
            return readableType;
        }
        throw new NoSuchKeyException(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(String str) {
        return getLocalMap().containsKey(str);
    }

    public int hashCode() {
        return getLocalMap().hashCode();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(String str) {
        if (getLocalMap().containsKey(str)) {
            return getLocalMap().get(str) == null;
        }
        throw new NoSuchKeyException(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMapKeySetIterator(this);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>(getLocalMap());
        for (String str : hashMap.keySet()) {
            int ordinal = getType(str).ordinal();
            if (!(ordinal == 0 || ordinal == 1 || ordinal == 2 || ordinal == 3)) {
                if (ordinal == 4) {
                    ReadableNativeMap map = getMap(str);
                    R$dimen.assertNotNull(map);
                    hashMap.put(str, map.toHashMap());
                } else if (ordinal == 5) {
                    ReadableArray array = getArray(str);
                    R$dimen.assertNotNull(array);
                    hashMap.put(str, array.toArrayList());
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline9("Could not convert object with key: ", str, "."));
                }
            }
        }
        return hashMap;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableNativeMap getMap(String str) {
        return (ReadableNativeMap) getNullableValue(str, ReadableNativeMap.class);
    }

    private <T> T getNullableValue(String str, Class<T> cls) {
        T t = (T) getNullableValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    private <T> T getValue(String str, Class<T> cls) {
        T t = (T) getValue(str);
        checkInstance(str, t, cls);
        return t;
    }
}
