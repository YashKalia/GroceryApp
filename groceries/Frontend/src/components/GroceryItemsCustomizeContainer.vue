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
    <!-- <div class="GroceryItemsCustomizeContainer" >
      <button type="button" class="btn btn-primary" className="CheckoutButton" @click="handleCheckout()">Checkout-></button>
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
    </div> -->
  </template>
  
  <script setup>
  import { getAllItems } from '@/services/ItemService';
  import { CheckOut } from '@/services/CheckoutService';
//   import RecieptPopUp from './components/Reciept/RecieptPopUp.vue';
  import { useItemStore } from '@/itemstore';
  // import BeerCustomizeCard from './components/ItemCards/BeerCustomizeCard.vue';
  // import BreadCustomizeCard from './components/ItemCards/BreadCustomizeCard.vue';
  // import VegetableCustomizeCard from './components/ItemCards/VegetableCustomizeCard.vue';
  import ItemCustomizeCard from './ItemCards/ItemCustomizeCard.vue';
  
  import {ref, onMounted} from "vue";
  
  const isRecieptVisible = ref(false);
  
  // var beerItems = ref([])
  // var breadItems = ref([])
  // var vegetableItems = ref([])
  
  
  const itemStore = useItemStore();
  
  // var storeItems = ref(itemStore.cartItems);
  var items = ref([])
  var itemTypes = ref([]);
  
  // async function handleCheckout() {
  //       reciept.value = await CheckOut();
  //       isRecieptVisible.value = true;
  //       itemStore.emptyEntireCart();
  
  // }
  
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
  