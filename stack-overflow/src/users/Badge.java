package users;

public class Badge {
    private final String text;
    public Badge(String text) {
        this.text = text;
    }
    @Override
    public String toString() { return text.toUpperCase(); }
}
