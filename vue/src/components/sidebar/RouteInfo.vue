<template>
    <b-navbar-nav>
        <b-nav-text>{{ routeDesc }}</b-nav-text>
        <b-button size="sm" variant="dark" @click="queryBusInfo">路線資訊</b-button>
    </b-navbar-nav>
</template>

<script>
export default {
  name: 'RouteInfo',
  components: {},
  props:{
      routeDesc:{
          type: String,
          default:''
      },
      routeID:{
          type: Number,
          default: 0
      }
  },
  methods:{
      queryBusInfo : function (e) {
          window.open("http://www.2384.com.tw/ebus/pathInfo.jsp?pathId=" + this.routeID , this.routeID)
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>