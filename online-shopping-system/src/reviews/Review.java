package reviews;

import users.User;

public record Review(String text, User user, int rating) {
    @Override
    public String toString() {
        return user.getId() + "\n" +
                "*".repeat(Math.max(0, rating)) +
                "\n" + text;
    }
}
