package co.pupil.squarecodingtest;

import co.pupil.squarecodingtest.entity.GeometryDescriptor;
import co.pupil.squarecodingtest.entity.Shape;
import co.pupil.squarecodingtest.entity.Type;

public class ShapeFactory {
    public static Shape createShape(String name, int x, int y, int size) {
        return Shape.builder()
                .name(name)
                .type(Type.SQUARE)
                .geometryDescriptor(GeometryDescriptor.builder()
                        .x(x)
                        .y(y)
                        .size(size)
                        .build())
                .build();
    }
}
