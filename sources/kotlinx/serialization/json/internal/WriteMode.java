package kotlinx.serialization.json.internal;
/* compiled from: WriteMode.kt */
/* loaded from: classes.dex */
public enum WriteMode {
    OBJ('{', '}'),
    LIST('[', ']'),
    MAP('{', '}'),
    POLY_OBJ('[', ']');
    
    public final char begin;
    public final char end;

    WriteMode(char c, char c2) {
        this.begin = c;
        this.end = c2;
    }
}
