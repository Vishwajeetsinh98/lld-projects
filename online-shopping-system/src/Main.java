import order.Order;
import order.shipment.ShipmentStatus;
import product.Product;
import system.OnlineOrderSystem;
import users.Admin;
import users.Customer;

public class Main {
    public static void main(String[] args) {
        OnlineOrderSystem onlineOrderSystem = OnlineOrderSystem.getInstance();
        Admin admin = new Admin("admin", "1234");
        Customer customer = new Customer("customer", "1234");

        Product pen = admin.addProduct("pen", 10, "writing", "notes", "stationery");
        Product book = admin.addProduct("diary", 20, "writing", "stationery", "books");
        Product knife = admin.addProduct("knife", 30, "kitchen", "cooking");
        Product temp = admin.addProduct("temp", 1000, "temp");
        admin.removeProduct(temp);


        customer.listProductsByCategory("cooking");
        customer.addProductReview(pen, 5, "");
        customer.addProductReview(pen, 1, "");
        customer.listProducts();
        customer.checkProductReviews(pen);
        customer.login("1234");
        customer.addProductToCart(pen);
        customer.addProductToCart(pen);
        customer.addProductToCart(book);
        customer.addProductToCart(knife);
        customer.decreaseProductFromCart(pen);
        customer.deleteProductFromCart(book);
        customer.showCart();

        Order order = customer.order("Test address");
        System.out.println(order.getShipment());

        admin.addTrackingEntry(order, "Package left warehouse");
        admin.changeShipmentStatus(order, ShipmentStatus.DELAYED);

        customer.trackOrder(order);
        customer.cancelOrder(order);

    }
}
