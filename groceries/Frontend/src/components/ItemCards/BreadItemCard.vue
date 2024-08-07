<template>
    <div class="-bread-item-card" style="width: 18rem;">
  <img src="https://plus.unsplash.com/premium_photo-1675788939191-713c2abf3da6?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="card-img-top" alt="Bread image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
    <p class="card-text">Price(€): {{ price }}</p>
    <p class="card-text">Age: {{ age }} days old</p>
    <a class="btn btn-light" @click="decrement()">-</a>
    <a class="btn btn-success" @click="increment()">+</a>
    <p v-if="itemCount > 0"> {{ itemCount }} units chosen</p>
  </div>
</div>
</template>

<script setup>
import {useItemStore} from "../../itemstore.js"
import {ref, defineProps} from 'vue';

const itemCount = ref(0);
const itemStore = useItemStore();

const props = defineProps({
     itemType: String,
      itemName: String,
      price: Number,
      age: Number,
      id: String
})

function increment() {
    itemCount.value = itemStore.addToCart(props);
}

function decrement() {
    itemCount.value = itemStore.removeFromCart(props.id);
}
</script>

<style>
.bread-item-card{
  display:block;
}

.card-text{
  margin-bottom :0rem;
}
</style>