package com.techelevator.controller;

import com.techelevator.dao.UserDao;
//import com.techelevator.model.Itinerary;
//import com.techelevator.model.Landmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserDao userDao;

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "/create", method = RequestMethod.POST)
//    public int addItinerary(@RequestBody Itinerary itinerary) {
//        return userDao.addItinerary(userId, itineraryId);
//    }

}
