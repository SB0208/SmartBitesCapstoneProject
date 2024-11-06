import React, { useEffect, useState } from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import Navbar from '../assets/Navbar';
import axios from 'axios';



interface Meal {
    id?: string;
    name: string;
    nutrition:string;
    link: string;
    description: string;
}



const CategoryPage: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [_meals, setMeals] = useState<Meal[]>([]);
    const [mealName, setMealName] = useState("");
    const [mealNutrition, setMealNutrition] = useState('');
    const [mealLink, setMealLink] = useState('');
    const [mealDescription, setMealDescription] = useState('');


    useEffect(() => {
        const fetchMeals = async () => {
            try {
                const response = await axios.get("api/meals/categories");
                setMeals(response.data);
            } catch (error) {
                console.error('Error', error);
            }
        };

        fetchMeals();
    }, [id]);


    const handleAddMeal = async (event: React.FormEvent) =>{
        event.preventDefault();
        const newMeal ={
            name: mealName,
            nutrition: mealNutrition,
            link: mealLink,
            description: mealDescription,
        };

        try {
            await axios.post(`/api/meals/categories/category`, newMeal);
            navigate("/categories");
        }catch (error){
            console.error("Error",error);
        }

    };

    return (
        <div>
            <Navbar />
            <h1> Add New Meal</h1>


            <form onSubmit={handleAddMeal}>
                <div>
                    <label>Meal Name:</label>
                    <input
                        type="text"
                        value={mealName}
                        onChange={(e) => setMealName(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Nutrition:</label>
                    <input
                        type="text"
                        value={mealNutrition}
                        onChange={(e) => setMealNutrition(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Link for Picture:</label>
                    <input
                        type="url"
                        value={mealLink}
                        onChange={(e) => setMealLink(e.target.value)}
                        required
                    />
                </div>


                <div>
                    <label>Description:
                    (add link for Recepie)</label>
                    <textarea
                        value={mealDescription}
                        onChange={(e) => setMealDescription(e.target.value)}
                        required
                    ></textarea>
                </div>

                <button type="submit">Add Meal</button>
            </form>

        </div>
    );
};

export default CategoryPage;

