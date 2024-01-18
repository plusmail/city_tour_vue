import axios from 'axios';

export default {

    returnLandmarks(city) {
      return axios.post('/landmarks/', city)
    }
  
  }