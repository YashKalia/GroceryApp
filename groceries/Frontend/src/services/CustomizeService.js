import api from './api';

export const UpdateItem = async (id, price) => {
  console.log('UpdateItem', typeof id, typeof price);
  try {
    const response = await api.post('/api/v1/items/updateItem', { id, price });
    return response.data;
  } catch (error) {
    console.error('Error', error);
    throw error;
  }
};
