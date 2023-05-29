import axios from "axios";

export const getBooksOwnedByUser = async (userId, token) => {
	const headers = {
		Authorization: `Bearer ${token}`,
	};
	try {
		const response = await axios.get(
			`http://127.0.0.1:8080/users/booksOwned/${userId}`,
			{ headers: headers }
		);
		console.log(response.data);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};

export const getAllBooks = async (token) => {
	const headers = {
		Authorization: `Bearer ${token}`,
	};

	try {
		const response = await axios.get(
			`http://127.0.0.1:8080/books/getAllBooks`,
			{ headers: headers }
		);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};
