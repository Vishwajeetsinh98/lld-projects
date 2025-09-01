package users;

import notifications.NotificationService;
import order.Order;
import order.shipment.ShipmentStatus;
import product.Catalog;
import product.Product;
import product.ProductFactory;
import system.OnlineOrderSystem;


public class Admin extends User {

    private final Catalog catalog;

    public Admin(String id, String password) {
        super(id, password, true, true);
        catalog = OnlineOrderSystem.getInstance().getCatalog();
    }

    public Product addProduct(String name, double cost, String... categories) {
        Product product = ProductFactory.createProduct(name, cost, categories);
        System.out.println("[Admin] " + id + " created new product: " + product);
        return product;
    }

    public void removeProduct(Product product) {
        System.out.println("[Admin] " + id + " removing product: " + product);
        catalog.removeProduct(product);
    }

    public void addProductCategory(Product product, String category) {
        catalog.addProductCategory(product, category);
        System.out.println("[Admin] " + id + " added new product category: " + category + " to " + product);
    }

    public void deleteProductCategory(Product product, String category) {
        catalog.deleteProductCategory(product, category);
        System.out.println("[Admin] " + id + " deleted product category: " + category + " from " + product);
    }

    public void changeProductCategory(Product product, String oldCategory, String newCategory) {
        catalog.updateProductCategory(product, oldCategory, newCategory);
        System.out.println("[Admin] " + id + " updated product category from: " + oldCategory + " to: " + newCategory + " for " + product);
    }

    public void blockUser(User user) {
        System.out.println("[Admin] " + id + " blocked user " + user.id);
        user.isBlocked = true;
    }

    public void addTrackingEntry(Order order, String entry) {
        System.out.println("[Admin] " + id + " adding tracking entry to: " + order.getOrderId());
        order.getShipment().addTrackingEntry(entry);
        NotificationService.sendMessage(order.getCustomer(), "New shipment tracking update for " + order.getOrderId());
    }

    public void changeShipmentStatus(Order order, ShipmentStatus status) {
        System.out.println("[Admin] " + id + " changing shipment status for: " + order.getOrderId());
        order.getShipment().setShipmentStatus(status);
        NotificationService.sendMessage(order.getCustomer(), "Shipment status for " + order.getOrderId() + " changed to: " + status);
    }
}
