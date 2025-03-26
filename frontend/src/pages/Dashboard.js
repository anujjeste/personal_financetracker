// src/pages/Dashboard.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [budget, setBudget] = useState(null);

  const token = localStorage.getItem('token');
  const axiosConfig = {
    headers: { Authorization: `Bearer ${token}` }
  };

  useEffect(() => {
    fetchTransactions();
    fetchBudget();
  }, []);

  const fetchTransactions = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/transactions', axiosConfig);
      setTransactions(response.data);
    } catch (error) {
      console.error('Error fetching transactions:', error);
    }
  };

  const fetchBudget = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/budget', axiosConfig);
      setBudget(response.data);
    } catch (error) {
      console.error('Error fetching budget:', error);
    }
  };

  const handleAddTransaction = async (e) => {
    e.preventDefault();
    try {
      await axios.post(
        'http://localhost:8080/api/transactions',
        { amount, description },
        axiosConfig
      );
      setAmount('');
      setDescription('');
      fetchTransactions();
    } catch (error) {
      console.error('Error adding transaction:', error);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Welcome to BudgetMate Dashboard</h2>

      <div style={{ marginTop: '20px' }}>
        <h3>Monthly Budget</h3>
        <p>{budget ? `₹${budget.amount}` : 'Loading budget...'}</p>
      </div>

      <div style={{ marginTop: '30px' }}>
        <h3>Add Transaction</h3>
        <form onSubmit={handleAddTransaction}>
          <input
            type="number"
            placeholder="Amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
          <input
            type="text"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
          <button type="submit">Add</button>
        </form>
      </div>

      <div style={{ marginTop: '40px' }}>
        <h3>Transactions</h3>
        {transactions.length === 0 ? (
          <p>No transactions found.</p>
        ) : (
          <ul>
            {transactions.map((tx) => (
              <li key={tx.id}>{tx.description} - ₹{tx.amount}</li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
