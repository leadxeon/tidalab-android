package kotlinx.serialization.json.internal;

import kotlinx.serialization.json.Json;
/* compiled from: Composers.kt */
/* loaded from: classes.dex */
public class Composer {
    public final Json json;
    public int level;
    public final JsonStringBuilder sb;
    public boolean writingFirst = true;

    public Composer(JsonStringBuilder jsonStringBuilder, Json json) {
        this.sb = jsonStringBuilder;
        this.json = json;
    }

    public final void nextItem() {
        this.writingFirst = false;
        if (this.json.configuration.prettyPrint) {
            this.sb.append("\n");
            int i = this.level;
            for (int i2 = 0; i2 < i; i2++) {
                this.sb.append(this.json.configuration.prettyPrintIndent);
            }
        }
    }

    public final void print(char c) {
        JsonStringBuilder jsonStringBuilder = this.sb;
        jsonStringBuilder.ensureAdditionalCapacity(1);
        char[] cArr = jsonStringBuilder.array;
        int i = jsonStringBuilder.size;
        jsonStringBuilder.size = i + 1;
        cArr[i] = c;
    }

    public final void space() {
        if (this.json.configuration.prettyPrint) {
            print(' ');
        }
    }

    public void print(byte b) {
        this.sb.append(b);
    }

    public void print(short s) {
        this.sb.append(s);
    }

    public void print(int i) {
        this.sb.append(i);
    }

    public void print(long j) {
        this.sb.append(j);
    }
}
