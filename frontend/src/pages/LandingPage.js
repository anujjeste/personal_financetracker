import React from 'react';
import { useNavigate } from 'react-router-dom';
import './LandingPage.css';
import logo from '/home/anuj/personal-finance-tracker/frontend/src/assets/logo.png';


function LandingPage() {
  const navigate = useNavigate();

  return (
    <div className="landing-wrapper">
      <header>
        <h1 className="app-title">💰 Budget Mate</h1>
        <p className="tagline">Take control of your money, one budget at a time..</p>
      </header>

      <main>
        <img
          src="logo.png"
          alt="Finance Illustration"
          className="hero-image"
        />

        <div className="button-container">
          <button className="login-btn" onClick={() => navigate('/login')}>Login</button>
          <button className="register-btn" onClick={() => navigate('/register')}>Register</button>
        </div>
      </main>

      <footer>
        <p>© 2025 Personal Finance Tracker. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default LandingPage;
