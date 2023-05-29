import React from 'react'
import './ProfileMainContent.css'
import MyBooksSlider from './MyBooksSlider/MyBooksSlider'
import UserReviews from '../UserReviews/UserReviews';

export default function ProfileMainContent() {

  return (
    <div className='ProfileMainContentContainer'>
        <MyBooksSlider/>
        <UserReviews/>
    </div>
  )
}
