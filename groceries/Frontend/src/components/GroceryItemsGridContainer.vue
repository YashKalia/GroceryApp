<template>
  <div class="GroceryItemsGridContainer" >
    {{ console.log(breadItems) }}
    <RecieptPopUp :isVisible ="isRecieptVisible" :reciept="reciept" @update:visible="isRecieptVisible = $event"/>
    <button type="button" class="btn btn-primary" className="CheckoutButton" @click="handleCheckout()">Checkout-></button>
    <div v-for="(itemType) in itemTypes" class="ProductTitle">
      <h1>{{itemType}}</h1>
      <div class="GridItemsContainer">       
        <ItemCard v-for="(item) in items.filter( item => item.itemType == itemType)" 
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
import ItemCard from './ItemCards/ItemCard.vue';
import { CheckOut } from '@/services/CheckoutService';
import RecieptPopUp from './Reciept/RecieptPopUp.vue';
import { useItemStore } from '@/itemstore';

import {ref, onMounted} from "vue";

const isRecieptVisible = ref(false);

var breadItems = ref([])
var reciept = ref({})
var items = ref([])


const itemStore = useItemStore();

var storeItems = ref(itemStore.cartItems);

var itemTypes = ref([]);

async function handleCheckout() {
      reciept.value = await CheckOut();
      isRecieptVisible.value = true;
      itemStore.emptyEntireCart();

}

// function clostRecieptPopup(close){
//   isRecieptVisible.value = close
// }

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
.GroceryItemsGridContainer{
  border: 1px solid black;
  height:100%;

}

.GridItemsContainer{
  border: 1px solid black;
  margin: 2rem;
  display:grid;
  grid-auto-flow: column;
}

.ProductTitle{
display:block;
border: 1px solid black;

.CheckoutButton{
  float: right
}

}
</style>
