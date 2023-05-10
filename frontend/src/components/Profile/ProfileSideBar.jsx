import React, {useEffect} from 'react'
import './ProfileSideBar.css'
import {useNavigate} from 'react-router-dom'
import UserContext from '../../pages/LogIn/UserContext/UserContext';
import {useContext} from 'react';


function ProfileSideBar() {

  const navigate = useNavigate();

  const {user} = useContext(UserContext);

  const userObj = JSON.parse(sessionStorage.getItem('user'));

  useEffect(() => {
  }, [user]);

  
  return (
    <div className='Profile-Container'>
        <div className='profilePicture'>
            <span id='profile-picture-placeholder'>{userObj.username}</span>
        </div>
        <span id='below-profilePic'>{userObj.email}</span>
        <div className='profileButtonsContainer'>
          <div className='profile-button'>
              <span className='buttonTitle'>My Profile</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle'>Find Books</span>
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