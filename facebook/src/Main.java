import groups.Group;
import posts.Post;
import posts.comments.Comment;
import privacy.PrivacyType;
import system.FacebookSystem;
import users.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User john = new User("john");
        User bob = new User("bob");
        Group group = new Group("running_group");
        group.setAdmin(john);

        FacebookSystem facebookSystem = FacebookSystem.getInstance();
        facebookSystem.addUser(john);
        facebookSystem.addUser(bob);
        facebookSystem.addGroup(group);

        john.updateProfileField("name", "John Smith");
        john.updateProfileField("currentAddress", "New York");
        john.updateProfileField("email", "john@test.com");
        john.updateProfileField("phone", "+1 123456789");
        john.addEducation("Harvard Business School", "MBA", LocalDate.of(2019, 6, 1), LocalDate.of(2021, 6, 1));
        john.addWorkExperience("Some Corp", "Partner", LocalDate.of(2021, 6, 1), null);
        john.addAddress("Denver, CO", LocalDate.of(1998, 1, 1), LocalDate.of(2019, 5, 31));

        bob.createFriendRequest(john);
        john.acceptRequest(bob);

        bob.createGroupFollowRequest(group);
        group.acceptRequest(john, bob);

        Post post = john.createPost("This is a test post from john");



//        john.removeFriend(bob);
//        john.addFollower(bob);
//        john.removeFollower(bob);

//        bob.showOtherUserProfile(john);
        post.setPrivacyType(PrivacyType.PRIVATE);
        Comment bobComment = bob.addComment(post, "Bob's comment");
        john.addComment(bobComment, "thanks bob!");
        post.render(bob);

//        post.render(bob);
//        Comment testComment = new Comment(post, bob, "Bob's 2nd comment");
//        testComment.addComment(bob, "new comment");

    }
}