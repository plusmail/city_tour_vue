package com.techelevator.dao;

import com.techelevator.model.itinerary.Itinerary;

import java.util.List;

public interface ItineraryDao {
    //Create Itinerary
    Itinerary createItinerary(int itineraryId);

    //Get Itinerary
    Itinerary getItinerary(int itineraryId);

    //Get List of Itineraries
    List<Itinerary> getItineraries();

    //Edit Itinerary
    Itinerary editItinerary(Itinerary itinerary, int itineraryId);

    //Delete Itinerary
    void deleteItinerary(int itineraryId);
    void addLandmarkToItinerary(int itineraryId, String placeId);


}
