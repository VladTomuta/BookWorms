import React,{useState} from 'react'
import { useNavigate, useLocation } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
//import "./AddBookPage.css"
import UserContext from '../../pages/LogIn/UserContext/UserContext';
import {useContext} from 'react';

function AddBookPage() {

//    const user = JSON.parse(sessionStorage.getItem('user'));
    const {user} = useContext(UserContext);

    const [book, setBook] = useState(
      {
        title:"",
        author:"",
        genre:""
      }
    )

    const{title,author,genre}=book

    const onInputChange=(e)=>{
        setBook({...book,[e.target.name]:e.target.value})
    }

    const location = useLocation();
    const navigate = useNavigate();
    
    const LogInViaFacebook = (e) =>{
      return true
    }

    const LogInViaGoogle = (e) =>{
      return true
    }

  const handleOnAddBookButton = async (e) => {
    e.preventDefault()
    console.log(book)

    try {
      console.log(user);
      console.log(user.user_id);
      await axios.post(`http://127.0.0.1:8080/books/${user.user_id}/addBook`, book)
      .then((res) =>
        {
          console.log(res.data);

            navigate("/loggedIn");
        
      }, fail => {
        console.error(fail); // Error!
         });
    }
      catch (err) {
      alert(err);
    }
  }

  const navigateToHomePage = () => {
    navigate("/");
  }

  const navigateToLoggedInPage = () => {
    navigate("/loggedIn");
  }

  return (
    <div className='PageContainer'>
      <div className='logoContainer'>
        <a  onClick={navigateToHomePage}><img id="SignUpPagelogo" src={YourSvg} alt="Your SVG" /></a>
      </div>
      <div className='signUpFormContainer'>
        <form className='signUpForm'>
          <span className='signUpText'>Add Book</span>
          <input id='signUpInput' type="text" placeholder='title' name='title' value={title} onChange={(e)=>onInputChange(e)}></input>
          <input id='signUpInput' type="text" placeholder='author' name='author' value={author} onChange={(e)=>onInputChange(e)}></input>
          <input id='signUpInput' type="text" placeholder='genre' name='genre' value={genre} onChange={(e)=>onInputChange(e)}></input>
          
          <button className='signUpButton' onClick={handleOnAddBookButton}>Add Book</button>
          <div className='line'></div>
          <button className='LogInViaFacebookButton' onClick={navigateToLoggedInPage}>Cancel</button>
        </form>
      </div>
    </div>
  )
}
 
export default AddBookPage