import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../images/logo.png';

const HomePage: React.FC = () => {
    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', height: '100vh', justifyContent: 'center' }}>
            <img src={logo} alt="SmartBites Logo" style={{ width: '500px',  marginBottom: '80px' }} />
            <h1>Welcome to SmartBites</h1>
            <Link to="/categories">
                <button style={{ padding: '10px 20px', fontSize: '16px', cursor: 'pointer' }}>Lets Go</button>
            </Link>
        </div>
    );
};

export default HomePage;
