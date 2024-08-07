import axios from 'axios';
import { useItemStore } from '@/itemstore';

export const CheckOut = async () => {
    const itemStore = useItemStore();
    console.log("Cart items: ", itemStore.cartItems)
    try {
        const response = await axios.post("http://localhost:8080/api/v1/items/checkout",itemStore.cartItems);
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };