<template>
  <div class="home">
    <h1>Home</h1>
    <GoogleMap/>

  </div>
</template>

<script>
import GoogleMap from '../components/GoogleMap.vue';

export default {
  components: {
    GoogleMap
  },
  methods: {
    citySelection(city) {
      
    },
    methods: {
      landmarks() {
  // LandmarkService
  //   .returnLandmarks(this.citySelected)
  //   .then(response => {
  //     if (response.status == 200) {
  //       this.$store.commit('setLandmarks', response.data.places);


  //     }
  //   })
  //   .catch(error => {
  //     if (error.response && error.response.status === 401) {
  //       this.invalidCredentials = true;
  //     } else {
  //       console.error('Error fetching landmarks:', error);
  //     }
  //   });
  // }
}
  }
}
}
</script>