package com.techelevator.services;

import com.techelevator.model.Landmarks;
import org.jboss.logging.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LandmarksAPI {
    private static final String API_BASE_URL = "https://places.googleapis.com/v1/places";
    private static final String API_KEY = "AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA";

    public static String listLandmarks(String city){
        String line = "";
        String search = city + " Landmarks";
        Landmarks[] landmarks = null;
        try{
            String command =
                    "curl -X POST -d '{  \"textQuery\" : \""+search+"\"}' -H 'Content-Type: application/json' -H 'X-Goog-Api-Key: AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA' -H 'X-Goog-FieldMask: places.id,places.displayName' 'https://places.googleapis.com/v1/places:searchText'";
            Process process = new ProcessBuilder("",command).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        }catch (Exception ex){
            System.out.println("Error getting search results from CURL");;
        }
        return line;
    }
}