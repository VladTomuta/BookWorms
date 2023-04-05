// import "./App.css";
//import SignUp from "./pages/signUp/SignUp";
import NavBar from "./components/NavBar/NavBar";
import LogInPage from "./pages/LogIn/LogInPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<LogInPage />} />
				{/* <Route path="/login" element={<LogIn />} /> */}
				{/* <Route path="/signup" element={<SignUp />} /> */}
			</Routes>
		</Router>
	);
}

export default App;
