package com.techelevator.dao;

import com.techelevator.model.Itinerary;

import java.util.List;

public interface ItineraryDao {
    int create(Itinerary itinerary);

    Itinerary findById(int itineraryId);

    List<Itinerary> findAll();

    Itinerary update(Itinerary itinerary);

    void delete(int itineraryId);

    int addLandmark(int itineraryId, String placeId);
}
