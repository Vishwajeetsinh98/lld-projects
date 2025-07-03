package renderers.impl;

import renderers.Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class VectorizedRenderer extends JFrame implements Renderer {

    private final DrawPanel drawPanel;

    public VectorizedRenderer () {
        setTitle("Vectorized Drawing Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();
        add(drawPanel);

        setVisible(true);
    }

    @Override
    public void drawSquare(int posX, int posY, int side) {
        drawPanel.setShape(posX, posY, side, ShapeType.SQUARE);
        drawPanel.repaint();
    }

    @Override
    public void drawCircle(int posX, int posY, int radius) {
        drawPanel.setShape(posX, posY, radius, ShapeType.CIRCLE);
        drawPanel.repaint();
    }

    private enum ShapeType {
        SQUARE,
        CIRCLE;
    }

    private class DrawPanel extends JPanel {

        private final List<int[]> shapes = new ArrayList<>();
        public void setShape(int posX, int posY, int size, ShapeType shape) {
            this.shapes.add(new int[] {posX, posY, size, shape.ordinal()});
        }

        @Override
        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
            for (int[] shape : shapes) {
                if (shape[3] == ShapeType.SQUARE.ordinal()) {
                    g.setColor(Color.RED);
                    g.fillRect(shape[0], shape[1], shape[2], shape[2]);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillOval(shape[0], shape[1], shape[2], shape[2]);
                }
            }
        }
    }
}
