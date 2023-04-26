import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [users, setUsers] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://127.0.0.1:8080/users/getAllUsers");
    setUsers(result.data);
  };

  const deleteUser = async (id) => {
    await axios.delete(`http://127.0.0.1:8080/users/deleteUser/${id}`);
    loadUsers();
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Nr.</th>
              <th scope="col">Id</th>
              <th scope="col">Full Name</th>
              <th scope="col">Username</th>
              <th scope="col">Email</th>
              <th scope="col">Phone Number</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {users
                .filter(user => user.role === "USER")
                .map((user, index) => (
                    <tr key={user.user_id}>
                        <th scope="row">
                            {index + 1}
                        </th>
                        <td>{user.user_id}</td>
                        <td>{user.fullName}</td>
                        <td>{user.username}</td>
                        <td>{user.email}</td>
                        <td>{user.phoneNumber}</td>
                        <td>
                            <Link
                                className="btn btn-outline-primary mx-2"
                                to={`/edituser/${user.user_id}`}
                            >
                                Edit
                            </Link>
                            <button
                                className="btn btn-danger mx-2"
                                onClick={() => deleteUser(user.user_id)}
                            >
                            Delete
                            </button>
                        </td>
                    </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}