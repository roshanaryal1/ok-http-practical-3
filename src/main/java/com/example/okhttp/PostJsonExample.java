package com.example.okhttp;

import okhttp3.*;
import java.io.IOException;

/**
 * Example 2: POST Request with JSON payload
 * This example demonstrates how to send JSON data to a server
 * using OkHttp POST request.
 */
public class PostJsonExample {
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void main(String[] args) {
        System.out.println("=== OkHttp POST Request with JSON Example ===");

        try {
            // Step 1: Create JSON payload
            String jsonPayload = createJsonPayload();
            System.out.println("JSON Payload to send:");
            System.out.println(jsonPayload);

            // Step 2: Create RequestBody with JSON content
            RequestBody requestBody = RequestBody.create(jsonPayload, JSON);

            // Step 3: Build the POST request
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            System.out.println("\nMaking POST request to: " + request.url());

            // Step 4: Execute the request
            try (Response response = client.newCall(request).execute()) {

                // Step 5: Handle the response
                System.out.println("\n--- Response Details ---");
                System.out.println("Status Code: " + response.code());
                System.out.println("Status Message: " + response.message());

                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    System.out.println("\n--- Server Response ---");
                    System.out.println(responseBody);
                    System.out.println("\n--- POST Request Completed Successfully ---");
                } else {
                    System.err.println("Request failed with code: " + response.code());
                }
            }

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to create JSON payload
     */
    private static String createJsonPayload() {
        return "{\n" +
                "  \"title\": \"OkHttp Demo Post\",\n" +
                "  \"body\": \"This is a demonstration of OkHttp POST request with JSON payload done by Roshan Aryal. " +
                "Created for IX608001 assignment - Practical 3.\",\n" +
                "  \"userId\": 1000123440\n" +
                "}";
    }
}