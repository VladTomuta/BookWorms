import React, {useState, useEffect} from 'react'
import './ProfileSideBar.css'
import {useNavigate} from 'react-router-dom'


function ProfileSideBar() {

  const navigate = useNavigate();

  const user = JSON.parse(sessionStorage.getItem('user'));

  const handleLogOutButton = () => {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('user_token');
    navigate("/");
  }

  const handleMyProfileButton = () => {
    return true;
  }

  return (
    <div className='Profile-Container'>
        <div className='profilePicture'>
            <span id='profile-picture-placeholder'>{user.username}</span>
        </div>
        <span id='below-profilePic' style={{padding:"0px 10px"}}>{user.username}</span>
        <div className='profileButtonsContainer'>
          <div className='profile-button'>
              <span className='buttonTitle' onClick={() => {handleMyProfileButton()}}>My Profile</span>
          </div>
          <div className='profile-button' onClick={() => navigate("/availableBooks")}>
              <span className='buttonTitle'>Find Books</span>
          </div>
          <div className='profile-button' onClick={() => navigate("/addBook")}>
              <span className='buttonTitle'>Put a Book yourself</span>
          </div>
        </div>
        <div className='logOutContainer'>
          <button id='logOutButton' onClick={() => {handleLogOutButton()}}>Log Out</button>
        </div>
    </div>
  )
}

export default ProfileSideBar