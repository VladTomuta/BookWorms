import React from 'react'
import './MyBooksSlider.css'
import bookCardImage from '../images/image-book-card.jpg'
import BookCard from '../../BookCard/BookCard'

const books = [
{
    id:1,
    image:bookCardImage,
    title:"Harap-Alb",
    description:"This is the book about Harap-Alb's story",
    author:"Ion Creanga"
},
{
    id:2,
    image:bookCardImage,
    title:"Colt-Alb",
    description:"This is the book about Colt-Alb's story",
    author:"Jack London"
}]

export default function MyBooksSlider() {
  return (
    <div className='MyBooksSliderContainer'>
        <span id='MyBooksTitle'>My books</span>
        <div className='BooksSlider'>
            {books.map(b => {
                return <BookCard
                key = {b.id}
                image={b.image}
                description={b.description}
                title={b.title}
                author={b.author}
                />
            })}
        </div>
    </div>
  )
}
