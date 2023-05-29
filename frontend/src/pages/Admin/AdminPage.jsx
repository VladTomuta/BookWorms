import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import UserContext from '../../pages/LogIn/UserContext/UserContext';
import {useContext} from 'react';
import { useNavigate } from 'react-router-dom';

export default function Home() {
  const [users, setUsers] = useState([]);

  const { id } = useParams();

  //trebuie sa iei userul salvat local (ii la fel daca ai schimbat din useContext in local storage)
  const {user} = useContext(UserContext);

  //token-ul asta ii hard codat ca sa verific eu daca merge
  //trebuie luat si el de unde ii stocat local
  //il primesti in raspuns cand dai login
  //ti-am pus si acolo comentariu de unde sa il iei
  const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29rd29ybXNAZ21haWwuY29tIiwiaWF0IjoxNjg1MzQ5MzExLCJleHAiOjE2ODUzNTA3NTF9.4MutfBfxQ2jbSKhU1eRGlvtEG7Fg9F6uUeOhpC2Ozhw"

  //trebuie sa construiesti un header pentru mesaj care sa fie de forma asta
  //doar il copiezi de aici unde iti trebuie
  const headers = {
    'Authorization': `Bearer ${token}`
  }

  const navigate = useNavigate();

  console.log(headers);

  useEffect(() => {
    //la fiecare load de pagina mai intai verifici care ii rolul user-ului
    checkUserRole();

    //daca userul are access la pagina regenereaza token-ul ca sa nu mai ai sesiuni de doar 15 minute
    regenerateJwtToken();

    loadUsers();
  }, []);

  const checkUserRole = async () => {
    console.log(user.user_id)

    try {
      //ti-am facut un api care iti da direct rolul user-ului
      const role = await axios.get(`http://127.0.0.1:8080/users/getRoleOfUser/${user.user_id}`, { headers: headers});

      console.log(role);

      //verifici daca ii ce trebuie, daca nu il duci la login sa se autetifice
      if(role.data != "ADMIN") {
        navigate("/login");
      }
    } catch(err) {
      //asta ii in caz in care scrii tu url pagini si nu te loghezi
      //get-ul o sa dea o eroare ca nu are user_id si te intorci la pagina de login
      console.log("eroare");
      navigate("/login");
    }
    
  }

  const regenerateJwtToken = async () => {
    try {
      //regenereaza jwt token-ul
      const token = await axios.get(`http://127.0.0.1:8080/users/getRoleOfUser/${user.user_id}`, { headers: headers});

      console.log(token);

      //trebuie sa il stochezi local
      
    } catch(err) {
      //asta ii in caz in care scrii tu url pagini si nu te loghezi
      //get-ul o sa dea o eroare ca nu are user_id si te intorci la pagina de login
      console.log("eroare");
      navigate("/login");
    }
    
  }

  const loadUsers = async () => {
    const result = await axios.get("http://127.0.0.1:8080/users/getAllUsers", { headers: headers });
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