<template>
    <div class="bvegetable-item-card" style="width: 18rem;">
  <img src="https://media.istockphoto.com/id/174429248/photo/fresh-vegetables.jpg?s=612x612&w=0&k=20&c=fxlgOIET7gKa8M3rwkV974aUfB0gVpWiJQwUoxA4dtQ=" class="card-img-top" alt="vegetable image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
    <p class="card-text">Weight(g): {{ weight }}</p>
    <p class="card-text">Price(€): {{ price }}</p>
    <a class="btn btn-light" @click="decrement">-</a>
    <a class="btn btn-success" @click="increment">+</a>
    <p v-if="itemCount > 0">{{ itemCount }} units chosen</p>
  </div>
</div>
</template>

<script setup>
 import {ref, defineProps} from 'vue';
import {useItemStore} from "../../itemstore.js"


//  const itemStore = useItemStore();
const itemCount = ref(0);
const itemStore = useItemStore();

 const props = defineProps({
      itemType: String,
      itemName: String,
      price: Number,
      id: String,
      weight: Number
})

function increment() {
    itemCount.value = itemStore.addToCart(props);
}

function decrement() {
    itemCount.value = itemStore.removeFromCart(props.id);
}
</script>