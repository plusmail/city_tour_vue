package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "/itinerary")
public class ItineraryController {
    @Autowired
    private ItineraryDao itineraryDao;

    /*
     * localhost:9000/itinerary/create
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public int create(@RequestBody Itinerary itinerary) {
        return itineraryDao.create(itinerary);
    }

    /*
     * localhost:9000/itinerary/find?itinerary_id=2001
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public Itinerary findById(@RequestParam(value = "itinerary_id") int itineraryId) {
        return itineraryDao.findById(itineraryId);
    }

    /*
     * localhost:9000/itinerary/find_all
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/find_all", method = RequestMethod.GET)
    public List<Itinerary> findAll() {
        return itineraryDao.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Itinerary update(@RequestBody Itinerary itinerary) {
        return itineraryDao.update(itinerary);
    }



//    public void addLandmark(@RequestParam(value = "itinerary_id") int itineraryId, @RequestParam(value = "place_id") String placeId) {

//        if (itineraryDao.findById(itineraryId) == null) {
//            itineraryDao.create(itineraryId);
//        } else {
//            itineraryDao.addLandmark(itineraryId, placeId);
//        }
//    }
}
