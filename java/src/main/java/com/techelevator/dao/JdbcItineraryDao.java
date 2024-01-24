package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Itinerary itinerary) {
        String sql = "INSERT INTO itinerary (name, event_date, start_time, end_time, starting_point) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING itinerary_id";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,
                    itinerary.getName(),
                    itinerary.getEventDate(),
                    itinerary.getStartTime(),
                    itinerary.getEndTime(),
                    itinerary.getStartingPoint());

            if (result.next()) {
                int itineraryId = result.getInt("itinerary_id");
                itinerary.setItineraryId(itineraryId); // Set the itinerary_id back into the Itinerary object
                return itineraryId;
            } else {
                throw new DaoException("Itinerary not created");
            }
        } catch (Exception e) {
            throw new DaoException("Itinerary not created: " + e.getMessage());
        }
    }


    @Override
    public Itinerary findById(int itineraryId) {
        String sql = "select * from itinerary where itinerary_id = ?;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itineraryId);
            if (result.next()) {
                return mapRowToItinerary(result);
            }
        } catch (Exception e) {
            String message = String.format("Itinerary not found: itinerary_id(%s)", itineraryId);
            throw new DaoException(message);
        }
        return null;
    }

    @Override
    public List<Itinerary> findAll() {
        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "select * from itinerary;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                itineraries.add(mapRowToItinerary(result));
            }
        } catch (Exception e) {
            throw new DaoException("Itinerary not found");
        }
        return itineraries;
    }

    @Override
    public Itinerary update(Itinerary itinerary) {
        Itinerary updatedItinerary = null;
        String sql = "update itinerary set event_date = ?, start_time = ?, end_time = ? where itinerary_id = ?";

        try {
            int itineraryId = itinerary.getItineraryId();

            if (itinerary.getItineraryId() == 0)
                throw new DaoException("Missing required value: itineraryId");

            if (itinerary.getEventDate() == null)
                itinerary.setEventDate(findById(itineraryId).getEventDate());

            if (itinerary.getStartTime() == null)
                itinerary.setStartTime(findById(itineraryId).getStartTime());

            if (itinerary.getEndTime() == null)
                itinerary.setEndTime(findById(itineraryId).getEndTime());

            jdbcTemplate.update(sql,
                    itinerary.getEventDate(),
                    itinerary.getStartTime(),
                    itinerary.getEndTime(),
                    itinerary.getItineraryId());

            updatedItinerary = findById(itineraryId);
        } catch (Exception e) {
            String message = String.format("Itinerary not found: itinerary_id(%s)", itinerary.getItineraryId());
            throw new DaoException(message);
        }
        return updatedItinerary;
    }

    @Override
    public void delete(int itineraryId) {
        String sql = "delete from users_itinerary where itinerary_id = ?;\n" +
                "delete from itinerary where itinerary_id = ?;";

        try {
            jdbcTemplate.update(sql, itineraryId);
        } catch (Exception e) {
            String message = String.format("Itinerary not delete: itinerary_id(%s)", itineraryId);
            throw new DaoException(message);
        }
    }

    @Override
    public int addLandmarkToItinerary(int itineraryId, String placeId) {
        String sql = "INSERT INTO itinerary_landmarks (itinerary_id, place_id) VALUES (?, ?) returning itinerary_id;";
        try {
            System.out.println("Inserting place ID: " + placeId + " into itinerary ID: " + itineraryId); // Log the place ID and itinerary ID
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itineraryId, placeId);
            if (result.next()) {
                return result.getInt("itinerary_id");
            } else {
                throw new DaoException("Landmark not added to itinerary");
            }
        } catch (Exception e) {
            throw new DaoException("Landmark not added to itinerary: " + e.getMessage());
        }
    }


    @Override
    public void removeLandmarkFromItinerary(int itineraryId, String placeId) {
        String sql = "delete from itinerary_landmarks where itinerary_id = ? and place_id = ?;";

        try {
            jdbcTemplate.update(sql, itineraryId, placeId);
        } catch (Exception e) {
            String message = String.format("Landmark not removed from itinerary: itinerary_id(%s), place_id(%s)", itineraryId, placeId);
            throw new DaoException(message);
        }
    }
    @Override
    public void removeLandmarkFromAllItinerary(String placeId) {
        String sql = "delete from itinerary_landmarks where place_id = ?;";

        try {
            jdbcTemplate.update(sql, placeId);
        } catch (Exception e) {
            String message = String.format("Landmark not removed from itinerary: place_id(%s)", placeId);
            throw new DaoException(message);
        }
    }

//    public static class LandmarkRequest {
//        private int itineraryId;
//        private String placeId;
//
//        public int getItineraryId() {
//            return itineraryId;
//        }
//
//        public void setItineraryId(int itineraryId) {
//            this.itineraryId = itineraryId;
//        }
//
//        public String getPlaceId() {
//            return placeId;
//        }
//
//        public void setPlaceId(String placeId) {
//            this.placeId = placeId;
//        }
//    }

    private Itinerary mapRowToItinerary(SqlRowSet result) {
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(result.getInt("itinerary_id"));
        itinerary.setName(result.getString("name"));

        if (result.getDate("event_date") != null)
            itinerary.setEventDate(result.getDate("event_date").toLocalDate());

        if (result.getTime("start_time") != null)
            itinerary.setStartTime(result.getTime("start_time").toLocalTime());

        if (result.getTime("end_time") != null)
            itinerary.setEndTime(result.getTime("end_time").toLocalTime());

        itinerary.setStartingPoint(result.getString("starting_point"));

        return itinerary;
    }
}
