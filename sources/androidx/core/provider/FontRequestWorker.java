package androidx.core.provider;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Process;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class FontRequestWorker {
    public static final ExecutorService DEFAULT_EXECUTOR_SERVICE;
    public static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    public static final Object LOCK = new Object();
    public static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new ThreadFactory("fonts-androidx", 10) { // from class: androidx.core.provider.RequestExecutor$DefaultThreadFactory
            public int mPriority;
            public String mThreadName;

            /* loaded from: classes.dex */
            public static class ProcessPriorityThread extends Thread {
                public final int mPriority;

                public ProcessPriorityThread(Runnable runnable, String str, int i) {
                    super(runnable, str);
                    this.mPriority = i;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(this.mPriority);
                    super.run();
                }
            }

            {
                this.mThreadName = r1;
                this.mPriority = r2;
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new ProcessPriorityThread(runnable, this.mThreadName, this.mPriority);
            }
        });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        DEFAULT_EXECUTOR_SERVICE = threadPoolExecutor;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.provider.FontRequestWorker.TypefaceResult getFontSync(java.lang.String r7, android.content.Context r8, androidx.core.provider.FontRequest r9, int r10) {
        /*
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r0 = androidx.core.provider.FontRequestWorker.sTypefaceCache
            java.lang.Object r0 = r0.get(r7)
            android.graphics.Typeface r0 = (android.graphics.Typeface) r0
            if (r0 == 0) goto L_0x0010
            androidx.core.provider.FontRequestWorker$TypefaceResult r7 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r7.<init>(r0)
            return r7
        L_0x0010:
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontFamilyResult r9 = androidx.core.provider.FontProvider.getFontFamilyResult(r8, r9, r0)     // Catch: NameNotFoundException -> 0x0061
            int r1 = r9.mStatusCode
            r2 = -3
            r3 = 1
            if (r1 == 0) goto L_0x0020
            if (r1 == r3) goto L_0x001e
            goto L_0x0035
        L_0x001e:
            r1 = -2
            goto L_0x003e
        L_0x0020:
            androidx.core.provider.FontsContractCompat$FontInfo[] r1 = r9.mFonts
            r4 = 0
            if (r1 == 0) goto L_0x003d
            int r5 = r1.length
            if (r5 != 0) goto L_0x0029
            goto L_0x003d
        L_0x0029:
            int r3 = r1.length
            r5 = 0
        L_0x002b:
            if (r5 >= r3) goto L_0x003c
            r6 = r1[r5]
            int r6 = r6.mResultCode
            if (r6 == 0) goto L_0x0039
            if (r6 >= 0) goto L_0x0037
        L_0x0035:
            r1 = -3
            goto L_0x003e
        L_0x0037:
            r1 = r6
            goto L_0x003e
        L_0x0039:
            int r5 = r5 + 1
            goto L_0x002b
        L_0x003c:
            r3 = 0
        L_0x003d:
            r1 = r3
        L_0x003e:
            if (r1 == 0) goto L_0x0046
            androidx.core.provider.FontRequestWorker$TypefaceResult r7 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r7.<init>(r1)
            return r7
        L_0x0046:
            androidx.core.provider.FontsContractCompat$FontInfo[] r9 = r9.mFonts
            androidx.core.graphics.TypefaceCompatBaseImpl r1 = androidx.core.graphics.TypefaceCompat.sTypefaceCompatImpl
            android.graphics.Typeface r8 = r1.createFromFontInfo(r8, r0, r9, r10)
            if (r8 == 0) goto L_0x005b
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r9 = androidx.core.provider.FontRequestWorker.sTypefaceCache
            r9.put(r7, r8)
            androidx.core.provider.FontRequestWorker$TypefaceResult r7 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r7.<init>(r8)
            return r7
        L_0x005b:
            androidx.core.provider.FontRequestWorker$TypefaceResult r7 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r7.<init>(r2)
            return r7
        L_0x0061:
            androidx.core.provider.FontRequestWorker$TypefaceResult r7 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r8 = -1
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.getFontSync(java.lang.String, android.content.Context, androidx.core.provider.FontRequest, int):androidx.core.provider.FontRequestWorker$TypefaceResult");
    }

    /* loaded from: classes.dex */
    public static final class TypefaceResult {
        public final int mResult;
        public final Typeface mTypeface;

        public TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        @SuppressLint({"WrongConstant"})
        public TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }
    }
}
