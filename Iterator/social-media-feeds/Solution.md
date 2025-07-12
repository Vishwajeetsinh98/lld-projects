# 🧵 Iterator Design Pattern – Social Media Feed System

## 📘 Overview

This project demonstrates the **Iterator Design Pattern** in a social media platform context. Users can view a scrolling feed that displays posts from:

* Friends (UserPosts)
* Followed Pages (PagePosts)
* Advertisements (AdPosts)

Each post type has its own iteration and filtering logic, but the client (`Feed`) remains unaware of the concrete implementations.

---

## 🧰 Design Patterns Used

| Pattern         | Description                                                              |
| --------------- | ------------------------------------------------------------------------ |
| Iterator        | Abstracts how posts are iterated over per type                           |
| Template Method | `FeedIterator#getAndFilterPosts()` overridden in each subclass           |
| Factory         | `PostFactory`, `CreatorFactory` create posts and users/pages dynamically |
| Singleton       | `*CollectionManager` classes manage and provide access to global state   |

---

## 🧩 Class Structure

### ✅ Abstract Iterator

```java
abstract class FeedIterator {
    boolean hasNext();
    Post getNext();
    void reset();
    protected abstract void getAndFilterPosts();
}
```

### ✅ Concrete Iterators

* `FriendPostsIterator`: Filters posts by friends
* `PagePostsIterator`: Filters posts by followed pages
* `AdPostsIterator`: No filtering

### ✅ Feed Class

Responsible for post rotation:

```java
if (postNum % 3 == 0) return pageIterator.getNext();
if (postNum % 5 == 0) return adIterator.getNext();
return friendsIterator.getNext();
```

### ✅ PostsCollection

Stores posts and creates iterators based on class type:

```java
static FeedIterator createIterator(Class type, String user)
```

### ✅ SocialMediaPlatform

Main entry point simulating the app logic:

* Create users/pages
* Add followers
* Create and view posts

---

## 📈 How to Extend

To add a new post type:

1. Add a new `Post` subclass (e.g. `GroupPost`)
2. Create a `GroupPostsIterator`
3. Update `PostsCollection.createIterator()` or move to a `FeedIteratorFactory`

---

## 🛡️ Potential Improvements

* Refactor `PostsCollection.createIterator()` into a factory class
* Add a `PostType` enum to avoid `Class` comparison
* Use a `Map<PostType, Supplier<FeedIterator>>` for extensibility
* Thread-safe Singleton using double-checked locking

---

## 📂 Packages Summary

| Package                | Responsibility                               |
| ---------------------- | -------------------------------------------- |
| `platform.users`       | User and Page entity models                  |
| `platform.posts`       | Post hierarchy and PostFactory               |
| `platform.feed`        | Feed traversal logic                         |
| `iterators`            | FeedIterator hierarchy                       |
| `collections.managers` | Singleton managers for post/user collections |
| `collections.creators` | Internal creator map management              |

---

## ▶️ Sample Usage (Main.java)

```java
CreatorFactory.addCreator(USER, "alice", "alice@gmail.com");
CreatorFactory.addCreator(PAGE, "openai", "admin");

Post post = PostFactory.createPost(USER, "Hello World", "Just testing", "alice");
UserPostsCollectionManager.getInstance().add(post);

Feed feed = new Feed("bob");
System.out.println(feed.getNextPost());
```
