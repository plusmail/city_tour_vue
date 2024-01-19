import axios from 'axios';

export default {

    returnLandmarks(city) {
      return axios.get('/landmarks/list/' + city)
    }
  
  }