import React from 'react'
import './ProfileSideBar.css'
function ProfileSideBar() {
    const username = "Profile Picture"
  return (
    <div className='Profile-Container'>
        <div className='profilePicture'>
            <span id='profile-picture-placeholder'>{username}</span>
        </div>
        <span id='below-profilePic'>{username}</span>
        <div className='profileButtonsContainer'>
          <div className='profile-button'>
              <span className='buttonTitle'>My Profile</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle'>Find Books</span>
          </div>
          <div className='profile-button'>
              <span className='buttonTitle'>Put a Book yourself</span>
          </div>
        </div>
        <div className='logOutContainer'>
          <button id='logOutButton'>Log Out</button>
        </div>
    </div>
  )
}

export default ProfileSideBar