<template>
  <div>
    <h1>Landmarks</h1>
    <SearchHeader v-model="selectedCity" />
    <Accordion>
      <AccordionTab v-for="landmark in landmarks" :key="landmark.id" :header="landmark.displayName?.text">
        <h2>{{ landmark.displayName?.text }}</h2>
        <p v-if="landmark.formattedAddress"><strong>Address:</strong> {{ landmark.formattedAddress }}</p>
        <div v-if="typeof landmark.rating === 'number'">
      <strong>Rating:</strong>
      <Rating v-model="landmark.rating" :cancel="false" readonly></Rating>
    </div>        
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

        <p v-if="landmark.location && landmark.location.latitude && landmark.location.longitude">
          <GoogleMap :latitude="landmark.location.latitude" :longitude="landmark.location.longitude" />

          <strong>Location:</strong> Latitude: {{ landmark.location.latitude }}, Longitude: {{ landmark.location.longitude }}
        </p>
        <p v-if="landmark.types"><strong>Types:</strong> {{ landmark.types.join(', ') }}</p>
        <p v-if="landmark.accessibilityOptions"><strong>Accessibility Options:</strong> {{ formatAccessibilityOptions(landmark.accessibilityOptions) }}</p>
      </AccordionTab>
    </Accordion>
  </div>
</template>




  
  
<script>
import { ref, onMounted, watch } from 'vue';
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import LandmarkService from '../services/LandmarkService';
import Rating from 'primevue/rating';
import SearchHeader from '../components/SearchHeader.vue';

export default {
  components: {
    Accordion,
    AccordionTab,
    Rating,
    SearchHeader
  },
  setup() {
    const selectedCity = ref();
    const groupedCities = ref([
    {
        label: 'Germany',
        code: 'DE',
        items: [
            { label: 'Berlin', value: 'Berlin' },
            { label: 'Frankfurt', value: 'Frankfurt' },
            { label: 'Hamburg', value: 'Hamburg' },
            { label: 'Munich', value: 'Munich' }
        ]
    },
    {
        label: 'USA',
        code: 'US',
        items: [
            { label: 'Chicago', value: 'Chicago' },
            { label: 'Los Angeles', value: 'Los Angeles' },
            { label: 'New York', value: 'New York' },
            { label: 'San Francisco', value: 'San Francisco' }
        ]
    },
    {
        label: 'Japan',
        code: 'JP',
        items: [
            { label: 'Kyoto', value: 'Kyoto' },
            { label: 'Osaka', value: 'Osaka' },
            { label: 'Tokyo', value: 'Tokyo' },
            { label: 'Yokohama', value: 'Yokohama' }
        ]
    }    ]);
    const landmarks = ref([]);

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
}
</style>