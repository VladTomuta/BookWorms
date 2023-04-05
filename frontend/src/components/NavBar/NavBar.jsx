import React from 'react'
import "./NavBar.css"

function NavBar() {
  return (
    <div className='navBar'>
        <a className='logo' href='./'>LOGO</a>
        <div className='menu'>
        <a href='./login'>Log In</a>
        <a href='./signup'>Sign up</a>
        </div>
        </div>
  )
}

export default NavBar