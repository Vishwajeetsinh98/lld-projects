package shapes;

import renderers.Renderer;

public abstract class Shape {
    private final int posX;
    private final int posY;
    private final Renderer renderer;

    public Shape (Renderer renderer, int posX, int posY) {
        this.renderer = renderer;
        this.posX = posX;
        this.posY = posY;
    }
    public Shape (Renderer renderer) {
        this.renderer = renderer;
        this.posX = 0;
        this.posY = 0;
    }

    public abstract void show();

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }
}
