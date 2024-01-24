<template>
  <div class="home">
    <h1>Home</h1>
    <SearchHeader v-model="selectedCity" />
    <DeckGLMap/>
    <GoogleMap/>
    
    <Recent/>
  </div>
</template>

<script>
import SearchHeader from '../components/SearchHeader.vue';
import Recent from '../components/Recent.vue';
import GoogleMap from '../components/GoogleMap.vue';
import DeckGLMap from '../components/DeckGLMap.vue';
import LandmarkService from '../services/LandmarkService';
export default {
  components: {
    SearchHeader,
    Recent,
    GoogleMap,
    DeckGLMap
  },
  methods: {
    citySelection(city) {
      
    },
    methods: {
      landmarks() {
  LandmarkService
    .returnLandmarks(this.citySelected)
    .then(response => {
      if (response.status == 200) {
        this.$store.commit('setLandmarks', response.data.places);


      }
    })
    .catch(error => {
      if (error.response && error.response.status === 401) {
        this.invalidCredentials = true;
      } else {
        console.error('Error fetching landmarks:', error);
      }
    });
  }
}
  }
}
</script>