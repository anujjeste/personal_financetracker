import React, { useState } from 'react';
import axios from 'axios';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`http://localhost:8080/api/users/register`, { username, password });
      alert('User registered');
      console.log(response.data);
    } catch (error) {
      alert('Registration failed');
      console.error(error.response?.data || error.message);
    }
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl mb-4">Register</h2>
      <form onSubmit={handleRegister}>
        <input className="block mb-2" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input className="block mb-2" type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <button className="bg-green-500 text-white px-4 py-2" type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
