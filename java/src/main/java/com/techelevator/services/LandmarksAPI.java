package com.techelevator.services;

import com.techelevator.model.Landmarks;
import org.jboss.logging.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class LandmarksAPI {
    private static final String API_BASE_URL = "https://places.googleapis.com/v1/places";
    private static final String API_KEY = "AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA";

    public Landmarks[] listLandmarks(String city){
        String search = "places:";
        Landmarks[] landmarks = null;
        try{

        }catch (Exception ex){
            System.out.println("");;
        }
        return landmarks;
    }
}
