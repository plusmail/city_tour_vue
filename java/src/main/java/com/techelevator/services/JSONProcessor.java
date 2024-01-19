package com.techelevator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.searchlandmark.SearchResultList;

public class JSONProcessor {
    public static SearchResultList JSONParserMultiple(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SearchResultList landmarks = mapper.readValue(input, SearchResultList.class);
            return landmarks;
        } catch (Exception ex) {
            System.out.println("Error parsing API request");
        }
        return null;
    }
    public static PlaceResult JSONParserMultiple(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PlaceResult landmarks = mapper.readValue(input, PlaceResult.class);
            return landmarks;
        } catch (Exception ex) {
            System.out.println("Error parsing API request");
        }
        return null;
    }

}