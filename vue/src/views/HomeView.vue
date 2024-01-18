<template>
  <div class="home">
    <h1>Home</h1>
<Dropdown @citySelected="citySelection" v-model="selectedCity" :options="groupedCities" optionLabel="label" optionGroupLabel="label" optionGroupChildren="items" placeholder="Select a City" class="w-full md:w-14rem">
    <template #optiongroup="slotProps">
        <div class="flex align-items-center">
            <img :alt="slotProps.option.label" src="https://primefaces.org/cdn/primevue/images/flag/flag_placeholder.png" :class="`mr-2 flag flag-${slotProps.option.code.toLowerCase()}`" style="width: 18px" />
            <div>{{ slotProps.option.label }}</div>
        </div>
    </template>
</Dropdown>
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
        // Assuming you want to store the landmarks data in your Vuex store
        this.$store.commit('setLandmarks', response.data.places);

        // Navigate to another route, if needed
        // this.$router.push('/your-route');

        // Any other specific actions after successful response
      }
    })
    .catch(error => {
      if (error.response && error.response.status === 401) {
        this.invalidCredentials = true;
      } else {
        // Handle other types of errors (e.g., network issues, server errors)
        console.error('Error fetching landmarks:', error);
      }
    });
  }
}
  }
}
</script>