package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;

import java.util.List;

public interface ItineraryDao {
    int create(Itinerary itinerary, int userId);

    Itinerary findById(int itineraryId);

    List<Itinerary> findAll();

    Itinerary update(Itinerary itinerary);

    void delete(int itineraryId);

    int addLandmark(int itineraryId, String placeId);

//    List<Landmark> findAllLandmarks(int itineraryId);
}
