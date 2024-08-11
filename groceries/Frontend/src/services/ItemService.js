import axios from 'axios';

  export const getAllItems = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/items")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };
