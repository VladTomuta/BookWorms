import axios from "axios";

export const getBooksOwnedByUser = async (userId) => {
	try {
		const response = await axios.get(
			`http://127.0.0.1:8080/users/booksOwned/${userId}`
		);
		console.log(response.data);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};

export const getAllBooks = async () => {
	try {
		const response = await axios.get(`http://127.0.0.1:8080/books/getAllBooks`);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};
