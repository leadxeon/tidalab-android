package com.tidalab.v2board.clash.design.model;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Date;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.MatcherMatchResult;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
/* compiled from: LogFile.kt */
/* loaded from: classes.dex */
public final class LogFile {
    public static final LogFile Companion = null;
    public static final Regex REGEX_FILE = new Regex("clash-(\\d+).log");
    public final Date date;
    public final String fileName;

    public LogFile(String str, Date date) {
        this.fileName = str;
        this.date = date;
    }

    public static final LogFile parseFromFileName(String str) {
        MatchResult matchEntire = REGEX_FILE.matchEntire(str);
        if (matchEntire == null) {
            return null;
        }
        final MatcherMatchResult matcherMatchResult = (MatcherMatchResult) matchEntire;
        if (matcherMatchResult.groupValues_ == null) {
            matcherMatchResult.groupValues_ = new AbstractList<String>() { // from class: kotlin.text.MatcherMatchResult$groupValues$1
                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public final /* bridge */ boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return super.contains((String) obj);
                    }
                    return false;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public Object get(int i) {
                    String group = MatcherMatchResult.this.matcher.group(i);
                    return group != null ? group : HttpUrl.FRAGMENT_ENCODE_SET;
                }

                @Override // kotlin.collections.AbstractCollection
                public int getSize() {
                    return MatcherMatchResult.this.matcher.groupCount() + 1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return super.indexOf((String) obj);
                    }
                    return -1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return super.lastIndexOf((String) obj);
                    }
                    return -1;
                }
            };
        }
        return new LogFile(str, new Date(Long.parseLong(matcherMatchResult.groupValues_.get(1))));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogFile)) {
            return false;
        }
        LogFile logFile = (LogFile) obj;
        return Intrinsics.areEqual(this.fileName, logFile.fileName) && Intrinsics.areEqual(this.date, logFile.date);
    }

    public int hashCode() {
        return this.date.hashCode() + (this.fileName.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("LogFile(fileName=");
        outline13.append(this.fileName);
        outline13.append(", date=");
        outline13.append(this.date);
        outline13.append(')');
        return outline13.toString();
    }
}
