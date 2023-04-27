import React from 'react'
import './LoggedInHomePage.css'
import ProfileSideBar from '../../components/Profile/ProfileSideBar'
import bookCardImage from './images/image-book-card.jpg'

function LoggedInHomePage() {
  return (
    <div className='LoggedInHomePage-Container'>
        <ProfileSideBar />
        <div className='LoggedInContentContainer'>
            <div className='MyBooksSlider'>
                <span id='MyBooksTitle'>My Books</span>
                <div className='BookCardContainer'>
                    <div className='BookCard'>
                        <div id='bookCardImage' style={{backgroundImage:`url(${bookCardImage})`}}/>
                        <span id='BookCardTitle'>Book Title</span>
                        <span id='BookCardGenre'> Book Genre</span>
                    </div>
                    <div className='BookCard'>
                        <div id='bookCardImage' style={{backgroundImage:`url(${bookCardImage})`}}/>
                        <span id='BookCardTitle'>Book Title</span>
                        <span id='BookCardGenre'> Book Genre</span>
                    </div>
                    <div className='BookCard'>
                        <div id='bookCardImage' style={{backgroundImage:`url(${bookCardImage})`}}/>
                        <span id='BookCardTitle'>Book Title</span>
                        <span id='BookCardGenre'> Book Genre</span>
                    </div>
                </div>
            </div>
            <div style={{width:"100%", height:"100%"}}>
                <span style={{ fontSize:"55px"}}>Rest of the content</span>
            </div>
        </div>
    </div>
  )
}

export default LoggedInHomePage