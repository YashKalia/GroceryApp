import axios from 'axios';

const API_BASE =
  import.meta.env.VITE_API_BASE_URL?.replace(/\/$/, '') ||
  'http://localhost:8080';

export const api = axios.create({
  baseURL: API_BASE,
});

export default api;
