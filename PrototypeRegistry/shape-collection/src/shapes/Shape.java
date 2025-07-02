package shapes;

import prototype.Prototype;

import java.util.Random;

public abstract class Shape implements Prototype {
    private int posX;
    private int posY;
    private String color;
    private final long id;

    Shape (int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.id = new Random().nextLong();
    }

    Shape(Shape obj) {
        this.posX = obj.posX;
        this.posY = obj.posY;
        this.color = obj.color;
        this.id = new Random().nextLong();
    }

    public long getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract Shape copy();

}
