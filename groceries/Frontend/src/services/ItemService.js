import axios from 'axios';

export const getBeerItems = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/beer-items");
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };

  export const getBreadItems = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/bread-items")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };

  export const getVegetableItems = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/vegetable-items")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };

  export const getAllItems = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/items")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };

  export const getBreadDiscounts = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/bread-discounts")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };
  export const getVegetableDiscounts = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/vegetable-discounts")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };
  export const getBeerDiscounts = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/items/beer-discounts")
        return response.data; 
    } catch (error) {
        console.error('Error', error);
        throw error;
    }
  };
