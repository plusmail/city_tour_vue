import axios from 'axios';
const itineraryUrl = '/itinerary/';
export default {
    returnAllItinerary() {
      return axios.get(itineraryUrl + 'find_all')
    },
    addLandmarksToItinerary(id) {
      return axios.post(itineraryUrl + '/itinerary/add_landmark?itinerary_id=' + id)
    }
  
  }