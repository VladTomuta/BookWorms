import React,{useEffect, useState} from 'react'
import { useNavigate, useLocation } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import "./AddBookPage.css"

function AddBookPage() {



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

    const id = 40;

    try {
      console.log(location.state.id)
      await axios.post(`http://127.0.0.1:8080/books/${location.state.id}/addBook`, book)
      .then((res) =>
        {
          console.log(res.data);

            navigate("/loggedIn", {state:{id:location.state.id}});
        
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
          <button className='LogInViaFacebookButton' onClick={LogInViaFacebook}>Cancel</button>
        </form>
      </div>
    </div>
  )
}
 
export default AddBookPage