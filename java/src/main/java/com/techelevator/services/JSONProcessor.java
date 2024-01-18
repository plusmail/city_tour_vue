package com.techelevator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.landmarks.LandmarksResultList;

public class JSONProcessor {
    public static LandmarksResultList JSONParserMultiple(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LandmarksResultList landmarks = mapper.readValue(input, LandmarksResultList.class);
            return landmarks;
        } catch (Exception ex) {
            System.out.println("Error parsing API request");
        }
        return null;
    }

}