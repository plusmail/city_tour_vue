package com.techelevator.dao;

import com.techelevator.model.searchlandmark.Landmarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarksDao implements LandmarksDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Landmarks> getLandmarks() {
        List<Landmarks> landmarks = new ArrayList<>();
        String sql = "SELECT * FROM landmarks;";

        try{

            //SqlRowSet results = jdbcTemplate.queryForRowSet(sql, )

        } catch (Exception ex){
            System.out.println("Something went wrong: get landmark");
        }

        return landmarks;
    }

    @Override
    public Landmarks getLandmark(String placeId) {
        return null;
    }

    @Override
    public Landmarks createLandmark(String placeId) {
        Landmarks newLandmark = new Landmarks();
        String sql = "INSERT INTO landmarks\n" +
                "(place_id)\n" +
                "VALUES (?);";
        try{

            jdbcTemplate.update(sql, placeId);


        } catch (Exception ex){
            System.out.println("Something went wrong: create landmark");
        }
        return newLandmark;



    }

//    private void addLandmarkToLandmarks(String placeId){
//        String sql =
//    }

    @Override
    public Landmarks updateLandmark(Landmarks landmark, String placeId) {
        return null;
    }

    @Override
    public Landmarks deleteLandmark(String placeId) {
        return null;
    }

//    public Landmarks mapRowToAccount(SqlRowSet result) {
//        Landmarks account = new Landmarks();
//        landmark.setUserId(result.getInt("user_id"));
//        account.setAccountId(result.getInt("account_id"));
//        account.setBalance(result.getDouble("balance"));
//        return account;
//    }
}
