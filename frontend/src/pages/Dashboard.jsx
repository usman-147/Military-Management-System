// src/components/Dashboard.jsx
import React, { useEffect, useState } from "react";
import { fetchNetMovement } from "../api/dashboardApi";

const Dashboard = () => {
  const [data, setData] = useState([]);
  const [baseId] = useState(1);
  const [assetId] = useState(1);
  const [start] = useState("2025-07-01");
  const [end] = useState("2025-07-04");

  useEffect(() => {
    fetchNetMovement(baseId, assetId, start, end).then(setData);
  }, [baseId, assetId, start, end]);

  return (
    <div className="p-4 sm:p-6 space-y-6">
      {/* 🔷 Heading */}
      <h1 className="text-2xl sm:text-3xl font-bold text-center sm:text-left">
        Military Dashboard
      </h1>

      {/* 🔷 Filters Section */}
      <section className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <div className="bg-gray-100 p-4 rounded-2xl shadow text-center">
          📍 Base
        </div>
        <div className="bg-gray-100 p-4 rounded-2xl shadow text-center">
          💼 Asset
        </div>
        <div className="bg-gray-100 p-4 rounded-2xl shadow text-center">
          📅 Date Range
        </div>
      </section>

      {/* 🔷 Metric Cards */}
      <section className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📦 Total Purchases:{" "}
          <span className="font-semibold">
            {data.reduce((sum, row) => sum + row.totalPurchases, 0)}
          </span>
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          🚚 Transfers In:{" "}
          <span className="font-semibold">
            {data.reduce((sum, row) => sum + row.totalTransfersIn, 0)}
          </span>
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📤 Transfers Out:{" "}
          <span className="font-semibold">
            {data.reduce((sum, row) => sum + row.totalTransfersOut, 0)}
          </span>
        </div>
      </section>

      {/* 🔷 Net Movement Table */}
      <section className="bg-white p-4 rounded-2xl shadow overflow-x-auto">
        <table className="w-full text-sm text-left">
          <thead>
            <tr className="border-b">
              <th className="p-2">📅 Date</th>
              <th className="p-2">📦 Purchases</th>
              <th className="p-2">🚚 Transfers In</th>
              <th className="p-2">📤 Transfers Out</th>
            </tr>
          </thead>
          <tbody>
            {data.map((row, idx) => (
              <tr key={idx} className="border-b hover:bg-gray-50">
                <td className="p-2">{row.date}</td>
                <td className="p-2">{row.totalPurchases}</td>
                <td className="p-2">{row.totalTransfersIn}</td>
                <td className="p-2">{row.totalTransfersOut}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </div>
  );
};

export default Dashboard;
