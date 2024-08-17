<template>
  <div class="item-card">
  <img :src="props.productImage"  class="card-img-top" alt="Product image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
    <p v-if="weight > 0" class="card-text-weight">Weight(g): {{ weight }}</p>
    <p v-if="age >= 0 && itemType == 'Bread'" class="card-text-age">Age: {{ age }} days old</p>
    <p class="card-text-price">Price(€): {{ price }}</p>
    <a class="btn btn-light" @click="decrement">-</a>
    <a class="btn btn-success" @click="increment">+</a>
    <p v-if="itemCount > 0">{{ itemCount }} units chosen</p>
  </div>
</div>
</template>

<script setup>
 import {ref, defineProps} from 'vue';
import {useItemStore} from "../../itemstore.js"

const itemCount = ref(0);
const itemStore = useItemStore();

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
function increment() {
    itemCount.value = itemStore.addToCart(props);
}

function decrement() {
    itemCount.value = itemStore.removeFromCart(props.id);
}

</script>

<style>
.card-text-weight, .card-text-price .card-text-age{
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
}

</style>

