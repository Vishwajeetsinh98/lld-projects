package shapes.impl;

import renderers.Renderer;
import shapes.Shape;

public class Circle extends Shape {

    private final int radius;

    public Circle (Renderer renderer, int posX, int posY, int radius) {
        super(renderer, posX, posY);
        this.radius = radius;
    }

    @Override
    public void show() {
        this.getRenderer().drawCircle(this.getPosX(), this.getPosY(), this.radius);
    }
}
