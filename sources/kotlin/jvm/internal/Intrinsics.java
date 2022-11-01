package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import kotlin.UninitializedPropertyAccessException;
/* loaded from: classes.dex */
public class Intrinsics {
    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static <T extends Throwable> T sanitizeStackTrace(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    public static String stringPlus(String str, Object obj) {
        return GeneratedOutlineSupport.outline7(str, obj);
    }

    public static void throwUninitializedPropertyAccessException(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(GeneratedOutlineSupport.outline9("lateinit property ", str, " has not been initialized"));
        sanitizeStackTrace(uninitializedPropertyAccessException, Intrinsics.class.getName());
        throw uninitializedPropertyAccessException;
    }
}
