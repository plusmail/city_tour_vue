package com.techelevator.dao;

import com.techelevator.model.Landmarks;
import com.techelevator.model.User;

import java.util.List;

public interface LandmarksDao {
    List<Landmarks> getLandmarks(String placeId);
    Landmarks getLandmark(String placeId);
    Landmarks createLandmark(Landmarks landmark, String placeId);
    Landmarks updateLandmark(Landmarks landmark, String placeId);
    Landmarks deleteLandmark(String placeId);



}
