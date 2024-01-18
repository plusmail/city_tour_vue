<template>
  <div class="home">
    <h1>Home</h1>
    <SearchHeader @citySelected="citySelection" />
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
export default {
  components: {
    SearchHeader,
    Recent,
    GoogleMap,
    DeckGLMap
  },
  methods: {
    citySelection(city) {
      // placeholder for inputting city selection logic
    }
  }
};
</script>