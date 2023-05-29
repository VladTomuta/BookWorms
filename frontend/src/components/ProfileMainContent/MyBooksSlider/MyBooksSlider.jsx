import React, { useState, useEffect } from 'react'
import './MyBooksSlider.css'
import ImagePlaceholder from '../images/No-Image-Placeholder.svg.png'
import BookCard from '../../BookCard/BookCard'
import {getBooksOwnedByUser} from '../../../apis'


export default function MyBooksSlider() {
    
    const [books, setBooks] = useState();
    const user = JSON.parse(sessionStorage.getItem('user'));

    useEffect(() =>{
        const getBooksProperly = async () => {
            const response = await getBooksOwnedByUser(user.user_id);
            setBooks(response);
        }
        getBooksProperly();
    },[])

  return (
    <div className='MyBooksSliderContainer'>
        <span id='MyBooksTitle'>My books</span>
        <div className='BooksSlider'>
            {books ? (books.map(b => {
                return <BookCard
                key = {b.id}
                image= {ImagePlaceholder}
                description={b.description}
                title={b.title}
                author={b.author}
                />
            })) : 
            <p>loading</p>}
        </div>
    </div>
  )
}
