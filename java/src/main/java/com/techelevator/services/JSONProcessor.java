package com.techelevator.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.LandmarksResultList;

import java.net.http.HttpResponse;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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