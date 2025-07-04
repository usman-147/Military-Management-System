import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/dashboard";

export const fetchNetMovement = async (baseId, assetId, startDate, endDate) => {
  const response = await axios.get(`${API_BASE_URL}/net-movement`, {
    params: { baseId, assetId, startDate, endDate },
  });
  return response.data;
};
