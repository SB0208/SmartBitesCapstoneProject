import HomePage from "./assets/HomePage";
import CategoriesPage from './assets/CategoriesPage';
import CategoryPage from './assets/CategoryPage';
import {BrowserRouter, Route, Routes} from "react-router-dom";

const App: React.FC = () => {




    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />

                <Route path="/categories" element={<CategoriesPage />} />

                <Route path="/category" element={<CategoryPage />} />


            </Routes>

        </BrowserRouter>


    );
};

export default App;

