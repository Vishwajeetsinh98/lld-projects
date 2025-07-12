import platform.SocialMediaPlatform;
import platform.posts.PostFactory;
import platform.posts.Post;
import platform.users.CreatorFactory;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create Users
        SocialMediaPlatform platform = new SocialMediaPlatform("bob");
        platform.newUser(CreatorFactory.USER, "alice", "alice@gmail.com");
        platform.newUser(CreatorFactory.USER, "bob", "bob@gmail.com");
        platform.newUser(CreatorFactory.USER, "carol", "carol@gmail.com");

        // Step 2: Create Pages
        platform.newUser(CreatorFactory.PAGE, "techPage", "techHandler");
        platform.newUser(CreatorFactory.PAGE, "newsPage", "newsHandler");

        // Step 3: Add Friends and Followers
        platform.addFriend("alice", "bob");     // Bob follows Alice
        platform.addFriend("carol", "bob");     // Bob follows Carol
        platform.addFollower("techPage", "bob");
        platform.addFollower("newsPage", "bob");

        // Step 4: Create Posts
        platform.addPost(PostFactory.USER, "Alice's Update", "Learning Java Patterns!", "alice");
        platform.addPost(PostFactory.USER, "Carol's Thoughts", "Iterator pattern is cool!", "carol");

        platform.addPost(PostFactory.PAGE, "Tech News", "Java 22 Released!", "techPage");
        platform.addPost(PostFactory.PAGE, "World Update", "New AI model launched!", "newsPage");

        platform.addPost(PostFactory.AD, "Buy 1 Get 1", "Limited time sale!", "alice");  // ads still need a user

        platform.refreshFeed();

        // Step 5: Create feed for Bob and show posts
        System.out.println("🔁 Bob's Feed:");
        for (int i = 0; i < 10; i++) {
            Post nextPost = platform.showPost();
            if (nextPost == null) {
                System.out.println("No more posts.");
                break;
            }
            System.out.println(nextPost);
        }
    }
}
