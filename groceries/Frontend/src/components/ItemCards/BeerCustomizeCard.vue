<template>
    <div class="beer-item-card" style="width: 18rem;">
  <img src="https://images.unsplash.com/photo-1618885472179-5e474019f2a9?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8YmVlcnxlbnwwfHwwfHx8MA%3D%3D" class="card-img-top" alt="Beer image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
    <p class="card-text">Current Price(€): {{ price }}</p>
    <p class="card-text">Enter Updated Price(€):</p>
    <input  v-model="updatedPrice" placeholder="Add new numeric value" type="number"> 
    <a class="btn btn-success" @click="updateValue">Confirm</a>
  </div>
</div>
</template>

<script setup>
 import {ref, defineProps} from 'vue';
import {useItemStore} from "../../itemstore.js";
import { UpdateBeerItem } from '@/services/CustomizeService.js';

//  const itemStore = useItemStore();
const props = defineProps({
      itemName: String,
      price: Number,
      discountId:String,
      isPack:Boolean,
      id:String
})

const itemCount = ref(0);
const itemStore = useItemStore();

const updatedPrice = ref(props.price);

async function updateValue() {
    var response = await UpdateBeerItem(props.id, updatedPrice.value)
    console.log("response",response)
}


</script>