package com.facebook.common.internal;

import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public final class Objects$ToStringHelper {
    public final String className;
    public ValueHolder holderHead;
    public ValueHolder holderTail;

    /* loaded from: classes.dex */
    public static final class ValueHolder {
        public String name;
        public ValueHolder next;
        public Object value;

        public ValueHolder(Objects$1 objects$1) {
        }
    }

    public Objects$ToStringHelper(String str, Objects$1 objects$1) {
        ValueHolder valueHolder = new ValueHolder(null);
        this.holderHead = valueHolder;
        this.holderTail = valueHolder;
        Objects.requireNonNull(str);
        this.className = str;
    }

    public Objects$ToStringHelper add(String str, boolean z) {
        addHolder(str, String.valueOf(z));
        return this;
    }

    public final Objects$ToStringHelper addHolder(String str, Object obj) {
        ValueHolder valueHolder = new ValueHolder(null);
        this.holderTail.next = valueHolder;
        this.holderTail = valueHolder;
        valueHolder.value = obj;
        Objects.requireNonNull(str);
        valueHolder.name = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append('{');
        ValueHolder valueHolder = this.holderHead.next;
        String str = HttpUrl.FRAGMENT_ENCODE_SET;
        while (valueHolder != null) {
            sb.append(str);
            String str2 = valueHolder.name;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            sb.append(valueHolder.value);
            valueHolder = valueHolder.next;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
