package kotlin.text;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1;
/* compiled from: Indent.kt */
/* loaded from: classes.dex */
public class StringsKt__IndentKt extends StringsKt__AppendableKt {
    public static final String capitalize(String str) {
        Locale locale = Locale.getDefault();
        if (!(str.length() > 0)) {
            return str;
        }
        char charAt = str.charAt(0);
        if (!Character.isLowerCase(charAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char titleCase = Character.toTitleCase(charAt);
        if (titleCase != Character.toUpperCase(charAt)) {
            sb.append(titleCase);
        } else {
            sb.append(str.substring(0, 1).toUpperCase(locale));
        }
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static final boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence2 instanceof String) {
            if (indexOf$default(charSequence, (String) charSequence2, 0, z, 2) < 0) {
                return false;
            }
        } else if (indexOf$StringsKt__StringsKt$default(charSequence, charSequence2, 0, charSequence.length(), z, false, 16) < 0) {
            return false;
        }
        return true;
    }

    public static boolean endsWith$default(CharSequence charSequence, char c, boolean z, int i) {
        if ((i & 2) != 0) {
            z = false;
        }
        return charSequence.length() > 0 && InputKt.equals(charSequence.charAt(getLastIndex(charSequence)), c, z);
    }

    public static final boolean equals(String str, String str2, boolean z) {
        if (str == null) {
            return str2 == null;
        }
        if (!z) {
            return str.equals(str2);
        }
        return str.equalsIgnoreCase(str2);
    }

    public static final int getLastIndex(CharSequence charSequence) {
        return charSequence.length() - 1;
    }

    public static final int indexOf(CharSequence charSequence, String str, int i, boolean z) {
        if (z || !(charSequence instanceof String)) {
            return indexOf$StringsKt__StringsKt$default(charSequence, str, i, charSequence.length(), z, false, 16);
        }
        return ((String) charSequence).indexOf(str, i);
    }

    public static final int indexOf$StringsKt__StringsKt(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        IntProgression intProgression;
        if (!z2) {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence.length();
            if (i2 > length) {
                i2 = length;
            }
            intProgression = new IntRange(i, i2);
        } else {
            int lastIndex = getLastIndex(charSequence);
            if (i > lastIndex) {
                i = lastIndex;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            intProgression = new IntProgression(i, i2, -1);
        }
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            int i3 = intProgression.first;
            int i4 = intProgression.last;
            int i5 = intProgression.step;
            if (i5 < 0 ? i3 >= i4 : i3 <= i4) {
                while (!regionMatchesImpl(charSequence2, 0, charSequence, i3, charSequence2.length(), z)) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                return i3;
            }
        } else {
            int i6 = intProgression.first;
            int i7 = intProgression.last;
            int i8 = intProgression.step;
            if (i8 < 0 ? i6 >= i7 : i6 <= i7) {
                while (!regionMatches((String) charSequence2, 0, (String) charSequence, i6, charSequence2.length(), z)) {
                    if (i6 != i7) {
                        i6 += i8;
                    }
                }
                return i6;
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3) {
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    public static int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        if (z || !(charSequence instanceof String)) {
            return indexOfAny(charSequence, new char[]{c}, i, z);
        }
        return ((String) charSequence).indexOf(c, i);
    }

    public static final int indexOfAny(CharSequence charSequence, char[] cArr, int i, boolean z) {
        boolean z2;
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            if (i < 0) {
                i = 0;
            }
            int lastIndex = getLastIndex(charSequence);
            if (i > lastIndex) {
                return -1;
            }
            while (true) {
                char charAt = charSequence.charAt(i);
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z2 = false;
                        break;
                    } else if (InputKt.equals(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return i;
                }
                if (i == lastIndex) {
                    return -1;
                }
                i++;
            }
        } else {
            return ((String) charSequence).indexOf(ArraysKt___ArraysKt.single(cArr), i);
        }
    }

    public static final boolean isBlank(CharSequence charSequence) {
        boolean z;
        if (charSequence.length() == 0) {
            return true;
        }
        IntRange intRange = new IntRange(0, charSequence.length() - 1);
        if (!(intRange instanceof Collection) || !((Collection) intRange).isEmpty()) {
            Iterator<Integer> it = intRange.iterator();
            while (((IntProgressionIterator) it).hasNext) {
                if (!InputKt.isWhitespace(charSequence.charAt(((IntIterator) it).nextInt()))) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return z;
    }

    public static int lastIndexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        boolean z2 = (i2 & 4) != 0 ? false : z;
        if (z2 || !(charSequence instanceof String)) {
            return indexOf$StringsKt__StringsKt(charSequence, str, i, 0, z2, true);
        }
        return ((String) charSequence).lastIndexOf(str, i);
    }

    public static final Void numberFormatError(String str) {
        throw new NumberFormatException("Invalid number format: '" + str + '\'');
    }

    public static Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3) {
        boolean z2 = false;
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        if (i2 >= 0) {
            z2 = true;
        }
        if (z2) {
            return new DelimitedRangesSequence(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(Arrays.asList(strArr), z));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + '.').toString());
    }

    public static final boolean regionMatches(String str, int i, String str2, int i2, int i3, boolean z) {
        if (!z) {
            return str.regionMatches(i, str2, i2, i3);
        }
        return str.regionMatches(z, i, str2, i2, i3);
    }

    public static final boolean regionMatchesImpl(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!InputKt.equals(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static final List<String> split$StringsKt__StringsKt(CharSequence charSequence, String str, boolean z, int i) {
        int i2 = 0;
        if (i >= 0) {
            int indexOf = indexOf(charSequence, str, 0, z);
            if (indexOf == -1 || i == 1) {
                return Collections.singletonList(charSequence.toString());
            }
            boolean z2 = i > 0;
            int i3 = 10;
            if (z2 && i <= 10) {
                i3 = i;
            }
            ArrayList arrayList = new ArrayList(i3);
            do {
                arrayList.add(charSequence.subSequence(i2, indexOf).toString());
                i2 = str.length() + indexOf;
                if (z2 && arrayList.size() == i - 1) {
                    break;
                }
                indexOf = indexOf(charSequence, str, i2, z);
            } while (indexOf != -1);
            arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
            return arrayList;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + '.').toString());
    }

    public static List split$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2) {
        boolean z2 = false;
        boolean z3 = (i2 & 2) != 0 ? false : z;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() == 0) {
                z2 = true;
            }
            if (!z2) {
                return split$StringsKt__StringsKt(charSequence, str, z3, i3);
            }
        }
        SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 = new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, strArr, 0, z3, i3, 2));
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1, 10));
        Iterator it = sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1.iterator();
        while (it.hasNext()) {
            arrayList.add(substring(charSequence, (IntRange) it.next()));
        }
        return arrayList;
    }

    public static final boolean startsWith(String str, String str2, boolean z) {
        if (!z) {
            return str.startsWith(str2);
        }
        return regionMatches(str, 0, str2, 0, str2.length(), z);
    }

    public static /* synthetic */ boolean startsWith$default(String str, String str2, boolean z, int i) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(str, str2, z);
    }

    public static final String substring(CharSequence charSequence, IntRange intRange) {
        return charSequence.subSequence(Integer.valueOf(intRange.first).intValue(), Integer.valueOf(intRange.last).intValue() + 1).toString();
    }

    public static String substringAfter$default(String str, String str2, String str3, int i) {
        String str4 = (i & 2) != 0 ? str : null;
        int indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6);
        return indexOf$default == -1 ? str4 : str.substring(str2.length() + indexOf$default, str.length());
    }

    public static String substringAfterLast$default(String str, char c, String str2, int i) {
        String str3 = (i & 2) != 0 ? str : null;
        int lastIndexOf = str.lastIndexOf(c, getLastIndex(str));
        return lastIndexOf == -1 ? str3 : str.substring(lastIndexOf + 1, str.length());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r3 == '+') goto L_0x002e;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Integer toIntOrNull(java.lang.String r10) {
        /*
            r0 = 10
            com.tidalab.v2board.clash.design.dialog.InputKt.checkRadix(r0)
            int r1 = r10.length()
            if (r1 != 0) goto L_0x000c
            goto L_0x0051
        L_0x000c:
            r2 = 0
            char r3 = r10.charAt(r2)
            r4 = 48
            int r4 = kotlin.jvm.internal.Intrinsics.compare(r3, r4)
            r5 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            r6 = 1
            if (r4 >= 0) goto L_0x002d
            if (r1 != r6) goto L_0x0020
            goto L_0x0051
        L_0x0020:
            r4 = 45
            if (r3 != r4) goto L_0x0028
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1
            goto L_0x002f
        L_0x0028:
            r4 = 43
            if (r3 != r4) goto L_0x0051
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            r3 = 0
        L_0x002f:
            r4 = -59652323(0xfffffffffc71c71d, float:-5.0215282E36)
            r7 = -59652323(0xfffffffffc71c71d, float:-5.0215282E36)
        L_0x0035:
            if (r6 >= r1) goto L_0x0057
            char r8 = r10.charAt(r6)
            int r8 = java.lang.Character.digit(r8, r0)
            if (r8 >= 0) goto L_0x0042
            goto L_0x0051
        L_0x0042:
            if (r2 >= r7) goto L_0x004b
            if (r7 != r4) goto L_0x0051
            int r7 = r5 / 10
            if (r2 >= r7) goto L_0x004b
            goto L_0x0051
        L_0x004b:
            int r2 = r2 * 10
            int r9 = r5 + r8
            if (r2 >= r9) goto L_0x0053
        L_0x0051:
            r10 = 0
            goto L_0x0063
        L_0x0053:
            int r2 = r2 - r8
            int r6 = r6 + 1
            goto L_0x0035
        L_0x0057:
            if (r3 == 0) goto L_0x005e
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            goto L_0x0063
        L_0x005e:
            int r10 = -r2
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
        L_0x0063:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.toIntOrNull(java.lang.String):java.lang.Integer");
    }

    public static final Long toLongOrNull(String str) {
        InputKt.checkRadix(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        boolean z = false;
        char charAt = str.charAt(0);
        long j = -9223372036854775807L;
        int i = 1;
        if (Intrinsics.compare(charAt, 48) >= 0) {
            i = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                j = Long.MIN_VALUE;
                z = true;
            } else if (charAt != '+') {
                return null;
            }
        }
        long j2 = 0;
        long j3 = -256204778801521550L;
        long j4 = -256204778801521550L;
        while (i < length) {
            int digit = Character.digit((int) str.charAt(i), 10);
            if (digit < 0) {
                return null;
            }
            if (j2 < j4) {
                if (j4 != j3) {
                    return null;
                }
                j4 = j / 10;
                if (j2 < j4) {
                    return null;
                }
            }
            long j5 = j2 * 10;
            long j6 = digit;
            if (j5 < j + j6) {
                return null;
            }
            j2 = j5 - j6;
            i++;
            j3 = -256204778801521550L;
        }
        return z ? Long.valueOf(j2) : Long.valueOf(-j2);
    }

    public static final UInt toUIntOrNull(String str, int i) {
        InputKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char charAt = str.charAt(0);
        int i3 = 1;
        if (Intrinsics.compare(charAt, 48) >= 0) {
            i3 = 0;
        } else if (length == 1 || charAt != '+') {
            return null;
        }
        int i4 = 119304647;
        while (i3 < length) {
            int digit = Character.digit((int) str.charAt(i3), i);
            if (digit < 0) {
                return null;
            }
            if (InputKt.uintCompare(i2, i4) > 0) {
                if (i4 == 119304647) {
                    i4 = (int) (((-1) & 4294967295L) / (4294967295L & i));
                    if (InputKt.uintCompare(i2, i4) > 0) {
                    }
                }
                return null;
            }
            int i5 = i2 * i;
            int i6 = digit + i5;
            if (InputKt.uintCompare(i6, i5) < 0) {
                return null;
            }
            i3++;
            i2 = i6;
        }
        return new UInt(i2);
    }

    public static final ULong toULongOrNull(String str) {
        int i;
        int i2 = 10;
        InputKt.checkRadix(10);
        int length = str.length();
        if (length != 0) {
            char charAt = str.charAt(0);
            if (Intrinsics.compare(charAt, 48) >= 0) {
                i = 0;
            } else if (length != 1 && charAt == '+') {
                i = 1;
            }
            long j = 10;
            long j2 = 0;
            long j3 = 512409557603043100L;
            while (i < length) {
                int digit = Character.digit((int) str.charAt(i), i2);
                if (digit >= 0) {
                    if (InputKt.ulongCompare(j2, j3) > 0) {
                        if (j3 == 512409557603043100L) {
                            if (j < 0) {
                                j3 = InputKt.ulongCompare(-1L, j) < 0 ? 0L : 1L;
                            } else {
                                long j4 = (Long.MAX_VALUE / j) << 1;
                                j3 = j4 + (InputKt.ulongCompare((-1) - (j4 * j), j) >= 0 ? 1 : 0);
                            }
                            if (InputKt.ulongCompare(j2, j3) > 0) {
                            }
                        }
                    }
                    long j5 = j2 * j;
                    long j6 = (digit & 4294967295L) + j5;
                    if (InputKt.ulongCompare(j6, j5) >= 0) {
                        i++;
                        j2 = j6;
                        length = length;
                        i2 = 10;
                    }
                }
            }
            return new ULong(j2);
        }
        return null;
    }

    public static final CharSequence trim(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean isWhitespace = InputKt.isWhitespace(charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!isWhitespace) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!isWhitespace) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, str, i, z);
    }
}
