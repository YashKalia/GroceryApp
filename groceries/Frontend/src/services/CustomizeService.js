import axios from 'axios';

export const UpdateBeerItem = async (id, price) => {
    console.log("updatedPrice", typeof(id), typeof(price))
    try {
        const response = await axios.post("http://localhost:8080/api/v1/items/updateBeerItem", {id, price});
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };

  export const UpdateBreadItem = async (id, price) => {
    console.log("updateBreadItem", typeof(id), typeof(price))
    try {
        const response = await axios.post("http://localhost:8080/api/v1/items/updateBreadItem", {id, price});
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };


  export const UpdateVegetableItem = async (id, price) => {
    console.log("updateVegetableItem", typeof(id), typeof(price))
    try {
        const response = await axios.post("http://localhost:8080/api/v1/items/updateVegetableItem", {id, price});
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };