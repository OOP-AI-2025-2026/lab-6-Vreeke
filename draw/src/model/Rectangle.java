package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends DrawShape {

    public Rectangle() {
        super();
    }

    public Rectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public Shape getShape(Point startPoint, Point endPoint) {
        double x = Math.min(startPoint.getX(), endPoint.getX());
        double y = Math.min(startPoint.getY(), endPoint.getY());
        double w = Math.abs(startPoint.getX() - endPoint.getX());
        double h = Math.abs(startPoint.getY() - endPoint.getY());
        return new Rectangle2D.Double(x, y, w, h);
    }
}
