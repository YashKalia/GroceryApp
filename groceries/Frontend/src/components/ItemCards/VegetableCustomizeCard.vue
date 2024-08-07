<template>
  <div class="vegetable-item-card" style="width: 18rem;">
    <img src="https://media.istockphoto.com/id/174429248/photo/fresh-vegetables.jpg?s=612x612&w=0&k=20&c=fxlgOIET7gKa8M3rwkV974aUfB0gVpWiJQwUoxA4dtQ=" class="card-img-top" alt="vegetable image" height =200rem><div class="card-body">
  <h5 class="card-title">{{ itemName }}</h5>
  <p class="card-text">Weight(g): {{ weight }}</p>
  <p class="card-text">Current Price(€)/100g: {{ price }}</p>
  <p class="card-text">Enter Updated Price(€):</p>
  <input  v-model="updatedPrice" placeholder="Add new numeric value" type="number"> 
  <a class="btn btn-success" @click="updateValue">Confirm</a>
</div>
</div>
</template>

<script setup>
import {ref, defineProps} from 'vue';
import {useItemStore} from "../../itemstore.js";
import { UpdateVegetableItem } from '@/services/CustomizeService.js';

//  const itemStore = useItemStore();
const props = defineProps({
    itemName: String,
    price: Number,
    id:String,
    weight: Number

})

const itemCount = ref(0);
const itemStore = useItemStore();

const updatedPrice = ref(props.price);

async function updateValue() {
  var response = await UpdateVegetableItem(props.id, updatedPrice.value)
  console.log("response",response)
}

</script>