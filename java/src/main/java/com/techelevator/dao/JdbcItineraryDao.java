package com.techelevator.dao;

import com.techelevator.model.itinerary.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Itinerary createItinerary(int itineraryId){

        Itinerary newItinerary = new Itinerary();
        String sql = "INSERT INTO itinerary (event_date, event_start_time, event_end_time)\n" +
                "VALUES (?, ?, ?);";
        try{

            jdbcTemplate.update(sql, itineraryId);


        } catch (Exception ex){
            System.out.println("Something went wrong create itinerary ");
        }
        return newItinerary;
    }
    @Override
    public List<Itinerary> getItineraries(){
        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "SELECT * FROM itinerary;";

        try{

            //SqlRowSet results = jdbcTemplate.queryForRowSet(sql, )

        } catch (Exception ex){
            System.out.println("Something went wrong");
        }

        return itineraries;
    }
    @Override
    public Itinerary getItinerary(int itineraryId){
        Itinerary itinerary = null;
        String sql="SELECT * FROM itinerary WHERE itinerary_id=?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);
            while(results.next()){
                itinerary = mapRowToItinerary(results);
            }
        }catch (Exception ex){
            System.out.println("Something went wrong: get itinerary");
        }
        return itinerary;
    };
    @Override
    public Itinerary editItinerary(Itinerary itinerary, int itineraryId){
        return null;
    }
    @Override
    public Itinerary deleteItinerary(int itineraryId){
        return null;
    }

    @Override
    public void addLandmarkToItinerary(int itineraryId, String placeId){
        String sql = "INSERT INTO itinerary(place_id)\n" +
                "WHERE itinerary_id equals ?\n" +
                "VALUES (?);";
        try{

            jdbcTemplate.update(sql, itineraryId, placeId);


        } catch (Exception ex){
            System.out.println("Something went wrong: add landmark to itinerary");
        }
    }

    private Itinerary mapRowToItinerary(SqlRowSet results){
        Itinerary itinerary = new Itinerary();

        itinerary.setItineraryId(results.getInt("itinerary_id"));
        itinerary.setPlaceId(results.getString("place_id"));
        if (results.getTime("event_start_time") != null){
            itinerary.setStartTime(results.getTime("event_start_time").toLocalTime());
        }
        if (results.getTime("event_end_time") != null){
            itinerary.setStartTime(results.getTime("event_end_time").toLocalTime());
        }
        if (results.getDate("event_date") != null){
            itinerary.setEventDate(results.getDate("event_date").toLocalDate());
        }
        return itinerary;


    }




}
