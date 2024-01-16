package com.techelevator.controller;

import com.techelevator.dao.LandmarksDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/landmarks/")
public class LandmarksController {

    @Autowired
    private LandmarksDao landmarksDao;

}
