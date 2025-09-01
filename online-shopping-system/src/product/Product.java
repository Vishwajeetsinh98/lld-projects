package product;

import reviews.Review;

import java.util.*;

public class Product {
    private final String id;
    private final Set<String> categories;
    private final String name;
    private double cost;
    private final List<Review> reviews;

    public Product(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.categories = new HashSet<>();
        this.cost = cost;
        this.reviews = new ArrayList<>();
    }

    public void addCategory(String category) {
        System.out.println("[Product] Adding " + category + " for " + this);
        categories.add(category);
    }

    public void removeCategory(String category) {
        System.out.println("[Product] Removing " + category + " for " + this);
        categories.remove(category);
    }

    public void modifyCategory(String oldCategory, String newCategory) {
        if (!categories.contains(oldCategory)) {
            System.out.println("[Product] " + this + " doesn't have category " + oldCategory + ", skipping.");
            return;
        }
        System.out.println("[Product] Changing " + oldCategory + " to " + newCategory + " for " + this);
        removeCategory(oldCategory);
        addCategory(newCategory);
    }

    public double getAverageRating() {
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.rating();
        }
        return !reviews.isEmpty() ? sum / reviews.size() : 0.0;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getId() {
        return id;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")" + "["+"*".repeat((int)Math.ceil(getAverageRating()))+"]";
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (!(o instanceof Product)) return false;
        return ((Product) o).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
