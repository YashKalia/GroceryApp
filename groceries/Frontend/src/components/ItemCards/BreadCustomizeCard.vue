<template>
  <div class="bread-item-card" style="width: 18rem;">
    <img src="https://plus.unsplash.com/premium_photo-1675788939191-713c2abf3da6?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="card-img-top" alt="Bread image" height =200rem>
<div class="card-body">
  <h5 class="card-title">{{ itemName }}</h5>
  <p class="card-text">Age: {{ age }} days old</p>
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
import { UpdateBreadItem } from '@/services/CustomizeService.js';

//  const itemStore = useItemStore();
const props = defineProps({
    itemName: String,
    price: Number,
    id:String,
    age: Number,
})

const itemCount = ref(0);
const itemStore = useItemStore();

const updatedPrice = ref(props.price);

async function updateValue() {
  var response = await UpdateBreadItem(props.id, updatedPrice.value)
  console.log("response",response)
}

</script>