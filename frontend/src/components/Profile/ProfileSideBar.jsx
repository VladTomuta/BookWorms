import React, {useEffect} from 'react'
import './ProfileSideBar.css'
import {useNavigate} from 'react-router-dom'


function ProfileSideBar() {

  const navigate = useNavigate();

  const user = JSON.parse(sessionStorage.getItem('user'));

  return (
    <div className='Profile-Container'>
        <div className='profilePicture'>
            <span id='profile-picture-placeholder'>{user.username}</span>
        </div>
        <span id='below-profilePic' style={{padding:"0px 10px"}}>{user.username}</span>
        <div className='profileButtonsContainer'>
          <div className='profile-button'>
              <span className='buttonTitle'>My Profile</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle' onClick={() => navigate("/availableBooks")}>Find Books</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle' onClick={() => navigate("/addBook")}>Put a Book yourself</span>
          </div>
        </div>
        <div className='logOutContainer'>
          <button id='logOutButton' onClick={() => navigate("/")}>Log Out</button>
        </div>
    </div>
  )
}

export default ProfileSideBar