package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class JavaOnlyArray implements ReadableArray, WritableArray {
    private final List mBackingList;

    /* renamed from: com.facebook.react.bridge.JavaOnlyArray$1  reason: invalid class name */
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

    private JavaOnlyArray(Object... objArr) {
        this.mBackingList = Arrays.asList(objArr);
    }

    public static JavaOnlyArray deepClone(ReadableArray readableArray) {
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            int ordinal = readableArray.getType(i).ordinal();
            if (ordinal == 0) {
                javaOnlyArray.pushNull();
            } else if (ordinal == 1) {
                javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
            } else if (ordinal == 2) {
                javaOnlyArray.pushDouble(readableArray.getDouble(i));
            } else if (ordinal == 3) {
                javaOnlyArray.pushString(readableArray.getString(i));
            } else if (ordinal == 4) {
                javaOnlyArray.pushMap(JavaOnlyMap.deepClone(readableArray.getMap(i)));
            } else if (ordinal == 5) {
                javaOnlyArray.pushArray(deepClone(readableArray.getArray(i)));
            }
        }
        return javaOnlyArray;
    }

    public static JavaOnlyArray from(List list) {
        return new JavaOnlyArray(list);
    }

    public static JavaOnlyArray of(Object... objArr) {
        return new JavaOnlyArray(objArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        List list = this.mBackingList;
        List list2 = ((JavaOnlyArray) obj).mBackingList;
        return list == null ? list2 == null : list.equals(list2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableArray getArray(int i) {
        return (ReadableArray) this.mBackingList.get(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int i) {
        return ((Boolean) this.mBackingList.get(i)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int i) {
        return ((Number) this.mBackingList.get(i)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int i) {
        return DynamicFromArray.create(this, i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int i) {
        return ((Number) this.mBackingList.get(i)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableMap getMap(int i) {
        return (ReadableMap) this.mBackingList.get(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int i) {
        return (String) this.mBackingList.get(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int i) {
        Object obj = this.mBackingList.get(i);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer)) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        return null;
    }

    public int hashCode() {
        List list = this.mBackingList;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int i) {
        return this.mBackingList.get(i) == null;
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushArray(ReadableArray readableArray) {
        this.mBackingList.add(readableArray);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushBoolean(boolean z) {
        this.mBackingList.add(Boolean.valueOf(z));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushDouble(double d) {
        this.mBackingList.add(Double.valueOf(d));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushInt(int i) {
        this.mBackingList.add(new Double(i));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushMap(ReadableMap readableMap) {
        this.mBackingList.add(readableMap);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushNull() {
        this.mBackingList.add(null);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushString(String str) {
        this.mBackingList.add(str);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        return this.mBackingList.size();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        return new ArrayList<>(this.mBackingList);
    }

    public String toString() {
        return this.mBackingList.toString();
    }

    private JavaOnlyArray(List list) {
        this.mBackingList = new ArrayList(list);
    }

    public JavaOnlyArray() {
        this.mBackingList = new ArrayList();
    }
}
