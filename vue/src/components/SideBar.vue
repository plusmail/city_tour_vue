<template>
<section>
  <b-navbar fixed="top"  type="dark" variant="info">
      <b-navbar-brand>臺南市公車路線地圖</b-navbar-brand>
      <b-collapse id="nav-collapse" is-nav>
        <RouteInfo :routeDesc="currentDesc" :routeID="currentRouteID"></RouteInfo>
        <b-navbar-nav class="ml-auto">
          <RouteSelect @change-route="routeChange" :datas="routedata"></RouteSelect>
          <RouteDirection @change-dir="routeDirChange" :isOneWay="oneWay"></RouteDirection>
        </b-navbar-nav>
      </b-collapse>
  </b-navbar>
</section>
</template>

<script>
import RouteInfo from './sidebar/RouteInfo.vue'
import RouteSelect from './sidebar/RouteSelect.vue'
import RouteDirection from './sidebar/RouteDirection.vue'

let routeChange = function (value) {
  this.currentDesc = value.routedesc;
  this.currentRouteID = value.routeid;
  this.oneWay = value.oneWay;

  this.$emit('change-route' , value);
};

let routeDirChange = function (value) {
  this.$emit('change-dir' , value);
};

export default {
  name: 'SideBar',
  components: {
    RouteInfo,
    RouteSelect,
    RouteDirection
  },
  methods:{
    routeChange,
    routeDirChange
  },
  data:function(){
    return{
      currentRouteID : 10450,
      currentDesc : "火車站(北)─火車站(北)",
      oneWay : false
    }
  },
  props:{
    routedata:{
        type: Array,
        default:() => []
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>