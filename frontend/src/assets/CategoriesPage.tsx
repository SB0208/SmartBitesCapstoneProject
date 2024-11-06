import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../assets/Navbar';
import axios from "axios";






interface Category {
    id: string;
    name: string;
    description: string;
    nutrition:string;
    link:string;
}



const CategoriesPage: React.FC = () => {
    const [categories, setCategories] = useState<Category[]>([]);
    const fetchCategories = async () => {
        try {
            const response = await fetch('/api/meals/categories');
            const data = await response.json();
            setCategories(data);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    useEffect(() => {


        fetchCategories();
    }, []);

    function deleteMeal(id:string){
        axios.delete("/api/meals/" +id).then(fetchCategories).catch(error => console.log(error))
    }

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
                        <button onClick={() => deleteMeal(category.id)}>Delete Meal</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CategoriesPage;
