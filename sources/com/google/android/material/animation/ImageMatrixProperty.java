package com.google.android.material.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;
/* loaded from: classes.dex */
public class ImageMatrixProperty extends Property<ImageView, Matrix> {
    public final Matrix matrix = new Matrix();

    public ImageMatrixProperty() {
        super(Matrix.class, "imageMatrixProperty");
    }

    @Override // android.util.Property
    public Matrix get(ImageView imageView) {
        this.matrix.set(imageView.getImageMatrix());
        return this.matrix;
    }

    @Override // android.util.Property
    public void set(ImageView imageView, Matrix matrix) {
        imageView.setImageMatrix(matrix);
    }
}
