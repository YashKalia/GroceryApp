<template>
      <div class="GroceryItemsCustomizeContainer">
        <div v-for="(itemType) in itemTypes" class="ProductTitle">
          <h1>Customize {{itemType}}</h1>
            <div class="GridItemsContainer">       
              <ItemCustomizeCard v-for="(item) in items.filter( item => item.itemType == itemType)" 
              :itemName="item.itemName" 
              :price ="item.price"
              :itemType ="item.itemType"
              :id = "item.id"
              :isPack = "item.isPack"
              :discountId = "item.discountId"
              :age = "item.age"
              :weight = "item.weight"
              :productImage = "item.productImage"/>
            </div>
        </div>
      </div>
  </template>
  
  <script setup>
  import { getAllItems } from '@/services/ItemService';
  import ItemCustomizeCard from './ItemCards/ItemCustomizeCard.vue';
  
  import {ref, onMounted} from "vue";
  
  var items = ref([])
  var itemTypes = ref([]);
  
  
  onMounted(async () => {
        // This code will run when the component is mounted
        items.value = await getAllItems();
      console.log("items.value" , items.value)

      items.value.forEach(item => {
        if(!itemTypes.value.includes(item.itemType)){
          itemTypes.value.push(item.itemType)
        }
      });
      });
  
  
  </script>
  
  <style>
  .GroceryItemsCustomizeContainer{
    height:100%;
  
  }
  
  .GridItemsContainer{
    margin: 2rem;
    display:grid;
    grid-auto-flow: column;
  }
  
  .ProductTitle{
  display:block;
  border: 1px solid black;
  }
  
  </style>
  