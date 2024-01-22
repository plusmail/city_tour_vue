package com.techelevator.dao;

import com.techelevator.model.searchlandmark.Landmarks;
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

//    @Override
//    public List<Landmarks> list() {
//        List<Landmarks> landmarks = new ArrayList<>();
//        String sql = "SELECT * FROM landmarks;";
//
//        try {
//            //SqlRowSet results = jdbcTemplate.queryForRowSet(sql, )
//        } catch (Exception e) {
//            System.out.println("Something went wrong: get landmark");
//        }
//
//        return landmarks;
//    }

//    @Override
//    public Landmarks create(String placeId) {
//        Landmarks newLandmark = new Landmarks();
//        String sql = "INSERT INTO landmarks\n" +
//                "(place_id)\n" +
//                "VALUES (?);";
//        try {
//            jdbcTemplate.update(sql, placeId);
//        } catch (Exception e) {
//            System.out.println("Something went wrong: create landmark");
//        }
//
//        return newLandmark;
//    }
//
//    @Override
//    public Landmarks findByPlaceId(String placeId) {
//        return null;
//    }
//
//    @Override
//    public Landmarks update(Landmarks landmark, String placeId) {
//        return null;
//    }
//
//    @Override
//    public Landmarks delete(String placeId) {
//        return null;
//    }
//
//    private Landmarks mapRowToLandmark(SqlRowSet result) {
//        Landmarks landmark = new Landmarks();
//
//        return landmark;
//    }
}
