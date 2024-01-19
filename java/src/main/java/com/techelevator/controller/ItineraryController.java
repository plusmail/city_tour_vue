package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.dao.LandmarksDao;
import com.techelevator.model.itinerary.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/itinerary/")
public class ItineraryController {
    @Autowired
    private ItineraryDao itineraryDao;

    @RequestMapping(path="{itineraryId}/{placeId}", method = RequestMethod.POST)
    public void addLandmark(@PathVariable int itineraryId, String placeId){

        if (itineraryDao.getItinerary(itineraryId) == null){
            itineraryDao.createItinerary(itineraryId);
        } else {
            itineraryDao.addLandmarkToItinerary(itineraryId, placeId);
        }

    }
}
