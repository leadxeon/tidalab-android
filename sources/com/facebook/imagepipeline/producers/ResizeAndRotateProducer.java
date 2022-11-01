package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.MemoryPooledByteBufferOutputStream;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.transcoder.ImageTranscodeResult;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class ResizeAndRotateProducer implements Producer<EncodedImage> {
    public final Executor mExecutor;
    public final ImageTranscoderFactory mImageTranscoderFactory;
    public final Producer<EncodedImage> mInputProducer;
    public final boolean mIsResizingEnabled;
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    /* loaded from: classes.dex */
    public class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public final ImageTranscoderFactory mImageTranscoderFactory;
        public boolean mIsCancelled = false;
        public final boolean mIsResizingEnabled;
        public final JobScheduler mJobScheduler;
        public final ProducerContext mProducerContext;

        public TransformingConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
            super(consumer);
            this.mProducerContext = producerContext;
            Objects.requireNonNull(producerContext.getImageRequest());
            this.mIsResizingEnabled = z;
            this.mImageTranscoderFactory = imageTranscoderFactory;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new JobScheduler.JobRunnable(ResizeAndRotateProducer.this) { // from class: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.1
                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                public void run(EncodedImage encodedImage, int i) {
                    ImageTranscodeResult transcode;
                    TransformingConsumer transformingConsumer = TransformingConsumer.this;
                    ImageTranscoderFactory imageTranscoderFactory2 = transformingConsumer.mImageTranscoderFactory;
                    encodedImage.parseMetaDataIfNeeded();
                    ImageTranscoder createImageTranscoder = imageTranscoderFactory2.createImageTranscoder(encodedImage.mImageFormat, TransformingConsumer.this.mIsResizingEnabled);
                    Objects.requireNonNull(createImageTranscoder);
                    transformingConsumer.mProducerContext.getListener().onProducerStart(transformingConsumer.mProducerContext.getId(), "ResizeAndRotateProducer");
                    ImageRequest imageRequest = transformingConsumer.mProducerContext.getImageRequest();
                    PooledByteBufferOutputStream newOutputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.newOutputStream();
                    try {
                        try {
                            transcode = createImageTranscoder.transcode(encodedImage, newOutputStream, imageRequest.mRotationOptions, imageRequest.mResizeOptions, null, 85);
                        } finally {
                            newOutputStream.close();
                        }
                    } catch (Exception e) {
                        transformingConsumer.mProducerContext.getListener().onProducerFinishWithFailure(transformingConsumer.mProducerContext.getId(), "ResizeAndRotateProducer", e, null);
                        if (BaseConsumer.isLast(i)) {
                            transformingConsumer.mConsumer.onFailure(e);
                        }
                    }
                    if (transcode.mTranscodeStatus != 2) {
                        Map<String, String> extraMap = transformingConsumer.getExtraMap(encodedImage, imageRequest.mResizeOptions, transcode, createImageTranscoder.getIdentifier());
                        CloseableReference of = CloseableReference.of(((MemoryPooledByteBufferOutputStream) newOutputStream).toByteBuffer());
                        try {
                            EncodedImage encodedImage2 = new EncodedImage(of);
                            encodedImage2.mImageFormat = DefaultImageFormats.JPEG;
                            encodedImage2.parseMetaData();
                            transformingConsumer.mProducerContext.getListener().onProducerFinishWithSuccess(transformingConsumer.mProducerContext.getId(), "ResizeAndRotateProducer", extraMap);
                            if (transcode.mTranscodeStatus != 1) {
                                i |= 16;
                            }
                            transformingConsumer.mConsumer.onNewResult(encodedImage2, i);
                            encodedImage2.close();
                        } finally {
                            if (of != null) {
                                of.close();
                            }
                        }
                    } else {
                        throw new RuntimeException("Error while transcoding the image");
                    }
                }
            }, 100);
            producerContext.addCallbacks(new BaseProducerContextCallbacks(ResizeAndRotateProducer.this, consumer) { // from class: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.2
                public final /* synthetic */ Consumer val$consumer;

                {
                    this.val$consumer = consumer;
                }

                @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    TransformingConsumer.this.mJobScheduler.clearJob();
                    TransformingConsumer.this.mIsCancelled = true;
                    this.val$consumer.onCancellation();
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                        TransformingConsumer.this.mJobScheduler.scheduleJob();
                    }
                }
            });
        }

        public final Map<String, String> getExtraMap(EncodedImage encodedImage, ResizeOptions resizeOptions, ImageTranscodeResult imageTranscodeResult, String str) {
            String str2;
            long j;
            if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            encodedImage.parseMetaDataIfNeeded();
            sb.append(encodedImage.mWidth);
            sb.append("x");
            encodedImage.parseMetaDataIfNeeded();
            sb.append(encodedImage.mHeight);
            String sb2 = sb.toString();
            if (resizeOptions != null) {
                str2 = resizeOptions.width + "x" + resizeOptions.height;
            } else {
                str2 = "Unspecified";
            }
            HashMap hashMap = new HashMap();
            encodedImage.parseMetaDataIfNeeded();
            hashMap.put("Image format", String.valueOf(encodedImage.mImageFormat));
            hashMap.put("Original size", sb2);
            hashMap.put("Requested size", str2);
            JobScheduler jobScheduler = this.mJobScheduler;
            synchronized (jobScheduler) {
                j = jobScheduler.mJobStartTime - jobScheduler.mJobSubmitTime;
            }
            hashMap.put("queueTime", String.valueOf(j));
            hashMap.put("Transcoder id", str);
            hashMap.put("Transcoding result", String.valueOf(imageTranscodeResult));
            return new ImmutableMap(hashMap);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
            if (r6 != false) goto L_0x0075;
         */
        /* JADX WARN: Removed duplicated region for block: B:32:0x008a  */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onNewResultImpl(java.lang.Object r11, int r12) {
            /*
                Method dump skipped, instructions count: 263
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.onNewResultImpl(java.lang.Object, int):void");
        }
    }

    public ResizeAndRotateProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> producer, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
        Objects.requireNonNull(executor);
        this.mExecutor = executor;
        Objects.requireNonNull(pooledByteBufferFactory);
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
        Objects.requireNonNull(imageTranscoderFactory);
        this.mImageTranscoderFactory = imageTranscoderFactory;
        this.mIsResizingEnabled = z;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, producerContext, this.mIsResizingEnabled, this.mImageTranscoderFactory), producerContext);
    }
}
