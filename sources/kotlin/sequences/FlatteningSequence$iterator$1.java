package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class FlatteningSequence$iterator$1 implements Iterator<E>, KMappedMarker {
    public Iterator<? extends E> itemIterator;
    public final Iterator<T> iterator;
    public final /* synthetic */ FlatteningSequence this$0;

    public FlatteningSequence$iterator$1(FlatteningSequence flatteningSequence) {
        this.this$0 = flatteningSequence;
        this.iterator = flatteningSequence.sequence.iterator();
    }

    public final boolean ensureItemIterator() {
        Iterator<? extends E> it = this.itemIterator;
        if (it != 0 && !it.hasNext()) {
            this.itemIterator = null;
        }
        while (true) {
            if (this.itemIterator == null) {
                if (this.iterator.hasNext()) {
                    Object next = this.iterator.next();
                    Objects.requireNonNull(this.this$0);
                    Iterator<? extends E> it2 = (Iterator) SequencesKt___SequencesKt$flatMap$2.INSTANCE.invoke(this.this$0.transformer.invoke(next));
                    if (it2.hasNext()) {
                        this.itemIterator = it2;
                        break;
                    }
                } else {
                    return false;
                }
            } else {
                break;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return ensureItemIterator();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [E, java.lang.Object] */
    @Override // java.util.Iterator
    public E next() {
        if (ensureItemIterator()) {
            return this.itemIterator.next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
