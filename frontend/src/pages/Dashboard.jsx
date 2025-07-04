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

import { fetchBases, fetchAssets } from "../api/api";

import NetMovementModal from "./NetMovementModal";

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

  const [bases, setBases] = useState([]);
  const [assets, setAssets] = useState([]);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedDay, setSelectedDay] = useState({
    date: "2025-07-03",
    base: "Base Alpha",
    asset: "AK-47",
    purchases: 100,
    transfersIn: 50,
    transfersOut: 30,
  });

  const openModal = (dayData) => {
    setSelectedDay(dayData);
    setIsModalOpen(true);
  };

  const closeModal = () => setIsModalOpen(false);

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

    const initFilters = async () => {
      try {
        const [basesRes, assetsRes] = await Promise.all([
          fetchBases(),
          fetchAssets(),
        ]);
        setBases(basesRes);
        setAssets(assetsRes);
        if (basesRes.length > 0) setBaseId(basesRes[0].id);
        if (assetsRes.length > 0) setAssetId(assetsRes[0].id);
      } catch (err) {
        console.error("Init filters failed", err);
      }
    };

    initFilters();
  }, [baseId, assetId, startDate, endDate]);

  <NetMovementModal
    isOpen={isModalOpen}
    onClose={closeModal}
    details={selectedDay}
  />;

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
        <select
          className="bg-white p-3 rounded-2xl shadow"
          value={baseId}
          onChange={(e) => setBaseId(Number(e.target.value))}
        >
          {bases.map((b) => (
            <option key={b.id} value={b.id}>
              {b.name}
            </option>
          ))}
        </select>

        <select
          className="bg-white p-3 rounded-2xl shadow"
          value={assetId}
          onChange={(e) => setAssetId(Number(e.target.value))}
        >
          {assets.map((a) => (
            <option key={a.id} value={a.id}>
              {a.name}
            </option>
          ))}
        </select>

        <input
          type="date"
          className="bg-white p-3 rounded-2xl shadow"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />
        <input
          type="date"
          className="bg-white p-3 rounded-2xl shadow"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
        />
      </section>

      {/* 🔷 Chart Section */}
      <section className="bg-white p-6 rounded-2xl shadow h-64 flex items-center justify-center text-gray-500">
        <button
          onClick={() => openModal(selectedDay)}
          className="ml-4 px-4 py-2 bg-blue-600 text-white rounded-xl"
        >
          View Details
        </button>
      </section>
    </div>
  );
};

export default Dashboard;
