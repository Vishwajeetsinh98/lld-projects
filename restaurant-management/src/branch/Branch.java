package branch;

import branch.menu.Menu;
import branch.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String name;
    private final List<Table> tables;
    private Menu menu;
    public Branch(String name) {
        this.name = name;
        tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        if (tables.contains(table)) return;
        System.out.println("[Branch] " + name + " added new table: " + table.getId());
        tables.add(table);
    }

    public void removeTable(Table table) {
        if (!tables.contains(table)) return;
        System.out.println("[Branch] " + name + " removed table: " + table.getId());
        tables.remove(table);
    }

    public List<Table> getTables() { return tables; }

    public String getName() { return name; }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

}
