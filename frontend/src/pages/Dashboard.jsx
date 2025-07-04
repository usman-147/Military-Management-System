// src/pages/Dashboard.jsx
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

  const totalPurchases = data.reduce((sum, row) => sum + row.totalPurchases, 0);
  const totalTransfersIn = data.reduce(
    (sum, row) => sum + row.totalTransfersIn,
    0
  );
  const totalTransfersOut = data.reduce(
    (sum, row) => sum + row.totalTransfersOut,
    0
  );

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50">
      <div className="container mx-auto px-4 py-8 max-w-7xl">
        {/* Header */}
        <div className="text-center mb-12">
          <h1 className="text-4xl md:text-5xl font-bold text-slate-800 mb-4">
            Military Operations Dashboard
          </h1>
          <p className="text-lg text-slate-600 max-w-2xl mx-auto">
            Real-time insights into asset movements and logistics operations
          </p>
        </div>

        {/* Filters */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          <div className="bg-white/80 backdrop-blur-sm shadow-lg rounded-2xl p-6 text-center">
            <div className="text-2xl mb-3">🏢</div>
            <h3 className="font-semibold text-slate-700 mb-2">Base Location</h3>
            <span className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm">
              Base ID: {baseId}
            </span>
          </div>

          <div className="bg-white/80 backdrop-blur-sm shadow-lg rounded-2xl p-6 text-center">
            <div className="text-2xl mb-3">📦</div>
            <h3 className="font-semibold text-slate-700 mb-2">Asset Type</h3>
            <span className="bg-emerald-100 text-emerald-800 px-3 py-1 rounded-full text-sm">
              Asset ID: {assetId}
            </span>
          </div>

          <div className="bg-white/80 backdrop-blur-sm shadow-lg rounded-2xl p-6 text-center">
            <div className="text-2xl mb-3">📅</div>
            <h3 className="font-semibold text-slate-700 mb-2">Date Range</h3>
            <span className="bg-purple-100 text-purple-800 px-3 py-1 rounded-full text-sm">
              {start} to {end}
            </span>
          </div>
        </div>

        {/* Metrics */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8 text-white">
          <div className="bg-gradient-to-br from-blue-500 to-blue-600 rounded-2xl p-6 shadow-lg">
            <div className="flex justify-between items-center">
              <div>
                <p className="text-blue-100 text-sm">Total Purchases</p>
                <p className="text-3xl font-bold">{totalPurchases}</p>
              </div>
              <div className="text-4xl">📦</div>
            </div>
          </div>

          <div className="bg-gradient-to-br from-emerald-500 to-emerald-600 rounded-2xl p-6 shadow-lg">
            <div className="flex justify-between items-center">
              <div>
                <p className="text-emerald-100 text-sm">Transfers In</p>
                <p className="text-3xl font-bold">{totalTransfersIn}</p>
              </div>
              <div className="text-4xl rotate-180">🔄</div>
            </div>
          </div>

          <div className="bg-gradient-to-br from-orange-500 to-orange-600 rounded-2xl p-6 shadow-lg">
            <div className="flex justify-between items-center">
              <div>
                <p className="text-orange-100 text-sm">Transfers Out</p>
                <p className="text-3xl font-bold">{totalTransfersOut}</p>
              </div>
              <div className="text-4xl">📤</div>
            </div>
          </div>
        </div>

        {/* Table */}
        <div className="bg-white/90 backdrop-blur-sm rounded-2xl shadow-xl">
          <div className="bg-slate-800 text-white px-6 py-4 rounded-t-2xl font-semibold text-lg flex items-center gap-2">
            🔁 Net Movement Analysis
          </div>
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead className="bg-slate-50 text-slate-700 border-b">
                <tr>
                  <th className="px-6 py-4 text-left">📅 Date</th>
                  <th className="px-6 py-4 text-left">📦 Purchases</th>
                  <th className="px-6 py-4 text-left">🚚 Transfers In</th>
                  <th className="px-6 py-4 text-left">📤 Transfers Out</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-slate-100">
                {data.map((row, idx) => (
                  <tr key={idx} className="hover:bg-slate-50">
                    <td className="px-6 py-4 text-slate-800 font-medium">
                      {new Date(row.date).toLocaleDateString("en-US", {
                        weekday: "short",
                        year: "numeric",
                        month: "short",
                        day: "numeric",
                      })}
                    </td>
                    <td className="px-6 py-4 text-blue-700 font-semibold">
                      {row.totalPurchases}
                    </td>
                    <td className="px-6 py-4 text-emerald-700 font-semibold">
                      {row.totalTransfersIn}
                    </td>
                    <td className="px-6 py-4 text-orange-700 font-semibold">
                      {row.totalTransfersOut}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            {data.length === 0 && (
              <div className="text-center py-12 text-slate-500">
                <div className="text-4xl mb-2">📭</div>
                No data available for the selected period
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
