<template>
    <div class="google-map-container">
      <iframe
        v-if="mapUrl"
        :src="mapUrl"
        width="600"
        height="450"
        frameborder="0"
        style="border:0;"
        allowfullscreen="true"
        aria-hidden="false"
        tabindex="0">
      </iframe>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      latitude: Number,
      longitude: Number
    },
    computed: {
      mapUrl() {
        console.log(this.latitude, this.longitude, this.mapUrl);
        if (this.latitude == null || this.longitude == null) {
          return null;
        }
        const baseUrl = "https://www.google.com/maps/embed/v1/view";
        const apiKey = "AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA";
        const center = `${this.latitude},${this.longitude}`;
        const zoom = "14";
        return `${baseUrl}?key=${apiKey}&center=${center}&zoom=${zoom}`;
        
      }

    }
  };
  </script>
  
  
  <style>
  .google-map-container {
    width: 100%;
    height: 450px;
  }
  </style>