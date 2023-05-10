import React from 'react'
import './BookCard.css'

export default function BookCard(props) {
    const {title, image, description, author} = props;
    console.log(image);
  return (
    <div className='BookCard-Container'>
        <span id='BookCard-Title'>{title}</span>
        <div className='bookImage' style={{backgroundImage:`url(${image})`, width:"200px", height:"250px"}} />
        <span id='Author'><span style={{fontSize:"12px", color:'#dcdcdc'}}>Written by:</span> {author}</span>
        <span id='Description'><span style={{fontSize:"12px", color:'#dcdcdc'}}>short description:</span> {description}</span>
    </div>
  )
}
