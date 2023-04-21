import React from 'react'
import './PageDescription.css'
import booksImage from '../../assets/booksImage.jpg'


function PageDescription() {
  return (
    <div className='page-description'>
        <img src={booksImage} id='booksImage' alt='booksImage'/>
        <div className='text-container'>
          <span id='title'>BookWorm comes handy for everybody</span>
          <span id='description'>Welcome to BookWorm, the app that lets you share your love of reading with others! With BookWorm, you can easily rent books from other users in your community, without ever having to buy them. Whether you're looking for the latest bestseller, a classic novel, or a rare gem, you're sure to find it on BookWorm.</span>
          <span id='description'>Here's how it works: simply sign up for a free account and start browsing our catalog of available books. When you find a book you want to read, just request it from the owner. They'll receive a notification and can accept or decline your request. If they accept, you'll be able to pick up the book from them or arrange a delivery.</span>
          <span id='description'>And when you're finished reading, just return the book to the owner and rate your experience. You can also list your own books for others to rent, and earn some extra cash or just share your love of literature.</span>
          <span id='description'>Join the BookWorm community today and start exploring a world of books!</span>
        </div>
      </div>
  )
}

export default PageDescription