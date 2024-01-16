package com.techelevator.dao;

import com.techelevator.model.Landmarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarksDao implements LandmarksDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Landmarks> getLandmarks(String placeId) {
        List<Landmarks> landmarks = new ArrayList<>();
        String sql = null;

        return landmarks;
    }

    @Override
    public Landmarks getLandmark(String placeId) {
        return null;
    }

    @Override
    public Landmarks createLandmark(Landmarks newLandmark, String placeId) {
        //Landmarks newLandmark = null;

        String sql = "INSERT INTO landmarks\n" +
                "(place_id, landmark_name, opening_time, closing_time, landmark_description, tour_price, landmark_category, star_rating, location_address)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING place_id;";
        try{
            placeId = jdbcTemplate.queryForObject(sql, String.class, newLandmark.getPlaceId(), newLandmark.getLandmarkName(), newLandmark.getOpeningTime(),
                    newLandmark.getClosingTime(), newLandmark.getLandmarkDescription(), newLandmark.getTourPriceRange(), newLandmark.getLandmarkCategory(),
                    newLandmark.getStarRating(), newLandmark.getLocationAddress());

            addLandmarkToLandmarks(placeId);


        } catch (Exception ex){
            System.out.println("Something went wrong");
        }
        return newLandmark;
    }

    @Override
    public Landmarks updateLandmark(Landmarks landmark, String placeId) {
        return null;
    }

    @Override
    public Landmarks deleteLandmark(String placeId) {
        return null;
    }

    public Account mapRowToAccount(SqlRowSet result) {
        Account account = new Account();
        account.setUserId(result.getInt("user_id"));
        account.setAccountId(result.getInt("account_id"));
        account.setBalance(result.getDouble("balance"));
        return account;
    }
}
