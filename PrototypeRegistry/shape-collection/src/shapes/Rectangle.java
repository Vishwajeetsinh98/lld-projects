package shapes;

public class Rectangle extends Shape {
    private int length;
    private int breadth;

    public Rectangle (int posX, int posY, String color, int length, int breadth) {
        super(posX, posY, color);
        this.length = length;
        this.breadth = breadth;
    }

    public Rectangle (Rectangle r) {
        super(r);
        this.length = r.length;
        this.breadth = r.breadth;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return this.length * this.breadth;
    }

    @Override
    public double getPerimeter() {
        return 2 * (this.length + this.breadth);
    }

    @Override
    public Shape copy() {
        return new Rectangle(this);
    }

    public String toString() {
        return String.format("[RECTANGLE]: id: %d posX: %d posY: %d Color: %s length: %d breadth: %d area: %f perimeter: %f", getId(),
                getPosX(),
                getPosY(),
                getColor(),
                getLength(),
                getBreadth(),
                getArea(),
                getPerimeter());
    }

}
