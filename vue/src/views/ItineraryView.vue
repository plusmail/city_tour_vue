<template>
  <div class="itinerary-container">
    <h1><i class="pi pi-fw pi-calendar-plus"></i> Itinerary Planner</h1>
    <div class="itinerary-form">
      <div class="form-group">
        <InputText placeholder="Itinerary Name" v-model="itineraryName" />
        <p v-if="errors.name" class="error-message">{{ errors.name }}</p>
      </div>
      <div class="form-group">
        <Calendar placeholder="Select Date" v-model="itineraryDate" 
                  showTime hourFormat="24" :minDate="new Date()" 
                  dateFormat="yy-mm-dd" />
        <p v-if="errors.date" class="error-message">{{ errors.date }}</p>
      </div>
      <div class="form-group">
        <LandmarkQuickSelect v-if="selectedCity" :city="selectedCity" 
                             v-model="startingPoint" />
      </div>
      <div class="form-actions">
        <Button label="Create Itinerary" class="btn" icon="pi pi-check" 
                @click="createItinerary" :disabled="isLoading" />
        <Button label="Edit Itinerary" class="btn" icon="pi pi-pencil" 
                @click="editItinerary" :disabled="isLoading" />
        <Button label="Delete Itinerary" class="btn" icon="pi pi-trash" 
                @click="deleteItinerary" :disabled="isLoading" />
      </div>
    </div>
    <div v-if="message.text" class="message" 
         :class="{'message-success': message.type === 'success', 
                  'message-error': message.type === 'error'}">
      {{ message.text }}
    </div>
    <div class="landmarks-section">
      <h2><i class="pi pi-fw pi-map-marker"></i> Landmarks</h2>
      <div class="landmarks-list">
        <div class="landmark-item">
          <Dropdown class="landmark-name" :options="landmarks" optionLabel="placeId" v-model="dropdownLandmarks" 
              placeholder="Select a Landmark"></Dropdown>
          <Dropdown class="itinerary-name" :options="itineraries" optionLabel="name" v-model="selectedItinerary" 
              placeholder="Select an Itinerary"></Dropdown>
        </div>
      </div>
      <Button label="Add Selected Landmarks" class="btn" icon="pi pi-plus" 
              @click="addLandmarksToItinerary" :disabled="isLoading" />
    </div>
  </div>
</template>

  
  
<script>
import { ref, reactive } from "vue";
import InputText from "primevue/inputtext";
import Calendar from "primevue/calendar";
import Button from "primevue/button";
import Dropdown from "primevue/dropdown";
import ItineraryService from "../services/ItineraryService";
import LandmarkQuickSelect from "../components/LandmarkQuickSelect.vue";
import LandmarkService from "../services/LandmarkService";


