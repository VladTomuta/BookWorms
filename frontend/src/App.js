// import "./App.css";
import SignUp from "./pages/SignUp/SignUpPage";
import HomePage from "./pages/HomePage/HomePage";
import LogInPage from "./pages/LogIn/LogInPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React from "react";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<HomePage />} />
				<Route path="/login" element={<LogInPage />} />
				<Route path="/signup" element={<SignUp />} />
			</Routes>
		</Router>
	);
}

export default App;
