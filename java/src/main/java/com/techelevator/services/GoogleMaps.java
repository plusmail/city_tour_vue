package com.techelevator.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class GoogleMaps {
    private final String API_KEY;

    public GoogleMaps(String apiKey) {
        this.API_KEY = apiKey;
    }

    public String search(String query) {
        String requestBody = "{\"textQuery\" : \"" + query + "\"}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://places.googleapis.com/v1/places:searchText"))
                .header("Content-Type", "application/json")
                .header("X-Goog-Api-Key", API_KEY)
                .header("X-Goog-FieldMask", "places.id,places.displayName," +
                        "places.currentOpeningHours,places.accessibilityOptions,places.formattedAddress,places.location,places.subDestinations,places.types,places.priceLevel,places.rating")
                .POST(BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return response.body();
        } catch (Exception var7) {
            System.out.println("Error Getting Response");
            return null;
        }
    }

    public String findByPlaceId(String placeId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://places.googleapis.com/v1/places/" + placeId))
                .header("Content-Type", "application/json")
                .header("X-Goog-Api-Key", API_KEY)
                .header("X-Goog-FieldMask", "displayName," +
                        "currentOpeningHours,accessibilityOptions,formattedAddress," +
                        "location,subDestinations,types,priceLevel,rating")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAPIKey(){
        return API_KEY;
    }
}