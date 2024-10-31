# Caching Techniques and Strategies

Caching is a crucial technique in system design that enhances performance and scalability by storing frequently accessed data closer to the application or user. This document outlines various types of caching and strategies for cache invalidation.

## 1. Cache

Caching involves storing copies of data in temporary storage (cache) to serve future requests for that data faster. Caching can occur at multiple layers depending on the system architecture.

### Benefits of Caching

- **Reduced Latency**: Serving data from cache significantly reduces the time it takes to fetch data.
- **Reduced Load on Origin Servers**: Cached data reduces the number of requests hitting the database or application servers, improving overall system performance.
- **Scalability**: Caching helps systems handle large amounts of traffic by distributing the load across cache layers.

### 1.1 Client Caching

- **What it is**: Client caching happens directly on the user’s device (e.g., browser caching). It stores data like static web assets (images, CSS, JavaScript) on the client side, so the next time the user requests that resource, it doesn't need to be fetched from the server.
- **Example**: When you visit a website, your browser caches assets like images and stylesheets. On subsequent visits, the browser loads these assets from the local cache rather than downloading them again from the web server.
- **Use Case**: Web applications that use client caching improve page load times and reduce server load, especially for returning users.

### 1.2 CDN Caching

- **What it is**: A Content Delivery Network (CDN) caches static assets (e.g., images, videos, CSS, JavaScript) on edge servers located around the world. When a user requests content, the CDN serves it from the nearest edge server instead of the origin server.
- **How it Works**: When a user in Tokyo requests a video from a U.S.-based website, instead of fetching it from the U.S., the CDN caches and serves the video from a server located in Tokyo, reducing latency and bandwidth usage.
- **Use Case**: Ideal for large-scale applications (like Netflix, YouTube) where users around the world need to access static content.

### 1.3 Web Server Caching

- **What it is**: Web server caching stores static or dynamically generated content at the web server level. This caching happens on the server itself, often using tools like Nginx or Apache to store frequently accessed resources.
- **Example**: Web server caching can store pre-rendered HTML pages for high-traffic parts of a website, such as the home page, to serve the content faster without generating the page on each request.
- **Use Case**: Web server caching is useful for high-traffic websites where frequently accessed content doesn't need to change on every request, like news sites or e-commerce home pages.

### 1.4 Database Caching

- **What it is**: Database caching stores query results in a cache layer to reduce the number of queries hitting the database. This can be done using in-memory cache stores like Redis or Memcached.
- **Example**: If a query to retrieve a user profile is frequently executed, the result of that query can be cached, so subsequent requests for that user’s data are served from the cache without hitting the database.
- **Use Case**: Database caching is critical in read-heavy systems where reducing database load is crucial for performance, like social media platforms where user profiles or posts are fetched frequently.

### 1.5 Application Caching

- **What it is**: Application caching stores data in a cache layer within the application logic. Developers can cache computed results, expensive calculations, or frequently used data at the application level, reducing the need for redundant computations.
- **Example**: In a web application, a complex business logic calculation (e.g., total number of users or sales for the day) can be cached, avoiding recalculating it for each request.
- **Use Case**: Application caching is used in systems that involve computationally expensive operations. This technique speeds up applications by avoiding repeated work for identical requests.

### 1.6 Caching at the Database Query Level

- **What it is**: Caching query results at the query level involves storing the results of frequently run SQL queries. This avoids the need to execute the query against the database every time.
- **Example**: If a query like SELECT * FROM products WHERE category = 'Electronics' is run often, the result can be cached. The cache can serve subsequent requests, reducing the load on the database.
- **Use Case**: This technique is used in systems with complex queries or joins that are expensive to run, such as e-commerce websites where product searches or filtered views are common.

### 1.7 Caching at the Object Level

- **What it is**: Caching at the object level involves storing objects (like entire user profiles, product listings, etc.) in a cache. Rather than caching individual query results, whole objects are stored.
- **Example**: Instead of querying the database every time for user data, a user’s entire profile (as an object) can be stored in the cache.
- **Use Case**: Object-level caching is useful in object-oriented applications, where data is structured around objects (e.g., users, products, orders).

## 2. Cache Invalidation

Caching can introduce the challenge of stale data—data that has changed in the backend but is still being served from the cache. Cache invalidation is the process of removing or updating cached data when the underlying data changes.

### 2.1 Cache-aside (Lazy Loading)

- **What it is**: In the cache-aside strategy, the application is responsible for checking whether data exists in the cache. If the data is not found (cache miss), the application fetches the data from the database, stores it in the cache, and then returns it to the user.
- **Example**: When a user requests a product list, the application first checks the cache. If the list isn’t there, it retrieves it from the database, stores it in the cache, and serves it to the user. Future requests for the same product list will be served from the cache.
- **Use Case**: Cache-aside is commonly used in systems where data doesn’t change frequently, and stale data is acceptable for short periods. It’s a good fit for read-heavy applications.

### 2.2 Write-through

- **What it is**: In the write-through strategy, every time a write operation (e.g., inserting or updating data) occurs in the database, the cache is also updated. This ensures that the cache always has fresh data.
- **Example**: If a user updates their profile, both the database and the cache are updated simultaneously. This way, the next read request will serve the updated profile from the cache.
- **Use Case**: Write-through is useful when the system requires strong consistency between the cache and the database, and write operations are relatively frequent.

### 2.3 Write-behind (Write-back)

- **What it is**: In the write-behind strategy, data is written to the cache first, and then asynchronously written to the database. The idea is that writes to the cache are fast, and the database can be updated in the background to avoid blocking the user.
- **Example**: When a user makes a purchase, the purchase data is first written to the cache. Later, the cache system writes this data to the database, ensuring that the user experiences low latency.
- **Use Case**: Write-behind is useful for write-heavy applications where performance is critical, and the system can tolerate a delay in writing to the database. It’s commonly used in logging systems or analytics where data is aggregated in batches.

### 2.4 Refresh-ahead

- **What it is**: In the refresh-ahead strategy, the cache proactively refreshes data before it expires. This ensures that users always get fresh data without having to experience a cache miss.
- **Example**: If a news site updates its homepage every 10 minutes, the cache can be refreshed just before it expires so that users always get the latest news without hitting the database.
- **Use Case**: Refresh-ahead is useful for predictable access patterns, like displaying popular news stories or weather forecasts where the data expires after a known period.

### Example: Designing a Caching Strategy for an E-commerce Platform

- **CDN Caching**: Static content like product images, CSS, and JavaScript are cached at the CDN level, ensuring that users around the world experience fast load times.
- **Database Caching**: Frequently accessed data like product catalogs or category pages are cached using a cache-aside strategy, reducing database load.
- **Object-Level Caching**: Entire user profiles are cached using a write-through strategy, ensuring that profile updates are instantly reflected in the cache.
- **Write-behind**: In the shopping cart system, updates to the user’s cart are first written to the cache for low latency, with the cart data written to the database later in the background.
- **Cache Invalidation**: When the product catalog is updated (e.g., a new product is added), cache invalidation policies like cache-aside ensure that the next request will fetch the updated catalog from the database and refresh the cache.

### Summary

- **Types of Caching**: Client Caching, CDN Caching, Web Server Caching, Database Caching, Application Caching, Caching at Database Query/Object Level.
- **Cache Invalidation**: Cache-aside, Write-through, Write-behind, Refresh-ahead.