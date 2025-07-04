const API_BASE = "http://localhost:8080/api";

export const fetchBases = async () => {
  const res = await fetch(`${API_BASE}/bases`);
  return res.json();
};

export const fetchAssets = async () => {
  const res = await fetch(`${API_BASE}/assets`);
  return res.json();
};

export const fetchNetMovement = async (baseId, assetId, start, end) => {
  const res = await fetch(
    `${API_BASE}/dashboard/net-movement?baseId=${baseId}&assetId=${assetId}&start=${start}&end=${end}`
  );
  return res.json();
};
