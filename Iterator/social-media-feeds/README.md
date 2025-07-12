# 💬 LLD Question: Social Media Feed Iterator

### See Solution architecture for my solution [link](Solution.md)

## 📱 Problem Statement:
Design the feed component of a social media platform (like Instagram, Twitter, or LinkedIn), where users can scroll through their feed which contains a mixture of content — posts from:

- Friends
- Followed Pages
- Recommended Content
- Sponsored Ads

The feed should support lazy traversal using the Iterator Design Pattern, allowing users to scroll one post at a time (like infinite scrolling).

### 📌 Requirements:
Design a `FeedIterator` interface that supports standard methods:
- `hasNext()`
- `getNext()`

**There are different types of post sources:**
- FriendPosts
- PagePosts
- AdPosts

The `Feed` class must use these sources and allow uniform traversal using an iterator.
Posts must be fetched **lazily** — only when needed, not all at once.
#### 🔁 Iterator Pattern Constraints:
- The user of the feed should not know or care about where the post is coming from.
- You should use the Iterator Pattern to unify the traversal of diverse post types.
- Posts may be retrieved from different APIs or databases, so they must be abstracted behind an iterator.
#### 🧪 Example Scenario:
```
User opens app →
Feed internally wires:
- FriendPostsIterator
- AdPostsIterator
- PagePostsIterator

User scrolls down →
Feed.getNextPost() →
Internally fetches next post from correct iterators, based on strategy (e.g., 3 friends posts, then 1 ad, etc.)
```
### 🎯 Bonus Points For:
- Supporting multiple traversal strategies (e.g., interleave ads every 5 posts)
- Resetting the iterators
- Supporting infinite feeds using pagination