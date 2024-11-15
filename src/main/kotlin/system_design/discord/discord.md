# Designing a System Like Discord

To design a system like Discord, we'll start by breaking down core requirements and discussing each component's design approach at a high level:

## 1. Requirements Gathering

### Functional Requirements:
- Real-time text, voice, and video communication.
- Support for chat channels, voice channels, and private messages.
- Notifications, status updates, and rich media sharing (images, files, etc.).
- Moderation tools (banning, muting, etc.) and roles/permissions.

### Non-Functional Requirements:
- Low latency for real-time interactions, particularly for voice and video.
- High availability and scalability to support millions of concurrent users.
- Consistent performance under high load, especially for notifications and message delivery.

## 2. System Components Overview

- **Gateway & WebSocket Service:** Handles real-time communication using WebSocket connections.
- **Message Service:** Manages text messaging, including storing, delivering, and retrieving messages.
- **Voice and Video Service:** A separate service to manage real-time audio/video streams using specialized protocols (e.g., WebRTC).
- **User Service:** Manages user authentication, sessions, profiles, and status updates.
- **Channel Management Service:** Handles creation, deletion, and management of channels and groups.
- **Notification Service:** Provides notifications for messages, mentions, etc., both in-app and push notifications.
- **Media Storage:** For storing images, videos, and other rich media files.
- **Database and Caching:** Stores persistent data and caches frequently accessed items for faster read operations.

## 3. Detailed Component Design

### a. Gateway & WebSocket Service
- **Purpose:** Manages bidirectional communication for real-time events (messages, status updates).
- **Design:** Uses load balancers to distribute WebSocket connections across multiple servers. Each server maintains a large number of persistent WebSocket connections.
- **Scalability:** Uses horizontal scaling to add more WebSocket servers as user load grows.
- **State Management:** Makes this layer stateless by storing connection states in a distributed in-memory cache like Redis.

### b. Message Service
- **Purpose:** Handles text-based communication.
- **Database:** Uses a NoSQL database like Cassandra or MongoDB for storing chat messages due to its horizontal scalability.
- **Caching:** Uses an in-memory cache like Redis to reduce database load for recent messages.
- **Data Consistency:** Uses eventual consistency for non-critical data, prioritizing availability and speed over strict consistency.
- **Data Partitioning:** Shards messages by channel or user ID to enable faster retrieval and storage.

### c. Voice & Video Service
- **Protocol:** Uses WebRTC, which supports low-latency P2P communication, ideal for voice and video.
- **Signaling Server:** WebRTC requires a signaling mechanism to exchange connection data. This can be handled by WebSocket or HTTP.
- **Load Balancing:** For larger servers, uses a load balancer to distribute users across different regions.
- **Media Servers:** For group calls, implements selective forwarding units (SFU) or multipoint control units (MCU) to handle multiple streams and minimize bandwidth.

### d. Channel & User Management Services
- **User Service:** Manages user sessions, profiles, and statuses (online, offline, Do Not Disturb).
- **Channel Management:** Includes permission settings for channels, role management, and private messaging.
- **Database:** Uses a relational database with ACID compliance, such as PostgreSQL, for user profiles and server/channel settings.

### e. Notification Service
- **In-App Notifications:** Real-time alerts via WebSocket for mentions, DMs, or other user actions.
- **Push Notifications:** Uses a third-party service like Firebase Cloud Messaging (FCM) for mobile push notifications.
- **Optimization:** Batches notifications for users in multiple groups to minimize duplicate notifications.

## 4. Storage and Caching

- **Media Storage:** Stores images, videos, and other files in a cloud-based object storage solution like Amazon S3 for scalability and redundancy.
- **Database Choices:**
    - Messages: Uses a NoSQL store, optimized for high-throughput writes.
    - Metadata: Uses SQL for user and channel metadata.
- **Caching Strategy:** Uses Redis for user session data, message cache, and recently accessed channels.

## 5. Scalability and Load Management

- **Load Balancing:** Places load balancers at every layer to ensure even distribution and avoid overloading individual servers.
- **Microservices:** Deploys services independently to scale based on individual needs, such as more WebSocket servers during peak times.
- **Data Partitioning:** Applies horizontal scaling on databases using sharding strategies. Messages can be partitioned by time or by channel for distributed storage.

## 6. Back-of-the-Envelope Estimations

- **WebSocket Connections:** If each server can handle 100,000 WebSocket connections, and you expect 10 million users at peak, you’ll need approximately 100 WebSocket servers.
- **Message Throughput:** If average message size is 1 KB and there are 10 million messages per day, this translates to around 10 GB of storage per day for messages alone.
- **Storage:** For media storage, accounts for large uploads by implementing restrictions or limiting media to reduce storage overhead.

## 7. Challenges and Trade-offs

- **Latency vs. Consistency:** In real-time systems like chat, prioritize low latency. Eventual consistency is often acceptable for non-critical data.
- **Database Partitioning:** Sharding messages is complex but necessary for scaling. Choose a sharding strategy that aligns with access patterns.
- **Resource Optimization:** WebRTC’s P2P is ideal for small groups but can be optimized for larger calls by routing through a server.
