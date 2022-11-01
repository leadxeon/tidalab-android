package com.facebook.react.bridge;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.Arrays;
@DoNotStrip
/* loaded from: classes.dex */
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static int jniPassCounter = 0;
    private Object[] mLocalArray;
    private ReadableType[] mLocalTypeArray;

    /* renamed from: com.facebook.react.bridge.ReadableNativeArray$1  reason: invalid class name */
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

    static {
        ReactBridge.staticInit();
    }

    public ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        Object[] objArr = this.mLocalArray;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            if (this.mLocalArray == null) {
                jniPassCounter++;
                Object[] importArray = importArray();
                R$dimen.assertNotNull(importArray);
                this.mLocalArray = importArray;
            }
        }
        return this.mLocalArray;
    }

    private ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr = this.mLocalTypeArray;
        if (readableTypeArr != null) {
            return readableTypeArr;
        }
        synchronized (this) {
            if (this.mLocalTypeArray == null) {
                jniPassCounter++;
                Object[] importTypeArray = importTypeArray();
                R$dimen.assertNotNull(importTypeArray);
                Object[] objArr = importTypeArray;
                this.mLocalTypeArray = (ReadableType[]) Arrays.copyOf(objArr, objArr.length, ReadableType[].class);
            }
        }
        return this.mLocalTypeArray;
    }

    private native Object[] importArray();

    private native Object[] importTypeArray();

    public boolean equals(Object obj) {
        if (!(obj instanceof ReadableNativeArray)) {
            return false;
        }
        return Arrays.deepEquals(getLocalArray(), ((ReadableNativeArray) obj).getLocalArray());
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int i) {
        return ((Boolean) getLocalArray()[i]).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int i) {
        return ((Double) getLocalArray()[i]).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int i) {
        return DynamicFromArray.create(this, i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int i) {
        return ((Double) getLocalArray()[i]).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int i) {
        return (String) getLocalArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int i) {
        return getLocalTypeArray()[i];
    }

    public int hashCode() {
        return getLocalArray().hashCode();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int i) {
        return getLocalArray()[i] == null;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        return getLocalArray().length;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            int ordinal = getType(i).ordinal();
            if (ordinal == 0) {
                arrayList.add(null);
            } else if (ordinal == 1) {
                arrayList.add(Boolean.valueOf(getBoolean(i)));
            } else if (ordinal == 2) {
                arrayList.add(Double.valueOf(getDouble(i)));
            } else if (ordinal == 3) {
                arrayList.add(getString(i));
            } else if (ordinal == 4) {
                arrayList.add(getMap(i).toHashMap());
            } else if (ordinal == 5) {
                arrayList.add(getArray(i).toArrayList());
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("Could not convert object at index: ", i, "."));
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeArray getArray(int i) {
        return (ReadableNativeArray) getLocalArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeMap getMap(int i) {
        return (ReadableNativeMap) getLocalArray()[i];
    }
}
