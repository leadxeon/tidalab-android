package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class BucketMap<T> {
    public LinkedEntry<T> mHead;
    public final SparseArray<LinkedEntry<T>> mMap = new SparseArray<>();
    public LinkedEntry<T> mTail;

    /* loaded from: classes.dex */
    public static class LinkedEntry<I> {
        public int key;
        public LinkedList<I> value;
        public LinkedEntry<I> prev = null;
        public LinkedEntry<I> next = null;

        public LinkedEntry(LinkedEntry linkedEntry, int i, LinkedList linkedList, LinkedEntry linkedEntry2, AnonymousClass1 r5) {
            this.key = i;
            this.value = linkedList;
        }

        public String toString() {
            return GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("LinkedEntry(key: "), this.key, ")");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void moveToFront(LinkedEntry<T> linkedEntry) {
        if (this.mHead != linkedEntry) {
            prune(linkedEntry);
            LinkedEntry linkedEntry2 = (LinkedEntry<T>) this.mHead;
            if (linkedEntry2 == null) {
                this.mHead = linkedEntry;
                this.mTail = linkedEntry;
                return;
            }
            linkedEntry.next = linkedEntry2;
            linkedEntry2.prev = linkedEntry;
            this.mHead = linkedEntry;
        }
    }

    public final synchronized void prune(LinkedEntry<T> linkedEntry) {
        LinkedEntry linkedEntry2 = (LinkedEntry<T>) linkedEntry.prev;
        LinkedEntry linkedEntry3 = (LinkedEntry<T>) linkedEntry.next;
        if (linkedEntry2 != null) {
            linkedEntry2.next = linkedEntry3;
        }
        if (linkedEntry3 != null) {
            linkedEntry3.prev = linkedEntry2;
        }
        linkedEntry.prev = null;
        linkedEntry.next = null;
        if (linkedEntry == this.mHead) {
            this.mHead = linkedEntry3;
        }
        if (linkedEntry == this.mTail) {
            this.mTail = linkedEntry2;
        }
    }
}
