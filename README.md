# OkHttp Demo Project

**Course:** IX608001 Intermediate Application Development Concepts  
**Assignment:** Practical 3 - Java Library/Framework Study  
**Library:** OkHttp  
**Students:** Roshan Aryal

## Project Overview

This project demonstrates the usage of OkHttp, a modern HTTP client library for Java applications. OkHttp is developed by Square Inc. and provides an efficient way to make HTTP requests with features like connection pooling, caching, and interceptors.

## What is OkHttp?

OkHttp is an HTTP client that's efficient by default:
- HTTP/2 support allows all requests to the same host to share a socket
- Connection pooling reduces request latency (if HTTP/2 isn't available)
- Transparent GZIP shrinks download sizes
- Response caching avoids the network completely for repeat requests

## Project Structure

```
okhttp-demo/
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── okhttp/
                        ├── BasicGetExample.java      # Example 1: GET request
                        ├── PostJsonExample.java      # Example 2: POST with JSON
                        └── AsyncRequestExample.java  # Example 3: Async requests
```

## Examples Included

### 1. BasicGetExample.java
- Demonstrates basic HTTP GET request
- Shows how to handle response data
- Includes error handling
- **Maven Example:** This uses Maven dependency management

### 2. PostJsonExample.java
- Shows how to send JSON data via POST
- Demonstrates request body creation
- Shows proper content-type headers

### 3. AsyncRequestExample.java
- Illustrates asynchronous request handling
- Shows concurrent request processing
- Demonstrates callback mechanisms

## How to Run

1. Open project in IntelliJ IDEA
2. Ensure Maven dependencies are downloaded
3. Run any example class individually:
   - Right-click on the class file
   - Select "Run '[ClassName].main()'"

## Dependencies

- **OkHttp 4.12.0** - Main HTTP client library
- **Gson 2.10.1** - JSON processing (optional)

## Use Cases for OkHttp

- **REST API Integration** - Consuming web services
- **File Uploads/Downloads** - Transferring files over HTTP
- **Web Scraping** - Fetching and parsing web content
- **Mobile App Backend Communication** - Android/Java applications
- **Microservices Communication** - Service-to-service HTTP calls

## Key Architecture Components

1. **OkHttpClient** - Main entry point and configuration
2. **Request** - HTTP request representation
3. **Response** - HTTP response container
4. **Call** - Represents a request ready for execution
5. **Callback** - Interface for asynchronous response handling
6. **Interceptors** - Middleware for request/response processing

## Benefits Over Java's Built-in HTTP Client

- More intuitive API
- Better performance with connection pooling
- Built-in support for modern protocols (HTTP/2)
- Comprehensive interceptor system
- Better error handling and retry mechanisms
- Extensive documentation and community support

## References

- [Official OkHttp Website](https://square.github.io/okhttp/)
- [OkHttp GitHub Repository](https://github.com/square/okhttp)
- [OkHttp Recipes and Examples](https://square.github.io/okhttp/recipes/)
- [Maven Central Repository](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
