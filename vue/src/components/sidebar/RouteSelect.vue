<template>
    <b-nav-form>
        <b-form-select v-model="categoryselected" :options="categoryoptions"></b-form-select>
        <b-form-select v-model="routeselected" :options="routeoptions" @change="changeroute"></b-form-select>
    </b-nav-form>
</template>

<script>
export default {
  name: 'RouteSelect',
  props:{
    datas:{
        type: Array,
        default:() => []
    }
  },
  computed: {
    categoryoptions :function(){
      let options = [];

      for (let index = 0; index < this.datas.length; index++) {
        let element = this.datas[index];
    
        let option = { value: element.categoryIndex , text:element.categoryName };
        options.push(option);
      }
      return options;
    },
    routeoptions : function(){
      return this.getRouteSelectOptions(this.datas , this.categoryselected);
    }
  },
  data: () => ({
    categoryselected: 1,
    routeselected: 10450
  }),
  watch: {
    categoryselected : function(){
      this.detectRouteChange();
    }
  },
  methods:{
      changeroute : function (value) {
          this.$emit('change-route' , this.getRouteSelectData(this.datas , this.categoryselected , value));
      },
      getRouteSelectData : function(d , selC , selR){
        let category = d.find(element => element.categoryIndex === selC);
        let route = category.routes.find(ele => ele.RouteCode === selR);

        let routeData = { 
          categoryIndex:category.categoryIndex,
          routeid:route.RouteCode ,
          osmid:route.RouteOSMRelation ,
          routedesc:route.RouteDescription ,
          mainColor:category.categoryLineColor,
          extendColor:category.categoryLineColor2,
          oneWay:route.OneWay
        };

        return routeData;
      },
      getRouteSelectOptions:function(d , sel) {
        let category = d.find(element => element.categoryIndex === sel);
        let options = [];

        for (let index = 0; index < category.routes.length; index++) {
          let element = category.routes[index];

          let option = { value: element.RouteCode , text:element.RouteName};
          options.push(option);
        }

        return options;
      },
      detectRouteChange:function(){
        this.routeselected = this.getRouteSelectOptions(this.datas ,this.categoryselected)[0].value;
        this.changeroute(this.routeselected);
      }
  },
  mounted: function () {
    this.detectRouteChange();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>