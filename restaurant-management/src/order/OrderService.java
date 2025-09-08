package order;

import branch.table.Table;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {
    private final Map<Table, Order> orderMap;
    public OrderService() {
        orderMap = new ConcurrentHashMap<>();
    }

    public Order createOrderForTable(Table table) {
        if (orderMap.containsKey(table))
            throw new IllegalArgumentException("Ongoing order at table: " + table.getId());
        Order newOrder = new Order(table);
        orderMap.put(table, newOrder);
        return newOrder;
    }

    public Order getOrderForTable(Table table) {
        return orderMap.getOrDefault(table, null);
    }
}
