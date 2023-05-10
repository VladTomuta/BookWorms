import React from 'react'
import './ProfileSideBar.css'
import {useNavigate, useLocation} from 'react-router-dom'
import UserContext from '../../pages/LogIn/UserContext/UserContext';
import { useContext } from 'react';


function ProfileSideBar() {

  const location = useLocation();
  const navigate = useNavigate();

  const {user} = useContext(UserContext);
  
  return (
    <div className='Profile-Container'>
        <div className='profilePicture'>
            <span id='profile-picture-placeholder'>{user.username}</span>
        </div>
        <span id='below-profilePic'>{user.email}</span>
        <div className='profileButtonsContainer'>
          <div className='profile-button'>
              <span className='buttonTitle'>My Profile</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle'>Find Books</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle' onClick={() => navigate("/addBook", {state:{id:location.state.id}})}>Put a Book yourself</span>
          </div>
        </div>
        <div className='logOutContainer'>
          <button id='logOutButton' onClick={() => navigate("/")}>Log Out</button>
        </div>
    </div>
  )
}

export default ProfileSideBar