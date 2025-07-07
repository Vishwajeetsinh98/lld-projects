# LLD Question: Design an Image Loading Service with Caching using the Proxy Pattern

You are asked to design an image loading service for a web or mobile application. To optimize performance, you need to implement caching so that:

## Requirements:
- Images are fetched from a remote server when requested for the first time.
- Subsequent requests for the same image should load the image from the local cache, avoiding a network call.
- Your design should use the Proxy Pattern, where:
  - The proxy adds caching behavior.
  - The real service handles the actual network call to load the image.
### Tasks:
  ✅ Identify the interfaces and classes needed for this design.  
  ✅ Implement caching logic inside the Proxy class.  
  ✅ Show how the client requests images and how caching affects behavior.  

### Optional Extensions (for deeper discussion):
- How would you handle cache eviction (e.g., LRU)?
- How would you make this thread-safe?
- How would you extend this for lazy loading or asynchronous loading?
