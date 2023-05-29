import React from 'react'
import './BookCard.css'

export default function BookCard(props) {
    const {author, image, title} = props;
  return (
    <div className='BookCard-Container'>
        <span id='BookCard-Title'>{title}</span>
        <div className='bookImage' style={{backgroundImage:`url(${image})`, width:"160px", height:"200px"}} />
        <span id='Author'><span style={{fontSize:"12px", color:'#dcdcdc'}}>Written by:</span> {author}</span>
    </div>
  )
}
