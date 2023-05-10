import React from 'react'
import {useNavigate} from 'react-router-dom'
import "./HorizontalMenu.css"
 
function HorizontalMenu() {

  const navigate = useNavigate();

  return (
    <div className='horizontal-menu-container'>
        <button className='button-menu' onClick={() => navigate("/books")}>Available Books</button>
        <button className='button-menu' onClick={() => navigate("/aboutUs")}>About Us</button>
        <button className='button-menu' onClick={() => navigate("/signup")}>Sign Up</button>
        <button className='button-menu' onClick={() => navigate("/login")}>Login</button>
    </div>
  )
}

export default HorizontalMenu