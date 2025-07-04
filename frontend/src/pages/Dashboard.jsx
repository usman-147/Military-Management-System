// src/pages/Dashboard.jsx
import React from "react";

const Dashboard = () => {
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
          📦 Total Purchases
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          🚚 Transfers In
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📤 Transfers Out
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
