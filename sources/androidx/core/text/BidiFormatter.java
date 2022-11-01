package androidx.core.text;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import java.util.Locale;
import okhttp3.HttpUrl;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public final class BidiFormatter {
    public static final BidiFormatter DEFAULT_LTR_INSTANCE;
    public static final BidiFormatter DEFAULT_RTL_INSTANCE;
    public static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    public static final String LRM_STRING = Character.toString(8206);
    public static final String RLM_STRING = Character.toString(8207);
    public final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    public final int mFlags;
    public final boolean mIsRtlContext;

    /* loaded from: classes.dex */
    public static class DirectionalityEstimator {
        public static final byte[] DIR_TYPE_CACHE = new byte[1792];
        public int charIndex;
        public char lastChar;
        public final int length;
        public final CharSequence text;

        static {
            for (int i = 0; i < 1792; i++) {
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
            }
        }

        public DirectionalityEstimator(CharSequence charSequence, boolean z) {
            this.text = charSequence;
            this.length = charSequence.length();
        }

        public byte dirTypeBackward() {
            char charAt = this.text.charAt(this.charIndex - 1);
            this.lastChar = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.charIndex--;
            char c = this.lastChar;
            return c < 1792 ? DIR_TYPE_CACHE[c] : Character.getDirectionality(c);
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_TEXT_DIRECTION_HEURISTIC = textDirectionHeuristicCompat;
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    public BidiFormatter(boolean z, int i, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = z;
        this.mFlags = i;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
        if (r3 != 0) goto L_0x0073;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
        if (r4 == 0) goto L_0x0077;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0079, code lost:
        if (r0.charIndex <= 0) goto L_0x0091;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007f, code lost:
        switch(r0.dirTypeBackward()) {
            case 14: goto L_0x008a;
            case 15: goto L_0x008a;
            case 16: goto L_0x0086;
            case 17: goto L_0x0086;
            case 18: goto L_0x0083;
            default: goto L_0x0082;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
        if (r3 != r5) goto L_0x008e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0088, code lost:
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008a, code lost:
        if (r3 != r5) goto L_0x008e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008e, code lost:
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:?, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:?, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getEntryDir(java.lang.CharSequence r9) {
        /*
            androidx.core.text.BidiFormatter$DirectionalityEstimator r0 = new androidx.core.text.BidiFormatter$DirectionalityEstimator
            r1 = 0
            r0.<init>(r9, r1)
            r0.charIndex = r1
            r9 = -1
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x000d:
            int r6 = r0.charIndex
            int r7 = r0.length
            if (r6 >= r7) goto L_0x0070
            if (r3 != 0) goto L_0x0070
            java.lang.CharSequence r7 = r0.text
            char r6 = r7.charAt(r6)
            r0.lastChar = r6
            boolean r6 = java.lang.Character.isHighSurrogate(r6)
            if (r6 == 0) goto L_0x0039
            java.lang.CharSequence r6 = r0.text
            int r7 = r0.charIndex
            int r6 = java.lang.Character.codePointAt(r6, r7)
            int r7 = r0.charIndex
            int r8 = java.lang.Character.charCount(r6)
            int r8 = r8 + r7
            r0.charIndex = r8
            byte r6 = java.lang.Character.getDirectionality(r6)
            goto L_0x004d
        L_0x0039:
            int r6 = r0.charIndex
            int r6 = r6 + r2
            r0.charIndex = r6
            char r6 = r0.lastChar
            r7 = 1792(0x700, float:2.511E-42)
            if (r6 >= r7) goto L_0x0049
            byte[] r7 = androidx.core.text.BidiFormatter.DirectionalityEstimator.DIR_TYPE_CACHE
            byte r6 = r7[r6]
            goto L_0x004d
        L_0x0049:
            byte r6 = java.lang.Character.getDirectionality(r6)
        L_0x004d:
            if (r6 == 0) goto L_0x006b
            if (r6 == r2) goto L_0x0068
            r7 = 2
            if (r6 == r7) goto L_0x0068
            r7 = 9
            if (r6 == r7) goto L_0x000d
            switch(r6) {
                case 14: goto L_0x0064;
                case 15: goto L_0x0064;
                case 16: goto L_0x0060;
                case 17: goto L_0x0060;
                case 18: goto L_0x005c;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x006e
        L_0x005c:
            int r5 = r5 + (-1)
            r4 = 0
            goto L_0x000d
        L_0x0060:
            int r5 = r5 + 1
            r4 = 1
            goto L_0x000d
        L_0x0064:
            int r5 = r5 + 1
            r4 = -1
            goto L_0x000d
        L_0x0068:
            if (r5 != 0) goto L_0x006e
            goto L_0x0088
        L_0x006b:
            if (r5 != 0) goto L_0x006e
            goto L_0x008c
        L_0x006e:
            r3 = r5
            goto L_0x000d
        L_0x0070:
            if (r3 != 0) goto L_0x0073
            goto L_0x0091
        L_0x0073:
            if (r4 == 0) goto L_0x0077
            r1 = r4
            goto L_0x0091
        L_0x0077:
            int r4 = r0.charIndex
            if (r4 <= 0) goto L_0x0091
            byte r4 = r0.dirTypeBackward()
            switch(r4) {
                case 14: goto L_0x008a;
                case 15: goto L_0x008a;
                case 16: goto L_0x0086;
                case 17: goto L_0x0086;
                case 18: goto L_0x0083;
                default: goto L_0x0082;
            }
        L_0x0082:
            goto L_0x0077
        L_0x0083:
            int r5 = r5 + 1
            goto L_0x0077
        L_0x0086:
            if (r3 != r5) goto L_0x008e
        L_0x0088:
            r1 = 1
            goto L_0x0091
        L_0x008a:
            if (r3 != r5) goto L_0x008e
        L_0x008c:
            r1 = -1
            goto L_0x0091
        L_0x008e:
            int r5 = r5 + (-1)
            goto L_0x0077
        L_0x0091:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.getEntryDir(java.lang.CharSequence):int");
    }

    public static int getExitDir(CharSequence charSequence) {
        DirectionalityEstimator directionalityEstimator = new DirectionalityEstimator(charSequence, false);
        directionalityEstimator.charIndex = directionalityEstimator.length;
        int i = 0;
        int i2 = 0;
        while (directionalityEstimator.charIndex > 0) {
            byte dirTypeBackward = directionalityEstimator.dirTypeBackward();
            if (dirTypeBackward != 0) {
                if (dirTypeBackward == 1 || dirTypeBackward == 2) {
                    if (i == 0) {
                        return 1;
                    }
                    if (i2 == 0) {
                        i2 = i;
                    }
                } else if (dirTypeBackward != 9) {
                    switch (dirTypeBackward) {
                        case 14:
                        case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                            if (i2 == i) {
                                return -1;
                            }
                            i--;
                            break;
                        case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                        case 17:
                            if (i2 == i) {
                                return 1;
                            }
                            i--;
                            break;
                        case 18:
                            i++;
                            break;
                        default:
                            if (i2 != 0) {
                                break;
                            } else {
                                i2 = i;
                                break;
                            }
                    }
                } else {
                    continue;
                }
            } else if (i == 0) {
                return -1;
            } else {
                if (i2 == 0) {
                    i2 = i;
                }
            }
        }
        return 0;
    }

    public static BidiFormatter getInstance() {
        Locale locale = Locale.getDefault();
        Locale locale2 = TextUtilsCompat.ROOT;
        boolean z = true;
        if (TextUtils.getLayoutDirectionFromLocale(locale) != 1) {
            z = false;
        }
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = DEFAULT_TEXT_DIRECTION_HEURISTIC;
        if (textDirectionHeuristicCompat == DEFAULT_TEXT_DIRECTION_HEURISTIC) {
            return z ? DEFAULT_RTL_INSTANCE : DEFAULT_LTR_INSTANCE;
        }
        return new BidiFormatter(z, 2, textDirectionHeuristicCompat);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        String str;
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = ((TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl) textDirectionHeuristicCompat).isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        boolean z2 = (this.mFlags & 2) != 0;
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (z2 && z) {
            boolean isRtl2 = ((TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl) (isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR)).isRtl(charSequence, 0, charSequence.length());
            if (this.mIsRtlContext || (!isRtl2 && getEntryDir(charSequence) != 1)) {
                str = (!this.mIsRtlContext || (isRtl2 && getEntryDir(charSequence) != -1)) ? str2 : RLM_STRING;
            } else {
                str = LRM_STRING;
            }
            spannableStringBuilder.append((CharSequence) str);
        }
        if (isRtl != this.mIsRtlContext) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            boolean isRtl3 = ((TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl) (isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR)).isRtl(charSequence, 0, charSequence.length());
            if (!this.mIsRtlContext && (isRtl3 || getExitDir(charSequence) == 1)) {
                str2 = LRM_STRING;
            } else if (this.mIsRtlContext && (!isRtl3 || getExitDir(charSequence) == -1)) {
                str2 = RLM_STRING;
            }
            spannableStringBuilder.append((CharSequence) str2);
        }
        return spannableStringBuilder;
    }
}
