import api from './api';
import { useItemStore } from '@/itemstore';

export const CheckOut = async () => {
  const itemStore = useItemStore();
  console.log('Cart items: ', itemStore.cartItems);
  try {
    const response = await api.post(
      '/api/v1/items/checkout',
      itemStore.cartItems
    );
    return response.data;
  } catch (error) {
    console.error('Error', error);
    throw error;
  }
};
