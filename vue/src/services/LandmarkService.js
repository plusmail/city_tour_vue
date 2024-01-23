import axios from 'axios';
const landmarkUrl = '/landmarks/';
export default {
    returnLandmarks(city) {
      return axios.get(landmarkUrl + 'search?city=' + city)
    }

  }