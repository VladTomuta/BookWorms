import { createContext, useState } from "react";

const UserContext = createContext();

export function UserProvider({ children }) {
	const [user, setUser] = useState({
		username: "marcel",
		email: "marcelCostesti@email.com",
	});

	const addUser = (userRequested) => {
		setUser(userRequested);
	};
	return (
		<UserContext.Provider value={{ user, addUser }}>
			{children}
		</UserContext.Provider>
	);
}

export default UserContext;
