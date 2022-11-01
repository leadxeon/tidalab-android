package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>> {
    public final PlatformBitmapFactory mBitmapFactory;
    public final Executor mExecutor;
    public final Producer<CloseableReference<CloseableImage>> mInputProducer;

    /* loaded from: classes.dex */
    public class PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        public boolean mIsClosed;
        public final RequestListener mListener;
        public final Postprocessor mPostprocessor;
        public final String mRequestId;
        public CloseableReference<CloseableImage> mSourceImageRef = null;
        public int mStatus = 0;
        public boolean mIsDirty = false;
        public boolean mIsPostProcessingRunning = false;

        /* renamed from: com.facebook.imagepipeline.producers.PostprocessorProducer$PostprocessorConsumer$2  reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass2 implements Runnable {
            public AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public void run() {
                CloseableReference<CloseableImage> closeableReference;
                int i;
                boolean runningIfDirtyAndNotRunning;
                synchronized (PostprocessorConsumer.this) {
                    PostprocessorConsumer postprocessorConsumer = PostprocessorConsumer.this;
                    closeableReference = postprocessorConsumer.mSourceImageRef;
                    i = postprocessorConsumer.mStatus;
                    postprocessorConsumer.mSourceImageRef = null;
                    postprocessorConsumer.mIsDirty = false;
                }
                if (CloseableReference.isValid(closeableReference)) {
                    try {
                        PostprocessorConsumer.access$600(PostprocessorConsumer.this, closeableReference, i);
                        closeableReference.close();
                    } catch (Throwable th) {
                        if (closeableReference != null) {
                            closeableReference.close();
                        }
                        throw th;
                    }
                }
                PostprocessorConsumer postprocessorConsumer2 = PostprocessorConsumer.this;
                synchronized (postprocessorConsumer2) {
                    postprocessorConsumer2.mIsPostProcessingRunning = false;
                    runningIfDirtyAndNotRunning = postprocessorConsumer2.setRunningIfDirtyAndNotRunning();
                }
                if (runningIfDirtyAndNotRunning) {
                    PostprocessorProducer.this.mExecutor.execute(new AnonymousClass2());
                }
            }
        }

        public PostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, RequestListener requestListener, String str, Postprocessor postprocessor, ProducerContext producerContext) {
            super(consumer);
            this.mListener = requestListener;
            this.mRequestId = str;
            this.mPostprocessor = postprocessor;
            producerContext.addCallbacks(new BaseProducerContextCallbacks(PostprocessorProducer.this) { // from class: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.1
                @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    PostprocessorConsumer postprocessorConsumer = PostprocessorConsumer.this;
                    if (postprocessorConsumer.close()) {
                        postprocessorConsumer.mConsumer.onCancellation();
                    }
                }
            });
        }

        public static void access$600(PostprocessorConsumer postprocessorConsumer, CloseableReference closeableReference, int i) {
            Objects.requireNonNull(postprocessorConsumer);
            R$dimen.checkArgument(CloseableReference.isValid(closeableReference));
            if (!(((CloseableImage) closeableReference.get()) instanceof CloseableStaticBitmap)) {
                postprocessorConsumer.maybeNotifyOnNewResult(closeableReference, i);
                return;
            }
            postprocessorConsumer.mListener.onProducerStart(postprocessorConsumer.mRequestId, "PostprocessorProducer");
            CloseableReference<CloseableImage> closeableReference2 = null;
            try {
                try {
                    closeableReference2 = postprocessorConsumer.postprocessInternal((CloseableImage) closeableReference.get());
                    RequestListener requestListener = postprocessorConsumer.mListener;
                    String str = postprocessorConsumer.mRequestId;
                    requestListener.onProducerFinishWithSuccess(str, "PostprocessorProducer", postprocessorConsumer.getExtraMap(requestListener, str, postprocessorConsumer.mPostprocessor));
                    postprocessorConsumer.maybeNotifyOnNewResult(closeableReference2, i);
                } catch (Exception e) {
                    RequestListener requestListener2 = postprocessorConsumer.mListener;
                    String str2 = postprocessorConsumer.mRequestId;
                    requestListener2.onProducerFinishWithFailure(str2, "PostprocessorProducer", e, postprocessorConsumer.getExtraMap(requestListener2, str2, postprocessorConsumer.mPostprocessor));
                    if (postprocessorConsumer.close()) {
                        postprocessorConsumer.mConsumer.onFailure(e);
                    }
                }
            } finally {
                if (closeableReference2 != null) {
                    closeableReference2.close();
                }
            }
        }

        public final boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> closeableReference = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference != null) {
                    closeableReference.close();
                }
                return true;
            }
        }

        public final Map<String, String> getExtraMap(RequestListener requestListener, String str, Postprocessor postprocessor) {
            if (!requestListener.requiresExtraMap(str)) {
                return null;
            }
            return ImmutableMap.of("Postprocessor", postprocessor.getName());
        }

        /* JADX WARN: Code restructure failed: missing block: B:7:0x000a, code lost:
            if (r1 != false) goto L_0x0010;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void maybeNotifyOnNewResult(com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r3, int r4) {
            /*
                r2 = this;
                boolean r0 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r4)
                if (r0 != 0) goto L_0x0010
                monitor-enter(r2)
                boolean r1 = r2.mIsClosed     // Catch: all -> 0x000d
                monitor-exit(r2)
                if (r1 == 0) goto L_0x0018
                goto L_0x0010
            L_0x000d:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            L_0x0010:
                if (r0 == 0) goto L_0x001d
                boolean r0 = r2.close()
                if (r0 == 0) goto L_0x001d
            L_0x0018:
                com.facebook.imagepipeline.producers.Consumer<O> r0 = r2.mConsumer
                r0.onNewResult(r3, r4)
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.maybeNotifyOnNewResult(com.facebook.common.references.CloseableReference, int):void");
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            if (close()) {
                this.mConsumer.onCancellation();
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            if (close()) {
                this.mConsumer.onFailure(th);
            }
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            CloseableReference closeableReference = (CloseableReference) obj;
            if (CloseableReference.isValid(closeableReference)) {
                synchronized (this) {
                    if (!this.mIsClosed) {
                        CloseableReference<CloseableImage> closeableReference2 = this.mSourceImageRef;
                        this.mSourceImageRef = CloseableReference.cloneOrNull(closeableReference);
                        this.mStatus = i;
                        this.mIsDirty = true;
                        boolean runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
                        if (closeableReference2 != null) {
                            closeableReference2.close();
                        }
                        if (runningIfDirtyAndNotRunning) {
                            PostprocessorProducer.this.mExecutor.execute(new AnonymousClass2());
                        }
                    }
                }
            } else if (BaseConsumer.isLast(i)) {
                maybeNotifyOnNewResult(null, i);
            }
        }

        public final CloseableReference<CloseableImage> postprocessInternal(CloseableImage closeableImage) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            CloseableReference<Bitmap> process = this.mPostprocessor.process(closeableStaticBitmap.mBitmap, PostprocessorProducer.this.mBitmapFactory);
            try {
                CloseableReference<CloseableImage> of = CloseableReference.of(new CloseableStaticBitmap(process, closeableImage.getQualityInfo(), closeableStaticBitmap.mRotationAngle, closeableStaticBitmap.mExifOrientation));
                if (process != null) {
                    process.close();
                }
                return of;
            } catch (Throwable th) {
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (process != null) {
                    process.close();
                }
                throw th;
            }
        }

        public final synchronized boolean setRunningIfDirtyAndNotRunning() {
            if (this.mIsClosed || !this.mIsDirty || this.mIsPostProcessingRunning || !CloseableReference.isValid(this.mSourceImageRef)) {
                return false;
            }
            this.mIsPostProcessingRunning = true;
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
        public boolean mIsClosed = false;
        public CloseableReference<CloseableImage> mSourceImageRef = null;

        public RepeatedPostprocessorConsumer(PostprocessorProducer postprocessorProducer, PostprocessorConsumer postprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext producerContext, AnonymousClass1 r5) {
            super(postprocessorConsumer);
            repeatedPostprocessor.setCallback(this);
            producerContext.addCallbacks(new BaseProducerContextCallbacks(postprocessorProducer) { // from class: com.facebook.imagepipeline.producers.PostprocessorProducer.RepeatedPostprocessorConsumer.1
                @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (RepeatedPostprocessorConsumer.this.close()) {
                        RepeatedPostprocessorConsumer.this.mConsumer.onCancellation();
                    }
                }
            });
        }

        public final boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> closeableReference = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference != null) {
                    closeableReference.close();
                }
                return true;
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            if (close()) {
                this.mConsumer.onCancellation();
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            if (close()) {
                this.mConsumer.onFailure(th);
            }
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            CloseableReference closeableReference = (CloseableReference) obj;
            if (!BaseConsumer.isNotLast(i)) {
                synchronized (this) {
                    if (!this.mIsClosed) {
                        CloseableReference<CloseableImage> closeableReference2 = this.mSourceImageRef;
                        this.mSourceImageRef = CloseableReference.cloneOrNull(closeableReference);
                        if (closeableReference2 != null) {
                            closeableReference2.close();
                        }
                    }
                }
                synchronized (this) {
                    if (!this.mIsClosed) {
                        CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mSourceImageRef);
                        try {
                            this.mConsumer.onNewResult(cloneOrNull, 0);
                        } finally {
                            if (cloneOrNull != null) {
                                cloneOrNull.close();
                            }
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        public SingleUsePostprocessorConsumer(PostprocessorProducer postprocessorProducer, PostprocessorConsumer postprocessorConsumer, AnonymousClass1 r3) {
            super(postprocessorConsumer);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            CloseableReference closeableReference = (CloseableReference) obj;
            if (!BaseConsumer.isNotLast(i)) {
                this.mConsumer.onNewResult(closeableReference, i);
            }
        }
    }

    public PostprocessorProducer(Producer<CloseableReference<CloseableImage>> producer, PlatformBitmapFactory platformBitmapFactory, Executor executor) {
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
        this.mBitmapFactory = platformBitmapFactory;
        Objects.requireNonNull(executor);
        this.mExecutor = executor;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer<CloseableReference<CloseableImage>> consumer2;
        RequestListener listener = producerContext.getListener();
        Postprocessor postprocessor = producerContext.getImageRequest().mPostprocessor;
        PostprocessorConsumer postprocessorConsumer = new PostprocessorConsumer(consumer, listener, producerContext.getId(), postprocessor, producerContext);
        if (postprocessor instanceof RepeatedPostprocessor) {
            consumer2 = new RepeatedPostprocessorConsumer(this, postprocessorConsumer, (RepeatedPostprocessor) postprocessor, producerContext, null);
        } else {
            consumer2 = new SingleUsePostprocessorConsumer(this, postprocessorConsumer, null);
        }
        this.mInputProducer.produceResults(consumer2, producerContext);
    }
}
