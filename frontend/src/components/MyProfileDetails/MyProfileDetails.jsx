import React from 'react'
import "./MyProfileDetails.css"

export default function MyProfileDetails() {
    const user = JSON.parse(sessionStorage.getItem('user'));
    return (
    <div className='MyProfileDetailsContainer'>
        {user.username}
    </div>
  )
}
