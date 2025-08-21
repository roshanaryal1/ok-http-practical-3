package com.example.okhttp;

import okhttp3.*;
import java.io.IOException;

/**
 * Example 4: Using Interceptors with OkHttp
 * This example demonstrates how to use interceptors for logging,
 * authentication, and request modification.
 */
public class InterceptorExample {

    public static void main(String[] args) {
        System.out.println("=== OkHttp Interceptor Example by Roshan Aryal ===");

        // Create client with custom interceptors
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new AuthInterceptor())
                .build();

        try {
            // Create request
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/1")
                    .addHeader("User-Agent", "OkHttp-Demo/1.0")
                    .build();

            System.out.println("Making request with interceptors...\n");

            // Execute request
            try (Response response = client.newCall(request).execute()) {
                System.out.println("Final Response Status: " + response.code());
                System.out.println("Response Headers: " + response.headers().size() + " headers");
                System.out.println("Response Body Length: " + response.body().string().length() + " characters");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Custom logging interceptor to monitor requests and responses
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // Log request details
            System.out.println("🔄 REQUEST: " + request.method() + " " + request.url());
            System.out.println("📋 Headers: " + request.headers().size() + " headers");

            long startTime = System.nanoTime();
            Response response = chain.proceed(request);
            long endTime = System.nanoTime();

            // Log response details
            System.out.println("✅ RESPONSE: " + response.code() + " " + response.message());
            System.out.println("⏱️  Time: " + (endTime - startTime) / 1_000_000 + "ms");
            System.out.println("📦 Size: " + response.body().contentLength() + " bytes\n");

            return response;
        }
    }

    /**
     * Custom authentication interceptor
     */
    static class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            // Add authentication header to all requests
            Request authenticatedRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer demo-token-12345")
                    .addHeader("X-API-Version", "v1")
                    .build();

            System.out.println("🔐 Added authentication headers");

            return chain.proceed(authenticatedRequest);
        }
    }
}