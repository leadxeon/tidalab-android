package okhttp3.internal.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
/* loaded from: classes.dex */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.encodeUtf8("\t ,=");

    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int code = response.code();
        return (((code >= 100 && code < 200) || code == 204 || code == 304) && contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) ? false : true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0079, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0079, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseChallengeHeader(java.util.List<okhttp3.Challenge> r8, okio.Buffer r9) {
        /*
            r0 = 0
        L_0x0001:
            r1 = r0
        L_0x0002:
            if (r1 != 0) goto L_0x000e
            skipWhitespaceAndCommas(r9)
            java.lang.String r1 = readToken(r9)
            if (r1 != 0) goto L_0x000e
            return
        L_0x000e:
            boolean r2 = skipWhitespaceAndCommas(r9)
            java.lang.String r3 = readToken(r9)
            if (r3 != 0) goto L_0x002c
            boolean r9 = r9.exhausted()
            if (r9 != 0) goto L_0x001f
            return
        L_0x001f:
            okhttp3.Challenge r9 = new okhttp3.Challenge
            java.util.Map r0 = java.util.Collections.emptyMap()
            r9.<init>(r1, r0)
            r8.add(r9)
            return
        L_0x002c:
            r4 = 61
            int r5 = skipAll(r9, r4)
            boolean r6 = skipWhitespaceAndCommas(r9)
            if (r2 != 0) goto L_0x005c
            if (r6 != 0) goto L_0x0040
            boolean r2 = r9.exhausted()
            if (r2 == 0) goto L_0x005c
        L_0x0040:
            okhttp3.Challenge r2 = new okhttp3.Challenge
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r4 = repeat(r4, r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.util.Map r3 = java.util.Collections.singletonMap(r0, r3)
            r2.<init>(r1, r3)
            r8.add(r2)
            goto L_0x0001
        L_0x005c:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            int r6 = skipAll(r9, r4)
            int r5 = r5 + r6
        L_0x0066:
            if (r3 != 0) goto L_0x0077
            java.lang.String r3 = readToken(r9)
            boolean r5 = skipWhitespaceAndCommas(r9)
            if (r5 == 0) goto L_0x0073
            goto L_0x0079
        L_0x0073:
            int r5 = skipAll(r9, r4)
        L_0x0077:
            if (r5 != 0) goto L_0x0084
        L_0x0079:
            okhttp3.Challenge r4 = new okhttp3.Challenge
            r4.<init>(r1, r2)
            r8.add(r4)
            r1 = r3
            goto L_0x0002
        L_0x0084:
            r6 = 1
            if (r5 <= r6) goto L_0x0088
            return
        L_0x0088:
            boolean r6 = skipWhitespaceAndCommas(r9)
            if (r6 == 0) goto L_0x008f
            return
        L_0x008f:
            boolean r6 = r9.exhausted()
            if (r6 != 0) goto L_0x00a4
            r6 = 0
            byte r6 = r9.getByte(r6)
            r7 = 34
            if (r6 != r7) goto L_0x00a4
            java.lang.String r6 = readQuotedString(r9)
            goto L_0x00a8
        L_0x00a4:
            java.lang.String r6 = readToken(r9)
        L_0x00a8:
            if (r6 != 0) goto L_0x00ab
            return
        L_0x00ab:
            java.lang.Object r3 = r2.put(r3, r6)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L_0x00b4
            return
        L_0x00b4:
            boolean r3 = skipWhitespaceAndCommas(r9)
            if (r3 != 0) goto L_0x00c1
            boolean r3 = r9.exhausted()
            if (r3 != 0) goto L_0x00c1
            return
        L_0x00c1:
            r3 = r0
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpHeaders.parseChallengeHeader(java.util.List, okio.Buffer):void");
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < headers.size(); i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(headers.value(i));
                parseChallengeHeader(arrayList, buffer);
            }
        }
        return arrayList;
    }

    public static int parseSeconds(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private static String readQuotedString(Buffer buffer) {
        if (buffer.readByte() == 34) {
            Buffer buffer2 = new Buffer();
            while (true) {
                long indexOfElement = buffer.indexOfElement(QUOTED_STRING_DELIMITERS);
                if (indexOfElement == -1) {
                    return null;
                }
                if (buffer.getByte(indexOfElement) == 34) {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    return buffer2.readUtf8();
                } else if (buffer.size == indexOfElement + 1) {
                    return null;
                } else {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    buffer2.write(buffer, 1L);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String readToken(Buffer buffer) {
        try {
            long indexOfElement = buffer.indexOfElement(TOKEN_DELIMITERS);
            if (indexOfElement == -1) {
                indexOfElement = buffer.size;
            }
            if (indexOfElement != 0) {
                return buffer.readUtf8(indexOfElement);
            }
            return null;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> parseAll = Cookie.parseAll(httpUrl, headers);
            if (!parseAll.isEmpty()) {
                cookieJar.saveFromResponse(httpUrl, parseAll);
            }
        }
    }

    private static String repeat(char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    private static int skipAll(Buffer buffer, byte b) {
        int i = 0;
        while (!buffer.exhausted() && buffer.getByte(0L) == b) {
            i++;
            buffer.readByte();
        }
        return i;
    }

    public static int skipUntil(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int skipWhitespace(String str, int i) {
        char charAt;
        while (i < str.length() && ((charAt = str.charAt(i)) == ' ' || charAt == '\t')) {
            i++;
        }
        return i;
    }

    private static boolean skipWhitespaceAndCommas(Buffer buffer) {
        boolean z = false;
        while (!buffer.exhausted()) {
            byte b = buffer.getByte(0L);
            if (b != 44) {
                if (b != 32 && b != 9) {
                    break;
                }
                buffer.readByte();
            } else {
                buffer.readByte();
                z = true;
            }
        }
        return z;
    }

    private static long stringToLong(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response)) {
            if (!Util.equal(headers.values(str), request.headers(str))) {
                return false;
            }
        }
        return true;
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains("*");
    }

    public static Set<String> varyFields(Headers headers) {
        Set<String> emptySet = Collections.emptySet();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            if ("Vary".equalsIgnoreCase(headers.name(i))) {
                String value = headers.value(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : value.split(",")) {
                    emptySet.add(str.trim());
                }
            }
        }
        return emptySet;
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set<String> varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i));
            }
        }
        return builder.build();
    }
}
