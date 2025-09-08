package branch.menu;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class MenuItem {
    private static final AtomicLong idCounter = new AtomicLong(1);
    private final String id;
    private String name;
    private boolean isActive;
    private MenuSection menuSection;
    private double price;

    public MenuItem(String name, MenuSection menuSection, double price) {
        this(name, true, menuSection, price);
    }

    public MenuItem(String name, boolean isActive, MenuSection menuSection, double price) {
        id = "" + menuSection.toString().charAt(0) + idCounter.getAndIncrement();
        this.name = name;
        this.isActive = isActive;
        this.menuSection = menuSection;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    public void setMenuSection(MenuSection menuSection) {
        this.menuSection = menuSection;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MenuItem)) return false;
        return id.equals(((MenuItem)o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
