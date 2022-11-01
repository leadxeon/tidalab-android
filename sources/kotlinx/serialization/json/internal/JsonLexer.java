package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: JsonLexer.kt */
/* loaded from: classes.dex */
public final class JsonLexer {
    public int currentPosition;
    public StringBuilder escapedString = new StringBuilder();
    public String peekedString;
    public final String source;

    public JsonLexer(String str) {
        this.source = str;
    }

    public static Void fail$default(JsonLexer jsonLexer, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = jsonLexer.currentPosition;
        }
        throw InputKt.JsonDecodingException(i, str, jsonLexer.source);
    }

    public final boolean canConsumeValue() {
        int i = this.currentPosition;
        while (true) {
            boolean z = false;
            if (i < this.source.length()) {
                char charAt = this.source.charAt(i);
                if (charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t') {
                    i++;
                } else {
                    this.currentPosition = i;
                    if (((charAt == '}' || charAt == ']') || charAt == ':') || charAt == ',') {
                        z = true;
                    }
                    return !z;
                }
            } else {
                this.currentPosition = i;
                return false;
            }
        }
    }

    public final boolean consumeBoolean(int i) {
        if (i == this.source.length()) {
            throw InputKt.JsonDecodingException(this.currentPosition, "EOF", this.source);
        }
        int i2 = i + 1;
        int charAt = this.source.charAt(i) | ' ';
        if (charAt == 102) {
            consumeBooleanLiteral("alse", i2);
            return false;
        } else if (charAt != 116) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected valid boolean literal prefix, but had '");
            outline13.append(consumeStringLenient());
            outline13.append('\'');
            throw InputKt.JsonDecodingException(this.currentPosition, outline13.toString(), this.source);
        } else {
            consumeBooleanLiteral("rue", i2);
            return true;
        }
    }

    public final void consumeBooleanLiteral(String str, int i) {
        if (this.source.length() - i >= str.length()) {
            int i2 = 0;
            int length = str.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i3 = i2 + 1;
                    if (str.charAt(i2) != (this.source.charAt(i2 + i) | ' ')) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected valid boolean literal prefix, but had '");
                        outline13.append(consumeStringLenient());
                        outline13.append('\'');
                        throw InputKt.JsonDecodingException(this.currentPosition, outline13.toString(), this.source);
                    } else if (i3 > length) {
                        break;
                    } else {
                        i2 = i3;
                    }
                }
            }
            this.currentPosition = str.length() + i;
            return;
        }
        throw InputKt.JsonDecodingException(this.currentPosition, "Unexpected end of boolean literal", this.source);
    }

    public final String consumeKeyString() {
        String str;
        consumeNextToken('\"');
        int i = this.currentPosition;
        int indexOf$default = StringsKt__IndentKt.indexOf$default((CharSequence) this.source, '\"', i, false, 4);
        if (indexOf$default != -1) {
            if (i < indexOf$default) {
                int i2 = i;
                while (true) {
                    int i3 = i2 + 1;
                    if (this.source.charAt(i2) == '\\') {
                        int i4 = this.currentPosition;
                        String str2 = this.source;
                        char charAt = str2.charAt(i2);
                        int i5 = i4;
                        while (charAt != '\"') {
                            if (charAt == '\\') {
                                this.escapedString.append((CharSequence) this.source, i5, i2);
                                int i6 = i2 + 1;
                                i5 = i6 + 1;
                                char charAt2 = this.source.charAt(i6);
                                if (charAt2 == 'u') {
                                    String str3 = this.source;
                                    int i7 = i5 + 4;
                                    if (i7 < str3.length()) {
                                        this.escapedString.append((char) (fromHexChar(str3, i5 + 3) + (fromHexChar(str3, i5) << 12) + (fromHexChar(str3, i5 + 1) << 8) + (fromHexChar(str3, i5 + 2) << 4)));
                                        i5 = i7;
                                    } else {
                                        throw InputKt.JsonDecodingException(this.currentPosition, "Unexpected EOF during unicode escape", this.source);
                                    }
                                } else {
                                    char c = charAt2 < 'u' ? CharMappings.ESCAPE_2_CHAR[charAt2] : (char) 0;
                                    if (c != 0) {
                                        this.escapedString.append(c);
                                    } else {
                                        throw InputKt.JsonDecodingException(this.currentPosition, "Invalid escaped char '" + charAt2 + '\'', this.source);
                                    }
                                }
                                i2 = i5;
                            } else {
                                i2++;
                                if (i2 >= str2.length()) {
                                    throw InputKt.JsonDecodingException(i2, "EOF", this.source);
                                }
                            }
                            charAt = str2.charAt(i2);
                        }
                        if (i5 == i4) {
                            str = str2.substring(i5, i2);
                        } else {
                            this.escapedString.append((CharSequence) this.source, i5, i2);
                            str = this.escapedString.toString();
                            this.escapedString.setLength(0);
                        }
                        this.currentPosition = i2 + 1;
                        return str;
                    } else if (i3 >= indexOf$default) {
                        break;
                    } else {
                        i2 = i3;
                    }
                }
            }
            this.currentPosition = indexOf$default + 1;
            String str4 = this.source;
            Objects.requireNonNull(str4, "null cannot be cast to non-null type java.lang.String");
            return str4.substring(i, indexOf$default);
        }
        fail((byte) 1);
        throw null;
    }

    public final byte consumeNextToken(byte b) {
        byte consumeNextToken = consumeNextToken();
        if (consumeNextToken == b) {
            return consumeNextToken;
        }
        fail(b);
        throw null;
    }

    public final long consumeNumericLiteral() {
        boolean z;
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces != this.source.length()) {
            if (this.source.charAt(skipWhitespaces) == '\"') {
                skipWhitespaces++;
                if (skipWhitespaces != this.source.length()) {
                    z = true;
                } else {
                    throw InputKt.JsonDecodingException(this.currentPosition, "EOF", this.source);
                }
            } else {
                z = false;
            }
            int i = skipWhitespaces;
            boolean z2 = true;
            boolean z3 = false;
            long j = 0;
            while (z2) {
                char charAt = this.source.charAt(i);
                if (charAt == '-') {
                    if (i == skipWhitespaces) {
                        i++;
                        z3 = true;
                    } else {
                        throw InputKt.JsonDecodingException(this.currentPosition, "Unexpected symbol '-' in numeric literal", this.source);
                    }
                } else if (InputKt.charToTokenClass(charAt) != 0) {
                    break;
                } else {
                    i++;
                    z2 = i != this.source.length();
                    int i2 = charAt - '0';
                    if (i2 >= 0 && i2 <= 9) {
                        j = (j * 10) - i2;
                        if (j > 0) {
                            throw InputKt.JsonDecodingException(this.currentPosition, "Numeric value overflow", this.source);
                        }
                    } else {
                        throw InputKt.JsonDecodingException(this.currentPosition, "Unexpected symbol '" + charAt + "' in numeric literal", this.source);
                    }
                }
            }
            if (skipWhitespaces == i || (z3 && skipWhitespaces == i - 1)) {
                throw InputKt.JsonDecodingException(this.currentPosition, "Expected numeric literal", this.source);
            }
            if (z) {
                if (!z2) {
                    throw InputKt.JsonDecodingException(this.currentPosition, "EOF", this.source);
                } else if (this.source.charAt(i) == '\"') {
                    i++;
                } else {
                    throw InputKt.JsonDecodingException(this.currentPosition, "Expected closing quotation mark", this.source);
                }
            }
            this.currentPosition = i;
            if (z3) {
                return j;
            }
            if (j != Long.MIN_VALUE) {
                return -j;
            }
            throw InputKt.JsonDecodingException(i, "Numeric value overflow", this.source);
        }
        throw InputKt.JsonDecodingException(this.currentPosition, "EOF", this.source);
    }

    public final String consumeString() {
        String str = this.peekedString;
        if (str == null) {
            return consumeKeyString();
        }
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenient() {
        String str = this.peekedString;
        if (str != null) {
            this.peekedString = null;
            return str;
        }
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces < this.source.length()) {
            byte charToTokenClass = InputKt.charToTokenClass(this.source.charAt(skipWhitespaces));
            if (charToTokenClass == 1) {
                return consumeString();
            }
            if (charToTokenClass == 0) {
                while (skipWhitespaces < this.source.length() && InputKt.charToTokenClass(this.source.charAt(skipWhitespaces)) == 0) {
                    skipWhitespaces++;
                }
                String str2 = this.source;
                int i = this.currentPosition;
                Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
                String substring = str2.substring(i, skipWhitespaces);
                this.currentPosition = skipWhitespaces;
                return substring;
            }
            throw InputKt.JsonDecodingException(this.currentPosition, Intrinsics.stringPlus("Expected beginning of the string, but got ", Character.valueOf(this.source.charAt(skipWhitespaces))), this.source);
        }
        throw InputKt.JsonDecodingException(skipWhitespaces, "EOF", this.source);
    }

    public final void fail(byte b) {
        int i;
        String str = b == 1 ? "quotation mark '\"'" : b == 4 ? "comma ','" : b == 5 ? "semicolon ':'" : b == 6 ? "start of the object '{'" : b == 7 ? "end of the object '}'" : b == 8 ? "start of the array '['" : b == 9 ? "end of the array ']'" : "valid token";
        String valueOf = (this.currentPosition == this.source.length() || (i = this.currentPosition) <= 0) ? "EOF" : String.valueOf(this.source.charAt(i - 1));
        fail("Expected " + str + ", but had '" + valueOf + "' instead", this.currentPosition - 1);
        throw null;
    }

    public final int fromHexChar(String str, int i) {
        char charAt = str.charAt(i);
        boolean z = false;
        if ('0' <= charAt && charAt <= '9') {
            return charAt - '0';
        }
        char c = 'a';
        if (!('a' <= charAt && charAt <= 'f')) {
            c = 'A';
            if ('A' <= charAt && charAt <= 'F') {
                z = true;
            }
            if (!z) {
                throw InputKt.JsonDecodingException(this.currentPosition, "Invalid toHexChar char '" + charAt + "' in unicode escape", this.source);
            }
        }
        return (charAt - c) + 10;
    }

    public final byte peekNextToken() {
        String str = this.source;
        while (this.currentPosition < str.length()) {
            char charAt = str.charAt(this.currentPosition);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                return InputKt.charToTokenClass(charAt);
            }
            this.currentPosition++;
        }
        return (byte) 10;
    }

    public final int skipWhitespaces() {
        char charAt;
        int i = this.currentPosition;
        while (i < this.source.length() && ((charAt = this.source.charAt(i)) == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t')) {
            i++;
        }
        this.currentPosition = i;
        return i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("JsonReader(source='");
        outline13.append(this.source);
        outline13.append("', currentPosition=");
        outline13.append(this.currentPosition);
        outline13.append(')');
        return outline13.toString();
    }

    public final boolean tryConsumeComma() {
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces == this.source.length() || this.source.charAt(skipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        return true;
    }

    public final boolean tryConsumeNotNull() {
        int skipWhitespaces = skipWhitespaces();
        if (this.source.length() - skipWhitespaces < 4) {
            return true;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if ("null".charAt(i) != this.source.charAt(i + skipWhitespaces)) {
                return true;
            }
            if (i2 > 3) {
                this.currentPosition = skipWhitespaces + 4;
                return false;
            }
            i = i2;
        }
    }

    public final void unexpectedToken(char c) {
        this.currentPosition--;
        if (c != '\"' || !Intrinsics.areEqual(consumeStringLenient(), "null")) {
            fail(InputKt.charToTokenClass(c));
            throw null;
        }
        throw InputKt.JsonDecodingException(this.currentPosition - 4, "Expected string literal but 'null' literal was found.\nUse 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.", this.source);
    }

    public final void consumeNextToken(char c) {
        String str = this.source;
        while (this.currentPosition < str.length()) {
            int i = this.currentPosition;
            this.currentPosition = i + 1;
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                if (charAt != c) {
                    unexpectedToken(c);
                    throw null;
                }
                return;
            }
        }
        unexpectedToken(c);
        throw null;
    }

    public final Void fail(String str, int i) {
        throw InputKt.JsonDecodingException(i, str, this.source);
    }

    public final byte consumeNextToken() {
        String str = this.source;
        while (this.currentPosition < str.length()) {
            int i = this.currentPosition;
            this.currentPosition = i + 1;
            byte charToTokenClass = InputKt.charToTokenClass(str.charAt(i));
            if (charToTokenClass != 3) {
                return charToTokenClass;
            }
        }
        return (byte) 10;
    }
}
