package com.horcrux.svg;
/* compiled from: PathParser.java */
/* loaded from: classes.dex */
public class PathElement {
    public Point[] points;
    public int type;

    public PathElement(int i, Point[] pointArr) {
        this.type = i;
        this.points = pointArr;
    }
}
