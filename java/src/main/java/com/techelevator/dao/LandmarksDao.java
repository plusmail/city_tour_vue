package com.techelevator.dao;

import com.techelevator.model.searchlandmark.Landmarks;

import java.util.List;

public interface LandmarksDao {
    List<Landmarks> getLandmarks();
    Landmarks getLandmark(String placeId);
    Landmarks createLandmark(String placeId);
    Landmarks updateLandmark(Landmarks landmark, String placeId);
    Landmarks deleteLandmark(String placeId);



}
