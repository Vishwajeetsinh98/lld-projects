package shapes.impl;

import renderers.Renderer;
import shapes.Shape;

public class Square extends Shape {

    private final int side;

    public Square (Renderer renderer, int posX, int posY, int side) {
        super(renderer, posX, posY);
        this.side = side;
    }

    @Override
    public void show() {
        this.getRenderer().drawSquare(this.getPosX(), this.getPosY(), this.side);
    }
}
