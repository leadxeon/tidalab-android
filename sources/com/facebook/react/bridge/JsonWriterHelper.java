package com.facebook.react.bridge;

import android.util.JsonWriter;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class JsonWriterHelper {

    /* renamed from: com.facebook.react.bridge.JsonWriterHelper$1  reason: invalid class name */
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

    private static void dynamicValue(JsonWriter jsonWriter, Dynamic dynamic) throws IOException {
        int ordinal = dynamic.getType().ordinal();
        if (ordinal == 0) {
            jsonWriter.nullValue();
        } else if (ordinal == 1) {
            jsonWriter.value(dynamic.asBoolean());
        } else if (ordinal == 2) {
            jsonWriter.value(dynamic.asDouble());
        } else if (ordinal == 3) {
            jsonWriter.value(dynamic.asString());
        } else if (ordinal == 4) {
            readableMapValue(jsonWriter, dynamic.asMap());
        } else if (ordinal == 5) {
            readableArrayValue(jsonWriter, dynamic.asArray());
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown data type: ");
            outline13.append(dynamic.getType());
            throw new IllegalArgumentException(outline13.toString());
        }
    }

    private static void listValue(JsonWriter jsonWriter, List<?> list) throws IOException {
        jsonWriter.beginArray();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            objectValue(jsonWriter, it.next());
        }
        jsonWriter.endArray();
    }

    private static void mapValue(JsonWriter jsonWriter, Map<?, ?> map) throws IOException {
        jsonWriter.beginObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            jsonWriter.name(entry.getKey().toString());
            value(jsonWriter, entry.getValue());
        }
        jsonWriter.endObject();
    }

    private static void objectValue(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
        } else if (obj instanceof String) {
            jsonWriter.value((String) obj);
        } else if (obj instanceof Number) {
            jsonWriter.value((Number) obj);
        } else if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("Unknown value: ", obj));
        }
    }

    public static void readableArrayValue(JsonWriter jsonWriter, ReadableArray readableArray) throws IOException {
        jsonWriter.beginArray();
        for (int i = 0; i < readableArray.size(); i++) {
            try {
                int ordinal = readableArray.getType(i).ordinal();
                if (ordinal == 0) {
                    jsonWriter.nullValue();
                } else if (ordinal == 1) {
                    jsonWriter.value(readableArray.getBoolean(i));
                } else if (ordinal == 2) {
                    jsonWriter.value(readableArray.getDouble(i));
                } else if (ordinal == 3) {
                    jsonWriter.value(readableArray.getString(i));
                } else if (ordinal == 4) {
                    readableMapValue(jsonWriter, readableArray.getMap(i));
                } else if (ordinal == 5) {
                    readableArrayValue(jsonWriter, readableArray.getArray(i));
                } else {
                    throw new IllegalArgumentException("Unknown data type: " + readableArray.getType(i));
                }
            } finally {
                jsonWriter.endArray();
            }
        }
    }

    private static void readableMapValue(JsonWriter jsonWriter, ReadableMap readableMap) throws IOException {
        jsonWriter.beginObject();
        try {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                jsonWriter.name(nextKey);
                int ordinal = readableMap.getType(nextKey).ordinal();
                if (ordinal == 0) {
                    jsonWriter.nullValue();
                } else if (ordinal == 1) {
                    jsonWriter.value(readableMap.getBoolean(nextKey));
                } else if (ordinal == 2) {
                    jsonWriter.value(readableMap.getDouble(nextKey));
                } else if (ordinal == 3) {
                    jsonWriter.value(readableMap.getString(nextKey));
                } else if (ordinal == 4) {
                    readableMapValue(jsonWriter, readableMap.getMap(nextKey));
                } else if (ordinal == 5) {
                    readableArrayValue(jsonWriter, readableMap.getArray(nextKey));
                } else {
                    throw new IllegalArgumentException("Unknown data type: " + readableMap.getType(nextKey));
                }
            }
        } finally {
            jsonWriter.endObject();
        }
    }

    public static void value(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj instanceof Map) {
            mapValue(jsonWriter, (Map) obj);
        } else if (obj instanceof List) {
            listValue(jsonWriter, (List) obj);
        } else if (obj instanceof ReadableMap) {
            readableMapValue(jsonWriter, (ReadableMap) obj);
        } else if (obj instanceof ReadableArray) {
            readableArrayValue(jsonWriter, (ReadableArray) obj);
        } else if (obj instanceof Dynamic) {
            dynamicValue(jsonWriter, (Dynamic) obj);
        } else {
            objectValue(jsonWriter, obj);
        }
    }
}
