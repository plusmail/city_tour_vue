package com.techelevator.dao;

import com.techelevator.model.itinerary.Itinerary;
import com.techelevator.model.landmarks.Landmarks;

import java.util.List;

public interface ItineraryDao {
    //Create Itinerary
    void createItinerary(int itineraryId);

    //Get Itinerary
    Itinerary getItinerary(int itineraryId);

    //Get List of Itineraries
    List<Itinerary> getItineraries();

    //Edit Itinerary
    Itinerary editItinerary(Itinerary itinerary, int itineraryId);

    //Delete Itinerary
    Itinerary deleteItinerary(int itineraryId);
    void addLandmarkToItinerary(String placeId, int itineraryId);


}
