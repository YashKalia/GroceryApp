import axios from 'axios';

  export const UpdateItem = async (id, price) => {
    console.log("UpdateItem", typeof(id), typeof(price))
    try {
        const response = await axios.post("http://localhost:8080/api/v1/items/updateItem", {id, price});
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };