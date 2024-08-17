<script setup>
import GroceryItemsGridContainer from '@/components/Containers/GroceryItemsGridContainer.vue';
</script>

<template>
  <main class="main">
    <h1>Welcome to the GroceryApp</h1>
    <h2>Add items to your cart below</h2>
    <GroceryItemsGridContainer></GroceryItemsGridContainer>
  </main>
</template>

<style>
.main{
  margin: 1rem;
}
</style>
