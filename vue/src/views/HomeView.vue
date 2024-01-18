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