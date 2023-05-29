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

export const getAllAvailableBooks = async (token, userId) => {
	const headers = {
		Authorization: `Bearer ${token}`,
	};

	try {
		const response = await axios.get(
			`http://127.0.0.1:8080/books/getAllAvailableBooks/${userId}`,
			{ headers: headers }
		);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};

export const addBook = async (token, userId, book) => {
	const headers = {
		Authorization: `Bearer ${token}`,
	};

	try {
		const response = await axios.post(
			`http://127.0.0.1:8080/books/addBook/${userId}`,
			book,
			{ headers: headers }
		);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};

//getAllTheReviewsOfAUser

export const getAllTheReviewsOfAUser = async (token, userId) => {
	const headers = {
		Authorization: `Bearer ${token}`,
	};

	try {
		const response = await axios.get(
			`http://127.0.0.1:8080/profileReviews/getAllProfileReviewsForUser/${userId}`,
			{ headers: headers }
		);
		return response.data;
	} catch (err) {
		alert(err);
		return null;
	}
};
