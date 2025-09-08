import posts.Post;
import posts.comments.Comment;
import privacy.PrivacyType;
import users.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User john = new User("john");
        User bob = new User("bob");

        john.updateProfileField("name", "John Smith");
        john.updateProfileField("currentAddress", "New York");
        john.updateProfileField("email", "john@test.com");
        john.updateProfileField("phone", "+1 123456789");
        john.addEducation("Harvard Business School", "MBA", LocalDate.of(2019, 6, 1), LocalDate.of(2021, 6, 1));
        john.addWorkExperience("Some Corp", "Partner", LocalDate.of(2021, 6, 1), null);
        john.addAddress("Denver, CO", LocalDate.of(1998, 1, 1), LocalDate.of(2019, 5, 31));

        john.addFriend(bob);
//        john.removeFriend(bob);
//        john.addFollower(bob);
//        john.removeFollower(bob);

//        bob.showOtherUserProfile(john);

        Post post = new Post(john, "This is a test post");
        post.setPrivacyType(PrivacyType.PRIVATE);
//        post.addComment(bob, "Bob's comment");
//        post.render(bob);
//        Comment testComment = new Comment(post, bob, "Bob's 2nd comment");
//        testComment.addComment(bob, "new comment");

    }
}