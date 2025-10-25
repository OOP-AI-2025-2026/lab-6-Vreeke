package view;

import model.DrawShape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DrawFrame extends JFrame {

    private PaintSurface surface;

    public DrawFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(setButtonPanel(), BorderLayout.NORTH);
        surface = new PaintSurface();
        this.add(surface, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JPanel setButtonPanel() {
        JPanel buttonPanel = new JPanel(true);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

        BigTextButton rect = new BigTextButton("Rectangle");
        rect.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_RECTANGLE));
        buttonPanel.add(rect);

        BigTextButton rounded_rect = new BigTextButton("Rounded rect.");
        rounded_rect.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_ROUNDED_RECT));
        buttonPanel.add(rounded_rect);

        BigTextButton ellipseBtn = new BigTextButton("Ellipse");
        ellipseBtn.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_ELLIPSE));
        buttonPanel.add(ellipseBtn);

        BigTextButton clearBtn = new BigTextButton("Clear");
        clearBtn.addActionListener(e -> surface.clearShapes());
        buttonPanel.add(clearBtn);

        return buttonPanel;
    }
}
