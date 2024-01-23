<template>
  <div>
    <h1>Landmarks</h1>
    <SearchHeader v-model="selectedCity" />
    <Button label="Add To Itinerary" @click="addToItinerary" />
    <Accordion>
      <AccordionTab v-for="landmark in landmarks" :key="landmark.id">
        <template #header>
          <div class="accordion-header">
          <span>{{ landmark.displayName?.text }}</span>
          <Checkbox :value="landmark.id" v-model="selectedLandmarks" ></Checkbox>
        </div>
        </template>
        <h2>{{ landmark.displayName?.text }}</h2>
        <p v-if="landmark.formattedAddress"><strong>Address:</strong> {{ landmark.formattedAddress }}</p>
        <!-- <div v-if="typeof landmark.rating === 'number'"> -->
      <!-- <strong>Rating:</strong>
      <Rating v-model="landmark.rating" :cancel="false" readonly></Rating> -->
    <!-- </div>         -->
        <div v-if="landmark.currentOpeningHours && landmark.currentOpeningHours.weekdayDescriptions">
          <h3>Opening Hours</h3>
          <table v-if="landmark.currentOpeningHours && landmark.currentOpeningHours.weekdayDescriptions">
  <thead>
    <tr>
      <th>Day</th>
      <th>Hours</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(hours, index) in landmark.currentOpeningHours.weekdayDescriptions" :key="index">
      <td>{{ getDayFromHoursString(hours) }}</td>
      <td>{{ getHoursFromHoursString(hours) }}</td>
    </tr>
  </tbody>
</table>
        </div>
        <p v-else>No opening hours information available</p>
        <div id="media-view">
          <p v-if="landmark.location && landmark.location.latitude && landmark.location.longitude" id="map-media">
          
            <GoogleMap :displayName="landmark.displayName?.text" :city="selectedCity.value" />

            <strong>Location:</strong> Latitude: {{ landmark.location.latitude }}, Longitude: {{ landmark.location.longitude }}
            
          </p>
          <div id="image-media">
            <img src="https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Fd8b08775-c331-4240-9183-0913c1af0cf5_1536x1024.png" />
          </div>  
        </div>
        <p v-if="landmark.types"><strong>Types:</strong> {{ landmark.types.join(', ') }}</p>
        <p v-if="landmark.accessibilityOptions"><strong>Accessibility Options:</strong> {{ formatAccessibilityOptions(landmark.accessibilityOptions) }}</p>
      </AccordionTab>
    </Accordion>
  </div>
</template>




  
  
<script>
import Checkbox from 'primevue/checkbox';
import { ref, onMounted, watch } from 'vue';
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import LandmarkService from '../services/LandmarkService';
import Rating from 'primevue/rating';
import SearchHeader from '../components/SearchHeader.vue';
import GoogleMap from '../components/GoogleMap.vue';

export default {
  components: {
    Accordion,
    AccordionTab,
    SearchHeader,
    GoogleMap,
    Checkbox,
  },
  setup() {
    const selectedCity = ref();
    const groupedCities = ref();
    const landmarks = ref([]);

    const selectedLandmarks = ref([]);

const addToItinerary = async () => {
  try {
    for (let id of selectedLandmarks.value) {
      await fetch(`/itinerary/add/${id}`, {
        method: 'POST',
      });
    }
  } catch (error) {
    console.error('Error adding to itinerary:', error);
  }
};

    const fetchLandmarks = async () => {
  try {
    const cityName = selectedCity.value?.value;

    if (cityName) {
      const response = await LandmarkService.returnLandmarks(cityName);
      if (response.status === 200) {
        landmarks.value = response.data.places;
        
      }
    } else {
      console.error("City name is not defined");
    }
  } catch (error) {
    console.error('Error fetching landmarks:', error);
  }
  console.log(landmarks.value);

};


    watch(selectedCity, (newValue, oldValue) => {
      if (newValue !== oldValue) {
        fetchLandmarks();
      }
    });

    const getDayFromHoursString = (hoursString) => {
      return hoursString.split(':')[0];
    };

    const getHoursFromHoursString = (hoursString) => {
      return hoursString.split(':').slice(1).join(':').trim();
    };

    const formatAccessibilityOptions = (options) => {
      if (!options) {
        return 'Not available';
      }
      return Object.entries(options)
        .filter(([_, value]) => value)
        .map(([key, _]) => humanizeString(key))
        .join(', ');
    };

    const humanizeString = (str) => {
      return str.replace(/([A-Z])/g, ' $1').trim();
    };

    onMounted(fetchLandmarks);

    return {
      landmarks,
      selectedCity,
      groupedCities,
      getDayFromHoursString,
      getHoursFromHoursString,
      formatAccessibilityOptions
    };
  }
}
</script>


<style scoped>
SearchHeader{
  margin-bottom: 0.5rem;
  align-self: center;
  /* display: flex; */
}
.accordion-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
/* AccordionTab{
  width: 50%;
} */


#media-view{
  display: grid;
  
  
}
#map-media{
  /* display: inline-block;
  width: 35%; */

}
#image-media{
  margin-left: 5%;
  width: 40%;
  height: 100%;
}



</style>