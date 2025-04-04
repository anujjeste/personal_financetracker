import React, { useEffect, useState } from "react";
import axios from "axios";

function Dashboard() {
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState("");
  const [description, setDescription] = useState("");
  const [budget, setBudget] = useState(0);

  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };


  useEffect(() => {
    axios
      .get("http://localhost:8080/api/transactions", { headers })
      .then((res) => setTransactions(res.data))
      .catch((err) => console.error("Error fetching transactions:", err));
  }, []);

  // Fetch Budget
  useEffect(() => {
    if (userId) {
      axios
        .get(`http://localhost:8080/api/budget/user/${userId}`, { headers })
        .then((res) => setBudget(res.data.amount))
        .catch((err) => console.error("Error fetching budget:", err));
    }
  }, [userId]);

  //  Add Transaction
  const handleAddTransaction = () => {
    const newTransaction = { amount, description };
    axios
      .post("http://localhost:8080/api/transactions", newTransaction, { headers })
      .then((res) => {
        setTransactions([...transactions, res.data]);
        setAmount("");
        setDescription("");
      })
      .catch((err) => console.error("Error adding transaction:", err));
  };

  return (
    <div style={{ padding: "2rem", fontFamily: "Arial" }}>
      <h2>Dashboard</h2>
      <p><strong>Monthly Budget:</strong> ₹{budget}</p>

      <h3>Transactions</h3>
      <ul>
        {transactions.map((t, index) => (
          <li key={index}>
            ₹{t.amount} - {t.description}
          </li>
        ))}
      </ul>

      <h3>Add Transaction</h3>
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />
      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button onClick={handleAddTransaction}>Add</button>
    </div>
  );
}

export default Dashboard;
