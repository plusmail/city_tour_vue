<template>
<!--  <SideBar v-if="hasLoaded" id="sidebar" @change-route="routeChange" @change-dir="directionChange"-->
<!--           :routedata="savedData"></SideBar>-->
  <div id="capstone-app">
    <Menubar :model="items"/>
    <router-view/>
  </div>

</template>

<script>
import Menubar from "primevue/menubar";
import SideBar from './components/SideBar.vue'
import axios from 'axios'

let routeChange = function (value) {
  this.currentRouteData = value;
};

let directionChange = function (value) {
  this.showForward = value;
};

export default {
  components: {
    Menubar,
    SideBar,
  },
  methods: {
    routeChange,
    directionChange
  },
  created: function () {
    let _this = this;
    axios.get(`${window.location.origin}/AllBusRoutes.json`)
        .then(function (response) {
          console.log("json-> ", response)
          _this.savedData = response.data;
          _this.hasLoaded = true;
        });
  },
  data() {
    return {
      noclick: true,
      hasLoaded: true,
      savedData: [],
      showForward: true,
      currentRouteData: {
        categoryIndex: 1,
        routeid: 10450,
        osmid: 4016613,
        routedesc: "火車站(北)─火車站(北)",
        mainColor: "#9900CC",
        extendColor: "#D699EB"
      },
      items: [
        {
          label: "Search",
          command: () => {
            this.$router.push({name: "search"});
          },
        },
        {
          label: "Itinerary",
          command: () => {
            this.$router.push({name: "itinerary"});
          },
          visible: this.$store.state.token !== "",
        },
        {
          label: "Logout",
          command: () => {
            this.$router.push({name: "logout"});
          },
          visible: this.$store.state.token !== "",
        },
        {
          label: "Login",
          command: () => {
            this.$router.push({name: "login"});
          },
          visible: this.$store.state.token === "",
        }
      ],
    };
  },
};
</script>


<style>
html, body {
  height: 100%;
  width: 100%;
}

body {
  padding-top: 55px;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  height: 100%;
  width: 100%;
}

#map {
  width: auto;
  height: 100%;
}
</style>