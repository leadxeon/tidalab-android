package com.facebook.imagepipeline.producers;

import android.util.Pair;
import androidx.recyclerview.R$dimen;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public abstract class MultiplexProducer<K, T extends Closeable> implements Producer<T> {
    public final Producer<T> mInputProducer;
    public final Map<K, MultiplexProducer<K, T>.Multiplexer> mMultiplexers = new HashMap();

    /* loaded from: classes.dex */
    public class Multiplexer {
        public final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> mConsumerContextPairs = new CopyOnWriteArraySet<>();
        public MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer mForwardingConsumer;
        public final K mKey;
        public T mLastIntermediateResult;
        public float mLastProgress;
        public int mLastStatus;
        public BaseProducerContext mMultiplexProducerContext;

        /* loaded from: classes.dex */
        public class ForwardingConsumer extends BaseConsumer<T> {
            public ForwardingConsumer(AnonymousClass1 r2) {
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onCancellationImpl() {
                try {
                    FrescoSystrace.isTracing();
                    Multiplexer multiplexer = Multiplexer.this;
                    synchronized (multiplexer) {
                        if (multiplexer.mForwardingConsumer == this) {
                            multiplexer.mForwardingConsumer = null;
                            multiplexer.mMultiplexProducerContext = null;
                            multiplexer.closeSafely(multiplexer.mLastIntermediateResult);
                            multiplexer.mLastIntermediateResult = null;
                            multiplexer.startInputProducerIfHasAttachedConsumers();
                        }
                    }
                } finally {
                    FrescoSystrace.isTracing();
                }
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onFailureImpl(Throwable th) {
                try {
                    FrescoSystrace.isTracing();
                    Multiplexer.this.onFailure(this, th);
                } finally {
                    FrescoSystrace.isTracing();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(Object obj, int i) {
                Closeable closeable = (Closeable) obj;
                try {
                    FrescoSystrace.isTracing();
                    Multiplexer.this.onNextResult(this, closeable, i);
                } finally {
                    FrescoSystrace.isTracing();
                }
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onProgressUpdateImpl(float f) {
                try {
                    FrescoSystrace.isTracing();
                    Multiplexer.this.onProgressUpdate(this, f);
                } finally {
                    FrescoSystrace.isTracing();
                }
            }
        }

        public Multiplexer(K k) {
            this.mKey = k;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean addNewConsumer(Consumer<T> consumer, ProducerContext producerContext) {
            MultiplexProducer<K, T>.Multiplexer multiplexer;
            final Pair<Consumer<T>, ProducerContext> create = Pair.create(consumer, producerContext);
            synchronized (this) {
                MultiplexProducer multiplexProducer = MultiplexProducer.this;
                K k = this.mKey;
                synchronized (multiplexProducer) {
                    multiplexer = multiplexProducer.mMultiplexers.get(k);
                }
                if (multiplexer != this) {
                    return false;
                }
                this.mConsumerContextPairs.add(create);
                List<ProducerContextCallbacks> updateIsPrefetch = updateIsPrefetch();
                List<ProducerContextCallbacks> updatePriority = updatePriority();
                List<ProducerContextCallbacks> updateIsIntermediateResultExpected = updateIsIntermediateResultExpected();
                Closeable closeable = this.mLastIntermediateResult;
                float f = this.mLastProgress;
                int i = this.mLastStatus;
                BaseProducerContext.callOnIsPrefetchChanged(updateIsPrefetch);
                BaseProducerContext.callOnPriorityChanged(updatePriority);
                BaseProducerContext.callOnIsIntermediateResultExpectedChanged(updateIsIntermediateResultExpected);
                synchronized (create) {
                    synchronized (this) {
                        if (closeable != this.mLastIntermediateResult) {
                            closeable = null;
                        } else if (closeable != null) {
                            closeable = MultiplexProducer.this.cloneOrNull(closeable);
                        }
                    }
                    if (closeable != null) {
                        if (f > 0.0f) {
                            consumer.onProgressUpdate(f);
                        }
                        consumer.onNewResult(closeable, i);
                        closeSafely(closeable);
                    }
                }
                producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.1
                    @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onCancellationRequested() {
                        boolean remove;
                        List<ProducerContextCallbacks> list;
                        List<ProducerContextCallbacks> list2;
                        List<ProducerContextCallbacks> list3;
                        BaseProducerContext baseProducerContext;
                        synchronized (Multiplexer.this) {
                            remove = Multiplexer.this.mConsumerContextPairs.remove(create);
                            list = null;
                            if (!remove) {
                                baseProducerContext = null;
                            } else if (Multiplexer.this.mConsumerContextPairs.isEmpty()) {
                                baseProducerContext = Multiplexer.this.mMultiplexProducerContext;
                            } else {
                                List<ProducerContextCallbacks> updateIsPrefetch2 = Multiplexer.this.updateIsPrefetch();
                                list3 = Multiplexer.this.updatePriority();
                                list2 = Multiplexer.this.updateIsIntermediateResultExpected();
                                baseProducerContext = null;
                                list = updateIsPrefetch2;
                            }
                            list3 = null;
                            list2 = null;
                        }
                        BaseProducerContext.callOnIsPrefetchChanged(list);
                        BaseProducerContext.callOnPriorityChanged(list3);
                        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(list2);
                        if (baseProducerContext != null) {
                            baseProducerContext.cancel();
                        }
                        if (remove) {
                            ((Consumer) create.first).onCancellation();
                        }
                    }

                    @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onIsIntermediateResultExpectedChanged() {
                        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(Multiplexer.this.updateIsIntermediateResultExpected());
                    }

                    @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onIsPrefetchChanged() {
                        BaseProducerContext.callOnIsPrefetchChanged(Multiplexer.this.updateIsPrefetch());
                    }

                    @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onPriorityChanged() {
                        BaseProducerContext.callOnPriorityChanged(Multiplexer.this.updatePriority());
                    }
                });
                return true;
            }
        }

        public final void closeSafely(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public final synchronized boolean computeIsIntermediateResultExpected() {
            Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (((ProducerContext) it.next().second).isIntermediateResultExpected()) {
                    return true;
                }
            }
            return false;
        }

        public final synchronized boolean computeIsPrefetch() {
            Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (!((ProducerContext) it.next().second).isPrefetch()) {
                    return false;
                }
            }
            return true;
        }

        public final synchronized Priority computePriority() {
            Priority priority;
            priority = Priority.LOW;
            Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                Priority priority2 = ((ProducerContext) it.next().second).getPriority();
                if (priority == null || (priority2 != null && priority.ordinal() <= priority2.ordinal())) {
                    priority = priority2;
                }
            }
            return priority;
        }

        public void onFailure(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer, Throwable th) {
            synchronized (this) {
                if (this.mForwardingConsumer == forwardingConsumer) {
                    Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
                    this.mConsumerContextPairs.clear();
                    MultiplexProducer.access$700(MultiplexProducer.this, this.mKey, this);
                    closeSafely(this.mLastIntermediateResult);
                    this.mLastIntermediateResult = null;
                    while (it.hasNext()) {
                        Pair<Consumer<T>, ProducerContext> next = it.next();
                        synchronized (next) {
                            ((Consumer) next.first).onFailure(th);
                        }
                    }
                }
            }
        }

        public void onNextResult(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer, T t, int i) {
            synchronized (this) {
                if (this.mForwardingConsumer == forwardingConsumer) {
                    closeSafely(this.mLastIntermediateResult);
                    this.mLastIntermediateResult = null;
                    Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
                    if (BaseConsumer.isNotLast(i)) {
                        this.mLastIntermediateResult = (T) MultiplexProducer.this.cloneOrNull(t);
                        this.mLastStatus = i;
                    } else {
                        this.mConsumerContextPairs.clear();
                        MultiplexProducer.access$700(MultiplexProducer.this, this.mKey, this);
                    }
                    while (it.hasNext()) {
                        Pair<Consumer<T>, ProducerContext> next = it.next();
                        synchronized (next) {
                            ((Consumer) next.first).onNewResult(t, i);
                        }
                    }
                }
            }
        }

        public void onProgressUpdate(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer, float f) {
            synchronized (this) {
                if (this.mForwardingConsumer == forwardingConsumer) {
                    this.mLastProgress = f;
                    Iterator<Pair<Consumer<T>, ProducerContext>> it = this.mConsumerContextPairs.iterator();
                    while (it.hasNext()) {
                        Pair<Consumer<T>, ProducerContext> next = it.next();
                        synchronized (next) {
                            ((Consumer) next.first).onProgressUpdate(f);
                        }
                    }
                }
            }
        }

        public final void startInputProducerIfHasAttachedConsumers() {
            synchronized (this) {
                boolean z = true;
                R$dimen.checkArgument(this.mMultiplexProducerContext == null);
                if (this.mForwardingConsumer != null) {
                    z = false;
                }
                R$dimen.checkArgument(z);
                if (this.mConsumerContextPairs.isEmpty()) {
                    MultiplexProducer.access$700(MultiplexProducer.this, this.mKey, this);
                    return;
                }
                ProducerContext producerContext = (ProducerContext) this.mConsumerContextPairs.iterator().next().second;
                BaseProducerContext baseProducerContext = new BaseProducerContext(producerContext.getImageRequest(), producerContext.getId(), producerContext.getListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority());
                this.mMultiplexProducerContext = baseProducerContext;
                MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer = new ForwardingConsumer(null);
                this.mForwardingConsumer = forwardingConsumer;
                MultiplexProducer.this.mInputProducer.produceResults(forwardingConsumer, baseProducerContext);
            }
        }

        public final synchronized List<ProducerContextCallbacks> updateIsIntermediateResultExpected() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            ArrayList arrayList = null;
            if (baseProducerContext == null) {
                return null;
            }
            boolean computeIsIntermediateResultExpected = computeIsIntermediateResultExpected();
            synchronized (baseProducerContext) {
                if (computeIsIntermediateResultExpected != baseProducerContext.mIsIntermediateResultExpected) {
                    baseProducerContext.mIsIntermediateResultExpected = computeIsIntermediateResultExpected;
                    arrayList = new ArrayList(baseProducerContext.mCallbacks);
                }
            }
            return arrayList;
        }

        public final synchronized List<ProducerContextCallbacks> updateIsPrefetch() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            ArrayList arrayList = null;
            if (baseProducerContext == null) {
                return null;
            }
            boolean computeIsPrefetch = computeIsPrefetch();
            synchronized (baseProducerContext) {
                if (computeIsPrefetch != baseProducerContext.mIsPrefetch) {
                    baseProducerContext.mIsPrefetch = computeIsPrefetch;
                    arrayList = new ArrayList(baseProducerContext.mCallbacks);
                }
            }
            return arrayList;
        }

        public final synchronized List<ProducerContextCallbacks> updatePriority() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            ArrayList arrayList = null;
            if (baseProducerContext == null) {
                return null;
            }
            Priority computePriority = computePriority();
            synchronized (baseProducerContext) {
                if (computePriority != baseProducerContext.mPriority) {
                    baseProducerContext.mPriority = computePriority;
                    arrayList = new ArrayList(baseProducerContext.mCallbacks);
                }
            }
            return arrayList;
        }
    }

    public MultiplexProducer(Producer<T> producer) {
        this.mInputProducer = producer;
    }

    public static void access$700(MultiplexProducer multiplexProducer, Object obj, Multiplexer multiplexer) {
        synchronized (multiplexProducer) {
            if (multiplexProducer.mMultiplexers.get(obj) == multiplexer) {
                multiplexProducer.mMultiplexers.remove(obj);
            }
        }
    }

    public abstract T cloneOrNull(T t);

    public abstract K getKey(ProducerContext producerContext);

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean z;
        MultiplexProducer<K, T>.Multiplexer multiplexer;
        try {
            FrescoSystrace.isTracing();
            K key = getKey(producerContext);
            do {
                z = false;
                synchronized (this) {
                    synchronized (this) {
                        multiplexer = this.mMultiplexers.get(key);
                    }
                }
                if (multiplexer == null) {
                    synchronized (this) {
                        multiplexer = new Multiplexer(key);
                        this.mMultiplexers.put(key, multiplexer);
                        z = true;
                    }
                }
            } while (!multiplexer.addNewConsumer(consumer, producerContext));
            if (z) {
                multiplexer.startInputProducerIfHasAttachedConsumers();
            }
        } finally {
            FrescoSystrace.isTracing();
        }
    }
}
