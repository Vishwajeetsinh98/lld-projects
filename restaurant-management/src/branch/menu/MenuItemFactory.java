package branch.menu;

public class MenuItemFactory {
    public static MenuItem createMenuItem(String name, MenuSection menuSection, double price) {
        return new MenuItem(name, menuSection, price);
    }
}
