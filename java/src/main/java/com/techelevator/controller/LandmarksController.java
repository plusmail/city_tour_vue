package com.techelevator.controller;

import com.techelevator.dao.LandmarksDao;
import com.techelevator.services.GoogleMaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.github.cdimascio.dotenv.Dotenv;

@RestController
@CrossOrigin
@RequestMapping(path = "/landmarks")
public class LandmarksController {

    private final String API_KEY = Dotenv.configure().load().get("GOOGLE_MAPS_API_KEY");
    private final GoogleMaps googleMaps = new GoogleMaps(API_KEY);

    @Autowired
    private LandmarksDao landmarksDao;

    /*
     * example query
     * localhost:9000/landmarks/create?place_id=1234567890
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public void create(@RequestParam(value = "place_id") String placeId) {
        landmarksDao.create(placeId);
    }

    /*
     * localhost:9000/landmarks/search?city=dubai
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "city") String city) {
        String query = city + "things to do";
        return googleMaps.search(query);
    }

    /*
     * localhost:9000/landmarks/find?place_id=1234567890
     * */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String findByPlaceId(@RequestParam(value = "place_id") String placeId) {
        return googleMaps.findByPlaceId(placeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/API_KEY", method = RequestMethod.POST)
    public String getAPIKey(){
        return googleMaps.getAPIKey();
    }
}