package shapes;

public class Circle extends Shape {

    private int radius;

    public Circle(int posX, int posY, String color, int radius) {
        super(posX, posY, color);
        this.radius = radius;
    }

    public Circle (Circle c) {
        super(c);
        this.radius = c.radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public Shape copy() {
        return new Circle(this);
    }

    public String toString() {
        return String.format("[CIRCLE]: id: %d posX: %d posY: %d Color: %s radius: %d area: %f perimeter: %f", getId(),
                                                                                                               getPosX(),
                                                                                                               getPosY(),
                                                                                                               getColor(),
                                                                                                               getRadius(),
                                                                                                               getArea(),
                                                                                                               getPerimeter());
    }

}
