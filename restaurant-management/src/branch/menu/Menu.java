package branch.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Menu {
    private final Map<MenuSection, List<MenuItem>> menuItems;
    public Menu() {
        menuItems = new ConcurrentHashMap<>();
    }

    public void addMenuItem(MenuItem item) {
        if (!menuItems.containsKey(item.getMenuSection()))
            menuItems.put(item.getMenuSection(), new ArrayList<>());
        menuItems.get(item.getMenuSection()).add(item);
    }

    public void removeMenuItem(MenuItem item) {
        if (!menuItems.containsKey(item.getMenuSection()))
            return;
        menuItems.get(item.getMenuSection()).remove(item);
    }

    public void showMenu() {
        for (MenuSection section : menuItems.keySet()) {
            if (menuItems.get(section).isEmpty()) continue;
            System.out.println("XXXXX " + section + " XXXXX");
            for (MenuItem item : menuItems.get(section))
                System.out.println(item.getName() + "\t\t" + item.getPrice());
        }
    }
}
