<template>
    <div>
      <h1>Landmarks</h1>
      <div v-for="landmark in landmarks" :key="landmark.id">
        <h2>{{ landmark.displayName.text }}</h2>
        <p>Address: {{ landmark.formattedAddress }}</p>
        <p>Rating: {{ landmark.rating }}</p>
      </div>
    </div>
  </template>
  
  
  <script>
  import LandmarkService from '../services/LandmarkService'; // Update the path accordingly
  
  export default {
    data() {
      return {
        landmarks: []
      };
    },
    created() {
      this.fetchLandmarks();
    },
    methods: {
      fetchLandmarks() {
        // Replace 'cityName' with the actual city name or a variable that holds the city name
        LandmarkService.returnLandmarks('cityName')
          .then(response => {
            if (response.status === 200) {
              this.landmarks = response.data.places;
            }
          })
          .catch(error => {
            console.error('Error fetching landmarks:', error);
          });
      }
    }
  };
  </script>
  