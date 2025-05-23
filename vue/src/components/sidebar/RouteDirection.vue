<template>
    <b-nav-form>
        <b-form-radio-group
        id="btn-radios-1"
        v-model="dirselected"
        :options="getDirectionOptions"
        buttons
        button-variant="primary"
        name="radios-btn-default"
        @change = "changedir"
        ></b-form-radio-group>
    </b-nav-form>
</template>

<script>
export default {
  name: 'RouteDirection',
  components: {},
  props:{
    isOneWay:{
      type:Boolean,
      default:false
    }
  },
  computed:{
    getDirectionOptions:function(){
      let r = [];

      if(this.isOneWay === true)
      {
        r.push({ text: '往', value: true });
      }
      else
      {
        r.push({ text: '往', value: true });
        r.push({ text: '返', value: false });
      }

      return r;
    }
  },
  methods:{
    changedir : function (value) {
        this.$emit('change-dir' , value);
    }
  },
  data() {
    return {
      dirselected: true
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>