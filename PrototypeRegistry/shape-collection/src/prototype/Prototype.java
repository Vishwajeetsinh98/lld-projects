package prototype;

public interface Prototype {
    // Naming copy to avoid conflict with Java's Object.clone()
    public Prototype copy();
}
