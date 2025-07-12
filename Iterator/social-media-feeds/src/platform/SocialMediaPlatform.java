package platform;


import collections.PostsCollection;
import collections.managers.*;
import platform.feed.Feed;
import platform.posts.*;
import platform.users.CreatorFactory;

public class SocialMediaPlatform {

    private Feed feed;
    private final String user;

    public SocialMediaPlatform(String user) {
        this.user = user;
    }

    public void newUser(int type, String name, String email) {
        CreatorFactory.addCreator(type, name, email);
    }

    public void addPost(int type, String title, String content, String author) {
        try {
            Post newPost = PostFactory.createPost(type, title, content, author);
            switch (type) {
                case PostFactory.USER -> {
                    UserPostsCollectionManager.getInstance().add(newPost);
                }
                case PostFactory.PAGE -> {
                    PagePostsCollectionManager.getInstance().add(newPost);
                }
                case PostFactory.AD -> {
                    AdPostsCollectionManager.getInstance().add(newPost);
                }
            }
        } catch (Exception e) {
            System.out.println("User/Page not found, post not created.");
        }
    }

    public void addFriend(String username, String followerName) {
        UserCollectionManager.getInstance().get(username).addFollower(followerName);
    }

    public void addFollower(String page, String followerName) {
        PageCollectionManager.getInstance().get(page).addFollower(followerName);
    }

    public void refreshFeed() {
        feed = new Feed(user);
    }

    public Post showPost() {
        if (feed == null)
            refreshFeed();
        return feed.getNextPost();
    }
}
