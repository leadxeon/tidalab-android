package kotlinx.serialization.json.internal;

import kotlin.Result;
import kotlin.collections.ArrayDeque;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: CharArrayPool.kt */
/* loaded from: classes.dex */
public final class CharArrayPool {
    public static final int MAX_CHARS_IN_POOL;
    public static int charsTotal;
    public static final CharArrayPool INSTANCE = new CharArrayPool();
    public static final ArrayDeque<char[]> arrays = new ArrayDeque<>();

    static {
        Object obj;
        try {
            obj = StringsKt__IndentKt.toIntOrNull(System.getProperty("kotlinx.serialization.json.pool.size"));
        } catch (Throwable th) {
            obj = new Result.Failure(th);
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Integer num = (Integer) obj;
        MAX_CHARS_IN_POOL = num == null ? 1048576 : num.intValue();
    }
}
