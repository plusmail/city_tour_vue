<template>
    <div class="search-header">
      <AutoComplete 
        placeholder="Search for a city..." 
        v-model="selectedCity" 
        :suggestions="citySuggestions"
        @completeMethod="searchCities"
        field="name"
        @select="onCitySelect"/>
  
      <h2 v-if="selectedCity">{{ selectedCity.name }}</h2>
    </div>
  </template>
  
  <script>
  import AutoComplete from 'primevue/autocomplete';
  
  export default {
    components: {
      AutoComplete
    },
    data() {
      return {
        selectedCity: null,
        citySuggestions: []
      };
    },
    methods: {
      searchCities(event) {
      },
      onCitySelect(city) {
        this.selectedCity = city;
        this.$emit('citySelected', city);
      }
    }
  };
  </script>
  