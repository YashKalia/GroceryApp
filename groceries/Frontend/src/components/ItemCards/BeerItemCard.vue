<template>
    <div class="beer-item-card" style="width: 18rem;">
  <img src="https://images.unsplash.com/photo-1618885472179-5e474019f2a9?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8YmVlcnxlbnwwfHwwfHx8MA%3D%3D" class="card-img-top" alt="Beer image" height =200rem>
  <div class="card-body">
    <h5 class="card-title">{{ itemName }}</h5>
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

 const itemCount = ref(0);
const itemStore = useItemStore();

//  const itemStore = useItemStore();
const props = defineProps({
      itemType: String,
      itemName: String,
      price: Number,
      id: String,
      isPack: Boolean,
      discountId: String

})
function increment() {
    itemCount.value = itemStore.addToCart(props);
}

function decrement() {
    itemCount.value = itemStore.removeFromCart(props.id);
}

</script>