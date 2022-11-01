package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePath;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class ShapeAppearancePathProvider {
    public final ShapePath[] cornerPaths = new ShapePath[4];
    public final Matrix[] cornerTransforms = new Matrix[4];
    public final Matrix[] edgeTransforms = new Matrix[4];
    public final PointF pointF = new PointF();
    public final Path overlappedEdgePath = new Path();
    public final Path boundsPath = new Path();
    public final ShapePath shapePath = new ShapePath();
    public final float[] scratch = new float[2];
    public final float[] scratch2 = new float[2];
    public final Path edgePath = new Path();
    public final Path cornerPath = new Path();
    public boolean edgeIntersectionCheckEnabled = true;

    /* loaded from: classes.dex */
    public static class Lazy {
        public static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();
    }

    /* loaded from: classes.dex */
    public interface PathListener {
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.cornerPaths[i] = new ShapePath();
            this.cornerTransforms[i] = new Matrix();
            this.edgeTransforms[i] = new Matrix();
        }
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, PathListener pathListener, Path path) {
        float[] fArr;
        EdgeTreatment edgeTreatment;
        CornerSize cornerSize;
        CornerTreatment cornerTreatment;
        path.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF, Path.Direction.CW);
        int i = 0;
        while (i < 4) {
            if (i == 1) {
                cornerSize = shapeAppearanceModel.bottomRightCornerSize;
            } else if (i == 2) {
                cornerSize = shapeAppearanceModel.bottomLeftCornerSize;
            } else if (i != 3) {
                cornerSize = shapeAppearanceModel.topRightCornerSize;
            } else {
                cornerSize = shapeAppearanceModel.topLeftCornerSize;
            }
            if (i == 1) {
                cornerTreatment = shapeAppearanceModel.bottomRightCorner;
            } else if (i == 2) {
                cornerTreatment = shapeAppearanceModel.bottomLeftCorner;
            } else if (i != 3) {
                cornerTreatment = shapeAppearanceModel.topRightCorner;
            } else {
                cornerTreatment = shapeAppearanceModel.topLeftCorner;
            }
            ShapePath shapePath = this.cornerPaths[i];
            Objects.requireNonNull(cornerTreatment);
            cornerTreatment.getCornerPath(shapePath, 90.0f, f, cornerSize.getCornerSize(rectF));
            int i2 = i + 1;
            float f2 = i2 * 90;
            this.cornerTransforms[i].reset();
            PointF pointF = this.pointF;
            if (i == 1) {
                pointF.set(rectF.right, rectF.bottom);
            } else if (i == 2) {
                pointF.set(rectF.left, rectF.bottom);
            } else if (i != 3) {
                pointF.set(rectF.right, rectF.top);
            } else {
                pointF.set(rectF.left, rectF.top);
            }
            Matrix matrix = this.cornerTransforms[i];
            PointF pointF2 = this.pointF;
            matrix.setTranslate(pointF2.x, pointF2.y);
            this.cornerTransforms[i].preRotate(f2);
            float[] fArr2 = this.scratch;
            ShapePath[] shapePathArr = this.cornerPaths;
            fArr2[0] = shapePathArr[i].endX;
            fArr2[1] = shapePathArr[i].endY;
            this.cornerTransforms[i].mapPoints(fArr2);
            this.edgeTransforms[i].reset();
            Matrix matrix2 = this.edgeTransforms[i];
            float[] fArr3 = this.scratch;
            matrix2.setTranslate(fArr3[0], fArr3[1]);
            this.edgeTransforms[i].preRotate(f2);
            i = i2;
        }
        int i3 = 0;
        while (i3 < 4) {
            float[] fArr4 = this.scratch;
            ShapePath[] shapePathArr2 = this.cornerPaths;
            fArr4[0] = shapePathArr2[i3].startX;
            fArr4[1] = shapePathArr2[i3].startY;
            this.cornerTransforms[i3].mapPoints(fArr4);
            if (i3 == 0) {
                float[] fArr5 = this.scratch;
                path.moveTo(fArr5[0], fArr5[1]);
            } else {
                float[] fArr6 = this.scratch;
                path.lineTo(fArr6[0], fArr6[1]);
            }
            this.cornerPaths[i3].applyToPath(this.cornerTransforms[i3], path);
            if (pathListener != null) {
                ShapePath shapePath2 = this.cornerPaths[i3];
                Matrix matrix3 = this.cornerTransforms[i3];
                MaterialShapeDrawable.AnonymousClass1 r13 = (MaterialShapeDrawable.AnonymousClass1) pathListener;
                BitSet bitSet = MaterialShapeDrawable.this.containsIncompatibleShadowOp;
                Objects.requireNonNull(shapePath2);
                bitSet.set(i3, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr = MaterialShapeDrawable.this.cornerShadowOperation;
                shapePath2.addConnectingShadowIfNecessary(shapePath2.endShadowAngle);
                shadowCompatOperationArr[i3] = new ShapePath.ShadowCompatOperation(shapePath2, new ArrayList(shapePath2.shadowCompatOperations), new Matrix(matrix3)) { // from class: com.google.android.material.shape.ShapePath.1
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    {
                        this.val$operations = r2;
                        this.val$transformCopy = r3;
                    }

                    @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
                    public void draw(Matrix matrix4, ShadowRenderer shadowRenderer, int i4, Canvas canvas) {
                        for (ShadowCompatOperation shadowCompatOperation : this.val$operations) {
                            shadowCompatOperation.draw(this.val$transformCopy, shadowRenderer, i4, canvas);
                        }
                    }
                };
            }
            int i4 = i3 + 1;
            int i5 = i4 % 4;
            float[] fArr7 = this.scratch;
            ShapePath[] shapePathArr3 = this.cornerPaths;
            fArr7[0] = shapePathArr3[i3].endX;
            fArr7[1] = shapePathArr3[i3].endY;
            this.cornerTransforms[i3].mapPoints(fArr7);
            float[] fArr8 = this.scratch2;
            ShapePath[] shapePathArr4 = this.cornerPaths;
            fArr8[0] = shapePathArr4[i5].startX;
            fArr8[1] = shapePathArr4[i5].startY;
            this.cornerTransforms[i5].mapPoints(fArr8);
            float f3 = this.scratch[0];
            float[] fArr9 = this.scratch2;
            float max = Math.max(((float) Math.hypot(f3 - fArr9[0], fArr[1] - fArr9[1])) - 0.001f, 0.0f);
            float[] fArr10 = this.scratch;
            ShapePath[] shapePathArr5 = this.cornerPaths;
            fArr10[0] = shapePathArr5[i3].endX;
            fArr10[1] = shapePathArr5[i3].endY;
            this.cornerTransforms[i3].mapPoints(fArr10);
            float abs = (i3 == 1 || i3 == 3) ? Math.abs(rectF.centerX() - this.scratch[0]) : Math.abs(rectF.centerY() - this.scratch[1]);
            this.shapePath.reset(0.0f, 0.0f, 270.0f, 0.0f);
            if (i3 == 1) {
                edgeTreatment = shapeAppearanceModel.bottomEdge;
            } else if (i3 == 2) {
                edgeTreatment = shapeAppearanceModel.leftEdge;
            } else if (i3 != 3) {
                edgeTreatment = shapeAppearanceModel.rightEdge;
            } else {
                edgeTreatment = shapeAppearanceModel.topEdge;
            }
            edgeTreatment.getEdgePath(max, abs, f, this.shapePath);
            this.edgePath.reset();
            this.shapePath.applyToPath(this.edgeTransforms[i3], this.edgePath);
            if (!this.edgeIntersectionCheckEnabled || (!pathOverlapsCorner(this.edgePath, i3) && !pathOverlapsCorner(this.edgePath, i5))) {
                this.shapePath.applyToPath(this.edgeTransforms[i3], path);
            } else {
                Path path2 = this.edgePath;
                path2.op(path2, this.boundsPath, Path.Op.DIFFERENCE);
                float[] fArr11 = this.scratch;
                ShapePath shapePath3 = this.shapePath;
                fArr11[0] = shapePath3.startX;
                fArr11[1] = shapePath3.startY;
                this.edgeTransforms[i3].mapPoints(fArr11);
                Path path3 = this.overlappedEdgePath;
                float[] fArr12 = this.scratch;
                path3.moveTo(fArr12[0], fArr12[1]);
                this.shapePath.applyToPath(this.edgeTransforms[i3], this.overlappedEdgePath);
            }
            if (pathListener != null) {
                ShapePath shapePath4 = this.shapePath;
                Matrix matrix4 = this.edgeTransforms[i3];
                MaterialShapeDrawable.AnonymousClass1 r12 = (MaterialShapeDrawable.AnonymousClass1) pathListener;
                Objects.requireNonNull(shapePath4);
                MaterialShapeDrawable.this.containsIncompatibleShadowOp.set(i3 + 4, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr2 = MaterialShapeDrawable.this.edgeShadowOperation;
                shapePath4.addConnectingShadowIfNecessary(shapePath4.endShadowAngle);
                shadowCompatOperationArr2[i3] = new ShapePath.ShadowCompatOperation(shapePath4, new ArrayList(shapePath4.shadowCompatOperations), new Matrix(matrix4)) { // from class: com.google.android.material.shape.ShapePath.1
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    {
                        this.val$operations = r2;
                        this.val$transformCopy = r3;
                    }

                    @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
                    public void draw(Matrix matrix42, ShadowRenderer shadowRenderer, int i42, Canvas canvas) {
                        for (ShadowCompatOperation shadowCompatOperation : this.val$operations) {
                            shadowCompatOperation.draw(this.val$transformCopy, shadowRenderer, i42, canvas);
                        }
                    }
                };
            }
            i3 = i4;
        }
        path.close();
        this.overlappedEdgePath.close();
        if (!this.overlappedEdgePath.isEmpty()) {
            path.op(this.overlappedEdgePath, Path.Op.UNION);
        }
    }

    public final boolean pathOverlapsCorner(Path path, int i) {
        this.cornerPath.reset();
        this.cornerPaths[i].applyToPath(this.cornerTransforms[i], this.cornerPath);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.cornerPath.computeBounds(rectF, true);
        path.op(this.cornerPath, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }
}
