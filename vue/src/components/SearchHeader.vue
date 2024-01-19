<template>
    <Dropdown v-model="selectedCity" :options="groupedCities" optionLabel="label" optionGroupLabel="label" optionGroupChildren="items" placeholder="Select a City" class="w-full md:w-14rem">
    <template #optiongroup="slotProps">
        <div class="flex align-items-center">
            <img :alt="slotProps.option.label" src="https://primefaces.org/cdn/primevue/images/flag/flag_placeholder.png" :class="`mr-2 flag flag-${slotProps.option.code.toLowerCase()}`" style="width: 18px" />
            <div>{{ slotProps.option.label }}</div>
        </div>
    </template>
</Dropdown>
  </template>
  
  <script>
  import { ref, watch } from 'vue';
  import Dropdown from 'primevue/dropdown';
  
  export default {
    components: {
      Dropdown
    },
    props: {
      modelValue: {
        type: Object,
        default: () => ({})
      }
    },
    setup(props, { emit }) {
      // Local reactive state for the dropdown
      const selectedCity = ref(props.modelValue);
  
      // Watch for changes in the local state and emit an update
      watch(selectedCity, (newValue) => {
        emit('update:modelValue', newValue);
      });
  
      // Also watch for changes in props and update local state
      watch(() => props.modelValue, (newValue) => {
        if (newValue !== selectedCity.value) {
          selectedCity.value = newValue;
        }
      });
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
  }]);

watch(() => props.selectedCity, (newValue) => {
  emit('update:selectedCity', newValue);
});

return {
  selectedCity,
  groupedCities
};
}
};
</script>
  