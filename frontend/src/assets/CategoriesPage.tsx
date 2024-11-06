import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../assets/Navbar';




interface Category {
    id: string;
    name: string;
    description: string;
    nutrition:string;
    link:string;
}



const CategoriesPage: React.FC = () => {
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch('/api/meals/categories');
                const data = await response.json();
                setCategories(data);
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchCategories();
    }, []);

    return (
        <div>
            <Navbar />
            <h1>Meals</h1>
            <ul>
                {categories.map((category) => (
                    <li key={category.id}>
                        <Link to={`/category/${category.id}`}>
                            <h2>{category.name}</h2>
                            <p>{category.description}</p>
                            <p>{category.nutrition}</p>
                            <img src={category.link} alt={"bild"}/>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CategoriesPage;
