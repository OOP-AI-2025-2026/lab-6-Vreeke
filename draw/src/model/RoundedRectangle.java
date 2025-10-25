package model;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedRectangle extends DrawShape {

    public RoundedRectangle() {
        super();
    }

    public RoundedRectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public Shape getShape(Point startPoint, Point endPoint) {
        double x = Math.min(startPoint.getX(), endPoint.getX());
        double y = Math.min(startPoint.getY(), endPoint.getY());
        double w = Math.abs(startPoint.getX() - endPoint.getX());
        double h = Math.abs(startPoint.getY() - endPoint.getY());
        // радіуси закруглення можна підлаштувати
        return new RoundRectangle2D.Double(x, y, w, h, 55.0, 55.0);
    }
}
