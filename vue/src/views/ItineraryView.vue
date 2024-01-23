<template>
    <div class="landmarks-container">
      <h1>Explore Landmarks</h1>
      <Button label="Remove From Itinerary" class="remove-itinerary-btn" @click="removeFromItinerary" />
  
      <Accordion>
        <AccordionTab v-for="landmark in landmarks" :key="landmark.id" class="landmark-tab">
          <template #header>
            <div class="accordion-header">
              <span>{{ landmark.displayName?.text }}</span>
              <Checkbox :value="landmark.id" v-model="selectedLandmarks" />
            </div>
          </template>
  
          <div class="landmark-details">
            <div class="landmark-info">
              <h2>{{ landmark.displayName?.text }}</h2>
              <p v-if="landmark.formattedAddress"><strong>Address:</strong> {{ landmark.formattedAddress }}</p>
              <p v-if="landmark.types"><strong>Types:</strong> {{ landmark.types.join(', ') }}</p>
              <p v-if="landmark.accessibilityOptions"><strong>Accessibility Options:</strong> {{ formatAccessibilityOptions(landmark.accessibilityOptions) }}</p>
            </div>
  
            <div class="landmark-media">
              <!-- ImageMapSwap component will handle toggling between image and map -->
              <ImageMapSwap 
  v-if="landmark.photos.length > 0 || (landmark.location && landmark.location.latitude && landmark.location.longitude)"
  :photos="landmark.photos"
  :displayName="landmark.displayName?.text"
  :city="selectedCity.value"
/>

              
              <div v-if="landmark.currentOpeningHours && landmark.currentOpeningHours.weekdayDescriptions" class="hours-table">
                <h3>Opening Hours</h3>
                <table>
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
            </div>
          </div>
        </AccordionTab>
      </Accordion>
    </div>
  </template>
  
  
  
  <script>
  import Checkbox from 'primevue/checkbox';
  import { ref, onMounted, watch } from 'vue';
  import Accordion from 'primevue/accordion';
  import AccordionTab from 'primevue/accordiontab';
  import ItineraryService from '../services/ItineraryService';
  import Button from 'primevue/button'; // Import Button component
  import ImageMapSwap from '../components/ImageMapSwap.vue';
  
  export default {
    components: {
    ImageMapSwap,
      Accordion,
      AccordionTab,
      Checkbox,
      Button, // Register Button component
    },
    setup() {
      const selectedCity = ref();
      const landmarks = ref([]);
      const selectedLandmarks = ref([]);
  
      const removeFromItinerary = async () => {
        try {
          for (let id of selectedLandmarks.value) {
            await fetch(`/itinerary/delete?itinerary_id=${id}`, {
              method: 'POST',
            });
          }
        } catch (error) {
          console.error('Error removing from itinerary:', error);
        }
      };
  
      const fetchLandmarks = async () => {
        try {
          const cityName = selectedCity.value?.value;
          if (cityName) {
            const response = await ItineraryService.returnAllItinerary();
            if (response.status === 200) {
              landmarks.value = response.data.places;
            }
          } else {
            console.error("Error fetching landmarks by Itinerary ID");
          }
        } catch (error) {
          console.error('Error fetching landmarks:', error);
        }
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
        selectedLandmarks,
        getDayFromHoursString,
        getHoursFromHoursString,
        formatAccessibilityOptions
      };
    }
  }
  </script>
  
  
  <style scoped>
  .landmarks-container {
    padding: 1rem;
    max-width: 1200px;
    margin: auto;
  }
  
  h1 {
    text-align: center;
    margin-bottom: 2rem;
  }
  
  .add-itinerary-btn {
    display: block;
    margin: 1rem auto;
    color: #ffffff;
    background-color: #10c469; /* PrimeVue Soho Dark theme success color */
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
  }
  
  .accordion-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 1.1rem;
    padding: 0.5rem 1rem;
    border-radius: 4px;
  }
  
  .landmark-tab {
    margin-bottom: 1rem;
    border: 1px solid #444; /* Slightly lighter border for contrast */
    background-color: #262626; /* Dark background for tab content */
    border-radius: 8px;
  }
  
  .landmark-details {
    display: grid;
    grid-template-columns: 1fr 2fr;
    gap: 20px;
    padding: 1rem;
  }
  
  .landmark-info, .map-container, .image-container, .hours-table {
    background-color: #333333; /* Dark backgrounds for content */
    margin-top: 8px;    
    padding: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5); /* Subtle shadow for depth */
  }
  
  .image-container img {
    width: 100%;
    height: auto;
    border-radius: 5px;
  }
  
  .hours-table table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .hours-table th, .hours-table td {
    border: 1px solid #555; /* Border color for table */
    padding: 8px;
    text-align: left;
  }
  
  @media (max-width: 768px) {
    .landmark-details {
      grid-template-columns: 1fr;
    }
  
    .accordion-header {
      flex-direction: column;
      align-items: flex-start;
    }
  }
  
  /* Additional styles for checkbox alignment */
  .checkbox-container {
    display: flex;
    align-items: center;
    column-gap: 0.5rem; /* No affect, fix */
  }
  </style>