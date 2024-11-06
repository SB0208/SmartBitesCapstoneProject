import React from 'react';
import { Link } from 'react-router-dom';

const Navbar: React.FC = () => {
    return (
        <nav style={{display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '10px'}}>

            <Link to="/" style={{marginBottom: '10px'}}>Home</Link>

            <Link to="/category" style={{marginBottom: '10px'}}>Add Meal</Link>

            <Link to="/categories" style={{marginBottom: "10px"}}>All Meals</Link>


        </nav>

    );
};

export default Navbar;
