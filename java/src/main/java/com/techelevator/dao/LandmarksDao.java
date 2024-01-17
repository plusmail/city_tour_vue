package com.techelevator.dao;

import com.techelevator.model.Landmarks;
import com.techelevator.model.User;

import java.util.List;

public interface LandmarksDao {
    List<Landmarks> getLandmarks();
    Landmarks getLandmark(String placeId);
    void createLandmark(String placeId);
    Landmarks updateLandmark(Landmarks landmark, String placeId);
    Landmarks deleteLandmark(String placeId);



}
