<template>
  <div class="accordion-header">
    <span>{{ landmark.displayName?.text }}</span>
    <Checkbox :value="landmark.id" v-model="checkedLandmarks"></Checkbox>
  </div>
</template>

<script>
import Checkbox from 'primevue/checkbox';

export default {
  components: {
    Checkbox
  },
  props: {
    landmark: Object
  },
  model: {
    prop: 'checkedLandmarks',
    event: 'change'
  }
};
</script>

<style scoped>
</style>
