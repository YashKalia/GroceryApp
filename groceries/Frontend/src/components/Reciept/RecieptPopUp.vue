<template>
    <div v-if="isVisible" class="receipt-popup">
      <div class="popup-content">
        <h2>Receipt</h2>
        <p>Thank you for your purchase!</p>
        <ul>
          <li v-for="item in reciept.receiptItems" :key="item.id">
            {{ item.cartItem.itemCount }} x {{ item.cartItem.props.itemName }} = €{{ item.totalPrice }}
          </li>
        </ul>
        <p>Total(€): {{ reciept.totalOrderPrice }}</p>
        <button @click="close">Close</button>
      </div>
    </div>
  </template>
  
  <script setup>
    import {ref, defineProps} from 'vue';
    const emit = defineEmits(['update:visible']);
    const props = defineProps({
        reciept: Object,
        isVisible: Boolean,

    })
    console.log("props.isVisible", props.isVisible)
    var popUpVisible = ref(props.isVisible);

    // function closePopup() {
    //     popUpVisible.value = false;
    //     console.log("popUpVisible.value",popUpVisible.value)
    // }

    function close() {
    emit('update:visible', false); // Notify parent to close the popup
}
  </script>
  
  <style scoped>
  .receipt-popup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .popup-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
  }
  </style>
  