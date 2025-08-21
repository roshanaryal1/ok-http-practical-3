package com.example.okhttp;

import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Example 3: Asynchronous HTTP Request
 * This example demonstrates how to make non-blocking HTTP requests
 * using OkHttp's asynchronous API.
 */
public class AsyncRequestExample {
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        System.out.println("=== OkHttp Asynchronous Request Example by Roshan Aryal ===");

        // CountDownLatch to keep main thread alive until async operations complete
        CountDownLatch latch = new CountDownLatch(2);

        try {
            // Make two asynchronous requests
            makeAsyncRequest("https://jsonplaceholder.typicode.com/posts/1", "Request 1", latch);
            makeAsyncRequest("https://jsonplaceholder.typicode.com/users/1", "Request 2", latch);

            System.out.println("Both requests initiated. Main thread continuing other work...");
            System.out.println("Waiting for async requests to complete...\n");

            // Wait for both requests to complete
            latch.await();

            System.out.println("\nAll async requests completed!");
            System.out.println("\nDemo completed successfully - Prepared by Roshan Aryal");


        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

    /**
     * Helper method to make an asynchronous HTTP request
     */
    private static void makeAsyncRequest(String url, String requestName, CountDownLatch latch) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        System.out.println("Initiating " + requestName + " to: " + url);

        // Execute request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println(requestName + " failed: " + e.getMessage());
                latch.countDown(); // Signal completion even on failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        System.out.println("\n--- " + requestName + " Response ---");
                        System.out.println("Status: " + response.code());
                        System.out.println("Content Length: " + body.length() + " characters");
                        System.out.println("First 100 characters: " +
                                body.substring(0, Math.min(100, body.length())) + "...");
                    } else {
                        System.err.println(requestName + " failed with status: " + response.code());
                    }
                } finally {
                    response.close();
                    latch.countDown(); // Signal completion
                }
            }
        });
    }
}