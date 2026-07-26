import api from './api';

export const getAllItems = async () => {
  try {
    const response = await api.get('/api/v1/items/items');
    return response.data;
  } catch (error) {
    console.error('Error', error);
    throw error;
  }
};
