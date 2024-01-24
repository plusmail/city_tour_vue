import axios from 'axios';
const BASE_URL = '/itinerary/';

export default {
  returnAllItinerary() {
    return axios.get(BASE_URL + 'find_all');
  },
  createItinerary(itinerary) {
    return axios.post(BASE_URL + 'create', itinerary);
  },
  updateItinerary(itinerary) {
    return axios.put(BASE_URL + 'update', itinerary); // Change to PUT for update
  },
  deleteItinerary(itineraryId) {
    return axios.delete(BASE_URL + 'delete', { params: { itinerary_id: itineraryId } }); // Change to DELETE for delete
  },
  addLandmarkToItinerary(itineraryId, placeId) {
    return axios.post(BASE_URL + 'add_landmark', {
        itinerary_id: itineraryId,
        place_id: placeId
    });
}
,
  findById(itineraryId) {
    return axios.get(BASE_URL + 'find', { params: { itinerary_id: itineraryId } });
  }
};
