package product;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Catalog {
    private final Map<String, Product> allProducts;
    private final Map<String, Set<Product>> categoryProductMap;

    public Catalog() {
        allProducts = new ConcurrentHashMap<>();
        categoryProductMap = new ConcurrentHashMap<>();
    }

    public Product getProductById(String id) {
        return allProducts.getOrDefault(id, null);
    }

    public List<Product> getAllProducts() {
        return allProducts.values().stream().toList();
    }

    public List<Product> getProductsByCategory(String category) {
        return categoryProductMap.getOrDefault(category, new HashSet<>()).stream().toList();
    }

    public void addProduct(Product product) {
        allProducts.put(product.getId(), product);
        for (String category : product.getCategories()) {
            if (!categoryProductMap.containsKey(category))
                categoryProductMap.put(category, new HashSet<>());
            categoryProductMap.get(category).add(product);
        }
        System.out.println("[Catalog] Added " + product + " to catalog");
    }

    public void addProductCategory(Product product, String category) {
        if (!allProducts.containsKey(product.getId()))
            throw new IllegalArgumentException("Invalid product - not available in catalog");
        if (!categoryProductMap.containsKey(category))
            categoryProductMap.put(category, new HashSet<>());
        categoryProductMap.get(category).add(product);
        product.addCategory(category);
        System.out.println("[Catalog] Added " + category + " to product " + product);
    }

    public void deleteProductCategory(Product product, String category) {
        if (!allProducts.containsKey(product.getId()))
            throw new IllegalArgumentException("Invalid product - not available in catalog");
        if (!categoryProductMap.containsKey(category))
            categoryProductMap.put(category, new HashSet<>());
        categoryProductMap.get(category).add(product);
        product.removeCategory(category);
        System.out.println("[Catalog] Added " + category + " to product " + product);
    }

    public void updateProductCategory(Product product, String oldCategory, String newCategory) {
        if (!allProducts.containsKey(product.getId()))
            throw new IllegalArgumentException("Invalid product - not available in catalog");
        if (!categoryProductMap.containsKey(oldCategory) || !categoryProductMap.get(oldCategory).contains(product))
            throw new IllegalArgumentException("Category not found, or product doesn't belong the entered category");

        categoryProductMap.get(oldCategory).remove(product);
        if (!categoryProductMap.containsKey(newCategory))
            categoryProductMap.put(newCategory, new HashSet<>());
        categoryProductMap.get(newCategory).add(product);
        product.modifyCategory(oldCategory, newCategory);
        System.out.println("[Catalog] Changed " + oldCategory + " to " + newCategory + " for product " + product);
    }

    public void removeProduct(Product product) {
        if (!allProducts.containsKey(product.getId())) {
            System.out.println("[Catalog] No such product in catalog " + product);
            return;
        }
        allProducts.remove(product.getId());
        for (String category : product.getCategories()) {
            if (categoryProductMap.containsKey(category))
                categoryProductMap.get(category).remove(product);
        }
    }
}
