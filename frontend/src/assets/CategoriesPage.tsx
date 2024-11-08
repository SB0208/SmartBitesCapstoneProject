import React, { useEffect, useState } from 'react';
import Navbar from '../assets/Navbar';
import axios from "axios";
import {Link} from "react-router-dom";







interface Category {
    id: string;
    name: string;
    description: string;
    nutrition:string;
    link:string;
    mealLink:string;
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
                            <h2>{category.name}</h2>
                            <p>{category.nutrition}</p>
                        <img src={category.link} alt={"bild"}/>

                        <Link to={category.description} > Recepie/Info </Link>
                        <button onClick={() => deleteMeal(category.id)}>Delete Meal</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CategoriesPage;
