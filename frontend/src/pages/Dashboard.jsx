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
  const [selectedDay, setSelectedDay] = useState(null);

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
          baseId: row.baseId,
          assetId: row.assetId,
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
    loadData();
  }, [baseId, assetId, startDate, endDate]);

  return (
    <div className="p-6 space-y-6">
      {/* 🔷 Heading */}
      <h1 className="text-3xl font-bold">Military Dashboard</h1>

      {/* 🔷 Filters Section */}
      <section className="grid grid-cols-1 md:grid-cols-4 gap-4">
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

      {/* 🔷 Metrics Section */}
      <section className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📦 Purchases: {stats.totalPurchases}
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          🚚 Transfers In: {stats.totalTransfersIn}
        </div>
        <div className="bg-white p-4 rounded-2xl shadow text-center">
          📤 Transfers Out: {stats.totalTransfersOut}
        </div>
      </section>

      {/* 🔷 Net Movement Table */}
      <section className="bg-white p-6 rounded-2xl shadow">
        <h3 className="text-lg font-semibold mb-4">📊 Net Movement Summary</h3>
        <div className="overflow-x-auto">
          <table className="min-w-full text-sm text-left">
            <thead>
              <tr className="text-gray-600 border-b">
                <th className="py-2 px-4">Date</th>
                <th className="py-2 px-4">Purchases</th>
                <th className="py-2 px-4">Transfers In</th>
                <th className="py-2 px-4">Transfers Out</th>
                <th className="py-2 px-4">Action</th>
              </tr>
            </thead>
            <tbody>
              {data.map((entry, idx) => (
                <tr key={idx} className="border-b hover:bg-gray-50">
                  <td className="py-2 px-4">{entry.date}</td>
                  <td className="py-2 px-4">{entry.totalPurchases}</td>
                  <td className="py-2 px-4">{entry.totalTransfersIn}</td>
                  <td className="py-2 px-4">{entry.totalTransfersOut}</td>
                  <td className="py-2 px-4">
                    <button
                      onClick={() =>
                        openModal({
                          date: entry.date,
                          base: bases.find((b) => b.id === baseId)?.name || "",
                          asset:
                            assets.find((a) => a.id === assetId)?.name || "",
                          purchases: entry.totalPurchases,
                          transfersIn: entry.totalTransfersIn,
                          transfersOut: entry.totalTransfersOut,
                        })
                      }
                      className="text-blue-600 hover:underline"
                    >
                      View Details
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>

      {/* 🔷 Modal Component */}
      {isModalOpen && (
        <NetMovementModal
          isOpen={isModalOpen}
          onClose={closeModal}
          details={selectedDay}
        />
      )}
    </div>
  );
};

export default Dashboard;
