import React from "react";

const NetMovementModal = ({ isOpen, onClose, details }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
      <div className="bg-white rounded-2xl p-6 w-[90%] max-w-lg shadow-lg space-y-4">
        <h2 className="text-xl font-semibold">📊 Net Movement Details</h2>

        <ul className="space-y-2 text-gray-700 text-sm">
          <li>
            🗓️ <strong>Date:</strong> {details.date}
          </li>
          <li>
            🏢 <strong>Base:</strong> {details.base}
          </li>
          <li>
            🧰 <strong>Asset:</strong> {details.asset}
          </li>
          <li>
            📦 <strong>Purchases:</strong> {details.purchases}
          </li>
          <li>
            🚚 <strong>Transfers In:</strong> {details.transfersIn}
          </li>
          <li>
            📤 <strong>Transfers Out:</strong> {details.transfersOut}
          </li>
        </ul>

        <button
          onClick={onClose}
          className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-xl hover:bg-blue-700"
        >
          Close
        </button>
      </div>
    </div>
  );
};

export default NetMovementModal;
