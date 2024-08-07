<template>
    <div class="GroceryItemsCustomizeContainer" >
      <!-- <button type="button" class="btn btn-primary" className="CheckoutButton" @click="handleCheckout()">Checkout-></button> -->
      <div class="ProductTitle">
        <h1>Customize Beers</h1>
        <div class="GridItemsContainer">
            <BeerCustomizeCard  v-for="(item) in beerItems" :itemName="item.itemName" :price="item.price" :discountId="item.discountId" :isPack="item.isPack" :id="item.id"/>       
        </div>
      </div>
      
       <div class="ProductTitle">
        <h1>Customize Breads</h1>
        <div class="GridItemsContainer">   
            <BreadCustomizeCard  v-for="(item) in breadItems" :itemName="item.itemName" :price="item.price" :id="item.id" :age = "item.age"/>    
        </div>
      </div> 
      <div class="ProductTitle">
        <h1>Customize Vegetables</h1>
        <div class="GridItemsContainer">   
          <VegetableCustomizeCard v-for="(item) in vegetableItems" :itemName="item.itemName" :price ="item.price" :id = "item.id" :weight = "item.weight"/>
       </div>
      </div> 
    </div>
  </template>
  
  <script setup>
  import { getBeerDiscounts, getBreadDiscounts, getVegetableDiscounts, getAllItems, getBeerItems, getBreadItems, getVegetableItems } from '@/services/ItemService';
  import { CheckOut } from '@/services/CheckoutService';
//   import RecieptPopUp from './components/Reciept/RecieptPopUp.vue';
  import { useItemStore } from '@/itemstore';
  import BeerCustomizeCard from './components/ItemCards/BeerCustomizeCard.vue';
  import BreadCustomizeCard from './components/ItemCards/BreadCustomizeCard.vue';
  import VegetableCustomizeCard from './components/ItemCards/VegetableCustomizeCard.vue';
  
  import {ref, onMounted} from "vue";
  
  const isRecieptVisible = ref(false);
  
  var beerItems = ref([])
  var breadItems = ref([])
  var vegetableItems = ref([])
//   var reciept = ref({})
//   var items = ref([])
  
  
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
        // items.value = await getAllItems();
        // console.log("items.value" , items.value)
        console.log("beerItems.value", beerItems.value)
        console.log("breadItems.value", breadItems.value)
        console.log("vegetableItems.value",vegetableItems.value)
      });
  
  
  
  </script>
  
  <style>
  .GroceryItemsCustomizeContainer{
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
  