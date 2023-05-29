import React, { useState, useEffect } from 'react'
import './MyBooksSlider.css'
import ImagePlaceholder from '../images/No-Image-Placeholder.svg.png'
import BookCard from '../../BookCard/BookCard'
import {getBooksOwnedByUser} from '../../../apis'

import {Carousel} from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';

export default function MyBooksSlider() {
    
    const [books, setBooks] = useState();

    const user = JSON.parse(sessionStorage.getItem('user'));
    const token = JSON.parse(sessionStorage.getItem('user_token'));

    useEffect(() =>{
        const getBooksProperly = async () => {
            const response = await getBooksOwnedByUser(user.user_id,token);
            setBooks(response);
        }
        getBooksProperly();
    },[])

    console.log(books);
  return (
    <div className='MyBooksSliderContainer'>
        <span id='MyBooksTitle'>My books</span>
        <div className='BooksSlider'>
        <Carousel width="300px" showIndicators={false}>
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
        </Carousel> 
        </div>
    </div>
  )
}
