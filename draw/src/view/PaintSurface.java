package view;

import model.DrawShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaintSurface extends JComponent {

    private final List<DrawShape> shapes = new ArrayList<>();
    private int shapeType;
    private Point startDrag;
    private Point endDrag;

    private final List<Color> colors = Arrays.asList(
            Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK
    );

    public PaintSurface() {
        shapeType = DrawShape.SHAPE_RECTANGLE;
        super.setPreferredSize(new Dimension(400, 400));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                DrawShape shape = DrawShape.newInstance(shapeType);

                if (shape != null) {
                    shape.setStartPoint(startDrag);
                    shape.setEndPoint(endDrag);
                    shapes.add(shape);
                }

                startDrag = null;
                endDrag = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    public void setShapeType(int type) {
        this.shapeType = type;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackgroundGrid(g2);
        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        for (DrawShape s : shapes) {
            if (s == null) continue;
            g2.setPaint(Color.BLACK);
            Shape sh = s.getShape();
            if (sh != null) {
                g2.draw(sh);
                g2.setPaint(colors.get(shapes.indexOf(s) % colors.size()));
                g2.fill(sh);
            }
        }

        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);
            DrawShape preview = DrawShape.newInstance(shapeType);
            if (preview != null) {
                Shape pShape = preview.getShape(startDrag, endDrag);
                if (pShape != null) g2.draw(pShape);
            }
        }
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    private void paintBackgroundGrid(Graphics2D g2) {
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }
        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }
    }
}
