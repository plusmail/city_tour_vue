package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
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
    @Autowired
    private UserDao userDao;

    /*
     * localhost:9000/itinerary/create?user_id=1001
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public int create(@RequestBody Itinerary itinerary, @RequestParam(value = "user_id" int user_id)) {
//    public int create(@RequestBody Itinerary itinerary) {
        itineraryDao.create(itinerary);
        userDao.
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

    /*
     * localhost:9000/itinerary/update
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Itinerary update(@RequestBody Itinerary itinerary) {
        return itineraryDao.update(itinerary);
    }

    /*
     * localhost:9000/itinerary/delete?itinerary_id=2003
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam(value = "itinerary_id") int itinerary) {
        itineraryDao.delete(itinerary);
    }

    /*
     * localhost:9000/itinerary/add_landmark?itinerary_id=2003
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/add_landmark", method = RequestMethod.POST)
    public int addLandmark(@RequestParam(value = "itinerary_id") int itineraryId,
                            @RequestParam(value = "place_id") String placeId) {
        return itineraryDao.addLandmark(itineraryId, placeId);
    }

    /*
     * localhost:9000/itinerary/add_landmark?itinerary_id=2003
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/add_la", method = RequestMethod.POST)
    public int addItineraryToUser(@RequestParam(value = "itinerary_id") int itineraryId,
                            @RequestParam(value = "place_id") String placeId) {
        return itineraryDao.addLandmark(itineraryId, placeId);
    }

    /*
     * localhost:9000/itinerary/add_landmark?itinerary_id=2003
     * */
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "/add_landmark", method = RequestMethod.POST)
//    public List<Landmark> findAllLandmarks(@RequestParam(value = "itinerary_id") int itineraryId,
//                                           @RequestParam(value = "place_id") String placeId) {
//        return itineraryDao.addLandmark(itineraryId, placeId);
//    }


//    public void addLandmark(@RequestParam(value = "itinerary_id") int itineraryId, @RequestParam(value = "place_id") String placeId) {
//        if (itineraryDao.findById(itineraryId) == null) {
//            itineraryDao.create(itineraryId);
//        } else {
//            itineraryDao.addLandmark(itineraryId, placeId);
//        }
//    }
}
