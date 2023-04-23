import React from 'react'
import {useNavigate} from 'react-router-dom'
import "./HorizontalMenu.css"
 
function HorizontalMenu() {

  const navigate = useNavigate();

  return (
    <div className='horizontal-menu-container'>
        <button className='button1' onClick={() => navigate("/books")}>Available Books</button>
        <button className='button2' onClick={() => navigate("/aboutUs")}>About Us</button>
        <button className='button3' onClick={() => navigate("/signup")}>Sign Up</button>
        <button className='button4' onClick={() => navigate("/login")}>Login</button>
    </div>
  )
}

export default HorizontalMenu