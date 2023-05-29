import React,{useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import './AddBookPage.css'
import { addBook } from '../../apis';

function AddBookPage() {

    const user = JSON.parse(sessionStorage.getItem('user'));
    const token =JSON.parse(sessionStorage.getItem('user_token'));

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
        console.log(book);
    }

    const navigate = useNavigate();
    

  const handleOnAddBookButton = async (e) => {
    e.preventDefault()
    
    const response = await addBook(token,user.user_id,book);
    console.log("success");

    navigate("/loggedIn");
  }

  const navigateToHomePage = () => {
    navigate("/");
  }

  const navigateToLoggedInPage = () => {
    navigate("/loggedIn");
  }

  return (
    <div className='AddBookPageContainer'>
      <div className='addBookLogoContainer'>
        <a  onClick={navigateToHomePage}><img id="AddBookLogo" src={YourSvg} alt="Your SVG" /></a>
      </div>
      <div className='addBookFormContainer'>
        <form className='addBookForm'>
          <span className='addBookText'>Add Book</span>
          <input id='addBookInput' type="text" placeholder='title' name='title' value={title} onChange={(e)=>onInputChange(e)}></input>
          <input id='addBookInput' type="text" placeholder='author' name='author' value={author} onChange={(e)=>onInputChange(e)}></input>
          <input id='addBookInput' type="text" placeholder='genre' name='genre' value={genre} onChange={(e)=>onInputChange(e)}></input>
          
          <button className='addBookButton' onClick={handleOnAddBookButton}>Add Book</button>
          <button className='CancelButton' onClick={navigateToLoggedInPage}>Cancel</button>
        </form>
      </div>
    </div>
  )
}
 
export default AddBookPage