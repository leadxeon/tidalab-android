package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Pair;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
@DoNotStrip
/* loaded from: classes.dex */
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished;
    private final Looper mLooper;
    private final String mName;
    private MessageQueueThreadPerfStats mPerfStats;

    /* renamed from: com.facebook.react.bridge.queue.MessageQueueThreadImpl$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType;

        static {
            MessageQueueThreadSpec.ThreadType.values();
            int[] iArr = new int[2];
            $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType = iArr;
            try {
                iArr[MessageQueueThreadSpec.ThreadType.MAIN_UI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType[MessageQueueThreadSpec.ThreadType.NEW_BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this(str, looper, queueThreadExceptionHandler, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assignToPerfStats(MessageQueueThreadPerfStats messageQueueThreadPerfStats, long j, long j2) {
        messageQueueThreadPerfStats.wallTime = j;
        messageQueueThreadPerfStats.cpuTime = j2;
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec messageQueueThreadSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        int ordinal = messageQueueThreadSpec.getThreadType().ordinal();
        if (ordinal == 0) {
            return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
        }
        if (ordinal == 1) {
            return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown thread type: ");
        outline13.append(messageQueueThreadSpec.getThreadType());
        throw new RuntimeException(outline13.toString());
    }

    private static MessageQueueThreadImpl createForMainThread(String str, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(str, Looper.getMainLooper(), queueThreadExceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(-4);
                }
            });
        }
        return messageQueueThreadImpl;
    }

    private static MessageQueueThreadImpl startNewBackgroundThread(String str, long j, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        new Thread(null, new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.4
            /* JADX WARN: Type inference failed for: r2v0, types: [T, android.util.Pair] */
            /* JADX WARN: Unknown variable types count: 1 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r5 = this;
                    r0 = -4
                    android.os.Process.setThreadPriority(r0)
                    android.os.Looper.prepare()
                    com.facebook.react.bridge.queue.MessageQueueThreadPerfStats r0 = new com.facebook.react.bridge.queue.MessageQueueThreadPerfStats
                    r0.<init>()
                    long r1 = android.os.SystemClock.uptimeMillis()
                    long r3 = android.os.SystemClock.currentThreadTimeMillis()
                    com.facebook.react.bridge.queue.MessageQueueThreadImpl.access$100(r0, r1, r3)
                    com.facebook.react.common.futures.SimpleSettableFuture r1 = com.facebook.react.common.futures.SimpleSettableFuture.this
                    android.util.Pair r2 = new android.util.Pair
                    android.os.Looper r3 = android.os.Looper.myLooper()
                    r2.<init>(r3, r0)
                    r1.checkNotSet()
                    r1.mResult = r2
                    java.util.concurrent.CountDownLatch r0 = r1.mReadyLatch
                    r0.countDown()
                    android.os.Looper.loop()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass4.run():void");
            }
        }, GeneratedOutlineSupport.outline8("mqt_", str), j).start();
        try {
            Pair pair = (Pair) simpleSettableFuture.get();
            return new MessageQueueThreadImpl(str, (Looper) pair.first, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) pair.second);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.1
            /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Object] */
            /* JADX WARN: Unknown variable types count: 1 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r2 = this;
                    com.facebook.react.common.futures.SimpleSettableFuture r0 = r2     // Catch: Exception -> 0x0013
                    java.util.concurrent.Callable r1 = r3     // Catch: Exception -> 0x0013
                    java.lang.Object r1 = r1.call()     // Catch: Exception -> 0x0013
                    r0.checkNotSet()     // Catch: Exception -> 0x0013
                    r0.mResult = r1     // Catch: Exception -> 0x0013
                    java.util.concurrent.CountDownLatch r0 = r0.mReadyLatch     // Catch: Exception -> 0x0013
                    r0.countDown()     // Catch: Exception -> 0x0013
                    goto L_0x0020
                L_0x0013:
                    r0 = move-exception
                    com.facebook.react.common.futures.SimpleSettableFuture r1 = r2
                    r1.checkNotSet()
                    r1.mException = r0
                    java.util.concurrent.CountDownLatch r0 = r1.mReadyLatch
                    r0.countDown()
                L_0x0020:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass1.run():void");
            }
        });
        return simpleSettableFuture;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public MessageQueueThreadPerfStats getPerfStats() {
        return this.mPerfStats;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException unused) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Got interrupted waiting to join thread ");
                outline13.append(this.mName);
                throw new RuntimeException(outline13.toString());
            }
        }
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public void resetPerfStats() {
        assignToPerfStats(this.mPerfStats, -1L, -1L);
        runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.2
            @Override // java.lang.Runnable
            public void run() {
                MessageQueueThreadImpl.assignToPerfStats(MessageQueueThreadImpl.this.mPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
            }
        });
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Tried to enqueue runnable on already finished thread: '");
            outline13.append(getName());
            outline13.append("... dropping Runnable.");
            FLog.w("ReactNative", outline13.toString());
        }
        this.mHandler.post(runnable);
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler, MessageQueueThreadPerfStats messageQueueThreadPerfStats) {
        this.mIsFinished = false;
        this.mName = str;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, queueThreadExceptionHandler);
        this.mPerfStats = messageQueueThreadPerfStats;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected to be called from the '");
        outline13.append(getName());
        outline13.append("' thread!");
        this.mAssertionErrorMessage = outline13.toString();
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @DoNotStrip
    public void assertIsOnThread(String str) {
        boolean isOnThread = isOnThread();
        SoftAssertions.assertCondition(isOnThread, this.mAssertionErrorMessage + " " + str);
    }
}
