package com.example.okhttp;

import okhttp3.*;
import java.io.IOException;

/**
 * Example 1: Basic GET Request using OkHttp
 * This example demonstrates how to make a simple HTTP GET request
 * and handle the response.
 */
public class BasicGetExample {
    // Create a single OkHttpClient instance (recommended for reuse)
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        System.out.println("=== OkHttp Basic GET Request Example ===");

        try {
            // Step 1: Create a Request object
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/1")
                    .build();

            System.out.println("Making GET request to: " + request.url());

            // Step 2: Execute the request synchronously
            try (Response response = client.newCall(request).execute()) {

                // Step 3: Check if request was successful
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + response);
                }

                // Step 4: Extract and display response information
                System.out.println("\n--- Response Details ---");
                System.out.println("Status Code: " + response.code());
                System.out.println("Status Message: " + response.message());
                System.out.println("Content-Type: " + response.header("Content-Type"));
                System.out.println("Content-Length: " + response.header("Content-Length"));

                // Step 5: Get the response body
                String responseBody = response.body().string();
                System.out.println("\n--- Response Body ---");
                System.out.println(responseBody);

                System.out.println("\n--- Request Completed Successfully ---");
            }

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}