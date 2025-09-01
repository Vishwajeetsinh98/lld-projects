package product;

import system.OnlineOrderSystem;

import java.util.concurrent.atomic.AtomicLong;

public class ProductFactory {
    private static final AtomicLong id = new AtomicLong(1);
    public static Product createProduct(String name, double cost, String... categories) {
        Product product = new Product("P_" + id.getAndIncrement(), name, cost);
        for (String category : categories)
            product.addCategory(category);
        OnlineOrderSystem.getInstance().getCatalog().addProduct(product);
        return product;
    }
}
