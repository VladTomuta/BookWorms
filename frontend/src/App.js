// import "./App.css";
//import SignUp from "./pages/signUp/SignUp";
import HomePage from "./pages/HomePage/HomePage";
import LogInPage from "./pages/LogIn/LogInPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<HomePage />} />
				<Route path="/login" element={<LogInPage />} />
				{/* <Route path="/signup" element={<SignUp />} /> */}
			</Routes>
		</Router>
	);
}

export default App;
