/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { fetchNetMovement } from "../api/dashboardApi";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Legend,
} from "recharts";

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

  const [data, setData] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      try {
        const result = await fetchNetMovement(
          baseId,
          assetId,
          startDate,
          endDate
        );
        const mapped = result.map((row) => ({
          date: row.date,
          totalPurchases: row.totalPurchases,
          totalTransfersIn: row.totalTransfersIn,
          totalTransfersOut: row.totalTransfersOut,
        }));
        setData(mapped);
        if (mapped.length > 0) {
          const latest = mapped[mapped.length - 1];
          setStats({
            totalPurchases: latest.totalPurchases,
            totalTransfersIn: latest.totalTransfersIn,
            totalTransfersOut: latest.totalTransfersOut,
          });
        }
      } catch (err) {
        console.error("Dashboard chart load failed", err);
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

      {/* 🔷 Chart Section */}
      <section className="bg-white p-6 rounded-2xl shadow">
        <h2 className="text-xl font-semibold mb-4">📊 Net Movement Chart</h2>
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line
              type="monotone"
              dataKey="totalPurchases"
              stroke="#8884d8"
              name="Purchases"
            />
            <Line
              type="monotone"
              dataKey="totalTransfersIn"
              stroke="#82ca9d"
              name="Transfers In"
            />
            <Line
              type="monotone"
              dataKey="totalTransfersOut"
              stroke="#ff7300"
              name="Transfers Out"
            />
          </LineChart>
        </ResponsiveContainer>
      </section>
    </div>
  );
};

export default Dashboard;
