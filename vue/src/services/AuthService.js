import axios from 'axios';

export default {

  login(user) {
    console.log("login->", user)
    return axios.post('http://localhost:9000/login', user)
  },

  register(user) {
    console.log(user)
    return axios.post('/register', user)
  }

}