export default {
  components: {
    InputText,
    Calendar,
    Button,
    LandmarkQuickSelect,
    Dropdown
  },
  setup() {
    const itineraryName = ref("");
    const itineraryDate = ref(null);
    const selectedCity = ref(null);
    const startingPoint = ref(null);
    const landmarks = ref([]);
    const selectedLandmarks = ref([]);
    const createdItineraryId = ref(null);
    const isLoading = ref(false);
    const message = reactive({ text: "", type: "" });
    const errors = reactive({ name: "", date: "" });
    const itineraries = ref([]);
    const selectedItinerary = ref();
    const selectedPlaceId = ref(null);
    const dropdownLandmarks = ref([]);

    async function getLandmarkDetails(placeId) {
      try {
        const response = await LandmarkService.fetchLandmarkDetails(placeId);
      } catch (error) {
        console.error("Error fetching landmark details:", error);
      }
    }

    const validateForm = () => {
      errors.name = itineraryName.value ? "" : "Itinerary name is required.";
      errors.date = itineraryDate.value ? "" : "Itinerary date is required.";
      return !errors.name && !errors.date;
    };

    const formatDate = (date) => {
      if (!date) return null;
      return date.toISOString().split("T")[0];
    };

    const fetchItineraryLandmarks = async () => {
      try {
        const response = await ItineraryService.returnAllItinerary();
        landmarks.value = response.data;
      } catch (error) {
        console.error("Error fetching landmarks:", error);
      }
    };
    
    fetchItineraryLandmarks();

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
        console.error("Error fetching landmarks:", error);
      }
    };

    fetchLandmarks();

    const fetchItineraries = async () => {
      try {
        const response = await ItineraryService.returnAllItinerary();
        itineraries.value = response.data;
      } catch (error) {
        console.error("Error fetching itineraries:", error);
      }
    };

    fetchItineraries();

    const createItinerary = async () => {
      if (!validateForm()) return;
      isLoading.value = true;
      try {
        const newItinerary = {
          name: itineraryName.value,
          startingPoint: startingPoint.value,
          eventDate: formatDate(itineraryDate.value),
          landmarks: selectedLandmarks.value.map((landmark) => landmark.id),
        };
        const response = await ItineraryService.createItinerary(newItinerary);
        createdItineraryId.value = response.data;
        message.text = "Itinerary created successfully.";
        message.type = "success";
      } catch (error) {
        message.text = "Error creating itinerary: " + error.message;
        message.type = "error";
      } finally {
        isLoading.value = false;
      }
    };

    const editItinerary = async () => {
      isLoading.value = true;
      try {
        // Assume we have the edited itinerary data
        const editedItinerary = { /* ... */ };
        await ItineraryService.updateItinerary(editedItinerary);
        message.text = "Itinerary updated successfully.";
        message.type = "success";
      } catch (error) {
        message.text = "Error updating itinerary: " + error.message;
        message.type = "error";
      } finally {
        isLoading.value = false;
      }
    };

    const deleteItinerary = async () => {
      if (confirm("Are you sure you want to delete this itinerary?")) {
        isLoading.value = true;
        try {
          console.log(selectedItinerary.value);
          await ItineraryService.deleteItinerary(selectedItinerary.value.itineraryId);
          message.text = "Itinerary deleted successfully.";
          message.type = "success";
        } catch (error) {
          message.text = "Error deleting itinerary: " + error.message;
          message.type = "error";
        } finally {
          isLoading.value = false;
        }
      }
    };

    const addLandmarksToItinerary = async () => {
      if (!createdItineraryId.value) return;
      isLoading.value = true;
      try {
        for (const landmarkId of selectedLandmarks.value) {
          await ItineraryService.addLandmarkToItinerary(
            createdItineraryId.value,
            landmarkId
          );
        }
        message.text = "Landmarks added to itinerary successfully.";
        message.type = "success";
      } catch (error) {
        message.text = "Error adding landmarks to itinerary: " + error.message;
        message.type = "error";
      } finally {
        isLoading.value = false;
      }
    };

    return {
      itineraryName,
      itineraryDate,
      selectedCity,
      startingPoint,
      landmarks,
      selectedLandmarks,
      createdItineraryId,
      createItinerary,
      editItinerary,
      deleteItinerary,
      addLandmarksToItinerary,
      isLoading,
      message,
      errors,
      itineraries,
      selectedItinerary,
      selectedPlaceId,
      dropdownLandmarks
    };
  },
};
</script>


  
  
  

  <style scoped>
.itinerary-container h1 i,
.landmarks-section h2 i {
  margin-right: 0.5rem;
}
.error-message {
  color: red;
  font-size: 0.8em;
}

.message {
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
  text-align: center;
}

.message-success {
  background-color: #d4edda;
  color: #155724;
}

.message-error {
  background-color: #f8d7da;
  color: #721c24;
}

.form-group {
  margin-bottom: 15px;
}

.form-actions {
  display: flex;
  justify-content: space-between;
}

.landmark-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.landmark-name {
  margin-left: 10px;
  cursor: pointer;
}
</style>
