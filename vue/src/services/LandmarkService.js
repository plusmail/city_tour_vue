import axios from 'axios';

const BASE_URL = '/landmarks/';

export default {
    returnLandmarks(city) {
        return axios.get(`${BASE_URL}search?city=${city}`);
    },

    fetchLandmarkDetails(placeId) {
        return axios.get(`${BASE_URL}find_by_place_id/${placeId}`);
    }
}
