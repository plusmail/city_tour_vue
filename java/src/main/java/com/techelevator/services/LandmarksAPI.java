package com.techelevator.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;



public class LandmarksAPI {
    public void GooglePlacesAPIClient() {
    }

    public static String searchForLandmarks(String city) {
        String searchTerm = city + " Things to do";
        String apiKey = "AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA";
        String requestBody = "{\"textQuery\" : \"" + searchTerm + "\"}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://places.googleapis.com/v1/places:searchText")).header("Content-Type", "application/json").header("X-Goog-Api-Key", apiKey).header("X-Goog-FieldMask", "places.id").POST(BodyPublishers.ofString(requestBody)).build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return response.body();
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }
}