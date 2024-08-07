<template>
    <div class="items">
      <h1>This is the items page</h1>
        
    </div>
  </template>


<script>
import axios from 'axios';

export default{
    name:"items",
    data(){
        return{
            items:[]
        }
    },

mounted() {

axios.get("http://localhost:8080/api/v1/items/beer-items").then(
    res => {this.items.push(res)}
)

axios.get("http://localhost:8080/api/v1/items/bread-items").then(
    res => {this.items.push(res)})


axios.get("http://localhost:8080/api/v1/items/vegetable-items").then(
    res => {this.items.push(res)})

}
}
</script>