package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {
    public final ByteArrayPool mByteArrayPool;
    public final CloseableReferenceFactory mCloseableReferenceFactory;
    public final boolean mDecodeCancellationEnabled;
    public final boolean mDownsampleEnabled;
    public final boolean mDownsampleEnabledForNetwork;
    public final Executor mExecutor;
    public final ImageDecoder mImageDecoder;
    public final Producer<EncodedImage> mInputProducer;
    public final int mMaxBitmapSize;
    public final ProgressiveJpegConfig mProgressiveJpegConfig;

    /* loaded from: classes.dex */
    public class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        public LocalImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i) {
            super(consumer, producerContext, z, i);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return encodedImage.getSize();
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public QualityInfo getQualityInfo() {
            return new ImmutableQualityInfo(0, false, false);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            if (BaseConsumer.isNotLast(i)) {
                return false;
            }
            return this.mJobScheduler.updateJob(encodedImage, i);
        }
    }

    /* loaded from: classes.dex */
    public class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        public int mLastScheduledScanNumber = 0;
        public final ProgressiveJpegConfig mProgressiveJpegConfig;
        public final ProgressiveJpegParser mProgressiveJpegParser;

        public NetworkImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser, ProgressiveJpegConfig progressiveJpegConfig, boolean z, int i) {
            super(consumer, producerContext, z, i);
            this.mProgressiveJpegParser = progressiveJpegParser;
            Objects.requireNonNull(progressiveJpegConfig);
            this.mProgressiveJpegConfig = progressiveJpegConfig;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return this.mProgressiveJpegParser.mBestScanEndOffset;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public QualityInfo getQualityInfo() {
            return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.mBestScanNumber);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            boolean updateJob = this.mJobScheduler.updateJob(encodedImage, i);
            if ((BaseConsumer.isNotLast(i) || BaseConsumer.statusHasFlag(i, 8)) && !BaseConsumer.statusHasFlag(i, 4) && EncodedImage.isValid(encodedImage)) {
                encodedImage.parseMetaDataIfNeeded();
                if (encodedImage.mImageFormat == DefaultImageFormats.JPEG) {
                    if (!this.mProgressiveJpegParser.parseMoreData(encodedImage)) {
                        return false;
                    }
                    int i2 = this.mProgressiveJpegParser.mBestScanNumber;
                    int i3 = this.mLastScheduledScanNumber;
                    if (i2 <= i3) {
                        return false;
                    }
                    if (i2 < this.mProgressiveJpegConfig.getNextScanNumberToDecode(i3) && !this.mProgressiveJpegParser.mEndMarkerRead) {
                        return false;
                    }
                    this.mLastScheduledScanNumber = i2;
                }
            }
            return updateJob;
        }
    }

    /* loaded from: classes.dex */
    public abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        public final ImageDecodeOptions mImageDecodeOptions;
        public boolean mIsFinished = false;
        public final JobScheduler mJobScheduler;
        public final ProducerContext mProducerContext;
        public final RequestListener mProducerListener;

        public ProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerListener = producerContext.getListener();
            ImageDecodeOptions imageDecodeOptions = producerContext.getImageRequest().mImageDecodeOptions;
            this.mImageDecodeOptions = imageDecodeOptions;
            this.mJobScheduler = new JobScheduler(DecodeProducer.this.mExecutor, new JobScheduler.JobRunnable(DecodeProducer.this, producerContext, i) { // from class: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.1
                public final /* synthetic */ int val$maxBitmapSize;
                public final /* synthetic */ ProducerContext val$producerContext;

                {
                    this.val$producerContext = producerContext;
                    this.val$maxBitmapSize = i;
                }

                /* JADX WARN: Can't wrap try/catch for region: R(19:85|38|cd|43|(14:47|(12:52|54|80|55|83|56|57|(1:59)|60|61|70|87)|53|54|80|55|83|56|57|(0)|60|61|70|87)|48|(12:52|54|80|55|83|56|57|(0)|60|61|70|87)|53|54|80|55|83|56|57|(0)|60|61|70|87) */
                /* JADX WARN: Code restructure failed: missing block: B:62:0x0134, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:63:0x0136, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:67:0x0167, code lost:
                    r1 = null;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:69:0x016b, code lost:
                    r3.mProducerListener.onProducerFinishWithFailure(r3.mProducerContext.getId(), "DecodeProducer", r0, r3.getExtraMap(r1, r6, r5, r9, r4, r11, r8, r13));
                    r3.maybeFinish(true);
                    r3.mConsumer.onFailure(r0);
                 */
                /* JADX WARN: Removed duplicated region for block: B:59:0x011a  */
                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run(com.facebook.imagepipeline.image.EncodedImage r20, int r21) {
                    /*
                        Method dump skipped, instructions count: 406
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.AnonymousClass1.run(com.facebook.imagepipeline.image.EncodedImage, int):void");
                }
            }, imageDecodeOptions.minDecodeIntervalMs);
            producerContext.addCallbacks(new BaseProducerContextCallbacks(DecodeProducer.this, z) { // from class: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.2
                public final /* synthetic */ boolean val$decodeCancellationEnabled;

                {
                    this.val$decodeCancellationEnabled = z;
                }

                @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (this.val$decodeCancellationEnabled) {
                        ProgressiveDecoder progressiveDecoder = ProgressiveDecoder.this;
                        progressiveDecoder.maybeFinish(true);
                        progressiveDecoder.mConsumer.onCancellation();
                    }
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (ProgressiveDecoder.this.mProducerContext.isIntermediateResultExpected()) {
                        ProgressiveDecoder.this.mJobScheduler.scheduleJob();
                    }
                }
            });
        }

        public final Map<String, String> getExtraMap(CloseableImage closeableImage, long j, QualityInfo qualityInfo, boolean z, String str, String str2, String str3, String str4) {
            if (!this.mProducerListener.requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(((ImmutableQualityInfo) qualityInfo).mIsOfGoodEnoughQuality);
            String valueOf3 = String.valueOf(z);
            if (closeableImage instanceof CloseableStaticBitmap) {
                Bitmap bitmap = ((CloseableStaticBitmap) closeableImage).mBitmap;
                HashMap hashMap = new HashMap(8);
                hashMap.put("bitmapSize", bitmap.getWidth() + "x" + bitmap.getHeight());
                hashMap.put("queueTime", valueOf);
                hashMap.put("hasGoodQuality", valueOf2);
                hashMap.put("isFinal", valueOf3);
                hashMap.put("encodedImageSize", str2);
                hashMap.put("imageFormat", str);
                hashMap.put("requestedImageSize", str3);
                hashMap.put("sampleSize", str4);
                return new ImmutableMap(hashMap);
            }
            HashMap hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put("hasGoodQuality", valueOf2);
            hashMap2.put("isFinal", valueOf3);
            hashMap2.put("encodedImageSize", str2);
            hashMap2.put("imageFormat", str);
            hashMap2.put("requestedImageSize", str3);
            hashMap2.put("sampleSize", str4);
            return new ImmutableMap(hashMap2);
        }

        public abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        public abstract QualityInfo getQualityInfo();

        public final void handleCancellation() {
            maybeFinish(true);
            this.mConsumer.onCancellation();
        }

        public final void handleResult(CloseableImage closeableImage, int i) {
            CloseableReference.LeakHandler leakHandler = DecodeProducer.this.mCloseableReferenceFactory.mLeakHandler;
            Class<CloseableReference> cls = CloseableReference.TAG;
            CloseableReference closeableReference = null;
            Throwable th = null;
            if (closeableImage != null) {
                ResourceReleaser<Closeable> resourceReleaser = CloseableReference.DEFAULT_CLOSEABLE_RELEASER;
                if (leakHandler.requiresStacktrace()) {
                    th = new Throwable();
                }
                closeableReference = new CloseableReference(closeableImage, resourceReleaser, leakHandler, th);
            }
            try {
                maybeFinish(BaseConsumer.isLast(i));
                this.mConsumer.onNewResult(closeableReference, i);
            } finally {
                if (closeableReference != null) {
                    closeableReference.close();
                }
            }
        }

        public final void maybeFinish(boolean z) {
            synchronized (this) {
                if (z) {
                    if (!this.mIsFinished) {
                        this.mConsumer.onProgressUpdate(1.0f);
                        this.mIsFinished = true;
                        this.mJobScheduler.clearJob();
                    }
                }
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            handleCancellation();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            maybeFinish(true);
            this.mConsumer.onFailure(th);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            try {
                FrescoSystrace.isTracing();
                boolean isLast = BaseConsumer.isLast(i);
                if (isLast && !EncodedImage.isValid(encodedImage)) {
                    ExceptionWithNoStacktrace exceptionWithNoStacktrace = new ExceptionWithNoStacktrace("Encoded image is not valid.");
                    maybeFinish(true);
                    this.mConsumer.onFailure(exceptionWithNoStacktrace);
                } else if (updateDecodeJob(encodedImage, i)) {
                    boolean statusHasFlag = BaseConsumer.statusHasFlag(i, 4);
                    if (isLast || statusHasFlag || this.mProducerContext.isIntermediateResultExpected()) {
                        this.mJobScheduler.scheduleJob();
                    }
                }
            } finally {
                FrescoSystrace.isTracing();
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onProgressUpdateImpl(float f) {
            this.mConsumer.onProgressUpdate(f * 0.99f);
        }

        public abstract boolean updateDecodeJob(EncodedImage encodedImage, int i);
    }

    public DecodeProducer(ByteArrayPool byteArrayPool, Executor executor, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, Producer<EncodedImage> producer, int i, CloseableReferenceFactory closeableReferenceFactory) {
        Objects.requireNonNull(byteArrayPool);
        this.mByteArrayPool = byteArrayPool;
        Objects.requireNonNull(executor);
        this.mExecutor = executor;
        Objects.requireNonNull(imageDecoder);
        this.mImageDecoder = imageDecoder;
        Objects.requireNonNull(progressiveJpegConfig);
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        this.mDownsampleEnabled = z;
        this.mDownsampleEnabledForNetwork = z2;
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
        this.mDecodeCancellationEnabled = z3;
        this.mMaxBitmapSize = i;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer<EncodedImage> consumer2;
        try {
            FrescoSystrace.isTracing();
            if (!UriUtil.isNetworkUri(producerContext.getImageRequest().mSourceUri)) {
                consumer2 = new LocalImagesProgressiveDecoder(this, consumer, producerContext, this.mDecodeCancellationEnabled, this.mMaxBitmapSize);
            } else {
                consumer2 = new NetworkImagesProgressiveDecoder(this, consumer, producerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig, this.mDecodeCancellationEnabled, this.mMaxBitmapSize);
            }
            this.mInputProducer.produceResults(consumer2, producerContext);
        } finally {
            FrescoSystrace.isTracing();
        }
    }
}
