<template>
  <div class="GroceryItemsGridContainer" >
    {{ console.log(breadItems) }}
    <RecieptPopUp :isVisible ="isRecieptVisible" :reciept="reciept" @update:visible="isRecieptVisible = $event"/>
    <button type="button" class="btn btn-primary" className="CheckoutButton" @click="handleCheckout()">Checkout-></button>
    <div class="ProductTitle">
      <h1>Beers</h1>
      <div class="GridItemsContainer">       
        <BeerItemCard v-for="(item) in beerItems" 
        :itemName="item.itemName" 
        :price ="item.price"
        :itemType ="item.itemType"
        :id = "item.id"
        :isPack = "item.isPack"
        :discountId = "item.discountId"/>
      </div>
    </div>
    
    <div class="ProductTitle">
      <h1>Breads</h1>
      <div class="GridItemsContainer">   
        <BreadItemCard v-for="(item) in breadItems" 
        :itemName="item.itemName" 
        :price ="item.price"
        :itemType ="item.itemType"
        :age = "item.age"
        :id = "item.id"/>

      </div>
    </div> 
    <div class="ProductTitle">
      <h1>Vegetables</h1>
      <div class="GridItemsContainer">   
        <VegetableItemCard v-for="(item) in vegetableItems" 
        :itemName="item.itemName" 
        :price ="item.price"
        :itemType ="item.itemType"
        :id = "item.id"
        :weight = "item.weight"/>
     </div>
    </div>  
  </div>
</template>

<script setup>
import { getBeerItems, getBreadItems, getVegetableItems, getAllItems } from '@/services/ItemService';
import BreadItemCard from './ItemCards/BreadItemCard.vue';
import VegetableItemCard from './ItemCards/VegetableItemCard.vue';
import BeerItemCard from './ItemCards/BeerItemCard.vue';
import { CheckOut } from '@/services/CheckoutService';
import RecieptPopUp from './Reciept/RecieptPopUp.vue';
import { useItemStore } from '@/itemstore';

import {ref, onMounted} from "vue";

const isRecieptVisible = ref(false);

var beerItems = ref([])
var breadItems = ref([])
var vegetableItems = ref([])
var reciept = ref({})
var items = ref([])


const itemStore = useItemStore();

var storeItems = ref(itemStore.cartItems);

async function handleCheckout() {
      reciept.value = await CheckOut();
      isRecieptVisible.value = true;
      itemStore.emptyEntireCart();

}

function clostRecieptPopup(close){
  isRecieptVisible.value = close
}

onMounted(async () => {
      // This code will run when the component is mounted
      beerItems.value = await getBeerItems();
      breadItems.value = await getBreadItems();
      vegetableItems.value = await getVegetableItems();
      items.value = await getAllItems();
      console.log("items.value" , items.value)
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
