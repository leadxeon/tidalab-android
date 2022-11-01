package kotlinx.serialization.json.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
/* compiled from: JsonTreeReader.kt */
/* loaded from: classes.dex */
public final class JsonTreeReader {
    public final boolean isLenient;
    public final JsonLexer lexer;

    public JsonTreeReader(JsonConfiguration jsonConfiguration, JsonLexer jsonLexer) {
        this.lexer = jsonLexer;
        this.isLenient = jsonConfiguration.isLenient;
    }

    public final JsonElement read() {
        byte peekNextToken = this.lexer.peekNextToken();
        if (peekNextToken == 1) {
            return readValue(true);
        }
        if (peekNextToken == 0) {
            return readValue(false);
        }
        if (peekNextToken == 6) {
            byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
            if (this.lexer.peekNextToken() != 4) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (this.lexer.canConsumeValue()) {
                    String consumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
                    this.lexer.consumeNextToken((byte) 5);
                    linkedHashMap.put(consumeStringLenient, read());
                    consumeNextToken = this.lexer.consumeNextToken();
                    if (consumeNextToken != 4 && consumeNextToken != 7) {
                        JsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, 2);
                        throw null;
                    }
                }
                if (consumeNextToken == 6) {
                    this.lexer.consumeNextToken((byte) 7);
                } else if (consumeNextToken == 4) {
                    JsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2);
                    throw null;
                }
                return new JsonObject(linkedHashMap);
            }
            JsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2);
            throw null;
        } else if (peekNextToken == 8) {
            byte consumeNextToken2 = this.lexer.consumeNextToken();
            if (this.lexer.peekNextToken() != 4) {
                ArrayList arrayList = new ArrayList();
                while (this.lexer.canConsumeValue()) {
                    arrayList.add(read());
                    consumeNextToken2 = this.lexer.consumeNextToken();
                    if (consumeNextToken2 != 4) {
                        JsonLexer jsonLexer = this.lexer;
                        boolean z = consumeNextToken2 == 9;
                        int i = jsonLexer.currentPosition;
                        if (!z) {
                            throw InputKt.JsonDecodingException(i, "Expected end of the array or comma", jsonLexer.source);
                        }
                    }
                }
                if (consumeNextToken2 == 8) {
                    this.lexer.consumeNextToken((byte) 9);
                } else if (consumeNextToken2 == 4) {
                    JsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2);
                    throw null;
                }
                return new JsonArray(arrayList);
            }
            JsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2);
            throw null;
        } else {
            JsonLexer.fail$default(this.lexer, "Can't begin reading element, unexpected token", 0, 2);
            throw null;
        }
    }

    public final JsonPrimitive readValue(boolean z) {
        String str;
        if (this.isLenient || !z) {
            str = this.lexer.consumeStringLenient();
        } else {
            str = this.lexer.consumeString();
        }
        return (z || !Intrinsics.areEqual(str, "null")) ? new JsonLiteral(str, z) : JsonNull.INSTANCE;
    }
}
