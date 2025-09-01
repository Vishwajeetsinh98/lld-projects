package users;

import cart.Cart;
import order.Order;
import product.Catalog;
import product.Product;
import reviews.Review;
import system.OnlineOrderSystem;

import java.util.Map;

public class Customer extends User {
    private final Cart cart;

    public Customer(String id, String password) {
        super(id, password, true, false);
        cart = new Cart();
    }

    private void checkUserCanDo() {
        if (!canPlaceOrders())
            throw new IllegalArgumentException("User not logged in - cannot perform action");
    }

    public void listProducts() {
        Catalog catalog = OnlineOrderSystem.getInstance().getCatalog();
        System.out.println("[Customer] " + id + " viewing all products.");
        for (Product product : catalog.getAllProducts()) {
            System.out.println(product);
        }
        System.out.println("========================================================");
    }

    public void listProductsByCategory(String category) {
        Catalog catalog = OnlineOrderSystem.getInstance().getCatalog();
        System.out.println("[Customer] " + id + " viewing products for category: " + category);
        for (Product product : catalog.getProductsByCategory(category)) {
            System.out.println(product);
        }
        System.out.println("========================================================");
    }

    public void showCart() {
        checkUserCanDo();
        System.out.println("[Customer] " + id + " view cart");
        for (Map.Entry<Product, Integer> entry : cart.getProductMap().entrySet()) {
            Product product = entry.getKey();
            int number = entry.getValue();
            double cost = product.getCost() * number;
            System.out.println(product + " X " + number + " amount: " + cost);
        }
        System.out.println("Cart total: " + cart.getCartTotal());
    }

    public void addProductToCart(Product product) {
        checkUserCanDo();
        System.out.println("[Customer] " + id + " adding product " + product + " to cart");
        cart.addProduct(product);
    }

    public void deleteProductFromCart(Product product) {
        checkUserCanDo();
        System.out.println("[Customer] " + id + " removing product " + product + " from cart");
        cart.removeProduct(product);
    }

    public void decreaseProductFromCart(Product product) {
        checkUserCanDo();
        System.out.println("[Customer] " + id + " decreasing product " + product + " from cart");
        cart.decreaseProduct(product);
    }

    public void addProductReview(Product product, int rating, String text) {
        System.out.println("[Customer] " + id + " added review for product " + product);
        product.addReview(new Review(text, this, rating));
    }

    public void checkProductReviews(Product product) {
        System.out.println("[Customer] checking reviews for " + product);
        for (Review review : product.getReviews())
            System.out.println(review);
    }

    public void clearCart() {
        checkUserCanDo();
        System.out.println("[Customer] " + id + " clearing cart");
        cart.clear();
    }

    public Order order(String address) {
        checkUserCanDo();
        return cart.checkout(this, address);
    }

    public void trackOrder(Order order) {
        checkUserCanDo();
        System.out.println(order.getShipment());
        for (String entry : order.getShipment().getShipmentTracking())
            System.out.println(entry);
    }

    public void cancelOrder(Order order) {
        checkUserCanDo();
        OnlineOrderSystem.getInstance().cancelOrder(order);
    }
}
