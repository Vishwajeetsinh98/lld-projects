package shapes;

public class Triangle extends Shape {

    private int base;
    private int height;

    public Triangle(int posX, int posY, String color, int base, int height) {
        super(posX, posY, color);
        this.base = base;
        this.height = height;
    }

    public Triangle (Triangle t) {
        super(t);
        this.base = t.base;
        this.height = t.height;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return this.base * this.height / 2.0;
    }

    @Override
    public double getPerimeter() {
        // base + height + hypotenuse (sqrt(base^2+height^2))
        return this.base + this.height + Math.sqrt(this.base * this.base
                                                    + this.height * this.height);
    }

    @Override
    public Shape copy() {
        return new Triangle(this);
    }

    public String toString() {
        return String.format("[TRIANGLE]: id: %d posX: %d posY: %d Color: %s base: %d height: %d area: %f perimeter: %f", getId(),
                getPosX(),
                getPosY(),
                getColor(),
                getBase(),
                getHeight(),
                getArea(),
                getPerimeter());
    }

}
