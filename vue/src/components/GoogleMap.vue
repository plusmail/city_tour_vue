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
      location: String
    },
    computed: {
      mapUrl() {
        if (!this.location) {
          return null;
        }
        const baseUrl = "https://www.google.com/maps/embed/v1/place";
        const apiKey = "AIzaSyB3DzEl4eOx63tJTTcmByC3PccyAthJRyA";
        return `${baseUrl}?key=${apiKey}&q=${encodeURIComponent(this.location)}`;
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
  