package com.techelevator.dao;

import com.techelevator.model.searchlandmark.Landmarks;

import java.util.List;

public interface LandmarksDao {
    Landmarks create(String placeId);
//    List<Landmarks> list();
//    Landmarks findByPlaceId(String placeId);
//    Landmarks update(Landmarks landmark, String placeId);
//    Landmarks delete(String placeId);
}
