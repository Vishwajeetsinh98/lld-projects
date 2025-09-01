package cart;

import order.Order;
import product.Product;
import system.OnlineOrderSystem;
import users.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private final Map<Product, Integer> productMap;
    private double cartTotal;

    public Cart() {
        productMap = new ConcurrentHashMap<>();
        cartTotal = 0.0;
    }

    public void addProduct(Product product) {
        System.out.println("[Card] Adding one more unit of " + product + " to cart");
        productMap.put(product, productMap.getOrDefault(product, 0) + 1);
        cartTotal += product.getCost();
    }

    public void removeProduct(Product product) {
        if (!productMap.containsKey(product)) return;
        System.out.println("[Card] Completely removing " + product + " from cart");
        cartTotal -= (product.getCost() * productMap.get(product));
        productMap.remove(product);
    }

    public void decreaseProduct(Product product) {
        if (!productMap.containsKey(product)) return;
        System.out.println("[Card] Removing 1 unit of " + product + " from cart");
        productMap.put(product, productMap.get(product) - 1);
        cartTotal -= product.getCost();
        if (productMap.get(product) == 0)
            productMap.remove(product);
    }

    public void clear() {
        productMap.clear();
        cartTotal = 0.0;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public Order checkout(Customer customer, String address) {
        return OnlineOrderSystem.getInstance().placeOrder(this, customer, address);
    }
}
