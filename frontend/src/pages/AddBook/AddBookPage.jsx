import React,{useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import './AddBookPage.css'

function AddBookPage() {

    const user = JSON.parse(sessionStorage.getItem('user'));

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
    console.log(book)

    try {
      console.log(user);
      console.log(user.user_id);
      await axios.post(`http://127.0.0.1:8080/books/addBook/${user.user_id}`, book)
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

  useEffect(() =>{
    console.log(book);
  },[book]);
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