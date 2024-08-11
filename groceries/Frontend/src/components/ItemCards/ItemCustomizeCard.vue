<template>
    <div class="item-card" style="width: 18rem;">
  <img :src="props.productImage" class="card-img-top" alt="Product image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
    <p v-if="weight > 0" class="card-text">Weight(g): {{ weight }}</p>
    <p v-if="age >= 0" class="card-text">Age: {{ age }} days old</p>
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
import { UpdateItem } from '@/services/CustomizeService.js';

//  const itemStore = useItemStore();
const props = defineProps({
    itemName: String,
      price: Number,
      discountId:String,
      isPack:Boolean,
      id:String,
      itemType: String,
      weight:Number,
      age:Number,
      isPack:Boolean,
      discountId: String,
      productImage:String
})

const itemCount = ref(0);
const itemStore = useItemStore();

const updatedPrice = ref(props.price);

async function updateValue() {
    var response = await UpdateItem(props.id, updatedPrice.value)
    console.log("response",response)
}

</script>

<style>
.card-text{
    margin-bottom:0px;
}

.item-card{
  background-color: aliceblue;
  border: 2.5px solid lightcoral;
  width:16rem;
}

@media only screen and (max-width:1920px){
  .item-card{
    width: 10rem;
  }
}</style>