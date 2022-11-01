package com.horcrux.svg;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class RNSVGMarkerPosition {
    public static int element_index_;
    public static Point in_slope_;
    public static Point origin_;
    public static Point out_slope_;
    public static ArrayList<RNSVGMarkerPosition> positions_;
    public static Point subpath_start_;
    public double angle;
    public Point origin;
    public int type;

    public RNSVGMarkerPosition(int i, Point point, double d) {
        this.type = i;
        this.origin = point;
        this.angle = d;
    }

    public static double CurrentAngle(int i) {
        Point point = in_slope_;
        double atan2 = Math.atan2(point.y, point.x) * 57.29577951308232d;
        Point point2 = out_slope_;
        double atan22 = Math.atan2(point2.y, point2.x) * 57.29577951308232d;
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i);
        if ($enumboxing$ordinal == 0) {
            return atan22;
        }
        if ($enumboxing$ordinal == 1) {
            if (Math.abs(atan2 - atan22) > 180.0d) {
                atan2 += 360.0d;
            }
            return (atan2 + atan22) / 2.0d;
        } else if ($enumboxing$ordinal != 2) {
            return 0.0d;
        } else {
            return atan2;
        }
    }

    public static boolean isZero(Point point) {
        return point.x == 0.0d && point.y == 0.0d;
    }

    public static Point subtract(Point point, Point point2) {
        return new Point(point2.x - point.x, point2.y - point.y);
    }
}
