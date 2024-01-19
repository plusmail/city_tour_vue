package com.techelevator.controller;

import com.techelevator.dao.LandmarksDao;
import com.techelevator.model.searchlandmark.LandmarkResult;
import com.techelevator.model.searchlandmark.SearchResultList;
import com.techelevator.services.JSONProcessor;
import com.techelevator.services.LandmarksAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping(path="/landmarks/")
public class LandmarksController {

    @Autowired
    private LandmarksDao landmarksDao;

    @RequestMapping(path="create/{placeId}", method = RequestMethod.POST)
    public void createLandmark(@PathVariable String placeId){
        landmarksDao.createLandmark(placeId);
    }

    @RequestMapping(path="list/{city}", method = RequestMethod.GET)
    public SearchResultList listLandmarks(@PathVariable String city){

        return JSONProcessor.JSONParserMultiple(LandmarksAPI.searchForLandmarks(city));

    }
    @RequestMapping(path="place/{placeId}", method = RequestMethod.GET)
    public LandmarkResult getLandmark(@PathVariable String placeId){

        return JSONProcessor.JSONParserSingle(LandmarksAPI.getLandmark(placeId));

    }


}