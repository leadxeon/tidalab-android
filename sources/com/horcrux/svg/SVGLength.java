package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class SVGLength {
    public final int unit;
    public final double value;

    public SVGLength() {
        this.value = 0.0d;
        this.unit = 1;
    }

    public static ArrayList<SVGLength> arrayFrom(Dynamic dynamic) {
        int ordinal = dynamic.getType().ordinal();
        if (ordinal == 2) {
            ArrayList<SVGLength> arrayList = new ArrayList<>(1);
            arrayList.add(new SVGLength(dynamic.asDouble()));
            return arrayList;
        } else if (ordinal == 3) {
            ArrayList<SVGLength> arrayList2 = new ArrayList<>(1);
            arrayList2.add(new SVGLength(dynamic.asString()));
            return arrayList2;
        } else if (ordinal != 5) {
            return null;
        } else {
            ReadableArray asArray = dynamic.asArray();
            int size = asArray.size();
            ArrayList<SVGLength> arrayList3 = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList3.add(from(asArray.getDynamic(i)));
            }
            return arrayList3;
        }
    }

    public static SVGLength from(Dynamic dynamic) {
        int ordinal = dynamic.getType().ordinal();
        if (ordinal == 2) {
            return new SVGLength(dynamic.asDouble());
        }
        if (ordinal != 3) {
            return new SVGLength();
        }
        return new SVGLength(dynamic.asString());
    }

    public SVGLength(double d) {
        this.value = d;
        this.unit = 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
        if (r4.equals("em") == false) goto L_0x004d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SVGLength(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SVGLength.<init>(java.lang.String):void");
    }
}
