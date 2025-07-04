import axios from "axios";

const BASE_URL = "http://localhost:8080/api/dashboard";

export const fetchNetMovement = async (baseId, assetId, start, end) => {
  try {
    const response = await axios.get(`${BASE_URL}/net-movement`, {
      params: { baseId, assetId, start, end },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching net movement:", error);
    return [];
  }
};
