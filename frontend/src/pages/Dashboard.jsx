/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { fetchNetMovement } from "../api/dashboardApi";

const Dashboard = () => {
  const [baseId, setBaseId] = useState(1);
  const [assetId, setAssetId] = useState(1);
  const [startDate, setStartDate] = useState("2025-01-01");
  const [endDate, setEndDate] = useState("2025-12-31");

  const [stats, setStats] = useState({
    totalPurchases: 0,
    totalTransfersIn: 0,
    totalTransfersOut: 0,
  });

  useEffect(() => {
    const loadData = async () => {
      try {
        const data = await fetchNetMovement(
          baseId,
          assetId,
          startDate,
          endDate
        );
        if (data.length > 0) {
          const row = data[0];
          setStats({
            totalPurchases: row.totalPurchases,
            totalTransfersIn: row.totalTransfersIn,
            totalTransfersOut: row.totalTransfersOut,
          });
        }
      } catch (error) {
        console.error("Error fetching net movement:", error);
      }
    };
    loadData();
  }, [baseId, assetId, startDate, endDate]);

  return (
    <div className="p-6 space-y-6">
      {/* 🔷 Heading */}
      <h1 className="text-3xl font-bold">Military Dashboard</h1>

      {/* 🔷 Filters Section */}
      <section className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="bg-gray-100 p-4 rounded-2xl shadow">Base Dropdown</div>
        <div className="bg-gray-100 p-4 rounded-2xl shadow">Asset Dropdown</div>
        <div className="bg-gray-100 p-4 rounded-2xl shadow">
          Date Range Picker
        </div>
      </section>

      {/* 🔷 Metrics Cards Section */}
      <section className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📦 Total Purchases: {stats.totalPurchases}
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          🚚 Transfers In: {stats.totalTransfersIn}
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📤 Transfers Out: {stats.totalTransfersOut}
        </div>
      </section>

      {/* 🔷 Chart/Table Section */}
      <section className="bg-white p-6 rounded-2xl shadow h-64 flex items-center justify-center text-gray-500">
        📊 Net Movement Chart/Table Placeholder
      </section>
    </div>
  );
};

export default Dashboard;
